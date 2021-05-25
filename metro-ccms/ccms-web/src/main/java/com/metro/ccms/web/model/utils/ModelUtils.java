package com.metro.ccms.web.model.utils;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ：yuanjh
 * @description：模型专用工具类
 * @modified By：
 * @version: 1.0
 */
public class ModelUtils {

    //包含开始和结束1、不包含开始结束2、包含开始不包含结束3、包含结束不包含开始4
    private static final int INTERVAL_START_END = 1;
    private static final int INTERVAL_NO_START_END = 2;
    private static final int INTERVAL_START = 3;
    private static final int INTERVAL_END = 4;

    /**
     * 将指标表达式拆分为集合返回
     * "([资产总额ww（百万）]/[负债总额qq（百万）]+300)+[负债总额dd（百万）]*20"
     *
     * @param experssion
     * @return
     */
    public static Set<String> splitExperssion(String experssion) {
        char[] chars = experssion.toCharArray();

        Set<String> list = new HashSet<>(10);
        StringBuilder c = new StringBuilder();
        boolean bj = false;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '[') {
                bj = true;
                c = new StringBuilder();
                continue;
            }
            if (chars[i] == ']') {
                bj = false;
                list.add(c.toString());
                continue;
            }
            if (bj) {
                c.append(chars[i]);
            }

        }

        return list;
    }

    /**
     * 判断Value是否在begin和end区间内
     *
     * @param value
     * @param begin
     * @param end
     * @return
     */
    public static boolean isInterval(BigDecimal value, BigDecimal begin, BigDecimal end, int type) {

        Assert.notNull(value, "value不能为空");
        Assert.notNull(begin, "begin不能为空");
        Assert.notNull(end, "end不能为空");

        switch (type) {
            case INTERVAL_START_END:
                return value.compareTo(begin)>=0&&value.compareTo(end)<=0;

            case INTERVAL_NO_START_END:
                return value.compareTo(begin)>0&&value.compareTo(end)<0;

            case INTERVAL_START:
                return value.compareTo(begin)>=0&&value.compareTo(end)<0;

            case INTERVAL_END:
                return value.compareTo(begin)>0&&value.compareTo(end)<=0;

        }

        return false;
    }


    public static void main(String[] args) {
        BigDecimal b=BigDecimal.ONE;
        BigDecimal e=BigDecimal.TEN;

        BigDecimal val=new BigDecimal(10);

        System.out.println(isInterval(val,b,e,1));
        System.out.println(isInterval(val,b,e,2));
        System.out.println(isInterval(val,b,e,3));
        System.out.println(isInterval(val,b,e,4));
    }
}
