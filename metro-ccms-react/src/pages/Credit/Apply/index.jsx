import { DownOutlined, ExclamationCircleOutlined } from '@ant-design/icons';
import { Button, Modal, message, Divider, Dropdown, Menu } from 'antd';
import React, { useState, useRef } from 'react';
import { connect, request, useAccess, Access, history } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
// import { download } from '@/services/commom';
import EditForm from './components/EditForm';

const { confirm } = Modal;

// 限额申请模块
const CreditApply = () => {
  const [visible, setVisible] = useState(false);
  const actionRef = useRef();
  const formRef = useRef();
  const access = useAccess();

  // const query = (param = {}) => {
  //    dispatch({
  //       type: 'creditapply/query',
  //       payload: param,
  //    });
  // };

  //   useEffect(() => {
  //  formRef.current.setFieldsValue(filter);
  // query(filter);
  //   }, []);

  const columns = [
    // 合同状态和特批类型未分配字段
    {
      title: '申请单号',
      dataIndex: 'APPLICATION_NO',
      copyable: true,
    },
    {
      title: '客户编码',
      dataIndex: 'CUST_CODE',
    },
    {
      title: '客户名称',
      dataIndex: 'CUST_NAME',
    },
    {
      title: '部门',
      dataIndex: 'DEPART_CODE',
      hideInTable: true,
    },
    {
      title: '门店名称',
      dataIndex: 'STORE_name',
      search: false,
    },
    {
      title: '门店编码',
      dataIndex: 'STORE_CODE',
      search: false,
    },
    {
      title: '卡号编码',
      dataIndex: 'CARD_CODE',
      // search: false,
    },
    {
      title: '卡号名称',
      dataIndex: 'CARD_NAME',
      // search: false,
    },
    {
      title: '申请账期',
      dataIndex: 'APPLY_PAYTERM',
      search: false,
    },
    {
      title: '申请额度',
      dataIndex: 'APPLY_LIMIT',
      valueType: 'digit',
      search: false,
    },
    {
      title: '客户类型',
      dataIndex: 'CUST_TYPE',
      // search: false,
      valueType: 'select',
      valueEnum: {
        0: {
          text: 'A',
        },
      },
    },

    {
      title: '额度类型',
      dataIndex: 'LIMIT_TYPE',
      // search: false,
    },
    {
      title: '单据状态',
      dataIndex: 'APPROVE_STATUS',
      // search: false,
      valueType: 'select',
      valueEnum: {
        0: {
          text: '草稿',
        },
        1: {
          text: '准入退回',
        },
        2: {
          text: '待复核',
        },
        3: {
          text: '复核驳回',
        },
        4: {
          text: '额度批复中',
        },
        5: {
          text: '批复驳回',
        },
        6: {
          text: '特批中',
        },
        7: {
          text: '待维护合同',
        },
        8: {
          text: '合同复核中',
        },
        9: {
          text: '合同驳回',
        },
        10: {
          text: '待发布',
        },
        11: {
          text: '额度已发布',
        },
        12: {
          text: '发布失败',
        },
        13: {
          text: '已撤销',
        },
        14: {
          text: '超期未发布',
        },
      },
    },
    {
      title: '业务类型',
      dataIndex: 'BUSINESS_TYPE',
      search: false,
      hideInTable: true,
      valueType: 'select',
      valueEnum: {
        0: {
          text: '草稿',
        },
        1: {
          text: '准入退回',
        },
        2: {
          text: '待复核',
        },
        3: {
          text: '复核驳回',
        },
        4: {
          text: '额度批复中',
        },
        5: {
          text: '批复驳回',
        },
        6: {
          text: '特批中',
        },
        7: {
          text: '待维护合同',
        },
        8: {
          text: '合同复核中',
        },
        9: {
          text: '合同驳回',
        },
        10: {
          text: '待发布',
        },
        11: {
          text: '额度已发布',
        },
        12: {
          text: '发布失败',
        },
        13: {
          text: '已撤销',
        },
        14: {
          text: '超期未发布',
        },
      },
    },
    {
      title: '发布账期',
      dataIndex: 'PAYMENT_TERM',
      search: false,
    },
    {
      title: '发布额度',
      dataIndex: 'LIMIT',
      search: false,
    },
    {
      title: '发布时间',
      dataIndex: 'GRANT_TIME',
      valueType: 'date',
      // search: false,
    },
    {
      title: '生效区间',
      dataIndex: 'VALID_Range',
      valueType: 'dateRange',
      hideInTable: true,
      // search: false,
    },
    {
      title: '生效时间',
      dataIndex: 'VALID_FROM',
      valueType: 'date',
      search: false,
    },
    {
      title: '到期时间',
      dataIndex: 'VALID_to',
      valueType: 'date',
      search: false,
    },
    {
      title: '申请人姓名',
      dataIndex: 'LIMIT',
      search: false,
    },
    {
      title: '申请时间',
      dataIndex: 'APPLY_TIME',
      valueType: 'date',
      search: false,
    },
    {
      title: '信用组名称',
      dataIndex: 'GROUP_NAME',
      search: false,
    },

    {
      title: '是否信用组',
      dataIndex: 'IF_CREDITGR',
      // search: false,
      valueEnum: {
        0: {
          text: '全部',
        },
        1: {
          text: '是',
        },
        2: {
          text: '否',
        },
      },
    },
    {
      title: '是否准入特批',
      dataIndex: 'SPECIAL_TYPE',
      // search: false,
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
      title: '是否额度特批',
      dataIndex: 'SPECIAL_TYPE',
      // search: false,
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
      title: '是否合同特批',
      dataIndex: 'SPECIAL_TYPE',
      // search: false,
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
      title: '操作',
      width: 130,
      fixed: 'right',
      render: (_, record) => [
        <a
          onClick={() => {
            history.push({
              pathname: '/credit/creditApply/update',
              row: record,
            });
          }}
        >
          修改
        </a>,
        <Divider type="vertical" />,
        <Dropdown
          overlay={
            <Menu>
              <Menu.Item>
                <a
                  onClick={() => {
                    confirm({
                      title: `是否删除数据项?`,
                      icon: <ExclamationCircleOutlined />,
                      onOk() {
                        return request(`/credit/creditApply/${record.appliName}`, {
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
                    });
                  }}
                >
                  删除
                </a>
              </Menu.Item>
              <Menu.Item>
                <a onClick={() => {}}>撤销</a>
              </Menu.Item>
            </Menu>
          }
        >
          <a className="ant-dropdown-link" onClick={(e) => e.preventDefault()}>
            更多 <DownOutlined />
          </a>
        </Dropdown>,
      ],
    },
  ];

  const toolBarRender = () => [
    <Access key="add" accessible={access.compAccess('message')}>
      <Button
        type="primary"
        onClick={(e) => {
          e.preventDefault();
          setVisible(true);
        }}
      >
        新增
      </Button>
    </Access>,

    <Button
      key="export"
      onClick={() => {
        //   download('/credit/apply/export', filter);
      }}
    >
      导出
    </Button>,
  ];

  const testData = [
    {
      applyId: '123',
    },
  ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="userId"
        options={false}
        headerTitle="预警信息走势"
        actionRef={actionRef}
        formRef={formRef}
        // loading={loading}
        dataSource={testData}
        //   pagination={pagination}
        //   onSubmit={(value) => {
        //     query({ ...value });
        //   }}
        //   onReset={() => {
        //     query({});
        //   }}
        toolBarRender={toolBarRender}
        columns={columns}
        scroll={{ x: 4000 }}
        search={{ span: 8, labelWidth: 100 }}
      />
      <EditForm
        visible={visible}
        destroyOnClose
        onClose={() => {
          setVisible(false);
        }}
      />
    </PageContainer>
  );
};

export default connect(({ creditapply, loading }) => ({
  creditapply,
  loading: loading.effects['creditapply/query'],
}))(CreditApply);
