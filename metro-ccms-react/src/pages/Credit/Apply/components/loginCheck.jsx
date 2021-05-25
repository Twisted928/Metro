import React, { useState, useRef } from 'react';
import { DownOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card'
import { Table } from 'antd';


const LoginCheck = () => {
   const [collapsed, setCollapsed] = useState(false);
   const actionRef = useRef();
   const formRef = useRef();

   const columns = [
      {
         align: 'center',
         title: '状态',
         dataIndex: 'status',
         valueType: 'select',
         valueEnum: {
            0: {
               text: '驳回',
               status: 'Error',
            },
            1: {
               text: '通过',
               status: 'Success',
            },
            2: {
               text: '特批中',
               status: 'Default',
            },
         }
      },
      {
         title: '是否特批',
         dataIndex: 'ifspecial',
         align: 'center',
      },
      {
         title: '时间',
         dataIndex: 'timestamp',
         align: 'center',
      },
      {
         title: '指标状态',
         dataIndex: 'status',
         align: 'center',
      },
      {
         title: '指标状态',
         dataIndex: 'status',
         align: 'center',
      },
      {
         title: '指标状态',
         dataIndex: 'status',
         align: 'center',
      },
      {
         title: '指标状态',
         dataIndex: 'status',
         align: 'center',
      },
      {
         title: '状态描述',
         dataIndex: 'desc',
         align: 'center',
      },
   ]

   return (
      <div id="LoginCheck">
         <ProCard
            title="准入校验"
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
               bordered
               actionRef={actionRef}
               formRef={formRef}
            // dataSource={list}
            // pagination={pagination}
            // loading={loading}
            />
         </ProCard>
      </div>
   );
}

export default LoginCheck;
