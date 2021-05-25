import React, { useState } from 'react';
import { DownOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card';
import ProTable from '@ant-design/pro-table';
import { Form, Input, Row, Col, Button, Table, Space, Radio } from 'antd';
import Styles from './style.less';

const CustEval = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [form] = Form.useForm();

  const columns_now = [
    {
      title: '模型编码',
      dataIndex: 'MODEL_CODE',
    },
    {
      title: '模型名称',
      dataIndex: 'MODEL_NAME',
    },
    {
      title: '评估得分',
      dataIndex: 'GRADE',
    },
    {
      title: '评级',
      dataIndex: 'RANK',
    },
    {
      title: '建议额度',
      dataIndex: 'ADVICE_LIMIT',
    },
    {
      title: '建议账期',
      dataIndex: 'ADVICE_DAYS',
    },
    {
      title: '评估日期',
      dataIndex: 'PG_DATE',
      valueType: 'date',
    },
    {
      title: '有效截止日期',
      dataIndex: 'VALID_TO',
      valueType: 'date',
    },
  ];

  const columns_out = [
    {
      title: '接口名称',
      dataIndex: 'INTERFACE',
    },
    {
      title: '调用时间',
      dataIndex: 'DDATE',
    },
    {
      title: '有效截止日期',
      dataIndex: 'VALID_TO',
    },
    {
      title: '有效状态',
      dataIndex: 'INTERFACE',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '失效',
          status: 'Error',
        },
        1: {
          text: '失效',
          status: 'Success',
        },
      },
    },
    {
      title: '操作',
      dataIndex: 'option',
      valueType: 'option',
      render: (_, record) => [
        <a
          key="Initiative"
          onClick={(e) => {
            e.preventDefault();
            // eslint-disable-next-line no-console
            console.log(record);
          }}
        >
          主动调用
        </a>,
      ],
    },
  ];

  return (
    <div id="CustEval">
      <ProCard
        title="客户评估"
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
        <Space direction="vertical" style={{ width: '100%' }} size="large">
          <div>
            <h3 className={Styles.formLabel} style={{ display: 'inline' }}>
              当前有效评估
            </h3>
            <Button type="primary" className={Styles.rightButton}>
              复用
            </Button>
          </div>

          <Table columns={columns_now} />

          <div>
            <h3 className={Styles.formLabel} style={{ display: 'inline' }}>
              模型选择
            </h3>
          </div>

          <Radio.Group>
            <Space size="large">
              <Radio value={0}>存量客户模型（老客户+有财报）</Radio>
              <Radio value={1}>存量客户模型（老客户+无财报）</Radio>
              <Radio value={2}>增量客户模型（新客户+有财报）</Radio>
              <Radio value={3}>增量客户模型（新客户+无财报）</Radio>
            </Space>
          </Radio.Group>

          <div>
            <h3 className={Styles.formLabel} style={{ display: 'inline' }}>
              指标维护
            </h3>
          </div>

          <Form form={form} layout="vertical">
            <Row gutter={64}>
              <Col span={8} key={1}>
                <Form.Item
                  label="净资产收益率"
                  name="MODEL_CODE"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>

              <Col span={8} key={2}>
                <Form.Item
                  label="总资产周转率"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>

              <Col span={8} key={3}>
                <Form.Item label="资产负债率" name="ADVICE_LIMIT">
                  <Input disabled />
                </Form.Item>
              </Col>
            </Row>

            <Row gutter={64}>
              <Col span={8} key={4}>
                <Form.Item
                  label="资产规模"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>

              <Col span={8} key={5}>
                <Form.Item
                  label="销售（营业）增长率"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key={6}>
                <Form.Item
                  label="企业规模"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={64}>
              <Col span={8} key={4}>
                <Form.Item
                  label="资本累积率"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>

              <Col span={8} key={5}>
                <Form.Item
                  label="平均提前/逾期还款天数"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key={6}>
                <Form.Item
                  label="交易金额增长率"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={64}>
              <Col span={8} key={4}>
                <Form.Item
                  label="逾期金额比例变化率"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>

              <Col span={8} key={5}>
                <Form.Item
                  label="成立日期/时间"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key={6}>
                <Form.Item
                  label="主体类型"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={64}>
              <Col span={8} key={4}>
                <Form.Item
                  label="已抵押资产占总资产的比例"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>

              <Col span={8} key={5}>
                <Form.Item
                  label="对外投资情况"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key={6}>
                <Form.Item
                  label="企业负面信息"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={64}>
              <Col span={8} key={4}>
                <Form.Item
                  label="区域风险情况"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>

              <Col span={8} key={5}>
                <Form.Item
                  label="注册资本"
                  name="ADVICE_DAYS"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
            </Row>
          </Form>

          <div>
            <h3 className={Styles.formLabel} style={{ display: 'inline' }}>
              外部征信获取记录
            </h3>
          </div>

          <ProTable columns={columns_out} search={false} toolBarRender={false} />

          <div>
            <h3 className={Styles.formLabel} style={{ display: 'inline' }}>
              模型输出-建议
            </h3>
          </div>
          <Form form={form} layout="vertical">
            <Row gutter={64}>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="评估得分"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="评级"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="建议额度"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={64}>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="建议付款天数"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="付款条件01"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="付款条件02"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={64}>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="付款条件03"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="付款条件04"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
            </Row>
          </Form>

          <div>
            <h3 className={Styles.formLabel} style={{ display: 'inline' }}>
              模型输出-最终
            </h3>
          </div>
          <Form form={form} layout="vertical">
            <Row gutter={64}>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="最终得分"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="最终评级"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="最终额度"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={64}>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="最终建议天数"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="付款条件01"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="付款条件02"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={64}>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="付款条件03"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  label="付款条件04"
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Input disabled />
                </Form.Item>
              </Col>
              <Col span={8} key="MODEL_NAME">
                <Form.Item
                  name="MODEL_NAME"
                  rules={[
                    {
                      required: true,
                    },
                  ]}
                >
                  <Button type="primary" className={Styles.rightButton}>
                    详情
                  </Button>
                </Form.Item>
              </Col>
            </Row>
          </Form>
        </Space>
      </ProCard>
    </div>
  );
};

export default CustEval;
