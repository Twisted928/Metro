/* eslint-disable react-hooks/exhaustive-deps */
import React, { useRef, useEffect, useState } from 'react';
import { Button, message, Popconfirm } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import { deleteAutherRole } from './services';
import ProTable from '@ant-design/pro-table';
import RoleList from '@/components/RoleList';
import UpdateDoc from './components/updateDocModal';
import moment from 'moment';

const RoleAuth = ({ dispatch, loading, roleAuth: { list, pagination, filter } }) => {
  const ref = useRef();
  const formRef = useRef();
  const [updateVis, setUpdateVis] = useState(false);
  const [details, setDetails] = useState(false);
  const [mainId, setMainId] = useState(null);

  const query = (param = {}) => {
    dispatch({
      type: 'roleAuth/query',
      payload: param,
    });
  };

  useEffect(() => {
    query(filter);
  }, []);

  const invalid = async (record) => {
    const res = await deleteAutherRole({ id: record.id });
    const { code, msg } = res;
    if (code === 200) {
      // message.info(msg);
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
        history.push('/process/roleAuthorization/addAuthorization');
      }}
    >
      新增授权
    </Button>,
  ];

  const columns = [
    {
      title: '授权人',
      width: 150,
      dataIndex: 'autherUser',
      renderFormItem: () => {
        return <RoleList />;
      },
    },
    {
      title: '被授权角色',
      width: 150,
      dataIndex: 'receiveRoleName',
      search: false,
    },
    {
      title: '被授权人',
      width: 150,
      dataIndex: 'receiveUser',
    },
    {
      title: '授权角色',
      width: 150,
      dataIndex: 'autherRoleNa',
      search: false,
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
      title: '生效时间',
      width: 180,
      dataIndex: 'beginTime',
      search: false,
      valueType: 'date',
    },
    {
      title: '失效时间',
      width: 180,
      dataIndex: 'endTime',
      search: false,
      valueType: 'date',
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
      title: '操作',
      dataIndex: 'action',
      search: false,
      fixed: 'right',
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
        scroll={{ x: 1280 }}
        search={{
          labelWidth: 70,
        }}
        onSubmit={(value) => {
          const params = {
            ...value,
            beginTime: value.autherTime ? value.autherTime[0] : '',
            endTime: value.autherTime ? value.autherTime[1] : '',
          };
          query({ ...params });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
      />
      <UpdateDoc {...updateDocMsg} />
    </PageContainer>
  );
};

export default connect(({ roleAuth, loading }) => ({
  roleAuth,
  loading: loading.effects['roleAuth/query'],
}))(RoleAuth);
