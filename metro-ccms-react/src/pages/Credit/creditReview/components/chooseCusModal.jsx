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
      pathname: '/credit/creditReview/createForm',
      query: {},
    });
  };

  const columns = [
    {
      title: '卡号编码',
      dataIndex: 'cardCode',
      key: 'cardCode',
    },
    {
      title: '卡号名称',
      dataIndex: 'cardName',
      key: 'cardName',
      search: false,
    },
    {
      title: '门店编码',
      dataIndex: 'storeCode',
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
