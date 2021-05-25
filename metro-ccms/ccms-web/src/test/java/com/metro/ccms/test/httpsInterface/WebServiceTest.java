package com.metro.ccms.test.httpsInterface;

import com.metro.ccms.test.BaseTest;
import com.metro.ccms.web.httpsInterface.interceptor.LoginInterceptor;
import com.metro.ccms.web.utils.CommonUtils;
import com.metro.ccms.web.utils.MyAuthenticator;
import com.metro.ccms.web.utils.SFtpUtil;
import com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILSProxy;
import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.ClientImpl;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.*;
import org.apache.cxf.ws.policy.IgnorablePolicyInterceptorProvider;
import org.apache.cxf.ws.policy.PolicyInterceptorProviderRegistry;
import org.apache.cxf.ws.policy.v200409.Policy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.namespace.QName;
import javax.xml.rpc.holders.IntHolder;
import javax.xml.rpc.holders.StringHolder;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.Authenticator;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 16:15 2021/2/2
 * @Modified By:
 */
public class WebServiceTest extends BaseTest {

    @Autowired
    private CommonUtils commonUtils;


    @Test
    public void webTest(){
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        String webUrl="http://s033serv.metro-sap.com:8003/sap/bc/srt/wsdl/flv_10002A111AD1/bndg_url/sap/bc/srt/rfc/sap/zcnfi_if_ar_doc_details/100/zcnfi_if_ar_doc_details/zcnfi_if_ar_doc_details?sap-client=100";
        Client client = dcf.createClient(webUrl);
        // 需要密码的情况需要加上用户名和密码
        client.getOutInterceptors().add(new LoginInterceptor("201028001384", "+Bb33333"));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
            Authenticator.setDefault(new MyAuthenticator());
            String webUrl="http://s033serv.metro-sap.com:8003/sap/bc/srt/wsdl/flv_10002A111AD1/bndg_url/sap/bc/srt/rfc/sap/zcnfi_if_ar_doc_details/100/zcnfi_if_ar_doc_details/zcnfi_if_ar_doc_details?sap-client=100";
            Client client = dcf.createClient(webUrl);

            // 需要密码的情况需要加上用户名和密码
//            client.getOutInterceptors().add(new LoginInterceptor("201028001384", "+Bb33333"));

//            ClientImpl clientImpl = (ClientImpl) client;
            Endpoint endpoint = client.getEndpoint();

            //设置policy策略
            Bus bus = client.getBus();
            PolicyInterceptorProviderRegistry pipr = bus.getExtension(PolicyInterceptorProviderRegistry.class);
            Set<QName> set = new HashSet<>();
            set.add(new QName("http://www.sap.com/2007/03/sidl", "BN__ZCNFI_IF_AR_DOC_DETAILS") );
            set.add(new QName("http://www.sap.com/2007/03/sidl", "BN__ZCNFI_IF_AR_DOC_DETAILS_soap12") );
            set.add(new QName("http://www.sap.com/2007/03/sidl", "IF__ZCNFI_IF_AR_DOC_DETAILS") );
            set.add(new QName("http://www.sap.com/2007/03/sidl", "OP__ZcnfiIfArDocDetails") );
            pipr.register(new IgnorablePolicyInterceptorProvider(set));


            // Make use of CXF service model to introspect the existing WSDL
            ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
            // 创建QName来指定NameSpace和要调用的service
            QName bindingName = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZCNFI_IF_AR_DOC_DETAILS");
            BindingInfo binding = serviceInfo.getBinding(bindingName);
            // 创建QName来指定NameSpace和要调用的方法
            QName opName = new QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZcnfiIfArDocDetails");

            BindingOperationInfo boi = binding.getOperation(opName);
            BindingMessageInfo inputMessageInfo = boi.getInput();
            List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();

            // 取得对象实例
            MessagePartInfo partInfo = parts.get(0);
            Class<?> partClass = partInfo.getTypeClass();
            Object inputObject = partClass.newInstance();

            // 取得字段的set方法并赋值
            PropertyDescriptor partPropertyDescriptor = new PropertyDescriptor("storeId", partClass);
            Method userNoSetter = partPropertyDescriptor.getWriteMethod();
            userNoSetter.invoke(inputObject, "10");

            // 取得字段的set方法并赋值
//            PropertyDescriptor partPropertyDescriptor2 = new PropertyDescriptor(字段名, partClass);
//            Method productCodeSetter = partPropertyDescriptor2.getWriteMethod();
//            productCodeSetter.invoke(inputObject, 属性值);

            // invoke("方法名",参数1,参数2,参数3....);
            Object[] objects = client.invoke(opName,inputObject);
            // 取得的结果是一个对象
            Class<?> resultClass = objects[0].getClass();
            // 取得返回结果的get方法并得到它的值
            PropertyDescriptor resultDescriptor = new PropertyDescriptor("EvLength", resultClass);
            Object resultGetter = resultDescriptor.getReadMethod().invoke(objects[0]);
            // 取得返回结果的get方法并得到它的值
            PropertyDescriptor tokenDescriptor = new PropertyDescriptor("EvJson", resultClass);
            // 取得的是一个对象实例
            Object getObj= tokenDescriptor.getReadMethod().invoke(objects[0]);
            Class<?> resultTokenClass = tokenDescriptor.getReadMethod().invoke(objects[0]).getClass();
            // 得到对象实例下的***属性值
            PropertyDescriptor expiredTimeDescriptor = new PropertyDescriptor("字段名", resultTokenClass);
            Object getter = expiredTimeDescriptor.getReadMethod().invoke(getObj);
            System.out.println("字段名：" + getter );
            System.out.println("返回数据:" + objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sftpTest(){
        commonUtils.downloadFile("/home/ctrip","111.txt","D:\\ws\\1111111.txt");
    }

    @Test
    public void webserviceTest(){
        try {
            ZCNFI_IF_AR_DOC_DETAILSProxy proxy=new ZCNFI_IF_AR_DOC_DETAILSProxy();
            StringHolder holder=new StringHolder();
            IntHolder holder1=new IntHolder();
            proxy.zcnfiIfArDocDetails(null,null,null,null,null,null,holder, holder1);
            System.out.println("======");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

}
