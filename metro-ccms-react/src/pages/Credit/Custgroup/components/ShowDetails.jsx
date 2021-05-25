/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect, useState, useRef } from 'react';
import { Form, Input, Row, Col, Card, Button, message, Spin } from 'antd';
import ProTable from '@ant-design/pro-table';
import { listInfo } from '../service';
import numeral from 'numeral';
import FooterToolbar from '@/components/FooterToolbar';
import { history, connect } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import Styles from '../index.less';

const FormItem = Form.Item;

const ShowDetails = () => {
  const [form] = Form.useForm();
  const formRef = useRef();
  const [list, setList] = useState([]);
  const [loading, setLoading] = useState([false]);

  const query = async () => {
    setLoading(true);
    const { custGroup } = history.location.query;
    const res = await listInfo({ custGroup });
    const { code, data, msg } = res;
    if (code === 200) {
      form.setFieldsValue(data[0]);
      setList(data);
      setLoading(false);
    }
    if (code === 500) {
      message.error(msg);
      setLoading(false);
    }
  };

  useEffect(() => {
    query();
  }, []);

  const columns = [
    {
      title: '部门名称',
      dataIndex: 'deptName',
    },
    {
      title: '门店编码',
      dataIndex: 'storeCode',
    },
    {
      title: '卡号编码',
      dataIndex: 'cardCode',
    },
    {
      title: '卡号名称',
      dataIndex: 'cardName',
    },
    {
      title: '发布账期（天）',
      dataIndex: 'paymentTerm',
      align: 'right',
    },
    {
      title: '发布额度（万元）',
      dataIndex: 'limit',
      align: 'right',
      render: (_, record) => numeral(record.limit).format('0,0.00'),
    },
    {
      title: '生效时间',
      dataIndex: 'validFrom',
      valueTye: 'date',
    },
    {
      title: '到期时间',
      dataIndex: 'validTo',
      valueTye: 'date',
    },
    {
      title: '总收入（万元）',
      dataIndex: 'totalRevenue',
      align: 'right',
      render: (_, record) => numeral(record.totalRevenue).format('0,0.00'),
    },
    {
      title: '应收金额（万元）',
      dataIndex: 'iar',
      align: 'right',
      render: (_, record) => numeral(record.iar).format('0,0.00'),
    },
    {
      title: '逾期金额（万元）',
      dataIndex: 'idue',
      align: 'right',
      render: (_, record) => numeral(record.idue).format('0,0.00'),
    },
  ];

  return (
    <PageContainer
      ghost
      title={false}
      onBack={() => {
        history.goBack();
      }}
    >
      <Spin spinning={loading}>
        <Card
          className={Styles.cardWrap}
          title="详细信息"
          bordered
          // loading={loading}
        >
          <Form form={form} formRef={formRef} layout="vertical">
            <Row gutter={64}>
              <Col span={8}>
                <FormItem name="custGroup" label="客户组编码">
                  <Input width="130" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem name="custgrName" label="客户组名称">
                  <Input width="130" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem name="custgrPayterm" label="客户组账期（天）">
                  <Input width="130" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem name="custgrLimit" label="客户组额度（万元）">
                  <Input width="130" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem name="totalRevenue" label="总收入（万元）">
                  <Input width="130" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem name="iar" label="应收金额（万元）">
                  <Input width="130" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem name="idue" label="逾期金额（万元）">
                  <Input width="130" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem name="cardNum" label="卡号数量">
                  <Input width="130" disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem name="storeNum" label="门店数量">
                  <Input width="130" disabled />
                </FormItem>
              </Col>
            </Row>
          </Form>
        </Card>
        <Card style={{ marginTop: '24px' }} title="详情" bordered>
          <ProTable
            rowKey="id"
            search={false}
            options={false}
            columns={columns}
            pagination={false}
            scroll={{ x: 2000 }}
            dataSource={list}
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
        </FooterToolbar>
      </Spin>
    </PageContainer>
  );
};

export default connect(({ creditgroup, loading }) => ({
  creditgroup,
  loading: loading.models.creditgroup,
}))(ShowDetails);
