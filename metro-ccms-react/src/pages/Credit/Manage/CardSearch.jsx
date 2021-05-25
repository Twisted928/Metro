import { Button } from 'antd';
import React, { useEffect, useRef } from 'react';
import { connect, history } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import DepartmentTree from '@/components/Department/index';
import { download } from '@/services/commom';

const CardSearch = ({ dispatch, creditmanage: { filter, list, pagination }, loading }) => {
  const actionRef = useRef();
  const formRef = useRef();

  const query = async (param = {}) => {
    dispatch({
      type: 'creditmanage/queryById',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const columns = [
    {
      title: '部门',
      dataIndex: 'departcode',
      hideInTable: true,
      renderFormItem: (_, { type, defaultRender, fieldProps, ...rest }, form) => {
        if (type === 'form') {
          return null;
        }
        const status = form.getFieldValue('state');
        if (status !== 'open') {
          return <DepartmentTree {...rest} {...fieldProps} ifmultiple />;
        }
        return defaultRender(_);
      },
    },
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
      title: '部门名称',
      dataIndex: 'departname',
      search: false,
    },
    {
      title: '门店编码',
      dataIndex: 'storecode',
      search: false,
    },
    {
      title: '卡号编码',
      dataIndex: 'cardcode',
      // search: false,
    },
    {
      title: '卡号名称',
      dataIndex: 'cardname',
      // search: false,
    },
    {
      title: '额度类型',
      dataIndex: 'limittype',
      // search: false,
    },
    {
      title: '来源',
      dataIndex: 'source',
      // search: false,
    },
    {
      title: '申请账期',
      dataIndex: 'applypayterm',
      search: false,
    },
    {
      title: '申请额度',
      dataIndex: 'applylimit',
      search: false,
    },
    {
      title: '发布账期',
      dataIndex: 'paymentterm',
      search: false,
    },
    {
      title: '发布额度',
      dataIndex: 'limit',
      search: false,
    },
    {
      title: '发布日期',
      dataIndex: 'releaseRange',
      hideInTable: true,
      valueType: 'dateRange',
    },
    {
      title: '授信生效区间',
      dataIndex: 'validstrRange',
      valueType: 'dateRange',
    },
    {
      title: '授信失效区间',
      dataIndex: 'validendRange',
      valueType: 'dateRange',
    },
    {
      title: '生效时间',
      dataIndex: 'validfrom',
      search: false,
      valueType: 'date',
    },
    {
      title: '到期时间',
      dataIndex: 'validto',
      search: false,
      valueType: 'date',
    },
    {
      title: '客户组',
      dataIndex: 'custgrname',
      // search: false,
    },
    {
      title: '信用组',
      dataIndex: 'groupname',
      // search: false,
    },
    {
      title: '申请单号',
      dataIndex: 'applicationno',
      search: false,
    },
    {
      title: '发布日期',
      dataIndex: 'releasedate',
      search: false,
      valueType: 'date',
    },
    {
      title: '申请人姓名',
      dataIndex: 'applicant',
      search: false,
    },
    {
      title: '申请时间',
      dataIndex: 'applytime',
      search: false,
      valueType: 'date',
    },
    {
      title: '状态',
      dataIndex: 'status',
      // search: false,
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
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/credit/management/cardexport', filter);
      }}
    >
      导出
    </Button>,
  ];

  return (
    <PageContainer
      ghost
      title={false}
      onBack={() => {
        history.goBack();
      }}
    >
      <ProTable
        rowKey="id"
        headerTitle="查询列表"
        actionRef={actionRef}
        formRef={formRef}
        options={false}
        dataSource={list}
        loading={loading}
        pagination={pagination}
        onSubmit={(value) => {
          query({
            ...value,
            releasedatestr: value?.releaseRange?.[0],
            releasedateend: value?.releaseRange?.[1],
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
        columns={columns}
        search={{ span: 8, labelWidth: 100 }}
        scroll={{ x: 3600 }}
      />
    </PageContainer>
  );
};

export default connect(({ creditmanage, loading }) => ({
  creditmanage,
  loading: loading.effects['creditmanage/queryById'],
}))(CardSearch);
