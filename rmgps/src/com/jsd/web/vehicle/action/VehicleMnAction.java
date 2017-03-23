package com.jsd.web.vehicle.action;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.jsd.util.PageRequestBean;
import com.jsd.util.PageResponseBean;
import com.jsd.web.login.vo.UserVO;
import com.jsd.web.vehicle.po.History;
import com.jsd.web.vehicle.service.VehicleMnService;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings({ "rawtypes","serial" })
public class VehicleMnAction extends ActionSupport{
	
	public UserVO getUserVo(){
		return (UserVO) ServletActionContext.getRequest().getSession().getAttribute("USER");
	}
	
	public String home(){
		//得到登陆用户的信息
		UserVO uservo = this.getUserVo();
		//根据用户来查询用户下的代理商
		this.agentList = this.vehicleMnService.queryAgentList2(uservo);		
		return "success";
	}
	
	//紧急列表
	public String queryEmergentList() {
		UserVO uservo = this.getUserVo();
		// 将分页和排序参数保存 PageRequestBean
		PageRequestBean pageRequestBean = new PageRequestBean();
		pageRequestBean.setPage(page);
		pageRequestBean.setRows(rows);
		pageRequestBean.setOrder(order);
		pageRequestBean.setSort(sort);
		
		History h = new History();
		h.setVno(vno_list);
		if(user_id_list != null && !"".equals(user_id_list) && !"-1".equals(user_id_list)){
			h.setUser_id(Integer.valueOf(user_id_list));
		}
		//业务层
		pageResponseBean = vehicleMnService.queryEmergentList(h,uservo,pageRequestBean);
		
		//洗时间格式过程
		History h1 =new History();
		for(int i=0;i<pageResponseBean.getRows().size();i++){
			h1 = (History) pageResponseBean.getRows().get(i);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(h1.getSend_time()!=null && h1.getRev_time()!=null){
				h1.setSendtime(df.format(h1.getSend_time()));
				h1.setReceivetime(df.format(h1.getRev_time()));
			}
		}
		
		vno_list = null;
		user_id_list = null;
		
		return "emergent";
	}
	
	//Ajax 分页
	private int page;		// 页码			
	private int rows;		// 每页多少条
	private String sort;	//排序字段
	private String order;	//asc|desc
	private PageResponseBean pageResponseBean;
	
	private VehicleMnService vehicleMnService;
	private List agentList;			//代理商列表
	
	private String vno_list;		//查询字段
	private String user_id_list;	//查询字段
	
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
	public PageResponseBean getPageResponseBean() {
		return pageResponseBean;
	}
	public void setPageResponseBean(PageResponseBean pageResponseBean) {
		this.pageResponseBean = pageResponseBean;
	}
	public VehicleMnService getVehicleMnService() {
		return vehicleMnService;
	}
	public void setVehicleMnService(VehicleMnService vehicleMnService) {
		this.vehicleMnService = vehicleMnService;
	}
	public List getAgentList() {
		return agentList;
	}
	public void setAgentList(List agentList) {
		this.agentList = agentList;
	}
	public String getVno_list() {
		return vno_list;
	}
	public void setVno_list(String vno_list) {
		this.vno_list = vno_list;
	}
	public String getUser_id_list() {
		return user_id_list;
	}
	public void setUser_id_list(String user_id_list) {
		this.user_id_list = user_id_list;
	}
	
}
