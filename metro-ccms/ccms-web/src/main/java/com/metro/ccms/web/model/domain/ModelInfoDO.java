package com.metro.ccms.web.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 模型信息表
 * @author yuanjh
 */
public class ModelInfoDO extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Excel(name = "模型编码",sort = 0)
	private Long id;
	/**
	 * 模型名称
	 */
	@Excel(name = "模型名称",sort = 1)
	private String name;
	/**
	 * 描述
	 */
	@Excel(name = "模型描述",sort = 2)
	private String description;
	/**
	 * 有无财报 0:无；1:有财报
	 */
	@Excel(name = "有无财报",sort = 4,readConverterExp = "0=无财报,1=有财报")
	private String financial;
	/**
	 * 发布 0:未发布 ，1:发布
	 */
	@Excel(name = "发布状态",sort = 3,readConverterExp = "0=未发布,1=已发布")
	private String publish;
	/**
	 * 失效日期
	 */
	@Excel(name = "失效日期",sort = 5,dateFormat = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expiryDate;
	/**
	 *
	 */
	private String scope;

	/**
	 * 状态(0：无效，1：有效)
	 * */
	private Integer status;
	/**
	 * 删除标记(0：正常，1：已删除)
	 * */
	private Integer deleted;

	/**
	 * 行业类别 1企业类 2政府背景类 3社会组织类
	 */
	@Excel(name = "行业类别",sort = 6,readConverterExp = "1=企业类,2=政府背景类,3=社会组织类")
	private Integer indusType;
	/**
	 * 是否新老客户 1是 0否
	 */
	@Excel(name = "是否新老客户",sort = 7,readConverterExp = "1=是,0=否")
	private Integer ifOld;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFinancial() {
		return financial;
	}

	public void setFinancial(String financial) {
		this.financial = financial;
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
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

	public Integer getIndusType() {
		return indusType;
	}

	public void setIndusType(Integer indusType) {
		this.indusType = indusType;
	}

	public Integer getIfOld() {
		return ifOld;
	}

	public void setIfOld(Integer ifOld) {
		this.ifOld = ifOld;
	}
}
