import { ExclamationCircleOutlined } from '@ant-design/icons';
import { Button, Modal, message, Divider, Input } from 'antd';
import React, { useState, useEffect, useRef, Fragment, useCallback } from 'react';
import { connect, useAccess, Access, request } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';

import CreateForm from './components/CreateForm';

const { confirm } = Modal;

const RoleComponent = ({ dispatch, role: { list, pagination, filter }, loading }) => {
  const actionRef = useRef();
  const formRef = useRef();
  const [row, setRow] = useState();
  const [visible, setVisible] = useState(false);
  const access = useAccess();

  const query = useCallback(
    (param = {}) => {
      dispatch({
        type: 'role/query',
        payload: param,
      });
    },
    [dispatch],
  );

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, [filter, query]);

  const columns = [
    {
      title: '角色名称',
      dataIndex: 'roleName',
      renderFormItem: () => {
        return <Input placeholder="模糊查询" />;
      },
    },
    {
      title: '角色描述',
      dataIndex: 'remark',
      width: 200,
      search: false,
      ellipsis: true,
    },
    {
      title: '状态',
      dataIndex: 'status',
      valueEnum: {
        1: {
          text: '停用',
          status: 'Default',
        },
        0: {
          text: '正常',
          status: 'Processing',
        },
      },
    },
    // {
    //   title: '显示顺序',
    //   dataIndex: 'roleSort',
    //   search: false,
    // },
    {
      title: '创建人',
      search: false,
      dataIndex: 'createdBy',
    },
    {
      title: '更新人',
      dataIndex: 'updatedBy',
      search: false,
    },
    {
      title: '更新时间',
      dataIndex: 'updateTime',
      valueType: 'dateTime',
      search: false,
    },
    {
      title: '操作',
      dataIndex: 'option',
      search: false,
      render: (_, record) => (
        <Fragment>
          <a
            onClick={() => {
              setVisible(true);
              setRow(record);
            }}
          >
            修改
          </a>
          <Divider type="vertical" />
          <a
          // onClick={() => {
          //   setVisible(true);
          //   setRow(record);
          // }}
          >
            {record.status === '1' ? '启用' : '停用'}
          </a>
          <Divider type="vertical" />
          <a
            onClick={() => {
              confirm({
                title: `是否确认删除角色名称为"${record.roleName}"的数据项?`,
                icon: <ExclamationCircleOutlined />,
                onOk() {
                  return request(`/system/role/${record.roleId}`, {
                    method: 'DELETE',
                  }).then((res) => {
                    if (res.code === 200) {
                      message.success(res.msg);
                    } else {
                      message.error(res.msg);
                    }
                    query(filter);
                  });
                },
                onCancel() {},
              });
            }}
          >
            删除
          </a>
        </Fragment>
      ),
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/system/role/export', filter);
      }}
    >
      导出
    </Button>,
    <Access key="add" accessible={access.compAccess('message')}>
      <Button
        type="primary"
        onClick={() => {
          setVisible(true);
          setRow({});
        }}
      >
        新建
      </Button>
    </Access>,
  ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        headerTitle="查询列表"
        actionRef={actionRef}
        formRef={formRef}
        rowKey="roleId"
        options={false}
        onSubmit={(value) => {
          const params = {
            ...value,
          };
          query({ ...params });
        }}
        onReset={() => {
          query({});
        }}
        search={{
          collapsed: false,
          collapseRender: false,
        }}
        toolBarRender={toolBarRender}
        dataSource={list}
        pagination={pagination}
        loading={loading}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        columns={columns}
      />

      {!!row && (
        <CreateForm
          visible={visible}
          row={row}
          setRow={() => {
            setRow(undefined);
          }}
          onClose={() => {
            setVisible(false);
            query(filter);
          }}
        />
      )}
    </PageContainer>
  );
};

export default connect(({ role, loading }) => ({
  role,
  loading: loading.effects['role/query'],
}))(RoleComponent);
