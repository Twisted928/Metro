package com.metro.ccms.web.earlywarning.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description: 预警模型配置表
 * @Date: Created in 11:07 2021/2/18
 * @Modified By:
 */
public class EarlyWarningModelConfigDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 模型编码 */
    private String mocdelCode;

    /** A:逾期天数  B:合同到期前天数 C:授信到期前天数 */
    private String warnItem;

    /** 上限 */
    private Integer upper;

    /** 下限 */
    private Integer lower;

    /** 通知角色 */
    private String roles;

    /** A:一天一次 B:一周一次 C:半月一次 D:一月一次 */
    private String warnFre;

    /** A:通知 B:邮件 C:通知+邮件 */
    private String mode;

    /** 状态(0：无效，1：有效) */
    private Integer status;

    /** 备用字段 */
    private String item1;

    /** 备用字段 */
    private String item2;

    /** 备用字段 */
    private String item3;

    /** 备用字段 */
    private String item4;

    /** 备用字段 */
    private String item5;

    /** 备用字段 */
    private String item6;

    /** 备用字段 */
    private String item7;

    /** 备用字段 */
    private String item8;

    /** 备用字段 */
    private String item9;

    //冗余字段 接收通知角色数组
    private List<String> roleIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMocdelCode() {
        return mocdelCode;
    }

    public void setMocdelCode(String mocdelCode) {
        this.mocdelCode = mocdelCode;
    }

    public String getWarnItem() {
        return warnItem;
    }

    public void setWarnItem(String warnItem) {
        this.warnItem = warnItem;
    }

    public Integer getUpper() {
        return upper;
    }

    public void setUpper(Integer upper) {
        this.upper = upper;
    }

    public Integer getLower() {
        return lower;
    }

    public void setLower(Integer lower) {
        this.lower = lower;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getWarnFre() {
        return warnFre;
    }

    public void setWarnFre(String warnFre) {
        this.warnFre = warnFre;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
