import React, { useState, useEffect, useRef } from 'react';
import { Button, Card, Form, Row, Col, Input, DatePicker, InputNumber, message, Spin } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import moment from 'moment';
import ProTable from '@ant-design/pro-table';
import { downloadFile } from '@/services/commom';
import FooterToolbar from '@/components/FooterToolbar';
import { getDetailList } from '../service';
import Styles from '../index.less';

const FormItem = Form.Item;

const SafeguardForm = () => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [pageLoading, setPageLoading] = useState(false);
  const [fileList, setFile] = useState([]);
  const [tzList, setTzList] = useState([]);

  // 初始化数据
  const getBasicMsg = async () => {
    const { id, caseNo } = history.location.query;
    if (!id) {
      message.info('暂无数据！');
      return;
    }
    const param = {
      id,
      caseno: caseNo,
    };
    setPageLoading(true);
    const response = await getDetailList(param);
    const { code, data } = response;
    if (code === 200) {
      const { invoicedate, claimProgressDOList, sysBasicFileList } = data;
      form.setFieldsValue({
        ...data,
        invoicedate: invoicedate ? moment(invoicedate) : '',
      });
      setTzList(claimProgressDOList);
      setFile(sysBasicFileList);
      setPageLoading(false);
    }
  };

  useEffect(() => {
    getBasicMsg();
  }, []);

  // 下载
  const download = async (record) => {
    const { attachmentName, attachmentUrl } = record;
    const params = {
      fileName: attachmentName,
      filePath: attachmentUrl,
    };
    await downloadFile('/file/downAndUpload/downloadFile', params);
  };

  const columns = [
    {
      title: '案件进展',
      dataIndex: 'caseProgress',
      key: 'caseProgress',
    },
    {
      title: '案件状态',
      dataIndex: 'caseStatus',
      key: 'caseStatus',
      valueEnum: {
        1: {
          text: '自追',
        },
        2: {
          text: '委托',
        },
        3: {
          text: '关闭',
        },
      },
    },
    {
      title: '创建人',
      dataIndex: 'createdBy',
      key: 'createdBy',
    },
    {
      title: '创建时间',
      valueType: 'date',
      dataIndex: 'createTime',
      key: 'createTime',
    },
  ];

  const columnsUpload = [
    // {
    //   title: '附件信息',
    //   dataIndex: 'attachitems',
    // },
    {
      title: '附件名称',
      dataIndex: 'attachmentName',
    },
    // {
    //   title: '附件地址',
    //   dataIndex: 'attachmentUrl',
    // },
    {
      title: '操作',
      dataIndex: 'action',
      render: (_, record) => {
        return (
          <a
            onClick={() => {
              download(record);
            }}
          >
            下载
          </a>
        );
      },
    },
  ];

  return (
    <PageContainer ghost title={false}>
      <Spin spinning={pageLoading}>
        <Card title="报损信息">
          <Form form={form} layout="vertical">
            <Row gutter={64}>
              <Col span={8}>
                <FormItem
                  label="案件编号"
                  name="caseno"
                  rules={[
                    {
                      required: false,
                      message: '请输入案件编号！',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="客户编码"
                  name="custCode"
                  rules={[
                    {
                      required: false,
                      message: '请选择客户编码！',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="客户名称"
                  name="custName"
                  rules={[
                    {
                      required: false,
                      message: '请输入客户名称！',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
              <FormItem
                label="保险公司编码"
                name="compCode"
                rules={[
                  {
                    required: false,
                    message: '保险公司编码不能为空！',
                  },
                ]}
              >
                <Input disabled placeholder="" />
              </FormItem>
            </Col>
              <Col span={8}>
                <FormItem
                  label="汇总发票号"
                  name="invoiceNo"
                  rules={[
                    {
                      required: false,
                      message: '请输入汇总发票号！',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="买方代码"
                  name="buyerno"
                  rules={[
                    {
                      required: false,
                      message: '请输入买方代码！',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="汇总发票日期"
                  name="invoicedate"
                  rules={[
                    {
                      required: false,
                      message: '请选择出运日期！',
                    },
                  ]}
                >
                  <DatePicker disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="汇总发票金额（元）"
                  name="invoicesum"
                  rules={[
                    {
                      required: false,
                      message: '汇总发票金额不能为空！',
                    },
                  ]}
                >
                  <InputNumber disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="投保金额（元）"
                  name="insuresum"
                  rules={[
                    {
                      required: false,
                      message: '投保金额不能为空！',
                    },
                  ]}
                >
                  <InputNumber disabled />
                </FormItem>
              </Col>
            </Row>
          </Form>
        </Card>
        <Card className={Styles.extraStyle} title="台账信息" style={{ marginTop: '24px' }}>
          <ProTable
            headerTitle=""
            rowKey="id"
            dataSource={tzList}
            search={false}
            actionRef={ref}
            options={false}
            toolBarRender={false}
            pagination={false}
            columns={columns}
          />
        </Card>
        <Card className={Styles.extraStyle} title="附件信息" style={{ marginTop: '24px' }}>
          <ProTable
            headerTitle=""
            rowKey="id"
            dataSource={fileList}
            search={false}
            actionRef={ref}
            options={false}
            toolBarRender={false}
            pagination={false}
            columns={columnsUpload}
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

export default connect(({ lossmanagement, loading }) => ({
  lossmanagement,
  loading: loading.effects['lossmanagement/query'],
}))(SafeguardForm);
