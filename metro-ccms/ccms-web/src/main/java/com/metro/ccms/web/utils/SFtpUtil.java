package com.metro.ccms.web.utils;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @Author: fangyongjie
 * @Description: 下载sftp文件工具类
 * @Date: Created in 14:23 2021/2/3
 * @Modified By:
 */
@Component
public class SFtpUtil {

    private static final Logger log = LoggerFactory.getLogger(SFtpUtil.class);

    public static final String NO_FILE = "No such file";

    private ChannelSftp sftp = null;

    private Session sshSession = null;

    @Value("${sftp.username}")
    private String username;
    @Value("${sftp.password}")
    private String password;
    @Value("${sftp.host}")
    private String host;
    @Value("${sftp.port}")
    private int port;

    /**
     * 连接sftp服务器
     *
     * @return ChannelSftp sftp类型
     */
    public ChannelSftp connect() {
        log.info("ftp连接开始host=" + host + "port" + port + "username=" + username);
        JSch jsch = new JSch();
        try {
            sshSession = jsch.getSession(username, host, port);
            log.info("ftp---Session created.");
            sshSession.setPassword(password);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(properties);
            sshSession.connect();
            log.info("ftp---Session connected.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            log.info("Opening Channel.");
            sftp = (ChannelSftp) channel;
            log.info("ftp---Connected to " + host);
        } catch (JSchException e) {

        }
        return sftp;
    }

    public void disconnect() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
                this.sftp = null;
                log.info("sftp is closed already");
            }
        }
        if (this.sshSession != null) {
            if (this.sshSession.isConnected()) {
                this.sshSession.disconnect();
                this.sshSession = null;
                log.info("sshSession is closed already");
            }
        }
    }

}
