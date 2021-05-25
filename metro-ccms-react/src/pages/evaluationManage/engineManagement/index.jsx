/* eslint-disable react-hooks/exhaustive-deps */
import React, { useState, useEffect, useRef, Fragment } from 'react';
import { Button, Divider, Menu, Dropdown, message } from 'antd';
import { connect, history } from 'umi';
// import moment from 'moment';
import { DownOutlined } from '@ant-design/icons';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import DeleteText from '@/components/DeleteText';
import { deleteList } from './service';
import AddModal from './components/addModal';

const EngineManagement = ({
  dispatch,
  loading,
  engineManagement: { list, pagination, filter },
}) => {
  const ref = useRef();
  const formRef = useRef();
  const [visible, setVisible] = useState(false);
  const [formData, setFormData] = useState(false);
  const [ifView, setIfView] = useState(false);

  const query = (param = {}) => {
    dispatch({
      type: 'engineManagement/query',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const deleteMsg = async (res) => {
    const response = await deleteList({ id: res.id });
    const { code, msg } = response;
    if (code === 200) {
      query(filter);
    }
    if (code === 500) {
      message.error(msg);
    }
  };

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        // setVisible(true);
      }}
    >
      导出
    </Button>,
    <Button
      key="add"
      type="primary"
      onClick={() => {
        setVisible(true);
      }}
    >
      新增
    </Button>,
  ];

  const menu = (res) => (
    <Menu>
      <Menu.Item key="addZb">
        <a
          onClick={() => {
            history.push({
              pathname: '/evaluation/engineManagement/addIndicator',
              query: {
                modId: res.id,
              },
            });
          }}
        >
          添加指标
        </a>
      </Menu.Item>
      <Menu.Item key="updata">
        <a
          onClick={() => {
            setVisible(true);
            setFormData(res);
            setIfView(true);
          }}
        >
          详情
        </a>
      </Menu.Item>
      <Menu.Item key="updata">
        <DeleteText
          deleteFunc={() => {
            deleteMsg(res);
          }}
        />
      </Menu.Item>
    </Menu>
  );

  const columns = [
    {
      title: '模型编码',
      dataIndex: 'id',
      fixed: 'left',
      width: 100,
    },
    {
      title: '模型名称',
      dataIndex: 'name',
      ellipsis: true,
      fixed: 'left',
      width: 200,
    },
    {
      title: '描述',
      search: false,
      dataIndex: 'description',
      ellipsis: true,
      width: 200,
    },
    {
      title: '备注',
      search: false,
      dataIndex: 'remark',
      ellipsis: true,
      width: 200,
    },
    {
      title: '创建日期',
      search: false,
      dataIndex: 'createTime',
      width: 150,
      valueType: 'date',
    },
    {
      title: '失效日期',
      search: false,
      dataIndex: 'expirydate',
      width: 150,
      valueType: 'date',
    },
    {
      title: '创建人',
      search: false,
      dataIndex: 'createdBy',
      width: 120,
    },
    {
      title: '最后一次修改人',
      search: false,
      dataIndex: 'updateBy',
      width: 150,
    },
    {
      title: '最后修改时间',
      search: false,
      dataIndex: 'updateTime',
      width: 150,
      valueType: 'date',
    },
    {
      title: '操作',
      dataIndex: 'action',
      width: 120,
      fixed: 'right',
      search: false,
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                setVisible(true);
                setFormData(record);
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

  // 组件值
  const addmodal = {
    visible,
    formData,
    ifView,
    onCancel: (vis) => {
      setVisible(vis);
      setFormData({});
      setIfView(false);
    },
    loadQuery: () => {
      query(filter);
    },
  };

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
        onSubmit={(value) => {
          query({ ...value });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        search={{
          collapsed: false,
          collapseRender: false,
        }}
        onReset={() => {
          query({});
        }}
        scroll={{ x: 1740 }}
      />
      <AddModal {...addmodal} />
    </PageContainer>
  );
};

export default connect(({ engineManagement, loading }) => ({
  engineManagement,
  loading: loading.effects['engineManagement/query'],
}))(EngineManagement);
