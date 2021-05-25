import React, { useState, useEffect, useRef } from 'react';
import { Button, message } from 'antd';
import { connect } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import moment from 'moment';
import numeral from 'numeral';
import ProTable from '@ant-design/pro-table';
import DeleteBtu from '@/components/DeleteBtu';
import { download } from '@/services/commom';
import { deleteList, tbList } from './service';

const dateFormat = 'YYYY-MM-DD';
const overMonth = moment()
  .month(moment().month() - 1)
  .startOf('month');
const lastMonth = moment()
  .month(moment().month() - 1)
  .endOf('month');

const InsurManagement = ({ dispatch, insurManagement: { list, pagination, filter }, loading }) => {
  const ref = useRef();
  const [rowsKeys, setRowsKeys] = useState([]);
  const [rows, setRows] = useState([]);
  const [buttonStatus, setButtonStatus] = useState('1');
  const [delLoading, setDelLoading] = useState(false);
  // 页面查询
  const queryList = (params) => {
    let param;
    if (JSON.stringify(params) === '{}') {
      param = {
        status: '1',
        beginDate: params.begDate
          ? moment(params.begDate[0]).format(dateFormat)
          : moment(overMonth).format(dateFormat),
        endDate: params.begDate
          ? moment(params.begDate[1]).format(dateFormat)
          : moment(lastMonth).format(dateFormat),
      };
      dispatch({
        type: 'insurManagement/query',
        payload: param,
      });
    } else if (params.beginDate === '' || params.endDate === '') {
      param = {
        status: params.status,
        beginDate: params.beginDate !== '' ? moment(params.beginDate).format(dateFormat) : '',
        endDate: params.endDate !== '' ? moment(params.endDate).format(dateFormat) : '',
      };
      dispatch({
        type: 'insurManagement/query',
        payload: param,
      });
    } else {
      dispatch({
        type: 'insurManagement/query',
        payload: params,
      });
    }
  };

  useEffect(() => {
    if (JSON.stringify(filter) === '{}') {
      ref.current.setFieldsValue({ invoicedate: [overMonth, lastMonth], status: '1' });
    } else {
      const { status, beginDate, endDate } = filter;
      if (beginDate === '' && endDate === '') {
        ref.current.setFieldsValue({
          status,
          // invoicedate: [moment(beginDate), moment(endDate)],
        });
      } else {
        ref.current.setFieldsValue({
          status,
          invoicedate: [moment(beginDate), moment(endDate)],
        });
      }
      setButtonStatus(status);
    }
    queryList(filter);
  }, []);

  // 投保
  const tbFunction = async () => {
    const params = {
      idList: rowsKeys,
    };
    const response = await tbList(params);
    const { code, msg } = response;
    if (code === 200) {
      message.success(msg);
      queryList(filter);
    }
    if (code === 500) {
      message.error(msg);
    }
    setRows([]);
    setRowsKeys([]);
  };

  // 删除
  const delFunction = async () => {
    const params = {
      insureDOList: rows,
    };
    setDelLoading(true);
    const response = await deleteList(params);
    const { code, msg } = response;
    if (code === 200) {
      message.success(msg);
      queryList(filter);
      setRows([]);
      setRowsKeys([]);
    }
    if (code === 500) {
      message.error(msg);
    }
    setDelLoading(false);
  };

  const flag = () => {
    return rows.length === 0 && buttonStatus === '2';
  };

  const flag1 = () => {
    return rows.length === 0 && buttonStatus === '1';
  };

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/insurance/manage/export', filter);
      }}
    >
      导出
    </Button>,
    <Button
      disabled={buttonStatus === '2' || flag1()}
      onClick={() => tbFunction()}
      key="tb"
      type="primary"
    >
      投保
    </Button>,
    <DeleteBtu
      disabled={buttonStatus === '1' || flag()}
      deleteFunc={() => delFunction()}
      loading={delLoading}
    />,
  ];

  const columns = [
    {
      title: '保险公司编码',
      dataIndex: 'compCode',
      search: false,
      key: 'compCode',
      width: 150,
      fixed: 'left',
    },
    {
      title: '保单号',
      dataIndex: 'policyno',
      search: false,
      key: 'policyno',
      width: 150,
      ellipsis: true,
      fixed: 'left',
    },
    {
      title: '客户编码',
      dataIndex: 'custCode',
      key: 'custCode',
      width: 150,
      fixed: 'left',
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      key: 'custName',
      width: 200,
      ellipsis: true,
      fixed: 'left',
    },
    {
      title: '买方代码',
      dataIndex: 'buyerno',
      key: 'buyerno',
      search: false,
      width: 120,
    },
    {
      title: '保单主体',
      dataIndex: 'body',
      key: 'body',
      search: false,
      width: 150,
    },
    {
      title: '汇总发票号',
      dataIndex: 'invoiceNo',
      key: 'invoiceNo',
      search: false,
      width: 120,
      ellipsis: true,
    },
    {
      title: '汇总发票金额（元）',
      dataIndex: 'invoicesum',
      key: 'invoicesum',
      search: false,
      align: 'right',
      width: 160,
      render: (_, record) => numeral(record.invoicesum).format('0,0.00'),
    },
    {
      title: '投保金额（元）',
      dataIndex: 'insuresum',
      key: 'insuresum',
      search: false,
      align: 'right',
      width: 160,
      render: (_, record) => numeral(record.insuresum).format('0,0.00'),
    },
    {
      title: '付款方式',
      dataIndex: 'paymode',
      key: 'paymode',
      search: false,
      width: 120,
    },
    {
      title: '保险账期（天）',
      dataIndex: 'quotaDays',
      key: 'quotaDays',
      search: false,
      width: 140,
    },
    {
      title: '汇总发票日期',
      dataIndex: 'invoicedate',
      key: 'invoicedate',
      width: 130,
      valueType: 'dateRange',
      render: (_, record) => {
        return record.invoicedate ? moment(record.invoicedate).format('YYYY-MM-DD') : '-';
      },
    },
    {
      title: '最早发票日期',
      dataIndex: 'btime',
      key: 'btime',
      search: false,
      width: 130,
      valueType: 'date',
    },
    {
      title: '最晚发票日期',
      dataIndex: 'etime',
      key: 'etime',
      search: false,
      width: 130,
      valueType: 'date',
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      key: 'createTime',
      search: false,
      width: 130,
      valueType: 'date',
    },
    {
      title: '批复状态',
      dataIndex: 'status',
      key: 'status',
      width: 100,
      fixed: 'right',
      valueEnum: {
        1: {
          text: '未投保',
        },
        2: {
          text: '已投保',
        },
      },
    },
  ];

  const rowSelection = {
    onChange: (selectedRowKeys, selectedRows) => {
      setRowsKeys(selectedRowKeys);
      setRows(selectedRows);
    },
  };

  return (
    <PageContainer ghost title={false}>
      <ProTable
        headerTitle="查询列表"
        rowKey="id"
        pagination={pagination}
        search={{
          labelWidth: 100,
          collapsed: false,
          collapseRender: false,
        }}
        dataSource={list}
        onSubmit={(value) => {
          const params = {
            ...value,
            beginDate: value.invoicedate ? value.invoicedate[0] : '',
            endDate: value.invoicedate ? value.invoicedate[1] : '',
          };
          queryList({ ...params });
          setButtonStatus(value.status);
        }}
        onChange={(page) => {
          queryList({ ...filter, ...page });
        }}
        onReset={() => {
          queryList({});
        }}
        loading={loading}
        tableAlertRender={false}
        rowSelection={rowSelection}
        formRef={ref}
        options={false}
        toolBarRender={toolBarRender}
        columns={columns}
        scroll={{ x: 2250 }}
      />
    </PageContainer>
  );
};

export default connect(({ insurManagement, loading }) => ({
  insurManagement,
  loading: loading.effects['insurManagement/query'],
}))(InsurManagement);
