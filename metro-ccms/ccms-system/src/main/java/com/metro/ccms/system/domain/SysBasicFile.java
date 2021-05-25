package com.metro.ccms.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 13:51 2020/12/8
 * @Modified By:
 */
public class SysBasicFile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 状态(0：无效，1：有效) */
    @Excel(name = "状态(0：无效，1：有效)")
    private Integer status;

    /** 删除标记(0：正常，1：已删除) */
    @Excel(name = "删除标记(0：正常，1：已删除)")
    private Integer deleted;

    /** 时间戳 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间戳", width = 30, dateFormat = "yyyy-MM-dd")
    private Date timestamp;

    /** 主表ID */
    @Excel(name = "主表ID")
    private Long mainId;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item1;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item2;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item3;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item4;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item5;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item6;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item7;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item8;

    /** 备用字段 */
    @Excel(name = "备用字段")
    private String item9;

    /** 描述 */
    @Excel(name = "描述")
    private String desc;

    /** 申请单号 */
    @Excel(name = "申请单号")
    private String applicationNo;

    /** 附件信息 */
    @Excel(name = "附件信息")
    private String attachitems;

    /** 附件名称 */
    @Excel(name = "附件名称")
    private String attachmentName;

    /** 附件地址 */
    @Excel(name = "附件地址")
    private String attachmentUrl;

    /**
     * 类型
     * 1白名单管理  2信用组管理  3限额申请  4信用复审  5额度转移  6合同管理  7保险客户清单  8报损管理  9 担保函管理  10催收管理  11对账管理
     */
    @Excel(name = "类型")
    private String ctype;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getAttachitems() {
        return attachitems;
    }

    public void setAttachitems(String attachitems) {
        this.attachitems = attachitems;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }
}
