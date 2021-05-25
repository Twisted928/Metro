package com.metro.ccms.web.httpsInterface.mapper;

import com.metro.ccms.web.httpsInterface.domain.CustManagerDO;
import com.metro.ccms.web.httpsInterface.domain.PotentialDO;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 18:32 2021/1/18
 * @Modified By:
 */
public interface HttpsInterfaceMapper {

    /**
     * 根据岗位id获取客户经理信息
     * @param postId
     * @return
     */
    public CustManagerDO getCustManagerInfoByPostId(@Param("postId") String postId);

    /**
     * 根据卡号编码查询实时应收和采购潜力金额接口表
     * @param custNo
     * @return
     */
    public PotentialDO getPotentialByCustNo(@Param("custNo") String custNo);
}
