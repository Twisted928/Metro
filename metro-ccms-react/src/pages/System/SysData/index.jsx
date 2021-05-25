import { ExclamationCircleOutlined } from '@ant-design/icons';
import { Button, Divider, Modal, message, Input } from 'antd';
import React, { useState, useEffect, useRef, Fragment } from 'react';
import { connect, useAccess, Access, request } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';
import { handleTree } from '@/utils/utils';
import { queryDept, enableOrList } from './service';

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
      const deptList = handleTree(response.data, 'id');
      setDept(deptList);
    }

    setLoading(false);
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const enableOrDisable = async (res) => {
    const { id, status } = res;
    const flag = status === 0 ? 1 : 0;
    const params = {
      id,
      status: flag,
    };
    const response = await enableOrList(params);
    const { code, msg } = response;
    if (code === 200) {
      message.success(msg);
      query();
    } else {
      message.success(msg);
    }
  };

  const columns = [
    {
      title: '参数名称',
      width: 350,
      ellipsis: true,
      dataIndex: 'description',
      renderFormItem: () => {
        return (
          <Fragment>
            <Input placeholder="模糊查询" />
          </Fragment>
        );
      },
    },
    {
      title: '参数代码',
      dataIndex: 'ctype',
      width: 130,
    },
    {
      title: '状态',
      dataIndex: 'status',
      width: 80,
      valueEnum: {
        0: {
          text: '无效',
          status: 'Default',
        },
        1: {
          text: '有效',
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
      title: '修改时间',
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
          <a onClick={() => enableOrDisable(record)}>{record.status === 0 ? '启用' : '停用'}</a>
          <Divider type="vertical" />
          <a
            onClick={() => {
              confirm({
                title: `是否确认删除参数描述为"${record.description}"的数据项?`,
                icon: <ExclamationCircleOutlined />,
                onOk() {
                  return request(`/system/data/${record.id}`, {
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
        await download('/system/data/export', param);
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
        rowKey="id"
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
