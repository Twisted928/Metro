import React, { useState } from 'react';
import { DownOutlined, ExclamationCircleOutlined, PlusOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card';
import { Table, Upload, Divider, Modal, Button, message } from 'antd';
import { request } from 'umi';
import Styles from './style.less';

const { confirm } = Modal;

const WittInfo = () => {
  const [collapsed, setCollapsed] = useState(false);

  const columnsInfo = [
    {
      title: '附件名称',
      dataIndex: 'ATTACHMENT_NAME',
    },
    {
      title: '附件类型',
      dataIndex: 'CTYPE',
      valueType: 'select',
    },
    {
      title: '更新人',
      dataIndex: 'UPDATED_BY',
    },
    {
      title: '更新时间',
      dataIndex: 'UPDATE_TIME',
      valueType: 'date',
    },
    {
      title: '操作',
      valueType: 'option',
      render: (_, row) => [
        <a>下载</a>,
        <Divider type="vertical" />,
        <a
          onClick={() => {
            confirm({
              title: `是否确定删除名称为"${row.appliName}"的数据项?`,
              icon: <ExclamationCircleOutlined />,
              onOk() {
                return request(`/credit/creditApply/${row.appliName}`, {
                  method: 'DELETE',
                }).then((res) => {
                  if (res.code === 200) {
                    message.success(res.msg);
                  } else {
                    message.error(res.msg);
                  }
                  // query(filter);
                });
              },
              onCancel() {},
            });
          }}
        >
          删除
        </a>,
      ],
    },
  ];

  const props = {
    name: 'file',
    // action: 'https://www.mocky.io/v2/5cc8019d300000980a055e76',
    headers: {
      authorization: 'authorization-text',
    },
    onChange(info) {
      if (info.file.status !== 'uploading') {
        // eslint-disable-next-line no-console
        console.log(info.file, info.fileList);
      }
      if (info.file.status === 'done') {
        message.success(`${info.file.name} file uploaded successfully`);
      } else if (info.file.status === 'error') {
        message.error(`${info.file.name} file upload failed.`);
      }
    },
    progress: {
      strokeColor: {
        '0%': '#108ee9',
        '100%': '#87d068',
      },
      strokeWidth: 3,
      format: (percent) => `${parseFloat(percent.toFixed(2))}%`,
    },
  };

  return (
    <div id="WittInfo">
      <ProCard
        title="见证性资料"
        extra={
          <DownOutlined
            rotate={collapsed ? undefined : 180}
            onClick={() => {
              setCollapsed(!collapsed);
            }}
          />
        }
        collapsed={collapsed}
        bordered
        headerBordered
      >
        <Table bordered pagination={false} align="center" columns={columnsInfo} />
        <div className={Styles.uploadWrap}>
          <Upload style={{ width: '100%' }} {...props}>
            <Button className={Styles.uploadButton} icon={<PlusOutlined />}>
              添加附件
            </Button>
          </Upload>
        </div>
      </ProCard>
    </div>
  );
};

export default WittInfo;
