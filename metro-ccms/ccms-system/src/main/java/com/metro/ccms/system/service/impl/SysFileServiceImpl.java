package com.metro.ccms.system.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.utils.file.FileUtils;
import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.system.mapper.SysFileMapper;
import com.metro.ccms.system.service.ISysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description: 文件服务
 * @Date: Created in 13:42 2020/12/8
 * @Modified By:
 */
@Service
public class SysFileServiceImpl implements ISysFileService {

    @Autowired
    private SysFileMapper sysFileMapper;


    /**
     * 保存附件
     * @param sysBasicFile
     * @return
     */
    @Override
    public int saveFile(SysBasicFile sysBasicFile) {
        return sysFileMapper.insertTbBasicFile(sysBasicFile);
    }

    /**
     * 获取附件列表
     * @param applicationNo
     * @param ctype
     * @return
     */
    @Override
    public List<SysBasicFile> getFileList(String applicationNo, String ctype) {
        return sysFileMapper.getFileListByNoAndType(applicationNo,ctype);
    }

    /**
     * 删除附件
     * @param id
     * @param filePath
     * @return
     */
    @Override
    public Result deleteFile(Long id,String filePath){
        boolean dl=FileUtils.deleteFile(filePath);
        if (dl){
            sysFileMapper.deleteTbBasicFileById(id);
        }else{
            return Result.error("删除失败!");
        }
        return Result.success();
    }
}
