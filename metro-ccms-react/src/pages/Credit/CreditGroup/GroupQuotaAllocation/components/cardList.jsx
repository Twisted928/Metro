import React, { useState } from 'react';
import { Modal } from 'antd';
import ProTable from '@ant-design/pro-table';

const ChooseCardList = ({
  visible,
  onCancel,
  memberlList,
  memPagination,
  onload,
  loadingMem,
  getCardList,
}) => {
  const [filters, setFilters] = useState({});
  const [rowList, setRowList] = useState([]);

  const chooseCus = () => {
    getCardList(rowList);
    onCancel(false);
  };

  // 查询客户
  const columnsCus = [
    {
      title: '门店编码',
      dataIndex: 'storeCode',
      key: 'storeCode',
    },
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
      title: '卡号编码',
      dataIndex: 'cardCode',
      key: 'cardCode',
    },
    {
      title: '卡号名称',
      dataIndex: 'cardName',
      key: 'cardName',
    },
    // {
    //   title: '操作',
    //   dataIndex: 'action',
    //   key: 'action',
    //   search: false,
    //   render: (_, record) => {
    //     return <a onClick={() => chooseCus(record)}>选择</a>;
    //   },
    // },
  ];

  const rowSelection = {
    onChange: (_, rows) => {
      setRowList(rows);
    },
  };

  return (
    <Modal
      visible={visible}
      destroyOnClose
      title="卡片信息"
      width={1000}
      onOk={() => {
        chooseCus();
      }}
      onCancel={() => {
        onCancel(false);
      }}
    >
      <ProTable
        headerTitle=""
        rowKey="custCode"
        loading={loadingMem}
        onSubmit={(values) => {
          onload({ ...values });
          setFilters(values);
        }}
        onChange={(page) => {
          onload({ ...filters, ...page });
        }}
        tableAlertRender={false}
        rowSelection={rowSelection}
        pagination={memPagination}
        dataSource={memberlList}
        options={false}
        toolBarRender={false}
        columns={columnsCus}
      />
    </Modal>
  );
};

export default ChooseCardList;
