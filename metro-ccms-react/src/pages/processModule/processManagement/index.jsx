import React, { useState, useEffect, useRef, Fragment } from 'react';
import { Button, Divider, Menu, Dropdown, message } from 'antd';
import { connect, history } from 'umi';
import { DownOutlined } from '@ant-design/icons';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { goToActivitPage } from '@/utils/utils';
import { chnagePublish } from './processService';
import AddPro from './components/addProModal';
import AddSc from './components/addScModal';
import RePro from './components/reProcessModal';

const ProcessManage = ({ dispatch, loading, actModal: { list, pagination, filter } }) => {
  const ref = useRef();
  const formRef = useRef();
  const [visible, setVisible] = useState(false);
  const [scenVisible, setScenVisible] = useState(false);
  const [reProVisible, setReProVisible] = useState(false);
  const [conProcessVis, setConprocessVis] = useState(false);
  const [rowMkey, setRowMkey] = useState(null);

  const query = (param = {}) => {
    dispatch({
      type: 'actModal/query',
      payload: param,
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const publishMoId = async (res) => {
    const response = await chnagePublish({ modelId: res.actModelId });
    const { code, msg } = response;
    if (code === 200) {
      query(filter);
      message.success('发布成功！');
    }
    if (code === 500) {
      message.error(msg);
    }
  };

  const processCon = (data) => {
    // setConprocessVis(true);
    history.push('/process/processManagement/processConfiguration');
  };

  const setProcess = (record) => {
    window.open(`${goToActivitPage()}/model.html?modelId=${record.actModelId}`);
  };

  const toolBarRender = () => [
    <Button
      key="addAc"
      type="primary"
      onClick={() => {
        setVisible(true);
      }}
    >
      新增流程
    </Button>,
    <Button
      key="addSc"
      type="primary"
      onClick={() => {
        setScenVisible(true);
      }}
    >
      新增场景
    </Button>,
  ];

  const menu = (res) => (
    <Menu>
      <Menu.Item key="release">
        <a
          onClick={() => {
            publishMoId(res);
          }}
        >
          发布
        </a>
      </Menu.Item>
      <Menu.Item key="releasehis">
        <a
          onClick={() => {
            setReProVisible(true);
            setRowMkey(res.mkey);
          }}
        >
          发布历史
        </a>
      </Menu.Item>
      <Menu.Item key="set">
        <a
          onClick={() => {
            processCon(res);
          }}
        >
          规则设置
        </a>
      </Menu.Item>
    </Menu>
  );

  const columns = [
    {
      title: '流程ID',
      dataIndex: 'id',
      key: 'id',
      width: 100,
      search: false,
    },

    {
      title: '场景名称',
      dataIndex: 'name',
      width: 220,
      ellipsis: true,
      key: 'name',
    },
    {
      title: '场景编码',
      dataIndex: 'mkey',
      width: 180,
      ellipsis: true,
      key: 'mkey',
    },
    {
      title: '流程编码',
      dataIndex: 'actModelId',
      search: false,
      key: 'actModelId',
    },
    {
      title: '发起角色名称',
      dataIndex: 'roleName',
      key: 'roleName',
      width: 200,
      ellipsis: true,
      search: false,
    },

    {
      title: '创建人',
      dataIndex: 'createdBy',
      key: 'createdBy',
      width: 100,
    },
    {
      title: '创建日期',
      dataIndex: 'createTime',
      key: 'createTime',
      valueType: 'dateRange',
      render: (_, record) => {
        return record.createTime ? record.createTime : '';
      },
    },
    {
      title: '操作',
      dataIndex: 'action',
      search: false,
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                setProcess(record);
              }}
            >
              配置流程
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

  const addActivi = {
    visible,
    onCancel: (vis) => {
      setVisible(vis);
    },
    loadQuery: () => {
      query(filter);
    },
  };

  const addScenes = {
    visible: scenVisible,
    onCancel: (vis) => {
      setScenVisible(vis);
    },
    loadQuery: () => {
      query(filter);
    },
  };

  const reProcess = {
    rowMkey,
    visible: reProVisible,
    onCancel: (vis) => {
      setReProVisible(vis);
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
        onSubmit={(value) => {
          const params = {
            ...value,
            beginTime: value.createTime ? value.createTime[0] : '',
            endTime: value.createTime ? value.createTime[1] : '',
          };
          query({ ...params });
        }}
        onReset={() => {
          query({});
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
      />
      <AddPro {...addActivi} />
      <AddSc {...addScenes} />
      <RePro {...reProcess} />
    </PageContainer>
  );
};

export default connect(({ actModal, loading }) => ({
  actModal,
  loading: loading.effects['actModal/query'],
}))(ProcessManage);
