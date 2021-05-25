import React, { useState, useEffect } from 'react';
import { Modal, Row, Col, Form, Button, Input, message, Select, Radio } from 'antd';
import EndDateTime from '@/components/EndDateTime/index';
import moment from 'moment';
import TextArea from 'antd/lib/input/TextArea';
import { addAndUpdata } from '../service';

const FormItem = Form.Item;
const { Option } = Select;

const dateFormat = 'YYYY-MM-DD HH:mm:ss';

const AddModalForm = ({ id, data, ifView, visible, onCancel, onload }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (!id) {
      return;
    }
    form.setFieldsValue({
      ...data,
      expiryDate: data.expiryDate ? moment(data.expiryDate) : '',
    });
  }, [id]);

  const handleCancel = () => {
    onCancel(false);
    form.resetFields();
  };

  // 新增模型提交
  const handleOk = async () => {
    const addForm = await form.validateFields();
    setLoading(true);
    let params;
    if (id) {
      params = {
        ...addForm,
        id,
        expiryDate: addForm.expiryDate ? moment(addForm.expiryDate).format(dateFormat) : '',
      };
    } else {
      params = {
        ...addForm,
        expiryDate: addForm.expiryDate ? moment(addForm.expiryDate).format(dateFormat) : '',
      };
    }
    const response = await addAndUpdata(params);
    const { code, msg } = response;
    if (code === 200) {
      onload();
      handleCancel();
    }
    if (code === 500) {
      message.success(msg);
    }
    setLoading(false);
  };

  return (
    <Modal
      visible={visible}
      title="模型维护"
      onCancel={handleCancel}
      width={1000}
      footer={
        ifView
          ? null
          : [
              <Button key="back" onClick={handleCancel}>
                返回
              </Button>,
              <Button key="submit" type="primary" loading={loading} onClick={handleOk}>
                提交
              </Button>,
            ]
      }
    >
      <Form form={form} layout="vertical">
        <Row gutter={64}>
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
              <Input
                disabled={ifView || data.publish === '1'}
                maxLength={32}
                placeholder="长度小于32位"
              />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="行业类型"
              name="indusType"
              rules={[
                {
                  required: true,
                  message: '行业类型不能为空！',
                },
              ]}
            >
              <Select disabled={ifView || data.publish === '1'}>
                <Option value={1}>企业类</Option>
                <Option value={2}>政府背景类</Option>
                <Option value={3}>社会组织类</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="有无财报"
              name="financial"
              rules={[
                {
                  required: true,
                  message: '有无财报不能为空！',
                },
              ]}
            >
              <Radio.Group disabled={ifView || data.publish === '1'}>
                <Radio value="1">有财报</Radio>
                <Radio value="0">无财报</Radio>
              </Radio.Group>
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="新/老客户"
              name="ifOld"
              rules={[
                {
                  required: true,
                  message: '是否新老客户不能为空！',
                },
              ]}
            >
              <Radio.Group disabled={ifView || data.publish === '1'}>
                <Radio value={1}>新客户</Radio>
                <Radio value={0}>老客户</Radio>
              </Radio.Group>
            </FormItem>
          </Col>
        </Row>
        <Row gutter={64}>
          <Col span={24}>
            <FormItem
              label="模型描述"
              name="description"
              rules={[
                {
                  required: false,
                  message: '模型描述不能为空！',
                },
              ]}
            >
              <TextArea
                rows={2}
                disabled={ifView || data.publish === '1'}
                maxLength={128}
                placeholder="长度小于128位"
              />
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};

export default AddModalForm;
