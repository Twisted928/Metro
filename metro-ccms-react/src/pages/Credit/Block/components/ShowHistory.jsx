import React, { useRef } from 'react';
import { Modal } from 'antd';
// import { history, request } from 'umi';
import ProTable from '@ant-design/pro-table';

const ShowHistory = ({ visible, onClose }) => {
  const actionRef = useRef();
  const formRef = useRef();

  const columns = [
    {
      title: '卡号名称',
      dataIndex: 'CARD_NAME',
      // search: false,
      editable: false,
    },
    {
      title: '卡号编码',
      dataIndex: 'CARD_CODE',
      hideInTable: true,
      // search: false,
      editable: false,
    },
    {
      title: '部门名称',
      dataIndex: 'DEPART_NAME',
      search: false,
      editable: false,
    },
    {
      title: '门店编码',
      dataIndex: 'STORE_CODE',
      search: false,
      editable: false,
    },
    {
      title: '客户编码',
      dataIndex: 'CUST_CODE',
      // search:false,
      editable: false,
    },
    {
      title: '客户名称',
      dataIndex: 'CUST_NAME',
      // search:false,
      editable: false,
    },
    {
      title: '客户组名称',
      dataIndex: 'CUSTGR _NAME',
      search: false,
      editable: false,
    },
    {
      title: '信用组名称',
      dataIndex: 'GROUP_NAME',
      search: false,
      editable: false,
    },
    {
      title: '冻结原因',
      dataIndex: 'FREEZE_REASON',
      search: false,
      formItemProps: {
        rules: [
          {
            required: true,
            message: '此项为必填项',
          },
        ],
      },
    },
    {
      title: '信用冻结标记',
      dataIndex: 'CREDIT_BLOCK',
      valueType: 'select',
      // search:false,
      valueEnum: {
        0: {
          text: '解冻',
          status: '0',
        },
        1: {
          text: '冻结',
          status: '1',
        },
        2: {
          text: '全部',
          status: '1',
        },
      },
    },
    {
      title: '创建人',
      dataIndex: 'CREATED_BY',
      search: false,
      editable: false,
    },
    {
      title: '创建时间',
      dataIndex: 'CREATE_TIME',
      search: false,
      editable: false,
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
        search={false}
        options={false}
        dateFormatter='string'
        formRef={formRef}
        actionRef={actionRef}
        pagination={{
          pageSize: 5
        }}
        // params={payload}
        // request={async () =>
        //   request('/credit/management/listlishi', {
        //     params: {
        //       custcode: payload,
        //     },
        //   }).then((response) => {
        //     return { ...response, page: response.current, data: response.rows };
        //   })
        // }
        columns={columns}
        scroll={{ x: 3600 }}
      />
    </Modal>
  );
};

export default ShowHistory;
