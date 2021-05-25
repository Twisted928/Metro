package com.metro.ccms.web.model.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 模型指标表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
public class ModelInfoIndexDO extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	/**
	 * 模型ID
	 */
	private Long modelId;
	/**
	 * 指标ID
	 */
	private Long indexId;
	/**
	 * 排序
	 */
	private Integer orderId;
	/**
	 * 指标名字
	 */
	private String indexName;
	/**
	 * 表达式
	 */
	private String expression;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 指标类型（A:定性指标 B:财务指标 C:交易指标）
	 */
	private String type;
	/**
	 * 见证性资料1:有；0:无
	 */
	private String file;
	/**
	 * 权重
	 */
	private BigDecimal weight;
	/**
	 * 打分方法：逻辑判断和区间打分法
	 */
	private String method;
	/**
	 * 加减分  0:非加减项；1:加减分
	 */
	private String addSubtract;
	/**
	 * 标准分
	 */
	private Integer standrad;

	private String finStandrad;

	/**
	 * 状态(0：无效，1：有效)
	 * */
	private Integer status;
	/**
	 * 删除标记(0：正常，1：已删除)
	 * */
	private Integer deleted;


	/**
	 * 冗余字段
	 */
	private List<ModelInfoIndexItemDO> itemList;

	public List<ModelInfoIndexItemDO> getItemList() {
		return itemList;
	}

	public void setItemList(List<ModelInfoIndexItemDO> itemList) {
		this.itemList = itemList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public Long getIndexId() {
		return indexId;
	}

	public void setIndexId(Long indexId) {
		this.indexId = indexId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getAddSubtract() {
		return addSubtract;
	}

	public void setAddSubtract(String addSubtract) {
		this.addSubtract = addSubtract;
	}

	public Integer getStandrad() {
		return standrad;
	}

	public void setStandrad(Integer standrad) {
		this.standrad = standrad;
	}

	public String getFinStandrad() {
		return finStandrad;
	}

	public void setFinStandrad(String finStandrad) {
		this.finStandrad = finStandrad;
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
}
