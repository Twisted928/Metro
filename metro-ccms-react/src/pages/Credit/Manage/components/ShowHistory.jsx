import React, { useRef } from 'react';
import { Modal } from 'antd';
import { history } from 'umi';
import ProTable from '@ant-design/pro-table';
import { queryHistory } from '../service';

const ShowHistory = ({ visible, onClose, payload }) => {
  const actionRef = useRef();
  const formRef = useRef();

  const checkDetail = () => {
    history.push({
      pathname: '/credit/manageCredit/evaldetail',
      // query: {
      //     CUST_CODE: record.CUST_CODE,
      //     record,
      // },
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
      ],
    },
  ];

  return (
    <Modal
      destroyOnClose
      visible={visible}
      footer={null}
      onCancel={() => {
        onClose();
      }}
      title="历史记录"
      width="1000"
    >
      <ProTable
        rowKey="id"
        search={false}
        actionRef={actionRef}
        formRef={formRef}
        options={false}
        dateFormatter="string"
        params={payload}
        request={async (params) => {
          const res = await queryHistory(params);
          return {
            data: res?.rows,
            total: res?.total,
            success: true,
          };
        }}
        pagination={{
          pageSize: 10,
        }}
        columns={columns}
        scroll={{ x: 3600 }}
      />
    </Modal>
  );
};

export default ShowHistory;
