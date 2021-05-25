import React, { useEffect, useState } from 'react';
import { Modal, message, Form, Select, Input, Row, Col, DatePicker } from 'antd';
import moment from 'moment';
import { create } from '../service';
import Styles from '../index.less';

const { Option } = Select;
const { TextArea } = Input;
const { RangePicker } = DatePicker;
const FormItem = Form.Item;
const key = 'editForm';
const dateFormat = 'YYYY-MM-DD';

const BasicForm = ({ visible, onClose, payload }) => {
  const [form] = Form.useForm();
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    const { custcode, custname } = payload;
    form.setFieldsValue({
      custcode,
      custname,
      status: '1',
    });
  }, [payload]);

  const onFinish = async (values) => {
    message.loading({ content: '正在提交', key, duration: 1 });
    setSubmitting(true);

    const thisParams = {
      custcode: values.custcode,
      custname: values.custname,
      status: values.status,
      reason: values.reason,
      validfrom: values.validRange[0] && moment(values.validRange[0]).format(dateFormat),
      validto: values.validRange[1] && moment(values.validRange[1]).format(dateFormat),
    };

    const { code, msg } = await create(thisParams);

    setSubmitting(false);
    if (code === 200) {
      message.success(msg);
      onClose();
    } else {
      message.error(msg);
    }
  };

  const onFinishFailed = (errorInfo) => {
    // eslint-disable-next-line no-console
    console.log('Failed:', errorInfo);
    message.info(errorInfo);
  };

  return (
    <Modal
      title="新增"
      width={1000}
      className={Styles.modalWrap}
      destroyOnClose
      confirmLoading={submitting}
      visible={visible}
      onOk={() => {
        form.submit();
      }}
      onCancel={() => {
        onClose();
        form.resetFields();
      }}
    >
      <Form
        form={form}
        layout="vertical"
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        initialValues={{
          status: 1,
        }}
      >
        <Row gutter={24}>
          <Col span={8} key="custcode">
            <FormItem name="custcode" label="客户编码">
              <Input disabled />
            </FormItem>
          </Col>

          <Col span={8} key="custname">
            <FormItem name="custname" label="客户名称">
              <Input disabled />
            </FormItem>
          </Col>

          <Col span={8} key="validRange">
            <FormItem
              name="validRange"
              label="生效区间"
              rules={[
                {
                  required: true,
                  message: '请选择生效区间！',
                },
              ]}
            >
              <RangePicker />
            </FormItem>
          </Col>
        </Row>

        <Row gutter={24}>
          <Col span={8} key="status">
            <FormItem name="status" label="状态">
              <Select disabled>
                <Option value="0">无效</Option>
                <Option value="1">有效</Option>
              </Select>
            </FormItem>
          </Col>
        </Row>
        <Row className={Styles.reasonCol}>
          <Col span={24} key="reason">
            <FormItem name="reason" label="原因">
              <TextArea rows={4} />
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};

export default BasicForm;
