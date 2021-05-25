import React, { useEffect } from 'react';
import { Modal, Button } from 'antd';
import { connect } from 'umi';
import moment from 'moment';
import ProTable from '@ant-design/pro-table';

const ReProcessModal = ({
  dispatch,
  loading,
  actModal: { historydata },
  rowMkey,
  visible,
  onCancel,
}) => {
  const query = () => {
    if (!rowMkey) {
      return;
    }
    dispatch({
      type: 'actModal/historyList',
      payload: { key: rowMkey },
    });
  };

  useEffect(() => {
    query();
  }, [rowMkey]);

  const columns = [
    {
      title: '部署ID',
      dataIndex: 'deploymentId',
      key: 'deploymentId',
      width: 200,
    },
    {
      title: '部署人',
      dataIndex: 'tenantId',
      key: 'tenantId',
      width: 200,
    },
    {
      title: '部署时间',
      dataIndex: 'deploymentTime',
      key: 'deploymentTime',
      width: 200,
      render: (_, record) => {
        return record.deploymentTime
          ? moment(record.deploymentTime).format('YYYY-MM-DD hh:mm:ss')
          : '';
      },
    },
  ];

  const handleCancel = () => {
    onCancel(false);
  };

  return (
    <Modal
      visible={visible}
      title="发布流程"
      onCancel={() => {
        handleCancel();
      }}
      width={700}
      footer={[
        <Button
          key="back"
          onClick={() => {
            handleCancel();
          }}
        >
          返回
        </Button>,
      ]}
    >
      <ProTable
        headerTitle=""
        rowKey="deploymentId"
        bordered
        dataSource={historydata}
        search={false}
        options={false}
        loading={loading}
        toolBarRender={false}
        pagination={false}
        columns={columns}
        scroll={{ y: 500 }}
      />
    </Modal>
  );
};

export default connect(({ actModal, loading }) => ({
  actModal,
  loading: loading.effects['actModal/historyList'],
}))(ReProcessModal);
