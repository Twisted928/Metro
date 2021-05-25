package com.metro.ccms.web.debtprotection.mapper;


import com.metro.ccms.web.debtprotection.domian.GuaranteeDO;
import com.metro.ccms.web.debtprotection.query.GuaranteeQuery;
import com.metro.ccms.web.debtprotection.vo.GuaranteeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GuaranteeMapper {

    /**
     * 担保函管理-查询
     *
     * @param guaranteeQuery 条件查询Query
     * @return List<GuaranteeDO>
     */
    List<GuaranteeDO> list(GuaranteeQuery guaranteeQuery);

    /**
     * 担保函管理-新增
     *
     * @param guaranteeVO 担保函管理组合VO
     */
    void save(GuaranteeVO guaranteeVO);

    /**
     * 担保函管理-修改
     *
     * @param guaranteeVO 担保函管理组合VO
     */
    void update(GuaranteeVO guaranteeVO);

    /**
     * 担保函管理-删除
     *
     * @param id 主键ID
     */
    void delete(String id);

    /**
     * 担保函管理-详情
     *
     * @param id 主键
     * @return GuaranteeVO
     */
    GuaranteeVO selectByPrimaryKey(Long id);

    /**
     * 判断担保函编码是否唯一
     *
     * @param gtcode 担保函编码
     * @return int
     */
    int getByCode(String gtcode);
}