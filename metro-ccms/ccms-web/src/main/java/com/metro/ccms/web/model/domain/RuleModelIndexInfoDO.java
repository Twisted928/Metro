package com.metro.ccms.web.model.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;


/**
 *<p>
 *规则引擎管理
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/24
 * @desc
 **/
public class RuleModelIndexInfoDO extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 模型编码
	 */
	private Long modelId;
	/**
	 * 指标编码
	 */
	private Long indexId;
	/**
	 * 状态(0：无效，1：有效)
	 * */
	private Integer status;
	/**
	 * 删除标记(0：正常，1：已删除)
	 * */
	private Integer deleted;
	/**
	 * 打分方法(1逻辑2区间)
	 * */
	private String method;
	/**
	 * 指标类型（A:定性指标 B:财务指标 C:交易指标）
	 */
	private String type;
	/**
	 * 指标名字
	 */
	private String indexName;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 冗余字段
	 */
	private List<RuleModelIndexInfoitemDO> itemList;

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

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RuleModelIndexInfoitemDO> getItemList() {
		return itemList;
	}

	public void setItemList(List<RuleModelIndexInfoitemDO> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String toString() {
		return "RuleModelIndexInfoDO{" +
				"id=" + id +
				", modelId=" + modelId +
				", indexId=" + indexId +
				", status=" + status +
				", deleted=" + deleted +
				", method='" + method + '\'' +
				", type='" + type + '\'' +
				", indexName='" + indexName + '\'' +
				", description='" + description + '\'' +
				", itemList=" + itemList +
				'}';
	}
}
