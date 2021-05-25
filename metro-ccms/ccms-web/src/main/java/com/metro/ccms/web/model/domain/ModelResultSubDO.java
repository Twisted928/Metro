package com.metro.ccms.web.model.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 模型测算结果指标明细表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
public class ModelResultSubDO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主表ID
     */
    private Long mainId;
    /**
     * 指标ID
     */
    private Long indexId;
    /**
     * 指标名称
     */
    private String indexName;
    /**
     * 指标类型（A:定性指标 B:财务指标 C:交易指标）
     */
    private String type;
    /**
     * 描述
     */
    private String description;
    /**
     * 标准值
     */
    private Integer standard;
    /**
     * 权重
     */
    private BigDecimal weight;
    /**
     * 转换值
     */
    private BigDecimal value;
    /**
     * 原始值，用户填入值
     */
    private String nativeValue;
    private String itemDescription;
    private BigDecimal score;
    private String exception;
    private String remark;
    private String method;
    private String expression;
    private Integer orderId;

    public Long getMainId() {
        return mainId;
    }

    public void setMainId(Long mainId) {
        this.mainId = mainId;
    }

    public Long getIndexId() {
        return indexId;
    }

    public void setIndexId(Long indexId) {
        this.indexId = indexId;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
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

    public Integer getStandard() {
        return standard;
    }

    public void setStandard(Integer standard) {
        this.standard = standard;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getNativeValue() {
        return nativeValue;
    }

    public void setNativeValue(String nativeValue) {
        this.nativeValue = nativeValue;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
