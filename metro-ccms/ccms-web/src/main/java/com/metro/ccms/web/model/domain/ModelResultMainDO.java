package com.metro.ccms.web.model.domain;

import com.metro.ccms.common.core.domain.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;


/**
 * 模型测算结果表
 * @author yuanjh
 * @email yuanjhcn@aliyun.com
 */
public class ModelResultMainDO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;
    /**
     * 模型id
     */
    private Long modId;
    /**
     * 模型名称
     */
    private String modName;
    /**
     * 单号
     */
    private String documentNo;
    /**
     * 区域id
     */
    private Integer regionId;
    /**
     * 总分数
     */
    private BigDecimal score;
    /**
     * 指标分数
     */
    private BigDecimal indexScore;
    /**
     * 加减分数
     */
    private BigDecimal addSubScore;
    /**
     * 结果类型TEST试算、SUBMIT提交
     */
    private String type;

    /**
     * 冗余字段
     */
    private Map<String, String> map;


    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModId() {
        return modId;
    }

    public void setModId(Long modId) {
        this.modId = modId;
    }

    public String getModName() {
        return modName;
    }

    public void setModName(String modName) {
        this.modName = modName;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getIndexScore() {
        return indexScore;
    }

    public void setIndexScore(BigDecimal indexScore) {
        this.indexScore = indexScore;
    }

    public BigDecimal getAddSubScore() {
        return addSubScore;
    }

    public void setAddSubScore(BigDecimal addSubScore) {
        this.addSubScore = addSubScore;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
