/* eslint-disable react-hooks/exhaustive-deps */
import { ExclamationCircleOutlined } from '@ant-design/icons';
import { Button, message, Divider, Modal } from 'antd';
import React, { useState, useEffect, useRef, Fragment } from 'react';
import { connect, useAccess, request, Access } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';
import { handleTree } from '@/utils/utils';
import CreateForm from './components/CreateForm';
import { queryMenu } from './service';

const { confirm } = Modal;

const ResourceComponent = ({ resource: { filter, treeList } }) => {
  const actionRef = useRef();
  const formRef = useRef();

  const [loading, setLoading] = useState(false);
  const [menu, setMuen] = useState([]);
  const [row, setRow] = useState();
  const [visible, setVisible] = useState(false);
  // const [relaoding, setReloading] = useState(false);
  const access = useAccess();

  const query = async (param = {}) => {
    setLoading(true);
    const response = await queryMenu(param);

    if (response.code === 200) {
      const menuList = handleTree(response.data, 'menuId');
      setMuen(menuList);
    }

    setLoading(false);
  };

  // const reloadRes = async () => {
  //   const hide = message.loading('正在刷新', 0);
  //   setReloading(true);
  //   const response = await reloadResCache();

  //   if (response.code === 200) {
  //     message.success('刷新完成');
  //   } else {
  //     message.error(response.msg);
  //   }
  //   setReloading(false);
  //   hide();
  // };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  const columns = [
    {
      title: '菜单名称',
      dataIndex: 'menuName',
      ellipsis: true,
      width: 300,
    },
    // {
    //   title: '图标',
    //   hideInSearch: true,
    //   dataIndex: 'icon',
    //   width: 100,
    // },
    {
      title: '菜单类型',
      dataIndex: 'menuType',
      width: 100,
      valueEnum: {
        M: {
          text: '目录',
        },
        C: {
          text: '菜单',
        },
        F: {
          text: '按钮',
        },
      },
    },
    {
      title: '排序',
      search: false,
      dataIndex: 'orderNum',
      width: 100,
      align: 'right',
    },
    {
      title: '状态',
      dataIndex: 'status',
      width: 100,
      valueEnum: {
        1: {
          text: '停用',
          status: 'Default',
        },
        0: {
          text: '正常',
          status: 'Processing',
        },
      },
    },
    // {
    //   title: '创建人',
    //   search: false,
    //   width: 100,
    //   dataIndex: 'createdBy',
    // },
    // {
    //   title: '创建时间',
    //   valueType: 'dateTime',
    //   search: false,
    //   width: 200,
    //   dataIndex: 'createTime',
    // },
    {
      title: '操作',
      fixed: 'right',
      dataIndex: 'option',
      search: false,
      render: (_, record) => (
        <Fragment>
          <a
            onClick={() => {
              setVisible(true);
              setRow(record);
            }}
          >
            修改
          </a>
          <Divider type="vertical" />
          <a
            onClick={() => {
              confirm({
                title: `是否确认删除菜单名称为"${record.menuName}"的数据项?`,
                icon: <ExclamationCircleOutlined />,
                onOk() {
                  return request(`/system/menu/${record.menuId}`, {
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
    // <Button key="export" onClick={reloadRes} loading={relaoding}>
    //   刷新缓存
    // </Button>,
    <Button
      key="export"
      onClick={async () => {
        const param = { ...filter };
        await download('/system/menu/export', param);
      }}
    >
      导出
    </Button>,
    <Access key="add" accessible={access.compAccess('message')}>
      <Button
        type="primary"
        onClick={() => {
          setVisible(true);
          setRow({});
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
        rowKey="menuId"
        options={false}
        onSubmit={(value) => {
          query({ ...value });
        }}
        onReset={() => {
          query({});
        }}
        search={{
          collapsed: false,
          collapseRender: false,
        }}
        toolBarRender={toolBarRender}
        dataSource={menu}
        pagination={false}
        loading={loading}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        columns={columns}
        indentSize={40}
        expandRowByClick
      />

      {!!row && (
        <CreateForm
          visible={visible}
          treeData={treeList}
          row={row}
          setRow={() => {
            setRow(undefined);
          }}
          onClose={() => {
            setVisible(false);
            query(filter);
          }}
        />
      )}
    </PageContainer>
  );
};

export default connect(({ resource }) => ({
  resource,
}))(ResourceComponent);
