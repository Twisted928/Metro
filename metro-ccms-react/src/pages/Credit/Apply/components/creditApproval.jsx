import React, { useState } from 'react';
import { DownOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card';
import { Table } from 'antd';

const CreditApproval = () => {
  const [collapsed, setCollapsed] = useState(false);

  const columns = [
    {
      title: '审批人',
      dataIndex: 'approvedby',
      align: 'center',
      fixed: 'left',
    },
    {
      title: '角色',
      dataIndex: 'rolename',
      align: 'center',
      fixed: 'left',
    },
    {
      title: '审批时间',
      dataIndex: 'approvetime',
      align: 'center',
      fixed: 'left',
    },
    {
      title: '额度类型',
      dataIndex: 'limittype',
      align: 'center',
    },
    {
      title: '审批账期',
      dataIndex: 'approvalpayterm',
      align: 'center',
    },
    {
      title: '审批额度',
      dataIndex: 'approvallimit',
      align: 'center',
    },
    {
      title: '生效日',
      dataIndex: 'validfrom',
      valueType: 'date',
      align: 'center',
    },
    {
      title: '到期日',
      dataIndex: 'VALID_TO',
      valueType: 'date',
      align: 'center',
    },
    {
      title: '审批状态',
      dataIndex: 'APPROVE_STATUS',
      align: 'center',
    },
    {
      title: '审批意见',
      dataIndex: 'REMARK',
      align: 'center',
    },
    {
      title: '是否特批',
      dataIndex: 'IF_SPECIAL',
      align: 'center',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '否',
        },
        1: {
          text: '否',
        },
      },
    },
  ];

  return (
    <div id="CreditApproval">
      <ProCard
        title="授信审批"
        extra={
          <DownOutlined
            rotate={collapsed ? undefined : 180}
            onClick={() => {
              setCollapsed(!collapsed);
            }}
          />
        }
        collapsed={collapsed}
        bordered
        headerBordered
      >
        <Table
          columns={columns}
          rowKey="dataIndex"
          bordered
          align="center"
          pagination={false}
          scroll={{ x: 1500 }}
        />
      </ProCard>
    </div>
  );
};

export default CreditApproval;
