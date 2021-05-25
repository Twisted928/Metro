import React, { useState, useEffect, useRef, useCallback } from 'react';
import { Button, Dropdown, Menu, Modal, message } from 'antd';
import { DownOutlined, ExclamationCircleOutlined } from '@ant-design/icons';
import { connect, useAccess, Access, request, history } from 'umi';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';
import moment from 'moment';
import { PageContainer } from '@ant-design/pro-layout';
import { queryCust } from './service';
import PreSelectcust from './components/PreSelectcust';

const { confirm } = Modal;

// 白名单管理
const WhiteList = ({ dispatch, whitelist: { list, filter, pagination }, loading }) => {
  const formRef = useRef();
  const actionRef = useRef();
  const access = useAccess();
  // 控制客户选择弹窗
  const [visible, setVisible] = useState(false);

  const query = useCallback(
    (param = {}) => {
      dispatch({
        type: 'whitelist/query',
        payload: param,
      });
    },
    [dispatch],
  );

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
    queryCust({});
  }, [filter, query]);

  const approve = (record) => {
    history.push({
      pathname: '/customer/whitelist/list/approve',
      query: {
        id: record.id,
        applicationno: record.applicationno,
      },
    });
  };

  const Revoke = (record) => {
    if (record.approvestatus === '1') {
      confirm({
        title: `是否确认撤销该申请?`,
        icon: <ExclamationCircleOutlined />,
        onOk() {
          return request('/customer/whitelistmanagement/revoke', {
            method: 'POST',
            data: {
              id: record.id,
              applicationno: record.applicationno,
              taskId: '',
              instanceid: record.instanceid,
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
    } else {
      message.error('只允许撤销审批中的申请！');
    }
  };

  const EditRecord = (record) => {
    history.push({
      pathname: '/customer/whitelist/list/edit',
      query: {
        id: record.id,
        applicationno: record.applicationno,
      },
    });
  };

  const CheckDetails = (record) => {
    history.push({
      pathname: '/customer/whitelist/list/details',
      query: {
        id: record.id,
        applicationno: record.applicationno,
      },
    });
  };

  const deleteColumn = (record) => {
    if (record.approvestatus === '4') {
      confirm({
        title: '确定删除此条数据吗？',
        icon: <ExclamationCircleOutlined />,
        onOk() {
          return request('/customer/whitelistmanagement/delete', {
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
    } else {
      message.error('只有撤销状态的单据才可以删除!');
    }
  };

  const columns = [
    {
      title: '申请单号',
      dataIndex: 'applicationno',
      // search: false,
      fixed: 'left',
      width: 150,
      key: 'applicationno',
    },
    {
      title: '客户名称',
      fixed: 'left',
      dataIndex: 'custname',
      search: false,
      ellipsis: true,
      width: 200,
      key: 'custname',
    },
    {
      title: '客户编码',
      dataIndex: 'custcode',
      // search: false,
      width: 150,
      key: 'custcode',
    },
    {
      title: '申请单状态',
      dataIndex: 'approvestatus',
      // search: false,
      valueType: 'select',
      width: 100,
      key: 'approvestatus',
      valueEnum: {
        0: {
          text: '全部',
        },
        1: {
          text: '审批中',
        },
        2: {
          text: '生效',
        },
        3: {
          text: '退回',
        },
        4: {
          text: '已撤销',
        },
      },
    },
    {
      title: '有效期',
      width: 180,
      search: false,
      dataIndex: 'validity',
      key: 'validity',
      render: (_, record) => {
        return `${moment(record.validfrom).format('YYYY-MM-DD')} - ${moment(record.validto).format(
          'YYYY-MM-DD',
        )}`;
      },
    },
    {
      title: '申请人',
      width: 120,
      search: false,
      key: 'applicant',
      dataIndex: 'applicant',
    },
    {
      title: '申请时间',
      dataIndex: 'applytimeRange',
      // search: false,
      valueType: 'dateRange',
      width: 180,
      hideInTable: true,
      key: 'dateRange',
    },
    {
      title: '更新人',
      width: 120,
      search: false,
      dataIndex: 'updatedby',
      key: 'updatedby',
    },
    {
      title: '更新时间',
      width: 150,
      dataIndex: 'updatetime',
      search: false,
      valueType: 'date',
      key: 'updatetime',
    },
    // {
    //   title: '原因',
    //   width: 150,
    //   ellipsis: true,
    //   search: false,
    //   key: 'reason',
    //   dataIndex: 'reason',
    // },

    // {
    //   title: '申请时间',
    //   width: 150,
    //   dataIndex: 'applytime',
    //   search: false,
    //   valueType: 'date',
    //   key: 'applytime',
    // },
    // {
    //   title: '生效日期',
    //   width: 150,
    //   dataIndex: 'validfrom',
    //   search: false,
    //   valueType: 'date',
    //   key: 'validfrom',
    // },
    // {
    //   title: '失效日期',
    //   dataIndex: 'validto',
    //   search: false,
    //   width: 150,
    //   valueType: 'date',
    //   key: 'validto',
    // },
    {
      title: '操作',
      valueType: 'option',
      fixed: 'right',
      width: 120,
      key: 'action',
      render: (_, record) => [
        <a key="editable" onClick={() => approve(record)}>
          审批
        </a>,
        <Dropdown
          overlay={
            <Menu>
              <Menu.Item key="edit">
                <a key="edit" onClick={() => CheckDetails(record)}>
                  详情
                </a>
              </Menu.Item>
              {record.approvestatus === '1' ? (
                ''
              ) : (
                <Menu.Item key="edit">
                  <a key="edit" onClick={() => EditRecord(record)}>
                    修改
                  </a>
                </Menu.Item>
              )}
              <Menu.Item key="callback">
                <a key="callback" onClick={() => Revoke(record)}>
                  撤销
                </a>
              </Menu.Item>
              <Menu.Item key="delete">
                <a key="delete" onClick={() => deleteColumn(record)}>
                  删除
                </a>
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

  // Table行选择参数

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/customer/whitelistmanagement/export', filter);
      }}
    >
      导出
    </Button>,

    <Access key="add" accessible={access.compAccess('message')}>
      <Button
        type="primary"
        onClick={() => {
          setVisible(true);
        }}
      >
        新增
      </Button>
    </Access>,
  ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="id"
        headerTitle="查询列表"
        options={false}
        actionRef={actionRef}
        formRef={formRef}
        columns={columns}
        loading={loading}
        pagination={pagination}
        dataSource={list}
        onSubmit={(value) => {
          query({
            ...value,
            applytimestr: value?.applytimeRange?.[0],
            applytimeend: value?.applytimeRange?.[1],
          });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
        scroll={{ x: 1500 }}
        toolBarRender={toolBarRender}
        search={{ span: 8, labelWidth: 80 }}
      />

      {/* 新建预选客户 */}
      <PreSelectcust visible={visible} destroyOnClose onClose={() => setVisible(false)} />
    </PageContainer>
  );
};

export default connect(({ whitelist, loading }) => ({
  whitelist,
  loading: loading.models.whitelist,
}))(WhiteList);
