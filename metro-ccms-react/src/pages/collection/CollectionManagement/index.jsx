/* eslint-disable react-hooks/exhaustive-deps */
import React, { useRef, useState, useEffect, Fragment } from 'react';
import { Divider, Menu, Button, Dropdown, message } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import numeral from 'numeral';
import DeleteText from '@/components/DeleteText';
import { download } from '@/services/commom';
import { DownOutlined } from '@ant-design/icons';
import AddModalMsg from './components/addModal';
import ProTable from '@ant-design/pro-table';
import { deleteList, updateStaus } from './service';
import Styles from './index.less';

const CollectionManagement = ({
  dispatch,
  loading,
  collectionManagement: { list, pagination, filter },
}) => {
  const ref = useRef();
  const formRef = useRef();
  const [visAddModal, setVisAddModal] = useState(false);

  const query = (param = {}) => {
    dispatch({
      type: 'collectionManagement/query',
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
        download('/collection/management/export', filter);
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
      pathname: '/collection/CollectionManagement/updataFrom',
      query: {
        id: res.id,
        applicationNo: res.applicationNo,
      },
    });
  };

  const detailPage = (res) => {
    history.push({
      pathname: '/collection/CollectionManagement/detailsForm',
      query: {
        id: res.id,
        applicationNo: res.applicationNo,
      },
    });
  };

  const viewPage = async (res) => {
    const { id, applicationNo } = res;
    history.push({
      pathname: '/collection/CollectionManagement/wordPrint',
      query: {
        id,
        applicationNo,
      },
    });
  };

  const deleteMsg = async (res) => {
    const response = await deleteList({ id: res.id });
    if (response.code === 200) {
      query(filter);
    } else {
      message.error(response.msg);
    }
  };

  const menu = (res) => (
    <Menu>
      <Menu.Item key="del">
        <DeleteText
          deleteFunc={() => {
            deleteMsg(res);
          }}
        />
      </Menu.Item>
      <Menu.Item key="det">
        <a onClick={() => detailPage(res)} target="_blank">
          详情
        </a>
      </Menu.Item>
      <Menu.Item key="view">
        <a
          onClick={() => {
            viewPage(res);
          }}
          target="_blank"
        >
          预览
        </a>
      </Menu.Item>
    </Menu>
  );

  const addModalMsg = {
    vis: visAddModal,
    onCancel: (data) => {
      setVisAddModal(data);
      query();
    },
  };

  const columns = [
    {
      title: '催收单号',
      dataIndex: 'applicationNo',
      key: 'applicationNo',
      width: 130,
    },
    {
      title: '部门编码',
      dataIndex: 'departCode',
      key: 'departCode',
      search: false,
      width: 120,
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
      width: 150,
      ellipsis: true,
    },
    {
      title: '账期内应收',
      dataIndex: 'undue',
      key: 'undue',
      align: 'right',
      search: false,
      width: 120,
      render: (_, record) => numeral(record.undue).format('0,0.00'),
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
      title: '逾期账龄',
      search: false,
      children: [
        {
          title: '15天',
          dataIndex: 'idue015',
          width: 90,
          align: 'right',
          key: 'idue015',
        },
        {
          title: '16-30天',
          dataIndex: 'idue030',
          width: 90,
          align: 'right',
          key: 'idue030',
        },
        {
          title: '31-60天',
          align: 'right',
          width: 90,
          dataIndex: 'idue060',
          key: 'idue060',
        },
        {
          title: '61-90天',
          align: 'right',
          width: 100,
          dataIndex: 'idue090',
          key: 'idue090',
        },
        {
          title: '91-365天',
          align: 'right',
          width: 100,
          dataIndex: 'idueheji365',
          key: 'idueheji365',
        },
        {
          title: '12月',
          align: 'right',
          width: 90,
          dataIndex: 'idue360',
          key: 'idue360',
        },
        {
          title: '超一年',
          align: 'right',
          width: 90,
          dataIndex: 'idue361',
          key: 'idue361',
        },
      ],
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
      search: false,
      width: 130,
      valueType: 'date',
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
      key: 'updateTime',
      search: false,
      width: 130,
      valueType: 'date',
    },
    {
      title: '单据状态',
      dataIndex: 'status',
      key: 'status',
      width: 100,
      fixed: 'right',
      valueEnum: {
        0: {
          text: '未开始',
        },
        1: {
          text: '催收中',
        },
        2: {
          text: '催收成功',
        },
        3: {
          text: '催收失败',
        },
      },
    },
    {
      title: '日期',
      dataIndex: 'ddate',
      key: 'ddate',
      width: 130,
      fixed: 'right',
      valueType: 'date',
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

  return (
    <PageContainer ghost title={false}>
      <div className={Styles.tableSty}>
        <ProTable
          headerTitle="查询列表"
          rowKey="id"
          loading={loading}
          pagination={pagination}
          dataSource={list}
          actionRef={ref}
          formRef={formRef}
          options={false}
          toolBarRender={toolBarRender}
          columns={columns}
          scroll={{ x: 2960 }}
          onSubmit={(value) => {
            query({ ...value });
          }}
          onChange={(page) => {
            query({ ...filter, ...page });
          }}
          onReset={() => {
            query({});
          }}
        />
      </div>
      <AddModalMsg {...addModalMsg} />
    </PageContainer>
  );
};

export default connect(({ collectionManagement, loading }) => ({
  collectionManagement,
  loading: loading.effects['collectionManagement/query'],
}))(CollectionManagement);
