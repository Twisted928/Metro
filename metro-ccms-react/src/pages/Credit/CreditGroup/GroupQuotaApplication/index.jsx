import React, { useEffect, useRef, Fragment } from 'react';
import { Button, Divider, Menu, Dropdown } from 'antd';
import { connect, history } from 'umi';
import { download } from '@/services/commom';
import { DownOutlined } from '@ant-design/icons';
import { PageContainer } from '@ant-design/pro-layout';
import DeleteText from '@/components/DeleteText';
import ProTable from '@ant-design/pro-table';

const Companies = ({ dispatch, loading, groupQuotaApplication: { list, filter, pagination } }) => {
  const ref = useRef();
  const formRef = useRef();

  const query = (param = {}) => {
    dispatch({
      type: 'groupQuotaApplication/query',
      payload: param,
    });
  };

  useEffect(() => {
    query(filter);
    // getLimitType();
  }, []);

  const addComponents = () => {
    history.push({
      pathname: '/credit/Creditgroup/GroupQuotaApplication/createForm',
    });
  };

  const updataComponents = (res) => {
    history.push({
      pathname: '/credit/Creditgroup/GroupQuotaApplication/updataForm',
      query: {
        id: res.id,
      },
    });
  };

  const deleteMsg = () => {};

  const detailPage = () => {};

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
      <Menu.Item key="sp">
        <a target="_blank">审批/撤销</a>
      </Menu.Item>
    </Menu>
  );

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/insurance/checklist/export', filter);
      }}
    >
      导出
    </Button>,
    <Button
      key="add"
      type="primary"
      onClick={() => {
        addComponents();
      }}
    >
      新增
    </Button>,
  ];

  const columns = [
    {
      title: '申请单号',
      dataIndex: 'applicationNo',
      ellipsis: true,
      key: 'applicationNo',
      width: 150,
    },
    {
      title: '信用组编码',
      dataIndex: 'groupCode',
      ellipsis: true,
      key: 'groupName',
      width: 150,
    },
    {
      title: '信用组名称',
      dataIndex: 'groupName',
      ellipsis: true,
      key: 'groupName',
      width: 200,
    },
    {
      title: '申请账期',
      dataIndex: 'applyPayterm',
      search: false,
      key: 'applyPayterm',
      width: 120,
    },
    {
      title: '申请额度',
      dataIndex: 'applyLimit',
      key: 'applyLimit',
      search: false,
      align: 'right',
      width: 120,
    },
    {
      title: '生效时间',
      search: false,
      dataIndex: 'validFrom',
      valueType: 'date',
      key: 'validFrom',
      width: 150,
    },
    {
      title: '到期时间',
      dataIndex: 'validTo',
      key: 'validTo',
      search: false,
      width: 150,
      valueType: 'date',
    },
    {
      title: '申请单状态',
      search: false,
      dataIndex: 'approveStatus',
      key: 'approveStatus',
      width: 150,
    },
    {
      title: '申请人姓名',
      dataIndex: 'applicant',
      key: 'applicant',
      width: 150,
    },
    {
      title: '申请时间',
      dataIndex: 'applyTime',
      key: 'applyTime',
      valueType: 'date',
      width: 150,
    },
    {
      title: '状态',
      dataIndex: 'status',
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
      title: '流程实例ID',
      dataIndex: 'instanceid',
      key: 'instanceid',
      search: false,
      width: 120,
    },
    {
      title: '创建人',
      dataIndex: 'createdBy',
      key: 'createdBy',
      search: false,
      width: 120,
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      key: 'createTime',
      search: false,
      valueType: 'date',
      width: 150,
    },
    {
      title: '更新人',
      dataIndex: 'updatedBy',
      key: 'updatedBy',
      search: false,
      width: 120,
    },
    {
      title: '更新时间',
      dataIndex: 'updataTime',
      key: 'updataTime',
      search: false,
      valueType: 'date',
      width: 150,
    },
    {
      title: '操作',
      dataIndex: 'action',
      width: 120,
      key: 'action',
      fixed: 'right',
      search: false,
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                updataComponents(record);
              }}
            >
              修改
            </a>
            <Divider type='vertical' />
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
        scroll={{ x: 2400 }}
      />
    </PageContainer>
  );
};

export default connect(({ groupQuotaApplication, loading }) => ({
  groupQuotaApplication,
  loading: loading.effects['groupQuotaApplication/query'],
}))(Companies);
