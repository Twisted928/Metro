import React, { useState, useRef, Fragment } from 'react';
import { Button, Divider, Card, Form, Row, Col, Input, Upload, message, Radio } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history } from 'umi';
import ProTable from '@ant-design/pro-table';
import FooterToolbar from '@/components/FooterToolbar';
import Styles from '../index.less';

const FormItem = Form.Item;

const SafeguardForm = () => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [fileList, setFile] = useState([]);

  const onFinish = () => {};
  const onFinishFailed = () => {};

  const deleteFilled = (data) => {
    const newArr = fileList;
    const { id } = data;
    const result = newArr.filter((item) => {
      return item.id !== id;
    });
    setFile(result);
  };

  const props = {
    name: 'file',
    multiple: true,
    showUploadList: false,
    onChange(info) {
      const { status } = info.file;
      if (status === 'done') {
        message.success(`${info.file.name} 上传成功！`);
        const { name, size } = info.file;
        const fileUpList = {
          id: size,
          attachmentName: name,
          attachitems: name,
          attachmentUrl: 'http://localhost:8001/safeguard/Lossmanagement/createForm',
        };
        setFile([...fileList, fileUpList]);
      } else if (status === 'error') {
        message.error(`${info.file.name} 上传失败！`);
      }
    },
  };

  const toolBarUploadRender = (
    <Upload {...props}>
      <Button type="primary">附件上传</Button>
    </Upload>
  );

  const columnsUpload = [
    {
      title: '附件信息',
      dataIndex: 'attachitems',
    },
    {
      title: '附件名称',
      dataIndex: 'attachmentName',
    },
    {
      title: '附件地址',
      dataIndex: 'attachmentUrl',
    },
    {
      title: '操作',
      dataIndex: 'action',
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                deleteFilled(record);
              }}
            >
              删除
            </a>
            <Divider type="vertical" />
            <a>下载</a>
          </Fragment>
        );
      },
    },
  ];

  const columnsReview = [
    {
      title: '审批人',
      dataIndex: 'approvedBy',
      key: 'approvedBy',
    },
    {
      title: '生效日',
      dataIndex: 'validForm',
      key: 'validForm',
    },
    {
      title: '到期日',
      dataIndex: 'validTo',
      key: 'validTo',
    },
    {
      title: '递延到期日',
      dataIndex: 'delayDate',
      key: 'delayDate',
    },
    {
      title: '审批时间',
      dataIndex: 'approveTime',
      key: 'approveTime',
    },
    {
      title: '审批状态',
      dataIndex: 'approveStatus',
      key: 'approveStatus',
    },
    {
      title: '描述',
      dataIndex: 'desc',
      key: 'desc',
    },
    {
      title: '是否特批',
      dataIndex: 'ifSpecial',
      key: 'ifSpecial',
    },
    {
      title: '角色',
      dataIndex: 'roleName',
      key: 'roleName',
    },
  ];

  return (
    <PageContainer>
      <Card title="单据信息">
        <Form
          form={form}
          layout="vertical"
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
          initialValues={{
            approveStatus: 1,
          }}
        >
          <Row gutter={64}>
            <Col span={8}>
              <FormItem
                label="合同名称"
                name="contractName"
                rules={[
                  {
                    required: true,
                    message: '请输入合同名称',
                  },
                ]}
              >
                <Input placeholder="请输入合同名称" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="合同编码"
                name="contractNo"
                rules={[
                  {
                    required: true,
                    message: '请输入合同编码',
                  },
                ]}
              >
                <Input placeholder="请输入合同编码" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="合同类型"
                name="contractType"
                rules={[
                  {
                    required: true,
                    message: '请输入合同类型',
                  },
                ]}
              >
                <Input placeholder="请输入合同类型" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="审批状态"
                name="approveStatus"
                rules={[
                  {
                    required: true,
                    message: '请选择审批状态！',
                  },
                ]}
              >
                <Radio.Group>
                  <Radio value={0}>审批中</Radio>
                  <Radio value={1}>审批通过</Radio>
                  <Radio value={2}>审批退回</Radio>
                </Radio.Group>
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
      <Card className={Styles.extraStyle} title="合同复核" style={{ marginTop: '24px' }}>
        <ProTable
          headerTitle=""
          rowKey="id"
          bordered
          dataSource={[]}
          search={false}
          actionRef={ref}
          options={false}
          toolBarRender={false}
          pagination={false}
          columns={columnsReview}
        />
      </Card>
      <Card
        className={Styles.extraStyle}
        title="附件信息"
        extra={toolBarUploadRender}
        style={{ marginTop: '24px' }}
      >
        <ProTable
          headerTitle=""
          rowKey="id"
          bordered
          dataSource={fileList}
          search={false}
          actionRef={ref}
          options={false}
          toolBarRender={false}
          pagination={false}
          columns={columnsUpload}
        />
      </Card>
      <FooterToolbar>
        <Button
          key="back"
          onClick={() => {
            history.goBack();
          }}
        >
          返回
        </Button>
        <Button type="primary" htmlType="submit">
          通过
        </Button>
        <Button type="primary" htmlType="submit">
          驳回
        </Button>
      </FooterToolbar>
    </PageContainer>
  );
};

export default SafeguardForm;
