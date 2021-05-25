import React, { useRef } from 'react';
import {
   Input,
   Form,
   Row,
   Col,
   Select,
   Card,
   Table,
   Space,
   Button,
} from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { connect, history, } from 'umi';

const { Option } = Select;

const testData = {
   CUST_CODE: "356482216",
   CUST_NAME: "字符长度限制在十个字",
   BUSINESS_TYPE: "A",
   CUST_TYPE: "A",
   INDUSTRY_TYPE: "A",
   ORGAN_TYPE: "A",
   CUST_BLOCK_DESC: "字符长度限制在十个字",
   CREDIT_BLOCK_DESC: "字符长度限制在十个字",
   IFBLACK: "0",
   CREATED_BY: '字符长度限制在十个字',
   CREATE_TIME: '2020-12-03',
   UPDATED_BY: '字符长度限制在十个字',
   UPDATE_TIME: '2020-12-03',
}

const testDataTab = [
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

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const CreditEdit = ({  custgroup: { pagination } }) => {
   const [form] = Form.useForm();
   const formRef = useRef();
   const actionRef = useRef();

   const columns = [
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
         title: '客户经理',
         dataIndex: 'CUST_MANAGER_NAME',
      },
      {
         title: '客户组',
         dataIndex: 'CUST_GROUP',
      },
   ]
   
   const onFinish = (values) => {
      // eslint-disable-next-line no-console
      console.log("Finish", values)
   }

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
               title="信用客户管理-修改"
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
                           <Input disabled />
                        </Form.Item>
                     </Col>

                     <Col span={8} key={2}>
                        <Form.Item
                           label="客户名称"
                           name="CUST_NAME"
                        >
                           <Input disabled />
                        </Form.Item>
                     </Col>

                     <Col span={8} key={3}>
                        <Form.Item
                           label="业务类型"
                           name="BUSINESS_TYPE"
                        >
                           <Select>
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
                           <Select>
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
                           <Select>
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
                           <Select>
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
                           <Input disabled />
                        </Form.Item>
                     </Col>
                     <Col span={8} key={8}>
                        <Form.Item
                           label="信用冻结描述"
                           name="CREDIT_BLOCK_DESC"
                        >
                           <Input disabled />
                        </Form.Item>
                     </Col>
                     <Col span={8} key={9}>
                        <Form.Item
                           label="是否黑名单"
                           name="IFBLACK"
                        >
                           <Select disabled >
                              <Option value="0">否</Option>
                              <Option value="1">是</Option>
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
                           <Input disabled />
                        </Form.Item>
                     </Col>
                     <Col span={8} key={11}>
                        <Form.Item
                           label="创建时间"
                           name="CREATE_TIME"
                        >
                           <Input disabled />
                        </Form.Item>
                     </Col>
                     <Col span={8} key={12}>
                        <Form.Item
                           label="更新人"
                           name="UPDATED_BY"
                        >
                           <Input disabled />
                        </Form.Item>
                     </Col>
                  </Row>
                  <Row gutter={24}>
                     <Col span={8} key={13}>
                        <Form.Item
                           label="更新时间"
                           name="UPDATE_TIME"
                        >
                           <Input disabled />
                        </Form.Item>
                     </Col>
                  </Row>
                  <Form.Item
                     name="SUBMIT"
                  >
                     <Button type="primary" size="large" htmlType="submit" style={{ float: "right" }}>
                        提交
                     </Button>
                  </Form.Item>
               </Form>
            </Card>
            <Card>
               <Table
                  rowKey="id"
                  headerTitle="信用冻结解冻"
                  options={false}
                  actionRef={actionRef}
                  formRef={formRef}
                  columns={columns}
                  dataSource={testDataTab}
                  pagination={pagination}
                  // loading={loading}
                  search={{ span: 8, labelWidth: 100 }}
               />
            </Card>
         </Space>
      </PageContainer>
   );
};

export default connect(({ custgroup, loading }) => ({
   custgroup,
   loading: loading.models.custgroup,
}))(CreditEdit);
