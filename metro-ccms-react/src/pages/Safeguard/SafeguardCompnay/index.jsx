import React, { useEffect, useRef, Fragment } from 'react';
import { Button, Divider, Menu, Dropdown, message, Input } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { connect, history } from 'umi';
import DeleteText from '@/components/DeleteText';
import { download } from '@/services/commom';
import { DownOutlined } from '@ant-design/icons';
import ProTable from '@ant-design/pro-table';
import { deleteList } from './service';

const SafeguardCompnay = ({
  dispatch,
  safeguardCompnay: { list, pagination, filter },
  loading,
}) => {
  const ref = useRef();
  const formRef = useRef();

  // 页面查询
  const query = (param = {}) => {
    dispatch({
      type: 'safeguardCompnay/query',
      payload: param,
    });
  };

  // 页面初始化
  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  // 添加
  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/insurance/management/export', filter);
      }}
    >
      导出
    </Button>,
    <Button
      key="add"
      type="primary"
      onClick={() => {
        history.push('/safeguard/SafeguardCompnay/createForm');
      }}
    >
      新增
    </Button>,
  ];

  // 修改
  const updataMsg = (res) => {
    const { id } = res;
    history.push({
      pathname: '/safeguard/SafeguardCompnay/updataFrom',
      query: {
        id,
        disabled: false,
      },
    });
  };

  // 详情
  const detailPage = (res) => {
    const { id } = res;
    history.push({
      pathname: '/safeguard/SafeguardCompnay/detailsForm',
      query: {
        id,
        disabled: false,
      },
    });
  };

  // 删除
  const deleteMsg = async (res) => {
    const response = await deleteList({ id: res.id });
    const { msg, code } = response;
    if (code === 500) {
      message.error(msg);
      return;
    }
    query();
  };

  const menu = (res) => (
    <Menu>
      <Menu.Item key="del">
        <DeleteText deleteFunc={() => deleteMsg(res)} />
      </Menu.Item>
      <Menu.Item key="det">
        <a onClick={() => detailPage(res)} target="_blank">
          详情
        </a>
      </Menu.Item>
    </Menu>
  );

  const columns = [
    {
      title: '公司编码',
      width: 150,
      dataIndex: 'compCode',
      search: false,
      key: 'compCode',
      fixed: 'left',
      ellipsis: true,
    },
    {
      title: '公司名称',
      dataIndex: 'compName',
      ellipsis: true,
      fixed: 'left',
      key: 'compName',
      width: 200,
      renderFormItem: () => {
        return (
          <Fragment>
            <Input placeholder="模糊查询" />
          </Fragment>
        );
      },
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
      title: '生效时间',
      dataIndex: 'validFrom',
      key: 'validFrom',
      search: false,
      valueType: 'date',
    },
    {
      title: '到期时间',
      dataIndex: 'validTo',
      key: 'validTo',
      search: false,
      valueType: 'date',
    },
    {
      title: '创建人',
      dataIndex: 'createdBy',
      key: 'createdBy',
      search: false,
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      key: 'createTime',
      search: false,
      valueType: 'date',
    },
    {
      title: '更新人',
      dataIndex: 'updatedBy',
      key: 'updatedBy',
      ellipsis: true,
      search: false,
    },
    {
      title: '更新时间',
      dataIndex: 'updateTime',
      key: 'updateTime',
      search: false,
      valueType: 'date',
    },
    {
      title: '操作',
      dataIndex: 'action',
      key: 'action',
      search: false,
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                updataMsg(record);
              }}
            >
              修改
            </a>
            <Divider type="vertical" />
            <Dropdown overlay={() => menu(record)}>
              <a className="ant-dropdown-link" onClick={(e) => e.preventDefault()}>
                更多 <DownOutlined />
              </a>
            </Dropdown>
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
        onSubmit={(value) => {
          query({ ...value });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
        pagination={pagination}
        dataSource={list}
        loading={loading}
        actionRef={ref}
        formRef={formRef}
        options={false}
        toolBarRender={toolBarRender}
        columns={columns}
      />
    </PageContainer>
  );
};

export default connect(({ safeguardCompnay, loading }) => ({
  safeguardCompnay,
  loading: loading.effects['safeguardCompnay/query'],
}))(SafeguardCompnay);
