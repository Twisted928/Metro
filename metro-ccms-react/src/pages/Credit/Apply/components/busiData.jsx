import React, { useState } from 'react';
import { Table } from 'antd';
import { DownOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card'
import Styles from './style.less';

const BusiData = () => {
   const [collapsed, setCollapsed] = useState(false);

   const columns = [
      {
         title: '门店名称',
         dataIndex: 'IAR01',
         align: 'center',
         fixed: 'left',
      },
      {
         title: '卡号编码',
         dataIndex: 'IAR01',
         align: 'center',
         fixed: 'left',

      },
      {
         title: '卡号名称',
         dataIndex: 'IAR01',
         align: 'center',
         fixed: 'left',

      },
      {
         title: '1月应收',
         dataIndex: 'IAR01',
         align: 'center',

      },
      {
         title: '2月应收',
         dataIndex: 'IAR02',
         align: 'center',

      },
      {
         title: '3月应收',
         dataIndex: 'IAR03',
         align: 'center',

      },
      {
         title: '4月应收',
         dataIndex: 'IAR04',
         align: 'center',

      },
      {
         title: '5月应收',
         dataIndex: 'IAR05',
         align: 'center',

      },
      {
         title: '6月应收',
         dataIndex: 'IAR06',
         align: 'center',

      },
      {
         title: '7月应收',
         dataIndex: 'IAR07',
         align: 'center',

      },
      {
         title: '8月应收',
         dataIndex: 'IAR08',
         align: 'center',

      },
      {
         title: '9月应收',
         dataIndex: 'IAR09',
         align: 'center',

      },
      {
         title: '10月应收',
         dataIndex: 'IAR10',
         align: 'center',

      },
      {
         title: '11月应收',
         dataIndex: 'IAR11',
         align: 'center',

      },
      {
         title: '12月应收',
         dataIndex: 'IAR12',
         align: 'center',

      },
      {
         title: '12个月总计',
         dataIndex: 'IAR12',
         align: 'center',
         fixed: 'right',

      },
   ];

   return (
      <div id="BusiData">
         <ProCard
            title="业务数据"
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
            <div><h3 className={Styles.formLabel}> 动态加载</h3></div>
            <Table
               columns={columns}
               rowKey="id"
               className={Styles.tableMargin}
               bordered
               scroll={{ x: 2000 }}
               />
         </ProCard>
      </div>
   );
};

export default BusiData;
