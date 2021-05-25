import React, { useState, useRef } from 'react';
import { DownOutlined, ExclamationCircleOutlined, PlusOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card';
import {
  Form,
  Table,
  Upload,
  Divider,
  Modal,
  Button,
  message,
  Row,
  Col,
  Input,
  Select,
  DatePicker,
} from 'antd';
import { request } from 'umi';
import Styles from './style.less';

const { Option } = Select;
const { confirm } = Modal;

const ContractInfo = () => {
  const [form] = Form.useForm();
  const formRef = useRef();
  const actionRef = useRef();

  const [collapsed, setCollapsed] = useState(false);

  const columns = [
    {
      title: '附件名称',
      dataIndex: 'builder',
    },
    {
      title: '附件类型',
      dataIndex: 'buildDate',
    },
    {
      title: '上传人',
      dataIndex: 'attachName',
    },
    {
      title: '上传时间',
      dataIndex: 'attachType',
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

  const columnsApprove = [
    {
      title: '审批人',
      dataIndex: 'APPROVED_BY',
    },
    {
      title: '生效日',
      dataIndex: 'APPROVE_STATUS',
      valueType: 'date',
    },
    {
      title: '到期日',
      dataIndex: 'APPROVE_TIME',
      valueType: 'date',
    },
    {
      title: '递延到期日',
      dataIndex: 'DESC',
      valueType: 'date',
    },
    {
      title: '审批时间',
      dataIndex: 'IF_SPECIAL',
      valueType: 'date',

    },
    {
      title: '审批状态',
      dataIndex: 'APPROVED_BY',
    },
    {
      title: '描述',
      dataIndex: 'APPROVE_STATUS',
      valueType: 'date',
    },
    {
      title: '是否特批',
      dataIndex: 'APPROVE_TIME',
      valueType: 'date',
    },
    {
      title: '角色',
      dataIndex: 'DESC',
      valueType: 'date',
    },
    {
      title: '审批时间',
      dataIndex: 'IF_SPECIAL',
      valueType: 'date',

    },
    {
      title: '角色',
      dataIndex: 'ROLE_NAME',
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
    <div id="ContractInfo">
      <ProCard
        title="合同信息"
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
        <div>
          <h3 className={Styles.formLabel}> 合同信息</h3>
        </div>
        <Form form={form} layout="vertical">
          <Row gutter={64}>
            <Col span={8} key={1}>
              <Form.Item
                label="合同名称"
                name="CONTRACT_NO"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={8} key={2}>
              <Form.Item
                label="合同编码"
                name="CONTRACT_NAME"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={8} key={3}>
              <Form.Item
                label="合同类型"
                name="CONTRACT_TYPE"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input disabled />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={64}>
            <Col span={8} key={4}>
              <Form.Item
                label="审批人"
                name="SALES_TARGET"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={8} key={5}>
              <Form.Item
                label="审批状态"
                name="VALID_FROM"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Select disabled>
                  <Option value={0}>审批中</Option>
                  <Option value={1}>审批通过</Option>
                  <Option value={2}>审批退回</Option>
                </Select>
              </Form.Item>
            </Col>
            <Col span={8} key={6}>
              <Form.Item
                label="审批时间"
                name="VALID_TO"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <DatePicker disabled />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={64}>
            <Col span={8} key={7}>
              <Form.Item
                label="流程实例ID"
                name="DELAY_DATE"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={8} key={8}>
              <Form.Item
                label="审批流编号"
                name="EXTENSION_SIGN"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input disabled />
              </Form.Item>
            </Col>
          </Row>
        </Form>

        <Table
          bordered
          actionRef={actionRef}
          formRef={formRef}
          pagination={false}
          align="center"
          columns={columns}
        />
        <Upload {...props}>
          <Button className={Styles.uploadButton} icon={<PlusOutlined />}>
            上传附件
          </Button>
        </Upload>

        <div style={{ paddingTop: 10 }}>
          <h3 className={Styles.formLabel}> 合同复核</h3>
        </div>
        <Table
          actionRef={actionRef}
          formRef={formRef}
          columns={columnsApprove}
          bordered
          align="center"
          pagination={false}
          scroll={{ x: 2000 }}
        />
      </ProCard>
    </div>
  );
};

export default ContractInfo;
