package com.jsd.web.mq;

import java.io.IOException;

import javax.jms.JMSException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsd.cache.main.AppConfig;

@SuppressWarnings("serial")
public class InitReceiveMsgFromMQ extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public InitReceiveMsgFromMQ() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	@Override
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 *
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	@Override
	public void init() throws ServletException {
		try {
			String filepath = InitReceiveMsgFromMQ.class.getClassLoader().getResource("").toURI().getPath();
			AppConfig.instance().Init(filepath+"MqConfig.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}

		//发送MQ初始化
		SendMsgToMQ sendMsgToMQ = SendMsgToMQ.instance();
		try{
			sendMsgToMQ.setUrl(AppConfig.instance().getValue("MQBrokerURI"));
			sendMsgToMQ.setTopic(true);
			sendMsgToMQ.setSubject(AppConfig.instance().getValue("SwitchTopic"));
			sendMsgToMQ.run();
		}catch (JMSException e) {
//			System.out.println("MQ初始化失败,出现异常");
		}
	}
}
