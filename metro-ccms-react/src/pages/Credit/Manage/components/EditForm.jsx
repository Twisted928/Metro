import React, { useState } from 'react';
import { Modal, message, Form, Select, Input, Row, Col, Skeleton } from 'antd';
import { history } from 'umi';

const { Option } = Select
const FormItem = Form.Item;
const key = 'editForm'

const BasicForm = ({ row, visible, onClose }) => {
  const [form] = Form.useForm();
  const [submitting, setSubmitting] = useState(false);
  const [loadding, setLoadding] = useState(false);

  const onFinish = async (values) => {
    message.loading({ content: '正在提交', key, duration: 1 });
    setLoadding(true);
    setSubmitting(true);
    
    await history.push({
      pathname: '/credit/creditUpdate',
      query: {
        values
      },
    })
    
    setLoadding(false);
    message.success({ content: '成功', key, duration: 3 });
    onClose();
  };

  const onFinishFailed = (errorInfo) => {
    // eslint-disable-next-line no-console
    console.log('Failed:', errorInfo);
  };

  return (
    <Modal
      width={640}
      bodyStyle={{
        padding: '32px 40px 48px',
      }}
      destroyOnClose
      title={row ? '修改' : '新增'}
      confirmLoading={submitting}
      visible={visible}
      onOk={() => {
        form.submit();
      }}
      onCancel={() => {
        onClose();
      }}
    >
      <Skeleton loading={loadding}>
        <Form
          form={form}
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
        >
          <Row gutter={24}>
            <Col span={8} key={1}>
              <FormItem
                name='CUST_TYPE'
                label='客户类别'
              >
                <Input
                  width='130'

                />
              </FormItem>
            </Col>

            <Col span={8} key={2}>
              <FormItem
                name='PAYMENT_NAME'
                label='付款条件描述'
              >
                <Input
                  width='130'

                />
              </FormItem>
            </Col>

            <Col span={8} key={3}>
              <FormItem
                name='STARTPAYMENT'
                label='起始账期'
              >
                <Input
                  width='130'

                />
              </FormItem>
            </Col>
          </Row>

          <Row gutter={24}>
            <Col span={8} key={1}>
              <FormItem
                name='ENDPAYMENT'
                label='结束账期'
              >
                <Input
                  width='130'

                />
              </FormItem>
            </Col>

            <Col span={8} key={2}>
              {!!row && <FormItem
                name='ENDPAYMENT'
                label='结束账期'
              >
                <Select
                  width='130'

                >
                  <Option value={0}>无效</Option>
                  <Option value={1}>有效</Option>

                </Select>
              </FormItem>}
            </Col>
          </Row>

        </Form>
      </Skeleton>
    </Modal >
  )
}

export default BasicForm;