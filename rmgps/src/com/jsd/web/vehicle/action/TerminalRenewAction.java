package com.jsd.web.vehicle.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.jsd.web.login.vo.UserVO;
import com.jsd.web.vehicle.service.TerminalRenewService;
import com.jsd.web.vehicle.vo.TerminalRenewVO;
import com.opensymphony.xwork2.ActionSupport;

public class TerminalRenewAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private TerminalRenewService terminalRenewService;

	private String alldata;// 客户端需要修改的所有数据

	/* 查询条件 */
	private String vname;// 整机名称
	private String dno;// 终端编号
	private String simnum;// SIM号码
	private String lastrenewdate;// 最后续费时间
	private String renewperiod;// 续费周期
	
	private String daytime;		//即将到期的天数
	public String getDaytime() {
		return daytime;
	}
	public void setDaytime(String daytime) {
		this.daytime = daytime;
	}

	/* 返回客户端的终端续费信息集合 */
	private List<TerminalRenewVO> terminalRenewInfoList;
	
	private String operateresult;//操作完成后跳转地址

	/**
	 * 查询终端续费信息
	 * @return
	 */
	public String findTerminalRenew() {
		TerminalRenewVO vo = new TerminalRenewVO();
		vo.setVname(vname);
		vo.setDno(dno);
		vo.setSimnum(simnum);
		vo.setLastrenewdate(lastrenewdate);
		vo.setRenewperiod(renewperiod);
		vo.setDaytime(daytime);
		
		UserVO uservo = (UserVO) ServletActionContext.getRequest().getSession().getAttribute("USER");
		
		this.terminalRenewInfoList = this.terminalRenewService.findTerminalRenew(vo, uservo);

		if(vo.getDaytime()!=null && !"".equals(vo.getDaytime().trim())){
			List<TerminalRenewVO> t = new ArrayList<TerminalRenewVO>();
			for(int i=0;i<terminalRenewInfoList.size();i++){
				TerminalRenewVO tr = terminalRenewInfoList.get(i);
				if(tr.getLastrenewdate()!=null && tr.getRenewperiod()!=null && 
				!"".equals(tr.getLastrenewdate().trim()) && !"".equals(tr.getRenewperiod().trim())){
					String renewdate = tr.getLastrenewdate();//最后续费日期
					String period = tr.getRenewperiod();	//续费周期
					String dtime = getDaytime();			//多少天查询
					SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
					//SimpleDateFormat sf2 = new SimpleDateFormat("d");
					
					try {
						long lrenewdate = sf1.parse(renewdate).getTime();							//最后设置时间的毫秒值
						long systemSeconds = sf1.parse(sf1.format(new java.util.Date())).getTime();//系统时间的毫秒值
						long p = (long) ((Long.valueOf(period))*30.45*24*3600*1000);
						long p2 = (long) ((Long.valueOf(dtime))*24*3600*1000);//7天的毫秒值
						if(lrenewdate+p-systemSeconds<=p2 /*&& lrenewdate+p-systemSeconds>=0*/){
							t.add(tr);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			terminalRenewInfoList = t;
		}
		
		return "success";
	}
	
	/**
	 * 修改终端续费信息
	 * @return
	 */
	public String modifyTerminalRenew() {
		/* 服务器验证，如果未提交修改数据，则返回原列表 */
		if(this.alldata == null || "".equals(this.alldata)){
			findTerminalRenew();
			return "findAll";
		}
		
		String alldatas[] = this.alldata.split(";");
		String renewinfos[] = null;
		
		for(int i = 1; i < alldatas.length; i++){
			//默认从';'开始拼串，所以i从1开始
			if(alldatas[i].endsWith(",")){
				alldatas[i]+=",0";
			}
			renewinfos = alldatas[i].split(",");
			this.terminalRenewService.modifyTerminalRenew(renewinfos);
		}

		this.operateresult = "操作成功|terminalRenew.action";
		
		return "success";
	}

	/* get() set()方法 */
	public void setTerminalRenewService(
			TerminalRenewService terminalRenewService) {
		this.terminalRenewService = terminalRenewService;
	}

	public String getAlldata() {
		return alldata;
	}

	public void setAlldata(String alldata) {
		this.alldata = alldata;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getDno() {
		return dno;
	}

	public void setDno(String dno) {
		this.dno = dno;
	}

	public String getSimnum() {
		return simnum;
	}

	public void setSimnum(String simnum) {
		this.simnum = simnum;
	}

	public String getLastrenewdate() {
		return lastrenewdate;
	}

	public void setLastrenewdate(String lastrenewdate) {
		this.lastrenewdate = lastrenewdate;
	}

	public String getRenewperiod() {
		return renewperiod;
	}

	public void setRenewperiod(String renewperiod) {
		this.renewperiod = renewperiod;
	}

	public List<TerminalRenewVO> getTerminalRenewInfoList() {
		return terminalRenewInfoList;
	}

	public void setTerminalRenewInfoList(List<TerminalRenewVO> terminalRenewInfoList) {
		this.terminalRenewInfoList = terminalRenewInfoList;
	}

	public String getOperateresult() {
		return operateresult;
	}

	public void setOperateresult(String operateresult) {
		this.operateresult = operateresult;
	}

}
