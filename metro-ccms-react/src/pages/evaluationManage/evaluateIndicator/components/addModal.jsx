import React, { useState, useEffect } from 'react';
import { QuestionCircleOutlined } from '@ant-design/icons';
import { Modal, Row, Col, Form, Button, Input, Select, Tooltip, InputNumber } from 'antd';
import { addAndUpdataList } from '../service';

const FormItem = Form.Item;
const { Option } = Select;
const { TextArea } = Input;

const AddModalForm = ({ visible, onCancel, loadQuery, formData, ifView }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const handleCancel = () => {
    onCancel(false);
    form.resetFields();
  };

  useEffect(() => {
    if (formData.id) {
      form.setFieldsValue(formData);
    }
  }, [formData]);

  // 新增指标提交
  const handleOk = async () => {
    const addForm = await form.validateFields();
    setLoading(true);
    let params;
    if (formData.id) {
      params = {
        ...formData,
        ...addForm,
      };
    } else {
      params = {
        ...addForm,
      };
    }
    const response = await addAndUpdataList(params);
    if (response.code === 200) {
      loadQuery();
      handleCancel();
    }
    setLoading(false);
  };

  return (
    <Modal
      visible={visible}
      title="指标维护"
      onCancel={handleCancel}
      width={1000}
      footer={
        !ifView
          ? [
              <Button key="back" onClick={handleCancel}>
                返回
              </Button>,
              <Button key="submit" type="primary" loading={loading} onClick={handleOk}>
                提交
              </Button>,
            ]
          : ''
      }
    >
      <Form form={form} layout="vertical">
        <Row gutter={64}>
          <Col span={8}>
            <FormItem
              label="指标大类"
              name="type"
              rules={[
                {
                  required: true,
                  message: '指标大类不能为空！',
                },
              ]}
            >
              <Select disabled={ifView}>
                <Option key="A" value="A">
                  定性指标
                </Option>
                <Option key="B" value="B">
                  财务指标
                </Option>
                <Option key="C" value="C">
                  交易指标
                </Option>
              </Select>
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="指标名称"
              name="name"
              rules={[
                {
                  required: true,
                  message: '指标名称不能为空！',
                },
              ]}
            >
              <Input disabled={ifView} maxLength={32} placeholder="长度小于32位" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="打分方法"
              name="method"
              rules={[
                {
                  required: true,
                  message: '打分方法不能为空！',
                },
              ]}
            >
              <Select disabled={ifView}>
                <Option value="2">区间打分法</Option>
                <Option value="1">逻辑判断法</Option>
              </Select>
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="指标描述"
              name="description"
              rules={[
                {
                  required: true,
                  message: '指标描述不能为空！',
                },
              ]}
            >
              <Input disabled={ifView} maxLength={128} placeholder="长度小于128位" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="排序"
              name="orderId"
              rules={[
                {
                  required: false,
                  message: '备注不能为空！',
                },
              ]}
            >
              <InputNumber disabled={ifView} maxLength={16} placeholder="长度小于16位" />
            </FormItem>
          </Col>
          <Col span={24}>
            <FormItem
              label="备注"
              name="remark"
              rules={[
                {
                  required: false,
                  message: '备注不能为空',
                },
              ]}
            >
              <TextArea disabled={ifView} rows={2} maxLength={128} placeholder="长度小于128位" />
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};

export default AddModalForm;
