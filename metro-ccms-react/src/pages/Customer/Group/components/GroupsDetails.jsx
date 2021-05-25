import React, { useRef } from 'react';
import { Form, Select, Input, Row, Col, Space, Card, } from 'antd';
import { history, connect } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';


const { Option } = Select

const testData = {
  CUST_CODE: "356482216",
  CUST_NAME: "字符长度限制在十个字",
  BUSINESS_TYPE: "A",
  CUST_TYPE: "A",
  INDUSTRY_TYPE: "A",
  ORGAN_TYPE: "A",
  CUST_BLOCK_DESC: "字符长度限制在十个字",
  CREDIT_BLOCK_DESC: "字符长度限制在十个字",
  IFBLACK: 'Error',
  CREATED_BY: '字符长度限制在十个字',
  CREATE_TIME: '2020-12-03',
  UPDATED_BY: '字符长度限制在十个字',
  UPDATE_TIME: '2020-12-03',
}

const testDataTab_0 = [
  {
    id: 325132032,
    CARD_CODE: '155654831',
    CARD_NAME: '字符长度限制在十个字',
    DEPART_NAME: '字符长度限制在十个字',
    STORE_NAME: '字符长度限制在十个字',
    CUST_MANAGER_NAME: '字符长度限制在十个字',
    CUST_GROUP: '字符长度限制在十个字',
  },
  {
    id: 325162032,
    CARD_CODE: '155654831',
    CARD_NAME: '字符长度限制在十个字',
    DEPART_NAME: '字符长度限制在十个字',
    STORE_NAME: '字符长度限制在十个字',
    CUST_MANAGER_NAME: '字符长度限制在十个字',
    CUST_GROUP: '字符长度限制在十个字',
  },
  {
    id: 325139432,
    CARD_CODE: '155654831',
    CARD_NAME: '字符长度限制在十个字',
    DEPART_NAME: '字符长度限制在十个字',
    STORE_NAME: '字符长度限制在十个字',
    CUST_MANAGER_NAME: '字符长度限制在十个字',
    CUST_GROUP: '字符长度限制在十个字',
  },
]

const testDataTab_1 = [
  {
    id: 325132032,
    CARD_CODE: '155654831',
    CARD_NAME: '字符长度限制在十个字',
    LIMIT_TYPE: '字符长度限制在十个字',
    PAYMENT_TERM: '字符长度限制在十个字',
    LIMIT: '字符长度限制在十个字',
    CURRENCY: '字符长度限制在十个字',
    VALID_FROM: '2020-12-3',
    VALID_TO: '2020-12-3',
    STATUS: 0,
  },
  {
    id: 325132032,
    CARD_CODE: '155654831',
    CARD_NAME: '字符长度限制在十个字',
    LIMIT_TYPE: '字符长度限制在十个字',
    PAYMENT_TERM: '字符长度限制在十个字',
    LIMIT: '字符长度限制在十个字',
    CURRENCY: '字符长度限制在十个字',
    VALID_FROM: '2020-12-3',
    VALID_TO: '2020-12-3',
    STATUS: 1,
  },
  {
    id: 325132032,
    CARD_CODE: '155654831',
    CARD_NAME: '字符长度限制在十个字',
    LIMIT_TYPE: '字符长度限制在十个字',
    PAYMENT_TERM: '字符长度限制在十个字',
    LIMIT: '字符长度限制在十个字',
    CURRENCY: '字符长度限制在十个字',
    VALID_FROM: '2020-12-3',
    VALID_TO: '2020-12-3',
    STATUS: 0,
  },
]

const testDataTab_2 = [
  {
    id: 325132032,
    CARD_CODE: '155654831',
    CARD_NAME: '字符长度限制在十个字',
    CONTRACT_NO: '155654831',
    CONTRACT_TYPE: '字符长度限制在十个字',
    VALID_FROM: '2020-12-3',
    VALID_TO: '2020-12-3',
    APPLICATION_NO: '155654831',
    STATUS: 0,
  },
  {
    id: 325132332,
    CARD_CODE: '155654831',
    CARD_NAME: '字符长度限制在十个字',
    CONTRACT_NO: '155654831',
    CONTRACT_TYPE: '字符长度限制在十个字',
    VALID_FROM: '2020-12-3',
    VALID_TO: '2020-12-3',
    APPLICATION_NO: '155654831',
    STATUS: 1,
  },
  {
    id: 325132362,
    CARD_CODE: '155654831',
    CARD_NAME: '字符长度限制在十个字',
    CONTRACT_NO: '155654831',
    CONTRACT_TYPE: '字符长度限制在十个字',
    VALID_FROM: '2020-12-3',
    VALID_TO: '2020-12-3',
    APPLICATION_NO: '155654831',
    STATUS: 0,
  },
]

const testDataTab_3 = [
  {
    id: 325132032,
    DEPART_NAME: '字符长度限制在十个字',
    STORE_NAME: '字符长度限制在十个字',
    BENEFITOR: '字符长度限制在十个字',
    GTCODE: '155654831',
    GTSUM: '1000000',
    VALID_FROM: '2020-12-3',
    VALID_TO: '2020-12-3',
    IFLOOP: 1,
  },
  {
    id: 325742032,
    DEPART_NAME: '字符长度限制在十个字',
    STORE_NAME: '字符长度限制在十个字',
    BENEFITOR: '字符长度限制在十个字',
    GTCODE: '155654831',
    GTSUM: '1000000',
    VALID_FROM: '2020-12-3',
    VALID_TO: '2020-12-3',
    IFLOOP: 0,
  },
  {
    id: 325132152,
    DEPART_NAME: '字符长度限制在十个字',
    STORE_NAME: '字符长度限制在十个字',
    BENEFITOR: '字符长度限制在十个字',
    GTCODE: '155654831',
    GTSUM: '1000000',
    VALID_FROM: '2020-12-3',
    VALID_TO: '2020-12-3',
    IFLOOP: 1,
  },
]

const testDataTab_4 = [
  {
    id: 325132032,
    CARD_CODE: '155654831',
    CARD_NAME: '字符长度限制在十个字',
    DEPART_NAME: '字符长度限制在十个字',
    STORE_NAME: '155654831',
    IAR: '1000000',
    IDUE: '500000',
  },
  {
    id: 327632032,
    CARD_CODE: '155654831',
    CARD_NAME: '字符长度限制在十个字',
    DEPART_NAME: '字符长度限制在十个字',
    STORE_NAME: '155654831',
    IAR: '1000000',
    IDUE: '500000',
  },
  {
    id: 325132932,
    CARD_CODE: '155654831',
    CARD_NAME: '字符长度限制在十个字',
    DEPART_NAME: '字符长度限制在十个字',
    STORE_NAME: '155654831',
    IAR: '1000000',
    IDUE: '500000',
  },
]



// eslint-disable-next-line @typescript-eslint/no-unused-vars
const GroupsDetails = ({ custgroup: { pagination } }) => {
  const [form] = Form.useForm();
  const formRef = useRef();
  const actionRef = useRef();

  const onFinish = (value) => {
    // eslint-disable-next-line no-console
    console.log("Finish", value)
  };

  const columns_0 = [
    {
      title: '卡号编码',
      dataIndex: 'CARD_CODE',
    },
    {
      title: '卡号名称',
      dataIndex: 'CARD_NAME',
    },
    {
      title: '部门名称',
      dataIndex: 'DEPART_NAME',
    },
    {
      title: '门店名称',
      dataIndex: 'STORE_NAME',
    },
    {
      title: '客户经理名称',
      dataIndex: 'CUST_MANAGER_NAME',
    },
    {
      title: '客户组',
      dataIndex: 'CUST_GROUP',
    },
  ]

  const columns_1 = [
    {
      title: '卡号编码',
      dataIndex: 'CARD_CODE',
    },
    {
      title: '卡号名称',
      dataIndex: 'CARD_NAME',
    },
    {
      title: '额度类型',
      dataIndex: 'LIMIT_TYPE',
    },
    {
      title: '发布账期',
      dataIndex: 'PAYMENT_TERM',
    },
    {
      title: '发布额度',
      dataIndex: 'LIMIT',
    },
    {
      title: '币种',
      dataIndex: 'CURRENCY',
    },
    {
      title: '生效日',
      dataIndex: 'VALID_FROM',
      valueType: 'date',
    },
    {
      title: '到期日',
      dataIndex: 'VALID_TO',
      valueType: 'date',
    },
    {
      title: '状态',
      dataIndex: 'STATUS',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '无效',
          status: 'Error',
        },
        1: {
          text: '有效',
          status: 'Success',
        },
      }
    },
  ]

  const columns_2 = [
    {
      title: '卡号编码',
      dataIndex: 'CARD_CODE',
    },
    {
      title: '卡号名称',
      dataIndex: 'CARD_NAME',
    },
    {
      title: '合同编码',
      dataIndex: 'CONTRACT_NO',
    },
    {
      title: '合同类型',
      dataIndex: 'CONTRACT_TYPE',
    },
    {
      title: '生效日',
      dataIndex: 'VALID_FROM',
      valueType: 'date',
    },
    {
      title: '到期日',
      dataIndex: 'VALID_TO',
      valueType: 'date',
    },
    {
      title: '申请单号',
      dataIndex: 'APPLICATION_NO',
    },
    {
      title: '状态',
      dataIndex: 'STATUS',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '无效',
          status: 'Error',
        },
        1: {
          text: '有效',
          status: 'Success',
        },
      }
    },
  ]

  const columns_3 = [
    {
      title: '部门名称',
      dataIndex: 'DEPART_NAME',
    },
    {
      title: '门店名称',
      dataIndex: 'STORE_NAME',
    },
    {
      title: '担保函受益人',
      dataIndex: 'BENEFITOR',
    },
    {
      title: '担保函编码',
      dataIndex: 'GTCODE',
    },
    {
      title: '担保金额',
      dataIndex: 'GTSUM',
      valueType: 'money',
    },
    {
      title: '生效区间',
      dataIndex: 'VALID_FROM',
      valueType: 'dateRange',
    },
    {
      title: '是否循环',
      dataIndex: 'IFLOOP',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '非循环',
          status: 'Error',
        },
        1: {
          text: '循环',
          status: 'Success',
        },
      }
    },
  ]

  const columns_4 = [
    {
      title: '卡号编码',
      dataIndex: 'CARD_CODE',
      valueType: 'index'
    },
    {
      title: '卡号名称',
      dataIndex: 'CARD_NAME',
    },
    {
      title: '部门名称',
      dataIndex: 'DEPART_NAME',
    },
    {
      title: '门店名称',
      dataIndex: 'STORE_NAME',
    },
    {
      title: '应收金额',
      dataIndex: 'IAR',
      valueType: 'money',
    },
    {
      title: '逾期金额',
      dataIndex: 'IDUE',
      valueType: 'money',
    },
  ]

  return (
    <PageContainer
      ghost
      title={false}
      onBack={() => {
        history.goBack();
      }}
    >
      <Space
        direction="vertical"
        style={{ width: "100%" }}
        size="large"
      >
        <Card
          title="信用客户管理-详情"
        >
          <Form
            initialValues={testData}
            form={form}
            onFinish={onFinish}
            layout="vertical"
          >
            <Row gutter={24}>
              <Col span={8} key={1}>
                <Form.Item
                  label="客户编码"
                  name="CUST_CODE"
                >
                  <Input
                    disabled
                  />
                </Form.Item>
              </Col>

              <Col span={8} key={2}>
                <Form.Item
                  label="客户名称"
                  name="CUST_NAME"
                >
                  <Input
                    disabled
                  />
                </Form.Item>
              </Col>

              <Col span={8} key={3}>
                <Form.Item
                  label="业务类型"
                  name="BUSINESS_TYPE"
                >
                  <Select disabled>
                    <Option value="A">A</Option>
                    <Option value="B">B</Option>
                  </Select>
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={24}>
              <Col span={8} key={4}>
                <Form.Item
                  label="客户类型"
                  name="CUST_TYPE"
                >
                  <Select disabled>
                    <Option value="A">A</Option>
                    <Option value="B">B</Option>
                  </Select>
                </Form.Item>
              </Col>
              <Col span={8} key={5}>
                <Form.Item
                  label="行业类型"
                  name="INDUSTRY_TYPE"
                >
                  <Select disabled>
                    <Option value="A">A</Option>
                    <Option value="B">B</Option>
                  </Select>
                </Form.Item>
              </Col>
              <Col span={8} key={6}>
                <Form.Item
                  label="组织类型"
                  name="ORGAN_TYPE"
                >
                  <Select disabled>
                    <Option value="A">A</Option>
                    <Option value="B">B</Option>
                  </Select>
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={24}>
              <Col span={8} key={7}>
                <Form.Item
                  label="客户锁定状态描述"
                  name="CUST_BLOCK_DESC"
                >
                  <Input
                    disabled
                  />
                </Form.Item>
              </Col>
              <Col span={8} key={8}>
                <Form.Item
                  label="信用冻结描述"
                  name="CREDIT_BLOCK_DESC"
                >
                  <Input
                    disabled
                  />
                </Form.Item>
              </Col>
              <Col span={8} key={9}>
                <Form.Item
                  label="是否黑名单"
                  name="IFBLACK"
                >
                  <Select
                    disabled
                  >
                    <Option value="Error">否</Option>
                    <Option value="Success">是</Option>
                  </Select>
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={24}>
              <Col span={8} key={10}>
                <Form.Item
                  label="创建人"
                  name="CREATED_BY"
                >
                  <Input
                    disabled
                  />
                </Form.Item>
              </Col>
              <Col span={8} key={11}>
                <Form.Item
                  label="创建时间"
                  name="CREATE_TIME"
                >
                  <Input
                    disabled
                  />
                </Form.Item>
              </Col>
              <Col span={8} key={12}>
                <Form.Item
                  label="更新人"
                  name="UPDATED_BY"
                >
                  <Input
                    disabled
                  />
                </Form.Item>
              </Col>
            </Row>
            <Row gutter={24}>
              <Col span={8} key={13}>
                <Form.Item
                  label="更新时间"
                  name="UPDATE_TIME"
                >
                  <Input
                    disabled
                  />
                </Form.Item>
              </Col>
            </Row>
          </Form>
        </Card>
        <Card
          title="基本信息"
        >
          <ProTable
            rowKey="id"
            options={false}
            actionRef={actionRef}
            formRef={formRef}
            columns={columns_0}
            dataSource={testDataTab_0}
            pagination={pagination}
            // loading={loading}
            search={false}
          />
        </Card>
        <Card
          title="授信信息"
        >
          <ProTable
            rowKey="id"
            options={false}
            actionRef={actionRef}
            formRef={formRef}
            columns={columns_1}
            dataSource={testDataTab_1}
            pagination={pagination}
            // loading={loading}
            search={false}
          />
        </Card>
        <Card
          title="合同信息"
        >
          <ProTable
            rowKey="id"
            options={false}
            actionRef={actionRef}
            formRef={formRef}
            columns={columns_2}
            dataSource={testDataTab_2}
            pagination={pagination}
            // loading={loading}
            search={false}
          />
        </Card>
        <Card
          title="债券保障信息"
        >
          <ProTable
            rowKey="id"
            options={false}
            actionRef={actionRef}
            formRef={formRef}
            columns={columns_3}
            dataSource={testDataTab_3}
            pagination={pagination}
            // loading={loading}
            search={false}
          />
        </Card>
        <Card
          title="应收逾期账龄"
        >
          <ProTable
            rowKey="id"
            options={false}
            actionRef={actionRef}
            formRef={formRef}
            columns={columns_4}
            dataSource={testDataTab_4}
            pagination={pagination}
            // loading={loading}
            search={false}
          />
        </Card>
      </Space>
    </PageContainer>
  )
}

export default connect(({ custgroup, loading }) => ({
  custgroup,
  loading: loading.models.custgroup,
}))(GroupsDetails);
