/* eslint-disable no-nested-ternary */
import React, { useState, useEffect } from 'react';
import { Modal, Form, Button, Input, Select, InputNumber } from 'antd';
import { connect } from 'umi';
import styles from '../index.less';

const FormItem = Form.Item;
const { Option } = Select;
const { TextArea } = Input;

const AddModalForm = ({
  vis,
  onCancel,
  getMsg,
  method,
  formData,
  rowIndex,
  dispatch,
  engineManagement: { custList },
}) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);

  const formItemLayout = {
    labelCol: {
      span: 5,
    },
  };
  const formItemLayout1 = {
    labelCol: {
      span: 3,
    },
  };

  const query = (param = {}) => {
    dispatch({
      type: 'engineManagement/listkehu',
      payload: param,
    });
  };

  useEffect(() => {
    query();
  }, []);

  useEffect(() => {
    if (rowIndex >= 0) {
      form.setFieldsValue(formData);
    }
  }, [formData]);

  const handleCancel = () => {
    onCancel(false);
  };

  // 新增模型提交
  const handleOk = async () => {
    const addForm = await form.validateFields();
    setLoading(true);
    // 判断是否为修改
    if (rowIndex !== null) {
      const paramUpdata = {
        ...formData,
        ...addForm,
      };
      getMsg(paramUpdata, rowIndex);
      setLoading(false);
      handleCancel();
      form.resetFields();
      return;
    }
    getMsg(addForm);
    setLoading(false);
    handleCancel();
    form.resetFields();
  };

  // 区间打分法
  const getQuataForm = () => {
    return (
      <Form form={form} {...formItemLayout}>
        <FormItem
          label="区间下限"
          name="lowerValue"
          rules={[
            {
              required: true,
              message: '区间下限不能为空！',
            },
          ]}
        >
          <InputNumber maxLength={16} placeholder="长度小于16位" />
        </FormItem>
        <FormItem
          label="区间上限"
          name="upperValue"
          rules={[
            {
              required: true,
              message: '区间上限不能为空！',
            },
          ]}
        >
          <InputNumber maxLength={16} placeholder="长度小于16位" />
        </FormItem>
        <FormItem
          label="包含范围"
          name="includeRange"
          rules={[
            {
              required: true,
              message: '包含范围不能为空！',
            },
          ]}
        >
          <Select>
            <Option value={1}>
              包含上限和下限<a className={styles.khColor}>[</a>0,1
              <a className={styles.khColor}>]</a>
            </Option>
            <Option value={2}>
              不包含上限和下限<a className={styles.khColor}>(</a>0,1
              <a className={styles.khColor}>)</a>
            </Option>
            <Option value={3}>
              包含下限<a className={styles.khColor}>[</a>0,1<a className={styles.khColor}>)</a>
            </Option>
            <Option value={4}>
              包含上限<a className={styles.khColor}>(</a>0,1<a className={styles.khColor}>]</a>
            </Option>
          </Select>
        </FormItem>
        <FormItem
          label="描述"
          name="description"
          rules={[
            {
              required: false,
              message: '描述不能为空',
            },
          ]}
        >
          <TextArea rows={2} maxLength={128} placeholder="长度小于128位" />
        </FormItem>
      </Form>
    );
  };

  // 逻辑打分法
  const getLogicForm = () => {
    return (
      <Form form={form} {...formItemLayout1}>
        <FormItem
          label="指标值"
          name="grade"
          rules={[
            {
              required: true,
              message: '指标值不能为空！',
            },
          ]}
        >
          <Select>
            {(custList || []).map((item) => {
              return (
                <Option key={item.ctype} value={item.ctype}>
                  {item.ctype}
                </Option>
              );
            })}
          </Select>
        </FormItem>
        <FormItem
          label="描述"
          name="description"
          rules={[
            {
              required: true,
              message: '描述不能为空！',
            },
          ]}
        >
          <TextArea rows={2} maxLength={128} placeholder="长度小于128位" />
        </FormItem>
      </Form>
    );
  };
  // 加减分项
  // const getPlusAndMinusForm = () => {
  //   return (
  //     <Form form={form} {...formItemLayout}>
  //       <FormItem
  //         label="分数"
  //         name="grade"
  //         rules={[
  //           {
  //             required: true,
  //             message: '模型名称不能为空',
  //           },
  //         ]}
  //       >
  //         <InputNumber />
  //       </FormItem>
  //       <FormItem
  //         label="指标描述"
  //         name="description"
  //         rules={[
  //           {
  //             required: true,
  //             message: '使用范围不能为空',
  //           },
  //         ]}
  //       >
  //         <TextArea rows={2} />
  //       </FormItem>
  //       <FormItem
  //         label="说明"
  //         name="remark"
  //         rules={[
  //           {
  //             required: true,
  //             message: '模型名称不能为空',
  //           },
  //         ]}
  //       >
  //         <TextArea rows={2} />
  //       </FormItem>
  //     </Form>
  //   );
  // };

  const title = () => {
    if (method === '1') {
      return '逻辑判断法';
    }
    if (method === '2') {
      return '区间打分法';
    }
    return '指标维护';
  };

  return (
    <Modal
      visible={vis}
      title={title()}
      onCancel={handleCancel}
      width={550}
      footer={[
        <Button key="back" onClick={handleCancel}>
          返回
        </Button>,
        <Button key="submit" type="primary" loading={loading} onClick={handleOk}>
          提交
        </Button>,
      ]}
    >
      <div className={styles.setformHei}>
        {method === '1' ? getLogicForm() : method === '2' ? getQuataForm() : ''}
      </div>
    </Modal>
  );
};

export default connect(({ engineManagement, loading }) => ({
  engineManagement,
  loading: loading.effects['engineManagement/listkehu'],
}))(AddModalForm);
