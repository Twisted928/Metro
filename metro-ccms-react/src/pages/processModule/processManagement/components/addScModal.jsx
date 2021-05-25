import React, { useState } from 'react';
import { Modal, Form, Button, Input, message } from 'antd';
import RoleList from '@/components/RoleList';
import { saveActBusinessList } from '../processService';

const FormItem = Form.Item;

const AddScenes = ({ visible, onCancel, loadQuery }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const formItemLayout = {
    labelCol: {
      span: 6,
    },
    // wrapperCol: { span: 15 },
  };

  const handleCancel = () => {
    onCancel(false);
    form.resetFields();
  };

  // 新增模型提交
  const handleOk = async () => {
    const addForm = await form.validateFields();
    setLoading(true);
    const { name, mkey, roleName } = addForm;
    const params = {
      name,
      mkey,
      roleIdList: roleName,
    };
    // const response = await saveActBusinessList(params);
    // const { code, msg } = response;
    // if (code === 200) {
    //   loadQuery();
    //   setLoading(false);
    //   handleCancel();
    // }
    // if (code === 500) {
    //   message.error(msg);
    //   setLoading(false);
    // }
  };

  return (
    <Modal
      visible={visible}
      title="新增场景"
      onCancel={handleCancel}
      width={500}
      footer={[
        <Button key="back" onClick={handleCancel}>
          返回
        </Button>,
        <Button key="submit" type="primary" loading={loading} onClick={handleOk}>
          提交
        </Button>,
      ]}
    >
      <Form form={form} {...formItemLayout}>
        <FormItem
          label="场景编码"
          name="mkey"
          rules={[
            {
              required: true,
              message: '场景编码不能为空！',
            },
          ]}
        >
          <Input maxLength={32} placeholder="长度小于32位" />
        </FormItem>
        <FormItem
          label="场景名称"
          name="name"
          rules={[
            {
              required: true,
              message: '场景名称不能为空！',
            },
          ]}
        >
          <Input />
        </FormItem>
        <FormItem
          label="发起角色名称"
          name="roleName"
          rules={[
            {
              required: true,
              message: '发起角色名称不能为空！',
            },
          ]}
        >
          <RoleList />
        </FormItem>
      </Form>
    </Modal>
  );
};

export default AddScenes;
