package com.metro.ccms.framework.monitor;

import java.io.Serializable;
import java.util.Date;

public class JwMonitor implements Serializable{
	private static final long serialVersionUID = -5867391386472082694L;
	public static final int CACHENOTUSER=0; // 没有使用缓存
	public static final int CACHEEUSER=1;  // 使用了缓存
	public static final int IS_ERROR=1;
	public static final int IS_NOT_ERROR=0;
    private Integer id;
    private String traceId;
    private String parentUuid;
    private String uuid;
    private String uri;
    private Long startTimeInMillis;
    private Long endTimeInMillis;
    private Date startDate;
    private Date endDate;
    private Long costTime;
    private String ipAddress;
    private String inputParameter;
    private String classType;
    private Integer ifException=IS_NOT_ERROR;
    private String exception;
    private String sqlMemo;
    private Date ddate;
    private Integer cacheUsed=CACHENOTUSER;
    /**
     * 是否递归(如果为true,则controller,service,dao都进行监控，如果为false，则不监控)
     */
    private Boolean ifRecursion=true;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTraceId() {
		return traceId;
	}
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	public String getParentUuid() {
		return parentUuid;
	}
	public void setParentUuid(String parentUuid) {
		this.parentUuid = parentUuid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
 
	public Long getCostTime() {
		return costTime;
	}
	public void setCostTime(Long costTime) {
		this.costTime = costTime;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	public Integer getIfException() {
		return ifException;
	}
	public void setIfException(Integer ifException) {
		this.ifException = ifException;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getSqlMemo() {
		return sqlMemo;
	}
	public void setSqlMemo(String sqlMemo) {
		this.sqlMemo = sqlMemo;
	}
	public Date getDdate() {
		return ddate;
	}
	public void setDdate(Date ddate) {
		this.ddate = ddate;
	}
	public Integer getCacheUsed() {
		return cacheUsed;
	}
	public void setCacheUsed(Integer cacheUsed) {
		this.cacheUsed = cacheUsed;
	}
	public Long getStartTimeInMillis() {
		return startTimeInMillis;
	}
	public void setStartTimeInMillis(Long startTimeInMillis) {
		this.startTimeInMillis = startTimeInMillis;
	}
	public Long getEndTimeInMillis() {
		return endTimeInMillis;
	}
	public void setEndTimeInMillis(Long endTimeInMillis) {
		this.endTimeInMillis = endTimeInMillis;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getInputParameter() {
		return inputParameter;
	}
	public void setInputParameter(String inputParameter) {
		this.inputParameter = inputParameter;
	}
	public Boolean getIfRecursion() {
		return ifRecursion;
	}
	public void setIfRecursion(Boolean ifRecursion) {
		this.ifRecursion = ifRecursion;
	}

    
}
