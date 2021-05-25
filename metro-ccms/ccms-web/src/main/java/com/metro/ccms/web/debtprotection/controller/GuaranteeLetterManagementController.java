package com.metro.ccms.web.debtprotection.controller;

import com.alibaba.fastjson.JSON;
import com.metro.ccms.common.core.controller.BaseController;
import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.page.TableDataInfo;
import com.metro.ccms.common.utils.poi.ExcelUtil;
import com.metro.ccms.web.debtprotection.domian.GuaranteeDO;
import com.metro.ccms.web.debtprotection.domian.GuaranteeScopeDO;
import com.metro.ccms.web.debtprotection.query.GuaranteeQuery;
import com.metro.ccms.web.debtprotection.service.GuaranteeLetterManagementService;
import com.metro.ccms.web.debtprotection.vo.GuaranteeExport;
import com.metro.ccms.web.debtprotection.vo.GuaranteeVO;
import com.metro.ccms.web.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 担保函管理
 *
 * @author guangle
 * @date 2020/12/14
 * @since 1.0.0
 */
@RestController
@RequestMapping("/guaranteeLetter/management")
public class GuaranteeLetterManagementController extends BaseController {

    @Autowired
    private GuaranteeLetterManagementService guaranteeLetterManagementService;
    @Autowired
    private CommonUtils commonUtils;

    /**
     * 担保函管理-查询
     *
     * @param guaranteeQuery 条件查询Query
     * @return 分页数据
     */
//    @PreAuthorize("@ss.hasPermi('guaranteeLetter:management:list')")
    @GetMapping("/list")
    public TableDataInfo list(GuaranteeQuery guaranteeQuery) {
        startPage();
        return getDataTable(guaranteeLetterManagementService.list(guaranteeQuery));
    }

    /**
     * 担保函管理-新增
     *
     * @param guaranteeVO 担保函管理组合VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('guaranteeLetter:management:save')")
    @PostMapping("/save")
    public Result save(@RequestBody GuaranteeVO guaranteeVO) {
        if (fieldJudgment(guaranteeVO)) {
            return Result.error("必填字段未全部填写，请检查！");
        }
        return guaranteeLetterManagementService.save(guaranteeVO);
    }

    /**
     * 卡片新增
     *
     * @param guaranteeScopeDO 担保函范围表
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('guaranteeLetter:management:cardSave')")
    @PostMapping("/cardSave")
    public Result cardSave(@RequestBody GuaranteeScopeDO guaranteeScopeDO) {
        return guaranteeLetterManagementService.cardSave(guaranteeScopeDO);
    }

    /**
     * 卡片删除
     *
     * @param guaranteeScopeDO 担保函范围表
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('guaranteeLetter:management:cardDelete')")
    @PostMapping("/cardDelete")
    public Result cardDelete(@RequestBody GuaranteeScopeDO guaranteeScopeDO) {
        return guaranteeLetterManagementService.cardDelete(guaranteeScopeDO);
    }

    /**
     * 担保函管理-修改
     *
     * @param guaranteeVO 担保函管理组合VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('guaranteeLetter:management:update')")
    @PostMapping("/update")
    public Result update(@RequestBody GuaranteeVO guaranteeVO) {
        if (fieldJudgment(guaranteeVO)) {
            return Result.error("必填字段未全部填写，请检查！");
        }
        return guaranteeLetterManagementService.update(guaranteeVO);
    }

    /**
     * 担保函管理-删除
     *
     * @param guaranteeVO 担保函管理组合VO
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('guaranteeLetter:management:delete')")
    @PostMapping("/delete")
    public Result delete(@RequestBody GuaranteeVO guaranteeVO) {
        return guaranteeLetterManagementService.delete(guaranteeVO);
    }

    /**
     * 担保函管理-详情
     *
     * @param guaranteeVO 担保函管理组合VO
     * @return GuaranteeVO
     */
//    @PreAuthorize("@ss.hasPermi('guaranteeLetter:management:getOne')")
    @PostMapping("/getOne")
    public Result getOne(@RequestBody GuaranteeVO guaranteeVO) {
        return Result.success(guaranteeLetterManagementService.getOne(guaranteeVO));
    }

    /**
     * 获取担保参数类型
     *
     * @return List<BasicData>
     */
//    @PreAuthorize("@ss.hasPermi('guaranteeLetter:management:type')")
    @PostMapping("/type")
    public Result type() {
        return Result.success(commonUtils.getBasicDataByCType("GuaranteeType"));
    }

    /**
     * 导出
     *
     * @param guaranteeQuery 条件查询Query
     * @return Result
     */
//    @PreAuthorize("@ss.hasPermi('guaranteeLetter:management:export')")
    @GetMapping("/export")
    public Result export(GuaranteeQuery guaranteeQuery) {
        List<GuaranteeDO> list = guaranteeLetterManagementService.list(guaranteeQuery);
        List<GuaranteeExport> guaranteeExports = list2OtherList(list, GuaranteeExport.class);
        ExcelUtil<GuaranteeExport> excelUtil = new ExcelUtil<>(GuaranteeExport.class);
        return excelUtil.exportExcel(guaranteeExports, "担保函管理导出");
    }

    /**
     * 必填字段判断
     *
     * @param guaranteeVO 接受对象
     * @return boolean
     */
    private boolean fieldJudgment(GuaranteeVO guaranteeVO) {
        return guaranteeVO.getGtcode() == null || guaranteeVO.getGtcode().isEmpty() ||
                guaranteeVO.getGuaranteetype() == null ||
                guaranteeVO.getCustCode() == null || guaranteeVO.getCustCode().isEmpty() ||
                guaranteeVO.getCustName() == null || guaranteeVO.getCustName().isEmpty() ||
                guaranteeVO.getGtsum() == null ||
                guaranteeVO.getValidFrom() == null ||
                guaranteeVO.getValidTo() == null;
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
