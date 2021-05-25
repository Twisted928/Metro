import React, { useRef, Fragment } from 'react';
import { Button, Divider, Menu, Dropdown, message } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { connect, history } from 'umi';
// import moment from 'moment';
import DeleteText from '@/components/DeleteText';
import { download } from '@/services/commom';
import { DownOutlined } from '@ant-design/icons';
import ProTable from '@ant-design/pro-table';
import { deleteList } from './service';

// const dateFormat = 'YYYY-MM-DD';

const GroupOverview = ({ dispatch, groupOverview: { pagination, filter }, loading }) => {
  const ref = useRef();
  const formRef = useRef();

  // 页面查询
  const query = (param = {}) => {
    dispatch({
      type: 'safeguardCompnay/query',
      payload: param,
    });
  };

  // 页面初始化
  // useEffect(() => {
  //   query(filter);
  // }, []);

  // 添加
  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/insurance/management/export', filter);
      }}
    >
      导出
    </Button>,
    <Button
      key="add"
      type="primary"
      onClick={() => {
        history.push('/credit/Creditgroup/groupOverview/createForm');
      }}
    >
      新增
    </Button>,
  ];

  // 修改
  const updataMsg = (res) => {
    const { id } = res;
    history.push({
      pathname: '/credit/Creditgroup/groupOverview/updateForm',
      query: {
        id,
        disabled: false,
      },
    });
  };

  // 详情
  const detailPage = (res) => {
    const { id } = res;
    history.push({
      pathname: '/credit/Creditgroup/groupOverview/updateForm',
      query: {
        id,
        disabled: false,
      },
    });
  };

  // 删除
  const deleteMsg = async (res) => {
    const response = await deleteList({ id: res.id });
    const { msg, code } = response;
    if (code === 500) {
      message.error(msg);
      return;
    }
    query();
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
      title: '信用组编号',
      dataIndex: 'groupCode',
      key: 'groupCode',
      ellipsis: true,
      width: 200,
    },
    {
      title: '信用组名称',
      dataIndex: 'groupName',
      key: 'groupName',
      ellipsis: true,
      width: 200,
    },
    {
      title: '客户编码',
      dataIndex: 'custCode',
      ellipsis: true,
      search: false,
      key: 'custCode',
      width: 200,
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      ellipsis: true,
      search: false,
      key: 'custName',
      width: 200,
    },
    {
      title: '授信状态',
      dataIndex: 'status',
      align: 'center',
      key: 'status',
      width: 100,
      valueEnum: {
        1: {
          text: '有效',
        },
        0: {
          text: '无效',
        },
      },
    },
    {
      title: '额度类型',
      dataIndex: 'limitTyle',
      align: 'center',
      key: 'limitTyle',
      search: false,
      width: 100,
      valueEnum: {
        1: {
          text: '有效',
        },
        0: {
          text: '无效',
        },
      },
    },
    {
      title: '信用组账期',
      dataIndex: 'groupPayterm',
      ellipsis: true,
      search: false,
      align: 'right',
      key: 'groupPayterm',
      width: 120,
    },
    {
      title: '信用组额度',
      dataIndex: 'groupLimit',
      ellipsis: true,
      search: false,
      align: 'right',
      key: 'groupLimit',
      width: 120,
    },
    {
      title: '已占用额度',
      dataIndex: 'groupOccupied',
      search: false,
      align: 'right',
      key: 'groupOccupied',
      width: 120,
    },
    {
      title: '可用额度',
      dataIndex: 'groupUsable',
      ellipsis: true,
      search: false,
      align: 'right',
      key: 'groupUsable',
      width: 100,
    },
    {
      title: '生效时间',
      dataIndex: 'validFrom',
      key: 'validFrom',
      search: false,
      width: 130,
      valueType: 'date',
    },
    {
      title: '到期时间',
      dataIndex: 'validTo',
      key: 'validTo',
      width: 130,
      search: false,
      valueType: 'date',
    },

    {
      title: '操作',
      dataIndex: 'action',
      key: 'action',
      fixed: 'right',
      width: 120,
      search: false,
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
      <ProTable
        headerTitle="查询列表"
        rowKey="id"
        onSubmit={(value) => {
          query({ ...value });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        pagination={pagination}
        dataSource={[]}
        loading={loading}
        actionRef={ref}
        formRef={formRef}
        options={false}
        toolBarRender={toolBarRender}
        columns={columns}
        scroll={{ x: 1860 }}
      />
    </PageContainer>
  );
};

export default connect(({ groupOverview, loading }) => ({
  groupOverview,
  loading: loading.effects['groupOverview/query'],
}))(GroupOverview);
