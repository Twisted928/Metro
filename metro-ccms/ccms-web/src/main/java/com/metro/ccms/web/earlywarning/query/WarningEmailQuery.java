package com.metro.ccms.web.earlywarning.query;

import com.metro.ccms.common.core.query.BaseQuery;

import java.io.Serializable;

/**
 *<p>
 *  预警邮件
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/9
 * @desc
 **/
public class WarningEmailQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 标题
     */
    private String title;
    /**
     * 收件人
     */
    private String username;
    /**
     * 收件角色
     */
    private String rolename;
    /**
     * 发送日期
     */
    private String ddate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    @Override
    public String toString() {
        return "WarningemailQuery{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", username='" + username + '\'' +
                ", rolename='" + rolename + '\'' +
                ", ddate='" + ddate + '\'' +
                '}';
    }
}
