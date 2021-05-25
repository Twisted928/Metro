import React from 'react';
import { Modal } from 'antd';
import ProTable from '@ant-design/pro-table';
import { history } from 'umi';

const ChooseCusModal = ({ vis, onCancel }) => {
  const handleOk = async () => {};

  const handleCancel = () => {
    onCancel(false);
  };

  const chooseCus = () => {
    history.push({
      pathname: '/credit/QuotaTransfer/createForm',
      query: {},
    });
  };

  const columns = [
    {
      title: '客户编码',
      dataIndex: 'custCode',
      key: 'custCode',
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      key: 'custName',
    },
    {
      title: '原门店',
      dataIndex: 'storeNameOld',
      key: 'storeNameOld',
      search: false,
    },
    {
      title: '新门店',
      dataIndex: 'storeNameN',
      key: 'storeCode',
      search: false,
    },
    {
      title: '原卡号',
      dataIndex: 'cardCodeOld',
      key: 'storeCode',
      search: false,
    },
    {
      title: '新卡号',
      dataIndex: 'cardCodeN',
      key: 'storeCode',
      search: false,
    },
    {
      title: '操作',
      dataIndex: 'action',
      key: 'action',
      search: false,
      render: (_, record) => {
        return (
          <a
            onClick={() => {
              chooseCus(record);
            }}
          >
            选择
          </a>
        );
      },
    },
  ];

  const List = [
    {
      caseno: 'DT1233445',
      caseProgress: '无进展',
      caseStatus: '无状态',
      createdBy: '高贻飞',
      createTime: '2020-11-30',
    },
  ];

  return (
    <Modal title="选择客户" width={1000} visible={vis} onOk={handleOk} onCancel={handleCancel}>
      <ProTable
        headerTitle="查询列表"
        rowKey="caseno"
        dataSource={List}
        tableAlertRender={false}
        // rowSelection={rowSelection}
        options={false}
        toolBarRender={false}
        columns={columns}
      />
    </Modal>
  );
};

export default ChooseCusModal;
