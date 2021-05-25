import React, { useState } from 'react';
import { Modal, Row, Col, Form, Button, Input, Radio, DatePicker } from 'antd';

const FormItem = Form.Item;

const AddModalForm = ({ visible, onCancel }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const handleCancel = () => {
    onCancel(false);
  };

  // 新增模型提交
  const handleOk = async () => {
    const addForm = await form.validateFields();
    console.log(addForm);
    setLoading(true);
    setLoading(false);
  };

  return (
    <Modal
      visible={visible}
      title="新增规则"
      onCancel={handleCancel}
      width={1000}
      footer={[
        <Button key="back" onClick={handleCancel}>
          返回
        </Button>,
        <Button key="submit" type="primary" loading={loading} onClick={handleOk}>
          提交
        </Button>,
      ]}
    >
      <Form form={form} layout="vertical">
        <Row gutter={64}>
          <Col span={8}>
            <FormItem
              label="模型编码"
              name="code"
              rules={[
                {
                  required: true,
                  message: '模型编码不能为空！',
                },
              ]}
            >
              <Input placeholder="" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="模型名称"
              name="name"
              rules={[
                {
                  required: true,
                  message: '模型名称不能为空！',
                },
              ]}
            >
              <Input />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="描述"
              name="description"
              rules={[
                {
                  required: false,
                  message: '描述不能为空！',
                },
              ]}
            >
              <Input placeholder="" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="备注"
              name="remark"
              rules={[
                {
                  required: true,
                  message: '备注不能为空！',
                },
              ]}
            >
              <Input placeholder="" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="失效日期"
              name="expiryDate"
              rules={[
                {
                  required: true,
                  message: '失效日期不能为空！',
                },
              ]}
            >
              <DatePicker />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="发布状态"
              name="publish"
              rules={[
                {
                  required: true,
                  message: '请选择发布状态！',
                },
              ]}
            >
              <Radio.Group>
                <Radio value={1}>已发布</Radio>
                <Radio value={0}>未发布</Radio>
              </Radio.Group>
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="有效标记"
              name="iflag"
              rules={[
                {
                  required: true,
                  message: '请选择有效标记！',
                },
              ]}
            >
              <Radio.Group>
                <Radio value={1}>有效</Radio>
                <Radio value={0}>无效</Radio>
              </Radio.Group>
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};

export default AddModalForm;
