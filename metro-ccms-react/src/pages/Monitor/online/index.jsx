import { ExclamationCircleOutlined, DeleteOutlined } from '@ant-design/icons';
import { Button, Modal, message } from 'antd';
import React, { useRef } from 'react';
import { request } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';

const { confirm } = Modal;

const OnlineList = () => {
  const actionRef = useRef();
  const formRef = useRef();

  const columns = [
    {
      title: '序号',
      dataIndex: 'index',
      valueType: 'indexBorder',
      width: 72,
    },
    {
      title: '会话编号',
      dataIndex: 'tokenId',
      hideInSearch: true,
    },
    {
      title: '登录名称',
      dataIndex: 'userName',
    },
    {
      title: '部门名称',
      dataIndex: 'deptName',
    },
    {
      title: '主机',
      dataIndex: 'ipaddr',
    },
    {
      title: '登录地点',
      width: 100,
      hideInSearch: true,
      dataIndex: 'loginLocation',
    },
    {
      title: '浏览器',
      width: 150,
      hideInSearch: true,
      dataIndex: 'browser',
    },
    {
      title: '操作系统',
      width: 100,
      hideInSearch: true,
      dataIndex: 'os',
    },
    {
      title: '登录时间',
      dataIndex: 'loginTime',
      hideInSearch: true,
      valueType: 'dateTime',
      width: 150,
    },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => (
        <a
          onClick={() => {
            confirm({
              title: `是否确认删除字典名称为"${record.dictName}"的数据项?`,
              icon: <ExclamationCircleOutlined />,
              onOk() {
                return request(`/system/dict/type/${record.dictId}`, {
                  method: 'DELETE',
                }).then((res) => {
                  if (res.code === 200) {
                    message.success(res.msg);
                  } else {
                    message.error(res.msg);
                  }
                });
              },
              onCancel() {},
            });
          }}
        >
          <DeleteOutlined /> 强退
        </a>
      ),
    },
  ];

  return (
    <PageContainer>
      <ProTable
        headerTitle="查询表格"
        actionRef={actionRef}
        formRef={formRef}
        rowKey="tokenId"
        request={async (params = {}) =>
          request('/monitor/online/list', {
            params: {
              ...params,
            },
          }).then((response) => {
            return { ...response, page: response.current, data: response.rows };
          })
        }
        toolBarRender={() => [
          <Button
            key="export"
            onClick={() => {
              download('/system/dict/type/export');
            }}
          >
            导出
          </Button>,
        ]}
        columns={columns}
      />
    </PageContainer>
  );
};

export default OnlineList;
