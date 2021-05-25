package com.metro.ccms.web.httpsInterface.controller;

import com.alibaba.fastjson.JSONArray;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.web.httpsInterface.dto.CustDcrmDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: fangyongjie
 * @Description:
 * @Date: Created in 17:11 2021/2/23
 * @Modified By:
 */
@RestController
@RequestMapping("/api/out")
public class ApiOutController {


    @RequestMapping("/custDcrm")
    public Result custDcrm(@RequestBody JSONArray array){
        List<CustDcrmDTO> list=array.toJavaList(CustDcrmDTO.class);
        System.out.println("======");
        return Result.success();
    }

}
