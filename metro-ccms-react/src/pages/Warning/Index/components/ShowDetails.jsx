import React, { useEffect } from 'react';
import { Modal, Form, Input, Row, Col, Select } from 'antd';
import moment from 'moment';


const FormItem = Form.Item;
const { Option } = Select;
const dataFormater = 'YYYY-MM-DD';

const BasicForm = ({ visible, onClose, payload }) => {
  const [form] = Form.useForm();

  useEffect(() => {
    const {
      type,
      warningindex,
      remark,
      status,
      createdby,
      createtime,
      updatedby,
      updatetime,
    } = payload;
    form.setFieldsValue({
      type,
      warningindex,
      remark,
      status,
      createdby,
      updatedby,
      createtime: createtime && moment(createtime).format(dataFormater),
      updatetime: updatetime && moment(updatetime).format(dataFormater),
    });
  }, [payload]);

  return (
    <Modal
      title="详情"
      width={1000}
      destroyOnClose
      visible={visible}
      footer={false}
      onCancel={() => {
        onClose();
      }}
    >
      <Form form={form} layout="vertical" preserve={false}>
        <Row gutter={64}>
          <Col span={8} key="type">
            <FormItem name="type" label="规则大类">
              <Input disabled />
            </FormItem>
          </Col>

          <Col span={8} key="warningindex">
            <FormItem name="warningindex" label="预警规则">
              <Input disabled />
            </FormItem>
          </Col>

          <Col span={8} key="remark">
            <FormItem name="remark" label="描述">
              <Input disabled />
            </FormItem>
          </Col>
        </Row>

        <Row gutter={64}>
          <Col span={8} key="status">
            <FormItem name="status" label="状态">
              <Select disabled>
                <Option value="0">无效</Option>
                <Option value="1">有效</Option>
              </Select>
            </FormItem>
          </Col>

          <Col span={8} key="createdby">
            <FormItem name="createdby" label="创建人">
              <Input disabled />
            </FormItem>
          </Col>

          <Col span={8} key="createtime">
            <FormItem name="createtime" label="创建时间">
              <Input disabled />
            </FormItem>
          </Col>
        </Row>
        <Row gutter={64}>
          <Col span={8} key="updatedby">
            <FormItem name="updatedby" label="更新人">
              <Input disabled />
            </FormItem>
          </Col>

          <Col span={8} key="updatetime">
            <FormItem name="updatetime" label="更新时间">
              <Input disabled />
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};

export default BasicForm;
