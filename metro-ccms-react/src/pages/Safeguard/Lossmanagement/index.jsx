import React, { useState, useEffect, useRef, Fragment } from 'react';
import { Button, Divider, Menu, Dropdown, message } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import { DownOutlined } from '@ant-design/icons';
import numeral from 'numeral';
import DeleteText from '@/components/DeleteText';
import { download } from '@/services/commom';
import ProTable from '@ant-design/pro-table';
import moment from 'moment';
import { delList } from './service';
import ChooseModal from './components/chooseCusModal';

const Lossmanagement = ({ dispatch, loading, lossmanagement: { list, filter, pagination } }) => {
  const ref = useRef();
  const [modalVis, setModalVis] = useState(false);

  const query = (param = {}) => {
    dispatch({
      type: 'lossmanagement/query',
      payload: param,
    });
  };

  useEffect(() => {
    ref.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/reportLoss/management/export', filter);
      }}
    >
      导出
    </Button>,
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

  const deleteMsg = async (res) => {
    const { caseno, id } = res;
    const params = {
      caseno,
      id,
    };
    const response = await delList(params);
    const { code, msg } = response;
    if (code === 200) {
      message.success(msg);
      query();
    } else {
      message.error(msg);
    }
  };

  const detailPage = (res) => {
    history.push({
      pathname: '/safeguard/Lossmanagement/detailForm',
      query: {
        id: res.id,
        caseNo: res.caseno,
      },
    });
  };

  const uploadMsg = (res) => {
    history.push({
      pathname: '/safeguard/Lossmanagement/updataFrom',
      query: {
        id: res.id,
        caseNo: res.caseno,
      },
    });
  };

  const menu = (res) => (
    <Menu>
      <Menu.Item key="del">
        <DeleteText deleteFunc={() => deleteMsg(res)} />
      </Menu.Item>
      <Menu.Item key="det">
        <a onClick={() => detailPage(res)} target="_blank">
          详情
        </a>
      </Menu.Item>
    </Menu>
  );

  const columns = [
    {
      title: '案件编号',
      dataIndex: 'caseno',
      key: 'caseno',
      width: 130,
      fixed: 'left',
    },
    {
      title: '案件状态',
      dataIndex: 'caseStatus',
      key: 'caseStatus',
      width: 100,
      fixed: 'left',
      valueEnum: {
        1: {
          text: '自追',
        },
        2: {
          text: '委托',
        },
        3: {
          text: '关闭',
        },
      },
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
      search: false,
      ellipsis: true,
      width: 150,
    },
    {
      title: '汇总发票号',
      dataIndex: 'invoiceNo',
      key: 'invoiceNo',
      width: 130,
    },
    {
      title: '汇总发票日期',
      dataIndex: 'invoicedate',
      key: 'invoicedate',
      width: 130,
      valueType: 'dateRange',
      render: (_, record) => {
        return record.invoicedate ? moment(record.invoicedate).format('YYYY-MM-DD') : '-';
      },
    },
    {
      title: '买方代码',
      dataIndex: 'buyerno',
      key: 'buyerno',
      search: false,
      width: 120,
    },
    {
      title: '投保金额（元）',
      dataIndex: 'insuresum',
      key: 'insuresum',
      align: 'right',
      search: false,
      width: 150,
      render: (_, record) => numeral(record.insuresum).format('0,0.00'),
    },
    {
      title: '付款方式',
      dataIndex: 'paymode',
      key: 'paymode',
      search: false,
      width: 120,
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      key: 'createTime',
      search: false,
      width: 120,
      valueType: 'date',
    },
    {
      title: '操作',
      dataIndex: 'action',
      key: 'action',
      fixed: 'right',
      search: false,
      width: 120,
      render: (_, record) => {
        return (
          <Fragment>
            <a onClick={() => uploadMsg(record)}>修改</a>
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
        onSubmit={(value) => {
          const params = {
            ...value,
            beginDate: value.invoicedate ? value.invoicedate[0] : '',
            endDate: value.invoicedate ? value.invoicedate[1] : '',
          };
          query({ ...params });
        }}
        onReset={() => {
          query({});
        }}
        pagination={pagination}
        tableAlertRender={false}
        formRef={ref}
        options={false}
        loading={loading}
        toolBarRender={toolBarRender}
        columns={columns}
        scroll={{ x: 1410 }}
      />
      <ChooseModal {...chooseModal} />
    </PageContainer>
  );
};

export default connect(({ lossmanagement, loading }) => ({
  lossmanagement,
  loading: loading.effects['lossmanagement/query'],
}))(Lossmanagement);
