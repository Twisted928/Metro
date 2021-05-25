package com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com;

public class ZCNFI_IF_AR_DOC_DETAILSProxy implements com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILS_PortType {
  private String _endpoint = null;
  private com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILS_PortType zCNFI_IF_AR_DOC_DETAILS_PortType = null;

  public ZCNFI_IF_AR_DOC_DETAILSProxy() {
    _initZCNFI_IF_AR_DOC_DETAILSProxy();
  }

  public ZCNFI_IF_AR_DOC_DETAILSProxy(String endpoint) {
    _endpoint = endpoint;
    _initZCNFI_IF_AR_DOC_DETAILSProxy();
  }

  private void _initZCNFI_IF_AR_DOC_DETAILSProxy() {
    try {
      zCNFI_IF_AR_DOC_DETAILS_PortType = (new com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILS_ServiceLocator()).getZCNFI_IF_AR_DOC_DETAILS();
      if (zCNFI_IF_AR_DOC_DETAILS_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zCNFI_IF_AR_DOC_DETAILS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zCNFI_IF_AR_DOC_DETAILS_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }

    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }

  public String getEndpoint() {
    return _endpoint;
  }

  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zCNFI_IF_AR_DOC_DETAILS_PortType != null)
      ((javax.xml.rpc.Stub)zCNFI_IF_AR_DOC_DETAILS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);

  }

  public com.metro.ccms.web.webservice.mc_style.functions.soap.sap.document.sap_com.ZCNFI_IF_AR_DOC_DETAILS_PortType getZCNFI_IF_AR_DOC_DETAILS_PortType() {
    if (zCNFI_IF_AR_DOC_DETAILS_PortType == null)
      _initZCNFI_IF_AR_DOC_DETAILSProxy();
    return zCNFI_IF_AR_DOC_DETAILS_PortType;
  }
  
  public void zcnfiIfArDocDetails(String blart, String customerId, String ifid, String startDate, org.apache.axis.types.Time startTime, String storeId, javax.xml.rpc.holders.StringHolder evJson, javax.xml.rpc.holders.IntHolder evLength) throws java.rmi.RemoteException{
    if (zCNFI_IF_AR_DOC_DETAILS_PortType == null)
      _initZCNFI_IF_AR_DOC_DETAILSProxy();
    zCNFI_IF_AR_DOC_DETAILS_PortType.zcnfiIfArDocDetails(blart, customerId, ifid, startDate, startTime, storeId, evJson, evLength);
  }
  
  
}