import React, { useState, useRef } from 'react';
import { ExclamationCircleOutlined } from '@ant-design/icons';
import {
  message,
  Form,
  Button,
  Row,
  Col,
  Card,
  Input,
  Upload,
  Modal,
  InputNumber,
  Select,
  DatePicker,
} from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import ProTable from '@ant-design/pro-table';
import FooterToolbar from '@/components/FooterToolbar';
import EndDateTime from '@/components/EndDateTime';
import { addList, judgment } from '../service';
import Styles from '../index.less';

const { Option } = Select;
const FormItem = Form.Item;
const { confirm } = Modal;

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
      setLoading(false);
    }
    if (code === 200) {
      if (fileNew.length === 0) {
        setLoading(false);
        history.goBack();
        return;
      }
      Promise.all([getUpload(data.id)])
        .then((value) => {
          if (value[0] === 200) {
            setLoading(false);
            history.goBack();
          }
        })
        .catch(() => {
          message.error(msg);
          setLoading(false);
        });
    }
  };

  const showConfirm = () => {
    confirm({
      title: '提示信息',
      icon: <ExclamationCircleOutlined />,
      content: '存在重复的公司编码、客户编码以及保单号，是否继续提交？',
      onOk() {
        handleOk();
      },
    });
  };

  const ifSameValue = async () => {
    const getFormVal = await form.validateFields();
    const { compCode, custCode, policyno, status } = getFormVal;
    const param = {
      compCode,
      custCode,
      policyno,
      status,
    };
    const response = await judgment(param);
    const { code, data } = response;
    if (code === 200 && data === '1') {
      handleOk();
    }
    if (code === 200 && data === '0') {
      showConfirm();
    }
  };

  return (
    <PageContainer ghost title={false}>
      <Card title="基本信息">
        <Form form={form} layout="vertical">
          <Row gutter={64}>
            <Col span={8}>
              <FormItem
                label="申请单号"
                name="applicationNo"
                rules={[
                  {
                    required: true,
                    message: '申请单号不能为空！',
                  },
                ]}
              >
                <Input placeholder="" />
              </FormItem>
            </Col>
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
                <Input placeholder="" />
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
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="申请账期"
                name="applyPayterm"
                rules={[
                  {
                    required: true,
                    message: '申请账期不能为空！',
                  },
                ]}
              >
                <InputNumber placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="申请额度"
                name="applyLimit"
                rules={[
                  {
                    required: true,
                    message: '申请额度不能为空！',
                  },
                ]}
              >
                <InputNumber placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="生效时间"
                name="validFrom"
                rules={[
                  {
                    required: true,
                    message: '生效时间不能为空！',
                  },
                ]}
              >
                <DatePicker />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="到期时间"
                name="validTo"
                rules={[
                  {
                    required: true,
                    message: '到期时间不能为空！',
                  },
                ]}
              >
                <EndDateTime />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="申请单状态"
                name="approveStatus"
                rules={[
                  {
                    required: true,
                    message: '申请单状态不能为空！',
                  },
                ]}
              >
                <Select>
                  <Option value="0">无效</Option>
                  <Option value="1">有效</Option>
                </Select>
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="申请人"
                name="applicant"
                rules={[
                  {
                    required: true,
                    message: '申请人不能为空！',
                  },
                ]}
              >
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="申请时间"
                name="applyTime"
                rules={[
                  {
                    required: true,
                    message: '申请时间不能为空！',
                  },
                ]}
              >
                <DatePicker />
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
        <Button type="primary" loading={loading} onClick={ifSameValue}>
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
