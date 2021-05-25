package com.metro.ccms.web.httpsInterface.interceptor;


import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.namespace.QName;
import java.util.List;

/**
 * @Author: fangyongjie
 * @Description: 用于調用webservices接口的安全验证拦截器
 * @Date: Created in 16:38 2021/2/2
 * @Modified By:
 */
public class LoginInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginInterceptor(String username, String password) {
        //设置在发送请求前阶段进行拦截
        super(Phase.PREPARE_SEND);
        this.username=username;
        this.password=password;
    }

    @Override
    public void handleMessage(SoapMessage soapMessage) throws Fault {
        List<Header> headers = soapMessage.getHeaders();
        Document doc = DOMUtils.createDocument();
        Element auth = doc.createElementNS("http://cxf.wolfcode.cn/","SecurityHeader");
        Element UserName = doc.createElement("username");
        Element UserPass = doc.createElement("password");

        UserName.setTextContent(username);
        UserPass.setTextContent(password);

        auth.appendChild(UserName);
        auth.appendChild(UserPass);

        headers.add(0, new Header(new QName("SecurityHeader"),auth));
    }
}
