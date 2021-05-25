import React, { useState, useEffect, useRef } from 'react';
import { Button, message, Modal } from 'antd';
import { ExclamationCircleOutlined } from '@ant-design/icons';
import { connect, request } from 'umi';
import ProTable from '@ant-design/pro-table';
// import moment from 'moment';
import { download } from '@/services/commom';
import { PageContainer } from '@ant-design/pro-layout';
import ShowHistory from './components/ShowHistory';

const { confirm } = Modal;

// 白名单清单
const CheckList = ({ dispatch, whitelist: { pagination, list, filter }, loading }) => {
  const formRef = useRef();
  const actionRef = useRef();
  const [visible, setVisible] = useState(false);
  const [thisPayload, setPayload] = useState();

  const query = (param = {}) => {
    dispatch({
      type: 'whitelist/queryList',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const moveout = (record) => {
    confirm({
      title: '是否确定移出该项?',
      icon: <ExclamationCircleOutlined />,
      onOk() {
        // eslint-disable-next-line no-console
        console.log('onOK');
        return request('/customer/whitelistmanagement/remove', {
          method: 'POST',
          data: {
            id: record.id,
            reason: record.reason,
            status: record.status,
            custcode: record.custcode,
            custname: record.custname,
            validfrom: record.validfrom,
            validto: record.validto,
          },
        }).then((res) => {
          if (res.code === 200) {
            query(filter);
            message.success({ content: '移出成功', duration: 2 });
          } else {
            message.error({ content: res.msg, duration: 3 });
          }
        });
      },
      onCancel() {
        // eslint-disable-next-line no-console
        console.log('onCancel');
      },
    });
  };

  const columns = [
    {
      title: '客户编码',
      dataIndex: 'custcode',
      // search: false,
    },
    {
      title: '客户名称',
      dataIndex: 'custname',
      // search: false,
    },
    {
      title: '生效日期',
      dataIndex: 'validstrRange',
      valueType: 'dateRange',
      hideInTable: true,
    },
    {
      title: '失效时间',
      dataIndex: 'validendRange',
      valueType: 'dateRange',
      hideInTable: true,
    },
    {
      title: '生效时间',
      dataIndex: 'validfrom',
      search: false,
      valueType: 'date',
    },
    {
      title: '到期时间',
      dataIndex: 'validto',
      search: false,
      valueType: 'date',
    },
    {
      title: '原因',
      search: false,
      ellipsis: true,
      dataIndex: 'reason',
    },
    {
      title: '状态',
      dataIndex: 'status',
      // search: false,
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
      title: '创建人',
      search: false,
      dataIndex: 'createdby',
    },
    {
      title: '创建时间',
      dataIndex: 'createtime',
      search: false,
      valueType: 'date',
    },
    {
      title: '操作',
      valueType: 'option',
      fixed: 'right',
      width: 140,
      render: (_, record) => [
        <a key="editable" onClick={() => moveout(record)}>
          移出
        </a>,

        <a
          key="history"
          onClick={() => {
            setVisible(true);
            setPayload({
              custcode: record.custcode,
            });
          }}
        >
          历史记录
        </a>,
      ],
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/customer/whitelistmanagement/qdexport', filter);
      }}
    >
      导出
    </Button>,
  ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="id"
        headerTitle="白名单清单"
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
            validfromstr: value?.validstrRange?.[0],
            validfromend: value?.validstrRange?.[1],
            validtostr: value?.validendRange?.[0],
            validtoend: value?.validendRange?.[1],
          });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
        toolBarRender={toolBarRender}
        search={{ span: 8, labelWidth: 100 }}
      />

      <ShowHistory
        visible={visible}
        payload={thisPayload}
        destroyOnClose
        onClose={() => {
          setVisible(false);
        }}
      />
    </PageContainer>
  );
};

export default connect(({ whitelist, loading }) => ({
  whitelist,
  loading: loading.effects['whitelist/query'],
}))(CheckList);
