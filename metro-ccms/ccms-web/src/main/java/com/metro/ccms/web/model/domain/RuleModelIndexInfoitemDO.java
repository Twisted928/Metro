package com.metro.ccms.web.model.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.math.BigDecimal;


/**
 *<p>
 *规则引擎管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/
public class RuleModelIndexInfoitemDO extends BaseEntity {
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
	 * 固定值
	 */
	private String grade;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
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

	@Override
	public String toString() {
		return "RuleModelIndexInfoitemDO{" +
				"id=" + id +
				", modIndexId=" + modIndexId +
				", upperValue=" + upperValue +
				", lowerValue=" + lowerValue +
				", grade='" + grade + '\'' +
				", description='" + description + '\'' +
				", includeRange=" + includeRange +
				'}';
	}
}
