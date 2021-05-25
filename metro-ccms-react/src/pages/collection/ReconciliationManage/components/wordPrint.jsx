/* eslint-disable no-param-reassign */
import React, { Fragment } from 'react';
import { Button } from 'antd';
import JsPDF from 'jspdf';
import html2canvas from 'html2canvas';
import { history } from 'umi';
import FooterToolbar from '@/components/FooterToolbar';
// import Logo from '../../../../assets/logo.png';
import Styles from '../index.less';

const WordPrinf = () => {
  // PDF打印
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
      pdf.save('对账单明细.pdf');
    });
  };

  return (
    <Fragment>
      <a id="dlink" style={{ display: 'none' }}></a>
      <div id="wrap" className={Styles.wrap1}>
        <table id="tabled" border="1">
          <thead>
            <tr>
              <td
                colSpan="6"
                style={{
                  textAlign: 'center',
                  fontSize: '20px',
                  fontWeight: 'bold',
                }}
              >
                信 用 购 物 结 帐 确 认 书
              </td>
            </tr>
            <tr>
              <td
                colSpan="6"
                style={{
                  textAlign: 'center',
                  fontSize: '20px',
                  fontWeight: 'bold',
                }}
              >
                Letter of confirmation for credit purchase settlement
              </td>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }}>致/ To company:</td>
              <td style={{ height: '40px', padding: '0px 10px' }} colSpan="4">
                广发银行股份有限公司上海分行工会
              </td>
              <td style={{ height: '40px', padding: '0px 10px' }}>公司</td>
            </tr>
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }}>收件人 / Attention:</td>
              <td style={{ height: '40px', padding: '0px 10px' }} colSpan="5">
                广发银行股份有限公司上海分行工会
              </td>
            </tr>
            {/* <tr className={Styles.tableCon}>
              <td colSpan="6"></td>
            </tr> */}
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }}>发件人:</td>
              <td style={{ height: '40px', padding: '0px 10px' }} colSpan="4">
                麦德龙商业集团有限公司上海浦东分公司
              </td>
              <td style={{ height: '40px', padding: '0px 10px' }}>商场财务</td>
            </tr>
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }}>Form:</td>
              <td style={{ height: '40px', padding: '0px 10px' }} colSpan="5">
                The Main Cashier of METRO China Pudong Store
              </td>
            </tr>
            {/* <tr className={Styles.tableCon}>
              <td colSpan="6"></td>
            </tr> */}
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }}>标题:</td>
              <td style={{ height: '40px', padding: '0px 10px' }} colSpan="5">
                信用购物款结帐
              </td>
            </tr>
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }}>Subject:</td>
              <td style={{ height: '40px', padding: '0px 10px' }} colSpan="5">
                Credit purchase settlement{' '}
              </td>
            </tr>
            <tr>
              <td
                style={{
                  height: '40px',
                  padding: '0px 10px',
                  textAlign: 'center',
                }}
                colSpan="6"
              >
                请 贵 公 司 按 下 列 抬 头 及 帐 号 以 银 行 转 帐 的 方 式 结 清 信 用 购 物 金 额:
                <br />
                Please kindly close off your credit purchasing by bank transfer
                <br />
                within three days according to the following title and bank account of METRO :
              </td>
            </tr>
            <tr>
              <td
                style={{
                  height: '40px',
                  padding: '0px 10px',
                  textAlign: 'right',
                }}
                colSpan="1"
              >
                帐户名(Account name):
              </td>
              <td
                colSpan="5"
                style={{
                  textAlign: 'center',
                  fontWeight: 'bold',
                  height: '40px',
                  padding: '0px 10px',
                }}
              >
                麦德龙商业集团有限公司上海浦东分公司
              </td>
            </tr>
            <tr>
              <td
                style={{
                  height: '40px',
                  padding: '0px 10px',
                  textAlign: 'right',
                }}
                colSpan="1"
              >
                银行名(Bank Name):
              </td>
              <td colSpan="5" style={{ textAlign: 'center', fontWeight: 'bold' }}>
                中国银行上海市浦东分行
              </td>
            </tr>
            <tr>
              <td
                style={{
                  height: '40px',
                  padding: '0px 10px',
                  textAlign: 'right',
                }}
                colSpan="1"
              >
                银行帐号(Bank account No.):
              </td>
              <td
                colSpan="5"
                style={{
                  textAlign: 'center',
                  height: '40px',
                  fontWeight: 'bold',
                }}
              >
                442959215805
              </td>
            </tr>
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }}>截止日期（Run Date）:</td>
              <td
                style={{
                  height: '40px',
                  textAlign: 'center',
                  padding: '0px 10px',
                }}
                colSpan="5"
              >
                2021/1/21
              </td>
            </tr>
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }}>应收合计（Total AR AMT）:</td>
              <td
                style={{
                  height: '40px',
                  textAlign: 'center',
                  padding: '0px 10px',
                }}
                colSpan="5"
              >
                875,316.10-
              </td>
            </tr>
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }} colSpan="1">
                商场财务签字（Signed by the Main Cashier）:
              </td>
              <td
                style={{ height: '40px', padding: '0px 10px', borderBottom: '1px solid #000' }}
                colSpan="5"
              ></td>
            </tr>
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }} colSpan="1">
                日 期（Date）:
              </td>
              <td
                style={{ height: '40px', padding: '0px 10px', borderBottom: '1px solid #000' }}
                colSpan="5"
              ></td>
            </tr>
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }} colSpan="1">
                顾客确认（Confirmed by Customer）:
              </td>
              <td
                style={{ height: '40px', padding: '0px 10px', borderBottom: '1px solid #000' }}
                colSpan="5"
              ></td>
            </tr>
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }} colSpan="1">
                日 期（Date）:
              </td>
              <td
                style={{ height: '40px', padding: '0px 10px', borderBottom: '1px solid #000' }}
                colSpan="5"
              ></td>
            </tr>
          </tbody>
        </table>
        <table style={{ marginTop: '20px' }} id="tabled1" border="1">
          <thead>
            <tr>
              <td
                colSpan="10"
                style={{
                  textAlign: 'center',
                  fontSize: '20px',
                  fontWeight: 'bold',
                }}
              >
                <u>RECORDS OF THE CREDIT PURCHASING</u> <br />信 用 购 物 记 录
              </td>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td style={{ height: '40px', padding: '0px 10px' }}>
                月份
                <br />
                (MONTH):
              </td>
              <td style={{ height: '40px', padding: '0px 10px' }}>01</td>
              <td style={{ height: '40px', padding: '0px 10px' }}>
                年度
                <br />
                (YEAR):
              </td>
              <td style={{ height: '40px', padding: '0px 10px' }}>2021</td>
              <td style={{ height: '40px', padding: '0px 10px' }}>
                卡编码
                <br />
                (customer No):
              </td>
              <td style={{ height: '40px', padding: '0px 10px' }}>'1017F15049</td>
              <td style={{ height: '40px', padding: '0px 10px' }}>
                公司名称
                <br />
                (COMPANY):
              </td>
              <td style={{ height: '40px', padding: '0px 10px' }}>
                广发银行股份有限公司上海分行工会
              </td>
              <td style={{ height: '40px', padding: '0px 10px' }}>
                购物限额为
                <br />
                (LIMITATION):
              </td>
              <td style={{ height: '40px', padding: '0px 10px' }}>200000</td>
            </tr>
            <tr style={{ textAlign: 'center' }}>
              <td>
                Data
                <br />日 期
              </td>
              <td colSpan="4">
                VAT LIST / INVOICE NO.
                <br />购 物 清 单 / 发 票 号
              </td>
              <td>
                AMOUNT
                <br />
                采购金额
              </td>
              <td colSpan="4">
                COMMENTS
                <br />
                备注
              </td>
            </tr>
            <tr style={{ textAlign: 'center' }}>
              <td>2021/1/14</td>
              <td colSpan="4">广发银行股份有限公司上海分行工会调整SAP号</td>
              <td>1000</td>
              <td colSpan="4"></td>
            </tr>
            <tr style={{ textAlign: 'center' }}>
              <td>合计（Total）</td>
              <td colSpan="4"></td>
              <td></td>
              <td colSpan="4"></td>
            </tr>
          </tbody>
        </table>
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
        <Button
          onClick={() => {
            method5(['tabled', 'tabled1'], '对账单信息.xls');
          }}
          type="primary"
        >
          导出Excel
        </Button>
      </FooterToolbar>
    </Fragment>
  );
};

export default WordPrinf;
