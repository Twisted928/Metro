/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect, useState } from 'react';
import { Modal, Form, Button, Select, message } from 'antd';
import { connect } from 'umi';
import { modelCreate } from '../processService';
import Styles from '../index.less';

const FormItem = Form.Item;
const { Option } = Select;

const AddModalForm = ({
  dispatch,
  actModal: { getBusinesslist },
  visible,
  onCancel,
  loadQuery,
}) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const query = (param = {}) => {
    dispatch({
      type: 'actModal/getBusiness',
      payload: param,
    });
  };

  const handleCancel = () => {
    onCancel(false);
    form.resetFields();
  };

  useEffect(() => {
    query();
  }, []);

  // 新增模型提交
  const handleOk = async () => {
    const addForm = await form.validateFields();
    const {
      name: { label, value },
    } = addForm;
    setLoading(true);
    const params = {
      name: label,
      bkey: value,
      bname: label,
    };
    const response = await modelCreate(params);
    const { code, msg } = response;
    if (code === 200) {
      loadQuery();
      setLoading(false);
      handleCancel();
    }
    if (code === 500) {
      message.error(msg);
      setLoading(false);
    }
  };

  return (
    <Modal
      visible={visible}
      title="新增流程"
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
      <Form form={form} className={Styles.activeStyle}>
        <FormItem
          label="流程名称"
          name="name"
          rules={[
            {
              required: true,
              message: '流程名称不能为空！',
            },
          ]}
        >
          <Select labelInValue>
            {(getBusinesslist || []).map((item) => {
              return (
                <Option key={item.mkey} value={item.mkey} disabled={item.actModelId}>
                  {item.name}
                </Option>
              );
            })}
          </Select>
        </FormItem>
      </Form>
    </Modal>
  );
};

export default connect(({ actModal, loading }) => ({
  actModal,
  loading: loading.effects['actModal/getBusiness'],
}))(AddModalForm);
