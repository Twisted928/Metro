import React, { useEffect, useRef, Fragment, useState } from 'react';
import { Button, Divider, Menu, Dropdown } from 'antd';
import { connect, history } from 'umi';
import { download } from '@/services/commom';
import { DownOutlined } from '@ant-design/icons';
import { PageContainer } from '@ant-design/pro-layout';
import DeleteText from '@/components/DeleteText';
import ProTable from '@ant-design/pro-table';

const GroupQuotaAllocation = ({
  dispatch,
  loading,
  groupQuotaAllocation: { list, filter, pagination },
}) => {
  const ref = useRef();
  const formRef = useRef();
  const [modalVis, setModalVis] = useState(false);

  const query = (param = {}) => {
    // dispatch({
    //   type: 'groupQuotaApplication/query',
    //   payload: param,
    // });
  };

  // 获取额度类型
  const getLimitType = () => {
    dispatch({
      type: 'commonmodel/basciData',
      payload: { ctype: 'LimitType' },
    });
  };

  useEffect(() => {
    query(filter);
    getLimitType();
  }, []);

  const addComponents = () => {
    history.push({
      pathname: '/credit/Creditgroup/GroupQuotaAllocation/createForm',
    });
  };

  const updataComponents = (res) => {
    history.push({
      pathname: '/credit/Creditgroup/GroupQuotaAllocation/updataForm',
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
      <Menu.Item key="sp">
        <a target="_blank">发布/重新发布</a>
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
      title: '信用组编码',
      dataIndex: 'groupCode',
      ellipsis: true,
      key: 'groupCode',
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
      title: '额度类型',
      dataIndex: 'limitType',
      search: false,
      key: 'limitType',
      width: 120,
    },
    {
      title: '信用组账期',
      dataIndex: 'groupPayterm',
      key: 'groupPayterm',
      search: false,
      align: 'right',
      width: 120,
    },
    {
      title: '信用组额度',
      dataIndex: 'groupLimit',
      ellipsis: true,
      search: false,
      key: 'groupLimit',
      align: 'right',
      width: 120,
    },
    {
      title: '生效时间',
      search: false,
      dataIndex: 'validFrom',
      key: 'validFrom',
      width: 150,
    },
    {
      title: '到期时间',
      dataIndex: 'validTo',
      key: 'validTo',
      width: 150,
      valueType: 'dateRange',
      render: (_, record) => {
        return record.validTo || '-';
      },
    },
    {
      title: '门店编码',
      dataIndex: 'storeCode',
      key: 'storeCode',
      width: 150,
    },
    {
      title: '卡号编码',
      dataIndex: 'cardCode',
      key: 'cardCode',
      width: 150,
    },
    {
      title: '卡号名称',
      dataIndex: 'cardName',
      key: 'cardName',
      width: 150,
    },
    {
      title: '发布账期',
      dataIndex: 'paymentTerm',
      key: 'paymentTerm',
      search: false,
      align: 'right',
      width: 120,
    },
    {
      title: '发布额度',
      dataIndex: 'limit',
      key: 'limit',
      search: false,
      align: 'right',
      width: 100,
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
        scroll={{ x: 1810 }}
      />
    </PageContainer>
  );
};

export default connect(({ groupQuotaAllocation, commonmodel, loading }) => ({
  groupQuotaAllocation,
  commonmodel,
  loading: loading.effects['groupQuotaAllocation/query'],
}))(GroupQuotaAllocation);
