import React, { useState, useEffect } from 'react';
import { Modal, Row, Col, Form, Button, Input, message } from 'antd';
import EndDateTime from '@/components/EndDateTime';
import moment from 'moment';
import TextArea from 'antd/lib/input/TextArea';
import { addAndUpdataList } from '../service';

const FormItem = Form.Item;

const AddModalForm = ({ visible, onCancel, loadQuery, formData, ifView }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (formData.id) {
      form.setFieldsValue({
        ...formData,
        expirydate: formData.expirydate ? moment(formData.expirydate) : '',
      });
    }
  }, [formData]);

  const handleCancel = () => {
    onCancel(false);
    form.resetFields();
  };

  // 新增模型提交
  const handleOk = async () => {
    const addForm = await form.validateFields();
    setLoading(true);
    let params;
    if (formData.id) {
      params = {
        ...formData,
        ...addForm,
        expirydate: addForm.expirydate ? moment(addForm.expirydate).format('YYYY-MM-DD') : '',
      };
    } else {
      params = {
        ...addForm,
        expirydate: addForm.expirydate ? moment(addForm.expirydate).format('YYYY-MM-DD') : '',
      };
    }
    const response = await addAndUpdataList(params);
    const { code, msg } = response;
    if (code === 200) {
      loadQuery();
      handleCancel();
      setLoading(false);
    }
    if (code === 500) {
      message.error(msg);
      setLoading(false);
    }
  };

  return (
    <Modal
      visible={visible}
      title="规则维护"
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
              label="模型名称"
              name="name"
              rules={[
                {
                  required: true,
                  message: '模型名称不能为空！',
                },
              ]}
            >
              <Input disabled={ifView} maxLength={32} placeholder="长度小于32位" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="描述"
              name="description"
              rules={[
                {
                  required: true,
                  message: '描述不能为空！',
                },
              ]}
            >
              <Input disabled={ifView} maxLength={128} placeholder="长度小于128位" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="失效日期"
              name="expirydate"
              rules={[
                {
                  required: true,
                  message: '失效日期不能为空！',
                },
              ]}
            >
              <EndDateTime disabled={ifView} />
            </FormItem>
          </Col>
          <Col span={24}>
            <FormItem
              label="备注"
              name="remark"
              rules={[
                {
                  required: false,
                  message: '备注不能为空！',
                },
              ]}
            >
              <TextArea rows={2} disabled={ifView} maxLength={128} placeholder="长度小于128位" />
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};

export default AddModalForm;
