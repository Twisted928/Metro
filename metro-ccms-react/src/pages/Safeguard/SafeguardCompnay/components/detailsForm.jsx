import React, { useState, useEffect, useRef, Fragment } from 'react';
import { Button, Card, Form, Row, Col, Input, DatePicker, Radio, message, Modal, Spin } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import moment from 'moment';
import numeral from 'numeral';
import ProTable from '@ant-design/pro-table';
import FooterToolbar from '@/components/FooterToolbar';
import { details } from '../service';
import Styles from '../index.less';

const FormItem = Form.Item;
const dateFormat = 'YYYY-MM-DD';
const currentDate = moment().format(dateFormat);

const SafeguardForm = ({
  dispatch,
  loadingdetails,
  loadingboundary,
  safeguardCompnay: { boundaryData },
}) => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [policyList, setpolicyList] = useState([]);
  const [boundaryVis, setBoundaryVis] = useState(false);
  const [pageLoading, setPageLoading] = useState(false);

  const query = async () => {
    const { id } = history.location.query;
    if (!id) {
      return;
    }
    setPageLoading(true);
    const response = await details({ id });
    const { code, msg, data } = response;
    if (code === 200) {
      const { insurePolicyVOList, validFrom, validTo } = data;
      form.setFieldsValue({
        ...data,
        validFrom: validFrom ? moment(validFrom) : '',
        validTo: validTo ? moment(validTo) : '',
      });
      setpolicyList(insurePolicyVOList);
    } else {
      message.error(msg);
    }
    setPageLoading(false);
  };

  const queryBoundary = (param = {}) => {
    dispatch({
      type: 'safeguardCompnay/boundary',
      payload: param,
    });
  };

  useEffect(() => {
    query();
  }, []);

  // 查看保单范围
  const checkBoundary = (res) => {
    setBoundaryVis(true);
    const params = {
      policId: res.id,
      storeCodeList: res.insureScopeDOList,
    };
    queryBoundary(params);
  };

  const columns = [
    {
      title: '保单号',
      dataIndex: 'policyno',
      ellipsis: true,
      width: 150,
      fixed: 'left',
    },
    {
      title: '保单主体',
      ellipsis: true,
      dataIndex: 'body',
      width: 100,
      fixed: 'left',
    },
    {
      title: '约定投保金额（万元）',
      dataIndex: 'policySum',
      ellipsis: true,
      width: 180,
      align: 'right',
      render: (_, record) => numeral(record.policySum).format('0,0.00'),
    },
    {
      title: '最高赔偿限额（万元）',
      dataIndex: 'maxpaySum',
      width: 180,
      align: 'right',
      render: (val) => numeral(val).format('0,0.00'),
    },
    {
      title: '赔偿比例（%）',
      dataIndex: 'payLv',
      align: 'right',
      width: 120,
    },
    {
      title: '最长付款期限（天）',
      dataIndex: 'payperiod',
      width: 180,
      align: 'right',
    },
    {
      title: '限额闲置期（天）',
      dataIndex: 'quotafree',
      width: 150,
      align: 'right',
    },
    {
      title: '赔款等待期（天）',
      dataIndex: 'paywait',
      width: 150,
      align: 'right',
    },
    {
      title: '币种',
      dataIndex: 'currency',
      width: 100,
    },
    {
      title: '生效时间',
      dataIndex: 'validFrom',
      width: 130,
      render: (text) => {
        return moment(text).format(dateFormat);
      },
    },
    {
      title: '到期时间',
      dataIndex: 'validTo',
      width: 130,
      render: (text) => {
        return moment(text).format(dateFormat);
      },
    },
    {
      title: '创建人',
      dataIndex: 'createdBy',
      width: 130,
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      width: 130,
      render: (text) => {
        return moment(text).format(dateFormat);
      },
    },
    {
      title: '操作',
      dataIndex: 'action',
      width: 130,
      fixed: 'right',
      render: (_, record) => {
        return (
          <Fragment>
            <a onClick={() => checkBoundary(record)} target="_blank">
              查看保单范围
            </a>
          </Fragment>
        );
      },
    },
  ];

  const bouColumns = [
    {
      title: '部门编码',
      dataIndex: 'storeCode',
      key: 'storeCode',
      width: 200,
    },
    {
      title: '部门名称',
      dataIndex: 'compName',
      key: 'compName',
      width: 200,
    },
  ];

  return (
    <PageContainer ghost title={false}>
      <Spin spinning={pageLoading}>
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
                  label="公司编码"
                  name="compCode"
                  rules={[
                    {
                      required: true,
                      message: '请输入公司编码！',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="公司名称"
                  name="compName"
                  rules={[
                    {
                      required: true,
                      message: '请输入公司名称！',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="生效时间"
                  name="validFrom"
                  rules={[
                    {
                      required: true,
                      message: '请选择生效时间！',
                    },
                  ]}
                >
                  <DatePicker disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="到期时间"
                  name="validTo"
                  rules={[
                    {
                      required: true,
                      message: '请选择到期时间！',
                    },
                  ]}
                >
                  <DatePicker disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="有效状态"
                  name="status"
                  rules={[
                    {
                      required: true,
                      message: '请选择保单状态！',
                    },
                  ]}
                >
                  <Radio.Group disabled>
                    <Radio value={1}>有效</Radio>
                    <Radio value={0}>无效</Radio>
                  </Radio.Group>
                </FormItem>
              </Col>
            </Row>
          </Form>
        </Card>
        <Card className={Styles.extraStyle} title="保单信息" style={{ marginTop: '24px' }}>
          <ProTable
            headerTitle=""
            rowKey="id"
            dataSource={policyList}
            search={false}
            actionRef={ref}
            options={false}
            loading={loadingdetails}
            toolBarRender={false}
            pagination={false}
            columns={columns}
            scroll={{ x: 2170 }}
          />
        </Card>
        <Modal
          title="保单范围"
          width={700}
          visible={boundaryVis}
          onCancel={() => {
            setBoundaryVis(false);
          }}
          footer={null}
        >
          <ProTable
            headerTitle=""
            rowKey="storeCode"
            bordered
            dataSource={boundaryData}
            search={false}
            options={false}
            loading={loadingboundary}
            toolBarRender={false}
            pagination={false}
            columns={bouColumns}
            scroll={{ y: 500 }}
          />
        </Modal>
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

export default connect(({ safeguardCompnay, loading }) => ({
  safeguardCompnay,
  loadingboundary: loading.effects['safeguardCompnay/boundary'],
}))(SafeguardForm);
