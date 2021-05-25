package com.metro.ccms.web.earlywarning.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 *<p>
 *  邮件预警
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/9
 * @desc
 **/
public class WarningEmailDO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 标题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String text;
    /**
     * 收件人
     */
    private String username;
    /**
     * 收件角色
     */
    private String rolename;
    /**
     * 来源
     */
    private String source;
    /**
     * 申请单号
     */
    private String applicationno;
    /**
     * 发送日期
     */
    private String ddate;
    /**
     * 预警模型编码
     */
    private String modcode;

    /**
     * 备用字段
     */
    private String item1;
    /**
     * 备用字段
     */
    private String item2;
    /**
     * 备用字段
     */
    private String item3;
    /**
     * 备用字段
     */
    private String item4;
    /**
     * 备用字段
     */
    private String item5;
    /**
     * 备用字段
     */
    private String item6;
    /**
     * 备用字段
     */
    private String item7;
    /**
     * 备用字段
     */
    private String item8;
    /**
     * 备用字段
     */
    private String item9;

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getApplicationno() {
        return applicationno;
    }

    public void setApplicationno(String applicationno) {
        this.applicationno = applicationno;
    }

    public String getDdate() {
        return ddate;
    }

    public void setDdate(String ddate) {
        this.ddate = ddate;
    }

    public String getModcode() {
        return modcode;
    }

    public void setModcode(String modcode) {
        this.modcode = modcode;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public String getItem6() {
        return item6;
    }

    public void setItem6(String item6) {
        this.item6 = item6;
    }

    public String getItem7() {
        return item7;
    }

    public void setItem7(String item7) {
        this.item7 = item7;
    }

    public String getItem8() {
        return item8;
    }

    public void setItem8(String item8) {
        this.item8 = item8;
    }

    public String getItem9() {
        return item9;
    }

    public void setItem9(String item9) {
        this.item9 = item9;
    }

    @Override
    public String toString() {
        return "WarningemailDO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", username='" + username + '\'' +
                ", rolename='" + rolename + '\'' +
                ", source='" + source + '\'' +
                ", applicationno='" + applicationno + '\'' +
                ", ddate='" + ddate + '\'' +
                ", modcode='" + modcode + '\'' +
                ", item1='" + item1 + '\'' +
                ", item2='" + item2 + '\'' +
                ", item3='" + item3 + '\'' +
                ", item4='" + item4 + '\'' +
                ", item5='" + item5 + '\'' +
                ", item6='" + item6 + '\'' +
                ", item7='" + item7 + '\'' +
                ", item8='" + item8 + '\'' +
                ", item9='" + item9 + '\'' +
                '}';
    }
}