import React, { useState, useEffect, useRef, Fragment } from 'react';
import { Button, Modal, message, Dropdown, Menu, Divider } from 'antd';
import { ExclamationCircleOutlined, DownOutlined } from '@ant-design/icons';
import { connect, Access, useAccess, request } from 'umi';
import ProTable from '@ant-design/pro-table';
import moment from 'moment';
import { download } from '@/services/commom';
import { PageContainer } from '@ant-design/pro-layout';
import CreateForm from './components/CreateForm';
import EditForm from './components/EditForm';
import ShowDatails from './components/ShowDetails';
import { queryDetails } from './service';

const { confirm } = Modal;

// 监控客户清单
const MonitorCust = ({
  dispatch,
  monitorcust: { list, custlist, filter, filterCust, pagination, paginationCust },
  loading,
  loadingCust,
}) => {
  const formRef = useRef();
  const custformRef = useRef();
  const actionRef = useRef();
  const custactionRef = useRef();
  const access = useAccess();
  // const [submitting, setSubmitting] = useState(false);
  // 获取选中行id
  const [rowKeys, setRowKeys] = useState([]);
  // 控制客户选择弹窗
  const [visible, setVisible] = useState(false);
  // 控制客户新增弹窗
  const [createCust, setCreate] = useState(false);
  // 控制详情弹窗
  const [showDetails, setDetails] = useState(false);
  // 详情弹窗传值
  const [details, setDetailsParams] = useState(false);
  // 客户新增弹窗传值
  // const [thisPayloads, setPayloads] = useState([]);
  // 修改传值
  const [editInfo, setEditinfo] = useState([]);
  // 控制修改弹窗
  const [startEdit, setStartEdit] = useState(false);

  const [thisRow, setRow] = useState([]);

  const query = (param = {}) => {
    dispatch({
      type: 'monitorcust/query',
      payload: param,
    });
  };

  // 客户新增预选表单查询
  const queryCust = (param = {}) => {
    dispatch({
      type: 'monitorcust/queryCust',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
    queryCust(filter);
  }, []);

  // 删除
  const deleteColumn = (record) => {
    if (record.status === '1') {
      confirm({
        title: '确定删除此条数据吗？',
        icon: <ExclamationCircleOutlined />,
        onOk() {
          return request('/customer/monitoringcustomers/update', {
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
      message.error('失效客户无法删除！');
    }
  };

  // 详情
  const ShowDetails = async (record) => {
    const params = { id: record.id };
    const { code, data, msg } = await queryDetails(params);
    if (code === 200) {
      setDetailsParams(data[0]);
      setDetails(true);
    } else {
      message.error(msg);
    }
  };

  // 修改
  const editCust = (record) => {
    request('/customer/monitoringcustomers/kehulist', {
      params: {
        custcode: record.custcode,
      },
    }).then((res) => {
      if (res.code === 200 && res.rows[0]) {
        // 传递当前行
        setRow(record);
        // 传递查询结果
        setEditinfo(res.rows[0]);
        // 开启弹窗
        setStartEdit(true);
      } else if (res.code === 200 && !res.rows[0]) {
        message.warning({ content: '当前客户监控信息不可修改', duration: 3 });
      } else {
        message.error(res.msg);
      }
    });
  };

  const columns = [
    {
      title: '客户编码',
      dataIndex: 'custcode',
      width: 130,
      fixed: 'left',
      // search: false,
    },
    {
      title: '客户名称',
      dataIndex: 'custname',
      // search: false,
      width: 200,
      fixed: 'left',
      ellipsis: true,
    },
    {
      title: '生效区间',
      width: 150,
      dataIndex: 'validstrRange',
      valueType: 'dateRange',
      hideInTable: true,
    },
    {
      title: '失效区间',
      width: 150,
      dataIndex: 'validendRange',
      valueType: 'dateRange',
      hideInTable: true,
    },
    {
      title: '生效日期',
      width: 150,
      dataIndex: 'validfrom',
      valueType: 'date',
      search: false,
    },
    {
      title: '到期日期',
      width: 150,
      dataIndex: 'validto',
      valueType: 'date',
      search: false,
    },
    {
      title: '创建人',
      width: 130,
      search: false,
      dataIndex: 'createdby',
    },
    {
      title: '创建时间',
      width: 150,
      dataIndex: 'createtime',
      search: false,
      valueType: 'date',
    },
    {
      title: '状态',
      width: 120,
      dataIndex: 'status',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '无效',
          status: 'error',
        },
        1: {
          text: '有效',
          status: 'success',
        },
      },
    },
    {
      title: '原因',
      width: 150,
      ellipsis: true,
      dataIndex: 'reason',
      search: false,
    },
    {
      title: '操作',
      valueType: 'option',
      width: 120,
      fixed: 'right',
      render: (_, record) => {
        return (
          <Fragment>
            <a ket="edit" onClick={() => editCust(record)}>
              修改
            </a>
            <Divider type="vertical" />
            <Dropdown
              overlay={
                <Menu>
                  <Menu.Item>
                    <a key="history" onClick={() => ShowDetails(record)}>
                      详情
                    </a>
                  </Menu.Item>
                  <Menu.Item>
                    <a key="delete" onClick={() => deleteColumn(record)}>
                      删除
                    </a>
                  </Menu.Item>
                </Menu>
              }
            >
              <a>
                更多
                <DownOutlined />
              </a>
            </Dropdown>
          </Fragment>
        );
      },
    },
  ];

  // 客户新增预选表单列
  const columnsCust = [
    {
      title: '客户编码',
      dataIndex: 'custcode',
    },
    {
      title: '客户名称',
      dataIndex: 'custname',
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/customer/monitoringcustomers/export', filter);
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

  // 客户新增预选提交
  const onFinish = () => {
    if (thisRow !== []) {
      // 打开新增弹窗,关闭预选弹窗
      setCreate(true);
      setVisible(false);
    } else {
      message.info({ content: '请选择一位客户!', duration: 2 });
    }
  };

  // Table行选择参数
  const rowSelection = {
    onChange: (selectedRowKeys, selectedRows) => {
      setRowKeys(selectedRows[0]);
      // eslint-disable-next-line no-console
      console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
    },
  };

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="id"
        headerTitle="查询列表"
        dateFormatter="string"
        options={false}
        actionRef={actionRef}
        formRef={formRef}
        columns={columns}
        pagination={pagination}
        loading={loading}
        dataSource={list}
        onSubmit={(value) => {
          query({
            ...value,
            validfromstr: value?.validstrRange?.[0],
            validfromend: value?.validstrRange?.[1],
            validtostr: value?.validendRange?.[0],
            validtoend: value?.validendRange?.[1],
          });
        }}
        scroll={{ x: 1630 }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
        toolBarRender={toolBarRender}
        search={{ span: 8, labelWidth: 100 }}
      />

      {/* 新建预选客户 */}
      <Modal
        width={1000}
        destroyOnClose
        title="新增"
        visible={visible}
        onOk={onFinish}
        onCancel={() => {
          setVisible(false);
        }}
      >
        <ProTable
          rowKey="id"
          options={false}
          actionRef={custactionRef}
          formRef={custformRef}
          dataSource={custlist}
          loading={loadingCust}
          pagination={paginationCust}
          columns={columnsCust}
          onSubmit={(value) => {
            queryCust({
              ...value,
            });
          }}
          onChange={(page) => {
            queryCust({ ...filterCust, ...page });
          }}
          onReset={() => {
            queryCust({});
          }}
          tableAlertRender={false}
          rowSelection={{
            type: 'radio',
            ...rowSelection,
          }}
        />
      </Modal>

      {/* 新建弹窗 */}
      <CreateForm
        visible={createCust}
        destroyOnClose
        payload={rowKeys}
        onClose={() => {
          setCreate(false);
          setRow([]);
          query(filter);
        }}
      />

      {/* 修改弹窗 */}
      <EditForm
        visible={startEdit}
        destroyOnClose
        payload={editInfo}
        thisRow={thisRow}
        onClose={() => {
          setStartEdit(false);
          setEditinfo([]);
          query(filter);
        }}
      />

      {/* 详情弹窗 */}
      <ShowDatails
        visible={showDetails}
        destroyOnClose
        payload={details}
        onClose={() => {
          setDetails(false);
        }}
      />
    </PageContainer>
  );
};

export default connect(({ monitorcust, loading }) => ({
  monitorcust,
  loading: loading.effects['monitorcust/query'],
  loadingCust: loading.effects['monitorcust/queryCust'],
}))(MonitorCust);
