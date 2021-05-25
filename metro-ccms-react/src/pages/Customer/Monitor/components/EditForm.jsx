import React, { useEffect, useState } from 'react';
import { Modal, message, Form, Input, Row, Col, DatePicker, Radio } from 'antd';
import moment from 'moment';
import { update } from '../service';

const { RangePicker } = DatePicker;
const FormItem = Form.Item;
const dateFormat = 'YYYY-MM-DD';
const { TextArea } = Input;

const BasicForm = ({ visible, onClose, payload, thisRow }) => {
  const [form] = Form.useForm();
  const [submitting, setSubmitting] = useState(false);

  useEffect(() => {
    const { custcode, custname } = payload;
    const { validfrom, validto, status } = thisRow;
    form.setFieldsValue({
      custcode,
      custname,
      status,
      validRange: validfrom && validto ? [moment(validfrom), moment(validto)] : null,
    });
  }, [payload, thisRow]);

  const onFinish = async () => {
    setSubmitting(true);
    const getFormVal = await form.validateFields();
    const { custcode, custname, validRange, status } = getFormVal;
    const params = {
      id: thisRow.id,
      status,
      custcode,
      custname,
      validfrom: validRange[0] && moment(validRange[0]).format(dateFormat),
      validto: validRange[1] && moment(validRange[1]).format(dateFormat),
    };

    const { code, msg } = await update(params);
    if (code === 200) {
      message.success(msg);
      onClose();
    } else {
      message.error(msg);
      onClose();
    }
    setSubmitting(false);
  };

  const onFinishFailed = (errorInfo) => {
    // eslint-disable-next-line no-console
    console.log('Failed:', errorInfo);
    message.error(errorInfo);
  };

  return (
    <Modal
      title="修改"
      width={1000}
      destroyOnClose
      confirmLoading={submitting}
      visible={visible}
      onOk={() => {
        form.submit();
      }}
      onCancel={() => {
        onClose();
      }}
    >
      <Form
        form={form}
        layout="vertical"
        preserve={false}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
      >
        <Row gutter={64}>
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
                  message: '请选择生效区间',
                },
              ]}
            >
              <RangePicker />
            </FormItem>
          </Col>
          {/* <Col span={8} key="status">
            <FormItem
              name="status"
              label="状态"
              rules={[
                {
                  required: true,
                  message: '请选择状态',
                },
              ]}
            >
              <Radio.Group disabled>
                <Radio value="0">无效</Radio>
                <Radio value="1">有效</Radio>
              </Radio.Group>
            </FormItem>
          </Col> */}
        </Row>
        <Row gutter={64}>
          <Col span={24}>
            <FormItem
              name="reason"
              label="原因"
              rules={[
                {
                  required: true,
                  message: '请选择状态',
                },
              ]}
            >
              <TextArea maxLength={128} placeholder="长度最大为128位" rows={2} />
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};

export default BasicForm;
