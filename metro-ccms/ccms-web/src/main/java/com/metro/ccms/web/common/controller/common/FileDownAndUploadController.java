package com.metro.ccms.web.common.controller.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.common.utils.file.FileUploadUtils;
import com.metro.ccms.common.utils.file.FileUtils;
import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.system.service.ISysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 10:30 2020/12/8
 * @Modified By:
 */
@RequestMapping("/file/downAndUpload")
@RestController
public class FileDownAndUploadController {

    @Autowired
    private ISysFileService iSysFileService;

    /**
     * 附件上传
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    public Result uploadFile(MultipartHttpServletRequest request){
        String path="";
        String fileName="";
        SysBasicFile sysBasicFile = null;
        try {
            Iterator<String> fileNameItr = request.getFileNames();
            String name = fileNameItr.next();
            MultipartFile file = request.getFile(name);

            //校验
            if(file.getOriginalFilename() == null || "".equals(file.getOriginalFilename())){
                return Result.error("未获取到文件名");
            }
            if(request.getParameter("id") == null || "".equals(request.getParameter("id"))){
                return Result.error("未获取到单号");
            }
            if(request.getParameter("type") == null || "".equals(request.getParameter("type"))){
                return Result.error("未获取到业务类型");
            }
            fileName=file.getOriginalFilename();
            path=FileUploadUtils.upload(file);
            //保存到附件表
            sysBasicFile =new SysBasicFile();
            sysBasicFile.setApplicationNo(request.getParameter("id"));
            sysBasicFile.setCtype(request.getParameter("type"));
            sysBasicFile.setAttachmentUrl(path);
            sysBasicFile.setAttachmentName(fileName);
            sysBasicFile.setCreatedBy(SecurityUtils.getLoginUser().getUser().getNickName());
            sysBasicFile.setCreateTime(new Date());
            sysBasicFile.setAttachitems(request.getParameter("fileSize"));
            iSysFileService.saveFile(sysBasicFile);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("上传失败");
        }
        return Result.success(sysBasicFile);
    }

    /**
     * 附件下载
     * @param data
     * @param response
     * @throws Exception
     */
    @RequestMapping("/downloadFile")
    public void downloadFile(@RequestBody JSONObject data, HttpServletResponse response) throws Exception{
        String fileName=data.getString("fileName");
        String filePath=data.getString("filePath");
        OutputStream outputStream = null;
        String[] name = fileName.split("\\.");
        String lowName = name[name.length-1].toLowerCase();
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<name.length;i++){
            if(i == name.length-1){
                sb.append(lowName);
            }else {
                sb.append(name[i]+".");
            }
        }

        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + new String(new String(sb).getBytes("gb2312"), "ISO8859-1"));

        outputStream = response.getOutputStream();

        FileUtils.writeBytes(filePath,outputStream);
    }

    /**
     * 删除附件
     * @param data
     * @return
     */
    @RequestMapping("/deleteFile")
    public Result deleteFile(@RequestBody JSONObject data){
        String filePath=data.getString("filePath");
        Long id=data.getLong("id");
        return iSysFileService.deleteFile(id,filePath);
    }

    /**
     * 批量删除附件
     * @param array
     * @return
     */
    @RequestMapping("/deleteFileList")
    public Result deleteFileList(@RequestBody JSONArray array){
        List<SysBasicFile> files=array.toJavaList(SysBasicFile.class);
        if (files==null || files.size()==0){
            return Result.error("附件列表为空!");
        }
        for (SysBasicFile file:files){
            iSysFileService.deleteFile(file.getId(),file.getAttachmentUrl());
        }
        return Result.success();
    }
}
