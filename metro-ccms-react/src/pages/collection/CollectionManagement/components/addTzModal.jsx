import React, { useEffect } from 'react';
import { Form, Input, Modal, Select } from 'antd';
import Styles from '../index.less';

const FormItem = Form.Item;
const { Option } = Select;
const { TextArea } = Input;

const AddTzModal = ({ visible, rowlist, onCancel, getFormMsg }) => {
  const [form] = Form.useForm();

  const formItemLayout = {
    labelCol: {
      span: 5,
    },
  };

  useEffect(() => {
    if (rowlist) {
      form.setFieldsValue({
        ...rowlist,
        status: rowlist.status.toString(),
      });
    }
  }, [rowlist]);

  const finishehed = async () => {
    const formMsg = await form.validateFields();
    let params;
    if (rowlist) {
      params = {
        ...rowlist,
        ...formMsg,
      };
    } else {
      params = {
        ...formMsg,
      };
    }
    getFormMsg(params, rowlist);
    onCancel(false);
    form.resetFields();
  };

  return (
    <Modal
      visible={visible}
      title="台账维护"
      width={500}
      onOk={() => {
        finishehed();
      }}
      onCancel={() => {
        onCancel(false);
      }}
    >
      <Form form={form} {...formItemLayout} className={Styles.modalItem}>
        <FormItem
          label="催收状态"
          name="status"
          rules={[
            {
              required: true,
              message: '请选择催收状态',
            },
          ]}
        >
          <Select allowClear>
            <Option value="1">催收中</Option>
            <Option value="2">催收成功</Option>
            <Option value="3">催收失败</Option>
          </Select>
        </FormItem>
        <FormItem
          label="催收进度描述"
          name="desc"
          rules={[
            {
              required: false,
              message: '请输入催收进度描述',
            },
          ]}
        >
          <TextArea rows={2} maxLength={128} placeholder="长度小于128位" />
        </FormItem>
      </Form>
    </Modal>
  );
};

export default AddTzModal;
