import React, { useState } from 'react';
import { DownOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card';
import { Row, Col, DatePicker, Form, Input } from 'antd';

const CustEvalOut = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [form] = Form.useForm();

  return (
    <div id="CustEvalOut">
      <ProCard
        title="客户外评"
        extra={
          <DownOutlined
            rotate={collapsed ? undefined : 180}
            onClick={() => {
              setCollapsed(!collapsed);
            }}
          />
        }
        collapsed={collapsed}
        bordered
        headerBordered
      >
        <Form form={form} layout="vertical">
          <Row gutter={64}>
            <Col span={8} key="MODEL_NAME">
              <Form.Item
                label="保险评级"
                name="MODEL_NAME"
              >
                <Input />
              </Form.Item>
            </Col>
            <Col span={8} key="MODEL_NAME">
              <Form.Item
                label="保险额度"
                name="MODEL_NAME"
              >
                <Input suffix="RMB"/>
              </Form.Item>
            </Col>
            <Col span={8} key="MODEL_NAME">
              <Form.Item
                label="保险账期"
                name="MODEL_NAME"
              >
                <Input />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={64}>
            <Col span={8} key="MODEL_NAME">
              <Form.Item
                label="有效起始日期"
                name="MODEL_NAME"
              >
                <DatePicker />
              </Form.Item>
            </Col>
            <Col span={8} key="MODEL_NAME">
              <Form.Item
                label="有效截止日期"
                name="MODEL_NAME"
              >
                <Input />
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </ProCard>
    </div>
  );
};

export default CustEvalOut;
