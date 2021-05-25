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
      title: '部门名称',
      dataIndex: 'departName',
      key: 'departName',
    },
    {
      title: '门店编码',
      dataIndex: 'storeCode',
      key: 'storeCode',
    },
    {
      title: '额度类型',
      dataIndex: 'limitType',
      key: 'limitType',
    },
    {
      title: '原额度',
      dataIndex: 'limit',
      key: 'limit',
    },
    {
      title: '复核后额度',
      dataIndex: 'fe',
      key: 'fe',
      // render: (text, record) => {
      //   const editable = isEditing(record);
      //   if (editable) {
      //     return (
      //       <Input
      //         defaultValue={text}
      //         onChange={(e) => insureSumChange(e, 'fe', record)}
      //         style={{ width: '100%' }}
      //       />
      //     );
      //   }
      //   return <a onClick={(e) => toggleEditable(e, record.id)}>{text}</a>;
      // },
    },
    {
      title: '复合后账期',
      dataIndex: 'fhe',
      key: 'fhe',
    },
    {
      title: '原账期',
      dataIndex: 'paymentTerm',
      key: 'paymentTerm',
    },
    {
      title: '生效日',
      dataIndex: 'validForm',
      key: 'validForm',
    },
    {
      title: '生效日',
      dataIndex: 'validTo',
      key: 'validTo',
    },
    {
      title: '是否冻结',
      dataIndex: 'ifdj',
      key: 'ifdj',
    },
    {
      title: '操作',
      dataIndex: 'action',
      // render: (_, record) => {
      //   const editable = isEditing(record);
      //   if (editable) {
      //     return (
      //       <Fragment>
      //         <a
      //           onClick={() => {
      //             saveRow(record);
      //           }}
      //         >
      //           保存
      //         </a>
      //         <Divider type="vertical" />
      //         <a
      //           onClick={() => {
      //             cancelRow();
      //           }}
      //         >
      //           取消
      //         </a>
      //       </Fragment>
      //     );
      //   }
      // },
    },
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
                label="复审类型"
                name="ctype"
                rules={[
                  {
                    required: true,
                    message: '请选择复审类型',
                  },
                ]}
              >
                <Input placeholder="请输入被保险人声明" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="申请人"
                name="applicant"
                rules={[
                  {
                    required: true,
                    message: '请输入申请人',
                  },
                ]}
              >
                <Input placeholder="请输入申请人" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="申请时间"
                name="applyTime"
                rules={[
                  {
                    required: true,
                    message: '请选择申请时间',
                  },
                ]}
              >
                <DatePicker />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="单据状态"
                name="approveStatus"
                rules={[
                  {
                    required: true,
                    message: '请选择单据状态',
                  },
                ]}
              >
                <Select placeholder="请选择单据状态">
                  <Option value="0">审批中</Option>
                  <Option value="1">审批通过</Option>
                  <Option value="2">审批撤销</Option>
                  <Option value="3">驳回</Option>
                </Select>
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
