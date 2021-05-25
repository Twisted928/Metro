package com.metro.ccms.web.model.domain;

import com.metro.ccms.common.core.domain.BaseEntity;


/**
 *<p>
 *规则引擎指标
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/
public class RuleModelIndexDO extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 指标名称
	 */
	private String name;
	/**
	 * 指标类型（A:信用准入 B:保险准入）
	 */
	private String type;
	/**
	 * 打分方法
	 */
	private String method;
	/**
	 * 描述
	 */
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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

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

	@Override
	public String toString() {
		return "RuleModelIndexDO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", method='" + method + '\'' +
				", description='" + description + '\'' +
				", status=" + status +
				", deleted=" + deleted +
				", finStandrad='" + finStandrad + '\'' +
				", orderId=" + orderId +
				'}';
	}
}
