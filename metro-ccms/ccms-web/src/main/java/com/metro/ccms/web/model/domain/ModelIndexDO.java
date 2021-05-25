package com.metro.ccms.web.model.domain;

import com.metro.ccms.common.annotation.Excel;
import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.util.Date;


/**
 * 指标表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
public class ModelIndexDO extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Excel(name = "指标编码",sort = 0)
	private Long id;
	/**
	 * 指标名称
	 */
	@Excel(name = "指标名称",sort = 1)
	private String name;
	/**
	 * 指标类型（A:定性指标 B:财务指标 C:交易指标）
	 */
	@Excel(name = "指标大类",sort = 3,readConverterExp = "A=定性指标,B=财务指标,C=交易指标")
	private String type;
	/**
	 * 打分方法
	 */
	@Excel(name = "打分方法",sort = 4,readConverterExp = "1=逻辑判断法,2=区间打分法")
	private String method;
	/**
	 * 表达式
	 */
	private String expression;
	/**
	 * 描述
	 */
	@Excel(name = "指标描述",sort = 2)
	private String description;
	/**
	 * 状态(0：无效，1：有效)
	 * */
	private Integer status;
	/**
	 * 删除标记(0：正常，1：已删除)
	 * */
	private Integer deleted;
	/**
	 * 财务标记（计算分数时用来标记是否进行标准和非标准计算）
	 */
	private String finStandrad;

	private Integer orderId;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getFinStandrad() {
		return finStandrad;
	}

	public void setFinStandrad(String finStandrad) {
		this.finStandrad = finStandrad;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}
