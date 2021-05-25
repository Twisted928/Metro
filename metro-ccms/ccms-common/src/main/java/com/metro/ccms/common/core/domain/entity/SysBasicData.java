package com.metro.ccms.common.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.domain.BaseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: fangyongjie
 * @Description: 系统参数表 tb_basic_data
 * @Date: Created in 10:25 2020/12/14
 * @Modified By:
 */
public class SysBasicData extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 父ID */
    private Long parentId;

    /** 状态(0：无效，1：有效) */
    @Excel(name = "状态",sort = 2,readConverterExp = "0=无效,1=有效")
    private Integer status;

    /** 删除标记(0：正常，1：已删除) */
    private Integer deleted;

    /** 来源 */
    private String source;

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

    /** 描述 */
    @Excel(name = "参数名称",sort = 0)
    private String description;

    /** 类型 */
    @Excel(name = "参数代码",sort = 1)
    private String ctype;

    /** 生效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validFrom;

    /** 到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validTo;

    /** 父级类型 */
    private String parentType;

    /** 子菜单 */
    private List<SysBasicData> children = new ArrayList<SysBasicData>();


    public List<SysBasicData> getChildren() {
        return children;
    }

    public void setChildren(List<SysBasicData> children) {
        this.children = children;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }
}
