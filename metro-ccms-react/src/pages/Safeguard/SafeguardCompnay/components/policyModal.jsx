import React, { useEffect, useState } from 'react';
import {
  Modal,
  Button,
  Form,
  Row,
  Col,
  Input,
  InputNumber,
  DatePicker,
  Select,
  message,
} from 'antd';
import { connect } from 'umi';
import moment from 'moment';
import { abcAndNumber, maxNumber } from '@/services/commom';
import DepartMent from '@/components/Department/index';
import { timeJudgment } from '../service';

const dateFormat = 'YYYY-MM-DD';
const FormItem = Form.Item;
const { Option } = Select;

const PolicyModal = ({
  dispatch,
  vis,
  mainId,
  onCancel,
  formdata,
  id,
  queryList,
  rowList,
  commonmodel: { basciData },
}) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');

  // 查询币种
  const getMoneyType = () => {
    dispatch({
      type: 'commonmodel/basciData',
      payload: { ctype: 'Currency' },
    });
  };

  useEffect(() => {
    if (formdata.compCode) {
      if (id === 2) {
        form.setFieldsValue({
          ...formdata,
          validFrom: moment(formdata.validFrom),
          validTo: moment(formdata.validTo),
          insureScopeDOList: formdata.insureScopeDOList,
        });
      }
      setStartDate(moment(formdata.validFrom).format(dateFormat));
      setEndDate(moment(formdata.validTo).format(dateFormat));
      getMoneyType();
    }
  }, [formdata]);

  const handleCancel = () => {
    onCancel(false);
    form.resetFields();
  };

  const handleOk = async () => {
    const getFormVal = await form.validateFields();
    setLoading(true);
    const { policySum, maxpaySum, currency } = getFormVal;
    const isBelowThreshold = (currentValue) => !!currentValue;
    const arr = [policySum, maxpaySum];
    const flag = arr.some(isBelowThreshold);
    if (flag && !currency) {
      message.info('请选择币种！');
      setLoading(false);
      return;
    }
    // 检验相同的保单号
    if (formdata.id) {
      for (let i = 0; i < rowList.length; i += 1) {
        if (rowList[i].policyno === getFormVal.policyno && rowList[i].id !== formdata.id) {
          message.error('已存在相同的保单号!');
          setLoading(false);
          return;
        }
      }
    } else {
      for (let i = 0; i < rowList.length; i += 1) {
        if (
          rowList[i].policyno === getFormVal.policyno &&
          (rowList[i].textId !== formdata.textId || rowList[i].id !== formdata.id)
        ) {
          message.error('已存在相同的保单号!');
          setLoading(false);
          return;
        }
      }
    }

    const params = {
      ...formdata,
      ...getFormVal,
      validFrom: moment(getFormVal.validFrom).format(dateFormat),
      validTo: moment(getFormVal.validTo).format(dateFormat),
    };
    const timeParam = {
      validFrom: startDate,
      validTo: endDate,
      insurePolicyVOList: [
        {
          validFrom: moment(getFormVal.validFrom).format(dateFormat),
          validTo: moment(getFormVal.validTo).format(dateFormat),
        },
      ],
    };
    const response = await timeJudgment(timeParam);
    if (response.code === 500) {
      message.error(response.msg);
      setLoading(false);
      return;
    }
    if (response.code === 200) {
      queryList(id, params);
      setLoading(false);
      handleCancel();
    }
  };

  return (
    <Modal
      title="保单信息维护"
      width={1000}
      visible={vis}
      onCancel={handleCancel}
      footer={[
        <Button key="back" onClick={handleCancel}>
          返回
        </Button>,
        <Button key="submit" type="primary" loading={loading} onClick={handleOk}>
          提交
        </Button>,
      ]}
    >
      <Form form={form} layout="vertical">
        <Row gutter={64}>
          <Col span={8}>
            <FormItem
              label="保单号"
              name="policyno"
              rules={[
                {
                  required: true,
                  message: '请输入保单号！',
                },
                {
                  validator: abcAndNumber,
                },
              ]}
            >
              <Input readOnly={mainId && formdata.id} placeholder="长度小于32位" maxLength={32} />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="保单范围"
              name="insureScopeDOList"
              rules={[
                {
                  required: true,
                  message: '请输入保单范围！',
                },
              ]}
            >
              <DepartMent />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="保单主体"
              name="body"
              rules={[
                {
                  required: true,
                  message: '请输入保单主体！',
                },
              ]}
            >
              <Input placeholder="长度小于32位" maxLength={32} />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="约定投保金额（万元）"
              name="policySum"
              rules={[
                {
                  validator: (_, value) =>
                    value < 0
                      ? Promise.reject(new Error('约定投保金额不能为0和负数！'))
                      : Promise.resolve(),
                },
              ]}
            >
              <InputNumber maxLength={16} placeholder="长度小于16位" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="最高赔偿限额（万元）"
              name="maxpaySum"
              rules={[
                {
                  validator: (_, value) =>
                    value < 0
                      ? Promise.reject(new Error('高赔偿限额不能为0和负数！'))
                      : Promise.resolve(),
                },
              ]}
            >
              <InputNumber min={0} maxLength={16} placeholder="长度小于16位" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="赔偿比例（%）"
              name="payLv"
              rules={[
                {
                  validator: (_, value) =>
                    value < 0
                      ? Promise.reject(new Error('赔偿比例不能为0和负数！'))
                      : Promise.resolve(),
                },
                {
                  validator: maxNumber,
                },
              ]}
            >
              <InputNumber min={0} maxLength={16} placeholder="长度小于16位" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="最长付款期限（天）"
              name="payperiod"
              rules={[
                {
                  validator: (_, value) =>
                    value < 0
                      ? Promise.reject(new Error('最长付款期限不能为0和负数！'))
                      : Promise.resolve(),
                },
              ]}
            >
              <InputNumber min={0} maxLength={11} placeholder="长度小于11位" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="限额闲置期（天）"
              name="quotafree"
              rules={[
                {
                  required: false,
                  message: '请输入限额闲置期',
                },
                {
                  validator: (_, value) =>
                    value < 0
                      ? Promise.reject(new Error('销限额闲置期不能为0和负数！'))
                      : Promise.resolve(),
                },
              ]}
            >
              <InputNumber min={0} maxLength={11} placeholder="长度小于11位" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="赔款等待期（天）"
              name="paywait"
              rules={[
                {
                  required: false,
                  message: '请输入赔款等待期！',
                },
                {
                  validator: (_, value) =>
                    value < 0
                      ? Promise.reject(new Error('赔款等待期不能为0和负数！'))
                      : Promise.resolve(),
                },
              ]}
            >
              <InputNumber min={0} maxLength={11} placeholder="长度小于11位" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem label="币种" name="currency">
              <Select placeholder="请选择币种">
                {(basciData || []).map((item) => {
                  return (
                    <Option key={`${item.ctype}`} value={`${item.ctype}`}>
                      {item.ctype}
                    </Option>
                  );
                })}
              </Select>
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="生效时间"
              name="validFrom"
              rules={[
                {
                  required: true,
                  message: '请输入生效时间！',
                },
              ]}
            >
              <DatePicker />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem
              label="到期时间"
              name="validTo"
              rules={[
                {
                  required: true,
                  message: '请输入到期时间！',
                },
              ]}
            >
              <DatePicker />
            </FormItem>
          </Col>
        </Row>
      </Form>
    </Modal>
  );
};

export default connect(({ commonmodel, loading }) => ({
  commonmodel,
  loadingBasic: loading.effects['commonmodel/basciData'],
}))(PolicyModal);
