import React, { useEffect, useRef } from 'react';
import { connect, history } from 'umi';
import ProTable from '@ant-design/pro-table';
import { PageContainer } from '@ant-design/pro-layout';

// 合同模板预览
const TemplateView = ({ dispatch, templateview: { list, pagination, filter }, loading }) => {
  const formRef = useRef();
  const actionRef = useRef();

  const query = (param = {}) => {
    dispatch({
      type: 'templateview/query',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const goPreview = () => {
    history.push({
      pathname: '/contract/templateManage/preview',
    });
  };

  const columns = [
    {
      title: '模板名称',
      dataIndex: 'contracttemp',
      // search: false,
    },
    {
      title: '创建人',
      dataIndex: 'createdby',
      search: false,
    },
    {
      title: '创建时间',
      dataIndex: 'createtime',
      valueType: 'date',
      search: false,
    },
    {
      title: '更新人',
      dataIndex: 'updatedby',
      search: false,
    },
    {
      title: '更新时间',
      dataIndex: 'updatetime',
      valueType: 'date',
      search: false,
    },
    {
      title: '状态',
      dataIndex: 'status',
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
    {
      title: '合同文本',
      dataIndex: 'contracttext',
      search: false,
    },
    {
      title: '合同类型',
      dataIndex: 'contracttype',
      search: false,
    },
    {
      title: '操作',
      valueType: 'option',
      fixed: 'right',
      render: () => [
        <a key="editable" onClick={goPreview}>
          预览
        </a>,
      ],
    },
  ];
  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="id"
        headerTitle="查询列表"
        dateFormatter="string"
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
        search={{ span: 8, labelWidth: 100 }}
      />
    </PageContainer>
  );
};

export default connect(({ templateview, loading }) => ({
  templateview,
  loading: loading.effects['templateview/query'],
}))(TemplateView);
