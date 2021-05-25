import React, { useState } from 'react';
import { DownOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card'
import { Form, Input, Row, Col, Button } from 'antd';
import Styles from './style.less';

const CustTags = () => {
   const [collapsed, setCollapsed] = useState(false);
   const [form] = Form.useForm();

   return (
      <div id="CustTags">
         <ProCard
            title="客户标签"
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
            <div><h3 className={Styles.formLabel}> 标签维护</h3></div>
            <Form
               form={form}
               layout="vertical"
            >
               <Row gutter={64}>
                  <Col span={8} key={1}>
                     <Form.Item
                        label="业务类型"
                        name="BUSINESS_TYPE"
                        rules={[
                           {
                              required: true,
                           },
                        ]}
                     >
                        <Input disabled />
                     </Form.Item>
                  </Col>

                  <Col span={8} key={2}>
                     <Form.Item
                        label="行业分类"
                        name="INDUSTRY_TYPE"
                        rules={[
                           {
                              required: true,
                           },
                        ]}
                     >
                        <Input disabled />
                     </Form.Item>
                  </Col>

                  <Col span={8} key={3}>
                     <Form.Item
                        label="组织类型"
                        anme="ORGAN_TYPE"
                        rules={[
                           {
                              required: true,
                           },
                        ]}
                     >
                        <Input />
                     </Form.Item>
                  </Col>
               </Row>

               <Row gutter={24}>
                  <Col span={8} key={4}>
                     <Form.Item
                        label="模型编码"
                        anme="MODEL_NO"
                        rules={[
                           {
                              required: true,
                           },
                        ]}
                     >
                        <Input disabled />
                     </Form.Item>
                  </Col>

                  <Col span={8} key={5}>
                     <Form.Item
                        label="模型名称"
                        anme="MODEL_NAME"
                        rules={[
                           {
                              required: true,
                           },
                        ]}
                     >
                        <Input disabled />
                     </Form.Item>
                  </Col>
               </Row>
            </Form>

            <div><h3 className={Styles.formLabel}> 指标维护</h3></div>
            <Button
               type="primary"
               className={Styles.buttonFloat}
               onClick={() => {
                  window.location = '#CustEval';
               }}
            >
               模型计算
            </Button>
         </ProCard>
      </div>
   );
};

export default CustTags;
