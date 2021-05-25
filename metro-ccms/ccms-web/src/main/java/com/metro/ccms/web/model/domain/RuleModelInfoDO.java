package com.metro.ccms.web.model.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.util.Date;


/**
 *<p>
 *规则引擎管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/
public class RuleModelInfoDO extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 生效时间
	 */
	private Date expirydate;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态(0：无效，1：有效)
	 * */
	private Integer status;
	/**
	 * 删除标记(0：正常，1：已删除)
	 * */
	private Integer deleted;

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

	public Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}

	@Override
	public String getRemark() {
		return remark;
	}

	@Override
	public void setRemark(String remark) {
		this.remark = remark;
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

	@Override
	public String toString() {
		return "RuleModelInfoDO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", expirydate=" + expirydate +
				", remark='" + remark + '\'' +
				", status=" + status +
				", deleted=" + deleted +
				'}';
	}
}
