import React, { useState, useEffect, useCallback } from 'react';
import { Modal } from 'antd';
import { connect } from 'umi';
import ProTable from '@ant-design/pro-table';

const ChooseCuetomer = ({
  dispatch,
  commonmodel: { customerlList, cusPagination },
  visible,
  onCancel,
  loadingCus,
  getFormData,
}) => {
  const [filters, setFilters] = useState({});

  const getCusList = useCallback(
    (params = {}) => {
      dispatch({
        type: 'commonmodel/customerlList',
        payload: params,
      });
    },
    [dispatch],
  );

  useEffect(() => {
    getCusList();
  }, [getCusList]);

  const chooseCus = (record) => {
    getFormData(record);
    onCancel(false);
  };

  // 查询客户
  const columnsCus = [
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
      title: '操作',
      dataIndex: 'action',
      key: 'action',
      search: false,
      render: (_, record) => {
        return <a onClick={() => chooseCus(record)}>选择</a>;
      },
    },
  ];

  return (
    <Modal
      visible={visible}
      title="查询客户"
      width={1000}
      onCancel={() => {
        onCancel(false);
      }}
      footer={null}
    >
      <ProTable
        headerTitle=""
        rowKey="custCode"
        loading={loadingCus}
        onSubmit={(values) => {
          getCusList({ ...values });
          setFilters(values);
        }}
        onChange={(page) => {
          getCusList({ ...filters, ...page });
        }}
        search={{
          labelWidth: 70,
        }}
        pagination={cusPagination}
        dataSource={customerlList}
        options={false}
        scroll={{ y: 500 }}
        toolBarRender={false}
        columns={columnsCus}
      />
    </Modal>
  );
};

export default connect(({ commonmodel, loading }) => ({
  commonmodel,
  loadingCus: loading.effects['commonmodel/customerlList'],
}))(ChooseCuetomer);
