import React, { useRef } from 'react';
import { Modal } from 'antd';
import ProTable from '@ant-design/pro-table';
import { queryHistory } from '../service';

// 黑名单历史
const ShowHistory = ({ visible, onClose, payload }) => {
  const formRef = useRef();
  const actionRef = useRef();

  const columns = [
    {
      title: '客户编码',
      dataIndex: 'custcode',
      // search: false,
      align: 'center',
    },
    {
      title: '客户名称',
      dataIndex: 'custname',
      // search: false,
      align: 'center',
    },
    {
      title: '原因',
      dataIndex: 'reason',
      search: false,
      align: 'center',
    },
    {
      title: '生效日',
      dataIndex: 'validfrom',
      search: false,
      valueType: 'date',
      align: 'center',
    },
    {
      title: '到期日',
      dataIndex: 'validto',
      search: false,
      valueType: 'date',
    },
    {
      title: '创建人',
      dataIndex: 'createdby',
      align: 'center',
    },
    {
      title: '创建时间',
      dataIndex: 'createtime',
      search: false,
      valueType: 'date',
      align: 'center',
    },
    {
      title: '状态',
      dataIndex: 'status',
      align: 'center',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '无效',
          status: 'error',
        },
        1: {
          text: '有效',
          status: 'success',
        },
      },
    },
  ];

  return (
    <Modal
      destroyOnClose
      visible={visible}
      footer={null}
      onCancel={() => {
        onClose();
      }}
      title="历史记录"
      width="1000"
    >
      <ProTable
        rowKey="id"
        bordered
        dateFormatter="string"
        search={false}
        options={false}
        actionRef={actionRef}
        formRef={formRef}
        columns={columns}
        params={payload}
        request={async (params) => {
          const res = await queryHistory({
            ...params,
            page: params.current,
            pageSize: params.pageSize,
          });
          return {
            data: res?.rows,
            total: res?.total,
            success: true,
          };
        }}
        pagination={{
          pageSize: 10,
        }}
      />
    </Modal>
  );
};

export default ShowHistory;
