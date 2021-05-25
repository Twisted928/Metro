import React, { useState, useEffect } from 'react';
import { DownOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card';
import { Form, Input, Row, Col, DatePicker, Table, Select, message } from 'antd';
import { queryTerm, queryDesc } from '../service';

import Styles from './style.less';

const { RangePicker } = DatePicker;
const { Option } = Select;

const CreditApply = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [form] = Form.useForm();
  const [termDesc, setTermDesc] = useState([]);
  const [termType, setTermType] = useState([]);

  const getTermType = async () => {
    const res = await queryTerm();
    setTermType(res.data);
  };

  const getTerm = async (value) => {
    form.setFieldsValue({
      paymentName: '',
    });
    const res = await queryDesc({
      settleType: value,
    });
    if (res.code === 200) {
      setTermDesc(res.data);
    } else {
      message.error(res.msg);
    }
  };

  useEffect(() => {
    getTermType();
  });

  const columns = [
    {
      title: '发布额度(万元)',
      dataIndex: 'limit',
    },
    {
      title: '发布账期',
      dataIndex: 'paymentterm',
    },
    {
      title: '额度类型',
      dataIndex: 'limittype',
    },
    {
      title: '生效日',
      dataIndex: 'validfrom',
    },
    {
      title: '到期日',
      dataIndex: 'validto',
    },
    {
      title: '发布时间',
      dataIndex: 'granttime',
    },
    {
      title: '信用组账期',
      dataIndex: 'grouppayterm',
    },
    {
      title: '可发布额度',
      dataIndex: 'limitavaliable',
    },
  ];

  return (
    <div id="CreditApply">
      <ProCard
        title="授信申请"
        extra={
          <DownOutlined
            rotate={collapsed ? undefined : 180}
            onClick={() => {
              setCollapsed(!collapsed);
            }}
          />
        }
        collapsed={collapsed}
        bordered
        headerBordered
      >
        <div>
          <h3 className={Styles.formLabel}> 申请信息</h3>
        </div>
        <Form layout="vertical" form={form}>
          <Row gutter={64}>
            <Col span={8} key={1}>
              <Form.Item
                name="paymentTerm"
                label="付款条件"
                rules={[
                  {
                    required: true,
                    message: '请选择付款条件',
                  },
                ]}
              >
                <Select onSelect={getTerm}>
                  {termType.map((item) => {
                    return (
                      <Option key={item?.id} value={item?.description}>
                        {item?.description}
                      </Option>
                    );
                  })}
                </Select>
              </Form.Item>
            </Col>

            <Col span={8} key={2}>
              <Form.Item
                name="paymentName"
                label="付款条件描述"
                rules={[
                  {
                    required: true,
                    message: '请选择描述',
                  },
                ]}
              >
                <Select>
                  {termDesc.map((item) => {
                    return (
                      <Option key={item?.id} value={item?.paymentDesc}>
                        {item?.paymentDesc}
                      </Option>
                    );
                  })}
                </Select>
              </Form.Item>
            </Col>

            <Col span={8} key={3}>
              <Form.Item
                label="申请额度"
                name="applylimit"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input suffix="RMB" />
              </Form.Item>
            </Col>
          </Row>

          <Row gutter={64}>
            <Col span={8} key={4}>
              <Form.Item
                label="额度类型"
                name="limittype"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>

            <Col span={8} key={5}>
              <Form.Item label="生效区间" name="validRange">
                <RangePicker />
              </Form.Item>
            </Col>
          </Row>
        </Form>

        <div>
          <h3 className={Styles.formLabel}> 当前授信信息</h3>
        </div>
        <Table columns={columns} />
      </ProCard>
    </div>
  );
};

export default CreditApply;
