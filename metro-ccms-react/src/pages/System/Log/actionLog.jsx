import { Button, Drawer } from 'antd';
import React, { useState, useEffect, useRef } from 'react';
import { connect } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import ProDescriptions from '@ant-design/pro-descriptions';
import { doExport } from '@/services/commom';

const LoginComponent = ({ dispatch, logininfor: { list, pagination, filter }, loading }) => {
  const actionRef = useRef();
  const formRef = useRef();
  const [row, setRow] = useState();

  const query = (param = {}) => {
    dispatch({
      type: 'logininfor/query',
      payload: param,
    });

    // dispatch({ type: 'role/queryTree' });
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const columns = [
    {
      title: '序号',
      dataIndex: 'infoId',
      hideInSearch: true,
    },
    {
      title: '用户名称',
      dataIndex: 'userName',
    },
    {
      title: '登录地址',
      dataIndex: 'ipaddr',
    },
    {
      title: '登录地点',
      dataIndex: 'loginLocation',
    },
    {
      title: '状态',
      dataIndex: 'status',
      valueEnum: {
        1: {
          text: '失效',
        },
        0: {
          text: '正常',
        },
      },
    },
    {
      title: '浏览器',
      dataIndex: 'browser',
    },
    {
      title: '操作系统',
      dataIndex: 'os',
    },
    {
      title: '提示消息',
      dataIndex: 'msg',
    },
    {
      title: '访问时间',
      dataIndex: 'loginTime',
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={async () => {
        const param = { ...filter };
        // delete param.orders;
        await doExport('/basic/sysdata/export', param);
      }}
    >
      导出
    </Button>,
  ];

  return (
    <PageContainer>
      <ProTable
        headerTitle="查询表格"
        actionRef={actionRef}
        formRef={formRef}
        rowKey="infoId"
        options={{ reload: false }}
        onSearch={(value) => {
          query({ ...value });
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

      <Drawer
        width={600}
        visible={!!row}
        onClose={() => {
          setRow(undefined);
        }}
        closable={false}
      >
        {row?.id && (
          <ProDescriptions
            column={2}
            title={row.description}
            request={async () => ({
              data: row || {},
            })}
            params={{
              id: row.description,
            }}
            columns={columns}
          />
        )}
      </Drawer>
    </PageContainer>
  );
};

export default connect(({ logininfor, loading }) => ({
  logininfor,
  loading: loading.effects['logininfor/query'],
}))(LoginComponent);
