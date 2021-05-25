import React, { useState } from 'react';
import { Card, Form, Row, Col, Input, Select, InputNumber, Button } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import Details from './detailsModel';
import Styles from '../index.less';

const { Option } = Select;
const FormItem = Form.Item;

const InitiateEvalu = () => {
  const [form] = Form.useForm();
  const [detailsVis, setDetailsVis] = useState(false);

  const toolBarRender = (
    <Button key="add" type="primary">
      复用
    </Button>
  );
  const toolBarRender1 = (
    <Button
      key="end"
      type="primary"
      onClick={() => {
        setDetailsVis(true);
      }}
    >
      详情
    </Button>
  );

  const columns = [
    {
      title: '模型编码',
      dataIndex: 'modelCode',
    },
    {
      title: '模型名称',
      dataIndex: 'modelName',
    },
    {
      title: '评估得分',
      dataIndex: 'grade',
    },
    {
      title: '评级',
      dataIndex: 'rank',
    },
    {
      title: '建议额度',
      dataIndex: 'adviceLimit',
    },
    {
      title: '建议账期',
      dataIndex: 'adviceDays',
    },
    {
      title: '评估日期',
      dataIndex: 'pgDate',
      valueType: 'date',
    },
    {
      title: '有效截止日期',
      dataIndex: 'validTo',
      valueType: 'date',
    },
  ];

  const columns1 = [
    {
      title: '接口名称',
      dataIndex: 'interface',
    },
    {
      title: '调用时间',
      dataIndex: 'ddate',
    },
    {
      title: '有效截止日期',
      dataIndex: 'validTo',
    },
    {
      title: '有效状态',
      dataIndex: 'status',
      valueEnum: {
        0: {
          text: '失效',
        },
        1: {
          text: '失效',
        },
      },
    },
    {
      title: '操作',
      dataIndex: 'option',
      render: (_, record) => [<a key="Initiative">主动调用</a>],
    },
  ];

  const detailsMsg = {
    vis: detailsVis,
    onCancel: (data) => {
      setDetailsVis(data);
    },
  };

  return (
    <PageContainer ghost title={false}>
      <Card title="客户信息">
        <Form form={form} layout="vertical">
          <Row gutter={64}>
            <Col span={8}>
              <FormItem
                label="客户编码"
                name="custcode"
                rules={[
                  {
                    required: true,
                    message: '请输入公司编码',
                  },
                ]}
              >
                <Input maxLength={15} placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="客户名称"
                name="custname"
                rules={[
                  {
                    required: true,
                    message: '请输入公司名称',
                  },
                ]}
              >
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="行业分类"
                name="industrytype"
                rules={[
                  {
                    required: true,
                    message: '请选择生效时间',
                  },
                ]}
              >
                <Select>
                  <Option value="1">1</Option>
                  <Option value="2">2</Option>
                </Select>
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="组织类型"
                name="organtype"
                rules={[
                  {
                    required: true,
                    message: '组织类别不能为空',
                  },
                ]}
              >
                <Select>
                  <Option value="1">1</Option>
                  <Option value="2">2</Option>
                </Select>
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="CCMS统一社会信用代码"
                name="creditno"
                rules={[
                  {
                    required: true,
                    message: 'CCMS统一社会信用代码不能为空',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="是否黑名单"
                name="ifblack"
                rules={[
                  {
                    required: false,
                    message: '是否黑名单不能为空',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="是否白名单"
                name="ifwhite"
                rules={[
                  {
                    required: false,
                    message: '是否白名单不能为空',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
      <Card title="指标维护" style={{ marginTop: '24px' }}>
        <Form form={form} layout="vertical">
          <Row gutter={64}>
            <Col span={8}>
              <FormItem
                label="模型选择"
                name="chooseModal"
                rules={[
                  {
                    required: true,
                    message: '净资产收益率不能为空',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
          </Row>
          <Row gutter={64}>
            <Col span={8}>
              <FormItem
                label="净资产收益率（%）"
                name="MODEL_CODE"
                rules={[
                  {
                    required: true,
                    message: '净资产收益率不能为空',
                  },
                ]}
              >
                <InputNumber maxLength={15} placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="总资产周转率（%）"
                name="MODEL_NAME"
                rules={[
                  {
                    required: true,
                    message: '请输入总资产周转率',
                  },
                ]}
              >
                <InputNumber placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="资产负债率（%）"
                name="ADVICE_LIMIT"
                rules={[
                  {
                    required: false,
                    message: '请选择资产负债率',
                  },
                ]}
              >
                <InputNumber />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="资产规模"
                name="ADVICE_DAYS"
                rules={[
                  {
                    required: true,
                    message: '资产规模不能为空',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="销售（营业）增长率（%）"
                name="creditno"
                rules={[
                  {
                    required: true,
                    message: '销售（营业）增长率不能为空',
                  },
                ]}
              >
                <InputNumber />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="企业规模"
                name="ifblack"
                rules={[
                  {
                    required: true,
                    message: '企业规模不能为空',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="资本累积率（%）"
                name="ifwhite"
                rules={[
                  {
                    required: false,
                    message: '资本累积率不能为空',
                  },
                ]}
              >
                <InputNumber />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="平均提前/逾期还款天数（天）"
                name="ifwh345ite"
                rules={[
                  {
                    required: false,
                    message: '平均提前/逾期还款天数不能为空',
                  },
                ]}
              >
                <InputNumber />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="交易金额增长率（%）"
                name="ifwhi676te"
                rules={[
                  {
                    required: false,
                    message: '交易金额增长率不能为空',
                  },
                ]}
              >
                <InputNumber />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="逾期金额比例变化率（%）"
                name="if45white"
                rules={[
                  {
                    required: false,
                    message: '逾期金额比例变化率不能为空',
                  },
                ]}
              >
                <InputNumber />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="成立日期/时间"
                name="ifwh45ite"
                rules={[
                  {
                    required: false,
                    message: '成立日期/时间不能为空',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="主体类型"
                name="ifwhi453te"
                rules={[
                  {
                    required: false,
                    message: '主体类型不能为空',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="已抵押资产占总资产的比例（%）"
                name="ifwh354ite"
                rules={[
                  {
                    required: false,
                    message: '已抵押资产占总资产的比例不能为空',
                  },
                ]}
              >
                <InputNumber />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="对外投资情况"
                name="ifwhi352te"
                rules={[
                  {
                    required: false,
                    message: '对外投资情况不能为空',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="企业负面信息"
                name="ifwhi54te"
                rules={[
                  {
                    required: false,
                    message: '企业负面信息不能为空',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="区域风险情况"
                name="ifwhi35te"
                rules={[
                  {
                    required: false,
                    message: '区域风险情况不能为空',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="注册资本"
                name="ifwhit35e"
                rules={[
                  {
                    required: false,
                    message: '注册资本不能为空',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
      <Card title="模型输出-建议" style={{ marginTop: '24px' }}>
        <Form form={form} layout="vertical">
          <Row gutter={64}>
            <Col span={8}>
              <Form.Item
                label="评估得分"
                name="MODEL_NAME1"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <InputNumber />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="评级"
                name="MODEL_NAME2"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="建议额度（万元）"
                name="MODEL_NAME3"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <InputNumber />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={64}>
            <Col span={8}>
              <Form.Item
                label="建议付款天数（天）"
                name="MODEL_NAME4"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <InputNumber />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="付款条件01"
                name="MODEL_NAME5"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="付款条件02"
                name="MODEL_NAME6"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={64}>
            <Col span={8}>
              <Form.Item
                label="付款条件03"
                name="MODEL_NAME7"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="付款条件04"
                name="MODEL_NAME8"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Card>
      <Card
        title="模型输出-最终"
        className={Styles.extraStyle}
        extra={toolBarRender1}
        style={{ marginTop: '24px' }}
      >
        <Form form={form} layout="vertical">
          <Row gutter={64}>
            <Col span={8}>
              <Form.Item
                label="最终得分"
                name="MODEL_NAM1E"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="最终评级"
                name="MODEL_NAM2E"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="最终额度（万元）"
                name="MODEL_NA3ME"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <InputNumber />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={64}>
            <Col span={8}>
              <Form.Item
                label="最终建议天数（天）"
                name="MODEL_NAM4E"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <InputNumber />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="付款条件01"
                name="MODEL_NA4ME"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="付款条件02"
                name="MODEL_NAM5E"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={64}>
            <Col span={8}>
              <Form.Item
                label="付款条件03"
                name="MODEL_N4AME"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                label="付款条件04"
                name="MODEL_NA45ME"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>
            <Col span={8}>
              <Form.Item
                name="MODEL_N45AME"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                {/* <Button type="primary" className={Styles.rightButton}>
                  详情
                </Button> */}
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Card>
      <Card
        extra={toolBarRender}
        className={Styles.extraStyle}
        title="当前有效评估"
        style={{ marginTop: '24px' }}
      >
        <ProTable
          headerTitle=""
          rowKey="id"
          dataSource={[]}
          search={false}
          options={false}
          toolBarRender={false}
          pagination={false}
          columns={columns}
        />
      </Card>
      <Card title="外部征信获取记录" style={{ marginTop: '24px' }}>
        <ProTable
          headerTitle=""
          rowKey="id"
          dataSource={[]}
          search={false}
          options={false}
          toolBarRender={false}
          pagination={false}
          columns={columns1}
        />
      </Card>
      <Details {...detailsMsg} />
    </PageContainer>
  );
};

export default InitiateEvalu;
