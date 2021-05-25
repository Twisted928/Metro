package com.metro.ccms.system.service;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.system.domain.SysBasicFile;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description: 文件服务
 * @Date: Created in 13:42 2020/12/8
 * @Modified By:
 */
public interface ISysFileService {

    /**
     * 保存附件
     * @param sysBasicFile
     * @return
     */
    public int saveFile(SysBasicFile sysBasicFile);

    /**
     * 获取附件列表
     * @param applicationNo
     * @param ctype
     * @return
     */
    public List<SysBasicFile> getFileList(String applicationNo,String ctype);

    /**
     * 删除附件
     * @param id
     * @param filePath
     * @return
     */
    public Result deleteFile(Long id, String filePath);
}
