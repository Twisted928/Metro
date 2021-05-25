/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable @typescript-eslint/no-unused-vars */
import { ExclamationCircleOutlined } from '@ant-design/icons';
import { Button, Divider, Modal, message } from 'antd';
import React, { useState, useEffect, useRef, Fragment } from 'react';
import { connect, useAccess, Access, request } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { download, getDicts } from '@/services/commom';

import CreateForm from './components/CreateForm';

const { confirm } = Modal;

const RoleComponent = ({ dispatch, notice: { list, pagination, filter }, loading }) => {
  const actionRef = useRef();
  const formRef = useRef();
  const [row, setRow] = useState();
  const [typeOptions, setTypeOptions] = useState([]);
  const [statusOptions, setStatusOptions] = useState([]);
  const [visible, setVisible] = useState(false);
  const access = useAccess();

  const query = (param = {}) => {
    dispatch({
      type: 'notice/query',
      payload: param,
    });
  };

  const queryDict = () => {
    getDicts('sys_notice_status').then((res) => {
      if (res.code === 200) {
        setStatusOptions(res.data);
      }
    });

    getDicts('sys_notice_type').then((res) => {
      if (res.code === 200) {
        setTypeOptions(res.data);
      }
    });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
    queryDict();
  }, []);

  const columns = [
    {
      title: '序号',
      dataIndex: 'noticeId',
      search: false,
      width: 80,
    },
    {
      title: '标题',
      dataIndex: 'noticeTitle',
      width: 300,
      ellipsis: true,
    },
    // {
    //   title: '通知内容',
    //   dataIndex: 'noticeContent',
    //   width: 200,
    //   ellipsis: true,
    //   search: false,
    //   render: (_, record) => {
    //     return <p dangerouslySetInnerHTML={{ __html: record.noticeContent }} />;
    //   },
    // },
    {
      title: '状态',
      dataIndex: 'status',
      valueEnum: {
        0: {
          text: '待公示',
        },
        1: {
          text: '公示中',
        },
        2: {
          text: '已删除',
        },
        3: {
          text: '公到期失效示中',
        },
      },
    },

    {
      title: '创建人',
      dataIndex: 'createdBy',
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      valueType: 'dateRange',
      width: 180,
      render: (_, record) => {
        return record.createTime || '-';
      },
    },
    {
      title: '更新人',
      dataIndex: 'updatedBy',
      search: false,
    },
    {
      title: '更新时间',
      dataIndex: 'updateTime',
      valueType: 'dateTime',
      search: false,
      width: 180,
    },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => (
        <Fragment>
          <a
            onClick={() => {
              setRow(record);
              setVisible(true);
            }}
          >
            修改
          </a>
          <Divider type="vertical" />
          <a
            onClick={() => {
              confirm({
                title: `是否确认删除公告标题为"${record.noticeTitle}"的数据项?`,
                icon: <ExclamationCircleOutlined />,
                onOk() {
                  return request(`/system/role/${record.noticeId}`, {
                    method: 'DELETE',
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
            }}
          >
            删除
          </a>
        </Fragment>
      ),
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download('/system/role/export', filter);
      }}
    >
      导出
    </Button>,
    <Access key="add" accessible={access.compAccess('message')}>
      <Button
        type="primary"
        onClick={() => {
          setRow({});
          setVisible(true);
        }}
      >
        新建
      </Button>
    </Access>,
  ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        headerTitle="查询列表"
        actionRef={actionRef}
        formRef={formRef}
        rowKey="noticeId"
        options={false}
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
        toolBarRender={toolBarRender}
        dataSource={list}
        pagination={pagination}
        loading={loading}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        columns={columns}
      />
      {!!row && (
        <CreateForm
          row={row}
          visible={visible}
          onClose={() => {
            setRow(undefined);
            query(filter);
          }}
        />
      )}
    </PageContainer>
  );
};

export default connect(({ notice, loading }) => ({
  notice,
  loading: loading.effects['notice/query'],
}))(RoleComponent);
