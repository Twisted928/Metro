import React, { useState, useEffect } from 'react';
import { Modal, Form, Row, Col, Button, Input, Select, InputNumber } from 'antd';
import styles from '../index.less';

const FormItem = Form.Item;
const { Option } = Select;
const { TextArea } = Input;

const AddModalForm = ({ vis, onCancel, getMsg, method, formData, rowIndex }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  
  // const formItemLayout = {
  //   labelCol: {
  //     span: 3,
  //   },
  // };

  useEffect(() => {
    if (rowIndex >= 0) {
      form.setFieldsValue(formData);
    }
  }, [form, formData, rowIndex]);

  const handleCancel = () => {
    onCancel(false);
    form.resetFields();
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

  // const maxNumber10 = async (_, value) => {
  //   if (value && value > 10) {
  //     return Promise.reject(new Error('输入的数值不能大于10！'));
  //   }
  //   if (value && value < 0) {
  //     return Promise.reject(new Error('输入的数值不能小于0！'));
  //   }
  //   return false;
  // };

  // 区间打分法
  const getQuataForm = () => {
    return (
      <Form form={form} layout="vertical">
        <Row gutter={64}>
          <Col span={8}>
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
          </Col>
          <Col span={8}>
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
          </Col>
          <Col span={8}>
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
          </Col>
          <Col span={8}>
            <FormItem
              label="指标评分"
              name="grade"
              rules={[
                {
                  required: true,
                  message: '指标评分不能为空！',
                },
                // {
                //   validator: maxNumber10,
                // },
              ]}
            >
              <InputNumber placeholder="" />
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span={24}>
            <FormItem
              label="区间描述"
              name="description"
              rules={[
                {
                  required: true,
                  message: '区间描述不能为空！',
                },
              ]}
            >
              <TextArea rows={2} maxLength={128} placeholder="长度小于128位" />
            </FormItem>
          </Col>
        </Row>
      </Form>
    );
  };

  // 逻辑打分法
  // const getLogicForm = () => {
  //   return (
  //     <Form form={form} {...formItemLayout}>
  //       <FormItem
  //         label="指标值"
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
  //         label="描述"
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
  //     </Form>
  //   );
  // };
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
      width={1000}
      footer={[
        <Button key="back" onClick={handleCancel}>
          返回
        </Button>,
        <Button key="submit" type="primary" loading={loading} onClick={handleOk}>
          提交
        </Button>,
      ]}
    >
      {/* {getLogicForm()} */}
      <div className={styles.setformHei}>{getQuataForm()}</div>
      {/* {getPlusAndMinusForm()} */}
    </Modal>
  );
};

export default AddModalForm;
