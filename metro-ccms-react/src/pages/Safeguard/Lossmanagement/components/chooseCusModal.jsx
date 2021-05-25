import React, { useEffect } from 'react';
import { Modal } from 'antd';
import moment from 'moment';
import ProTable from '@ant-design/pro-table';
import { history, connect } from 'umi';

const ChooseCusModal = ({
  dispatch,
  loading,
  lossmanagement: { cusData, modalPagination },
  vis,
  onCancel,
}) => {
  const handleOk = async () => {};

  const handleCancel = () => {
    onCancel(false);
  };

  const query = (param = {}) => {
    if (!vis) {
      return;
    }
    dispatch({
      type: 'lossmanagement/insuredQuery',
      payload: param,
    });
  };

  useEffect(() => {
    query();
  }, [vis]);

  const chooseCus = (res) => {
    history.push({
      pathname: '/safeguard/Lossmanagement/createForm',
      query: {
        id: res.id,
      },
    });
  };

  const columns = [
    {
      title: '买方代码',
      dataIndex: 'buyerno',
      key: 'buyerno',
      ellipsis: true,
      width: 130,
      search: false,
    },
    {
      title: '客户编码',
      dataIndex: 'custCode',
      key: 'custCode',
      width: 130,
      ellipsis: true,
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      key: 'custName',
      width: 130,
      ellipsis: true,
    },
    {
      title: '保险公司编码',
      dataIndex: 'compCode',
      ellipsis: true,
      width: 150,
      key: 'compCode',
      search: false,
    },
    {
      title: '汇总发票号',
      dataIndex: 'invoiceNo',
      ellipsis: true,
      width: 130,
      key: 'invoiceNo',
      search: false,
    },
    {
      title: '汇总发票金额（元）',
      dataIndex: 'invoicesum',
      ellipsis: true,
      align: 'right',
      width: 200,
      key: 'invoicesum',
      search: false,
    },
    {
      title: '汇总发票日期',
      dataIndex: 'invoicedate',
      ellipsis: true,
      key: 'invoicedate',
      width: 150,
      search: false,
      render: (_, record) => {
        return record.invoicedate ? moment(record.invoicedate).format('YYYY-MM-DD') : '-';
      },
    },
    {
      title: '投保金额（元）',
      dataIndex: 'insuresum',
      ellipsis: true,
      width: 150,
      align: 'right',
      key: 'insuresum',
      search: false,
    },
    {
      title: '操作',
      width: 130,
      dataIndex: 'action',
      key: 'action',
      fixed:'right',
      search: false,
      render: (_, record) => {
        return (
          <a
            onClick={() => {
              chooseCus(record);
            }}
          >
            选择
          </a>
        );
      },
    },
  ];

  return (
    <Modal title="选择客户" width={1200} visible={vis} onOk={handleOk} onCancel={handleCancel}>
      <ProTable
        headerTitle="查询列表"
        rowKey="id"
        onSubmit={(values) => {
          query({ ...values });
        }}
        onChange={(page) => {
          query({ ...page });
        }}
        dataSource={cusData}
        tableAlertRender={false}
        pagination={modalPagination}
        loading={loading}
        options={false}
        toolBarRender={false}
        columns={columns}
        scroll={{ x: 1300 }}
      />
    </Modal>
  );
};

export default connect(({ lossmanagement, loading }) => ({
  lossmanagement,
  loading: loading.effects['lossmanagement/insuredQuery'],
}))(ChooseCusModal);
