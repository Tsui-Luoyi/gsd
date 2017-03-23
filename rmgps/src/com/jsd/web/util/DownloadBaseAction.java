package com.jsd.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class DownloadBaseAction {
	
	/**
	 * @param filePath 要下载的文件路径
	 * @param returnName 返回的文件名
	 * @param response HttpServletResponse
	 * @param delFlag 是否删除文件
	 */
	protected void download(String filePath,String returnName,HttpServletResponse response,boolean delFlag){
		this.prototypeDowload(new File(filePath), returnName, response, delFlag);
	}


	/**
	 * @param file 要下载的文件
	 * @param returnName 返回的文件名
	 * @param response HttpServletResponse
	 * @param delFlag 是否删除文件
	 */
	protected void download(File file,String returnName,HttpServletResponse response,boolean delFlag){
		this.prototypeDowload(file, returnName, response, delFlag);
	}
	
	/**
	 * @param file 要下载的文件
	 * @param returnName 返回的文件名
	 * @param response HttpServletResponse
	 * @param delFlag 是否删除文件
	 */
	public void prototypeDowload(File file,String returnName,HttpServletResponse response,boolean delFlag){
		// 下载文件
		FileInputStream inputStream = null;
		ServletOutputStream outputStream = null;
		try {
			if(!file.exists()) return;
			response.reset();   
			//设置响应类型
			response.setContentType("application/octet-stream");   
			//设置响应的文件名称,并转换成中文编码
			returnName = response.encodeURL(new String(returnName.getBytes(),"iso8859-1"));	//保存的文件名,必须和页面编码一致,否则乱码
			response.addHeader("Content-Disposition",   "attachment;filename="+returnName);  
			//将文件读入响应流
			inputStream = new FileInputStream(file);
			
			outputStream = response.getOutputStream();
			int length = 1024;
			int readLength=0;
			byte buf[] = new byte[1024];
			readLength = inputStream.read(buf, 0, length);
			while (readLength != -1) {
				outputStream.write(buf, 0, readLength);
				readLength = inputStream.read(buf, 0, length);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.flush();
			} catch (IOException e) {
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			try {
				inputStream.close();
			} catch (IOException e) {
			}
			//删除原文件
			if(delFlag) {				
				file.delete();
			}
		}
	}
}
