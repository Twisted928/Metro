import React, { useState, useRef } from 'react';
import { Input, Form, Row, Col, Button, Modal, Space, Popover, Radio } from 'antd';
import { connect } from 'umi';
import ProTable from '@ant-design/pro-table';
import Styles from './style.less';

const SearchCust = ({ visible, onClose }) => {
  const [form] = Form.useForm();
  const actionRef = useRef();
  const formRef = useRef();
  const [popVisible, setPopVisible] = useState(false);

  const onSearch = () => {};
  const onFinish = () => {};
  const onFinishFailed = () => {};

  const columns = [
    {
      title: '客户名称',
      dataIndex: 'custname',
    },
    {
      title: '统一社会信用代码',
      dataIndex: 'tcode',
    },
    {
      title: '行业分类',
      dataIndex: 'indusType',
    },
    {
      title: '黑名单',
      dataIndex: 'blacklist',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '否',
        },
        1: {
          text: '是',
        },
      },
    },
    {
      title: '白名单',
      dataIndex: 'blacklist',
      valueType: 'select',
      valueEnum: {
        0: {
          text: '否',
        },
        1: {
          text: '是',
        },
      },
    },
  ];

  const columns_cust = [
    {
      title: '统一社会信用代码',
      dataIndex: 'tcode',
    },
    {
      title: '客户名称',
      dataIndex: 'custname',
    },
    {
      title: '曾用名',
      dataIndex: 'namebefore',
    },
    {
      title: '获取时间',
      dataIndex: 'getTime',
    },
  ];

  // const toolBarRender = () => [
  //   <Button key="searchname" type="primary">
  //     按卡号查寻
  //   </Button>,
  //   <Button key="searchcode" type="primary">
  //     按名称查询
  //   </Button>,
  // ];

  return (
    <Modal
      visible={visible}
      width={1000}
      title="工商信息查询"
      destroyOnClose
      focusTriggerAfterClose={false}
      maskClosable={false}
      footer={false}
      onCancel={() => {
        onClose();
      }}
    >
      <Space direction="vertical" style={{ width: '100%' }} size="middle">
        <Form form={form} onFinish={onFinish} onFinishFailed={onFinishFailed} layout="vertical">
          <Row gutter={64}>
            <Col span={6} key="tcode">
              <Form.Item label="统一社会信用代码" name="tcode">
                <Input />
              </Form.Item>
            </Col>
            <Col span={6} key="custcode">
              <Form.Item label="客户编码" name="custcode">
                <Input />
              </Form.Item>
            </Col>
            <Col span={6} key="custname">
              <Form.Item label="客户名称" name="custname">
                <Input />
              </Form.Item>
            </Col>
            <Col span={6} key="search" flex="auto" className={Styles.buttonContainer}>
              <Space>
                <Button type="primary" onClick={onSearch}>
                  搜索
                </Button>

                <Popover
                  visible={popVisible}
                  content={() => {
                    return (
                      <Space>
                        <Radio.Group>
                          <Radio value={1}>统一社会信用代码</Radio>
                          <Radio value={2}>客户名称</Radio>
                        </Radio.Group>

                        <Button
                          onClick={() => {
                            setPopVisible(false);
                          }}
                          type="primary"
                        >
                          确认
                        </Button>
                      </Space>
                    );
                  }}
                >
                  <Button
                    type="primary"
                    onClick={() => {
                      setPopVisible(true);
                    }}
                  >
                    条件查询
                  </Button>
                </Popover>
              </Space>
            </Col>
          </Row>
        </Form>

        <ProTable
          rowKey="id"
          headerTitle="客户主数据信息"
          options={false}
          search={false}
          actionRef={actionRef}
          formRef={formRef}
          columns={columns}
          pagination={{
            pageSize: 5,
          }}
        />

        <ProTable
          rowKey="id"
          headerTitle="工商信息获取记录"
          options={false}
          search={false}
          actionRef={actionRef}
          formRef={formRef}
          columns={columns_cust}
          pagination={{
            pageSize: 5,
          }}
          // toolBarRender={toolBarRender}
        />
      </Space>
    </Modal>
  );
};

export default connect(({ creditapply, loading }) => ({
  creditapply,
  loading: loading.effects['creditapply/query'],
}))(SearchCust);
