import React, { useState, useEffect, useRef } from 'react';
import { Button } from 'antd';
import { connect } from 'umi';
import ProTable from '@ant-design/pro-table';
import { PageContainer } from '@ant-design/pro-layout';
import { download } from '@/services/commom';
import ShowHistory from './components/ShowHistory';

// 睡眠客户管理
const SleepCust = ({ dispatch, sleepcust: { list, filter, pagination }, loading }) => {
  const formRef = useRef();
  const actionRef = useRef();
  const [visible, setVisible] = useState(false);
  const [thisPayload, setPayload] = useState([]);

  const query = (param = {}) => {
    dispatch({
      type: 'sleepcust/query',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const columns = [
    {
      title: '客户编码',
      dataIndex: 'custcode',
      // search: false,
    },
    {
      title: '客户名称',
      dataIndex: 'custname',
      // search: false,
    },
    {
      title: '睡眠开始时间',
      dataIndex: 'validfrom',
      valueType: 'date',
      search: false,
    },
    {
      title: '睡眠开始时间',
      dataIndex: 'validstrRange',
      valueType: 'dateRange',
      hideInTable: true,
      // search: false,
    },
    {
      title: '再次交易时间',
      dataIndex: 'validto',
      valueType: 'date',
      search: false,
    },
    {
      title: '再次交易时间',
      dataIndex: 'validendRange',
      valueType: 'dateRange',
      hideInTable: true,
      // search: false,
    },
    {
      title: '状态',
      dataIndex: 'status',
      valueType: 'select',
      search: false,
      valueEnum: {
        0: {
          text: '无效',
          status: 'error',
        },
        1: {
          text: '有效',
          status: 'success',
        },
      },
    },
    {
      title: '原因',
      dataIndex: 'reason',
      search: false,
    },
    {
      title: '创建时间',
      dataIndex: 'createtime',
      valueType: 'date',
      search: false,
    },
    {
      title: '操作',
      valueType: 'option',
      fixed: 'right',
      width: 140,
      render: (_, record) => [
        <a
          key="history"
          onClick={() => {
            setPayload({ custcode: record.custcode });
            setVisible(true);
          }}
        >
          历史记录
        </a>,
      ],
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/customer/sleepcustomer/export', filter);
      }}
    >
      导出
    </Button>,
  ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="id"
        headerTitle="查询列表"
        dateFormatter="string"
        options={false}
        actionRef={actionRef}
        formRef={formRef}
        columns={columns}
        dataSource={list}
        pagination={pagination}
        loading={loading}
        onSubmit={(value) => {
          query({
            ...value,
            validfromstr: value?.validstrRange?.[0],
            validfromend: value?.validstrRange?.[1],
            validtostr: value?.validendRange?.[0],
            validtoend: value?.validendRange?.[1],
          });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
        toolBarRender={toolBarRender}
        search={{ span: 8, labelWidth: 100 }}
      />

      <ShowHistory
        destroyOnClose
        visible={visible}
        payload={thisPayload}
        onClose={() => {
          setVisible(false);
          setPayload([]);
        }}
      />
    </PageContainer>
  );
};

export default connect(({ sleepcust, loading }) => ({
  sleepcust,
  loading: loading.effects['sleepcust/query'],
}))(SleepCust);
