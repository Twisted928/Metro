package com.metro.ccms.web.credit.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.model.LoginUser;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.web.credit.domain.PayTermDO;
import com.metro.ccms.web.credit.mapper.PayTermMapper;
import com.metro.ccms.web.credit.query.PayTermQuery;
import com.metro.ccms.web.credit.service.PayTermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 * 付款条件
 *
 * @author guangle
 * @date 2020/12/16
 * @since 1.0.0
 */
@Service
public class PayTermServiceImpl implements PayTermService {

    private static final Logger logger = LoggerFactory.getLogger(PayTermServiceImpl.class);


    @Autowired
    private PayTermMapper paytermMapper;

    /**
     * 付款条件-查询
     *
     * @param payTermQuery 付款条件的查询条件
     * @return List<PayTermDO>
     */
    @Override
    public List<PayTermDO> list(PayTermQuery payTermQuery) {
        return paytermMapper.list(payTermQuery);
    }

    /**
     * 付款条件-新增
     *
     * @param payTermDO 付款条件DO
     * @return Result
     */
    @Override
    public Result save(PayTermDO payTermDO) {
        String judgment = judgment(payTermDO);
        if (!"1".equals(judgment)) {
            return Result.error("条件重复,请修改后重试");
        }
        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 创建人
        payTermDO.setCreatedBy(loginUser.getUser().getNickName());
        try {
            paytermMapper.save(payTermDO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            return Result.error("新增发生错误,请稍后再试");
        }
        return Result.success();
    }

    /**
     * 付款条件-修改
     *
     * @param payTermDO 付款条件DO
     * @return Result
     */
    @Override
    public Result update(PayTermDO payTermDO) {
        String judgment = judgment(payTermDO);
        if (!"1".equals(judgment)) {
            return Result.error("条件重复,请修改后重试");
        }
        // 获取当前登陆人信息
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 修改人
        payTermDO.setUpdatedBy(loginUser.getUser().getNickName());
        try {
            paytermMapper.update(payTermDO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            return Result.error("修改发生错误,请稍后再试");
        }
        return Result.success();
    }

    /**
     * 付款条件-删除
     *
     * @param payTermDO 付款条件DO
     * @return Result
     */
    @Override
    public Result delete(PayTermDO payTermDO) {
        try {
            paytermMapper.delete(payTermDO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            return Result.error("删除发生错误,请稍后再试");
        }
        return Result.success();
    }

    /**
     * 将异常信息转换为string类型
     *
     * @param e 异常
     * @return string后的异常信息
     */
    public static String getExceptionInfo(Exception e) {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(bao));
        return bao.toString();
    }

    /**
     * 判断
     * 付款条件先根据判断系统代码是否唯一,在判断付款条件天数和结算方式是否唯一
     *
     * @return "1" 1未重复
     */
    public String judgment(PayTermDO payTermDO) {
        // 先判断系统代码是否唯一
        int code = paytermMapper.getCode(payTermDO);
        if (code == 0) {
            // 判断付款条件天数和结算方式是否唯一
            int unique = paytermMapper.getUnique(payTermDO);
            if (unique == 0) {
                return "1";
            }
        }
        return "0";
    }
}
