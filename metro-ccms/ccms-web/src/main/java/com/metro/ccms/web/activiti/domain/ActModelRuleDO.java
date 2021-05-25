package com.metro.ccms.web.activiti.domain;

/**
 * @Author: fangyongjie
 * @Description: 流程规则配置表
 * @Date: Created in 16:09 2021/1/7
 * @Modified By:
 */
public class ActModelRuleDO {

    /**
     * modelId
     */
    private String mid;
    /**
     * 规则描述
     */
    private String des;
    /**
     * 规则表达式
     */
    private String expression;


    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
