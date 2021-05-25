/**
 * ZCNFI_IF_AR_DOC_DETAILS_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com;

public class ZCNFI_IF_AR_DOC_DETAILS_ServiceLocator extends org.apache.axis.client.Service implements com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILS_Service {

    public ZCNFI_IF_AR_DOC_DETAILS_ServiceLocator() {
    }


    public ZCNFI_IF_AR_DOC_DETAILS_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ZCNFI_IF_AR_DOC_DETAILS_ServiceLocator(String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ZCNFI_IF_AR_DOC_DETAILS
    private String ZCNFI_IF_AR_DOC_DETAILS_address = "http://s033serv.metro-sap.com:8003/sap/bc/srt/rfc/sap/zcnfi_if_ar_doc_details/100/zcnfi_if_ar_doc_details/zcnfi_if_ar_doc_details";

    public String getZCNFI_IF_AR_DOC_DETAILSAddress() {
        return ZCNFI_IF_AR_DOC_DETAILS_address;
    }

    // The WSDD service name defaults to the port name.
    private String ZCNFI_IF_AR_DOC_DETAILSWSDDServiceName = "ZCNFI_IF_AR_DOC_DETAILS";

    public String getZCNFI_IF_AR_DOC_DETAILSWSDDServiceName() {
        return ZCNFI_IF_AR_DOC_DETAILSWSDDServiceName;
    }

    public void setZCNFI_IF_AR_DOC_DETAILSWSDDServiceName(String name) {
        ZCNFI_IF_AR_DOC_DETAILSWSDDServiceName = name;
    }

    public com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILS_PortType getZCNFI_IF_AR_DOC_DETAILS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ZCNFI_IF_AR_DOC_DETAILS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getZCNFI_IF_AR_DOC_DETAILS(endpoint);
    }

    public com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILS_PortType getZCNFI_IF_AR_DOC_DETAILS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILS_BindingStub _stub = new com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILS_BindingStub(portAddress, this);
            _stub.setPortName(getZCNFI_IF_AR_DOC_DETAILSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setZCNFI_IF_AR_DOC_DETAILSEndpointAddress(String address) {
        ZCNFI_IF_AR_DOC_DETAILS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILS_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILS_BindingStub _stub = new com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILS_BindingStub(new java.net.URL(ZCNFI_IF_AR_DOC_DETAILS_address), this);
                _stub.setPortName(getZCNFI_IF_AR_DOC_DETAILSWSDDServiceName());
                return _stub;
            }
        }
        catch (Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        String inputPortName = portName.getLocalPart();
        if ("ZCNFI_IF_AR_DOC_DETAILS".equals(inputPortName)) {
            return getZCNFI_IF_AR_DOC_DETAILS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZCNFI_IF_AR_DOC_DETAILS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:sap-com:document:sap:soap:functions:mc-style", "ZCNFI_IF_AR_DOC_DETAILS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(String portName, String address) throws javax.xml.rpc.ServiceException {
        
if ("ZCNFI_IF_AR_DOC_DETAILS".equals(portName)) {
            setZCNFI_IF_AR_DOC_DETAILSEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
