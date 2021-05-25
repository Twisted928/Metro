import { Button } from 'antd';
import React, { useEffect, useRef, useCallback } from 'react';
import { connect, history } from 'umi';
import numeral from 'numeral';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';

const CustGroup = ({ dispatch, loading, custgroup: { list, pagination, filter } }) => {
  const actionRef = useRef();
  const formRef = useRef();

  const query = useCallback(
    (param = {}) => {
      dispatch({
        type: 'custgroup/query',
        payload: param,
      });
    },
    [dispatch],
  );

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, [filter, query]);

  const checkDetail = (res) => {
    history.push({
      pathname: '/credit/custGroup/showdetails',
      query: {
        custGroup: res.custGroup,
      },
    });
  };

  const columns = [
    {
      title: '客户组编码',
      dataIndex: 'custGroup',
      width: 150,
    },
    {
      title: '客户组名称',
      dataIndex: 'custgrName',
      width: 150,
    },
    {
      title: '客户组账期（天）',
      dataIndex: 'custgrPayterm',
      search: false,
      align: 'right',
      width: 150,
    },
    {
      title: '客户组额度（万元）',
      dataIndex: 'custgrLimit',
      search: false,
      width: 180,
      align: 'right',
      render: (_, record) => numeral(record.custgrLimit).format('0,0.00'),
    },
    {
      title: '总收入（万元）',
      dataIndex: 'totalRevenue',
      width: 150,
      search: false,
      align: 'right',
      render: (_, record) => numeral(record.totalRevenue).format('0,0.00'),
    },
    {
      title: '应收金额（万元）',
      width: 150,
      dataIndex: 'iar',
      search: false,
      align: 'right',
      render: (_, record) => numeral(record.iar).format('0,0.00'),
    },
    {
      title: '逾期金额（万元）',
      width: 150,
      dataIndex: 'idue',
      search: false,
      align: 'right',
      render: (_, record) => numeral(record.idue).format('0,0.00'),
    },
    {
      title: '卡号数量',
      dataIndex: 'cardNum',
      width: 150,
      search: false,
      align: 'right',
    },
    {
      title: '门店数量',
      width: 150,
      dataIndex: 'storeNum',
      search: false,
      align: 'right',
    },
    {
      title: '操作',
      width: 150,
      valueType: 'option',
      fixed: 'right',
      render: (text, record) => [
        <a key="detail" onClick={() => checkDetail(record)}>
          详情
        </a>,
      ],
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/Customergroup/management/export', filter);
      }}
    >
      导出
    </Button>,
  ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="id"
        options={false}
        headerTitle="查询列表"
        dateFormatter="string"
        actionRef={actionRef}
        formRef={formRef}
        loading={loading}
        dataSource={list}
        pagination={pagination}
        onSubmit={(value) => {
          query({ ...value });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
        toolBarRender={toolBarRender}
        columns={columns}
        search={{ span: 8, labelWidth: 'auto' }}
        scroll={{ x: 1550 }}
      />
    </PageContainer>
  );
};

export default connect(({ custgroup, loading }) => ({
  custgroup,
  loading: loading.effects['custgroup/query'],
}))(CustGroup);
