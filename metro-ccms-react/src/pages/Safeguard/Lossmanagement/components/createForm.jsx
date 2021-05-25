import React, { useState, useEffect, useRef } from 'react';
import {
  Button,
  Card,
  Form,
  Row,
  Col,
  Input,
  DatePicker,
  Modal,
  Select,
  message,
  InputNumber,
} from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import moment from 'moment';
import ProTable from '@ant-design/pro-table';
import UpdataFile from '@/components/UpdataFile';
import { promiseAll, deleteUploadList } from '@/services/commom';
import FooterToolbar from '@/components/FooterToolbar';
import { addList, getInsure } from '../service';
import Styles from '../index.less';

const FormItem = Form.Item;
const { Option } = Select;
const { TextArea } = Input;
const dateFormat = 'YYYY-MM-DD';
const currentDate = moment().format(dateFormat);

const SafeguardForm = () => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [formTz] = Form.useForm();
  const [tzVis, setTzVis] = useState(false);
  const [fileList, setFile] = useState([]);
  const [fileNew, setFileList] = useState([]);
  const [tzList, setTzList] = useState([]);
  const [submitLoading, setsubmitLoading] = useState(false);
  const [mainId, setMainID] = useState(null);

  const getBasicMsg = async () => {
    const { id } = history.location.query;
    if (id) {
      const res = await getInsure({ id });
      const { code, data, msg } = res;
      setMainID(id);
      if (code === 200) {
        form.setFieldsValue({
          ...data,
          invoicedate: data.invoicedate ? moment(data.invoicedate) : '',
        });
      } else {
        message.error(msg);
      }
    }
  };

  useEffect(() => {
    getBasicMsg();
  }, []);

  const submitNewMsg = async () => {
    const getFormVal = await form.validateFields();
    setsubmitLoading(true);
    const { caseno, declaration, buyerno, custCode, custName, invoiceNo, invoicedate } = getFormVal;
    const params = {
      caseno,
      declaration,
      buyerno,
      custCode,
      custName,
      invoiceNo,
      invoicedate,
      claimProgressDOList: tzList,
      insureId: mainId,
    };
    const response = await addList(params);
    const { code, data, msg } = response;
    if (code === 500) {
      message.error(msg);
    }
    if (code === 200) {
      if (fileNew.length === 0) {
        setsubmitLoading(false);
        history.goBack();
        return;
      }
      promiseAll(data.id, 8, fileNew);
      history.goBack();
    }
    setsubmitLoading(false);
  };

  const deleteTz = (num) => {
    const list = tzList;
    const result = list.filter((item, index) => {
      return num !== index;
    });
    setTzList(result);
  };

  const getFormatMsg = async () => {
    const formValue = await formTz.validateFields();
    const newList = [];
    const { caseProgress, caseStatus } = formValue;
    const obj = {
      caseProgress,
      caseStatus,
    };
    newList.push(obj);
    setTzList([...tzList, ...newList]);
    setTzVis(false);
    formTz.resetFields();
  };

  const toolBarRender = (
    <Button
      key="add"
      type="primary"
      onClick={() => {
        setTzVis(true);
      }}
    >
      新增
    </Button>
  );

  const columns = [
    {
      title: '案件进展',
      dataIndex: 'caseProgress',
      key: 'caseProgress',
      width: 600,
      ellipsis: true,
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
      title: '操作',
      dataIndex: 'action',
      render: (_, record, index) => {
        return (
          <a
            onClick={() => {
              deleteTz(index);
            }}
          >
            删除
          </a>
        );
      },
    },
  ];

  const getNewListDel = (id) => {
    const newArr = fileNew;
    const newFileList = newArr.filter((item) => {
      return item.uid !== id;
    });
    return newFileList;
  }

  const updataFile = {
    dataSource: fileList,
    loadFun: (id) => {
      setFile(deleteUploadList(id, fileList));
      setFileList(getNewListDel(id));
    },
    changeFun: (file, fileUpList) => {
      setFileList([...fileNew, file]);
      setFile([...fileList, fileUpList]);
    },
  };

  return (
    <PageContainer ghost title={false}>
      <Card title="报损信息">
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
                label="客户编码"
                name="custCode"
                rules={[
                  {
                    required: false,
                    message: '请选择客户编码！',
                  },
                ]}
              >
                <Input disabled maxLength={32} placeholder="长度小于32位" />
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
                <Input disabled maxLength={32} placeholder="长度小于32位" />
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
                <Input disabled maxLength={32} placeholder="长度小于32位" />
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
      <Card
        className={Styles.extraStyle}
        title="台账信息"
        extra={toolBarRender}
        style={{ marginTop: '24px' }}
      >
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
      <UpdataFile {...updataFile} />
      <FooterToolbar>
        <Button
          key="back"
          onClick={() => {
            history.goBack();
          }}
        >
          返回
        </Button>
        <Button loading={submitLoading} type="primary" onClick={() => submitNewMsg()}>
          提交
        </Button>
      </FooterToolbar>
      <Modal
        visible={tzVis}
        title="台账维护"
        width={500}
        onOk={() => {
          getFormatMsg();
        }}
        onCancel={() => {
          setTzVis(false);
        }}
      >
        <Form form={formTz}>
          <FormItem
            label="案件状态"
            name="caseStatus"
            rules={[
              {
                required: true,
                message: '请选择案件状态',
              },
            ]}
          >
            <Select allowClear>
              <Option value="1">自追</Option>
              <Option value="2">委托</Option>
              <Option value="3">关闭</Option>
            </Select>
          </FormItem>
          <FormItem
            label="案件进展"
            name="caseProgress"
            rules={[
              {
                required: true,
                message: '请输入案件进展',
              },
            ]}
          >
            <TextArea rows={2} maxLength={128} placeholder="长度小于128位" />
          </FormItem>
        </Form>
      </Modal>
    </PageContainer>
  );
};

export default connect(({ lossmanagement, loading }) => ({
  lossmanagement,
  loading: loading.effects['lossmanagement/query'],
}))(SafeguardForm);
