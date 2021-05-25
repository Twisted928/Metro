import React, { useState, useEffect, Fragment } from 'react';
import { Button, InputNumber, Card, Form, Row, Col, Input, message, Radio, Spin } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history } from 'umi';
import ProTable from '@ant-design/pro-table';
import FooterToolbar from '@/components/FooterToolbar';
import { downloadFile } from '@/services/commom';
import { detailsList } from '../service';

import Styles from '../index.less';

const FormItem = Form.Item;

const SafeguardForm = () => {
  const [form] = Form.useForm();
  const [pageloading, setPageLoading] = useState(false);
  const [fileList, setFile] = useState([]);

  const getDetails = async () => {
    const { id } = history.location.query;
    setPageLoading(true);
    const response = await detailsList({ id });
    const { code, data, msg } = response;
    if (code === 200) {
      form.setFieldsValue(data);
      setFile(data.sysBasicFile);
    } else {
      message.error(msg);
    }
    setPageLoading(false);
  };

  useEffect(() => {
    getDetails();
  }, []);

  // 下载
  const download = async (record) => {
    const { attachmentName, attachmentUrl } = record;
    const params = {
      fileName: attachmentName,
      filePath: attachmentUrl,
    };
    await downloadFile('/file/downAndUpload/downloadFile', params);
  };

  // 附件
  const columnsUpload = [
    {
      title: '附件名称',
      dataIndex: 'attachmentName',
      key: 'attachmentName',
    },
    {
      title: '操作',
      dataIndex: 'action',
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                download(record);
              }}
            >
              下载
            </a>
          </Fragment>
        );
      },
    },
  ];

  return (
    <PageContainer title={false} ghost>
      <Spin spinning={pageloading}>
        <Card title="单据信息">
          <Form
            form={form}
            layout="vertical"
            initialValues={{
              status: 1,
            }}
          >
            <Row gutter={64}>
              <Col span={8}>
                <FormItem
                  label="对账单号"
                  name="applicationNo"
                  rules={[
                    {
                      required: true,
                      message: '请输入催收单号',
                    },
                  ]}
                >
                  <Input placeholder="" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="部门编码"
                  name="departCode"
                  rules={[
                    {
                      required: true,
                      message: '请输入部门编码',
                    },
                  ]}
                >
                  <Input placeholder="" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="部门名称"
                  name="departName"
                  rules={[
                    {
                      required: true,
                      message: '请选择部门名称',
                    },
                  ]}
                >
                  <Input placeholder="" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="门店编码"
                  name="storeCode"
                  rules={[
                    {
                      required: true,
                      message: '请输入门店编码',
                    },
                  ]}
                >
                  <Input placeholder="" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="客户编码"
                  name="custCode"
                  rules={[
                    {
                      required: true,
                      message: '请输入客户编码',
                    },
                  ]}
                >
                  <Input placeholder="" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="客户名称"
                  name="custName"
                  rules={[
                    {
                      required: true,
                      message: '请输入客户名称',
                    },
                  ]}
                >
                  <Input placeholder="" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="卡号编码"
                  name="cardCode"
                  rules={[
                    {
                      required: true,
                      message: '请输入卡号编码',
                    },
                  ]}
                >
                  <Input placeholder="" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="卡号名称"
                  name="cardName"
                  rules={[
                    {
                      required: true,
                      message: '请输入卡号名称',
                    },
                  ]}
                >
                  <Input placeholder="" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="应收金额（万元）"
                  name="iar"
                  rules={[
                    {
                      required: true,
                      message: '请输入应收金额',
                    },
                    {
                      validator: (_, value) =>
                        value < 0
                          ? Promise.reject(new Error('应收金额不能为负数！'))
                          : Promise.resolve(),
                    },
                  ]}
                >
                  <InputNumber placeholder="" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="逾期金额（万元）"
                  name="idue"
                  rules={[
                    {
                      required: true,
                      message: '请输入逾期金额',
                    },
                    {
                      validator: (_, value) =>
                        value < 0
                          ? Promise.reject(new Error('逾期金额不能为负数！'))
                          : Promise.resolve(),
                    },
                  ]}
                >
                  <InputNumber placeholder="" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="补充说明"
                  name="desc"
                  rules={[
                    {
                      required: true,
                      message: '请选择补充说明',
                    },
                  ]}
                >
                  <Input placeholder="" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="状态"
                  name="status"
                  rules={[
                    {
                      required: true,
                      message: '请选择状态',
                    },
                  ]}
                >
                  <Radio.Group disabled>
                    <Radio value={1}>对账中</Radio>
                    <Radio value={2}>对账完成</Radio>
                    <Radio value={3}>对账差异</Radio>
                  </Radio.Group>
                </FormItem>
              </Col>
            </Row>
          </Form>
        </Card>
        <Card className={Styles.extraStyle} title="附件信息" style={{ marginTop: '24px' }}>
          <ProTable
            headerTitle=""
            rowKey="id"
            bordered
            dataSource={fileList}
            search={false}
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
        </FooterToolbar>
      </Spin>
    </PageContainer>
  );
};

export default SafeguardForm;
