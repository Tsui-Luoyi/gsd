/**
 *
 */
package com.jsd.web.login.action;

import org.apache.struts2.ServletActionContext;

import com.jsd.web.login.vo.UserVO;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 登出系统类
 * @author cuiluepng
 *
 */
public class LogoutAction extends ActionSupport{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 登录验证方法
     * @return
     */
	public String logout(){
		UserVO uservo = (UserVO)ServletActionContext.getRequest().getSession().getAttribute("USER");
		if(uservo!=null){
			ServletActionContext.getRequest().getSession().removeAttribute("USER");
			ServletActionContext.getRequest().getSession().invalidate();
		}
		return "success";
	}
}
