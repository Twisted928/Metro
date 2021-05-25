import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'umi';
import ProTable from '@ant-design/pro-table';
import { PageContainer } from '@ant-design/pro-layout';
import ShowDatails from './components/ShowDetails';

// 预警邮件
const WarningMail = ({ dispatch, warningmail: { list, filter, pagination }, loading }) => {
  const formRef = useRef();
  const actionRef = useRef();
  // 控制详情弹窗
  const [showDetails, setDetails] = useState(false);
  // 详情弹窗传值
  const [details, setDetailsParams] = useState([]);

  const query = (param = {}) => {
    dispatch({
      type: 'warningmail/query',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  // 详情
  const ShowDetails = (record) => {
    setDetailsParams(record);
    setDetails(true);
  };

  const columns = [
    {
      title: '标题',
      dataIndex: 'title',
      // search: false,
    },
    {
      title: '邮件内容',
      dataIndex: 'text',
      search: false,
    },
    {
      title: '收件人',
      dataIndex: 'username',
      // search: false,
    },
    {
      title: '收件角色',
      dataIndex: 'rolename',
      // search: false,
    },
    {
      title: '来源',
      dataIndex: 'source',
      search: false,
    },
    {
      title: '申请单号',
      dataIndex: 'applicationno',
      search: false,
    },
    {
      title: '发送日期',
      // search: false,
      valueType: 'date',
      dataIndex: 'ddate',
    },
    {
      title: '预警模型编码',
      search: false,
      dataIndex: 'modcode',
    },
    {
      title: '操作',
      valueType: 'option',
      fixed: 'right',
      render: (_, record) => [
        <a ket="edit" onClick={() => ShowDetails(record)}>
          详情
        </a>,
      ],
    },
  ];

  // const toolBarRender = () => [
  //   <Button
  //     key="export"
  //     onClick={() => {
  //       download('/customer/monitoringcustomers/export', filter);
  //     }}
  //   >
  //     导出
  //   </Button>,
  // ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="id"
        headerTitle="查询列表"
        options={false}
        actionRef={actionRef}
        formRef={formRef}
        columns={columns}
        pagination={pagination}
        loading={loading}
        dataSource={list}
        onSubmit={(value) => {
          query({
            ...value,
          });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
        // toolBarRender={toolBarRender}
        search={{ span: 8, labelWidth: 70, }}
      />

      {/* 详情弹窗 */}
      <ShowDatails
        visible={showDetails}
        destroyOnClose
        payload={details}
        onClose={() => {
          setDetails(false);
          setDetailsParams([]);
        }}
      />
    </PageContainer>
  );
};

export default connect(({ warningmail, loading }) => ({
  warningmail,
  loading: loading.effects['warningmail/query'],
}))(WarningMail);
