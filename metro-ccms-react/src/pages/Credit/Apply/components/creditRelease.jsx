import React, { useState } from 'react';
import { DownOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card';
import { Table } from 'antd';

const CreditRelease = () => {
  const [collapsed, setCollapsed] = useState(false);

  const columns = [
    {
      title: '发布额度',
      dataIndex: 'GRANTED_BY',
      align: 'center',
    },
    {
      title: '额度类型',
      dataIndex: 'GRANT_TIME',
      align: 'center',
    },
    {
      title: '发布账期',
      dataIndex: 'GRANT_STATUS',
      align: 'center',
    },
    {
      title: '生效日',
      dataIndex: 'DESC',
      align: 'center',
      valueType: 'date',
    },
    {
      title: '到期日',
      dataIndex: 'LIMIT',
      align: 'center',
      valueType: 'date',
    },
    {
      title: '发布人',
      dataIndex: 'LIMIT_TYPE',
      align: 'center',
    },
    {
      title: '发布时间',
      dataIndex: 'PAYMENT_TERM',
      align: 'center',
    },
    {
      title: '发布状态',
      dataIndex: 'VALID_FROM',
      align: 'center',
    },
    {
      title: '发布描述',
      dataIndex: 'VALID_TO',
      align: 'center',
    },
  ];

  return (
    <div id="CreditRelease">
      <ProCard
        title="授信发布"
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
        <Table columns={columns} rowKey="dataIndex" bordered align="center" pagination={false} />
      </ProCard>
    </div>
  );
};

export default CreditRelease;
