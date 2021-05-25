/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect } from 'react';
import { Modal } from 'antd';
import { connect } from 'umi';
import ProTable from '@ant-design/pro-table';

const BasicForm = ({
  dispatch,
  visible,
  cancel,
  loadingList,
  mocdelCode,
  warningmodel: { getEwModelRecord },
}) => {
  const query = () => {
    if (!mocdelCode) {
      return;
    }
    dispatch({
      type: 'warningmodel/statusList',
      payload: { mocdelCode },
    });
  };

  useEffect(() => {
    query();
  }, [mocdelCode]);

  const columns = [
    {
      title: '模型编码',
      dataIndex: 'mocdelCode',
      width: 150,
    },
    {
      title: '模型名称',
      fixed: 'left',
      dataIndex: 'mocdelName',
      // search: false,
      width: 200,
      ellipsis: true,
    },
    {
      title: '描述',
      dataIndex: 'remark',
      width: 200,
      ellipsis: true,
      search: false,
    },
    {
      title: '状态',
      width: 100,
      dataIndex: 'status',
      valueEnum: {
        0: {
          text: '停用',
          status: 'Error',
        },
        1: {
          text: '启用',
          status: 'Success',
        },
      },
    },
    {
      title: '启用/停用人',
      width: 120,
      dataIndex: 'createdBy',
      search: false,
    },
    {
      title: '启用/停用时间',
      width: 180,
      dataIndex: 'createTime',
      search: false,
    },
  ];

  return (
    <Modal
      width={1000}
      destroyOnClose
      title="状态记录"
      visible={visible}
      onCancel={() => {
        cancel(false);
      }}
      footer={null}
    >
      <ProTable
        rowKey="id"
        headerTitle=""
        options={false}
        columns={columns}
        pagination={false}
        loading={loadingList}
        dataSource={getEwModelRecord ?? []}
        toolBarRender={false}
        search={false}
      />
    </Modal>
  );
};

export default connect(({ warningmodel, loading }) => ({
  warningmodel,
  loadingList: loading.effects['warningmodel/statusList'],
}))(BasicForm);
