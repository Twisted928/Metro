import {
  EditOutlined,
  DeleteOutlined,
  ExclamationCircleOutlined,
  EyeOutlined,
  VerticalAlignBottomOutlined,
} from '@ant-design/icons';
import { Button, Drawer, Space, Modal, message } from 'antd';
import React, { useState, useRef } from 'react';
import { request, history } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { downLoadZip } from '@/services/commom';
import { importTable } from './service';

import Preview from './components/Preview';

const { confirm } = Modal;

const GenComponent = () => {
  const ref = useRef();
  const [row, setRow] = useState();
  const [visible, setVisible] = useState(false);
  const [selectedRow, setSelectedRowKeys] = useState([]);

  const importTableBtn = async () => {
    message.loading('导入中');
    const { code, msg } = await importTable(selectedRow.join());
    if (code === 200) {
      message.success('完成');
      setVisible(false);
      ref.current.reload();
    } else {
      message.error(msg);
    }
  };

  const columns = [
    {
      title: '编码',
      dataIndex: 'tableId',
      hideInSearch: true,
    },
    {
      title: '表名称',
      dataIndex: 'tableName',
    },
    {
      title: '描述',
      dataIndex: 'tableComment',
    },
    {
      title: '实体',
      dataIndex: 'className',
    },

    {
      title: '创建时间',
      dataIndex: 'createTime',
      valueType: 'dateTime',
    },

    {
      title: '更新时间',
      dataIndex: 'updateTime',
      valueType: 'dateTime',
    },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => (
        <Space>
          <a
            onClick={() => {
              setRow(record);
            }}
          >
            <EyeOutlined /> 预览
          </a>
          <a
            onClick={() => {
              downLoadZip(`/tool/gen/batchGenCode?tables=${record.tableName}`);
            }}
          >
            <VerticalAlignBottomOutlined />
            下载
          </a>

          <a
            onClick={() => {
              history.push({
                pathname: '/tool/gen/edit',
                query: {
                  tableId: record.tableId,
                },
              });
            }}
          >
            <EditOutlined /> 修改
          </a>
          <a
            onClick={() => {
              confirm({
                title: `是否确认删除表名称为"${record.tableName}"的数据项?`,
                icon: <ExclamationCircleOutlined />,
                onOk() {
                  return request(`/tool/gen/${record.tableId}`, {
                    method: 'DELETE',
                  }).then((res) => {
                    if (res.code === 200) {
                      message.success(res.msg);
                      ref.current.reload();
                    } else {
                      message.error(res.msg);
                    }
                  });
                },
                onCancel() {},
              });
            }}
          >
            <DeleteOutlined /> 删除
          </a>
        </Space>
      ),
    },
  ];

  const columnsR = [
    {
      title: '表名称',
      dataIndex: 'tableName',
    },
    {
      title: '描述',
      dataIndex: 'tableComment',
    },
    {
      title: '创建时间',
      hideInSearch: true,
      dataIndex: 'createTime',
    },
    {
      title: '更新时间',
      hideInSearch: true,
      dataIndex: 'updateTime',
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        setVisible(true);
      }}
    >
      导入
    </Button>,
  ];

  return (
    <PageContainer>
      <ProTable
        headerTitle="查询表格"
        rowKey="tableId"
        request={async (params = {}) =>
          request('/tool/gen/list', {
            params,
          }).then((response) => {
            return { ...response, page: response.current, data: response.rows };
          })
        }
        actionRef={ref}
        toolBarRender={toolBarRender}
        columns={columns}
        onLoad={() => {}}
      />

      <Drawer
        title="代码预览"
        width={1200}
        destroyOnClose
        visible={!!row}
        onClose={() => {
          setRow(undefined);
        }}
        closable={false}
      >
        <Preview
          row={row}
          onClose={() => {
            setRow(undefined);
          }}
        />
      </Drawer>

      <Modal
        title="导入表"
        width={1000}
        visible={visible}
        onOk={() => {
          importTableBtn();
        }}
        onCancel={() => {
          setVisible(false);
        }}
      >
        <ProTable
          columns={columnsR}
          pagination={{
            showQuickJumper: true,
          }}
          request={async (params = {}) =>
            request('/tool/gen/db/list', {
              params,
            }).then((response) => {
              return { ...response, page: response.current, data: response.rows };
            })
          }
          search={{
            span: 8,
          }}
          rowSelection={{
            columnWidth: 50,
            selectedRow,
            onChange: (selectedRowKeys) => {
              setSelectedRowKeys(selectedRowKeys);
            },
          }}
          tableAlertRender={false}
          options={false}
          rowKey="tableName"
          dateFormatter="string"
          headerTitle={null}
          scroll={{ y: 300 }}
        />
      </Modal>
    </PageContainer>
  );
};

export default GenComponent;
