import React, { useState, useRef } from 'react';
import { Modal, message } from 'antd';
import { history } from 'umi';
import ProTable from '@ant-design/pro-table';
import { queryCust } from '../service';

const PreSelectcust = ({ visible, onClose }) => {
  const formRef = useRef();
  const actionRef = useRef();
  const [thisRow, setRow] = useState([]);

  const onFinish = () => {
    if (thisRow.length !== 0) {
      history.push({
        pathname: '/customer/whitelist/list/create',
        query: {
          id: thisRow[0].id,
          custcode: thisRow[0].custcode,
          custname: thisRow[0].custname,
        },
      });
    } else {
      message.info({ content: '请选择需要加入白名单的客户!', duration: 2 });
    }
  };

  const columnsCust = [
    {
      title: '客户编码',
      width: '25%',
      dataIndex: 'custcode',
    },
    {
      title: '客户名称',
      width: '25%',
      ellipsis: true,
      dataIndex: 'custname',
    },
    {
      title: '工商代码',
      width: '25%',
      dataIndex: 'creditnoccms',
    },
    {
      title: '是否黑名单',
      width: '25%',
      dataIndex: 'ifblack',
      valueEnum: {
        0: {
          text: '否',
        },
        1: {
          text: '是',
        },
      },
    },
  ];

  const rowSelection = {
    onChange: (selectedRowKeys, selectedRows) => {
      setRow(selectedRows);
      // eslint-disable-next-line no-console
      console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
    },
  };

  return (
    <Modal
      width={1000}
      destroyOnClose
      title="客户选择"
      visible={visible}
      onOk={onFinish}
      onCancel={() => {
        onClose();
      }}
    >
      <ProTable
        rowKey="id"
        options={false}
        dateFormatter="string"
        actionRef={actionRef}
        formRef={formRef}
        columns={columnsCust}
        tableAlertRender={false}
        request={async (params) => {
          const msg = await queryCust({
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
        scroll={{ y: 300 }}
        pagination={{
          pageSize: 10,
        }}
        rowSelection={{
          type: 'radio',
          ...rowSelection,
        }}
      />
    </Modal>
  );
};

export default PreSelectcust;
