package com.metro.ccms.web.customer.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.model.LoginUser;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.web.customer.mapper.MonitoringCustomersMapper;
import com.metro.ccms.web.customer.query.CustomerlistQuery;
import com.metro.ccms.web.customer.query.MonitoringCustomersQuery;
import com.metro.ccms.web.customer.service.MonitoringCustomersService;
import com.metro.ccms.web.customer.vo.CustomerlistVO;
import com.metro.ccms.web.customer.vo.MonitoringCustomersVO;
import com.metro.ccms.web.customer.domian.MonitoringCustomersDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

/**
 *<p>
 *  监控客户清单
 * </p>
 *
 * @author  JIXIANG.SONG
 * @create  2020/12/4
 * @desc
 **/
@Service
public class MonitoringCustomersServiceImpl implements MonitoringCustomersService {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringCustomersServiceImpl.class);

    @Autowired
    private MonitoringCustomersMapper monitoringCustomersMapper;

    /**
     * 查询
     *
     * @param monitoringCustomersQuery
     * @return
     */
    @Override
    public List<MonitoringCustomersVO> pagesel(MonitoringCustomersQuery monitoringCustomersQuery) {
        List<MonitoringCustomersVO> list=monitoringCustomersMapper.pagesel(monitoringCustomersQuery);
        return list;
    }
    /**
     * 查询客户清单
     *
     * @param customerlistQuery
     * @return
     */
    @Override
    public List<CustomerlistVO> pageselkehu(CustomerlistQuery customerlistQuery) {
        List<CustomerlistVO> list=monitoringCustomersMapper.pageselkehu(customerlistQuery);
        return list;
    }
    /**
     * 新增
     *
     * @param monitoringCustomersDO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result savemonitoring(MonitoringCustomersDO monitoringCustomersDO) {
        try {
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            monitoringCustomersDO.setCreatedby(loginUser.getUser().getNickName());
            int count=monitoringCustomersMapper.selcount(monitoringCustomersDO);
            if(count>0){
                return Result.error("该客户编码已存在，不能新增！");
            }
            monitoringCustomersMapper.savemonitoring(monitoringCustomersDO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("保存失败");
        }
        return Result.success("保存成功");
    }
    /**
     * 修改
     *
     * @param monitoringCustomersDO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updatemonitoring(MonitoringCustomersDO monitoringCustomersDO) {
        try {
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            monitoringCustomersDO.setUpdatedby(loginUser.getUser().getNickName());
            int count=monitoringCustomersMapper.selcount(monitoringCustomersDO);
            if(count>0){
                return Result.error("该客户编码已存在有效数据，不能修改！");
            }
            monitoringCustomersMapper.updatemonitoring(monitoringCustomersDO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("修改失败");
        }
        return Result.success("修改成功");
    }
    /**
     * 删除
     *
     * @param monitoringCustomersDO
     * @return Result
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updmonitoring(MonitoringCustomersDO monitoringCustomersDO) {
        try {
            // 获取当前登陆人信息
            LoginUser loginUser = SecurityUtils.getLoginUser();
            monitoringCustomersDO.setCreatedby(loginUser.getUser().getNickName());
            monitoringCustomersMapper.updmonitoring(monitoringCustomersDO);
        } catch (Exception e) {
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }
    /**
     * 将异常信息转换为string类型
     * @param e 异常
     * @return string后的异常信息
     */
    public static String getExceptionInfo(Exception e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        return baos.toString();
    }


}
