import React, { useRef } from 'react';
import { Modal } from 'antd';
import { connect } from 'umi';
import ProTable from '@ant-design/pro-table';
import { queryHistory } from '../service';

// 睡眠客户历史
const ShowHistory = ({ visible, onClose, payload }) => {
  const formRef = useRef();
  const actionRef = useRef();

  const columns = [
    {
      title: '客户编码',
      dataIndex: 'custcode',
      align: 'center',
      // search: false,
    },
    {
      title: '客户名称',
      dataIndex: 'custname',
      align: 'center',
      // search: false,
    },
    {
      title: '睡眠开始时间',
      dataIndex: 'validfrom',
      valueType: 'date',
      align: 'center',
      // search: false,
    },
    {
      title: '再次交易时间',
      dataIndex: 'validto',
      valueType: 'date',
      align: 'center',
      // search: false,
    },
    {
      title: '状态',
      dataIndex: 'status',
      valueType: 'select',
      search: false,
      align: 'center',
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
    {
      title: '原因',
      dataIndex: 'reason',
      search: false,
      align: 'center',
    },
    {
      title: '创建人',
      dataIndex: 'createdby',
      search: false,
      align: 'center',
    },
    {
      title: '创建时间',
      dataIndex: 'createtime',
      valueType: 'date',
      search: false,
      align: 'center',
    },
  ];

  return (
    <Modal
      destroyOnClose
      visible={visible}
      onCancel={() => {
        onClose();
      }}
      footer={null}
      title="历史记录"
      width="1000"
    >
      <ProTable
        rowKey="id"
        dateFormatter="string"
        bordered
        options={false}
        search={false}
        actionRef={actionRef}
        formRef={formRef}
        columns={columns}
        params={payload}
        pagination={{
          pageSize: 10,
        }}
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
      />
    </Modal>
  );
};

export default connect(({ sleepcust, loading }) => ({
  sleepcust,
  loading: loading.effects['sleepcust/queryHistory'],
}))(ShowHistory);
