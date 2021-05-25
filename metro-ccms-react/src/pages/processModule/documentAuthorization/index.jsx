/* eslint-disable react-hooks/exhaustive-deps */
import React, { useRef, useState, useEffect } from 'react';
import { Button, message, Popconfirm } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import ProTable from '@ant-design/pro-table';
import { deleteAutherDo } from './services';
import RoleList from '@/components/RoleList';
import UpdateDoc from './components/updateDocModal';

import moment from 'moment';

const DocumentAut = ({ dispatch, loading, documentAuth: { list, pagination, filter } }) => {
  const ref = useRef();
  const formRef = useRef();
  const [updateVis, setUpdateVis] = useState(false);
  const [details, setDetails] = useState(false);
  const [mainId, setMainId] = useState(null);

  const query = (param = {}) => {
    dispatch({
      type: 'documentAuth/query',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const invalid = async (record) => {
    const { id, instanceId, taskId, receiveId } = record;
    const params = {
      id,
      instanceId,
      taskId,
      receiveId,
    };
    const res = await deleteAutherDo(params);
    const { code, msg } = res;
    if (code === 200) {
      message.info(msg);
      query(filter);
    }
    if (code === 500) {
      message.error(msg);
    }
  };

  const toolBarRender = () => [
    <Button
      key="sq"
      type="primary"
      onClick={() => {
        history.push('/process/documentAuthorization/addAuthorization');
      }}
    >
      新增授权
    </Button>,
  ];

  const columns = [
    {
      title: '授权人',
      dataIndex: 'autherUser',
      width: 100,
    },
    {
      title: '授权人角色',
      dataIndex: 'autherRoleName',
      search: false,
      width: 300,
      ellipsis: true,
      renderFormItem: () => {
        return <RoleList />;
      },
    },
    {
      title: '被授权人',
      dataIndex: 'receiveUser',
      width: 150,
    },
    {
      title: '被授权人角色',
      dataIndex: 'receiveRoleName',
      width: 150,
    },
    {
      title: '业务单据号',
      width: 150,
      dataIndex: 'applicationNo',
    },
    {
      title: '单据类型',
      width: 180,
      dataIndex: 'type',
      search: false,
      render: (val) => {
        if (val === 1) {
          return '自助平台客户引入';
        }
        if (val === 2) {
          return '大商旅客户引入';
        }
        if (val === 3) {
          return '临时额度申请';
        }
        if (val === 4) {
          return '特批客户申请';
        }
        if (val === 5) {
          return '客户进降阶';
        }
        if (val === 6) {
          return '逾期风险复核';
        }
        if (val === 7) {
          return '其他风险复核';
        }
        if (val === 8) {
          return '客户等级管理';
        }
        if (val === 9) {
          return '用户系统权限申请';
        }
        return '';
      },
    },
    {
      title: '状态',
      dataIndex: 'status',
      width: 100,
      valueEnum: {
        1: {
          text: '有效',
          status: 'Processing',
        },
        0: {
          text: '已作废',
          status: 'Default',
        },
      },
    },
    {
      title: '授权时间',
      width: 180,
      dataIndex: 'autherTime',
      valueType: 'dateRange',
      render: (_, record) => {
        return record.autherTime ? moment(record.autherTime).format('YYYY-MM-DD hh:mm:ss') : '-';
      },
    },
    {
      title: '生效时间',
      dataIndex: 'beginTime',
      width: 180,
      search: false,
      valueType: 'date',
    },
    {
      title: '失效时间',
      dataIndex: 'endTime',
      width: 180,
      search: false,
      valueType: 'date',
    },
    {
      title: '操作',
      dataIndex: 'action',
      fixed: 'right',
      search: false,
      render: (_, record) => {
        return record.status === 1 ? (
          <Popconfirm
            placement="top"
            title={'作废后无法恢复数据状态！'}
            onConfirm={() => invalid(record)}
            okText="确认"
            cancelText="取消"
          >
            <a>作废</a>
          </Popconfirm>
        ) : (
          '-'
        );
      },
    },
  ];

  const updateDocMsg = {
    details,
    updateVis,
    mainId,
    onCancel: (vis) => {
      setUpdateVis(vis);
      setMainId(null);
      setDetails(false);
    },
    queryLoad: () => {
      query(filter);
      setMainId(null);
      setDetails(false);
    },
  };

  return (
    <PageContainer title={false} ghost>
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
        scroll={{ x: 1830 }}
        onSubmit={(value) => {
          const params = {
            ...value,
            beginTime: value.autherTime ? value.autherTime[0] : '',
            endTime: value.autherTime ? value.autherTime[1] : '',
          };
          query({ ...params });
        }}
        onReset={() => {
          query({});
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        search={{
          labelWidth: 100,
        }}
      />
      <UpdateDoc {...updateDocMsg} />
    </PageContainer>
  );
};

export default connect(({ documentAuth, loading }) => ({
  documentAuth,
  loading: loading.effects['documentAuth/query'],
}))(DocumentAut);
