package com.jsd.web.vehicle.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.jsd.util.PageRequestBean;
import com.jsd.util.PageResponseBean;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.po.Tuser;
import com.jsd.web.util.DownloadBaseAction;
import com.jsd.web.util.FileUtil;
import com.jsd.web.util.PioUtil;
import com.jsd.web.vehicle.po.VehicleListPO;
import com.jsd.web.vehicle.service.VehicleMnService;
import com.jsd.web.vehicle.service.VehicleService;
import com.jsd.web.vehicle.vo.AjaxRemarkerBean;
import com.jsd.web.vehicle.vo.RemarkerVO;
import com.jsd.web.vehicle.vo.VehicleMapInfoVO;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

/**
 * @author limq
 */
@SuppressWarnings({"serial","rawtypes"})
public class VehicleAction extends ActionSupport {

	private VehicleService vehicleService;
	private List vehicleInfoList;

	private List vTypeList;//从数据库取得绑定到客户端整机型号下拉框
	private List agentList;//代理商列表
	private String queryVno;//整机编号
	private String queryVModel;//整机型号
	private String simnum;//手机号
	private String gsmstr;//信号是否异常
	private String locatef;//定位是否成功
	private String agentn;//代理商用户
	private String dno;//终端编号

	// ------在地图上添加标注时使用---------
	private String remarkerLat;	// 地图标注的纬度
	private String remarkerLng;	// 地图标注的经度
	private String remarkerInfo; // 地图标注的信息内容
	private String remarkerId; // 地图标注的索引
	private String remarkerTitle; // 地图标注的标题

	// 历史查询时使用
	private String rcmTimeStart;
	private String rcmTimeEnd;
	private List vhistoryInfoList;

	/*-------------------*/
	private VehicleMnService vehicleMnService;
	private List vnolist;
	public VehicleMnService getVehicleMnService() {
		return vehicleMnService;
	}
	public void setVehicleMnService(VehicleMnService vehicleMnService) {
		this.vehicleMnService = vehicleMnService;
	}
	public List getVnolist() {
		return vnolist;
	}
	public void setVnolist(List vnolist) {
		this.vnolist = vnolist;
	}
	/*----------Method---------*/
	/**
	 * 在地图上添加标注
	 * @author Jnhuy
	 */
	public String addRemarker() {
		RemarkerVO remarker = new RemarkerVO();
		remarker.setRemarkerLat(remarkerLat);
		remarker.setRemarkerLng(remarkerLng);
		remarker.setRemarkerInfo(remarkerInfo);
		remarker.setRemarkerId(remarkerId);
		remarker.setRemarkerTitle(remarkerTitle);

		UserVO loginUser = (UserVO) ServletActionContext.getRequest().getSession().getAttribute("USER");
		Tuser tuser = new Tuser();
		tuser.setUserId(loginUser.getUserId());
		remarker.setTuser(tuser);

		vehicleService.addRemarker(remarker);
		return "addremarkerSUCCESS";
	}

	/**
	 * 删除地图标注
	 * removeRemarker
	 * @return
	 * @author Jnhuy
	 */

	public String removeRemarker() {
		RemarkerVO remarker = new RemarkerVO();
		remarker.setRemarkerId(remarkerId);
		vehicleService.removeRemarker(remarker);
		return "removeremarkerSUCCESS";
	}

	/**
	 * 查询地图标注
	 * queryRemarker
	 * @return
	 * @author Jnhuy
	 */
	public String queryRemarker(){
		UserVO loginUser = (UserVO) ServletActionContext.getRequest().getSession().getAttribute("USER");
		int userId = loginUser.getUserId();
		List<RemarkerVO> remarkerlist = vehicleService.queryRemarker(userId);
		remarkerBean = new AjaxRemarkerBean();
		remarkerBean.setRemarkerlist(remarkerlist);
		return "queryRemarkerSUCCESS";
	}
	private AjaxRemarkerBean remarkerBean;
	public AjaxRemarkerBean getRemarkerBean() {
		return remarkerBean;
	}

	/******************尹志刚,陈一雄;完成hnzx整机列表任务,excel下载任务**********************/

	// 查询用户（Ajax 分页） =======================
	private int page;		// 页码
	private int rows;		// 每页多少条
	private String sort;	//排序字段
	private String order;	//asc|desc

	private PageResponseBean pageResponseBean;
	private String vname_list;			//查询车辆编号字段
	private String dno_list;			//查询终端编号字段
	private String agent_id_list;		//查询代理商字段
	private Integer offcount;			//用于获取离线时间字段

	//显示车辆列表
	public String home(){
		//得到登陆用户的信息
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession().getAttribute("USER");
		//根据用户来查询用户下的代理商
		this.agentList = this.vehicleService.queryAgentList2(uservo);
		return "home";
	}

	public String queryVehicleList2() {
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession().getAttribute("USER");
		// 将分页和排序参数保存 PageRequestBean
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		pageRequestBean.setOrder(order);
		pageRequestBean.setSort(sort);

		VehicleListPO vlp = new VehicleListPO();
		if(vname_list!=null && vname_list.length()>0){
			try {
				vlp.setVname_list(new String(vname_list.getBytes("UTF-8"),"ISO-8859-1"));//处理中文
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				System.out.println("vname_list"+"字符处理有误");
			}
		}
		vlp.setDno_list(dno_list);
		vlp.setAgent_id_list((agent_id_list));
		vlp.setOffcount(offcount);

		//根据用户userid,来查询tagent_tuser表
		List list = vehicleService.getAgentListByUserId(uservo.getUserId().toString());
		String orgId = uservo.getTorgTable().getOrgId().toString();
		// 调用业务层
		if(list!=null && list.size()>0){//如果list不为null，表示为子代；为null，表示是总代
			//子代理走的业务层
			pageResponseBean = vehicleService.pageQuery(vlp,uservo,pageRequestBean);
		}else{
			//总代理走的业务层
			pageResponseBean = vehicleService.pageQuery(vlp,orgId,pageRequestBean);
		}

		//洗时间格式过程
		VehicleListPO vp =new VehicleListPO();
		for(int i=0;i<pageResponseBean.getRows().size();i++){
			vp=(VehicleListPO) pageResponseBean.getRows().get(i);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(vp.getUpdate_time()!=null){
				vp.setTime(df.format(vp.getUpdate_time()));
			}
		}


		return "vehiclelist2";
	}

	/**
	 * 打印车辆信息列表 1、获取数据 2、POI打印 1）第一步：制作一个模板，打印一个模板文件 2）第二步：定位工作簿 3）第三步：获取数据
	 * 4）第四步：写单元格 5）第五步：设置单元格样式 6）第六步：关闭，保存 7）第七步：下载
	 *
	 * @throws IOException
	 */
	private String clbh;	//车辆编号
	private String xh;		//型号
	private String dlsmc;	//代理商名称
	private String zxsjsj;	//最新数据时间
	private String zdbh;	//终端编号
	private String jj;		//紧急
	private String dlwz;	//地理位置
	private String jpqjd;	//纠偏前的经度
	private String jpqwd;	//纠偏前的纬度
	private String jphjd;	//纠偏后的经度
	private String jphwd;	//纠偏后的纬度

	@SuppressWarnings({ "deprecation", "unused" })
	public void printVehicleList2() throws IOException {
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession().getAttribute("USER");
		// 将分页和排序参数保存 PageRequestBean
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		pageRequestBean.setOrder(order);
		pageRequestBean.setSort(sort);

		VehicleListPO vlp = new VehicleListPO();
		if(vname_list!=null && vname_list.length()>0){
			try {
				vlp.setVname_list(new String(vname_list.getBytes("UTF-8"),"ISO-8859-1"));//处理中文
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				System.out.println("vname_list"+"字符处理有误");
			}
		}
		vlp.setDno_list(dno_list);
		vlp.setAgent_id_list((agent_id_list));
		vlp.setOffcount(offcount);


		String userId = UUID.randomUUID().toString();
		// 获取运行时间
		int y, m, d;
		Calendar cal = Calendar.getInstance();	// Calendar获取年、月、日、时间等
		y = cal.get(Calendar.YEAR);
		m = cal.get(Calendar.MONTH) + 1;
		d = cal.get(Calendar.DATE);

		// 打开模板文件
		// 获取服务器路径
		String root = ServletActionContext.getServletContext().getRealPath("/tmp");

		String xlsFile = root + "/xlsprint/hnzx.xls";
		PioUtil pioUtil = new PioUtil();
		// 打开已存在的excel文件
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(xlsFile));//创建一个工作薄对象
		// 设置全局样式
		HSSFFont songBoldFont16 = pioUtil.songBoldFont16(wb); // 设置字体
		HSSFFont defaultFont10 = pioUtil.defaultFont10(wb); // 设置字体

		// 选择sheet1 索引值0代表第一个工作簿
		HSSFSheet sheet = wb.getSheetAt(0);//创建sheet页
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();//创建图片
		// 对标题和页脚进行设置

		wb.setRepeatingRowsAndColumns(0, 1, 11, 0, 1); // 将第一行作为标题，即每页都打印此行
											// sheetN,startCol,stopCol,startRow,stopRow
		HSSFFooter footer = sheet.getFooter(); // 页脚
		footer.setRight("第" + HSSFFooter.page() + "页 共"
				+ HSSFFooter.numPages() + "页     "); // 页数
		HSSFRow nRow = sheet.createRow(0);			//创建1行
		HSSFCell nCell = nRow.createCell((short) (1));//使用行创建列对象

		String strTitle=uservo.getOrgName() + y + "年" + m + "月" + d + "日";
		nCell.setCellValue(strTitle);
		nCell.setCellStyle(this.title(wb, songBoldFont16));
		float height = pioUtil.getCellAutoHeight(strTitle, 30f);
		nRow.setHeightInPoints(height);

		List l = vehicleService.getAgentListByUserId(uservo.getUserId().toString());
		String orgId = uservo.getTorgTable().getOrgId().toString();
		// 调用业务层
		List<VehicleListPO> list1 = null;
		if(l!=null && l.size()>0){
			//获取子代里的excel下载数据
			list1 = vehicleService.getVehicleListBySub(vlp,uservo,pageRequestBean);
		}else{
			//获取总代理的excel下载数据
			list1 = vehicleService.getVehicleListByAll(vlp,orgId,pageRequestBean);
		}

		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb3 = new StringBuffer();
		StringBuffer sb4 = new StringBuffer();
		StringBuffer sb5 = new StringBuffer();
		StringBuffer sb6 = new StringBuffer();
		StringBuffer sb7 = new StringBuffer();
		StringBuffer sb8 = new StringBuffer();
		StringBuffer sb9 = new StringBuffer();
		for(int i=0;i<list1.size();i++){
			VehicleListPO vehicle = list1.get(i);
			if(list1.size()-1 == i){
				String vname2 = vehicle.getVname();
				String vtype_name2 = vehicle.getVtype_name();
				String agent_name2 = vehicle.getAgent_name();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(vehicle.getUpdate_time()!=null){
					vehicle.setTime(df.format(vehicle.getUpdate_time()));
				}
				String update_time2 = vehicle.getTime();
				String dno2 = vehicle.getDno();
				Integer emergency_state2 = vehicle.getEmergency_state();
				String addr2 = vehicle.getAddr();
				String longitude2 = vehicle.getLongitude();
				String latitude2 = vehicle.getLatitude();
				sb1.append(vname2);
				sb2.append(vtype_name2);
				sb3.append(agent_name2);
				sb4.append(update_time2);
				sb5.append(dno2);
				sb6.append(emergency_state2);
				sb7.append(addr2);
				sb8.append(longitude2);
				sb9.append(latitude2);
			}else{
				String vname2 = vehicle.getVname();
				String vtype_name2 = vehicle.getVtype_name();
				String agent_name2 = vehicle.getAgent_name();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(vehicle.getUpdate_time()!=null){
					vehicle.setTime(df.format(vehicle.getUpdate_time()));
				}
				String update_time2 = vehicle.getTime();
				String dno2 = vehicle.getDno();
				Integer emergency_state2 = vehicle.getEmergency_state();
				String addr2 = vehicle.getAddr();
				String o_lng2 = vehicle.getO_lng();
				String o_lat2 = vehicle.getO_lat();
				String longitude2 = vehicle.getLongitude();
				String latitude2 = vehicle.getLatitude();
				sb1.append(vname2).append(",@,");
				sb2.append(vtype_name2).append(",@,");
				sb3.append(agent_name2).append(",@,");
				sb4.append(update_time2).append(",@,");
				sb5.append(dno2).append(",@,");
				sb6.append(emergency_state2).append(",@,");
				sb7.append(addr2).append(",@,");
				sb8.append(longitude2).append(",@,");
				sb9.append(latitude2).append(",@,");
			}
		}

		clbh = sb1.toString();
		xh = sb2.toString();
		dlsmc = sb3.toString();
		zxsjsj = sb4.toString();
		zdbh = sb5.toString();
		jj = sb6.toString();
		dlwz = sb7.toString();
		jphjd = sb8.toString();
		jphwd = sb9.toString();
		// 拆串
		String lie11[] = clbh.split(",@,");
		String lie22[] = xh.split(",@,");
		String lie33[] = dlsmc.split(",@,");
		String lie44[] = zxsjsj.split(",@,");
		String lie55[] = zdbh.split(",@,");
		String lie66[] = jj.split(",@,");
		String lie77[] = dlwz.split(",@,");
		String lie88[] = jphjd.split(",@,");
		String lie99[] = jphwd.split(",@,");

		//车辆编号
		int rowNum = 2;
		for (int i = 0; i < lie11.length; i++) {
			// 行列信息变量
			nRow = sheet.createRow(rowNum++);
			nRow.setHeightInPoints(18);
			nCell = nRow.createCell((short) (1));

			nCell.setCellValue(lie11[i]);
			String str ="1";
			if(str.equals(lie11[i])){
				System.out.println(lie11[i]);
			}
			str=lie11[i];
		}
		// 型号
		int rowNum2 = 2;
		for (int i = 0; i < lie22.length; i++) {
			// 行列信息变量
			nRow = sheet.createRow(rowNum2++);
			nRow.setHeightInPoints(18);
			nCell = nRow.createCell((short) (2));

			nCell.setCellValue(lie22[i]);
		}
		// 代理商
		int rowNum3 = 2;
		for (int i = 0; i < lie33.length; i++) {
			// 行列信息变量
			nRow = sheet.createRow(rowNum3++);
			nRow.setHeightInPoints(18);
			nCell = nRow.createCell((short) (3));

			nCell.setCellValue(lie33[i]);
		}
		// 最新数据时间
		int rowNum4 = 2;
		for (int i = 0; i < lie44.length; i++) {
			// 行列信息变量
			nRow = sheet.createRow(rowNum4++);
			nRow.setHeightInPoints(18);
			nCell = nRow.createCell((short) (4));

			nCell.setCellValue(lie44[i]);
		}
		// 终端编号
		int rowNum5 = 2;
		for (int i = 0; i < lie55.length; i++) {
			// 行列信息变量
			nRow = sheet.createRow(rowNum5++);
			nRow.setHeightInPoints(18);
			nCell = nRow.createCell((short) (5));

			nCell.setCellValue(lie55[i]);
		}
		//紧急
		int rowNum6 = 2;
		for (int i = 0; i < lie66.length; i++) {
			// 行列信息变量
			// nCell = nRow.createCell((short)(colno++));
			//设置logo图片
			//region = new Region(curRow-1, (short)(1), curRow-1+3, (short)1);	//纵向合并单元格
			//sheet.addMergedRegion(region);
			nRow = sheet.createRow(rowNum6);
			if(lie66[i].trim().equals("1")){
				nRow.setHeightInPoints(18);
				nCell = nRow.createCell((short) (6));

				nCell.setCellValue("");
			pioUtil.setPicture(wb, patriarch, root + "/xlsprint/2.png", rowNum6, 6, rowNum6+1, 6);

			}else{
				nRow.setHeightInPoints(18);
				nCell = nRow.createCell((short) (6));

				nCell.setCellValue("");
			}
			rowNum6++;
		}
		// 地理位置
		int rowNum7 = 2;
		for (int i = 0; i < lie77.length; i++) {
			// 行列信息变量
			nRow = sheet.createRow(rowNum7++);
			nRow.setHeightInPoints(18);
			nCell = nRow.createCell((short) (7));

			nCell.setCellValue(lie77[i]);
		}
		// 纠偏后的经度
		int rowNum8 = 2;
		for (int i = 0; i < lie88.length; i++) {
			// 行列信息变量
			nRow = sheet.createRow(rowNum8++);
			nRow.setHeightInPoints(18);
			nCell = nRow.createCell((short) (8));

			nCell.setCellValue(lie88[i]);
		}
		// 纠偏后的纬度
		int rowNum9 = 2;
		for (int i = 0; i < lie99.length; i++) {
			// 行列信息变量
			nRow = sheet.createRow(rowNum9++);
			nRow.setHeightInPoints(18);
			nCell = nRow.createCell((short) (9));

			nCell.setCellValue(lie99[i]);
		}

		// 设置列宽
		// sheet.setColumnWidth(4,15*2*256);

		// 关闭文件保存
		// 注意保存需要改名避免覆盖 绝对路径
		// 配置文件设置文件
		String dir = root + "/tmpFile";// 临时目录
		// 创建临时目录工具类 创建多级目录方法
		FileUtil.makeDir(dir);
		FileOutputStream fOut = new FileOutputStream(dir + "/" + userId
				+ ".xls");
		wb.write(fOut);
		fOut.flush();
		fOut.close();
		// 文件创建完毕 下载方法

		HttpServletResponse response = ServletActionContext.getResponse();

		DownloadBaseAction down = new DownloadBaseAction();
		/**
		 * @param file 要下载的文件
		 * @param returnName 返回的文件名
		 * @param response HttpServletResponse
		 * @param delFlag 是否删除文件
		 */
		 down.prototypeDowload(new File(dir+ "/"+userId+".xls"),strTitle+".xls", response, true);
	}
	/******************尹志刚,陈一雄;完成hnzx整机列表任务,excel下载任务**********************/

	/**
	 * 查询整机列表的百度地图
	 * @return
	 */
	Double lng=null;
	Double lat=null;

	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	/**
	 * 整机地图方法
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String queryVehicleListBaiduMap(){
		boolean firstFlag=false;
		JSONArray jsonBaiduMutiInfo = null;
		List mutiInfo4Json = new ArrayList();
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession().getAttribute("USER");

		vehicleInfoList = this.vehicleService.queryVehicleBaiduMap(uservo);

		for (int i = 0; i < vehicleInfoList.size(); i++) {

		VehicleMapInfoVO bean = (VehicleMapInfoVO) vehicleInfoList.get(i);
		if(firstFlag==false&&bean.getB_Longitude()!=null&&bean.getB_Latitude()!=null){
			firstFlag=true;
			//设置第一个点为中心
			lng=Double.valueOf(bean.getB_Longitude());
			lat=Double.valueOf(bean.getB_Latitude());
		}

		//放入bean剔除无用数据。
		List l=bean2mutiInfo(bean);
		if(l!=null){
			mutiInfo4Json.add(l);
		}

		}
		jsonBaiduMutiInfo = JSONArray.fromObject(mutiInfo4Json);
		ServletActionContext.getRequest().setAttribute("jsonBaiduMutiInfo", jsonBaiduMutiInfo);
		return "baidusuccess";

	}
	@SuppressWarnings({ "unused", "unchecked" })
	public List bean2mutiInfo(VehicleMapInfoVO bean){
		List listInlist =new ArrayList();
		List trickInlist= new ArrayList();
		if(bean.getB_Longitude()==null||bean.getB_Latitude()==null||bean.getB_Longitude().equals("0")||bean.getB_Latitude().equals("0")
				){
			return null;
		}else{
			listInlist.add(Double.valueOf(bean.getB_Longitude()));
			listInlist.add(Double.valueOf(bean.getB_Latitude()));
			String str = "整机编号："+bean.getVno()+"</br>";


			if(bean.getAddr()==null){
				str+="地址：</br>";
			}else{
				str+="地址："+bean.getAddr()+"</br>";
			}
			listInlist.add(str);
		}
		return listInlist;
	}

	private HSSFCellStyle title(HSSFWorkbook wb, HSSFFont curFont) {
		HSSFCellStyle curStyle = wb.createCellStyle();
		curStyle.setWrapText(false); // 换行
		curStyle.setFont(curFont);

		curStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		curStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 单元格垂直居中

		return curStyle;
	}

	/** **********************get\set*************************** */
	public String getRcmTimeStart() {
		return rcmTimeStart;
	}
	public void setRcmTimeStart(String rcmTimeStart) {
		this.rcmTimeStart = rcmTimeStart;
	}
	public String getRcmTimeEnd() {
		return rcmTimeEnd;
	}
	public void setRcmTimeEnd(String rcmTimeEnd) {
		this.rcmTimeEnd = rcmTimeEnd;
	}
	public List getVTypeList() {
		return vTypeList;
	}
	public void setVTypeList(List typeList) {
		vTypeList = typeList;
	}
	public List getVehicleInfoList() {
		return vehicleInfoList;
	}
	public void setVehicleInfoList(List vehicleInfoList) {
		this.vehicleInfoList = vehicleInfoList;
	}
	public void setVehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}
	public String getQueryVno() {
		return queryVno;
	}
	public void setQueryVno(String queryVno) {
		try {
			this.queryVno = java.net.URLDecoder.decode(queryVno, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	public List getVhistoryInfoList() {
		return vhistoryInfoList;
	}
	public void setVhistoryInfoList(List vhistoryInfoList) {
		this.vhistoryInfoList = vhistoryInfoList;
	}
	public String getQueryVModel() {
		return queryVModel;
	}
	public void setQueryVModel(String queryVModel) {
		this.queryVModel = queryVModel;
	}
	public String getSimnum() {
		return simnum;
	}
	public void setSimnum(String simnum) {
		this.simnum = simnum;
	}
	public String getGsmstr() {
		return gsmstr;
	}
	public void setGsmstr(String gsmstr) {
		this.gsmstr = gsmstr;
	}
	public String getLocatef() {
		return locatef;
	}
	public void setLocatef(String locatef) {
		this.locatef = locatef;
	}
	public String getAgentn() {
		return agentn;
	}
	public void setAgentn(String agentn) {
		this.agentn = agentn;
	}
	public List getAgentList() {
		return agentList;
	}
	public void setAgentList(List agentList) {
		this.agentList = agentList;
	}
	public String getDno() {
		return dno;
	}
	public void setDno(String dno) {
		this.dno = dno;
	}
	public List getvTypeList() {
		return vTypeList;
	}
	public void setvTypeList(List vTypeList) {
		this.vTypeList = vTypeList;
	}
	public void setRemarkerLat(String remarkerLat) {
		this.remarkerLat = remarkerLat;
	}
	public void setRemarkerLng(String remarkerLng) {
		this.remarkerLng = remarkerLng;
	}
	public void setRemarkerInfo(String remarkerInfo) {
		this.remarkerInfo = remarkerInfo;
	}
	public void setRemarkerId(String remarkerId) {
		this.remarkerId = remarkerId;
	}
	public void setRemarkerTitle(String remarkerTitle) {
		this.remarkerTitle = remarkerTitle;
	}
	public Integer getOffcount() {
		return offcount;
	}
	public void setOffcount(Integer offcount) {
		this.offcount = offcount;
	}
	public String getVname_list() {
		return vname_list;
	}
	public void setVname_list(String vname_list) {
		this.vname_list = vname_list;
	}
	public String getDno_list() {
		return dno_list;
	}
	public void setDno_list(String dno_list) {
		this.dno_list = dno_list;
	}
	public String getAgent_id_list() {
		return agent_id_list;
	}
	public void setAgent_id_list(String agent_id_list) {
		this.agent_id_list = agent_id_list;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public PageResponseBean getPageResponseBean() {
		return pageResponseBean;
	}
	public void setPageResponseBean(PageResponseBean pageResponseBean) {
		this.pageResponseBean = pageResponseBean;
	}
	public String getClbh() {
		return clbh;
	}
	public void setClbh(String clbh) {
		this.clbh = clbh;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getDlsmc() {
		return dlsmc;
	}
	public void setDlsmc(String dlsmc) {
		this.dlsmc = dlsmc;
	}
	public String getZxsjsj() {
		return zxsjsj;
	}
	public void setZxsjsj(String zxsjsj) {
		this.zxsjsj = zxsjsj;
	}
	public String getZdbh() {
		return zdbh;
	}
	public void setZdbh(String zdbh) {
		this.zdbh = zdbh;
	}
	public String getJj() {
		return jj;
	}
	public void setJj(String jj) {
		this.jj = jj;
	}
	public String getDlwz() {
		return dlwz;
	}
	public void setDlwz(String dlwz) {
		this.dlwz = dlwz;
	}
	public String getJpqjd() {
		return jpqjd;
	}
	public void setJpqjd(String jpqjd) {
		this.jpqjd = jpqjd;
	}
	public String getJpqwd() {
		return jpqwd;
	}
	public void setJpqwd(String jpqwd) {
		this.jpqwd = jpqwd;
	}
	public String getJphjd() {
		return jphjd;
	}
	public void setJphjd(String jphjd) {
		this.jphjd = jphjd;
	}
	public String getJphwd() {
		return jphwd;
	}
	public void setJphwd(String jphwd) {
		this.jphwd = jphwd;
	}
}
