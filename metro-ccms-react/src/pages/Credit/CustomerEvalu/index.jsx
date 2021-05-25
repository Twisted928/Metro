import { Button } from 'antd';
import React, { useEffect, useRef, useCallback, useState } from 'react';
import { connect, history } from 'umi';
import numeral from 'numeral';
import { PageContainer } from '@ant-design/pro-layout';
import ChooseCustomer from '@/components/CustomerList';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';

const CustomerEvalu = ({ dispatch }) => {
  const actionRef = useRef();
  const formRef = useRef();
  const [cusVis, setCusVis] = useState(false);

  //   const query = useCallback(
  //     (param = {}) => {
  //       dispatch({
  //         type: 'custgroup/query',
  //         payload: param,
  //       });
  //     },
  //     [dispatch],
  //   );

  const industryType = useCallback(() => {
    dispatch({
      type: 'commonmodel/basciData',
      payload: { ctype: 'IndustryType' },
    });
  }, [dispatch]);

  useEffect(() => {
    // formRef.current.setFieldsValue(filter);
    // query(filter);
    industryType();
  }, [industryType]);

  const startPg = () => {
    setCusVis(true);
  };

  const checkDetail = (res) => {
    history.push({
      pathname: '/credit/custGroup/showdetails',
      query: {
        custGroup: res.custGroup,
      },
    });
  };

  const columns = [
    {
      title: '客户编码',
      dataIndex: 'custCode',
      ellipsis: true,
      width: 150,
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      ellipsis: true,
      width: 150,
    },
    {
      title: '行业类型',
      dataIndex: 'industryType',
      width: 120,
    },
    {
      title: '模型名称',
      dataIndex: 'modelName',
      ellipsis: true,
      width: 150,
    },
    {
      title: '评估日期',
      dataIndex: 'appraisalDate',
      width: 150,
      valueType: 'dateRange',
    },
    {
      title: '评估得分',
      width: 120,
      dataIndex: 'grade',
      search: false,
      align: 'right',
    },
    {
      title: '客户评级',
      width: 120,
      dataIndex: 'rank',
    },
    {
      title: '建议额度',
      dataIndex: 'adviceLimit',
      width: 120,
      search: false,
      align: 'right',
    },
    {
      title: '建议账期',
      width: 120,
      dataIndex: 'adviceDays',
      search: false,
      align: 'right',
    },
    {
      title: '生效时间',
      width: 150,
      dataIndex: 'validFrom',
      search: false,
      valueType: 'date',
    },
    {
      title: '到期时间',
      width: 150,
      dataIndex: 'validTo',
      search: false,
      valueType: 'date',
    },
    {
      title: '是否黑名单',
      width: 120,
      dataIndex: 'ifblack',
      search: false,
    },
    {
      title: '是否白名单',
      width: 120,
      dataIndex: 'ifwhite',
      search: false,
    },
    {
      title: '信用组名称',
      width: 150,
      ellipsis: true,
      dataIndex: 'groupName',
      search: false,
    },
    {
      title: '申请人',
      width: 150,
      dataIndex: 'createdBy',
      search: false,
    },
    {
      title: '申请时间',
      width: 150,
      dataIndex: 'createTime',
      search: false,
      valueType: 'date',
    },
    {
      title: '申请单号',
      width: 150,
      dataIndex: 'applicationNo',
      search: false,
    },
    {
      title: '状态',
      dataIndex: 'status',
      width: 120,
      valueEnum: {
        1: {
          text: '有效',
        },
        0: {
          text: '无效',
        },
      },
    },
    {
      title: '操作',
      width: 120,
      valueType: 'option',
      fixed: 'right',
      render: (text, record) => [
        <a key="detail" onClick={() => checkDetail(record)}>
          详情
        </a>,
      ],
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/Customergroup/management/export', filter);
      }}
    >
      导出
    </Button>,
    <Button
      type="primary"
      key="start"
      onClick={() => {
        startPg();
      }}
    >
      发起评估
    </Button>,
  ];

  const chooseCustomer = {
    visible: cusVis,
    onCancel: (vis) => {
      setCusVis(vis);
    },
    getFormData: (data) => {
      history.push({
        pathname: '/credit/CustomerEvalu/initiateEvalu',
      });
    },
  };

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="id"
        options={false}
        headerTitle="查询列表"
        dateFormatter="string"
        actionRef={actionRef}
        formRef={formRef}
        // loading={loading}
        dataSource={[]}
        // pagination={pagination}
        onSubmit={(value) => {
          //   query({ ...value });
        }}
        onChange={(page) => {
          //   query({ ...filter, ...page });
        }}
        toolBarRender={toolBarRender}
        columns={columns}
        scroll={{ x: 2620 }}
      />
      <ChooseCustomer {...chooseCustomer} />
    </PageContainer>
  );
};

export default connect(({ commonmodel, loading }) => ({
  commonmodel,
  //   loading: loading.effects['custgroup/query'],
}))(CustomerEvalu);
