import React, { useEffect, useState } from 'react';
import { ExclamationCircleOutlined } from '@ant-design/icons';
import {
  message,
  Form,
  Button,
  Row,
  Col,
  Card,
  Input,
  Modal,
  InputNumber,
  Select,
  // Radio,
} from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import FooterToolbar from '@/components/FooterToolbar';
import UpdataFile from '@/components/UpdataFile';
import ChooseCustomer from '@/components/CustomerList';
import CompanyList from '@/components/CompanyList';
import { promiseAll, deleteUploadList, abcAndNumber } from '@/services/commom';
import { addList, judgment } from '../service';

const { Search } = Input;
const { Option } = Select;
const FormItem = Form.Item;
const { confirm } = Modal;

const CreateModal = ({ dispatch, companies: { listPolicy } }) => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [fileList, setFile] = useState([]);
  const [fileNew, setFileList] = useState([]);
  const [CcxRank, setCcxRank] = useState([]);
  const [cusVis, setCusVis] = useState(false);
  const [comVis, setComVis] = useState(false);

  const getlistPolicy = (param = {}) => {
    dispatch({
      type: 'companies/listPolicy',
      payload: param,
    });
  };
  // 查询保险公司
  const getCompany = () => {
    dispatch({
      type: 'commonmodel/basciData',
      payload: { ctype: 'InsureCompany' },
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
    getlistPolicy();
    getCompany();
    getCcxRank();
  }, []);

  const handleOk = async () => {
    const getFormVal = await form.validateFields();
    setLoading(true);
    const params = {
      ...getFormVal,
      type: 1,
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
      promiseAll(data.id, 7, fileNew);
      history.goBack();
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
    const { compCode, custCode, policyno } = getFormVal;
    const param = {
      compCode,
      custCode,
      policyno,
      status: 1,
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

  const onSearch = () => {
    setCusVis(true);
  };

  const onSearchCom = () => {
    setComVis(true);
  };

  const getNewListDel = (id) => {
    const newArr = fileNew;
    const newFileList = newArr.filter((item) => {
      return item.uid !== id;
    });
    return newFileList;
  };

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

  const chooseCustomer = {
    visible: cusVis,
    onCancel: (vis) => {
      setCusVis(vis);
    },
    getFormData: (data) => {
      form.setFieldsValue(data);
    },
  };

  const companyList = {
    visible: comVis,
    onCancel: (vis) => {
      setComVis(vis);
    },
    getFormData: (data) => {
      form.setFieldsValue({
        policyno: undefined,
      });
      form.setFieldsValue(data);
      getlistPolicy({ compCode: data.compCode });
    },
  };

  return (
    <PageContainer ghost title={false}>
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
                <Search
                  readOnly
                  placeholder="长度小于32位"
                  maxLength={32}
                  onSearch={onSearchCom}
                  enterButton
                />
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
                <Input readOnly placeholder="" />
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
                <Search
                  readOnly
                  placeholder="长度小于32位"
                  maxLength={32}
                  onSearch={onSearch}
                  enterButton
                />
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
                <Input readOnly placeholder="" />
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
          </Row>
        </Form>
      </Card>
      <UpdataFile {...updataFile} />
      <ChooseCustomer {...chooseCustomer} />
      <CompanyList {...companyList} />
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

export default connect(({ companies, commonmodel }) => ({
  companies,
  commonmodel,
}))(CreateModal);
