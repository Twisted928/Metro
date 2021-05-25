package com.metro.ccms.system.mapper;

import com.metro.ccms.system.domain.SysBasicFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 13:41 2020/12/8
 * @Modified By:
 */
public interface SysFileMapper {

    /**
     * 根据单号与业务类型查询附件列表
     * @param applicationNo
     * @param ctype
     * @return
     */
    public List<SysBasicFile> getFileListByNoAndType(@Param("applicationNo") String applicationNo, @Param("ctype") String ctype);
    /**
     * 查询文件列表
     *
     * @param sysBasicFile 文件
     * @return 文件集合
     */
    public List<SysBasicFile> selectTbBasicFileList(SysBasicFile sysBasicFile);

    /**
     * 新增文件
     *
     * @param sysBasicFile 文件
     * @return 结果
     */
    public int insertTbBasicFile(SysBasicFile sysBasicFile);

    /**
     * 修改文件
     *
     * @param sysBasicFile 文件
     * @return 结果
     */
    public int updateTbBasicFile(SysBasicFile sysBasicFile);

    /**
     * 删除文件
     *
     * @param id 文件ID
     * @return 结果
     */
    public int deleteTbBasicFileById(Long id);

    /**
     * 批量删除文件
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbBasicFileByIds(Long[] ids);
}
