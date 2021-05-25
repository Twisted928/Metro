import React, { useState, useEffect, useRef } from 'react';
import { Button } from 'antd';
import { connect } from 'umi';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';
import { PageContainer } from '@ant-design/pro-layout';
import ShowHistory from './components/ShowHistory';
import { query } from './service';

// 黑名单管理
const BlackList = ({ dispatch, blacklist: { filter } }) => {
  const formRef = useRef();
  const actionRef = useRef();
  const [searchParams, setSearchParams] = useState({});
  const [visible, setVisible] = useState(false);
  const [thisPayload, setPayload] = useState();

  const updateFilter = (param = {}) => {
    dispatch({
      type: 'blacklist/updateFilter',
      payload: param,
    });
  };

  useEffect(() => {
    setSearchParams(filter);
    formRef.current.setFieldsValue({
      ...searchParams,
    });
  }, [filter]);

  const columns = [
    {
      title: '客户编码',
      dataIndex: 'custcode',
      width: 150,
      // search: false,
    },
    {
      title: '客户名称',
      dataIndex: 'custname',
      width: 200,
      ellipsis: true,
      // search: false,
    },
    {
      title: '原因',
      width: 200,
      ellipsis: true,
      dataIndex: 'reason',
      search: false,
    },
    {
      title: '生效日',
      dataIndex: 'validstrRange',
      valueType: 'dateRange',
      hideInTable: true,
    },
    {
      title: '到期日',
      dataIndex: 'validendRange',
      valueType: 'dateRange',
      hideInTable: true,
    },
    {
      title: '生效日',
      dataIndex: 'validfrom',
      search: false,
      valueType: 'date',
    },
    {
      title: '到期日',
      dataIndex: 'validto',
      search: false,
      valueType: 'date',
    },
    {
      title: '创建人',
      search: false,
      dataIndex: 'createdby',
    },
    {
      title: '创建时间',
      dataIndex: 'createtime',
      search: false,
      valueType: 'date',
    },
    {
      title: '状态',
      dataIndex: 'status',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '无效',
          status: 'Error',
        },
        1: {
          text: '有效',
          status: 'Success',
        },
      },
    },
    {
      title: '操作',
      valueType: 'option',
      fixed: 'right',
      render: (_, record) => [
        <a
          key="editable"
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
        download('/customer/blacklist/export', filter);
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
        params={searchParams}
        onSubmit={(value) => {
          setSearchParams(value);
        }}
        onReset={() => {
          setSearchParams({});
        }}
        pagination={{
          pageSize: 10,
        }}
        request={async (params) => {
          const validfromstr = params?.validstrRange?.[0];
          const validfromend = params?.validstrRange?.[1];
          const validtostr = params?.validendRange?.[0];
          const validtoend = params?.validendRange?.[1];
          updateFilter({
            ...params,
            validfromstr,
            validfromend,
            validtostr,
            validtoend,
          });
          const msg = await query({
            ...params,
            validfromstr,
            validfromend,
            validtostr,
            validtoend,
            page: params.current,
            pageSize: params.pageSize,
          });
          return {
            data: msg.rows,
            success: true,
            total: msg.total,
          };
        }}
        toolBarRender={toolBarRender}
        search={{ span: 8, labelWidth: 100 }}
      />

      <ShowHistory
        visible={visible}
        destroyOnClose
        payload={thisPayload}
        onClose={() => {
          setVisible(false);
        }}
      />
    </PageContainer>
  );
};

export default connect(({ blacklist, loading }) => ({
  blacklist,
  loading: loading.effects['blacklist/query'],
}))(BlackList);
