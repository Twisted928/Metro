import React, { useRef, useEffect, Fragment, useState } from 'react';
import { Button, Divider, Menu, Dropdown, message } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import numeral from 'numeral';
import { download } from '@/services/commom';
import { DownOutlined } from '@ant-design/icons';
import AddModalMsg from './components/addModal';
import ProTable from '@ant-design/pro-table';
import { updateStaus } from './service';

const ReconciliationManage = ({
  dispatch,
  loading,
  reconciliationManage: { list, pagination, filter },
}) => {
  const ref = useRef();
  const formRef = useRef();
  const [visAddModal, setVisAddModal] = useState(false);

  const query = (param = {}) => {
    dispatch({
      type: 'reconciliationManage/query',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/reconciliation/management/export', filter);
      }}
    >
      导出
    </Button>,
    <Button
      key="add"
      type="primary"
      onClick={() => {
        setVisAddModal(true);
      }}
    >
      新增
    </Button>,
  ];

  const updataMsg = (res) => {
    history.push({
      pathname: '/collection/ReconciliationManage/updataFrom',
      query: {
        id: res.id,
      },
    });
  };

  const detailPage = (res) => {
    history.push({
      pathname: '/collection/ReconciliationManage/detailsForm',
      query: {
        id: res.id,
      },
    });
  };

  const viewPage = async (res) => {
    const resData = await updateStaus({ id: res.id });
    const { code, msg } = resData;
    if (code === 200) {
      history.push('/collection/ReconciliationManage/wordPrint');
      query(filter);
    } else {
      message.error(msg);
    }
  };

  const menu = (res) => (
    <Menu>
      <Menu.Item key="det">
        <a onClick={() => detailPage(res)} target="_blank">
          详情
        </a>
      </Menu.Item>
      <Menu.Item key="view">
        <a onClick={() => viewPage(res)} target="_blank">
          预览
        </a>
      </Menu.Item>
    </Menu>
  );

  const columns = [
    {
      title: '对账单号',
      dataIndex: 'applicationNo',
      key: 'applicationNo',
      width: 130,
    },
    {
      title: '部门编码',
      dataIndex: 'departCode',
      key: 'departCode',
      search: false,
      width: 130,
    },
    {
      title: '部门名称',
      dataIndex: 'deptName',
      key: 'deptName',
      width: 150,
      ellipsis: true,
    },
    {
      title: '门店编码',
      dataIndex: 'storeCode',
      key: 'storeCode',
      search: false,
      width: 130,
    },
    {
      title: '客户编码',
      dataIndex: 'custCode',
      key: 'custCode',
      width: 130,
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      key: 'custName',
      width: 150,
      ellipsis: true,
    },
    {
      title: '卡号编码',
      dataIndex: 'cardCode',
      key: 'cardCode',
      width: 130,
    },
    {
      title: '卡号名称',
      dataIndex: 'cardName',
      key: 'cardName',
      width: 200,
      ellipsis: true,
    },
    {
      title: '应收金额',
      dataIndex: 'iar',
      key: 'iar',
      align: 'right',
      search: false,
      width: 120,
      render: (_, record) => numeral(record.iar).format('0,0.00'),
    },
    {
      title: '逾期金额',
      dataIndex: 'idue',
      align: 'right',
      key: 'idue',
      width: 120,
      search: false,
      render: (_, record) => numeral(record.idue).format('0,0.00'),
    },
    {
      title: '状态',
      dataIndex: 'status',
      key: 'status',
      width: 100,
      fixed: 'right',
      valueEnum: {
        0: {
          text: '未对账',
        },
        1: {
          text: '对账中',
        },
        2: {
          text: '对账完成',
        },
        3: {
          text: '存在差异',
        },
      },
    },
    {
      title: '创建人',
      dataIndex: 'createdBy',
      key: 'createdBy',
      search: false,
      width: 130,
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      key: 'createTime',
      valueType: 'date',
      search: false,
      width: 130,
    },
    {
      title: '更新人',
      dataIndex: 'updatedBy',
      key: 'updateTime',
      search: false,
      width: 120,
    },
    {
      title: '更新时间',
      dataIndex: 'updateTime',
      key: 'updateTime',
      valueType: 'date',
      search: false,
      width: 130,
    },
    {
      title: '对账结果描述',
      dataIndex: 'desc',
      key: 'desc',
      width: 150,
      ellipsis: true,
      fixed: 'right',
    },
    {
      title: '操作',
      dataIndex: 'action',
      width: 120,
      key: 'action',
      search: false,
      fixed: 'right',
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                updataMsg(record);
              }}
            >
              修改
            </a>
            <Divider type="vertical" />
            <Dropdown overlay={() => menu(record)}>
              <a className="ant-dropdown-link" onClick={(e) => e.preventDefault()}>
                更多 <DownOutlined />
              </a>
            </Dropdown>
          </Fragment>
        );
      },
    },
  ];

  const addModalMsg = {
    vis: visAddModal,
    onCancel: (data) => {
      setVisAddModal(data);
      query();
    },
  };

  return (
    <PageContainer ghost title={false}>
      <div>
        <ProTable
          headerTitle="查询列表"
          dataSource={list}
          pagination={pagination}
          loading={loading}
          actionRef={ref}
          formRef={formRef}
          options={false}
          toolBarRender={toolBarRender}
          columns={columns}
          scroll={{ x: 2270 }}
          onSubmit={(value) => {
            query({ ...value });
          }}
          onChange={(page) => {
            query({ ...filter, ...page });
          }}
        />
      </div>
      <AddModalMsg {...addModalMsg} />
    </PageContainer>
  );
};

export default connect(({ reconciliationManage, loading }) => ({
  reconciliationManage,
  loading: loading.effects['reconciliationManage/query'],
}))(ReconciliationManage);
