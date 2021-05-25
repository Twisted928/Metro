package com.metro.ccms.web.collection.service.impl;

import com.metro.ccms.common.core.domain.Result;
import com.metro.ccms.common.core.domain.model.LoginUser;
import com.metro.ccms.common.utils.SecurityUtils;
import com.metro.ccms.system.domain.SysBasicFile;
import com.metro.ccms.system.service.ISysFileService;
import com.metro.ccms.web.collection.vo.CollectionAndDetailVO;
import com.metro.ccms.web.collection.vo.CollectionVO;
import com.metro.ccms.web.collection.domian.*;
import com.metro.ccms.web.collection.mapper.CollectionMapper;
import com.metro.ccms.web.collection.query.CollectionQuery;
import com.metro.ccms.web.collection.service.CollectionService;
import com.metro.ccms.web.collection.vo.CollectionletterVO;
import com.metro.ccms.web.httpsInterface.domain.BsegInterfaceDO;
import com.metro.ccms.web.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;

/**
* @description
* @author weiwenhui
* @date 2020/12/11 17:56
*/
@Service
public class CollectionServiceImpl implements CollectionService {
    private static final Logger logger = LoggerFactory.getLogger(CollectionServiceImpl.class);
    @Resource
    private CollectionMapper collectionMapper;
    @Resource
    private ISysFileService iSysFileService;
    @Resource
    private CommonUtils commonUtils;

    @Override
    public List<CollectionVO> selectCollection(CollectionQuery collectionQuery) {
        return collectionMapper.selectCollection(collectionQuery);
    }

    @Override
    public CollectionAndDetailVO selectCollectionDetail(CollectionDO collectionDO) {
        CollectionAndDetailVO collectionDetailVO = new CollectionAndDetailVO();
        CollectionVO collection = collectionMapper.selPdfInfo(collectionDO.getId());
        List<CollectionDetailDO> collectionDetailDOList = collectionMapper.getCollectionDetails(collection.getApplicationNo());
        List<CollectionRecordDO> collectionRecordDOList = collectionMapper.getCollectionRecords(collection.getApplicationNo());
        //获取附件列表
        List<SysBasicFile> sysBasicFiles = iSysFileService.getFileList(collectionDO.getId().toString(),"10");
        collectionDetailVO.setSysBasicFile(sysBasicFiles);
        collectionDetailVO.setCollectionVO(collection);
        collectionDetailVO.setCollectionDetailDO(collectionDetailDOList);
        collectionDetailVO.setCollectionRecordDO(collectionRecordDOList);
        return collectionDetailVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveCollection(CollectionAndDetailVO collectionAndDetailVO) {
        try {
            //获取当前用户
            LoginUser loginUser = SecurityUtils.getLoginUser();
            List<CollectionRecordDO> listRecord =collectionAndDetailVO.getCollectionRecordDO();
            //默认页面查出的数据都是删除的状态
            collectionMapper.delTzInfo(loginUser.getUser().getNickName(),collectionAndDetailVO.getCollectionVO().getApplicationNo());
            //通过ID循环判断新增或修改催收台账信息
            for (CollectionRecordDO collectionRecordDO:listRecord) {
                if (collectionRecordDO.getId()!=null){
                    //将修改的台账信息更新为正常状态
                    collectionRecordDO.setDeleted(0);
                    //更新修改人
                    collectionRecordDO.setUpdatedBy(loginUser.getUser().getNickName());
                    collectionMapper.updateTzInfo(collectionRecordDO);
                }
                else {
                    collectionRecordDO.setApplicationNo(collectionAndDetailVO.getCollectionVO().getApplicationNo());
                    collectionRecordDO.setDepartCode(collectionAndDetailVO.getCollectionVO().getDepartCode());
                    collectionRecordDO.setStoreCode(collectionAndDetailVO.getCollectionVO().getStoreCode());
                    //更新创建人
                    collectionRecordDO.setCreatedBy(loginUser.getUser().getNickName());
                    collectionMapper.saveTzInfo(collectionRecordDO);
                }
            }
            //判断删除台账时，页面上没有台账时默认单据状态初始值为催收中
            int count = collectionMapper.selCountRecord(collectionAndDetailVO.getCollectionVO().getApplicationNo());
            if(count <= 0 ){
                collectionMapper.upunningStatusForappNo(collectionAndDetailVO.getCollectionVO().getApplicationNo());
            }else {
                //根据催收单号更新主表单据状态（这个状态需要页面传给后台）单号
                collectionMapper.updunningStatus(collectionAndDetailVO.getCollectionVO().getApplicationNo());
            }
        }
        catch (Exception e){
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("提交失败！");
        }
        return Result.success("提交成功！");
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteCollection(CollectionDO collectionDO) {
        try {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            int record = collectionMapper.selCountRecord(collectionDO.getApplicationNo());
            if(record>=1){
                return Result.error("存在台账信息，不能删除");
            }
            collectionDO.setUpdatedBy(loginUser.getUser().getNickName());
            collectionMapper.deleteCollection(collectionDO);
        }
        catch (Exception e){
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("删除失败！");
        }
        return Result.success("删除成功！");
    }

    /**
     * 催收单据PDF预览根据id查询
     */

    @Override
    public CollectionletterVO selPdfInfo(CollectionVO collectionVO) {
        CollectionletterVO collectionletterVO = new CollectionletterVO();
        CollectionVO coll = collectionMapper.selPdfInfo(collectionVO.getId());
        if ((coll.getIdueheji365()!=null && coll.getIdueheji365()>0) || (coll.getIdue090()!=null && coll.getIdue090()>0)){
            coll.setIflag("逾期60天");
        }else if (coll.getIdue060()!=null && coll.getIdue060()>0){
            coll.setIflag("逾期30天");
        }
        else{
            coll.setIflag("逾期15天");
        }
        List<Date> fdate = collectionMapper.selDate(coll);
        List<CollectionDetailDO> collDetails = collectionMapper.getCollectionDetails(collectionVO.getApplicationNo());
        collectionletterVO.setCollectionVO(coll);
        collectionletterVO.setFdate(fdate);
        collectionletterVO.setCollectionDetailDO(collDetails);
        return collectionletterVO;
    }

    /**
     * 催收单据保存功能
     * @param bsegInterfaceDO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result insertCollection(BsegInterfaceDO bsegInterfaceDO) {
        try {
            //获取当前用户
            LoginUser loginUser = SecurityUtils.getLoginUser();
            //查询明细
            List<BsegInterfaceDO> bsegInterfaceDOList =collectionMapper.selRange(bsegInterfaceDO);
            if(bsegInterfaceDOList.size()<=0){
                return Result.error("未查到符合条件的数据");
            }
            BsegInterfaceDO b= bsegInterfaceDOList.get(0);
            //查询金额
            CollectionDO collectionDO = selMoney(bsegInterfaceDO);
            if (collectionDO ==null){
                return Result.error("未查到符合条件的数据");
            }
            collectionDO.setCardName(b.getCardName());
            collectionDO.setCardCode(b.getCustomerId());
            collectionDO.setStoreCode(b.getStoreId());
            collectionDO.setDepartCode(b.getDepartCode());
            collectionDO.setCustCode(b.getCustCode());
            collectionDO.setCustName(b.getCustName());
            collectionDO.setCreatedBy(loginUser.getUser().getNickName());
            collectionDO.setDdate(bsegInterfaceDO.getZaldt());
            //查询最大id，生成单号
            String id =collectionMapper.selmaxid(collectionDO);
            String applicationNo = commonUtils.getApplicationNo(Long.parseLong(id==null ? "0":id),6);
            collectionDO.setApplicationNo(applicationNo);
            //保存催收单据表
            collectionMapper.saveDunning(collectionDO);
            //循环list保存催收明细表
             for (BsegInterfaceDO bseg: bsegInterfaceDOList
                ) {
                 CollectionDetailDO collectionDetailDO= new CollectionDetailDO();
                 collectionDetailDO.setCreatedBy(loginUser.getUser().getNickName());
                 collectionDetailDO.setApplicationNo(collectionDO.getApplicationNo());
                 collectionDetailDO.setCardName(bseg.getCardName());
                 collectionDetailDO.setCustName(bseg.getCustName());
                 collectionDetailDO.setCustCode(bseg.getCustCode());
                 collectionDetailDO.setDepartCode(bseg.getDepartCode());
                 collectionDetailDO.setAmount(bseg.getWrbtr());
                 collectionDetailDO.setCardCode(bseg.getCustomerId());
                 collectionDetailDO.setCsbvcode(bseg.getBelnr());
                 collectionDetailDO.setDduedate(bseg.getZaldt());
                 collectionDetailDO.setDsbvdate(bseg.getBudat());
                 collectionDetailDO.setPaymentTerm(bseg.getZterm());
                 collectionDetailDO.setStandardCurrency(bseg.getlWaers());
                 collectionDetailDO.setStoreCode(bseg.getStoreId());
                 collectionMapper.saveDunningDetail(collectionDetailDO);
                }
            
        }catch (Exception e){
            // 打印错误信息
            logger.error(getExceptionInfo(e));
            // 在catch异常中进行手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            // TODO 你处理信息
            return Result.error("新增失败！");
        }
        return Result.success("新增成功！");
    }

    /**
     * 催收单据新增查询求和金额
     * @param bsegInterfaceDO
     * @return
     */
    public CollectionDO selMoney(BsegInterfaceDO bsegInterfaceDO) {
        CollectionDO collectionDO =new CollectionDO();
        collectionDO.setIdue015(collectionMapper.selIdueDay(bsegInterfaceDO,"0","15"));
        collectionDO.setIdue030(collectionMapper.selIdueDay(bsegInterfaceDO,"16","30"));
        collectionDO.setIdue060(collectionMapper.selIdueDay(bsegInterfaceDO,"31","60"));
        collectionDO.setIdue090(collectionMapper.selIdueDay(bsegInterfaceDO,"61","90"));
        collectionDO.setIdue180(collectionMapper.selIdueDay(bsegInterfaceDO,"151","180"));
        collectionDO.setIdue360(collectionMapper.selIdueDay(bsegInterfaceDO,"331","360"));
        collectionDO.setIdue361(collectionMapper.selIdueDay(bsegInterfaceDO,"361","720"));
        collectionDO.setIdue120(collectionMapper.selIdueDay(bsegInterfaceDO,"91","120"));
        collectionDO.setIdue150(collectionMapper.selIdueDay(bsegInterfaceDO,"121","150"));
        collectionDO.setIdue210(collectionMapper.selIdueDay(bsegInterfaceDO,"181","210"));
        collectionDO.setIdue240(collectionMapper.selIdueDay(bsegInterfaceDO,"211","240"));
        collectionDO.setIdue270(collectionMapper.selIdueDay(bsegInterfaceDO,"241","270"));
        collectionDO.setIdue300(collectionMapper.selIdueDay(bsegInterfaceDO,"271","300"));
        collectionDO.setIdue330(collectionMapper.selIdueDay(bsegInterfaceDO,"301","330"));
        collectionDO.setIdue721(collectionMapper.sel721Idue(bsegInterfaceDO));
        collectionDO.setIdue(collectionMapper.selIdue(bsegInterfaceDO));
        collectionDO.setIar(collectionMapper.selIar(bsegInterfaceDO));
        collectionDO.setUndue(collectionMapper.selUndue(bsegInterfaceDO));
        return collectionDO;
    }
    /**
     * 催收单据新增查询范围
     * @param bsegInterfaceDO
     * @return
     */
    @Override
    public List<BsegInterfaceDO> selRange(BsegInterfaceDO bsegInterfaceDO) {
        return collectionMapper.selRange(bsegInterfaceDO);
    }

    /**
     * 将异常信息转换为string类型
     * @param e 异常
     * @return string后的异常信息
     */
    public static String getExceptionInfo(Exception e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        return baos.toString();
    }
}
