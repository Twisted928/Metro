import React, { useEffect, Fragment } from 'react';
import { Button, Divider, Menu, Dropdown } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { connect, history } from 'umi';
import { DownOutlined } from '@ant-design/icons';
import ProTable from '@ant-design/pro-table';

const Contract = ({ dispatch, loading, contract: { list, pagination } }) => {
  const query = (param={}) => {
    dispatch({
      type:'contract/query',
      payload: param
    })
  };

  useEffect(() => {
    query();
  },[])

  const toolBarRender = () => [<Button key="export">导出</Button>];

  const updataMsg = () => {
    history.push('/contract/contractManage/updataFrom');
  };

  const review = () => {
    history.push('/contract/contractManage/review');
  };

  const menu = (res) => (
    <Menu>
      <Menu.Item key="del">
        <a
          onClick={() => {
            review(res);
          }}
          target="_blank"
        >
          特批
        </a>
      </Menu.Item>
      <Menu.Item key="det">
        <a
          onClick={() => {
            review(res);
          }}
          target="_blank"
        >
          复核
        </a>
      </Menu.Item>
    </Menu>
  );

  const columns = [
    {
      title: '合同编码',
      dataIndex: 'contractNo',
      key: 'contractNo',
      width: 130,
    },
    {
      title: '合同名称',
      dataIndex: 'contractName',
      search: false,
      key: 'custName',
      ellipsis: true,
      width: 200,
    },
    {
      title: '客户编码',
      dataIndex: 'custCode',
      key: 'custCode',
      width: 130,
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      key: 'custName',
      ellipsis: true,
      width: 200,
    },
    {
      title: '门店编码',
      dataIndex: 'storeName',
      search: false,
      key: 'storeName',
      width: 130,
    },
    {
      title: '卡号编码',
      search: false,
      dataIndex: 'cardCode',
      key: 'status',
      width: 130,
    },
    {
      title: '卡号名称',
      dataIndex: 'cardName',
      key: 'cardName',
      search: false,
      ellipsis: true,
      width: 200,
    },
    {
      title: '合同类型',
      dataIndex: 'contractType',
      key: 'contractType',
      search: false,
      width: 130,
    },
    {
      title: '合同类型描述',
      dataIndex: 'contractDesc',
      key: 'contractDesc',
      search: false,
      width: 200,
    },
    {
      title: '合同目标交易额',
      dataIndex: 'salesTarget',
      key: 'salesTarget',
      search: false,
      align: 'right',
      width: 150,
    },
    {
      title: '币种',
      dataIndex: 'currency',
      key: 'currency',
      search: false,
      width: 100,
    },
    {
      title: '合同账期',
      dataIndex: 'contractDays',
      key: 'contractDays',
      search: false,
      align: 'right',
      width: 100,
    },
    {
      title: '生效日',
      dataIndex: 'validFrom',
      key: 'validFrom',
      search: false,
      width: 130,
    },
    {
      title: '到期日',
      dataIndex: 'validTo',
      key: 'validTo',
      search: false,
      width: 130,
    },
    {
      title: '递延到期日',
      dataIndex: 'delayDate',
      key: 'delayDate',
      search: false,
      width: 130,
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
      width: 130,
    },
    {
      title: '申请单号',
      dataIndex: 'applicationNo',
      key: 'applicationNo',
      search: false,
      width: 130,
    },
    {
      title: '审批状态',
      dataIndex: 'approveStatus',
      key: 'approveStatus',
      width: 100,
      fixed: 'right',
      valueEnum: {
        all: {
          text: '全部',
        },
        1: {
          text: '审批中',
        },
        0: {
          text: '审批退回',
        },
        2: {
          text: '审批通过',
        },
        3: {
          text: '已撤销',
        },
      },
    },
    {
      title: '是否特批',
      dataIndex: 'ifSpecial',
      key: 'ifSpecial',
      width: 100,
      fixed: 'right',
      valueEnum: {
        all: {
          text: '全部',
        },
        1: {
          text: '是',
        },
        0: {
          text: '否',
        },
      },
    },
    {
      title: '操作',
      dataIndex: 'action',
      key: 'action',
      width: 120,
      search: false,
      fixed: 'right',
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
        dataSource={list}
        pagination={pagination}
        loading={loading}
        options={false}
        toolBarRender={toolBarRender}
        columns={columns}
        scroll={{ x: 2950 }}
      />
    </PageContainer>
  );
};

export default connect(({ contract, loading }) => ({
  contract,
  loading: loading.effects['contract/query'],
}))(Contract);
