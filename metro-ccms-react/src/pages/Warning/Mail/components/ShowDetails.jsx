import React, { useEffect } from 'react';
import { Modal, Form, Input, Row, Col } from 'antd';
import moment from 'moment';
import TextArea from 'antd/lib/input/TextArea';

const FormItem = Form.Item;

const BasicForm = ({ visible, onClose, payload }) => {
  const [form] = Form.useForm();

  useEffect(() => {
    // eslint-disable-next-line no-console
    console.log(payload);
    const { title, text, username, rolename, source, applicationno, ddate, modcode } = payload;
    form.setFieldsValue({
      title,
      text,
      username,
      rolename,
      source,
      applicationno,
      modcode,
      ddate: ddate && moment(ddate).format('YYYY-MM-DD'),
    });
  }, [payload]);

  return (
    <Modal
      width={1000}
      destroyOnClose
      title="详情"
      visible={visible}
      footer={false}
      onCancel={() => {
        onClose();
      }}
    >
      <Form form={form} layout="vertical" preserve={false}>
        <Row gutter={64}>
          <Col span={8} key="title">
            <FormItem name="title" label="标题">
              <Input disabled />
            </FormItem>
          </Col>
          <Col span={8} key="username">
            <FormItem name="username" label="收件人">
              <Input disabled />
            </FormItem>
          </Col>
          <Col span={8} key="rolename">
            <FormItem name="rolename" label="收件角色">
              <Input disabled />
            </FormItem>
          </Col>
        </Row>

        <Row gutter={64}>
          <Col span={8} key="source">
            <FormItem name="source" label="来源">
              <Input disabled />
            </FormItem>
          </Col>

          <Col span={8} key="applicationno">
            <FormItem name="applicationno" label="申请单号">
              <Input disabled />
            </FormItem>
          </Col>
          <Col span={8} key="ddate">
            <FormItem name="ddate" label="发送日期">
              <Input disabled />
            </FormItem>
          </Col>
          <Col span={8} key="modcode">
            <FormItem name="modcode" label="预警模型编码">
              <Input disabled />
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span={24} key="text">
            <FormItem name="text" label="邮件内容">
              <TextArea disabled />
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};

export default BasicForm;
