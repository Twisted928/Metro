package com.metro.ccms.system.mapper;

import com.metro.ccms.common.core.domain.entity.SysBasicData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 10:31 2020/12/14
 * @Modified By:
 */
public interface SysBasicDataMapper {

    /**
     * 查询参数
     *
     * @param id 参数ID
     * @return 参数
     */
    public SysBasicData selectTbBasicDataById(Long id);

    /**
     * 查询参数列表
     *
     * @param tbBasicData 参数
     * @return 参数集合
     */
    public List<SysBasicData> selectTbBasicDataList(SysBasicData tbBasicData);

    /**
     * 新增参数
     *
     * @param tbBasicData 参数
     * @return 结果
     */
    public int insertTbBasicData(SysBasicData tbBasicData);

    /**
     * 修改参数
     *
     * @param tbBasicData 参数
     * @return 结果
     */
    public int updateTbBasicData(SysBasicData tbBasicData);

    /**
     * 删除参数
     *
     * @param id 参数ID
     * @return 结果
     */
    public int deleteTbBasicDataById(Long id);

    /**
     * 批量删除参数
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbBasicDataByIds(Long[] ids);

    /**
     * 根据查询条件获取数量
     * @param ctype
     * @param description
     * @return
     */
    public int getBasicDataBySelect(@Param("ctype")String ctype,@Param("description")String description);

    /**
     * 根据id获取下级
     * @param id
     * @return
     */
    public int hasChildById(@Param("id") Long id);
}
