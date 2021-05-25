package com.metro.ccms.web.model.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 模型指标元素表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
public class ModelInfoIndexItemDO extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	/**
	 * 模型指标ID
	 */
	private Long modIndexId;
	/**
	 * 最大值
	 */
	private BigDecimal upperValue;
	/**
	 * 最小值
	 */
	private BigDecimal lowerValue;
	/**
	 * 分数
	 */
	private BigDecimal grade;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 包含范围
	 */
	private Integer includeRange;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getModIndexId() {
		return modIndexId;
	}

	public void setModIndexId(Long modIndexId) {
		this.modIndexId = modIndexId;
	}

	public BigDecimal getUpperValue() {
		return upperValue;
	}

	public void setUpperValue(BigDecimal upperValue) {
		this.upperValue = upperValue;
	}

	public BigDecimal getLowerValue() {
		return lowerValue;
	}

	public void setLowerValue(BigDecimal lowerValue) {
		this.lowerValue = lowerValue;
	}

	public BigDecimal getGrade() {
		return grade;
	}

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getIncludeRange() {
		return includeRange;
	}

	public void setIncludeRange(Integer includeRange) {
		this.includeRange = includeRange;
	}
}
