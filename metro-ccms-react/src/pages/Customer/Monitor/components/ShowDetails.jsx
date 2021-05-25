import React, { useEffect } from 'react';
import { Modal, Form, Radio, Input, Row, Col, DatePicker } from 'antd';
import moment from 'moment';

const { RangePicker } = DatePicker;
const FormItem = Form.Item;
const { TextArea } = Input;

const BasicForm = ({ visible, onClose, payload }) => {
  const [form] = Form.useForm();

  useEffect(() => {
    const {
      custcode,
      custname,
      reason,
      validfrom,
      validto,
      updatedby,
      updatetime,
      status,
      createdby,
      createtime,
    } = payload;
    form.setFieldsValue({
      custcode,
      custname,
      reason,
      validRange: validfrom ? [moment(validfrom), moment(validto)] : null,
      updatedby,
      updatetime: updatetime && moment(updatetime),
      status,
      createdby,
      createtime: createtime && moment(createtime),
    });
  });

  return (
    <Modal
      width={1000}
      destroyOnClose
      title="详情"
      visible={visible}
      footer={null}
      onCancel={() => {
        onClose();
      }}
    >
      <Form form={form} layout="vertical" preserve={false}>
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
            <FormItem name="validRange" label="生效区间">
              <RangePicker disabled />
            </FormItem>
          </Col>
        </Row>

        <Row gutter={24}>
          <Col span={8} key="status">
            <FormItem name="status" label="状态">
              <Radio.Group disabled>
                <Radio value="0">无效</Radio>
                <Radio value="1">有效</Radio>
              </Radio.Group>
            </FormItem>
          </Col>

          <Col span={8} key="createdby">
            <FormItem name="createdby" label="创建人">
              <Input disabled />
            </FormItem>
          </Col>

          <Col span={8} key="createtime">
            <FormItem name="createtime" label="创建时间">
              <DatePicker disabled />
            </FormItem>
          </Col>
        </Row>

        <Row gutter={24}>
          <Col span={8} key="updatedby">
            <FormItem name="updatedby" label="更新人">
              <Input disabled />
            </FormItem>
          </Col>

          <Col span={8} key="updatetime">
            <FormItem name="updatetime" label="更新时间">
              <DatePicker disabled />
            </FormItem>
          </Col>
        </Row>
        <Row gutter={64}>
          <Col span={24} style={{ height: '100px' }}>
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
              <TextArea disabled maxLength={100} rows={2} />
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};

export default BasicForm;
