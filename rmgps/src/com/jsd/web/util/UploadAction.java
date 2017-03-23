package com.jsd.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.jsd.web.login.vo.UserVO;
import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport{
	// 封装上传文件域的属性
    private File image;
    private OperateImage image2;
    private String uid;
    // 封装上传文件类型的属性
    private String imageContentType;
    // 封装上传文件名的属性
    private String imageFileName;
    private String imagePath;
    private String message;

    private static final long serialVersionUID = -3455411632907896807L;  
    public void upload() throws Exception{
    	String savePath = ServletActionContext.getServletContext().getRealPath("/upload");
    	createAndDeleteFolder(savePath+"/"+uid);
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            // 建立文件输出流
            fos = new FileOutputStream(savePath+"/"+uid+"/"+ getImageFileName());
            // 建立文件上传流
            fis = new FileInputStream(getImage());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            imagePath = savePath+"/"+uid+"/"+imageFileName;
    		clearResponse(imageFileName);
            setMessage(imagePath);
        } catch (Exception e) {
        	clearResponse("文件上传失败");
            e.printStackTrace();
        } finally {
            close(fos, fis);
        }
    }

	public void cutPic() throws Exception {
    	String savePath = ServletActionContext.getServletContext().getRealPath("/upload/");
    	createAndDeleteFolder(savePath+"/"+uid+"/buffer");
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		String imgFileName2 = format1.format(new Date())+imageFileName.toString();
		if(imagePath == null || imagePath.equals(""))
			return;
		image2.setSrcpath(imagePath);
		image2.setSubpath(ServletActionContext.getServletContext().getRealPath("/upload/"+uid+"/buffer/"+imgFileName2));
		String ext=imgFileName2.substring(imgFileName2.lastIndexOf(".")+1);
		image2.setExt(ext);
		try {
			image2.cut(); // 执行裁剪操作 执行完后即可生成目标图在对应文件夹内。
			UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession().getAttribute("USER");
			String logoPath="/upload/"+uservo.getUserId()+"/buffer/"+imgFileName2;
			uservo.setLogoPath(logoPath);
			ServletActionContext.getRequest().setAttribute("USER", uservo);
			List<String> list=new ArrayList<String>();
			list.add(logoPath);
			list.add("图标设置成功！");
			JSONArray jsonArray=JSONArray.fromObject(list);
			clearResponse(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
			clearResponse("系统繁忙，请稍后重试！");
		}
	}

	public void clearResponse(Object result) throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setHeader("Charset", "utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}

	/**
	 * 创建和删除目录
	 * @param folderName
	 * @param filePath
	 * @return 删除成功返回true
	 */
	public boolean createAndDeleteFolder(String filePath)
	{
		boolean result = false;
		try{
			File file = new File(filePath);
			if (file.exists()){
				DeleteFolder(filePath);
				file.mkdir();//目录不存在，已经建立!
			}else{
				file.mkdir();//目录不存在，已经建立!
			}
			result = true;
		}catch (Exception ex){
			result = false;
			//System.out.println("CreateAndDeleteFolder is error:" + ex);
		}
		return result;
	}
    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param sPath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public boolean DeleteFolder(String sPath) {
    	boolean result = false;
    	File file = new File(sPath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return result;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }
    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public boolean deleteFile(String sPath) {
    	boolean result = false;
    	File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            result = true;
        }
        return result;
    }
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public boolean deleteDirectory(String sPath) {
    	boolean result = false;
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        result = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
            	result = deleteFile(files[i].getAbsolutePath());
                if (!result) break;
            } //删除子目录
            else {
            	result = deleteDirectory(files[i].getAbsolutePath());
                if (!result) break;
            }
        }
        if (!result) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    private void close(FileOutputStream fos, FileInputStream fis) {
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println("FileInputStream关闭失败");
                e.printStackTrace();
            }
        }
        if (fos != null) {
            try {
                fos.close();
            } catch (IOException e) {
                System.out.println("FileOutputStream关闭失败");
                e.printStackTrace();
            }
        }
    }

	public OperateImage getImage2() {
		return image2;
	}

	public void setImage2(OperateImage image2) {
		this.image2 = image2;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}