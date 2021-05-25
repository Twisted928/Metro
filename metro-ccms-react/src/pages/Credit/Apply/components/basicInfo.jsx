import React, { useState } from 'react';
import { DownOutlined } from '@ant-design/icons';
import ProCard from '@ant-design/pro-card';
import { Input, Form, Row, Col, Select, Button } from 'antd';
import SearchCust from './SearchCust';
import Styles from './style.less';

const { Option } = Select;

const BasicInfo = () => {
  const [collapsed, setCollapsed] = useState(false);
  const [visible, setVisible] = useState(false);
  const [form] = Form.useForm();

  const onFocus = () => {
    setVisible(true);
  };

  return (
    <div id="BasicInfo">
      <ProCard
        title="基本信息"
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
          <h3 className={Styles.formLabel}> 卡号信息</h3>
        </div>
        <Form layout="vertical" form={form}>
          <Row gutter={64}>
            <Col span={8} key="storename">
              <Form.Item
                label="门店名称（编码）"
                name="storename"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input disabled />
              </Form.Item>
            </Col>

            <Col span={8} key="cardcode">
              <Form.Item
                label="卡号编码"
                name="cardcode"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input disabled />
              </Form.Item>
            </Col>

            <Col span={8} key="cardname">
              <Form.Item label="卡号名称" name="cardname">
                <Input disabled />
              </Form.Item>
            </Col>
          </Row>

          <Row gutter={64}>
            <Col span={8} key="businesstype">
              <Form.Item
                label="业务类型"
                name="businesstype"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Select disabled>
                  <Option value="0">全国重点客户</Option>
                  <Option value="1">集团关联客户</Option>
                  <Option value="2">项目客户</Option>
                  <Option value="3">内部公司客户</Option>
                  <Option value="4">普通客户</Option>
                  <Option value="5">预付款客户</Option>
                </Select>
              </Form.Item>
            </Col>

            <Col span={8} key="custtype">
              <Form.Item
                label="客户类型"
                name="custtype"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Select disabled>
                  <Option value="0">全国重点客户</Option>
                  <Option value="1">集团关联客户</Option>
                  <Option value="2">项目客户</Option>
                  <Option value="3">内部公司客户</Option>
                  <Option value="4">普通客户</Option>
                  <Option value="5">预付款客户</Option>
                </Select>
              </Form.Item>
            </Col>

            <Col span={8} key="groupname">
              <Form.Item
                label="信用组名称"
                name="groupname"
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
            <Col span={8} key="creditnocrm">
              <Form.Item label="统一社会信用代码(CRM)" name="creditnocrm">
                <Input disabled />
              </Form.Item>
            </Col>

            <Col span={8} key="custgroup">
              <Form.Item label="客户组" name="custgroup">
                <Input disabled />
              </Form.Item>
            </Col>
            <Col span={8} key="dealerpotential">
              <Form.Item label="客户购买潜力" name="dealerpotential">
                <Input disabled />
              </Form.Item>
            </Col>
          </Row>

          <Row gutter={64}>
            <Col span={8} key="custmanagername">
              <Form.Item label="客户经理" name="custmanagername">
                <Input disabled />
              </Form.Item>
            </Col>
          </Row>
        </Form>

        <div>
          <h3 className={Styles.formLabel}> 客户信息</h3>
        </div>
        <Form layout="vertical" form={form}>
          <Row gutter={64}>
            <Col span={8} key="custcode">
              <Form.Item
                name="custcode"
                label="客户编码"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input onFocus={onFocus} />
              </Form.Item>
            </Col>

            <Col span={8} key="custname">
              <Form.Item
                name="custname"
                label="客户名称"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input onFocus={onFocus} />
              </Form.Item>
            </Col>

            <Col span={8} key="industrytype">
              <Form.Item
                label="行业分类"
                name="industrytype"
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
            <Col span={8} key="organtype">
              <Form.Item
                label="组织类型"
                name="organtype"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input />
              </Form.Item>
            </Col>

            <Col span={8} key="creditno">
              <Form.Item
                label="CCMS统一社会信用代码"
                name="creditno"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input onFocus={onFocus} />
              </Form.Item>
            </Col>

            <Col span={8} key="ifblack">
              <Form.Item
                label="是否黑名单"
                name="ifblack"
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
          <Row gutter={64} align="middle">
            <Col span={8} key="ifwhite">
              <Form.Item
                label="是否白名单"
                name="ifwhite"
                rules={[
                  {
                    required: true,
                  },
                ]}
              >
                <Input disabled />
              </Form.Item>
            </Col>
            <Col
              span={3}
              key="searchBtn"
              offset={13}
              flex="auto"
            >
              <Button
                type="primary"
                // size="large"
                className={Styles.rightButton}
                onClick={() => {
                  setVisible(true);
                }}
              >
                维护
              </Button>
            </Col>
          </Row>
        </Form>
      </ProCard>

      <SearchCust
        visible={visible}
        destroyOnClose
        onClose={() => {
          setVisible(false);
        }}
      />
    </div>
  );
};

export default BasicInfo;
