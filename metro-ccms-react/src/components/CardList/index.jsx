import React, { useState, useEffect, useCallback } from 'react';
import { Modal } from 'antd';
import { connect } from 'umi';
import ProTable from '@ant-design/pro-table';

const ChooseCardList = ({
  dispatch,
  commonmodel: { memberlList, memPagination },
  visible,
  onCancel,
  params,
  loadingMem,
  getCardList,
}) => {
  const [filters, setFilters] = useState(null);
  const [rowList, setRowList] = useState([]);

  const getMemList = useCallback(
    (param = { ...params }) => {
      if (!params.custCode) {
        return;
      }
      dispatch({
        type: 'commonmodel/memberlList',
        payload: param,
      });
    },
    [dispatch, params],
  );

  useEffect(() => {
    getMemList();
  }, [getMemList]);

  const chooseCus = () => {
    getCardList(rowList);
    onCancel(false);
  };

  // 查询卡片
  const columnsCus = [
    {
      title: '门店编码',
      dataIndex: 'storeCode',
      key: 'storeCode',
      search: false,
    },
    {
      title: '客户编码',
      dataIndex: 'custCode',
      key: 'custCode',
      search: false,
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      key: 'custName',
      search: false,
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
          getMemList({ ...params, ...values });
          setFilters(values);
        }}
        onChange={(page) => {
          getMemList({ ...filters, ...page });
        }}
        search={{
          labelWidth: 70,
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

export default connect(({ commonmodel, loading }) => ({
  commonmodel,
  loadingMem: loading.effects['commonmodel/memberlList'],
}))(ChooseCardList);
