/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable consistent-return */
import React, { useState, useRef, useEffect } from 'react';
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
} from 'antd';
import moment from 'moment';
import EndDateTime from '@/components/EndDateTime';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import ProTable from '@ant-design/pro-table';
import FooterToolbar from '@/components/FooterToolbar';
import UpdataFile from '@/components/UpdataFile';
import { promiseAll, deleteUploadList } from '@/services/commom';
import ChooseCustomer from '@/components/CustomerList';
import CardList from '@/components/CardList';
import { aaddForm } from '../service';
import Styles from '../index.less';

const { Search } = Input;
const { Option } = Select;
const FormItem = Form.Item;
const dateFormat = 'YYYY-MM-DD';

const SafeguardForm = ({ dispatch, commonmodel: { basciData } }) => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [menVis, setMemVis] = useState(false);
  const [cusVis, setCusVis] = useState(false);
  const [fileList, setFile] = useState([]);
  const [fileNew, setFileList] = useState([]);
  const [newcardList, setNewcardList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [cardParams, setCardParams] = useState({});

  const getGuarantList = () => {
    dispatch({
      type: 'commonmodel/basciData',
      payload: { ctype: 'GuaranteeType' },
    });
  };

  useEffect(() => {
    getGuarantList();
  }, []);

  const sunmitFrom = async () => {
    const formValue = await form.validateFields();
    setLoading(true);
    const params = {
      ...formValue,
      validFrom: moment(formValue.validFrom).format(dateFormat),
      validTo: moment(formValue.validTo).format(dateFormat),
      guaranteeScopeDOList: newcardList,
    };
    const response = await aaddForm(params);
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
      promiseAll(data.id, 9, fileNew);
      history.goBack();
    }
    setLoading(false);
  };

  const onSearch = () => {
    setCusVis(true);
  };

  const addCustCode = async () => {
    const custCode = await form.validateFields(['custCode']);
    setCardParams(custCode);
    setMemVis(true);
  };

  const toolBarRender = (
    <Button
      key="add"
      type="primary"
      onClick={() => {
        addCustCode();
      }}
    >
      新增
    </Button>
  );

  const deleteMsg = async (res) => {
    const newList = newcardList;
    const newcardData = newList.filter((item) => item.id !== res.id);
    setNewcardList(newcardData);
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

  const deleteSameMsg = (arrOld, arrNew) => {
    const newData = arrNew;
    for (let i = 0; i < arrOld.length; i += 1) {
      for (let j = 0; j < newData.length; j += 1) {
        if (
          arrOld[i].storeCode + arrOld[i].cardCode ===
          newData[j].storeCode + newData[j].cardCode
        ) {
          message.info('已存在相同的卡号信息，请重新选择');
          return;
        }
      }
    }
    return newData;
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
    {
      title: '操作',
      dataIndex: 'action',
      render: (_, record) => {
        return <a onClick={() => deleteMsg(record)}>删除</a>;
      },
    },
  ];

  const chooseCustomer = {
    visible: cusVis,
    onCancel: (vis) => {
      setCusVis(vis);
    },
    getFormData: (data) => {
      form.setFieldsValue(data);
    },
  };

  const cardList = {
    visible: menVis,
    params: cardParams,
    onCancel: (vis) => {
      setMemVis(vis);
    },
    getCardList: (data) => {
      setNewcardList([...newcardList, ...deleteSameMsg(newcardList, data)]);
    },
  };

  return (
    <PageContainer ghost title={false}>
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
                <Input placeholder="长度小于32位" maxLength={32} />
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
                <Select allowClear>
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
                <InputNumber placeholder="长度小于16位" maxLength={16} />
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
                <DatePicker />
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
                <EndDateTime />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
      <Card
        className={Styles.extraStyle}
        title="卡号信息"
        extra={toolBarRender}
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
      <UpdataFile {...updataFile} />
      <ChooseCustomer {...chooseCustomer} />
      <CardList {...cardList} />
      <FooterToolbar>
        <Button
          key="back"
          onClick={() => {
            history.goBack();
          }}
        >
          返回
        </Button>
        <Button
          onClick={() => {
            sunmitFrom();
          }}
          loading={loading}
          type="primary"
        >
          提交
        </Button>
      </FooterToolbar>
    </PageContainer>
  );
};

export default connect(({ commonmodel }) => ({
  commonmodel,
}))(SafeguardForm);
