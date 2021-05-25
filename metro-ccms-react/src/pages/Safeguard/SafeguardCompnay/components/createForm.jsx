import React, { useState, useRef, Fragment } from 'react';
import { ExclamationCircleOutlined } from '@ant-design/icons';
import { Button, Divider, Card, Form, Row, Col, Input, DatePicker, message, Modal } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import numeral from 'numeral';
import { history } from 'umi';
import moment from 'moment';
import { abcAndNumber } from '@/services/commom';
import ProTable from '@ant-design/pro-table';
import FooterToolbar from '@/components/FooterToolbar';
import EndDateTime from '@/components/EndDateTime';
import { add, timeJudgment } from '../service';
import PolicyModal from './policyModal';
import Styles from '../index.less';

const { confirm } = Modal;
const FormItem = Form.Item;
const dateFormat = 'YYYY-MM-DD';

let textId = 0;

const SafeguardForm = () => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [policyVis, setPolicyVis] = useState(false);
  const [policyList, setpolicyList] = useState([]);
  const [rowList, setRowList] = useState([]);
  const [formdata, setFormData] = useState({});
  const [id, setID] = useState(null);
  const [addloading, setloading] = useState(false);

  const toolBarRender = (
    <Button
      key="add"
      type="primary"
      onClick={async () => {
        setID(1);
        const getForm = await form.validateFields();
        setPolicyVis(true);
        setFormData(getForm);
        setRowList(policyList);
      }}
    >
      新增
    </Button>
  );

  const showConfirm = () => {
    confirm({
      title: '保单信息未提交是否要返回?',
      icon: <ExclamationCircleOutlined />,
      onOk() {
        history.goBack();
      },
      onCancel() {},
    });
  };

  const goBack = () => {
    if (policyList.length > 0) {
      showConfirm();
      return;
    }
    history.goBack();
  };

  const getUpdataMsg = (dataModefily) => {
    const oldData = policyList;
    const newArr = [];
    const modefilyData = dataModefily;
    for (let i = 0; i < oldData.length; i += 1) {
      if (oldData[i].textId === dataModefily.textId) {
        oldData.splice(i, 1, modefilyData);
        newArr.push(...oldData);
      }
    }
    return newArr;
  };

  // 前台删除
  const deleteMsg = async (res) => {
    const newList = policyList;
    const newcardData = newList.filter((item) => item.textId !== res.textId);
    setpolicyList(newcardData);
  };

  // 提交方法
  const submit = async () => {
    const formMsg = await form.validateFields();
    setloading(true);
    const param = {
      ...formMsg,
      validFrom: moment(formMsg.validFrom).format(dateFormat),
      validTo: moment(formMsg.validTo).format(dateFormat),
      insurePolicyVOList: policyList,
    };
    const titmParams = {
      ...formMsg,
      insurePolicyVOList: policyList,
    };
    const response = await timeJudgment(titmParams);
    if (response.code === 500) {
      message.error(response.msg);
      setloading(false);
      return;
    }
    if (response.code === 200) {
      const res = await add(param);
      const { msg, code } = res;
      if (code === 500) {
        message.error(msg);
        setloading(false);
      }
      if (code === 200) {
        message.success(msg);
        history.goBack();
      }
      setloading(false);
    }
  };

  const modefily = (data) => {
    setID(2);
    setPolicyVis(true);
    setFormData(data);
    setRowList(policyList);
  };

  const columns = [
    {
      title: '保单号',
      dataIndex: 'policyno',
      ellipsis: true,
      width: 150,
      fixed: 'left',
    },
    {
      title: '保单主体',
      dataIndex: 'body',
      ellipsis: true,
      width: 100,
      fixed: 'left',
    },
    {
      title: '约定投保金额（万元）',
      dataIndex: 'policySum',
      ellipsis: true,
      width: 180,
      align: 'right',
      render: (_, record) => numeral(record.policySum).format('0,0.00'),
    },
    {
      title: '最高赔偿限额（万元）',
      dataIndex: 'maxpaySum',
      width: 180,
      align: 'right',
      render: (val) => numeral(val).format('0,0.00'),
    },
    {
      title: '赔偿比例（%）',
      dataIndex: 'payLv',
      align: 'right',
      width: 120,
    },
    {
      title: '最长付款期限（天）',
      dataIndex: 'payperiod',
      width: 180,
      align: 'right',
    },
    {
      title: '限额闲置期（天）',
      dataIndex: 'quotafree',
      width: 150,
      align: 'right',
    },
    {
      title: '赔款等待期（天）',
      dataIndex: 'paywait',
      width: 150,
      align: 'right',
    },
    {
      title: '币种',
      dataIndex: 'currency',
      width: 100,
    },
    {
      title: '生效时间',
      dataIndex: 'validFrom',
      width: 130,
      valueType: 'date',
    },
    {
      title: '到期时间',
      dataIndex: 'validTo',
      width: 130,
      valueType: 'date',
    },
    {
      title: '创建人',
      dataIndex: 'createdBy',
      width: 130,
    },
    {
      title: '创建时间',
      dataIndex: 'createTime',
      width: 130,
      valueType: 'date',
    },
    {
      title: '操作',
      dataIndex: 'action',
      width: 130,
      fixed: 'right',
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                modefily(record);
              }}
            >
              修改
            </a>
            <Divider type="vertical" />
            <a
              onClick={() => {
                deleteMsg(record);
              }}
            >
              删除
            </a>
          </Fragment>
        );
      },
    },
  ];

  const policymodal = {
    id,
    formdata,
    rowList,
    vis: policyVis,
    onCancel: (data) => {
      setPolicyVis(data);
      setFormData({});
    },
    queryList: (num, data) => {
      // 新增
      if (num === 1) {
        textId += 1;
        const addData = data;
        addData.textId = textId;
        setpolicyList([...policyList, addData]);
      }
      if (num === 2) {
        setpolicyList(getUpdataMsg(data));
      }
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
                label="公司编码"
                name="compCode"
                rules={[
                  {
                    required: true,
                    message: '请输入公司编码！',
                  },
                  {
                    validator: abcAndNumber,
                  },
                ]}
              >
                <Input maxLength={15} placeholder="长度小于15位" />
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
                <Input placeholder="请输入公司名称" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="生效时间"
                name="validFrom"
                rules={[
                  {
                    required: true,
                    message: '请选择生效时间！',
                  },
                ]}
              >
                <DatePicker style={{ width: '100%' }} />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="到期时间"
                name="validTo"
                rules={[
                  {
                    required: true,
                    message: '请选择到期时间！',
                  },
                ]}
              >
                <EndDateTime />
              </FormItem>
            </Col>
            {/* <Col span={8}>
              <FormItem
                label="有效状态"
                name="status"
                rules={[
                  {
                    required: true,
                    message: '请选择保单状态',
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
      <Card
        className={Styles.extraStyle}
        title="保单信息"
        extra={toolBarRender}
        style={{ marginTop: '24px' }}
      >
        <ProTable
          headerTitle=""
          rowKey="policyno"
          dataSource={policyList}
          search={false}
          actionRef={ref}
          options={false}
          toolBarRender={false}
          pagination={false}
          columns={columns}
          scroll={{ x: 2150 }}
        />
      </Card>
      <PolicyModal {...policymodal} />
      <FooterToolbar>
        <Button
          key="back"
          onClick={() => {
            goBack();
          }}
        >
          返回
        </Button>
        <Button
          type="primary"
          loading={addloading}
          onClick={() => {
            submit();
          }}
        >
          提交
        </Button>
      </FooterToolbar>
    </PageContainer>
  );
};

export default SafeguardForm;
