import React, { useState, useRef, useEffect, useCallback, Fragment } from 'react';
import { Button, Divider, Menu, Dropdown } from 'antd';
import { connect } from 'umi';
// import moment from 'moment';
import { download } from '@/services/commom';
import { DownOutlined } from '@ant-design/icons';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import DeleteText from '@/components/DeleteText';
import { deleteList } from './service';
import AddModal from './components/addModal';

const EvaluateIndicator = ({
  dispatch,
  loading,
  evaluateIndicator: { list, pagination, filter },
}) => {
  const ref = useRef();
  const formRef = useRef();
  const [visible, setVisible] = useState(false);
  const [formData, setFormData] = useState({});
  const [ifView, setIfView] = useState(false);

  const query = (param = {}) => {
    dispatch({
      type: 'evaluateIndicator/query',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query();
  }, []);

  const deleteMsg = async (res) => {
    const response = await deleteList({ id: res.id });
    if (response.code === 200) {
      query(filter);
    }
  };

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/model/index/export', filter);
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
      <Menu.Item key="updata">
        <a
          onClick={() => {
            setVisible(true);
            setIfView(true);
            setFormData(res);
          }}
        >
          详情
        </a>
      </Menu.Item>
      <Menu.Item key="det">
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
      title: '指标编码',
      dataIndex: 'id',
      width: 100,
      fixed: 'left',
    },
    {
      title: '指标名称',
      dataIndex: 'name',
      ellipsis: true,
      width: 200,
      fixed: 'left',
    },
    {
      title: '指标描述',
      dataIndex: 'description',
      ellipsis: true,
      width: 200,
    },
    {
      title: '指标大类',
      dataIndex: 'type',
      width: 120,
      valueEnum: {
        A: {
          text: '定性指标',
        },
        B: {
          text: '财务指标',
        },
        C: {
          text: '交易指标',
        },
      },
    },
    {
      title: '打分方法',
      dataIndex: 'method',
      ellipsis: true,
      width: 120,
      valueEnum: {
        1: {
          text: '逻辑判断法',
        },
        2: {
          text: '区间打分法',
        },
      },
    },
    {
      title: '创建人',
      search: false,
      dataIndex: 'createdBy',
      width: 120,
    },
    {
      title: '创建日期',
      search: false,
      dataIndex: 'createTime',
      width: 180,
    },
    {
      title: '修改人',
      search: false,
      dataIndex: 'updatedBy',
      width: 120,
    },
    {
      title: '修改日期',
      search: false,
      dataIndex: 'updateTime',
      width: 180,
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
      setIfView(false);
      setFormData({});
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
        onReset={() => {
          query({});
        }}
        scroll={{ x: 1470 }}
      />
      <AddModal {...addmodal} />
    </PageContainer>
  );
};

export default connect(({ evaluateIndicator, loading }) => ({
  evaluateIndicator,
  loading: loading.effects['evaluateIndicator/query'],
}))(EvaluateIndicator);
