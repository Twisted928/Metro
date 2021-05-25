import React, { useState, useEffect } from 'react';
import { Row, Col, Card, Anchor, Space, Button } from 'antd';
import { connect, history } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import { createFromIconfontCN } from '@ant-design/icons';
import FooterToolbar from '@/components/FooterToolbar';
import Styles from './components/style.less';

import BasicInfo from './components/basicInfo';
import LoginCheck from './components/loginCheck';
import CreditApply from './components/creditApply';
import CreditApproval from './components/creditApproval';
import BusiData from './components/busiData';
// import CustTags from './components/custTags';
import CustEval from './components/custEval';
import WittInfo from './components/wittInfo';
import CustEvalOut from './components/custEvalOut';
import ContractInfo from './components/contractInfo';
import CreditRelease from './components/creditRelease';

const { Link } = Anchor;

// 限额申请-新增
const CreditUpdate = () => {
  const [targetOffset, setTargetOffset] = useState(undefined);
  const IconFont = createFromIconfontCN({
    scriptUrl: '//at.alicdn.com/t/font_2134111_hucd0o1xlx.js',
  });

  useEffect(() => {
    console.log(history)
    setTargetOffset(window.innerHeight / 2);
  }, []);

  const onChange = (link) => {
    const thisLink = link.substring(1, link.length);
    const thisIcon = thisLink.concat('Icon');

    // 还原样式
    const bgIcon = document.getElementsByClassName('bgIcon');
    bgIcon.forEach((element, index) => {
      bgIcon[index].style.color = '';
    });
    const bgDiv = document.getElementsByClassName('bgColor');
    bgDiv.forEach((element, index) => {
      bgDiv[index].style.background = '';
      bgDiv[index].style.borderLeft = '3px solid #FFFFFF';
    });
    // 渲染样式
    const choosenIcon = document.getElementsByClassName(thisIcon);
    choosenIcon.forEach((element, index) => {
      choosenIcon[index].style.color = '#333FFF';
    });
    const choosenDiv = document.getElementsByClassName(thisLink);
    choosenDiv.forEach((element, index) => {
      choosenDiv[index].style.background = '#F1F4FF';
      choosenDiv[index].style.borderLeft = '3px solid #333FFF';
    });
  };

  return (
    <PageContainer ghost title={false}>
      <Card className={Styles.creditContainerCard}>
        <Row>
          <Col span={4} key="menu">
            <Anchor
              onChange={onChange}
              onClick={onChange}
              targetOffset={targetOffset}
              className={Styles.creditAnchor}
            >
              <div className={[`${Styles.divwrap}`, `BasicInfo`, `bgColor`].join(' ')}>
                <span className={Styles.iconSty}>
                  <IconFont
                    className={[`bgIcon`, `BasicInfoIcon`].join(' ')}
                    type="icon-jibenxinxi"
                  />
                </span>
                <span>
                  <Link href="#BasicInfo" title="基本信息" style={{ width: 86 }} />
                </span>
              </div>
              <div className={[`${Styles.divwrap}`, `LoginCheck`, `bgColor`].join(' ')}>
                <span className={Styles.iconSty}>
                  <IconFont
                    className={[`bgIcon`, `LoginCheckIcon`].join(' ')}
                    type="icon-zhunruxiaoyan"
                  />
                </span>
                <span>
                  <Link href="#LoginCheck" title="准入校验" />
                </span>
              </div>
              <div className={[`${Styles.divwrap}`, `CreditApply`, `bgColor`].join(' ')}>
                <span className={Styles.iconSty}>
                  <IconFont
                    className={[`bgIcon`, `CreditApplyIcon`].join(' ')}
                    type="icon-shouxinshenqing"
                  />
                </span>
                <span>
                  <Link href="#CreditApply" title="授信申请" />
                </span>
              </div>
              <div className={[`${Styles.divwrap}`, `bgColor`, `CustEval`].join(' ')}>
                <span className={Styles.iconSty}>
                  <IconFont
                    className={[`bgIcon`, `CustEvalIcon`].join(' ')}
                    type="icon-kehuneiping"
                  />
                </span>
                <span>
                  <Link href="#CustEval" title="客户评估" />
                </span>
              </div>
              <div className={[`${Styles.divwrap}`, `bgColor`, `CustEvalOut`].join(' ')}>
                <span className={Styles.iconSty}>
                  <IconFont
                    className={[`bgIcon`, `CustEvalOutIcon`].join(' ')}
                    type="icon-kehuwaiping"
                  />
                </span>
                <span>
                  <Link href="#CustEvalOut" title="客户外评" />
                </span>
              </div>
              <div className={[`${Styles.divwrap}`, `BusiData`, `bgColor`].join(' ')}>
                <span className={Styles.iconSty}>
                  <IconFont
                    className={[`bgIcon`, `BusiDataIcon`].join(' ')}
                    type="icon-yewushuju"
                  />
                </span>
                <span>
                  <Link href="#BusiData" title="业务数据" />
                </span>
              </div>
              <div className={[`${Styles.divwrap}`, `bgColor`, `WittInfo`].join(' ')}>
                <span className={Styles.iconSty}>
                  <IconFont
                    className={[`bgIcon`, `WittInfoIcon`].join(' ')}
                    type="icon-jianzhengxingziliao"
                  />
                </span>
                <span>
                  <Link href="#WittInfo" title="见证性资料" />
                </span>
              </div>
              <div className={[`${Styles.divwrap}`, `bgColor`, `CreditApproval`].join(' ')}>
                <span className={Styles.iconSty}>
                  <IconFont
                    className={[`bgIcon`, `CreditApprovalIcon`].join(' ')}
                    type="icon-kehubiaoqian"
                  />
                </span>
                <span>
                  <Link href="#CreditApproval" title="授信审批" />
                </span>
              </div>
              <div className={[`${Styles.divwrap}`, `bgColor`, `ContractInfo`].join(' ')}>
                <span className={Styles.iconSty}>
                  <IconFont
                    className={[`bgIcon`, `ContractInfoIcon`].join(' ')}
                    type="icon-hetongxinxi"
                  />
                </span>
                <span>
                  <Link href="#ContractInfo" title="合同信息" />
                </span>
              </div>
              <div className={[`${Styles.divwrap}`, `bgColor`, `CreditRelease`].join(' ')}>
                <span className={Styles.iconSty}>
                  <IconFont
                    className={[`bgIcon`, `CreditReleaseIcon`].join(' ')}
                    type="icon-shouxinfabu"
                  />
                </span>
                <span>
                  <Link href="#CreditRelease" title="授信发布" />
                </span>
              </div>
            </Anchor>
          </Col>

          <Col span={20} className={Styles.mainContainer} key="body">
            <Space direction="vertical" style={{ width: '100%' }} size={64}>
              <BasicInfo />
              <LoginCheck />
              <CreditApply />
              <CustEval />
              <CustEvalOut />
              <BusiData />
              <WittInfo />
              <CreditApproval />
              <ContractInfo />
              <CreditRelease />
            </Space>
          </Col>
        </Row>
      </Card>
      <FooterToolbar>
        <Button
          key="back"
          onClick={() => {
            history.push({ pathname: '/credit/creditApply/list' });
          }}
        >
          返回
        </Button>
        <Button type="primary">提交</Button>
      </FooterToolbar>
    </PageContainer>
  );
};

export default connect(({ creditapply, loading }) => ({
  creditapply,
  loading: loading.effects['creditapply/query'],
}))(CreditUpdate);
