import React, { Fragment, useEffect, useState } from 'react';
import { Descriptions, Button } from 'antd';
import ProTable from '@ant-design/pro-table';
import JsPDF from 'jspdf';
import numeral from 'numeral';
import html2canvas from 'html2canvas';
import { history } from 'umi';
import moment from 'moment';
import FooterToolbar from '@/components/FooterToolbar';
import Logo from '../../../../assets/logo.png';
import { selPDFinfo } from '../service';
import Styles from '../index.less';

const datae = '2020年10月23日';
const money = '1200000';
const moneyBig = '壹佰贰拾万';

const dateformat = 'YYYY-MM-DD HH:mm:ss';

const WordPrinf = () => {
  const [basicData, setBasicData] = useState({});
  const [firstDate, setBasicfirstdate] = useState('');
  const [secondDate, setBasicsecondtdate] = useState('');
  const [basicList, setBasicList] = useState([]);

  const query = async () => {
    const { id, applicationNo } = history.location.query;
    if (!id) {
      return;
    }
    const CollectionVO = {
      id,
      applicationNo,
    };
    const response = await selPDFinfo(CollectionVO);
    const { msg, data, code } = response;
    if (code === 200) {
      const { collectionVO, fdate, collectionDetailDO } = data;
      if (fdate.length) {
        setBasicfirstdate(fdate[0].ddate);
        setBasicsecondtdate(fdate[1].ddate);
      }
      setBasicData(collectionVO);
      setBasicList(collectionDetailDO);
    }
  };

  useEffect(() => {
    query();
  }, []);

  const columns = [
    {
      title: '账期内应收(万元)',
      dataIndex: 'undue',
      align: 'center',
      key: 'undue',
      search: false,
      render: (_, record) => numeral(record.undue).format('0,0.00'),
    },
    {
      title: '逾期1-15天',
      dataIndex: 'idue015',
      align: 'center',
      key: 'idue015',
      search: false,
      render: (_, record) => numeral(record.idue015).format('0,0.00'),
    },
    {
      title: '逾期16-30天',
      dataIndex: 'idue030',
      align: 'center',
      key: 'idue030',
      search: false,
      render: (_, record) => numeral(record.idue030).format('0,0.00'),
    },
    {
      title: '逾期31-60天',
      align: 'center',
      dataIndex: 'idue060',
      key: 'idue060',
      search: false,
      render: (_, record) => numeral(record.idue060).format('0,0.00'),
    },
    {
      title: '逾期61-90天',
      align: 'center',
      dataIndex: 'idue090',
      key: 'idue090',
      search: false,
      render: (_, record) => numeral(record.idue090).format('0,0.00'),
    },
    {
      title: '逾期91-365天',
      align: 'center',
      dataIndex: 'idueheji365',
      key: 'idueheji365',
      search: false,
      render: (_, record) => numeral(record.idueheji365).format('0,0.00'),
    },
    {
      title: '逾期一年',
      align: 'center',
      dataIndex: 'idue360',
      key: 'idue360',
      search: false,
      render: (_, record) => numeral(record.idue360).format('0,0.00'),
    },
    {
      title: '合计',
      align: 'center',
      dataIndex: 'heji',
      key: 'heji',
      search: false,
      render: (_, record) => numeral(record.heji).format('0,0.00'),
    },
  ];

  const columns1 = [
    {
      title: () => {
        return (
          <Fragment>
            <div>发票号</div>
            <div>（Invoice Number）</div>
          </Fragment>
        );
      },
      dataIndex: 'csbvcode',
      align: 'center',
      key: 'csbvcode',
      search: false,
    },
    {
      title: () => {
        return (
          <Fragment>
            <div>发票日期</div>
            <div>（Date）</div>
          </Fragment>
        );
      },
      dataIndex: 'dsbvdate',
      align: 'center',
      key: 'dsbvdate',
      search: false,
      render: (_, record) => {
        return record.dsbvdate ? moment(record.dsbvdate).format(dateformat) : '';
      },
    },
    {
      title: () => {
        return (
          <Fragment>
            <div>付款条件</div>
            <div>（Payment Term）</div>
          </Fragment>
        );
      },
      dataIndex: 'paymentTerm',
      align: 'center',
      key: 'paymentTerm',
      search: false,
    },
    // {
    //   title: () => {
    //     return (
    //       <Fragment>
    //         <div>到期日</div>
    //         <div>（Due Date）</div>
    //       </Fragment>
    //     );
    //   },
    //   align: 'center',
    //   dataIndex: 'idue060',
    //   key: 'idue060',
    //   search: false,
    // },
    {
      title: () => {
        return (
          <Fragment>
            <div>币种</div>
            <div>（Currency）</div>
          </Fragment>
        );
      },
      align: 'center',
      dataIndex: 'standardCurrency',
      key: 'standardCurrency',
      search: false,
    },
    {
      title: () => {
        return (
          <Fragment>
            <div>应付账款</div>
            <div>（Amount）</div>
          </Fragment>
        );
      },
      align: 'center',
      dataIndex: 'amount',
      key: 'amount',
      search: false,
      render: (_, record) => numeral(record.amount).format('0,0.00'),
    },
    // {
    //   title: () => {
    //     return (
    //       <Fragment>
    //         <div>门店</div>
    //         <div>（Store No）</div>
    //       </Fragment>
    //     );
    //   },
    //   align: 'center',
    //   dataIndex: 'idue360',
    //   key: 'idue360',
    //   search: false,
    // },
  ];

  // 打印
  const doPrint = () => {
    window.scroll(0, 0);
    const element = document.getElementById('wrap');
    html2canvas(element, {
      allowTaint: true,
      scale: 2,
    }).then((canvas) => {
      const contentWidth = canvas.width;
      const contentHeight = canvas.height;
      // 一页pdf显示html页面生成的canvas高度;
      const pageHeight = (contentWidth / 592.28) * 841.89;
      // 未生成pdf的html页面高度
      let leftHeight = contentHeight;
      // 页面偏移
      let position = 0;
      // a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
      const imgWidth = 595.28;
      const imgHeight = (592.28 / contentWidth) * contentHeight;
      const pageData = canvas.toDataURL('image/jpeg', 1.0);
      const pdf = new JsPDF('', 'pt', 'a4');
      if (leftHeight < pageHeight) {
        pdf.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight);
      } else {
        while (leftHeight > 0) {
          pdf.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight);
          leftHeight -= pageHeight;
          position -= 841.89;
          // 避免添加空白页
          if (leftHeight > 0) {
            pdf.addPage();
          }
        }
      }
      pdf.save('催收单明细.pdf');
    });
  };

  return (
    <Fragment>
      <div id="wrap" className={Styles.wrap}>
        <div className={Styles.Logo}>
          <img src={Logo} alt="" />
        </div>
        <div className={Styles.cusDesc}>
          <Descriptions column={2} bordered size="middle">
            <Descriptions.Item label="卡号编码（Number of Card）">
              {basicData.cardCode}
            </Descriptions.Item>
            <Descriptions.Item label="商场名称（Store Name）">
              {basicData.deptName}
            </Descriptions.Item>
            <Descriptions.Item label="客户名称（Customer Name）">
              {basicData.custName}
            </Descriptions.Item>
            <Descriptions.Item label="商场地址（Store Address）">
              {basicData.address}
            </Descriptions.Item>
            <Descriptions.Item label="客户地址（Customer Address）">
              {basicData.cusaddress}
            </Descriptions.Item>
            <Descriptions.Item label="催款日期（Dunning Date）">
              {basicData.ddate ? moment(basicData.ddate).format(dateformat) : ''}
            </Descriptions.Item>
            <Descriptions.Item label="联系人（Contract person）">
              {basicData.nickName}
            </Descriptions.Item>
            <Descriptions.Item label="电话（Telephone number）">
              {basicData.phonenumber}
            </Descriptions.Item>
            <Descriptions.Item label="传真+分机号（Fax）">{basicData.cardCode}</Descriptions.Item>
            <Descriptions.Item label="电子邮箱（Email）">{basicData.email}</Descriptions.Item>
          </Descriptions>
        </div>
        {basicData.iflag === '逾期15天' ? (
          <div>
            <div className={Styles.dunningLetter}>
              <p style={{ textAlign: 'center' }}>催款单（逾期15天）</p>
              <div>
                贵公司至{datae}尚欠我公司贷款，计人民币{money}元（大写{moneyBig}
                ）。其中根据贵方我方所签署的《信用顾客银行转账购物保证协议》的规定，贵公司已逾期未付的贷款，计人民币
                {money}元（大写{moneyBig}），
                请贵公司在收到此函后立即付清上述逾期未付贷款至我商场下列账户
                <div>户名：麦德龙商业集团有限公司上海浦东分公司</div>
                <div>开户行：中国银行上海市浦东分行</div>
                <div>账号：442959215805</div>
                感谢贵公司一直以来与我商场的合作并希望贵公司及早处理上述事宜。如贵公司有任何的疑问，请在收到本函之日三个工作日内向我商场提出。
              </div>
            </div>
            <div className={Styles.dunningLetter}>
              <p style={{ textAlign: 'center' }}>Dunning Letter(Overdue 15 days)</p>
              <div>
                This is a reminder that the balance due on your account is RMB {money} on {datae}.
                According to the ＜Guarantee Agreement on Sales upon Bank Transfer Credit Customer＞
                signed between us, the delinquent amount is RMB {money}
                please promptly pay off the abobe delinquent amount into our following bank account
                upon receipt of this reminder.
                <div>Account Name: 麦德龙商业集团有限公司上海浦东分公司</div>
                <div>Bank Name: 中国银行上海市浦东分行</div>
                <div>Bank Account: 442959215805</div>
                We thank you for you cooperation with our store and hope this reminder may have you
                early attention. if you have any question, please let us know within 3 working days
                upon receipt of thid reminder.
              </div>
            </div>
          </div>
        ) : (
          ''
        )}
        {basicData.iflag === '逾期30天' ? (
          <div>
            <div className={Styles.dunningLetter}>
              <p style={{ textAlign: 'center' }}>催款单（逾期30天）</p>
              <div>
                我商场于{datae}发催收函之后未收到贵公司逾期货款，我商场再次发函提醒贵公司至{datae}
                尚欠我商场货款，计人民币{money}元（大写{moneyBig}）。
                其中根据贵方我方所签署的《信用顾客银行转账购物保证协议》的规定，贵公司已逾期未付的贷款，计人民币
                {money}元（大写{moneyBig}），
                请贵公司在收到此函后立即付清上述逾期未付贷款至我商场下列账户
                <div>户名：麦德龙商业集团有限公司上海浦东分公司</div>
                <div>开户行：中国银行上海市浦东分行</div>
                <div>账号：442959215805</div>
                此外，本公司将依据所签署的《信用顾客银行转账购物保证协议》的约定，采取下列行动（□ 1
                / □ 2）
                <p>
                  1. 自{datae}
                  起终止贵公司在本公司的信用购物行为，并循法律途径要求贵公司支付该货款及有权要求逾期付款利息（自逾期之日起按中国人民银行规定的的逾期贷款利率计算）
                </p>
                <p>
                  2.
                  暂不终止与贵公司在本公司的信用购物行为，且不循法律途径要求贵公司支付该货款及有权要求逾期付款利息（自逾期之日起按中国人民银行规定的逾期贷款利率计算）
                </p>
              </div>
            </div>
            <div className={Styles.dunningLetter}>
              <p style={{ textAlign: 'center' }}>Dunning Letter(Overdue 30 days)</p>
              <div>
                No having received your overdue payment after our dunning letter if {datae},we are
                writing again to remind you that the balance due on you account is RMB {money}.
                <p>
                  According to the 《Guarantee Agreement on Sales upon Bank Transfer for Credit
                  Customers》signed between us,the delinquent amount is RMB {money}.
                </p>
                <p>
                  Please immediately pay off the above delinquent amount into our following bank
                  account upon receipt of this rerminder.
                </p>
                <div>Account Name: 麦德龙商业集团有限公司上海浦东分公司</div>
                <div>Bank Name: 中国银行上海市浦东分行</div>
                <div>Banl Account: 442959215805</div>
                <div>Moreover, we take the following （□ 1 / □ 2）</div>
                <div>
                  1. We will suspend your credit purchasing right in our store from {datae} in
                  accordance with the provisions of the Agreement, and to seek legal redress for the
                  overdue payment and the interest on the overdue payment amount(calculating since
                  the date of the overdue payment at the interest rate on overdue loan stipulated by
                  the People’s Bank of China).
                </div>
                <div>2. We won’t suspend you credit purchasing right in our store.</div>
              </div>
            </div>
          </div>
        ) : (
          ''
        )}
        {basicData.iflag === '逾期60天' ? (
          <div>
            <div className={Styles.dunningLetter}>
              <p style={{ textAlign: 'center' }}>催款单（逾期60天）</p>
              <div>
                我商场在{moment(firstDate).format('LL')}和{moment(secondDate).format('LL')}
                两次催收函之后未收到贵公司逾期货款，贵公司至{datae}
                欠我商场货款，计人民币{money}元（大写{moneyBig}）。
                请贵公司在收到此函后立即付清上述逾期未付贷款至我商场下列账户
                <div>户名：麦德龙商业集团有限公司上海浦东分公司</div>
                <div>开户行：中国银行上海市浦东分行</div>
                <div>账号：442959215805</div>
                <div>
                  此外，本公司将依据所签署的《信用顾客银行转账购物保证协议》的约定自{datae}
                  起终止贵公司在本公司的信用购物行为，
                  并循法律途径要求贵公司支付该贷款及有权要求逾期付款利息（自逾期之日起按中国人民银行规定的逾期贷款利率计算）。
                </div>
              </div>
            </div>
            <div className={Styles.dunningLetter}>
              <p style={{ textAlign: 'center' }}>Dunning Letter(Overdue 60 days)</p>
              <div>
                Not having received your any overdue payment after our dunning letter of{' '}
                {moment(firstDate).format('LL')} and
                {moment(secondDate).format('LL')}. The sum you are still owing is RMB {money}.
                <p>
                  Please immediately pay off the above delinquent amount into our following bank
                  account upon receipt of this rerminder.
                </p>
                <div>Account Name: 麦德龙商业集团有限公司上海浦东分公司</div>
                <div>Bank Name: 中国银行上海市浦东分行</div>
                <div>Banl Account: 442959215805</div>
                <div>
                  Moreover，we will terminate you credit purchasing right in our store from {datae}{' '}
                  in accordance with provisions of the Agreement, and to seek legal redress for the
                  overdue payment and the interest on the overdue payment amount (calculating since
                  the date of the overdue payment at the interest rate on overdue loan stipulated by
                  the People’s Bank of China).
                </div>
              </div>
            </div>
          </div>
        ) : (
          ''
        )}
        <div style={{ marginTop: '60px' }}>
          <ProTable
            headerTitle="发票明细（Invoice details）"
            rowKey="id"
            dataSource={[basicData]}
            search={false}
            options={false}
            bordered
            pagination={false}
            columns={columns}
          />
        </div>
        <div style={{ padding: '0px 24px', marginTop: '24px' }}>
          <ProTable
            headerTitle=""
            rowKey="id"
            dataSource={basicList}
            search={false}
            options={false}
            pagination={false}
            bordered
            columns={columns1}
          />
        </div>
        <div className={Styles.thanks}>
          <p>特此通知。</p>
          <p style={{ marginLeft: '15px' }}>祝商祺！</p>
          <p style={{ marginLeft: '15px' }}>Your faithfully</p>
          {/* <p>祝商祺！</p>
          <p>Your faithfully</p> */}
          <p>商场总经理 （Store General Manager）：＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿</p>
          <p>日期 （Date）：＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿＿</p>
        </div>
      </div>
      <FooterToolbar>
        <Button
          key="back"
          onClick={() => {
            history.goBack();
          }}
        >
          返回
        </Button>
        <Button onClick={doPrint} type="primary">
          打印PDF
        </Button>
      </FooterToolbar>
    </Fragment>
  );
};

export default WordPrinf;
