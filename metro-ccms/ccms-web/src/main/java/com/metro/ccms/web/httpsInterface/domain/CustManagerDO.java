package com.metro.ccms.web.httpsInterface.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: fangyongjie
 * @Description: 客户经理配置对象 tb_interface_custmanager
 * @Date: Created in 17:34 2021/1/18
 * @Modified By:
 */
public class CustManagerDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 岗位Id */
    private String postId;

    /** 岗位名称 */
    private String postName;

    /** 多点用户id */
    private String userId;

    /** 员工号 */
    private String sapId;

    /** 用户名称 */
    private String userName;

    /** 用户手机号 */
    private String userMobile;

    /** 写入时间 */
    private Date ddate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSapId() {
        return sapId;
    }

    public void setSapId(String sapId) {
        this.sapId = sapId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Date getDdate() {
        return ddate;
    }

    public void setDdate(Date ddate) {
        this.ddate = ddate;
    }
}
