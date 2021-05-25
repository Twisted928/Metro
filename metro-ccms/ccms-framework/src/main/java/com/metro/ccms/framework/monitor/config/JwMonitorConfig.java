package com.metro.ccms.framework.monitor.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.metro.ccms.framework.monitor.JwMonitor;
import com.metro.ccms.framework.monitor.JwMonitorMessageConsumer;

/**
 * @author lw 系统启动时,创建监控消费者用于消费 监控信息
 */
@Component
public class JwMonitorConfig implements ApplicationRunner {
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/**
	 * 最多存放2000的待消费数据
	 */
	public static BlockingQueue<Stack<JwMonitor>> queueMonitor = new LinkedBlockingQueue<Stack<JwMonitor>>(2000);
	/**
	 * 存放哪些url进行所有的监控
	 */
	public static Map<String, Boolean> urlAllMonitor = new HashMap<String, Boolean>();

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// 创建监控信息消费线程
		JwMonitorMessageConsumer messageConsumer = new JwMonitorMessageConsumer();
		threadPoolTaskExecutor.submit(messageConsumer);
		// 判断数据库是否存储监控表，如果没有则自动创建
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("CREATE TABLE  if not exists tb_monitor ( \r\n");
			sb.append("id int(11) NOT NULL AUTO_INCREMENT,\r\n");
			sb.append("trace_id varchar(64) DEFAULT NULL,\r\n");
			sb.append("uuid varchar(64) DEFAULT NULL,\r\n");
			sb.append("parent_uuid varchar(64) DEFAULT NULL,\r\n");
			sb.append("uri varchar(256) DEFAULT NULL,\r\n");
			sb.append("start_date datetime DEFAULT NULL,\r\n");
			sb.append("end_date datetime DEFAULT NULL,\r\n");
			sb.append("cost_time bigint(20) DEFAULT NULL,\r\n");
			sb.append("class_type varchar(32) DEFAULT NULL,\r\n");
			sb.append("ip_address varchar(32) DEFAULT NULL,\r\n");
			sb.append("input_parameter varchar(256) DEFAULT NULL,\r\n");
			sb.append("if_exception varchar(255) DEFAULT '0',\r\n");
			sb.append("exception varchar(2000) DEFAULT NULL,\r\n");
			sb.append("sql_memo varchar(256) DEFAULT NULL,\r\n");
			sb.append("cache_used int(11) DEFAULT '0',\r\n");
			sb.append("ddate datetime DEFAULT NULL,\r\n");
			sb.append("PRIMARY KEY (id)\r\n");
			sb.append(" ) ENGINE=InnoDB AUTO_INCREMENT=8990 DEFAULT CHARSET=utf8;\r\n");
			jdbcTemplate.update(sb.toString());
		} catch (Exception ex) {

		}

	}
}