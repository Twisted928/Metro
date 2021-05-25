import React, { useState, useEffect, useRef, Fragment } from 'react';
import { Button, Card, Form, Row, Col, Input, Spin } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import moment from 'moment';
import numeral from 'numeral';
import ProTable, { EditableProTable } from '@ant-design/pro-table';
import FooterToolbar from '@/components/FooterToolbar';
import { downloadFile } from '@/services/commom';
import Styles from '../index.less';

const FormItem = Form.Item;
const dateFormat = 'YYYY-MM-DD';
const currentDate = moment().format(dateFormat);

const SafeguardForm = ({ dispatch, loading }) => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [fileList, setFile] = useState([]);
  const [pageLoading, setPageLoading] = useState(false);
  const [editableKeys, setEditableRowKeys] = useState([]);
  const [csList, setCsList] = useState([]);
  const [tzList, setTzList] = useState([]);
  const [csMoneyList, setCsMoneyList] = useState([]);
  const [rowId, setRowId] = useState(null);

  // 催收单明细列表
  const csQuery = () => {
    const { id, applicationNo } = history.location.query;
    if (!id) {
      return;
    }
    const param = {
      id,
      applicationNo,
    };
    setPageLoading(true);
    dispatch({
      type: 'collectionManagement/detailsList',
      payload: param,
    }).then((res) => {
      const { collectionVO, collectionDetailDO, collectionRecordDO, sysBasicFile } = res.data;
      form.setFieldsValue({
        ...collectionVO,
        ddate: collectionVO.ddate ? moment(collectionVO.ddate).format(dateFormat) : '',
      });
      setCsMoneyList([collectionVO]);
      setCsList(collectionDetailDO);
      setTzList(collectionRecordDO);
      setFile(sysBasicFile);
      setPageLoading(false);
    });
  };

  useEffect(() => {
    csQuery();
  }, []);

  // 下载
  const download = async (record) => {
    const { attachmentName, attachmentUrl } = record;
    const params = {
      fileName: attachmentName,
      filePath: attachmentUrl,
    };
    await downloadFile('/file/downAndUpload/downloadFile', params);
  };

  // 修改原数据
  const updataOldMsg = (row) => {
    const oldMsg = tzList;
    const newRow = row;
    let newMsg = [];
    if (rowId) {
      for (let i = 0; i < oldMsg.length; i += 1) {
        if (oldMsg[i].id === newRow.id) {
          oldMsg.splice(i, 1, newRow);
          newMsg.push(...oldMsg);
        }
      }
    } else {
      newMsg = [...oldMsg, row];
    }
    return newMsg;
  };

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
      title: '发票号',
      dataIndex: 'csbvcode',
      key: 'csbvcode',
    },
    {
      title: '发票日期',
      dataIndex: 'dsbvdate',
      key: 'dsbvdate',
      valueType: 'date',
    },
    {
      title: '到期日',
      dataIndex: 'dduedate',
      key: 'dduedate',
      valueType: 'date',
    },
    {
      title: '本位币',
      dataIndex: 'standardCurrency',
      key: 'standardCurrency',
    },
    {
      title: '应收账款',
      dataIndex: 'amount',
      key: 'amount',
      align: 'right',
      render: (_, record) => numeral(record.amount).format('0,0.00'),
    },
    {
      title: '门店编码',
      dataIndex: 'storeCode',
      key: 'storeCode',
    },
  ];

  const columnsDes = [
    {
      title: '单据状态',
      dataIndex: 'status',
      key: 'status',
      valueEnum: {
        1: {
          text: '催收中',
          status: '1',
        },
        2: {
          text: '催收成功',
          status: '2',
        },
        3: {
          text: '催收失败',
          status: '3',
        },
      },
      formItemProps: {
        rules: [
          {
            required: true,
            message: '单据状态不能为空',
          },
        ],
      },
    },
    {
      title: '催收进度描述',
      dataIndex: 'desc',
      key: 'desc',
    },
  ];

  const columnsCs = [
    {
      title: '账期内应收(万元)',
      dataIndex: 'undue',
      align: 'center',
      key: 'undue',
      search: false,
      render: (_, record) => numeral(record.undue).format('0,0.00'),
    },
    {
      title: '应收金额(万元)',
      dataIndex: 'iar',
      align: 'center',
      key: 'iar',
      search: false,
      render: (_, record) => numeral(record.iar).format('0,0.00'),
    },
    {
      title: '逾期金额(万元)',
      dataIndex: 'idue',
      align: 'center',
      key: 'idue',
      search: false,
      render: (_, record) => numeral(record.idue).format('0,0.00'),
    },
    {
      title: '逾期1-15天',
      dataIndex: 'idue015',
      align: 'center',
      key: 'idue015',
      search: false,
      render: (_, record) => numeral(record.idue015).format('0,0.00'),
    },
    {
      title: '逾期16-30天',
      dataIndex: 'idue030',
      align: 'center',
      key: 'idue030',
      search: false,
      render: (_, record) => numeral(record.idue030).format('0,0.00'),
    },
    {
      title: '逾期31-60天',
      align: 'center',
      dataIndex: 'idue060',
      key: 'idue060',
      search: false,
      render: (_, record) => numeral(record.idue060).format('0,0.00'),
    },
    {
      title: '逾期61-90天',
      align: 'center',
      dataIndex: 'idue090',
      key: 'idue090',
      search: false,
      render: (_, record) => numeral(record.idue090).format('0,0.00'),
    },
    {
      title: '逾期91-365天',
      align: 'center',
      dataIndex: 'idue180',
      key: 'idue180',
      search: false,
      render: (_, record) => numeral(record.idue180).format('0,0.00'),
    },
    {
      title: '逾期一年',
      align: 'center',
      dataIndex: 'idue360',
      key: 'idue360',
      search: false,
      render: (_, record) => numeral(record.idue360).format('0,0.00'),
    },
    {
      title: '逾期超一年',
      align: 'center',
      dataIndex: 'idue361',
      key: 'idue361',
      search: false,
      render: (_, record) => numeral(record.idue361).format('0,0.00'),
    },
  ];

  // 附件
  const columnsUpload = [
    {
      title: '附件名称',
      dataIndex: 'attachmentName',
      key: 'attachmentName',
    },
    {
      title: '操作',
      dataIndex: 'action',
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                download(record);
              }}
            >
              下载
            </a>
          </Fragment>
        );
      },
    },
  ];

  return (
    <PageContainer title={false} ghost>
      <Spin spinning={pageLoading}>
        <Card title="单据信息">
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
                  label="催收单号"
                  name="applicationNo"
                  rules={[
                    {
                      required: false,
                      message: '请输入催收单号',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="门店编码"
                  name="storeCode"
                  rules={[
                    {
                      required: false,
                      message: '请输入门店编码',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="门店名称"
                  name="storeName"
                  rules={[
                    {
                      required: false,
                      message: '请输入账期内应收',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="门店地址"
                  name="storeAddress"
                  rules={[
                    {
                      required: false,
                      message: '请输入账期内应收',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="客户编码"
                  name="custCode"
                  rules={[
                    {
                      required: false,
                      message: '请输入客户编码',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="客户名称"
                  name="custName"
                  rules={[
                    {
                      required: false,
                      message: '请输入客户名称',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="客户地址"
                  name="custAddress"
                  rules={[
                    {
                      required: false,
                      message: '请输入账期内应收',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="卡号编码"
                  name="cardCode"
                  rules={[
                    {
                      required: false,
                      message: '请输入卡号编码',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="卡号名称"
                  name="cardName"
                  rules={[
                    {
                      required: false,
                      message: '请输入卡号名称',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="传真+分机号"
                  name="fax"
                  rules={[
                    {
                      required: false,
                      message: '请输入账期内应收',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="联系人"
                  name="contact"
                  rules={[
                    {
                      required: false,
                      message: '请输入账期内应收',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="电话"
                  name="phone"
                  rules={[
                    {
                      required: false,
                      message: '请输入账期内应收',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="电子邮箱"
                  name="email"
                  rules={[
                    {
                      required: false,
                      message: '请输入账期内应收',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="催款日期"
                  name="ddate"
                  rules={[
                    {
                      required: false,
                      message: '请输入账期内应收',
                    },
                  ]}
                >
                  <Input disabled placeholder="" />
                </FormItem>
              </Col>
            </Row>
          </Form>
        </Card>
        <Card
          className={Styles.extraStyle}
          title="催收单"
          // extra={toolBarRender}
          style={{ marginTop: '24px' }}
        >
          <ProTable
            headerTitle=""
            rowKey="id"
            bordered
            loading={loading}
            dataSource={csMoneyList}
            search={false}
            actionRef={ref}
            options={false}
            toolBarRender={false}
            pagination={false}
            columns={columnsCs}
          />
        </Card>
        <Card
          className={Styles.extraStyle}
          title="催收单明细"
          // extra={toolBarRender}
          style={{ marginTop: '24px' }}
        >
          <ProTable
            headerTitle=""
            rowKey="id"
            bordered
            loading={loading}
            dataSource={csList}
            search={false}
            actionRef={ref}
            options={false}
            toolBarRender={false}
            pagination={false}
            columns={columns}
          />
        </Card>
        <Card className={Styles.extraStyle} title="台账信息" style={{ marginTop: '24px' }}>
          <EditableProTable
            headerTitle=""
            rowKey="id"
            recordCreatorProps={false}
            value={tzList}
            actionRef={ref}
            toolBarRender={false}
            pagination={false}
            columns={columnsDes}
            editable={{
              editableKeys,
              onSave: async (_, row) => {
                setTzList(updataOldMsg(row));
                setRowId(null);
              },
              onDelete: (_, row) => {
                const deleData = tzList.filter((item) => {
                  return item.id !== row.id;
                });
                setTzList(deleData);
              },
              onChange: setEditableRowKeys,
            }}
          />
        </Card>
        {/* <UpdataFile {...updataFile} /> */}
        <Card className={Styles.extraStyle} title="附件信息" style={{ marginTop: '24px' }}>
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
        </FooterToolbar>
      </Spin>
    </PageContainer>
  );
};

export default connect(({ collectionManagement, loading }) => ({
  collectionManagement,
  loading: loading.effects['collectionManagement/detailsList'],
}))(SafeguardForm);
