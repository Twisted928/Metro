/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect, useState, useRef, Fragment } from 'react';
import { Button, Divider, message, Select } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import moment from 'moment';
import { download } from '@/services/commom';
import numeral from 'numeral';
import DeleteBtu from '@/components/DeleteBtu';
import ProTable from '@ant-design/pro-table';
import { deleteList } from './service';

const { option } = Select;

const GuaraManagement = ({
  dispatch,
  loading,
  commonmodel: { basciData },
  guaraManagement: { list, filter, pagination },
}) => {
  const ref = useRef();
  const [rowsKeys, setRowsKeys] = useState([]);
  const [deLoading, setDeLoading] = useState(false);

  const query = (param = {}) => {
    dispatch({
      type: 'guaraManagement/query',
      payload: param,
    });
  };

  // table列表显示ValueEnum下拉数据
  const getEnumName = (data) => {
    const arr = data;
    const obj = {};
    for (let i = 0; i < arr.length; i += 1) {
      obj[`${arr[i].ctype}`] = {
        text: arr[i].description,
      };
    }
    return obj;
  };

  const getGuarantList = () => {
    dispatch({
      type: 'commonmodel/basciData',
      payload: { ctype: 'GuaranteeType' },
    });
  };

  useEffect(() => {
    ref.current.setFieldsValue(filter);
    query(filter);
    getGuarantList();
  }, []);

  // 担保类型
  const guarantList = () => {
    return (
      <Select allowClear>
        {(basciData || []).map((item) => {
          return (
            <option key={`${item.ctype}`} value={`${item.ctype}`}>
              {item.description}
            </option>
          );
        })}
      </Select>
    );
  };

  const addNewMsg = () => {
    history.push({
      pathname: '/safeguard/GuaraManagement/createForm',
      query: {},
    });
  };

  // 删除
  const deletelist = async () => {
    setDeLoading(true);
    const response = await deleteList({ ids: rowsKeys });
    const { code, msg } = response;
    if (code === 200) {
      query();
      message.success('删除成功！');
    } else {
      message.success(msg);
    }
    setDeLoading(false);
    setRowsKeys([]);
  };

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/guaranteeLetter/management/export', filter);
      }}
    >
      导出
    </Button>,
    <Button
      key="export"
      type="primary"
      onClick={() => {
        addNewMsg();
      }}
    >
      新增
    </Button>,
    <DeleteBtu
      disabled={rowsKeys.length === 0}
      loading={deLoading}
      deleteFunc={() => {
        deletelist();
      }}
    />,
  ];

  const uploadMsg = (res) => {
    history.push({
      pathname: '/safeguard/GuaraManagement/updataFrom',
      query: {
        id: res.id,
      },
    });
  };
  const detailsMsg = (res) => {
    history.push({
      pathname: '/safeguard/GuaraManagement/detailForm',
      query: {
        id: res.id,
      },
    });
  };

  const columns = [
    {
      title: '担保函编码',
      dataIndex: 'gtcode',
      key: 'gtcode',
      ellipsis: true,
      fixed: 'left',
      width: 150,
    },
    {
      title: '担保函类型',
      dataIndex: 'guaranteetype',
      key: 'guaranteetype',
      fixed: 'left',
      ellipsis: true,
      width: 120,
      renderFormItem: () => {
        return guarantList();
      },
      valueEnum: getEnumName(basciData),
    },
    {
      title: '客户编码',
      dataIndex: 'custCode',
      key: 'custCode',
      ellipsis: true,
      width: 150,
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      key: 'custName',
      ellipsis: true,
      width: 200,
    },
    {
      title: '担保金额（元）',
      dataIndex: 'gtsum',
      key: 'gtsum',
      search: false,
      ellipsis: true,
      align: 'right',
      width: 190,
      render: (_, record) => numeral(record.gtsum).format('0,0.00'),
    },
    {
      title: '生效日期',
      dataIndex: 'validFrom',
      key: 'validFrom',
      search: false,
      width: 120,
      valueType: 'date',
    },
    {
      title: '失效日期',
      dataIndex: 'validTo',
      key: 'validTo',
      width: 120,
      valueType: 'dateRange',
      render: (_, record) => {
        return record.validTo ? moment(record.validTo).format('YYYY-MM-DD') : '-';
      },
    },
    {
      title: '创建人',
      dataIndex: 'createdBy',
      key: 'createdBy',
      search: false,
      width: 120,
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      key: 'createTime',
      search: false,
      valueType: 'date',
      width: 130,
    },
    {
      title: '更新人',
      dataIndex: 'updatedBy',
      key: 'updatedBy',
      search: false,
      width: 120,
    },
    {
      title: '更新时间',
      dataIndex: 'updateTime',
      valueType: 'date',
      key: 'updateTime',
      search: false,
      width: 130,
    },
    {
      title: '状态',
      dataIndex: 'status',
      key: 'status',
      search: false,
      width: 100,
      valueEnum: {
        0: {
          text: '无效',
        },
        1: {
          text: '有效',
        },
      },
    },
    {
      title: '操作',
      dataIndex: 'action',
      key: 'action',
      fixed: 'right',
      search: false,
      width: 120,
      render: (_, record) => {
        return (
          <Fragment>
            <a onClick={() => uploadMsg(record)}>修改</a>
            <Divider type="vertical" />
            <a onClick={() => detailsMsg(record)}>详情</a>
          </Fragment>
        );
      },
    },
  ];

  const rowSelection = {
    onChange: (selectedRowsKeys) => {
      setRowsKeys(selectedRowsKeys);
    },
  };

  return (
    <PageContainer ghost title={false}>
      <ProTable
        headerTitle="查询列表"
        rowKey="id"
        dataSource={list}
        pagination={pagination}
        tableAlertRender={false}
        rowSelection={rowSelection}
        formRef={ref}
        options={false}
        loading={loading}
        toolBarRender={toolBarRender}
        columns={columns}
        scroll={{ x: 1750 }}
        onSubmit={(value) => {
          const params = {
            ...value,
            beginDate: value.validTo ? value.validTo[0] : '',
            endDate: value.validTo ? value.validTo[1] : '',
          };
          query({ ...params });
        }}
        onReset={() => {
          query({});
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
      />
    </PageContainer>
  );
};

export default connect(({ guaraManagement, commonmodel, loading }) => ({
  guaraManagement,
  commonmodel,
  loading: loading.effects['guaraManagement/query'],
}))(GuaraManagement);
