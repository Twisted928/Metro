import React, { useState, useEffect, useRef, Fragment } from 'react';
import {
  Button,
  Divider,
  Card,
  Form,
  Row,
  Col,
  Input,
  DatePicker,
  message,
  Menu,
  Dropdown,
  Modal,
  Spin,
} from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import numeral from 'numeral';
import moment from 'moment';
import ProTable from '@ant-design/pro-table';
import { DownOutlined } from '@ant-design/icons';
import FooterToolbar from '@/components/FooterToolbar';
import EndDateTime from '@/components/EndDateTime';
import DeleteText from '@/components/DeleteText';
import { update, details, timeJudgment, policyJudgment } from '../service';
import PolicyModal from './policyModal';
import Styles from '../index.less';

const FormItem = Form.Item;
const dateFormat = 'YYYY-MM-DD';
const currentDate = moment().format(dateFormat);

let textId = `a${1}`;

const SafeguardForm = ({
  dispatch,
  loadingdetails,
  loadingboundary,
  safeguardCompnay: { boundaryData },
}) => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [policyVis, setPolicyVis] = useState(false);
  const [policyList, setpolicyList] = useState([]);
  const [modifyList, setModefiyList] = useState([]);
  const [addList, setAddList] = useState([]);
  const [updataloading, setloading] = useState(false);
  const [boundaryVis, setBoundaryVis] = useState(false);
  const [pageLoading, setPageLoading] = useState(false);
  const [idx, setID] = useState(null);
  const [mainId, setMainId] = useState(null);
  const [formdata, setformdata] = useState({});
  const [delId, setDelId] = useState([]);
  const [rowList, setRowList] = useState([]);

  const query = async () => {
    const { id } = history.location.query;
    if (!id) {
      return;
    }
    setPageLoading(true);
    const response = await details({ id });
    const { code, msg, data } = response;
    if (code === 200) {
      const { insurePolicyVOList, validFrom, validTo, status, compCode, compName } = data;
      form.setFieldsValue({
        status,
        compCode,
        compName,
        validFrom: validFrom ? moment(validFrom) : '',
        validTo: validTo ? moment(validTo) : '',
      });
      setMainId(id);
      setpolicyList(insurePolicyVOList);
    } else {
      message.error(msg);
    }
    setPageLoading(false);
  };

  const queryBoundary = (param = {}) => {
    dispatch({
      type: 'safeguardCompnay/boundary',
      payload: param,
    });
  };

  useEffect(() => {
    query();
  }, []);

  const toolBarRender = (
    <Button
      key="add"
      type="primary"
      onClick={() => {
        setID(1);
        setPolicyVis(true);
        setformdata(form.getFieldValue());
        setRowList(policyList);
      }}
    >
      新增
    </Button>
  );

  const removeTextId = (id) => {
    const modefiy = modifyList;
    const afterRemove = modefiy.filter((item) => item.textId !== id);
    return afterRemove;
  };

  const getUpdataMsg = (dataModefily) => {
    const oldData = policyList;
    const newArr = [];
    const modefilyData = dataModefily;
    for (let i = 0; i < oldData.length; i += 1) {
      if (oldData[i].id) {
        if (oldData[i].id === dataModefily.id) {
          modefilyData.idType = dataModefily.id;
          oldData.splice(i, 1, modefilyData);
          setModefiyList([...modifyList, modefilyData]);
          newArr.push(...oldData);
        }
      } else if (oldData[i].textId === dataModefily.textId) {
        const noRemoveList = removeTextId(oldData[i].textId);
        modefilyData.idType = dataModefily.textId;
        oldData.splice(i, 1, modefilyData);
        const newAddData = [...noRemoveList, modefilyData];
        setModefiyList([...newAddData]);
        newArr.push(...oldData);
      }
    }
    return newArr;
  };

  // 判断新增和修改的数据
  const policyVOList = (aArr, uArr) => {
    let arr;
    if (aArr.length === 0 && uArr.length === 0) {
      arr = [];
    }
    if (aArr.length === 0 && uArr.length > 0) {
      arr = uArr;
    }
    if (aArr.length > 0 && uArr.length === 0) {
      arr = aArr;
    }
    if (aArr.length > 0 && uArr.length > 0) {
      for (let i = 0; i < aArr.length; i += 1) {
        for (let j = 0; j < uArr.length; j += 1) {
          if (aArr[i].id === uArr[j].id) {
            aArr.splice(i, 1);
          }
        }
      }
      arr = [...aArr, ...uArr];
    }
    return arr;
  };

  // 提交
  const submit = async () => {
    const formMsg = await form.validateFields();
    setloading(true);
    const param = {
      ...formMsg,
      id: mainId,
      deleteId: delId,
      insurePolicyVOList: policyVOList(addList, modifyList),
      validFrom: moment(formMsg.validFrom).format(dateFormat),
      validTo: moment(formMsg.validTo).format(dateFormat),
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
      const res = await update(param);
      const { msg, code } = res;
      if (code === 500) {
        message.error(msg);
      }
      if (code === 200) {
        history.goBack();
      }
      setloading(false);
    }
  };

  // 保单删除
  const deleteMsg = async (res) => {
    const response = await policyJudgment({ policyno: res.policyno });
    if (response.code === 500) {
      message.error(response.msg);
      return;
    }
    if (response.code === 200) {
      const newList = policyList;
      const newcardData = newList.filter((item) => item.policyno !== res.policyno);
      setpolicyList(newcardData);
      if (res.id) {
        setDelId([...delId, res.id]);
      }
      if (res.textId) {
        const aList = addList;
        const uList = modifyList;
        const newAddList = aList.filter((item) => item.textId !== res.textId);
        const newUpList = uList.filter((item) => item.textId !== res.textId);
        setModefiyList(newUpList);
        setAddList(newAddList);
      }
    }
  };

  // 查看保单范围
  const checkBoundary = (res) => {
    setBoundaryVis(true);
    const params = {
      policId: res.id,
      storeCodeList: res.insureScopeDOList,
    };
    queryBoundary(params);
  };

  const menu = (res) => (
    <Menu>
      <Menu.Item key="del">
        <DeleteText deleteFunc={() => deleteMsg(res)} />
      </Menu.Item>
      {typeof res.id === 'number' ? (
        <Menu.Item key="det">
          <a onClick={() => checkBoundary(res)} target="_blank">
            查看保单范围
          </a>
        </Menu.Item>
      ) : (
        ''
      )}
    </Menu>
  );

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
      render: (_, record) => numeral(record.maxpaySum).format('0,0.00'),
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
      render: (_, record, index) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                setID(2);
                setPolicyVis(true);
                setformdata(record);
                setRowList(policyList);
              }}
            >
              修改
            </a>
            <Divider type="vertical" />
            <Dropdown overlay={() => menu(record, index)}>
              <a className="ant-dropdown-link" onClick={(e) => e.preventDefault()}>
                更多 <DownOutlined />
              </a>
            </Dropdown>
          </Fragment>
        );
      },
    },
  ];

  const bouColumns = [
    {
      title: '部门编码',
      dataIndex: 'storeCode',
      key: 'storeCode',
      width: 200,
    },
    {
      title: '部门名称',
      dataIndex: 'compName',
      key: 'compName',
      width: 200,
    },
  ];

  const policymodal = {
    mainId,
    formdata,
    rowList,
    id: idx,
    vis: policyVis,
    onCancel: (vis) => {
      setPolicyVis(vis);
      setformdata({});
    },
    queryList: (num, data) => {
      if (num === 1) {
        textId += 1;
        const addData = data;
        addData.textId = textId;
        addData.idType = textId;
        setpolicyList([...policyList, addData]);
        setAddList([...addList, addData]);
      }
      if (num === 2) {
        setpolicyList(getUpdataMsg(data));
      }
    },
  };

  return (
    <PageContainer ghost title={false}>
      <Spin spinning={pageLoading}>
        <Card title="基本信息">
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
                  label="公司编码"
                  name="compCode"
                  rules={[
                    {
                      required: true,
                      message: '请输入公司编码！',
                    },
                  ]}
                >
                  <Input disabled maxLength={15} placeholder="长度小于15位" />
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
                  label="生效时间"
                  name="validFrom"
                  rules={[
                    {
                      required: true,
                      message: '请选择生效时间！',
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
            loading={loadingdetails}
            toolBarRender={false}
            pagination={false}
            columns={columns}
            scroll={{ x: 2150 }}
          />
        </Card>
        <PolicyModal {...policymodal} />
        <Modal
          title="保单范围"
          width={700}
          visible={boundaryVis}
          onCancel={() => {
            setBoundaryVis(false);
          }}
          footer={null}
        >
          <ProTable
            headerTitle=""
            rowKey="storeCode"
            bordered
            dataSource={boundaryData}
            search={false}
            options={false}
            loading={loadingboundary}
            toolBarRender={false}
            pagination={false}
            columns={bouColumns}
            scroll={{ y: 500 }}
          />
        </Modal>
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
            type="primary"
            loading={updataloading}
            onClick={() => {
              submit();
            }}
          >
            提交
          </Button>
        </FooterToolbar>
      </Spin>
    </PageContainer>
  );
};

export default connect(({ safeguardCompnay, loading }) => ({
  safeguardCompnay,
  loadingboundary: loading.effects['safeguardCompnay/boundary'],
}))(SafeguardForm);
