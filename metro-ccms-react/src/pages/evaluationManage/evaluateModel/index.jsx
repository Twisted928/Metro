import React, { useState, useEffect, useRef, Fragment, useCallback } from 'react';
import { Button, Divider, Menu, Dropdown, message } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { DownOutlined } from '@ant-design/icons';
import { history, connect } from 'umi';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';
import DeleteText from '@/components/DeleteText';
import AddModal from './components/addModal';
import { deleteList, pushList, unpushList } from './service';

const EvaluateModel = ({ dispatch, loading, evaluateModel: { list, pagination, filter } }) => {
  const ref = useRef();
  const formRef = useRef();
  const [visible, setVisible] = useState(false);
  const [ifView, setView] = useState(false);
  const [rowId, setRowId] = useState(null);
  const [rowData, setRowData] = useState({});

  const query = useCallback(
    (param = {}) => {
      dispatch({
        type: 'evaluateModel/query',
        payload: param,
      });
    },
    [dispatch],
  );

  useEffect(() => {
    query(filter);
  }, [filter, query]);

  const deleteMessage = async (id) => {
    const response = await deleteList({ id });
    const { code, msg } = response;
    if (code === 200) {
      message.success('删除成功！');
      query();
    }
    if (code === 500) {
      message.info(msg);
    }
  };

  const pushlist = async (id) => {
    const response = await pushList({ id });
    const { code, msg } = response;
    if (code === 200) {
      message.success('发布成功！');
      query();
    }
    if (code === 500) {
      message.info(msg);
    }
  };
  const undorepush = async (id) => {
    const response = await unpushList({ id });
    const { code } = response;
    if (code === 200) {
      message.success('撤销成功！');
      query();
    }
  };

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/model/info/export', filter);
      }}
    >
      导出
    </Button>,
    <Button
      key="add"
      type="primary"
      onClick={() => {
        setVisible(true);
      }}
    >
      新增模型
    </Button>,
  ];

  const menu = (res) => (
    <Menu>
      <Menu.Item key="updata">
        <a
          onClick={() => {
            history.push({
              pathname: '/evaluation/evaluationManage/addIndicator',
              query: {
                id: res.id,
              },
            });
          }}
        >
          维护指标
        </a>
      </Menu.Item>
      <Menu.Item key="view">
        <a
          onClick={() => {
            setVisible(true);
            setView(true);
            setRowId(res.id);
            setRowData(res);
          }}
        >
          详情
        </a>
      </Menu.Item>
      {res.publish === '1' ? (
        <Menu.Item key="unfb">
          <a
            onClick={() => {
              undorepush(res.id);
            }}
          >
            撤销发布
          </a>
        </Menu.Item>
      ) : (
        <Menu.Item key="fb">
          <a
            onClick={() => {
              pushlist(res.id);
            }}
          >
            发布
          </a>
        </Menu.Item>
      )}
      {res.publish === '1' ? (
        ''
      ) : (
        <Menu.Item key="zf">
          <DeleteText
            deleteFunc={() => {
              deleteMessage(res.id);
            }}
          />
        </Menu.Item>
      )}
    </Menu>
  );

  const columns = [
    {
      title: '模型编码',
      dataIndex: 'id',
      fixed: 'left',
      width: 100,
    },
    {
      title: '模型名称',
      dataIndex: 'name',
      ellipsis: true,
      fixed: 'left',
      width: 180,
    },
    {
      title: '模型描述',
      dataIndex: 'description',
      ellipsis: true,
      width: 180,
    },
    {
      title: '行业类型',
      dataIndex: 'indusType',
      width: 130,
      search: false,
      valueEnum: {
        1: {
          text: '企业类',
        },
        2: {
          text: '政府背景类',
        },
        3: {
          text: '社会组织类',
        },
      },
    },
    {
      title: '发布状态',
      dataIndex: 'publish',
      width: 120,
      valueEnum: {
        1: {
          text: '已发布',
        },
        0: {
          text: '未发布',
        },
      },
    },
    {
      title: '有无财报',
      dataIndex: 'financial',
      width: 120,
      valueEnum: {
        1: {
          text: '有财报',
        },
        0: {
          text: '无财报',
        },
      },
    },
    {
      title: '新/老客户',
      dataIndex: 'ifOld',
      width: 120,
      search: false,
      valueEnum: {
        1: {
          text: '新客户',
        },
        0: {
          text: '老客户',
        },
      },
    },
    {
      title: '终止时间',
      search: false,
      dataIndex: 'expiryDate',
      width: 150,
      valueType: 'date',
    },
    {
      title: '创建人',
      search: false,
      dataIndex: 'createdBy',
      width: 120,
    },
    {
      title: '创建日期',
      search: false,
      dataIndex: 'createTime',
      width: 150,
      valueType: 'date',
    },
    {
      title: '修改人',
      search: false,
      dataIndex: 'updatedBy',
      width: 120,
    },
    {
      title: '修改日期',
      search: false,
      dataIndex: 'updateTime',
      width: 180,
    },
    {
      title: '操作',
      width: 120,
      fixed: 'right',
      search: false,
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                setVisible(true);
                setRowId(record.id);
                setRowData(record);
              }}
            >
              修改
            </a>
            <Divider type="vertical" />
            <Dropdown overlay={() => menu(record)}>
              <a className="ant-dropdown-link">
                更多 <DownOutlined />
              </a>
            </Dropdown>
          </Fragment>
        );
      },
    },
  ];

  // 组件值
  const addmodal = {
    visible,
    ifView,
    id: rowId,
    data: rowData,
    onCancel: (vis) => {
      setVisible(vis);
      setRowId(null);
      setView(false);
    },
    onload: () => {
      query();
    },
  };

  return (
    <PageContainer ghost title={false}>
      <ProTable
        headerTitle="查询列表"
        rowKey="id"
        dataSource={list}
        actionRef={ref}
        formRef={formRef}
        options={false}
        loading={loading}
        pagination={pagination}
        toolBarRender={toolBarRender}
        columns={columns}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        search={false}
        scroll={{ x: 1840 }}
      />
      <AddModal {...addmodal} />
    </PageContainer>
  );
};

export default connect(({ evaluateModel, loading }) => ({
  evaluateModel,
  loading: loading.effects['evaluateModel/query'],
}))(EvaluateModel);
