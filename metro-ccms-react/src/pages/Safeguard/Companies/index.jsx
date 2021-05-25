import React, { useEffect, useRef, Fragment, useCallback } from 'react';
import { Button, Divider, message } from 'antd';
import { connect, history } from 'umi';
import numeral from 'numeral';
import DeleteText from '@/components/DeleteText';
import { download } from '@/services/commom';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { delList } from './service';

const Companies = ({ dispatch, loading, companies: { list, filter, pagination } }) => {
  const ref = useRef();
  const formRef = useRef();

  const query = useCallback(
    (param = {}) => {
      dispatch({
        type: 'companies/query',
        payload: param,
      });
    },
    [dispatch],
  );

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, [filter, query]);

  const addComponents = () => {
    history.push({
      pathname: '/safeguard/Companies/createForm',
    });
  };

  const updataComponents = (res) => {
    history.push({
      pathname: '/safeguard/Companies/updataForm',
      query: {
        id: res.id,
      },
    });
  };

  const deleteMsg = async (res) => {
    const response = await delList({ id: res.id });
    const { msg, code } = response;
    if (code === 500) {
      message.error(msg);
      return;
    }
    query();
  };

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/insurance/checklist/export', filter);
      }}
    >
      导出
    </Button>,
    <Button
      key="add"
      type="primary"
      onClick={() => {
        addComponents();
      }}
    >
      新增
    </Button>,
  ];

  const columns = [
    {
      title: '公司编码',
      dataIndex: 'compCode',
      search: false,
      ellipsis: true,
      key: 'compCode',
      fixed: 'left',
      width: 160,
    },
    {
      title: '公司名称',
      dataIndex: 'compName',
      search: false,
      ellipsis: true,
      key: 'compName',
      fixed: 'left',
    },
    {
      title: '买方代码',
      dataIndex: 'buyerno',
      ellipsis: true,
      search: false,
      key: 'buyerno',
      width: 150,
    },
    {
      title: '客户编码',
      dataIndex: 'custCode',
      ellipsis: true,
      key: 'custCode',
      width: 120,
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      ellipsis: true,
      key: 'custName',
      width: 150,
    },
    {
      title: '保单号',
      search: false,
      dataIndex: 'policyno',
      ellipsis: true,
      key: 'policyno',
      width: 150,
    },
    {
      title: '保险评级',
      search: false,
      dataIndex: 'creditLevel',
      key: 'creditLevel',
      width: 100,
    },
    {
      title: '保险额度（元）',
      search: false,
      ellipsis: true,
      dataIndex: 'quota',
      key: 'quota',
      width: 150,
      align: 'right',
      render: (_, record) => numeral(record.quota).format('0,0.00'),
    },
    {
      title: '保险账期（天）',
      search: false,
      dataIndex: 'quotaDays',
      key: 'quotaDays',
      ellipsis: true,
      width: 130,
      align: 'right',
    },
    {
      title: '有效状态',
      dataIndex: 'status',
      align: 'center',
      key: 'status',
      width: 100,
      valueEnum: {
        1: {
          text: '有效',
        },
        0: {
          text: '无效',
        },
      },
    },
    {
      title: '创建人',
      dataIndex: 'createdBy',
      key: 'createdBy',
      search: false,
      width: 100,
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      key: 'createTime',
      search: false,
      width: 120,
      valueType: 'date',
    },
    {
      title: '更新人',
      dataIndex: 'updatedBy',
      key: 'updatedBy',
      search: false,
      width: 100,
    },
    {
      title: '更新时间',
      dataIndex: 'updateTime',
      key: 'updateTime',
      search: false,
      width: 180,
      valueType: 'date',
    },
    {
      title: '操作',
      dataIndex: 'action',
      width: 120,
      key: 'action',
      fixed: 'right',
      search: false,
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                updataComponents(record);
              }}
            >
              补录
            </a>
            <Divider type="vertical" />
            <DeleteText deleteFunc={() => deleteMsg(record)} />
          </Fragment>
        );
      },
    },
  ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        headerTitle="查询列表"
        rowKey="id"
        dataSource={list}
        actionRef={ref}
        formRef={formRef}
        options={false}
        loading={loading}
        pagination={pagination}
        toolBarRender={toolBarRender}
        columns={columns}
        onReset={() => {
          query({});
        }}
        onSubmit={(value) => {
          query({ ...value });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        scroll={{ x: 2050 }}
      />
    </PageContainer>
  );
};

export default connect(({ companies, loading }) => ({
  companies,
  loading: loading.effects['companies/query'],
}))(Companies);
