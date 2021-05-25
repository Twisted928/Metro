/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect, useState } from 'react';
import { Modal, Button, message, Popconfirm } from 'antd';
import { connect } from 'umi';
import { insert } from '../service';
import ProTable from '@ant-design/pro-table';

const AddModal = ({
  dispatch,
  vis,
  onCancel,
  loadingadd,
  collectionManagement: { addModalList, modalPagination },
}) => {
  const [formValue, setFormValue] = useState(null);
  const [addsLoading, setAddsLoading] = useState(false);

  const query = (params = {}) => {
    dispatch({
      type: 'collectionManagement/addModal',
      payload: params,
    });
  };

  useEffect(() => {
    query();
  }, []);

  const handleOk = async () => {
    setAddsLoading(true);
    if (!formValue) {
      setAddsLoading(false);
      onCancel(false);
      return;
    }
    const response = await insert(formValue);
    const { code, msg } = response;
    if (code === 500) {
      message.error(msg);
      setAddsLoading(false);
    }
    if (code === 200) {
      onCancel(false);
      setAddsLoading(false);
    }
  };

  const columns = [
    {
      title: '门店编码',
      dataIndex: 'storeId',
      key: 'storeId',
      width: 150,
      formItemProps: {
        rules: [
          {
            required: true,
            message: '门店编码不能为空！',
          },
        ],
      },
    },
    {
      title: '卡号编码',
      dataIndex: 'customerId',
      key: 'customerId',
      width: 150,
      formItemProps: {
        rules: [
          {
            required: true,
            message: '卡号编码不能为空！',
          },
        ],
      },
    },
    {
      title: '卡号名称',
      dataIndex: 'cardName',
      key: 'cardName',
      search: false,
      width: 150,
      ellipsis: true,
    },
    {
      title: '部门编码',
      dataIndex: 'departCode',
      key: 'departCode',
      search: false,
      width: 130,
    },
    {
      title: '部门名称',
      dataIndex: 'deptName',
      key: 'deptName',
      search: false,
      width: 120,
    },
    {
      title: '发票号',
      dataIndex: 'belnr',
      key: 'belnr',
      search: false,
      width: 130,
    },
    {
      title: '客户编码',
      dataIndex: 'custCode',
      key: 'custCode',
      search: false,
      width: 130,
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      key: 'custName',
      width: 150,
      search: false,
      ellipsis: true,
    },
    {
      title: '应收账款（元）',
      dataIndex: 'wrbtr',
      search: false,
      key: 'wrbtr',
      align: 'right',
      width: 150,
    },
    {
      title: '催收日期',
      dataIndex: 'zaldt',
      key: 'zaldt',
      width: 130,
      valueType: 'date',
      formItemProps: {
        rules: [
          {
            required: true,
            message: '付款日期不能为空！',
          },
        ],
      },
    },
    {
      title: '付款条件',
      dataIndex: 'zterm',
      key: 'zterm',
      search: false,
      width: 120,
    },
    {
      title: '本币货币',
      dataIndex: 'lWaers',
      key: 'lWaers',
      width: 120,
      search: false,
    },
    {
      title: '明细发票日期',
      dataIndex: 'budat',
      key: 'budat',
      search: false,
      valueType: 'date',
      width: 130,
    },
  ];

  const text = addModalList.length
    ? '确认将所有查询数据全部保存？'
    : '当前没有查询数据,点击确认直接关闭弹窗！';

  return (
    <Modal
      title="新增"
      width={1100}
      visible={vis}
      onCancel={() => {
        onCancel(false);
      }}
      footer={[
        <Button
          key="back"
          onClick={() => {
            onCancel(false);
          }}
        >
          关闭
        </Button>,
        <Popconfirm
          placement="topRight"
          title={text}
          onConfirm={handleOk}
          okText="确认"
          cancelText="取消"
        >
          <Button key="submit" loading={addsLoading} type="primary">
            生成单据
          </Button>
        </Popconfirm>,
      ]}
    >
      <ProTable
        headerTitle=""
        rowKey="id"
        loading={loadingadd}
        pagination={modalPagination}
        dataSource={addModalList}
        options={false}
        columns={columns}
        scroll={{ x: 1800, y: 300 }}
        onSubmit={(value) => {
          setFormValue(value);
          query({ ...value });
        }}
        form={{
          ignoreRules: false,
        }}
        search={{
          collapsed: false,
          collapseRender: false,
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
      />
    </Modal>
  );
};

export default connect(({ collectionManagement, loading }) => ({
  collectionManagement,
  loadingadd: loading.effects['collectionManagement/addModal'],
}))(AddModal);
