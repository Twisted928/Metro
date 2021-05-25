import React, { useState, useRef, Fragment, useEffect } from 'react';
import {
  Button,
  Card,
  Form,
  Row,
  Col,
  Input,
  DatePicker,
  InputNumber,
  message,
  Select,
  Spin,
} from 'antd';
import moment from 'moment';
import EndDateTime from '@/components/EndDateTime';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import ProTable from '@ant-design/pro-table';
import FooterToolbar from '@/components/FooterToolbar';
import { downloadFile } from '@/services/commom';
import { detailsMsg } from '../service';
import Styles from '../index.less';

// const { Search } = Input;
const { Option } = Select;
const FormItem = Form.Item;

const SafeguardForm = ({
  dispatch,
  commonmodel: { basciData },
}) => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [fileList, setFile] = useState([]);
  const [newcardList, setNewcardList] = useState([]);
  const [loadingPage, setLoadingPage] = useState(false);

  // const getCusList = (params = {}) => {
  //   dispatch({
  //     type: 'commonmodel/customerlList',
  //     payload: params,
  //   });
  // };

  const getDetailsMsg = async () => {
    const { id } = history.location.query;
    if (id) {
      setLoadingPage(true);
      const response = await detailsMsg({ id });
      const { code, data, msg } = response;
      if (code === 200) {
        form.setFieldsValue({
          ...data,
          validFrom: moment(data.validFrom),
          validTo: moment(data.validTo),
        });
        setFile(data.list);
        setNewcardList(data.guaranteeScopeDOList);
        setLoadingPage(false);
      }
      if (code === 500) {
        message.error(msg);
        setLoadingPage(false);
      }
    }
    return false;
  };

  const getMemList = (params = {}) => {
    dispatch({
      type: 'commonmodel/memberlList',
      payload: params,
    });
  };

  const getGuarantList = () => {
    dispatch({
      type: 'commonmodel/basciData',
      payload: { ctype: 'GuaranteeType' },
    });
  };

  useEffect(() => {
    // getCusList();
    getDetailsMsg();
    getMemList();
    getGuarantList();
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
      title: '门店编码',
      dataIndex: 'storeCode',
      key: 'storeCode',
    },
    {
      title: '卡号编码',
      dataIndex: 'cardCode',
      key: 'cardCode',
    },
    {
      title: '卡号名称',
      dataIndex: 'cardName',
      key: 'cardName',
    },
    {
      title: '客户编码',
      dataIndex: 'custCode',
      key: 'cardName',
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      key: 'cardName',
    },
  ];

  // 附件
  const columnsUpload = [
    {
      title: '附件名称',
      dataIndex: 'attachmentName',
      key: 'attachmentName',
    },
    {
      title: '操作',
      dataIndex: 'action',
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                download(record);
              }}
            >
              下载
            </a>
          </Fragment>
        );
      },
    },
  ];

  return (
    <PageContainer ghost title={false}>
      <Spin spinning={loadingPage}>
        <Card title="基本信息">
          <Form form={form} layout="vertical">
            <Row gutter={64}>
              <Col span={8}>
                <FormItem
                  label="担保函编码"
                  name="gtcode"
                  rules={[
                    {
                      required: true,
                      message: '请输入担保函编码！',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="担保函类型"
                  name="guaranteetype"
                  rules={[
                    {
                      required: true,
                      message: '请输入担保函类型！',
                    },
                  ]}
                >
                  <Select disabled allowClear>
                    {(basciData || []).map((item) => {
                      return (
                        <Option key={`${item.ctype}`} value={`${item.ctype}`}>
                          {item.description}
                        </Option>
                      );
                    })}
                  </Select>
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="客户编码"
                  name="custCode"
                  rules={[
                    {
                      required: true,
                      message: '请选择客户编码！',
                    },
                  ]}
                >
                  {/* <Search readOnly placeholder="请输入客户编码" onSearch={onSearch} enterButton /> */}
                  <Input disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="客户名称"
                  name="custName"
                  rules={[
                    {
                      required: true,
                      message: '请输入客户名称！',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="担保金额（元）"
                  name="gtsum"
                  rules={[
                    {
                      required: true,
                      message: '请输入担保金额！',
                    },
                    {
                      validator: (_, value) =>
                        value < 0
                          ? Promise.reject(new Error('担保金额不能为负数！'))
                          : Promise.resolve(),
                    },
                  ]}
                >
                  <InputNumber disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="生效日期"
                  name="validFrom"
                  rules={[
                    {
                      required: true,
                      message: '请选择生效日期！',
                    },
                  ]}
                >
                  <DatePicker disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="到期日期"
                  name="validTo"
                  rules={[
                    {
                      required: true,
                      message: '请选择到期日！',
                    },
                  ]}
                >
                  <EndDateTime disabled />
                </FormItem>
              </Col>
            </Row>
          </Form>
        </Card>
        <Card
          className={Styles.extraStyle}
          title="卡号信息"
          style={{ marginTop: '24px' }}
        >
          <ProTable
            headerTitle=""
            rowKey="id"
            bordered
            dataSource={newcardList}
            search={false}
            actionRef={ref}
            options={false}
            toolBarRender={false}
            pagination={false}
            columns={columns}
          />
        </Card>
        <Card
          className={Styles.extraStyle}
          title="附件信息"
          style={{ marginTop: '24px' }}
        >
          <ProTable
            headerTitle=""
            rowKey="id"
            bordered
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

export default connect(({ commonmodel, loading }) => ({
  commonmodel,
  loadingCus: loading.effects['commonmodel/customerlList'],
  loadingMem: loading.effects['commonmodel/memberlList'],
}))(SafeguardForm);
