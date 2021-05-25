import { Button, TreeSelect, message, Input, Form, Radio, Modal, Spin } from 'antd';
import React, { useEffect, useState } from 'react';
import { create, update, queryById } from '../service';
// import styles from './style.less';

const FormItem = Form.Item;

const key = 'messageloading';

const formItemLayout = {
  labelCol: {
    xs: { span: 24 },
    sm: { span: 6 },
  },
  wrapperCol: {
    xs: { span: 24 },
    sm: { span: 18 },
    md: { span: 18 },
  },
};

const BasicForm = ({ id, modalVisible, onCancel, treeData = [] }) => {
  const [data, setData] = useState({});
  const [submitting, setsubmitting] = useState(false);
  const [loading, setLoading] = useState(false);
  const [form] = Form.useForm();

  const submitFormLayout = {
    wrapperCol: {
      xs: { span: 24, offset: 0 },
      sm: { span: 10, offset: 7 },
    },
  };

  // 进入页面查询信息
  const queryData = async () => {
    if (!id) {
      return;
    }

    setLoading(true);
    const { code, data: responData } = await queryById(id);
    if (code === 200) {
      const roleResourceDos = responData.roleResourceDos.map((i) => `${i}`);
      const tempresponData = { ...responData, roleResourceDos };
      setData(tempresponData);
      form.setFieldsValue(tempresponData);
    } else {
      message.error('加载失败');
    }
    setLoading(false);
  };

  useEffect(() => {
    queryData();
  }, [id]);

  const onFinish = async (values) => {
    message.loading({ content: '正在提交', key, duration: 0 });
    setsubmitting(true);

    let response;
    if (data && data.id) {
      // 更新方法
      response = await update({ id: data.id, ...values });
    } else {
      // 新增方法
      response = await create({ ...values });
    }

    const { code, msg } = response;
    if (code === 200) {
      message.success({ content: msg, key });
      setsubmitting(false);
      onCancel();
    } else {
      message.error({ content: msg, key });
      setsubmitting(false);
    }

    // 返回首页
  };

  const onFinishFailed = (errorInfo) => {
    // eslint-disable-next-line no-console
    console.log('Failed:', errorInfo);
  };

  return (
    <Modal
      destroyOnClose
      title="维护角色"
      visible={modalVisible}
      onCancel={() => {
        setData({});
        form.resetFields();
        onCancel();
      }}
      footer={null}
    >
      <Spin spinning={loading}>
        <Form
          hideRequiredMark
          style={{ marginTop: 8 }}
          form={form}
          name="sysdateCreate"
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
        >
          <FormItem
            {...formItemLayout}
            label="角色名称"
            name="roleName"
            rules={[
              {
                required: true,
                message: '用户名称不能为空',
              },
            ]}
          >
            <Input placeholder="角色名称" />
          </FormItem>
          <FormItem {...formItemLayout} label="角色描述" name="description">
            <Input style={{ minHeight: 32 }} placeholder="角色描述" />
          </FormItem>
          <FormItem
            {...formItemLayout}
            label="状态"
            name="state"
            rules={[
              {
                required: true,
                message: '状态不能为空',
              },
            ]}
          >
            <Radio.Group>
              <Radio value={1}>有效</Radio>
              <Radio value={0}>无效</Radio>
            </Radio.Group>
          </FormItem>

          <FormItem {...formItemLayout} label="关联资源" name="roleResourceDos">
            <TreeSelect
              style={{ width: '100%' }}
              dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
              treeData={treeData}
              placeholder="父资源"
              treeDefaultExpandAll
              maxTagCount={1}
              treeCheckable
              allowClear
            />
          </FormItem>

          <FormItem {...submitFormLayout} style={{ marginTop: 32 }}>
            <Button type="primary" htmlType="submit" loading={submitting}>
              提交
            </Button>
          </FormItem>
        </Form>
      </Spin>
    </Modal>
  );
};

export default BasicForm;
