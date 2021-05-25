import React, { useEffect, useState } from 'react';
import { Modal, message, Form, Row, Col, Select, Input } from 'antd';
import { abcAndNumber } from '@/services/commom';
import { create, update, queryTerm } from '../service';

const FormItem = Form.Item;
const { Option } = Select;

const BasicForm = ({ row, visible, onClose }) => {
  const [form] = Form.useForm();
  const [submitting, setSubmitting] = useState(false);
  // const [termDesc, setTermDesc] = useState([]);
  const [termType, setTermType] = useState([]);
  // const [loading, setLoading] = useState(false);

  const getTermType = async () => {
    const res = await queryTerm();
    setTermType(res.data);
  };

  // const getTerm = async (value) => {
  //   form.setFieldsValue({
  //     paymentName: '',
  //   });
  //   const res = await queryDesc({
  //     settleType: value,
  //   });
  //   if (res.code === 200) {
  //     setTermDesc(res.data);
  //   } else {
  //     message.error(res.msg);
  //   }
  // };

  useEffect(() => {
    getTermType();
    if (row?.id) {
      const { settleType, paymentDesc, paymentCode, paymentDays } = row;
      // getTerm(settleType);
      form.setFieldsValue({
        settleType,
        paymentDesc,
        paymentCode,
        paymentDays,
      });
    }
  }, [row]);

  const maxDays = async (_, value) => {
    const reg = /^[0-9]+.?[0-9]*/;
    if (value && !reg.test(value)) {
      return Promise.reject(new Error('只能输入数字！'));
    }
    if (value && value > 1000) {
      return Promise.reject(new Error('付款条件天数不能大于1000！'));
    }
    if (value && value < 0) {
      return Promise.reject(new Error('付款条件天数不能小于0！'));
    }

    return false;
  };

  const onFinish = async () => {
    setSubmitting(true);
    const getFormVal = await form.validateFields();
    const { settleType, paymentDesc, paymentCode, paymentDays } = getFormVal;
    const params = {
      id: row?.id,
      settleType,
      paymentDesc,
      paymentCode,
      paymentDays,
    };

    if (row.id) {
      const { code, msg } = await update(params);
      // eslint-disable-next-line no-console
      console.log('update', code, msg);
      if (code === 200) {
        message.success(msg);
        onClose();
      } else {
        message.error(msg);
      }
    } else {
      // 新增
      const { code, msg } = await create(params);
      // eslint-disable-next-line no-console
      console.log('create', code, msg);
      if (code === 200) {
        message.success(msg);
        setSubmitting(false);
        onClose();
      } else {
        message.error(msg);
      }
    }
    setSubmitting(false);
    onClose();
  };

  const onFinishFailed = (errorInfo) => {
    message.error({ content: errorInfo, duration: 2 });
  };

  return (
    <Modal
      width={800}
      title={row ? '修改' : '新增'}
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
          <Col span={12} key="settleType">
            <FormItem
              name="settleType"
              label="付款条件"
              rules={[
                {
                  required: true,
                  message: '请选择付款条件',
                },
              ]}
            >
              <Select>
                {termType.map((item) => {
                  return (
                    <Option key={item?.id} value={item?.description}>
                      {item?.description}
                    </Option>
                  );
                })}
              </Select>
            </FormItem>
          </Col>

          <Col span={12} key="paymentDesc">
            <FormItem
              name="paymentDesc"
              label="付款条件描述"
              rules={[
                {
                  required: true,
                  message: '请选择描述',
                },
              ]}
            >
              <Input />
            </FormItem>
          </Col>
        </Row>
        <Row gutter={64}>
          <Col span={12} key="paymentCode">
            <FormItem
              name="paymentCode"
              label="付款条件代码"
              rules={[
                {
                  required: true,
                  message: '请输入付款条件代码',
                },
                {
                  validator: abcAndNumber,
                },
                {
                  max: 16,
                },
              ]}
            >
              <Input />
            </FormItem>
          </Col>

          <Col span={12} key="paymentDays">
            <FormItem
              name="paymentDays"
              label="付款条件天数"
              rules={[
                {
                  required: true,
                  message: '请选择付款条件天数',
                },
                {
                  validator: maxDays,
                },
              ]}
            >
              <Input suffix="天" />
            </FormItem>
          </Col>
        </Row>
      </Form>
      {/* </Skeleton> */}
    </Modal>
  );
};

export default BasicForm;
