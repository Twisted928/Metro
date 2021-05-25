/* eslint-disable react-hooks/exhaustive-deps */
import React, { useState, useEffect, useRef, Fragment, useCallback } from 'react';
import { Divider, message, Dropdown, Menu } from 'antd';
import { DownOutlined } from '@ant-design/icons';
import { connect, Access, useAccess, history } from 'umi';
import ProTable from '@ant-design/pro-table';
import { enableOrDisable } from './service';
import { PageContainer } from '@ant-design/pro-layout';
import EditForm from './components/EditForm';

const WarningModel = ({ dispatch, warningmodel: { list, filter, pagination }, loading }) => {
  const formRef = useRef();
  const actionRef = useRef();
  const access = useAccess();
  const [visible, setVisible] = useState(false);
  const [mocdelCode, setMocdelCode] = useState(null);

  const query = (param = {}) => {
    dispatch({
      type: 'warningmodel/query',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  // 删除
  const deleteColumn = async (record) => {
    const params = {
      id: record.id,
      status: record.status === 0 ? 1 : 0,
    };
    const res = await enableOrDisable(params);
    const { code, msg } = res;
    if (code === 200) {
      query(filter);
    }
    if (code === 500) {
      message.error(msg);
    }
  };

  // 详情
  const ShowDetails = (record) => {
    history.push({
      pathname: '/warning/warningModel/details',
      query: {
        mocdelCode: record.mocdelCode,
      },
    });
  };

  const AddEval = (record) => {
    history.push({
      pathname: '/warning/warningModel/addEval',
      query: {
        mocdelCode: record.mocdelCode,
      },
    });
  };

  const statusList = (record) => {
    setVisible(true);
    setMocdelCode(record.mocdelCode);
  };

  const menu = (record) => (
    <Menu>
      <Menu.Item key="details">
        <a key="history" onClick={() => ShowDetails(record)}>
          详情
        </a>
      </Menu.Item>
      <Menu.Item key="stoporbegin">
        <Access key="stop" accessible={access.compAccess('earlywarning:warningmodel:save')}>
          <a key="stat" onClick={() => deleteColumn(record)}>
            {record.status === 1 ? '停用' : '启用'}
          </a>
        </Access>
      </Menu.Item>
      <Menu.Item key="history">
        <a key="history" onClick={() => statusList(record)}>
          状态记录
        </a>
      </Menu.Item>
    </Menu>
  );

  const columns = [
    {
      title: '模型编码',
      dataIndex: 'mocdelCode',
      fixed: 'left',
      width: 150,
      search: true,
    },
    {
      title: '模型名称',
      fixed: 'left',
      dataIndex: 'mocdelName',
      // search: false,
      width: 200,
      ellipsis: true,
    },
    {
      title: '描述',
      dataIndex: 'remark',
      width: 200,
      ellipsis: true,
      search: false,
    },
    {
      title: '状态',
      width: 100,
      dataIndex: 'status',
      valueEnum: {
        0: {
          text: '停用',
          status: 'Error',
        },
        1: {
          text: '启用',
          status: 'Success',
        },
      },
    },
    {
      title: '创建人',
      width: 120,
      dataIndex: 'createdBy',
      search: false,
    },
    {
      title: '创建时间',
      width: 180,
      dataIndex: 'createTime',
      search: false,
    },
    {
      title: '更新人',
      width: 120,
      search: false,
      dataIndex: 'updatedBy',
    },
    {
      title: '更新时间',
      width: 180,
      search: false,
      dataIndex: 'updateTime',
    },
    {
      title: '操作',
      fixed: 'right',
      render: (_, record) => [
        <Fragment key="fra">
          <Access key="updata" accessible={access.compAccess('earlywarning:warningmodel:save')}>
            <a ket="edit" onClick={() => AddEval(record)}>
              配置模型
            </a>
          </Access>
          <Divider type="vertical" />
          <Dropdown overlay={() => menu(record)}>
            <a className="ant-dropdown-link" onClick={(e) => e.preventDefault()}>
              更多 <DownOutlined />
            </a>
          </Dropdown>
        </Fragment>,
      ],
    },
  ];

  const toolBarRender = () => [];

  const editform = {
    visible,
    mocdelCode,
    cancel: (data) => {
      setVisible(data);
    },
  };

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="id"
        headerTitle="查询列表"
        options={false}
        actionRef={actionRef}
        formRef={formRef}
        columns={columns}
        pagination={pagination}
        loading={loading}
        dataSource={list}
        onSubmit={(value) => {
          query(value);
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
        scroll={{ x: 1420 }}
        toolBarRender={toolBarRender}
        search={{ span: 8, labelWidth: 70, collapsed: false, collapseRender: false }}
      />

      {/* 新建弹窗 */}
      <EditForm {...editform} />
    </PageContainer>
  );
};

export default connect(({ warningmodel, loading }) => ({
  warningmodel,
  loading: loading.effects['warningmodel/query'],
  loadingList: loading.effects['warningmodel/statusList'],
}))(WarningModel);
