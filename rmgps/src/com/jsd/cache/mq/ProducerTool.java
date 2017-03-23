package com.jsd.cache.mq;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A simple tool for publishing messages
 *
 * @version $Revision: 1.2 $
 */
public class ProducerTool implements ExceptionListener {

	private Connection connection = null;
	private Destination destination = null;
	private Session session = null;
	private MessageProducer producer = null;
	private long timeToLive = 0;
	private String user = ActiveMQConnection.DEFAULT_USER;
	private String password = ActiveMQConnection.DEFAULT_PASSWORD;
	private String url = "";
	private String subject = "";
	private boolean topic = false;
	private boolean transacted = false;
	private boolean persistent = false;
	protected static Log console = LogFactory.getLog("console");
	protected static Log loggerMQ = LogFactory.getLog("loggerMQ");

	public void run() throws JMSException {

		if (timeToLive != 0) {
			console.info("Messages time to live " + timeToLive + " ms");
			loggerMQ.info("Messages time to live " + timeToLive + " ms");
		}

		// Create the connection.
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				user, password, url);
		connection = connectionFactory.createConnection();
		connection.start();

		// Create the session
		session = connection
				.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
		if (topic) {
			destination = session.createTopic(subject);
		} else {
			destination = session.createQueue(subject);
		}

		// Create the producer.
		producer = session.createProducer(destination);
		if (persistent) {
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		} else {
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		}
		if (timeToLive != 0) {
			producer.setTimeToLive(timeToLive);
		}
	}

	public String sendOrder(String msgText) {
		try {
			TextMessage msg = session.createTextMessage();
			msg.setText(msgText);
			msg.setStringProperty("msgdestination", "COMMAND");
			msg.setStringProperty("msgreturn", "FALSE");
			msg.setStringProperty("msgtype", "SWITCH");
			msg.setIntProperty("msgid", 1050);
			this.producer.send(msg);
//			System.out.println("网站发送命令:\n" + msgText);
		} catch (Exception e) {
//			System.out.println("队列发送命令异常" + new Date() + e);
		}
		return "";
	}

	@Override
	public synchronized void onException(JMSException ex) {
		console
				.error("JMS Exception occured on ProducerTool.  Shutting down client.");
		loggerMQ
				.error("JMS Exception occured on ProducerTool.  Shutting down client.");
	}

	public void stop() {
		try {
			connection.stop();
		} catch (JMSException e) {
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public Session getSession() {
		return session;
	}

	public MessageProducer getProducer() {
		return this.producer;
	}

	public void setPersistent(boolean durable) {
		this.persistent = durable;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setTimeToLive(long timeToLive) {
		this.timeToLive = timeToLive;
	}

	public void setTopic(boolean topic) {
		this.topic = topic;
	}

	public void setQueue(boolean queue) {
		this.topic = !queue;
	}

	public void setTransacted(boolean transacted) {
		this.transacted = transacted;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
