import { ExclamationCircleOutlined } from '@ant-design/icons';
import { Button, Modal, message, Divider, Select } from 'antd';
import React, { useState, useEffect, useRef } from 'react';
import { connect, request, useAccess, Access } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';
import EditForm from './components/EditForm';
import { queryTerm, queryDesc } from './service';

const { confirm } = Modal;
const { Option } = Select;

// 付款条件管理模块
const CreditTerms = ({ dispatch, creditTerms: { list, filter, pagination }, loading }) => {
  const [visible, setVisible] = useState(false);
  const [rows, setRow] = useState({});
  const actionRef = useRef();
  const Ref = useRef();
  const access = useAccess();
  const [termDesc, setTermDesc] = useState([]);
  const [termType, setTermType] = useState([]);

  const query = (param = {}) => {
    dispatch({
      type: 'creditTerms/query',
      payload: param,
    });
  };

  const getTermType = async () => {
    const res = await queryTerm();
    setTermType(res.data);
  };

  const getTerm = async (value) => {
    Ref.current?.setFieldsValue({
      paymentDesc: '',
    });
    const res = await queryDesc({
      settleType: value,
    });
    if (res.code === 200) {
      setTermDesc(res.data);
    } else {
      message.error(res.msg);
    }
  };

  useEffect(() => {
    // eslint-disable-next-line no-console
    console.log(Ref.current);
    getTermType();
    Ref.current?.setFieldsValue ({
      ...filter,
    });
    query(filter);
  }, [Ref.current]);

  const deleteColumn = (record) => {
    confirm({
      title: '确定删除此条数据吗？',
      icon: <ExclamationCircleOutlined />,
      onOk() {
        return request('/payTerm/delete', {
          method: 'POST',
          data: {
            id: record.id,
          },
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
  };

  const onEdit = (record) => {
    setRow(record);
    setVisible(true);
  };

  const columns = [
    {
      title: '结算方式',
      dataIndex: 'settleType',
      renderFormItem: (_, { type, defaultRender, formItemProps, fieldProps, ...rest }, form) => {
        if (type === 'form') {
          return null;
        }
        const status = form.getFieldValue('state');
        if (status !== 'open') {
          return (
            <Select onSelect={getTerm} {...fieldProps} {...rest}>
              {termType.map((item) => {
                return (
                  <Option key={item?.id} value={item?.description}>
                    {item?.description}
                  </Option>
                );
              })}
            </Select>
          );
        }
        return defaultRender(_);
      },
    },
    {
      title: '付款条件描述',
      dataIndex: 'paymentDesc',
      renderFormItem: (_, { type, defaultRender, formItemProps, fieldProps, ...rest }, form) => {
        if (type === 'form') {
          return null;
        }
        const status = form.getFieldValue('state');
        if (status !== 'open') {
          return (
            <Select {...fieldProps} {...rest}>
              {termDesc.map((item) => {
                return (
                  <Option key={item?.id} value={item?.paymentDesc}>
                    {item?.paymentDesc}
                  </Option>
                );
              })}
            </Select>
          );
        }
        return defaultRender(_);
      },
    },
    {
      title: '付款条件代码',
      dataIndex: 'paymentCode',
      search: false,
    },
    {
      title: '参考付款条件天数',
      dataIndex: 'paymentDays',
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
      search: false,
    },
    {
      title: '创建人',
      dataIndex: 'createdBy',
      search: false,
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      valueType: 'date',
      search: false,
    },
    {
      title: '更新人',
      dataIndex: 'updatedBy',
      search: false,
    },
    {
      title: '更新时间',
      dataIndex: 'updateTime',
      valueType: 'date',
      search: false,
    },
    {
      title: '操作',
      valueType: 'option',
      width: 150,
      fixed: 'right',
      render: (_, record) => [
        <a onClick={() => onEdit(record)}>修改</a>,
        <Divider type="vertical" />,
        <a onClick={() => deleteColumn(record)}>删除</a>,
      ],
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/payTerm/export', filter);
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
        新增
      </Button>
    </Access>,
    <Button
      key="export"
      onClick={() => {
        // eslint-disable-next-line no-console
        console.log(Ref);
      }}
    >
      test
    </Button>,
  ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="Id"
        headerTitle="付款条件管理"
        options={false}
        actionRef={actionRef}
        formRef={Ref}
        loading={loading}
        dataSource={list}
        pagination={pagination}
        onSubmit={(value) => {
          query({ ...value });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
        toolBarRender={toolBarRender}
        columns={columns}
        search={{ labelWidth: 'auto', span: 8 }}
      />
      <EditForm
        visible={visible}
        destroyOnClose
        row={rows}
        onClose={() => {
          setVisible(false);
          query(filter);
        }}
      />
    </PageContainer>
  );
};

export default connect(({ creditTerms, loading }) => ({
  creditTerms,
  loading: loading.effects['creditTerms/query'],
}))(CreditTerms);
