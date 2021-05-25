import { Button } from 'antd';
import React, { useEffect, useRef, useState } from 'react';
import { connect, history } from 'umi';
import moment from 'moment';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';
import ShowHistory from './components/ShowHistory';

// 授信管理模块
const CreditManage = ({ dispatch, creditmanage: { list, filter, pagination }, loading }) => {
  const actionRef = useRef();
  const formRef = useRef();
  const [payload, setPayload] = useState([]);
  const [visible, setVisible] = useState(false);

  const query = (param = {}) => {
    dispatch({
      type: 'creditmanage/query',
      payload: param,
    });
  };

  useEffect(() => {
    const thisValidfrom =
      filter && filter.appraisaldatestr ? moment(filter.appraisaldatestr) : null;
    const thisValidto = filter && filter.appraisaldateend ? moment(filter.appraisaldateend) : null;
    formRef.current.setFieldsValue({
      ...filter,
      validRange: [thisValidfrom, thisValidto],
    });
    query(filter);
  }, []);

  const checkHistory = (record) => {
    setPayload({ custcode: record.custcode });
    setVisible(true);
  };

  const checkDetail = (record) => {
    history.push({
      pathname: '/credit/manageCredit/custhistory',
      query: {
        id: record.id,
      },
    });
  };

  const columns = [
    {
      title: '申请单号',
      dataIndex: 'applicationno',
    },
    {
      title: '客户编码',
      dataIndex: 'custcode',
      // render: (text) => (
      //     <a
      //         onClick={() => {
      //             dispatch({
      //                 type: 'creditmanage/queryById',
      //                 payload: {
      //                     CustId: text,
      //                 },
      //             })
      //                 .then((res, error) => {
      //                     if (!error) {
      //                         history.push({
      //                             pathname: '/credit/manageCredit/cardsearch'
      //                         });
      //                     } else {
      //                         // eslint-disable-next-line no-console
      //                         console.log("Error", error)
      //                     }
      //                 });
      //         }}
      //     >
      //         {text}
      //     </a >
      // ),
    },
    {
      title: '客户名称',
      dataIndex: 'custname',
      // search: false,
    },
    {
      title: '行业类型',
      dataIndex: 'industrytype',
      // search: false,
      valueType: 'select',
      valueEnum: {
        0: {
          text: 'A',
        },
        1: {
          text: 'B',
        },
      },
    },
    {
      title: '评估模型',
      dataIndex: 'modname',
      // valueType: 'select',
      // valueEnum: {
      //   0: {
      //     text: 'A',
      //   },
      //   1: {
      //     text: 'B',
      //   },
      // },
      // search: false,
    },
    {
      title: '评估日期',
      dataIndex: 'appraisaldate',
      valueType: 'date',
      search: false,
    },
    {
      title: '评估得分',
      dataIndex: 'grade',
      search: false,
    },
    {
      title: '客户评级',
      dataIndex: 'rank',
      //   search: false,
      valueType: 'select',
      valueEnum: {
        A: {
          text: 'A',
        },
        B: {
          text: 'B',
        },
        C: {
          text: 'C',
        },
      },
    },
    {
      title: '有效状态',
      dataIndex: 'status',
      //   search: false,
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
      title: '建议额度',
      dataIndex: 'advicelimit',
      search: false,
    },
    {
      title: '建议账期',
      dataIndex: 'advicedays',
      search: false,
    },
    {
      title: '评估日期',
      dataIndex: 'validRange',
      //   search: false,
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
      title: '是否黑名单',
      dataIndex: 'ifblack',
      search: false,
      valueType: 'select',
      valueEnum: {
        0: {
          text: '否',
        },
        1: {
          text: '是',
        },
      },
    },
    {
      title: '是否白名单',
      dataIndex: 'ifwhite',
      search: false,
      valueType: 'select',
      valueEnum: {
        0: {
          text: '否',
        },
        1: {
          text: '是',
        },
      },
    },
    {
      title: '信用组名称',
      dataIndex: 'groupname',
      search: false,
    },
    {
      title: '申请人',
      dataIndex: 'createdby',
      search: false,
    },
    {
      title: '申请时间',
      dataIndex: 'createtime',
      search: false,
      valueType: 'date',
    },
    {
      title: '操作',
      valueType: 'option',
      width: 160,
      fixed: 'right',
      render: (_, record) => [
        <a key="detail" onClick={() => checkDetail(record)}>
          详情
        </a>,
        <a key="history" onClick={() => checkHistory(record)}>
          历史
        </a>,
      ],
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/credit/management/export', filter);
      }}
    >
      导出
    </Button>,
  ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="id"
        options={false}
        headerTitle="查询列表"
        dateFormatter="string"
        actionRef={actionRef}
        formRef={formRef}
        loading={loading}
        dataSource={list}
        pagination={pagination}
        onSubmit={(value) => {
          query({
            ...value,
            appraisaldatestr: value?.validRange?.[0],
            appraisaldateend: value?.validRange?.[1],
          });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
        toolBarRender={toolBarRender}
        columns={columns}
        search={{ span: 8 }}
        scroll={{ x: 3600 }}
      />

      <ShowHistory
        destroyOnClose
        visible={visible}
        payload={payload}
        onClose={() => {
          setVisible(false);
          setPayload([]);
        }}
      />
    </PageContainer>
  );
};

export default connect(({ creditmanage, loading }) => ({
  creditmanage,
  loading: loading.effects['creditmanage/query'],
}))(CreditManage);
