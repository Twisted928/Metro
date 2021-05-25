/* eslint-disable @typescript-eslint/no-unused-vars */
import React, { useState, useRef, Fragment } from 'react';
import {
  Button,
  Divider,
  Card,
  Form,
  Row,
  Col,
  Input,
  DatePicker,
  Upload,
  message,
  Select,
} from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history } from 'umi';
import moment from 'moment';
import ProTable from '@ant-design/pro-table';
import FooterToolbar from '@/components/FooterToolbar';
import Styles from '../index.less';

const FormItem = Form.Item;
const dateFormat = 'YYYY-MM-DD';
const { Option } = Select;
const currentDate = moment().format(dateFormat);

const SafeguardForm = () => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [fileList, setFile] = useState([]);
  const [tzList, setTzList] = useState([]);
  const [fileNew, setFileList] = useState([]);

  const onFinish = () => {};
  const onFinishFailed = () => {};

  const deleteFilled = (data) => {
    const newArr = fileList;
    const { id } = data;
    const result = newArr.filter((item) => {
      return item.id !== id;
    });
    setFile(result);
  };

  const props = {
    name: 'file',
    multiple: true,
    showUploadList: false,
    beforeUpload: (file) => {
      const fileUpList = {
        id: file.uid,
        attachmentName: file.name,
      };
      setFileList([...fileNew, file]);
      setFile([...fileList, fileUpList]);
      return false;
    },
  };

  const toolBarUploadRender = (
    <Upload {...props}>
      <Button type="primary">附件上传</Button>
    </Upload>
  );

  const columns = [
    {
      title: '客户编码',
      dataIndex: 'custCode',
      key: 'custCode',
    },
    {
      title: '客户名称',
      dataIndex: 'custName',
      key: 'custName',
    },
    {
      title: '原门店',
      dataIndex: 'storeNameOld',
      key: 'storeNameOld',
      search: false,
    },
    {
      title: '新门店',
      dataIndex: 'storeNameN',
      key: 'storeCode',
      search: false,
    },
    {
      title: '原卡号',
      dataIndex: 'cardCodeOld',
      key: 'storeCode',
      search: false,
    },
    {
      title: '新卡号',
      dataIndex: 'cardCodeN',
      key: 'storeCode',
      search: false,
    },
    // {
    //   title: '操作',
    //   dataIndex: 'action',
    //   key: 'action',
    //   search: false,
    //   render: (_, record) => {
    //     return (
    //       <a
    //         onClick={() => {
    //           chooseCus(record);
    //         }}
    //       >
    //         选择
    //       </a>
    //     );
    //   },
    // },
  ];

  const columnsUpload = [
    {
      title: '附件名称',
      dataIndex: 'attachmentName',
    },
    {
      title: '操作',
      dataIndex: 'action',
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                deleteFilled(record);
              }}
            >
              删除
            </a>
          </Fragment>
        );
      },
    },
  ];

  return (
    <PageContainer>
      <Card title="基本信息">
        <Form
          form={form}
          layout="vertical"
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
          initialValues={{
            status: 1,
            validFrom: moment(currentDate),
          }}
        >
          <Row gutter={64}>
            <Col span={8}>
              <FormItem
                label="申请单号"
                name="applicationNo"
                rules={[
                  {
                    required: true,
                    message: '请输入申请单号',
                  },
                ]}
              >
                <Input placeholder="请输入申请单号" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="老门店编码"
                name="storeCodeOld"
                rules={[
                  {
                    required: true,
                    message: '请输入老门店编码',
                  },
                ]}
              >
                <Input placeholder="请输入老门店编码" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="额度转移说明"
                name="reason"
                rules={[
                  {
                    required: true,
                    message: '请输入额度转移说明',
                  },
                ]}
              >
                <Input placeholder="请输入额度转移说明" />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
      <Card className={Styles.extraStyle} title="客户信息" style={{ marginTop: '24px' }}>
        <ProTable
          headerTitle=""
          rowKey="id"
          bordered
          dataSource={tzList}
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
        extra={toolBarUploadRender}
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
        <Button type="primary" htmlType="submit">
          提交
        </Button>
      </FooterToolbar>
    </PageContainer>
  );
};

export default SafeguardForm;
