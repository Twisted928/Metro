/* eslint-disable react-hooks/exhaustive-deps */
import { ExclamationCircleOutlined } from '@ant-design/icons';
import { Button, Divider, Modal, message, Input } from 'antd';
import React, { useState, useEffect, useRef, Fragment } from 'react';
import { connect, useAccess, Access, request } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';
import { handleTree } from '@/utils/utils';
import { queryDept } from './service';

import CreateForm from './components/CreateForm';

const { confirm } = Modal;

const DepartmentComponent = ({ department: { filter, treeList } }) => {
  const actionRef = useRef();
  const formRef = useRef();
  const [row, setRow] = useState();

  const [dept, setDept] = useState([]);
  const [loading, setLoading] = useState(false);

  const [visible, setVisible] = useState(false);

  const access = useAccess();

  const query = async (param = {}) => {
    setLoading(true);
    const response = await queryDept(param);
    if (response.code === 200) {
      const deptList = handleTree(response.data, 'deptId');
      setDept(deptList);
    }

    setLoading(false);
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const columns = [
    {
      title: '部门名称/编码',
      width: 400,
      dataIndex: 'deptName',
      renderFormItem: () => {
        return <Input placeholder="编码精确查询 名称模糊查询" />;
      },
    },
    {
      title: '状态',
      dataIndex: 'status',
      width: 100,
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
      width: 100,
      search: false,
      dataIndex: 'createdBy',
    },
    {
      title: '创建时间',
      valueType: 'dateTime',
      width: 180,
      search: false,
      dataIndex: 'createTime',
    },
    {
      title: '更新人',
      width: 100,
      search: false,
      dataIndex: 'updatedBy',
    },
    {
      title: '更新时间',
      valueType: 'dateTime',
      width: 180,
      search: false,
      dataIndex: 'updateTime',
    },
    {
      title: '操作',
      fixed: 'right',
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
                title: `是否确认删除部门名称为"${record.deptName}"的数据项?`,
                icon: <ExclamationCircleOutlined />,
                onOk() {
                  return request(`/system/dept/${record.deptId}`, {
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
      onClick={async () => {
        const param = { ...filter };
        await download('/system/dept/export', param);
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
        rowKey="deptId"
        options={false}
        onSubmit={(value) => {
          query({ ...value });
        }}
        onReset={() => {
          query({});
        }}
        search={{
          collapsed: false,
          collapseRender: false,
          labelWidth: 100,
        }}
        toolBarRender={toolBarRender}
        dataSource={dept}
        pagination={false}
        loading={loading}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        columns={columns}
        indentSize={40}
        expandRowByClick
      />

      {!!row && (
        <CreateForm
          visible={visible}
          treeList={treeList}
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

export default connect(({ department }) => ({
  department,
}))(DepartmentComponent);
