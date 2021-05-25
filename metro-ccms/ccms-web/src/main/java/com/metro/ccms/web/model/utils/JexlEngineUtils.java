package com.metro.ccms.web.model.utils;

import org.apache.commons.jexl2.*;

/**
 * @author ：yuanjh
 * @description：表达式引擎
 * @modified By：
 * @version: 1.0
 */
public class JexlEngineUtils {

    private static final JexlEngine ENGINE = new JexlEngine();

    private JexlEngineUtils() {

    }

    private static JexlEngine getJexlEngine() {
        return ENGINE;
    }

    public static Expression createExpression(String expressionStr) {
        ENGINE.setStrict(true);
        return ENGINE.createExpression(expressionStr);
    }

    public static UnifiedJEXL createUjexl() {
        return new UnifiedJEXL(ENGINE);
    }


}
