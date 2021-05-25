package com.metro.ccms.web.utils;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 14:23 2021/2/5
 * @Modified By:
 */
public class MyAuthenticator extends Authenticator {

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("201028001384", "+Bb33333".toCharArray());
    }
}
