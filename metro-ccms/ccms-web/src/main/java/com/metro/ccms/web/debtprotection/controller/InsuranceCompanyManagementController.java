package com.metro.ccms.web.debtprotection.controller;

import com.alibaba.fastjson.JSON;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.debtprotection.domian.InsureCompanyDO;
import com.metro.ccms.web.debtprotection.domian.InsurePolicyDO;
import com.metro.ccms.web.debtprotection.query.InsureCompanyQuery;
import com.metro.ccms.web.debtprotection.service.InsuranceCompanyManagementService;
import com.metro.ccms.web.debtprotection.vo.InsuranceCombinationVO;
import com.metro.ccms.web.debtprotection.vo.InsureCompanyExport;
import com.metro.ccms.web.debtprotection.vo.InsurePolicyVO;
import com.metro.ccms.web.debtprotection.vo.InsureScopeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 保险公司管理
 *
 * @author guangle
 * @create 2020/11/30
 * @since 1.0.0
 */
@RestController
@RequestMapping("/insurance/management")
public class InsuranceCompanyManagementController extends BaseController {
    @Autowired
    private InsuranceCompanyManagementService insuranceCompanyManagementService;

    /**
     * 保险公司管理-查询
     *
     * @param insureCompanyQuery 保险公司表查询类
     * @return insureCompanyVOPage
     */
//    @PreAuthorize("@ss.hasPermi('insurance:management:list')")
    @GetMapping("/list")
    public TableDataInfo insuranceCompanyManagementList(InsureCompanyQuery insureCompanyQuery) {
        startPage();
        return getDataTable(insuranceCompanyManagementService.insuranceCompanyManagementList(insureCompanyQuery));
    }

    /**
     * 保险公司管理-新增
     *
     * @param insuranceCombinationVO 组合VO 包含多个VO参数
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:management:save')")
    @PostMapping("/save")
    public Result insuranceCompanyManagementAdd(@RequestBody InsuranceCombinationVO insuranceCombinationVO) {
        if (fieldJudgment(insuranceCombinationVO)) {
            return Result.error("必填字段未全部填写，请检查！");
        }
        if (insuranceCombinationVO.getInsurePolicyVOList() != null && insuranceCombinationVO.getInsurePolicyVOList().size() > 0) {
            if (fieldJudgment2(insuranceCombinationVO)) {
                return Result.error("保单必填字段未全部填写，请检查！");
            }
        }
        return insuranceCompanyManagementService.saveInsuranceCompanyManagement(insuranceCombinationVO);
    }

    /**
     * 保险公司管理-修改
     *
     * @param insuranceCombinationVO 组合VO 包含多个VO参数
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:management:update')")
    @PostMapping("/update")
    public Result insuranceCompanyManagementUpdate(@RequestBody InsuranceCombinationVO insuranceCombinationVO) {
        if (fieldJudgment(insuranceCombinationVO)) {
            return Result.error("必填字段未全部填写，请检查！");
        }
        if (insuranceCombinationVO.getInsurePolicyVOList() != null && !insuranceCombinationVO.getInsurePolicyVOList().isEmpty()) {
            if (fieldJudgment2(insuranceCombinationVO)) {
                return Result.error("保单必填字段未全部填写，请检查！");
            }
        }
        return insuranceCompanyManagementService.insuranceCompanyManagementUpdate(insuranceCombinationVO);
    }

    /**
     * 保单新增时,判断时间
     *
     * @param insuranceCombinationVO 组合VO 包含多个VO参数
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:management:timeJudgment')")
    @PostMapping("/timeJudgment")
    public Result timeJudgment(@RequestBody InsuranceCombinationVO insuranceCombinationVO) {
        return insuranceCompanyManagementService.timeJudgment(insuranceCombinationVO);
    }

    /**
     * 保险公司修改时,删除保单判断保单是否可以删除
     *
     * @param insurePolicyDO 保单表DO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:management:policyJudgment')")
    @PostMapping("/policyJudgment")
    public Result insurePolicyJudgment(@RequestBody InsurePolicyDO insurePolicyDO) {
        return insuranceCompanyManagementService.insurePolicyJudgment(insurePolicyDO);
    }


    /**
     * 保险公司管理-删除
     *
     * @param insureCompanyDO 保险公司表
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:management:delete')")
    @PostMapping("/delete")
    public Result insuranceCompanyManagementDelete(@RequestBody InsureCompanyDO insureCompanyDO) {
        return insuranceCompanyManagementService.insuranceCompanyManagementDelete(insureCompanyDO);
    }

    /**
     * 保险公司管理-详细
     *
     * @param insuranceCombinationVO 组合VO
     * @return InsureCompanyVO
     */
//    @PreAuthorize("@ss.hasPermi('insurance:management:getone')")
    @GetMapping("/getone")
    public Result insuranceCompanyManagementGetById(InsuranceCombinationVO insuranceCombinationVO) {
        return Result.success(insuranceCompanyManagementService.insuranceCompanyManagementGetById(insuranceCombinationVO));
    }

    /**
     * 保险公司管理-导出
     *
     * @param insureCompanyQuery 保险公司表查询类
     */
//    @PreAuthorize("@ss.hasPermi('insurance:management:export')")
    @GetMapping("/export")
    public Result insuranceCompanyManagementExport(InsureCompanyQuery insureCompanyQuery) {
        List<InsureCompanyDO> insureCompany = insuranceCompanyManagementService.insuranceCompanyManagementList(insureCompanyQuery);
        List<InsureCompanyExport> insureCompanyExports = list2OtherList(insureCompany, InsureCompanyExport.class);
        ExcelUtil<InsureCompanyExport> excelUtil = new ExcelUtil<>(InsureCompanyExport.class);
        return excelUtil.exportExcel(insureCompanyExports, "保险公司清单导出");
    }

    /**
     * 查看保单范围
     *
     * @param insureScopeVO 保单范围表VO
     * @return page
     */
//    @PreAuthorize("@ss.hasPermi('insurance:management:insureScopeList')")
    @GetMapping("insureScopeList")
    public Result insureScopeList(InsureScopeVO insureScopeVO) {
        return Result.success(insuranceCompanyManagementService.insureScopeList(insureScopeVO));
    }

    /**
     * 保单删除
     *
     * @param insurePolicyVO 保单VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:management:policyDelete')")
    @PostMapping("/policyDelete")
    public Result policyDelete(@RequestBody InsurePolicyVO insurePolicyVO) {
        return insuranceCompanyManagementService.policyDelete(insurePolicyVO);
    }

    /**
     * 保单修改
     *
     * @param insurePolicyVO 保单VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:management:policyUpdate')")
    @PostMapping("/policyUpdate")
    public Result policyUpdate(@RequestBody InsurePolicyVO insurePolicyVO) {
        return insuranceCompanyManagementService.policyUpdate(insurePolicyVO);
    }

    /**
     * 保单新增
     *
     * @param insurePolicyVO 保单VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('insurance:management:policySave')")
    @PostMapping("/policySave")
    public Result policySave(@RequestBody InsurePolicyVO insurePolicyVO) {
        return insuranceCompanyManagementService.policySave(insurePolicyVO);
    }

    /**
     * 必填字段判断
     *
     * @param insuranceCombinationVO 接受对象
     * @return boolean
     */
    private boolean fieldJudgment(InsuranceCombinationVO insuranceCombinationVO) {
        return insuranceCombinationVO.getCompCode() == null || insuranceCombinationVO.getCompCode().isEmpty() ||
                insuranceCombinationVO.getCompName() == null || insuranceCombinationVO.getCompName().isEmpty() ||
                insuranceCombinationVO.getValidFrom() == null || insuranceCombinationVO.getValidTo() == null;
    }

    /**
     * 保单字段判断
     *
     * @param insuranceCombinationVO 接受对象
     * @return boolean
     */
    private boolean fieldJudgment2(InsuranceCombinationVO insuranceCombinationVO) {
        return insuranceCombinationVO.getInsurePolicyVOList().get(0).getPolicyno() == null || insuranceCombinationVO.getInsurePolicyVOList().get(0).getPolicyno().isEmpty() ||
                insuranceCombinationVO.getInsurePolicyVOList().get(0).getValidFrom() == null || insuranceCombinationVO.getInsurePolicyVOList().get(0).getValidTo() == null;
    }

    /**
     * 转为新列表(对象属性名要相同)
     *
     * @param originList 原列表
     * @param tClass     新列表类对象
     * @param <T>        泛型
     * @return list
     */
    public static <T> List<T> list2OtherList(List originList, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        for (Object info : originList) {
            T t = JSON.parseObject(JSON.toJSONString(info), tClass);
            list.add(t);
        }
        return list;
    }
}
