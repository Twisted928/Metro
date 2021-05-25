import React, { useState, useEffect, useRef } from 'react';
import { Button } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import moment from 'moment';
import ProTable from '@ant-design/pro-table';
import ChooseModal from './components/chooseCusModal';

const overMonth = moment()
  .month(moment().month() - 1)
  .startOf('month');
const lastMonth = moment()
  .month(moment().month() - 1)
  .endOf('month');

const QuotaTransfer = () => {
  const ref = useRef();
  const [modalVis, setModalVis] = useState(false);

  useEffect(() => {
    ref.current.setFieldsValue({ invoicedate: [overMonth, lastMonth] });
  }, []);

  const toolBarRender = () => [
    <Button key="export">导出</Button>,
    <Button key="export">模板下载</Button>,
    <Button
      key="export"
      type="primary"
      onClick={() => {
        setModalVis(true);
      }}
    >
      新增
    </Button>,
  ];

  // const deleteMsg = () => {};

  // const detailPage = () => {
  //   history.push({
  //     pathname: '/safeguard/Lossmanagement/updataFrom',
  //     query: {},
  //   });
  // };

  // const uploadMsg = () => {
  //   history.push({
  //     pathname: '/credit/creditReview/updataFrom',
  //     query: {},
  //   });
  // };

  const columns = [
    {
      title: '申请单号',
      dataIndex: 'applicationNo',
      key: 'applicationNo',
    },
    {
      title: '老门店编码',
      dataIndex: 'storeCodeOld',
      key: 'storeCodeOld',
    },
    {
      title: '额度转移说明',
      dataIndex: 'reason',
      key: 'reason',
      search: false,
      width: 250,
    },
    {
      title: '创建人',
      dataIndex: 'createdBy',
      key: 'createdBy',
      search: false,
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      key: 'createTime',
      search: false,
    },
    // {
    //   title: '操作',
    //   dataIndex: 'action',
    //   key: 'action',
    //   search: false,
    //   render: (_, record) => {
    //     return (
    //       <Fragment>
    //         <a onClick={() => uploadMsg()}>修改</a>
    //         <Divider type="vertical" />
    //         <a onClick={() => deleteMsg(record)} target="_blank">
    //           删除
    //         </a>
    //       </Fragment>
    //     );
    //   },
    // },
  ];

  const list = [
    {
      applicationNo: '123455',
      ctype: '1',
    },
  ];

  const chooseModal = {
    vis: modalVis,
    onCancel: (data) => {
      setModalVis(data);
    },
  };

  return (
    <PageContainer ghost title={false}>
      <ProTable
        headerTitle="查询列表"
        rowKey="id"
        dataSource={list}
        tableAlertRender={false}
        formRef={ref}
        options={false}
        toolBarRender={toolBarRender}
        columns={columns}
      />
      <ChooseModal {...chooseModal} />
    </PageContainer>
  );
};

export default QuotaTransfer;
