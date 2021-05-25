import React, { /* useState, */ useEffect, useRef } from 'react';
import {
  Button,
  Modal,
  message,
  Dropdown,
  Menu,
} from 'antd';
import {
  ExclamationCircleOutlined,
  DownOutlined,
} from '@ant-design/icons';
import { connect, request, history, } from "umi";
import ProTable from '@ant-design/pro-table';
import moment from 'moment'
import { download } from '@/services/commom';
import { PageContainer } from '@ant-design/pro-layout';

const { confirm } = Modal;

const textData = [
  {
    id: 325132032,
    CUST_CODE: "356482216",
    CUST_NAME: "字符长度限制在十个字",
    BUSINESS_TYPE: "A",
  },
  {
    id: 624691229,
    CUST_CODE: "356482216",
    CUST_NAME: "字符长度限制在十个字",
    BUSINESS_TYPE: "A",
  },
  {
    id: 624748504,
    CUST_CODE: "356482216",
    CUST_NAME: "字符长度限制在十个字",
    BUSINESS_TYPE: "A",
  },
]

// 信用客户管理
const CustomerGroup = ({ dispatch, customergroup: { filter, pagination } }) => {
  const formRef = useRef();
  const actionRef = useRef();

  const query = (param = {}) => {
    dispatch({
      type: 'customergroup/query',
      payload: param,
    });
  };

  useEffect(() => {
    const thisValidfrom = filter && filter.validfrom ? moment(filter.validfrom) : null
    const thisValidto = filter && filter.validto ? moment(filter.validfrom) : null
    formRef.current.setFieldsValue({
      ...filter,
      validRange: [thisValidfrom, thisValidto],
    });    query(filter);
  }, []);

  const deleteColumn = (record) => {
    confirm({
      title: '确定删除此条数据吗？',
      icon: <ExclamationCircleOutlined />,
      onOk() {
        return request(`/credit/creditApply/${record.STORE_NAME}`, {
          method: 'DELETE',
        }).then((res) => {
          if (res.code === 200) {
            message.success(res.msg);
          } else {
            message.error(res.msg);
          }
          // query(/* filter */);
        });
      },
      onCancel() {},
    })
  };

  const iconChoose = (record) => {
    const showMore = document.getElementById(record.id);
    showMore.style.transform = "rotate(180deg)"
  }

  const iconMoveout = (record) => {
    const closeAll = document.getElementById(record.id);
    closeAll.style.transform = "rotate(0deg)"
  }

  const checkDetails = (record) => {
    history.push({
      pathname: '/customer/creditGroup/details',
      query: {
        CUST_CODE: record.CUST_CODE,
      },
    });
  }

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
      title: '卡号编码',
      dataIndex: 'cardcode',
      search: false,

    },
    {
      title: '卡号名称',
      dataIndex: 'cardname',
      search: false,

    },
    {
      title: '业务类型',
      dataIndex: 'businesstype',
      // search:false,
      valueType: 'select',
      valueEnum: {
        0: {
          text: '类型A',
        },
        1: {
          text: '类型B',
        }
      },
    },
    {
      title: '客户类型',
      dataIndex: 'custtype',
      // search:false,
      valueType: 'select',
      valueEnum: {
        0: {
          text: '类型A',
        },
        1: {
          text: '类型B',
        }
      },
    },
    {
      title: '行业分类',
      dataIndex: 'industrytype',
      // search:false,
      valueType: 'select',
      valueEnum: {
        0: {
          text: '类型A',
        },
        1: {
          text: '类型B',
        }
      },
    },
    {
      title: '组织类型',
      dataIndex: 'organtype',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '类型A',
        },
        1: {
          text: '类型B',
        }
      },
    },
    {
      title: '有效状态',
      dataIndex: 'status',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '否',
          status: 'Error',
        },
        1: {
          text: '是',
          status: 'Success',
        },
      },
    },
    {
      title: '信用冻结状态',
      dataIndex: 'creditblock',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '否',
          status: 'Error',
        },
        1: {
          text: '是',
          status: 'Success',
        },
      },
    },
    {
      title: '客户冻结状态',
      dataIndex: 'custblock',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '否',
          status: 'Error',
        },
        1: {
          text: '是',
          status: 'Success',
        },
      },
    },
    {
      title: '客户组',
      dataIndex: 'custgroup',
      search: false,

    },
    {
      title: '客户经理编码',
      dataIndex: 'custmanagercode',
      search: false,
    },
    {
      title: '客户经理名称',
      dataIndex: 'custmanagername',
      search: false,

    },
    {
      title: '客户锁定状态描述',
      dataIndex: 'custblockdesc',
      search: false,

    },
    {
      title: '信用冻结描述',
      dataIndex: 'creditblockdesc',
      search: false,
    },
    {
      title: '是否黑名单',
      dataIndex: 'ifblack',
      search: false,
    },
    {
      title: '创建人',
      dataIndex: 'createby',
      search: false,

    },
    {
      title: '创建时间',
      dataIndex: 'createtime',
      search: false,

    },
    {
      title: '更新人',
      dataIndex: 'updateby',
      search: false,
    },
    {
      title: '更新时间',
      dataIndex: 'updatetime',
      search: false,
    },
    {
      title: '操作',
      valueType: 'option',
      fixed: 'right',
      render: (_, record) => [
        <a
          key="editable"
          onClick={() => {
            history.push({
              pathname: '/customer/creditGroup/edit',
              query: record.id
            });
          }}
        >
          编辑
        </a>,
        <Dropdown overlay={
          <Menu>
            <Menu.Item>
              <a key="history" onClick={() => checkDetails(record)} >
                详情
              </a>
            </Menu.Item>
            <Menu.Item>
              <a key="delete" onClick={() => deleteColumn(record)} >
                删除
              </a>
            </Menu.Item>
          </Menu>
        }>
          <a key={record.idid}
            onClick={(e) => {
              e.preventDefault()
            }}
            onMouseEnter={() => iconChoose(record)}
            onMouseLeave={() => iconMoveout(record)}
          >
            更多 <span><DownOutlined id={record.id} /></span>
          </a>
        </Dropdown>,
      ],
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download();
      }}
    >
      导出
    </Button>,
  ]

  return (
    <PageContainer
      ghost
      title={false}
    >
      <ProTable
        rowKey="id"
        headerTitle="信用冻结解冻"
        options={false}
        actionRef={actionRef}
        formRef={formRef}
        columns={columns}
        pagination={pagination}
        // loading={loading}
        request={async () => ({
          data: textData,
          total: 3,
          success: true,
        })}
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
        scroll={{ x: 4000 }}
        search={{ span: 8, labelWidth: 100 }}
      />
    </PageContainer>
  );
}

export default connect(({ customergroup, loading }) => ({
  customergroup,
  loading: loading.models.customergroup,
}))(CustomerGroup);
