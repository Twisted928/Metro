import { ExclamationCircleOutlined } from '@ant-design/icons';
import { Button, Modal, message, Divider } from 'antd';
import React, { useState, useEffect, useRef, Fragment, useCallback } from 'react';
import { connect, request, useAccess, Access } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';
import CreateForm from './components/CreateForm';

const { confirm } = Modal;
const UserComponent = ({ dispatch, user: { list, pagination, filter }, loading }) => {
  const actionRef = useRef();
  const formRef = useRef();
  const [row, setRow] = useState();
  const [visible, setVisible] = useState(false);
  const access = useAccess();

  const query = useCallback(
    (param = {}) => {
      dispatch({
        type: 'user/query',
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
      title: '序号',
      dataIndex: 'index',
      search: false,
      align: 'center',
      render: (dom, entity, index) => {
        return index + 1;
      },
    },
    {
      title: '用户名',
      dataIndex: 'userName',
    },
    {
      title: '用户姓名',
      dataIndex: 'nickName',
    },
    {
      title: '启用状态',
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
    {
      title: '创建人',
      dataIndex: 'createdBy',
      search: false,
    },
    {
      title: '修改人',
      dataIndex: 'updatedBy',
      search: false,
    },
    {
      title: '修改时间',
      dataIndex: 'updateTime',
      valueType: 'date',
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
              setRow(record);
              setVisible(true);
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
                title: `是否确认删除客户名称为"${record.userName}"的数据项?`,
                icon: <ExclamationCircleOutlined />,
                onOk() {
                  return request(`/system/user/${record.userId}`, {
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
    <Access key="add" accessible={access.compAccess('system:user:export')}>
      <Button
        key="export"
        onClick={() => {
          download('/system/user/export', filter);
        }}
      >
        导出
      </Button>
    </Access>,
    <Access key="add" accessible={access.compAccess('system:user:add')}>
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
        rowKey="userId"
        onSubmit={(value) => {
          const { userName, nickName, status } = value;
          const params = {
            userName,
            nickName,
            status,
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
        options={false}
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
          }}
        />
      )}
    </PageContainer>
  );
};

export default connect(({ user, loading }) => ({
  user,
  loading: loading.models.user,
}))(UserComponent);
