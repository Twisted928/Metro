import React, { useEffect, useState } from 'react';
import { Modal, message, Form, Input, Row, Col, Select } from 'antd';
import { create, update, queryIndex } from '../service';

const FormItem = Form.Item;
const { Option } = Select;

const BasicForm = ({ visible, onClose, payload }) => {
  const [form] = Form.useForm();
  const [indexEnum, setIndexEnum] = useState([]);
  const [submitting, setSubmitting] = useState(false);

  const layout = {
    labelCol: {
      span: 4,
    },
    wrapperCol: {
      span: 20,
    },
  };

  const getEnum = async () => {
    const { code, data, msg } = await queryIndex();
    if (code === 200) {
      setIndexEnum(data);
    } else {
      message.error(msg);
    }
  };

  useEffect(() => {
    getEnum();
    const { type, warningindex, remark } = payload;
    form.setFieldsValue({
      type,
      warningindex,
      remark,
    });
  }, [payload]);

  const handleRes = (code, msg) => {
    if (code === 200) {
      message.success(msg);
      onClose();
    } else {
      message.error(msg);
      onClose();
    }
  };

  const onFinish = async () => {
    setSubmitting(true);
    const getFormVal = await form.validateFields();
    const { type, warningindex, remark } = getFormVal;
    const { id } = payload;
    const params = { id, type, warningindex, remark };

    if (payload.length === 0) {
      const { code, msg } = await create(params);
      handleRes(code, msg);
    } else {
      const { code, msg } = await update(params);
      handleRes(code, msg);
    }

    setSubmitting(false);
  };

  const onFinishFailed = (errorInfo) => {
    message.error(errorInfo);
  };

  return (
    <Modal
      width={520}
      destroyOnClose
      title={payload.length === 0 ? '新增' : '修改'}
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
        {...layout}
        form={form}
        preserve={false}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
      >
        <FormItem
          name="type"
          label="规则大类"
          rules={[
            {
              required: true,
            },
          ]}
        >
          <Select>
            {indexEnum?.map((item) => {
              return (
                <Option key={item?.id} value={item?.description}>
                  {item?.description}
                </Option>
              );
            })}
          </Select>
        </FormItem>

        <FormItem
          name="warningindex"
          label="预警规则"
          rules={[
            {
              required: true,
            },
            {
              max: 128,
            },
          ]}
        >
          <Input />
        </FormItem>

        <FormItem
          name="remark"
          label="描述"
          rules={[
            {
              required: true,
            },
            {
              max: 128,
            },
          ]}
        >
          <Input />
        </FormItem>
      </Form>
    </Modal>
  );
};

export default BasicForm;
