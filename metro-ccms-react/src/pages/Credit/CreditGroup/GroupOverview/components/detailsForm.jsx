import React, { useState, useRef } from 'react';
import { Button, Card, Form, Row, Col, Input, message } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history } from 'umi';
import moment from 'moment';
import ProTable from '@ant-design/pro-table';
import FooterToolbar from '@/components/FooterToolbar';
import DeleteText from '@/components/DeleteText';
import { add, policyDelete } from '../service';
import Styles from '../index.less';

const FormItem = Form.Item;
const dateFormat = 'YYYY-MM-DD';
const currentDate = moment().format(dateFormat);

const SafeguardForm = () => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [policyList, setpolicyList] = useState([]);
  const [addloading, setloading] = useState(false);

  const toolBarRender = (
    <Button
      key="add"
      type="primary"
      onClick={async () => {
        
      }}
    >
      新增
    </Button>
  );

  const deleteMsg = async (res) => {
    const response = await policyDelete({ id: res.id });
    const { data, code, msg } = response;
    if (code === 200) {
      message.success(msg);
      setpolicyList(data ? [data] : []);
    } else {
      message.error(msg);
    }
  };

  // 提交方法
  const submit = async () => {
    const formMsg = await form.validateFields();
    setloading(true);
    const param = {
      ...formMsg,
      validFrom: moment(formMsg.validFrom).format(dateFormat),
      validTo: moment(formMsg.validTo).format(dateFormat),
      insurePolicyVOList: policyList,
    };
    const res = await add(param);
    const { msg, code } = res;
    if (code === 500) {
      message.error(msg);
      setloading(false);
    }
    if (code === 200) {
      message.success(msg);
      history.goBack();
    }
    setloading(false);
  };


  const columns = [
    {
      title: '卡号编码',
      dataIndex: 'compCode',
      hideInSearch: true,
      fixed: 'left',
      width: 120,
    },
    {
      title: '卡号名称',
      dataIndex: 'compName',
      ellipsis: true,
      width: 200,
      fixed: 'left',
    },
    {
      title: '客户编码',
      dataIndex: 'policyno',
      ellipsis: true,
      width: 150,
      fixed: 'left',
    },
    {
      title: '客户名称',
      dataIndex: 'body',
      ellipsis: true,
      width: 100,
      fixed: 'left',
    },
    {
      title: '操作',
      dataIndex: 'action',
      width: 130,
      fixed: 'right',
      render: (_, record) => {
        return <DeleteText deleteFunc={() => deleteMsg(record)} />;
      },
    },
  ];

  

  return (
    <PageContainer ghost title={false}>
      <Card title="基本信息">
        <Form
          form={form}
          layout="vertical"
          initialValues={{
            status: 1,
            validFrom: moment(currentDate),
          }}
        >
          <Row gutter={64}>
            <Col span={8}>
              <FormItem
                label="信用组编码"
                name="groupCode"
                rules={[
                  {
                    required: true,
                    message: '信用组编码不能为空！',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="信用组名称"
                name="groupName"
                rules={[
                  {
                    required: true,
                    message: '信用组名称不能为空！',
                  },
                ]}
              >
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem label="统一社会信用代码" name="creditno">
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem label="信用组负责人" name="groupCm">
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem label="负责人联系电话" name="mobil">
                <Input />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem label="负责人邮箱" name="email" rules={[{ type: 'email' }]}>
                <Input />
              </FormItem>
            </Col>
            {/* <Col span={8}>
              <FormItem
                label="有效状态"
                name="status"
                rules={[
                  {
                    required: true,
                    message: '请选择保单状态',
                  },
                ]}
              >
                <Radio.Group>
                  <Radio value={1}>有效</Radio>
                  <Radio value={0}>无效</Radio>
                </Radio.Group>
              </FormItem>
            </Col> */}
          </Row>
        </Form>
      </Card>
      <Card
        className={Styles.extraStyle}
        title="卡片信息"
        extra={toolBarRender}
        style={{ marginTop: '24px' }}
      >
        <ProTable
          headerTitle=""
          rowKey="policyno"
          dataSource={policyList}
          search={false}
          actionRef={ref}
          options={false}
          toolBarRender={false}
          pagination={false}
          columns={columns}
          scroll={{ x: 2170 }}
        />
      </Card>
      <FooterToolbar>
        <Button
          key="back"
          onClick={() => {
            history.goBack();
          }}
        >
          返回
        </Button>
        <Button
          type="primary"
          loading={addloading}
          onClick={() => {
            submit();
          }}
        >
          提交
        </Button>
      </FooterToolbar>
    </PageContainer>
  );
};

export default SafeguardForm;
