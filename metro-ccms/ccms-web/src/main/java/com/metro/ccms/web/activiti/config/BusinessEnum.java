package com.metro.ccms.web.activiti.config;

public enum BusinessEnum {

    //信用组-集团总额度批复-NKA
    CREDIT_GROUP_1("CREDIT_GROUP_1"),
    //信用组-集团总额度批复-长沙财政
    CREDIT_GROUP_2("CREDIT_GROUP_2"),
    //信用组-集团总额度内分配
    CREDIT_GROUP_3("CREDIT_GROUP_3"),
    //信用模块-额度申请批复
    CREDIT_LIMIT_1("CREDIT_LIMIT_1"),
    //信用复审类
    CREDIT_SENCOND_1("CREDIT_SENCOND_1"),
    //白名单
    CREDIT_WHITE_1("CREDIT_WHITE_1");

    String value;

    BusinessEnum(String cust) {
        this.value = cust;
    }

    public String getValue() {
        return value;
    }
}
