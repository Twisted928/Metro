import React, { useState, useEffect, useCallback } from 'react';
import { Modal } from 'antd';
import { connect } from 'umi';
import ProTable from '@ant-design/pro-table';

const ChooseCuetomer = ({
  dispatch,
  commonmodel: { companyList, companyPagination },
  visible,
  onCancel,
  loadingCom,
  getFormData,
}) => {
  const [filters, setFilters] = useState({});

  const getCusList = useCallback(
    (params = {}) => {
      dispatch({
        type: 'commonmodel/companyList',
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
      title: '公司编码',
      dataIndex: 'compCode',
      key: 'compCode',
    },
    {
      title: '公司名称',
      dataIndex: 'compName',
      key: 'compName',
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
      title="查询公司"
      width={1000}
      onCancel={() => {
        onCancel(false);
      }}
      footer={null}
    >
      <ProTable
        headerTitle=""
        rowKey="compCode"
        loading={loadingCom}
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
        pagination={companyPagination}
        dataSource={companyList}
        options={false}
        toolBarRender={false}
        columns={columnsCus}
      />
    </Modal>
  );
};

export default connect(({ commonmodel, loading }) => ({
  commonmodel,
  loadingCom: loading.effects['commonmodel/companyList'],
}))(ChooseCuetomer);
