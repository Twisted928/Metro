/* eslint-disable react-hooks/exhaustive-deps */
import { Modal, message, Select, Form, DatePicker, InputNumber } from 'antd';
import React, { useEffect, useState } from 'react';
import moment from 'moment';
import MoneyType from '@/components/MoneyType';
import { create, update } from '../service';

const FormItem = Form.Item;
const { Option } = Select;
const { MonthPicker } = DatePicker;
const key = 'messageloading';

const BasicForm = ({ row = {}, setRow, visible, onClose }) => {
  const [submitting, setsubmitting] = useState(false);
  const [mainId, setMainId] = useState(false);
  const [form] = Form.useForm();
  const formItemLayout = {
    labelCol: {
      span: 4,
    },
  };

  // 进入页面查询信息
  const queryData = async () => {
    if (!row || !row.id) {
      return;
    }
    setMainId(true);
    form.setFieldsValue({
      ...row,
      month: row.month ? moment(row.month) : '',
    });
  };

  useEffect(() => {
    // console.log(moment('2021-04-30T16:00:00.000Z').format('YYYY-MM'));
    queryData();
  }, []);

  const onFinish = async (values) => {
    const { currency, currencyDh, exchangeRate, month } = values;
    if (currencyDh === currency) {
      message.info('相同币种不能转换汇率，请重新选择！');
      return;
    }
    setsubmitting(true);
    const params = {
      currency,
      currencyDh,
      exchangeRate: exchangeRate.toFixed(4),
      month: moment(month).format('YYYY-MM'),
    };
    let response;
    if (row && row.id) {
      // 更新方法
      response = await update({ ...row, ...params });
    } else {
      // 新增方法
      response = await create({ ...params });
    }

    const { code, msg } = response;
    if (code === 200) {
      message.success({ content: msg, key });
      setsubmitting(false);
      onClose();
    } else {
      message.error({ content: msg }, 3);
      setsubmitting(false);
    }

    // 返回首页
  };

  const onFinishFailed = () => {};

  return (
    <Modal
      title="维护汇率信息"
      confirmLoading={submitting}
      destroyOnClose
      maskClosable={false}
      visible={visible}
      onOk={() => {
        form.submit();
      }}
      onCancel={() => {
        onClose();
      }}
      afterClose={() => {
        setRow();
      }}
    >
      <Form
        {...formItemLayout}
        style={{ marginTop: 8 }}
        form={form}
        name="sysdateCreate"
        initialValues={{
          status: '1',
          month: moment(),
        }}
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
      >
        <FormItem
          label="汇率"
          name="exchangeRate"
          rules={[
            {
              required: true,
              message: '汇率不能为空！',
            },
          ]}
        >
          <InputNumber placeholder="" />
        </FormItem>
        <FormItem
          label="币种"
          name="currency"
          rules={[
            {
              required: true,
              message: '币种不能为空！',
            },
          ]}
        >
          <MoneyType disabled={mainId} />
        </FormItem>
        <FormItem
          label="转换币种"
          name="currencyDh"
          rules={[
            {
              required: true,
              message: '转换币种不能为空！',
            },
          ]}
        >
          <Select disabled={mainId} placeholder="">
            <Option key="CNY" value="CNY">
              CNY
            </Option>
            <Option key="USD" value="USD">
              USD
            </Option>
          </Select>
        </FormItem>
        <FormItem
          label="月度"
          name="month"
          rules={[
            {
              required: true,
              message: '请选择月度！',
            },
          ]}
        >
          <MonthPicker disabled={mainId} />
        </FormItem>
      </Form>
    </Modal>
  );
};

export default BasicForm;
