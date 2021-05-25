import React, { useRef } from 'react';
import { Modal } from 'antd';
import ProTable from '@ant-design/pro-table';
import { queryListHis } from '../service';

// 白名单历史
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
      ellipsis: true,
      align: 'center',
    },
    {
      title: '生效时间',
      dataIndex: 'validfrom',
      search: false,
      valueType: 'date',
      align: 'center',
    },
    {
      title: '到期时间',
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
          status: 'Error',
        },
        1: {
          text: '有效',
          status: 'Success',
        },
      },
    },
  ];

  return (
    <Modal
      destroyOnClose
      visible={visible}
      footer={[]}
      onCancel={() => {
        onClose();
      }}
      title="历史记录"
      width="1000"
    >
      <ProTable
        rowKey="id"
        search={false}
        options={false}
        actionRef={actionRef}
        formRef={formRef}
        columns={columns}
        params={payload}
        request={async (params) => {
          const msg = await queryListHis({
            ...params,
            page: params.current,
            pageSize: params.pageSize,
          });
          return {
            success: true,
            data: msg?.rows,
            total: msg?.total,
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
