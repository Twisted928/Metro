import React, { useState, useEffect, useRef, Fragment } from 'react';
import { Button, Divider, Menu, Dropdown } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history } from 'umi';
import { DownOutlined } from '@ant-design/icons';
import moment from 'moment';
import ProTable from '@ant-design/pro-table';
import ChooseModal from './components/chooseCusModal';

const overMonth = moment()
  .month(moment().month() - 1)
  .startOf('month');
const lastMonth = moment()
  .month(moment().month() - 1)
  .endOf('month');

const SafeguardCompnay = () => {
  const ref = useRef();
  const [modalVis, setModalVis] = useState(false);
  const [editingKey, setEditingKey] = useState('');

  const isEditing = (record) => record.id === editingKey;

  useEffect(() => {
    ref.current.setFieldsValue({ invoicedate: [overMonth, lastMonth] });
  }, []);

  const toolBarRender = () => [
    <Button key="export">导出</Button>,
    <Button key="export">模板下载</Button>,
    <Button
      key="export"
      type="primary"
      onClick={() => {
        setModalVis(true);
      }}
    >
      新增
    </Button>,
  ];

  const deleteMsg = () => {};

  const detailPage = () => {
    history.push({
      pathname: '/safeguard/Lossmanagement/updataFrom',
      query: {},
    });
  };

  const uploadMsg = () => {
    history.push({
      pathname: '/credit/creditReview/updataFrom',
      query: {},
    });
  };

  const menu = (res) => (
    <Menu>
      <Menu.Item key="det">
        <a onClick={() => uploadMsg(res)} target="_blank">
          详情
        </a>
      </Menu.Item>
      <Menu.Item key="sp">
        <a target="_blank">审批</a>
      </Menu.Item>
      <Menu.Item key="cx">
        <a target="_blank">撤销</a>
      </Menu.Item>
    </Menu>
  );

  const columns = [
    {
      title: '申请单号',
      dataIndex: 'applicationNo',
      key: 'applicationNo',
    },
    {
      title: '复审类型',
      dataIndex: 'ctype',
      key: 'ctype',
    },
    {
      title: '复审说明',
      dataIndex: 'remark',
      key: 'remark',
      search: false,
      width: 250,
    },
    {
      title: '申请人',
      dataIndex: 'applicant',
      key: 'applicant',
      search: false,
    },
    {
      title: '申请时间',
      dataIndex: 'applyTime',
      key: 'applyTime',
      search: false,
    },
    {
      title: '单据状态',
      dataIndex: 'approveStatus',
      key: 'approveStatus',
      valueEnum: {
        0: {
          text: '审批中',
        },
        1: {
          text: '审批通过',
        },
        2: {
          text: '审批撤销',
        },
        3: {
          text: '驳回',
        },
      },
    },
    {
      title: '操作',
      dataIndex: 'action',
      key: 'action',
      search: false,
      render: (_, record) => {
        return (
          <Fragment>
            <a onClick={() => uploadMsg()}>修改</a>
            <Divider type="vertical" />
            <a onClick={() => deleteMsg(record)} target="_blank">
              删除
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

  const list = [
    {
      applicationNo: '123455',
      ctype: '1',
    },
  ];

  const chooseModal = {
    vis: modalVis,
    onCancel: (data) => {
      setModalVis(data);
    },
  };

  return (
    <PageContainer ghost title={false}>
      <ProTable
        headerTitle="查询列表"
        rowKey="id"
        dataSource={list}
        tableAlertRender={false}
        formRef={ref}
        options={false}
        toolBarRender={toolBarRender}
        columns={columns}
      />
      <ChooseModal {...chooseModal} />
    </PageContainer>
  );
};

export default SafeguardCompnay;
