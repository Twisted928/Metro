/* eslint-disable react-hooks/exhaustive-deps */
import React, { useState, useEffect } from 'react';
import { ExclamationCircleOutlined } from '@ant-design/icons';
import {
  message,
  Form,
  Button,
  Row,
  Col,
  Card,
  Input,
  InputNumber,
  Radio,
  Modal,
  Spin,
  Select,
} from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import FooterToolbar from '@/components/FooterToolbar';
import { deleteFile, promiseAll, deleteUploadList, abcAndNumber } from '@/services/commom';
import UpdataFile from '@/components/UpdataFile';
import { updataList, getFile, judgment } from '../service';

const { Option } = Select;
const FormItem = Form.Item;
const { confirm } = Modal;

const CreateModal = ({ dispatch, companies: { listPolicy } }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [loadingPage, setLoadingPage] = useState(false);
  const [CcxRank, setCcxRank] = useState([]);
  const [fileList, setFile] = useState([]); // 已存在的附件
  const [fileNew, setFileList] = useState([]); // 新增的附件
  const [fileDelList, setFileDelList] = useState([]); // 删除的附件
  const [mainId, setMainId] = useState(null);

  const query = async () => {
    setLoadingPage(true);
    const { id } = history.location.query;
    const response = await getFile({ id });
    const { code, data, msg } = response;
    if (code === 200) {
      setFile(data.sysBasicFileList);
      setMainId(id);
      form.setFieldsValue(data);
      setLoadingPage(false);
    } else {
      message.error(msg);
      setLoadingPage(false);
    }
  };

  const getlistPolicy = () => {
    dispatch({
      type: 'companies/listPolicy',
      payload: {},
    });
  };

  // 查询级别
  const getCcxRank = () => {
    dispatch({
      type: 'commonmodel/basciData',
      payload: { ctype: 'CcxRank' },
    }).then((res) => {
      const { code, data } = res;
      if (code === 200) {
        setCcxRank(data);
      }
    });
  };

  useEffect(() => {
    query();
    getlistPolicy();
    getCcxRank();
  }, []);

  const handleOk = async () => {
    const getFormVal = await form.validateFields();
    setLoading(true);
    const params = {
      ...getFormVal,
      id: mainId,
      type: 1,
    };
    const response = await updataList(params);
    const { code, msg } = response;
    if (code === 500) {
      message.error(msg);
    }
    if (code === 200) {
      if (!fileNew.length && !fileDelList.length) {
        setLoading(false);
        history.goBack();
        return;
      }
      if (fileDelList.length && fileNew.length) {
        deleteFile(fileDelList);
        promiseAll(mainId, 7, fileNew);
        history.goBack();
        return;
      }
      if (fileNew.length && !fileDelList.length) {
        promiseAll(mainId, 7, fileNew);
        history.goBack();
        return;
      }
      if (fileDelList.length && !fileNew.length) {
        deleteFile(fileDelList);
        history.goBack();
        return;
      }
    }
    setLoading(false);
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
      ifUpdata: 1,
      id: mainId,
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

  const selectPolicy = (value) => {
    const newList = listPolicy;
    newList.forEach((item) => {
      if (item.policyno === value) {
        form.setFieldsValue({
          compCode: item.compCode,
          compName: item.compName,
        });
      }
    });
  };

  const getNewListDel = (id) => {
    const newArr = fileNew;
    const newFileList = newArr.filter((item) => {
      return item.uid !== id;
    });
    return newFileList;
  };

  const updataFile = {
    downloadBtu: true,
    dataSource: fileList,
    loadFun: (id, obj) => {
      setFile(deleteUploadList(id, fileList));
      setFileDelList([...fileDelList, obj]);
      setFileList(getNewListDel(id));
    },
    changeFun: (file, fileUpList) => {
      setFileList([...fileNew, file]);
      setFile([...fileList, fileUpList]);
    },
    mainId,
    type: 7,
  };

  return (
    <PageContainer ghost title={false}>
      <Spin spinning={loadingPage}>
        <Card title="基本信息">
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
                  label="保单号"
                  name="policyno"
                  rules={[
                    {
                      required: true,
                      message: '请输入保单号！',
                    },
                  ]}
                >
                  <Select onSelect={selectPolicy}>
                    {(listPolicy || []).map((item) => {
                      return (
                        <Option key={item.id} value={item.policyno}>
                          {item.policyno}
                        </Option>
                      );
                    })}
                  </Select>
                </FormItem>
              </Col>
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
                  <Input disabled placeholder="长度小于15位" maxLength={15} />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="公司名称"
                  name="compName"
                  rules={[
                    {
                      required: true,
                      message: '请输入公司名称',
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
                      required: true,
                      message: '请输入买方代码',
                    },
                    {
                      validator: abcAndNumber,
                    },
                  ]}
                >
                  <Input maxLength={32} placeholder="长度最大为32位" />
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
                  <Input disabled placeholder="长度小于32位" maxLength={32} />
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
                  label="保险评级"
                  name="creditLevel"
                  rules={[
                    {
                      required: false,
                      message: '请输入保险评级！',
                    },
                  ]}
                >
                  <Select>
                    {(CcxRank || []).map((item) => {
                      return (
                        <Option key={item.ctype} value={item.ctype}>
                          {item.ctype}
                        </Option>
                      );
                    })}
                  </Select>
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="保险额度（元）"
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
                  <InputNumber min={0} placeholder="长度小于18位" maxLength={18} />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="保险账期（天）"
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
                  <InputNumber min={0} placeholder="长度小于10位" maxLength={10} />
                </FormItem>
              </Col>
              {/* <Col span={8}>
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
              </Col> */}
            </Row>
          </Form>
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
          <Button type="primary" loading={loading} onClick={ifSameValue}>
            提交
          </Button>
        </FooterToolbar>
      </Spin>
    </PageContainer>
  );
};

export default connect(({ companies, commonmodel, loading }) => ({
  companies,
  commonmodel,
  loadingFile: loading.effects['companies/getFile'],
}))(CreateModal);
