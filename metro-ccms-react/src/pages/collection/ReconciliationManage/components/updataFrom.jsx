import React, { useState, useEffect } from 'react';
import { Button, InputNumber, Card, Form, Row, Col, Input, message, Radio, Spin } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history } from 'umi';
import FooterToolbar from '@/components/FooterToolbar';
import { promiseAll, deleteFile, deleteUploadList } from '@/services/commom';
import UpdataFile from '@/components/UpdataFile';
import { detailsList, updataData } from '../service';

const FormItem = Form.Item;

const SafeguardForm = () => {
  const [form] = Form.useForm();
  const [pageloading, setPageLoading] = useState(false);
  const [fileList, setFile] = useState([]);
  const [fileNew, setFileList] = useState([]);
  const [fileDelList, setFileDelList] = useState([]); // 删除的附件
  const [mainId, setMainId] = useState(null);
  const [submitLoading, setSubmitLoading] = useState(false);

  const getDetails = async () => {
    const { id } = history.location.query;
    setMainId(id);
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

  const updataMessage = async () => {
    const formData = await form.validateFields();
    setSubmitLoading(true);
    const { status, desc } = formData;
    const params = {
      id: mainId,
      status,
      desc,
    };
    const response = await updataData(params);
    const { code, msg } = response;
    if (code === 200) {
      if (!fileNew.length && !fileDelList.length) {
        setSubmitLoading(false);
        history.goBack();
        return;
      }
      if (fileDelList.length && fileNew.length) {
        deleteFile(fileDelList);
        promiseAll(mainId, 11, fileNew);
        history.goBack();
        return;
      }
      if (fileNew.length && !fileDelList.length) {
        promiseAll(mainId, 11, fileNew);
        history.goBack();
        return;
      }
      if (fileDelList.length && !fileNew.length) {
        deleteFile(fileDelList);
        history.goBack();
        return;
      }
      setSubmitLoading(false);
    }
    if (code === 500) {
      message.error(msg);
      setSubmitLoading(false);
    }
  };

  const getNewListDel = (id) => {
    const newArr = fileNew;
    const newFileList = newArr.filter((item) => {
      return item.uid !== id;
    });
    return newFileList;
  };

  const updataFile = {
    downloadBtu: true,
    dataSource: fileList,
    loadFun: (id, obj) => {
      setFile(deleteUploadList(id, fileList));
      setFileDelList([...fileDelList, obj]);
      setFileList(getNewListDel(id));
    },
    changeFun: (file, fileUpList) => {
      setFileList([...fileNew, file]);
      setFile([...fileList, fileUpList]);
    },
    mainId,
    type: 11,
  };

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
                  <Input placeholder="" />
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
                  <Input placeholder="" />
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
                  <Input placeholder="" />
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
                  <Input placeholder="" />
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
                  <Input placeholder="" />
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
                  <Input placeholder="" />
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
                  <Input placeholder="" />
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
                  <Input placeholder="" />
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
                  <InputNumber placeholder="" />
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
                  <InputNumber placeholder="" />
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
                  <Input placeholder="" />
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
                  <Radio.Group>
                    <Radio value={1}>对账中</Radio>
                    <Radio value={2}>对账完成</Radio>
                    <Radio value={3}>对账差异</Radio>
                  </Radio.Group>
                </FormItem>
              </Col>
            </Row>
          </Form>
        </Card>
        <UpdataFile {...updataFile} />
        <FooterToolbar>
          <Button
            key="back"
            onClick={() => {
              history.goBack();
            }}
          >
            返回
          </Button>
          <Button
            type="primary"
            htmlType="submit"
            loading={submitLoading}
            onClick={() => {
              updataMessage();
            }}
          >
            提交
          </Button>
        </FooterToolbar>
      </Spin>
    </PageContainer>
  );
};

export default SafeguardForm;
