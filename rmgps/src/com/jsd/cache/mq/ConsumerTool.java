package com.jsd.cache.mq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * MQ通信工具包，消费者基类
 * 
 * @author liujd
 * @date 2009-10-27
 * @version $Revision: 3.0.0.1 $
 */

public class ConsumerTool implements MessageListener, ExceptionListener {

	private boolean running = false;

	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;

	private String subject = "";
	private boolean topic = false;
	private String user = ActiveMQConnection.DEFAULT_USER;
	private String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private String url = "";
	private boolean transacted = false;
	private int ackMode = Session.AUTO_ACKNOWLEDGE;
	private String selector = "";
	protected static Log console = LogFactory.getLog("console");
	protected static Log loggerMQ = LogFactory.getLog("loggerMQ");

	public ConsumerTool() {

	}

	public void run() throws JMSException {
		if (connection == null) {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					user, password, url);
			connection = connectionFactory.createConnection();
		}
		connection.setExceptionListener(this);
		connection.start();

		session = connection.createSession(transacted, ackMode);
		if (topic) {
			destination = session.createTopic(subject);
		} else {
			destination = session.createQueue(subject);
		}

		if (selector.equals(""))
			consumer = session.createConsumer(destination);
		else
			consumer = session.createConsumer(destination, selector);

		consumer.setMessageListener(this);
		running = true;
	}

	public void stop() {
		try {
			connection.stop();
		} catch (JMSException e) {

		}
	}

	public void onMessage(Message message) {
		try {

			if (message instanceof TextMessage) {
				TextMessage txtMsg = (TextMessage) message;

				String msg = txtMsg.getText();
				console.info("ReceivedMQ消息: " + msg);
				loggerMQ.info("ReceivedMQ消息: " + msg);
			} else {
				console.warn("Received未知格式的MQ消息: " + message);
				loggerMQ.warn("Received未知格式的MQ消息: " + message);
				return;
			}

			if (transacted) {
				session.commit();
			} else if (ackMode == Session.CLIENT_ACKNOWLEDGE) {
				message.acknowledge();
			}

		} catch (JMSException e) {
			console.error(e);
			loggerMQ.error(e);
		}
	}

	public synchronized void onException(JMSException ex) {
		loggerMQ
				.error("JMS Exception occured on ConsumerTool.  Shutting down client.");
		running = false;
	}

	synchronized boolean isRunning() {
		return running;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection con) {
		this.connection = con;
	}

	public Session getSession() {
		return session;
	}

	public void setAckMode(String ackMode) {
		if ("CLIENT_ACKNOWLEDGE".equals(ackMode)) {
			this.ackMode = Session.CLIENT_ACKNOWLEDGE;
		}
		if ("AUTO_ACKNOWLEDGE".equals(ackMode)) {
			this.ackMode = Session.AUTO_ACKNOWLEDGE;
		}
		if ("DUPS_OK_ACKNOWLEDGE".equals(ackMode)) {
			this.ackMode = Session.DUPS_OK_ACKNOWLEDGE;
		}
		if ("SESSION_TRANSACTED".equals(ackMode)) {
			this.ackMode = Session.SESSION_TRANSACTED;
		}
	}

	public int getAckMode() {
		return this.ackMode;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public String getPassword() {
		return this.password;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setTopic(boolean topic) {
		this.topic = topic;
	}

	public boolean getTopic() {
		return this.topic;
	}

	public void setQueue(boolean queue) {
		this.topic = !queue;
	}

	public boolean getQueue() {
		return !this.topic;
	}

	public void setTransacted(boolean transacted) {
		this.transacted = transacted;
	}

	public boolean getTransacted() {
		return this.transacted;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return this.user;
	}

	public void setSelector(String selector) {
		this.selector = selector;
	}

	public String getSelector() {
		return this.selector;
	}
}
