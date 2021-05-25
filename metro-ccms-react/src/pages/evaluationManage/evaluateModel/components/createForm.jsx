import React, { useState, useRef } from 'react';
import { message, Form, Button, Row, Col, Card, Input, Upload, InputNumber, Radio } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import ProTable from '@ant-design/pro-table';
import FooterToolbar from '@/components/FooterToolbar';
import { addList } from '../service';
import Styles from '../index.less';

const FormItem = Form.Item;

const CreateModal = ({ dispatch }) => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [fileList, setFile] = useState([]);
  const [fileNew, setFileList] = useState([]);

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
              deleteFilled(record);
            }}
          >
            删除
          </a>
        );
      },
    },
  ];

  // 使用Promise解决多个并列方法重复执行某个条件(后期封装公共方法)

  const getUpload = (id) => {
    const uploadFile = fileNew;
    return new Promise((resolve) => {
      uploadFile.forEach((item) => {
        const paramUpload = {
          id,
          type: 7,
          file: item,
        };
        dispatch({
          type: 'companies/upload',
          payload: paramUpload,
        }).then((response) => {
          resolve(response.code);
        });
      });
    });
  };

  const handleOk = async () => {
    const getFormVal = await form.validateFields();
    setLoading(true);
    const {
      compCode,
      compName,
      buyerno,
      custCode,
      custName,
      policyno,
      creditLevel,
      status,
      quota,
      quotaDays,
    } = getFormVal;
    const params = {
      compCode,
      compName,
      buyerno,
      custCode,
      custName,
      policyno,
      creditLevel,
      status,
      quota,
      quotaDays,
    };
    const response = await addList(params);
    const { code, data, msg } = response;
    if (code === 500) {
      message.error(msg);
    }
    if (code === 200) {
      if (fileNew.length === 0) {
        message.success(msg);
        history.goBack();
        return;
      }
      Promise.all([getUpload(data.id)])
        .then((value) => {
          if (value[0] === 200) {
            message.success(msg);
            history.goBack();
          }
        })
        .catch(() => {
          message.error(msg);
        });
    }
    setLoading(false);
  };

  return (
    <PageContainer>
      <Card title="保单信息">
        <Form
          form={form}
          layout="vertical"
          initialValues={{
            status: 1,
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
                <Input placeholder="" />
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
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="买方代码"
                name="buyerno"
                rules={[
                  {
                    required: true,
                    message: '请输入买方代码！',
                  },
                ]}
              >
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="客户编码"
                name="custCode"
                rules={[
                  {
                    required: true,
                    message: '请输入客户编码！',
                  },
                ]}
              >
                <Input placeholder="" />
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
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="保单号"
                name="policyno"
                rules={[
                  {
                    required: true,
                    message: '请输入保单号！',
                  },
                ]}
              >
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="保险评级"
                name="creditLevel"
                rules={[
                  {
                    required: true,
                    message: '请输入保险评级！',
                  },
                ]}
              >
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="保险额度"
                name="quota"
                rules={[
                  {
                    required: false,
                    message: '请输入保险额度！',
                  },
                  {
                    validator: (_, value) =>
                      value < 0
                        ? Promise.reject(new Error('保险额度不能为负数！'))
                        : Promise.resolve(),
                  },
                ]}
              >
                <InputNumber min={0} placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="保险账期"
                name="quotaDays"
                rules={[
                  {
                    required: false,
                    message: '请输入保险账期！',
                  },
                  {
                    validator: (_, value) =>
                      value < 0
                        ? Promise.reject(new Error('保险账期不能为负数！'))
                        : Promise.resolve(),
                  },
                ]}
              >
                <InputNumber min={0} placeholder="" />
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
                <Radio.Group>
                  <Radio value={1}>有效</Radio>
                  <Radio value={0}>无效</Radio>
                </Radio.Group>
              </FormItem>
            </Col>
          </Row>
        </Form>
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
        <Button type="primary" loading={loading} onClick={handleOk}>
          提交
        </Button>
      </FooterToolbar>
    </PageContainer>
  );
};

export default connect(({ companies, loading }) => ({
  companies,
  loading: loading.effects['companies/upload'],
}))(CreateModal);
