/* eslint-disable react-hooks/exhaustive-deps */
import { ExclamationCircleOutlined } from '@ant-design/icons';
import { Button, Modal, message, Divider, DatePicker } from 'antd';
import React, { useState, useEffect, useRef, Fragment } from 'react';
import { connect, request, useAccess, Access } from 'umi';
import moment from 'moment';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import MoneyType from '@/components/MoneyType';
import { download } from '@/services/commom';
import CreateForm from './components/CreateForm';

const { confirm } = Modal;
const { MonthPicker } = DatePicker;

const Exchange = ({ dispatch, exchange: { list, pagination, filter }, loading }) => {
  const actionRef = useRef();
  const formRef = useRef();
  const [row, setRow] = useState();
  const [visible, setVisible] = useState(false);
  const access = useAccess();

  const query = (param = {}) => {
    dispatch({
      type: 'exchange/query',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const columns = [
    {
      title: '币种',
      dataIndex: 'currency',
      renderFormItem: () => {
        return <MoneyType />;
      },
    },
    {
      title: '转换币种',
      dataIndex: 'currencyDh',
      search: false,
    },
    {
      title: '月度',
      dataIndex: 'month',
      renderFormItem: () => {
        return <MonthPicker />;
      },
    },
    {
      title: '汇率',
      dataIndex: 'exchangeRate',
      align: 'right',
      width: 100,
      search: false,
    },
    // {
    //   title: '启用状态',
    //   dataIndex: 'status',
    //   valueEnum: {
    //     1: {
    //       text: '失效',
    //       status: 'Default',
    //     },
    //     0: {
    //       text: '正常',
    //       status: 'Processing',
    //     },
    //   },
    // },
    {
      title: '创建时间',
      valueType: 'dateRange',
      dataIndex: 'createTime',
      search: false,
      render: (_, record) => {
        return record.createTime || '-';
      },
    },
    {
      title: '修改时间',
      dataIndex: 'updateTime',
      search: false,
      render: (_, record) => {
        return record.updateTime || '-';
      },
    },
    {
      title: '操作',
      dataIndex: 'option',
      search: false,
      render: (_, record) => (
        <Fragment>
          <a
            onClick={() => {
              setRow(record);
              setVisible(true);
            }}
          >
            修改
          </a>
          <Divider type="vertical" />
          <a
            onClick={() => {
              confirm({
                title: `是否确认删除币种名称为"${record.currency}"的数据项?`,
                icon: <ExclamationCircleOutlined />,
                onOk() {
                  return request('/system/exchange/deleteExchangeById', {
                    data: { id: record.id },
                    method: 'POST',
                  }).then((res) => {
                    if (res.code === 200) {
                      message.success(res.msg);
                    } else {
                      message.error(res.msg);
                    }
                    query(filter);
                  });
                },
                onCancel() {},
              });
            }}
          >
            删除
          </a>
        </Fragment>
      ),
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/system/exchange/export', filter);
      }}
    >
      导出
    </Button>,
    <Access key="add" accessible={access.compAccess('message')}>
      <Button
        type="primary"
        onClick={() => {
          setVisible(true);
          setRow({});
        }}
      >
        新建
      </Button>
    </Access>,
  ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        headerTitle="查询列表"
        actionRef={actionRef}
        formRef={formRef}
        rowKey="id"
        onSubmit={(value) => {
          const { currency, month } = value;
          const params = {
            currency,
            month: moment(month).format('YYYY-MM'),
          };
          query({ ...params });
        }}
        onReset={() => {
          query({});
        }}
        search={{
          collapsed: false,
          collapseRender: false,
          labelWidth: 70,
        }}
        options={false}
        toolBarRender={toolBarRender}
        dataSource={list}
        pagination={pagination}
        loading={loading}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        columns={columns}
      />

      {!!row && (
        <CreateForm
          visible={visible}
          row={row}
          setRow={() => {
            setRow(undefined);
          }}
          onClose={() => {
            setVisible(false);
            query();
          }}
        />
      )}
    </PageContainer>
  );
};

export default connect(({ exchange, loading }) => ({
  exchange,
  loading: loading.effects['exchange/query'],
}))(Exchange);
