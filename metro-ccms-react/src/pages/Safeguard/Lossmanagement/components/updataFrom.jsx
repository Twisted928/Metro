import React, { useState, useEffect, useRef, Fragment } from 'react';
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
  Divider,
  message,
  Popconfirm,
  InputNumber,
  Spin,
} from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { history, connect } from 'umi';
import moment from 'moment';
import ProTable from '@ant-design/pro-table';
import FooterToolbar from '@/components/FooterToolbar';
import UpdataFile from '@/components/UpdataFile';
import { deleteFile, promiseAll, deleteUploadList } from '@/services/commom';
import { getDetailList, tzUpdata } from '../service';
import Styles from '../index.less';

const FormItem = Form.Item;
const { Option } = Select;
const { TextArea } = Input;

const SafeguardForm = () => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [formTz] = Form.useForm();
  const [tzVis, setTzVis] = useState(false);
  const [pageLoading, setPageLoading] = useState(false);
  const [submitloading, setsubmitloading] = useState(false);
  const [fileList, setFile] = useState([]);
  const [tzList, setTzList] = useState([]);
  const [fileNew, setFileList] = useState([]); // 新增的附件
  const [fileDelList, setFileDelList] = useState([]); // 删除的附件
  const [mainId, setMainId] = useState(null);
  const [delId, setDelId] = useState([]);
  const [rowkeys, setRowKeys] = useState(null);
  const [rowList, setRowList] = useState(null);

  // 初始化数据
  const getBasicMsg = async () => {
    const { id, caseNo } = history.location.query;
    if (!id) {
      message.info('暂无数据！');
      return;
    }
    const param = {
      id,
      caseno: caseNo,
    };
    setPageLoading(true);
    const response = await getDetailList(param);
    const { code, data } = response;
    if (code === 200) {
      const { invoicedate, claimProgressDOList, sysBasicFileList } = data;
      form.setFieldsValue({
        ...data,
        invoicedate: invoicedate ? moment(invoicedate) : '',
      });
      setMainId(id);
      setTzList(claimProgressDOList);
      setFile(sysBasicFileList);
      setPageLoading(false);
    }
  };

  useEffect(() => {
    getBasicMsg();
  }, []);

  const tzModefiy = (res, idx) => {
    setTzVis(true);
    setRowKeys(idx);
    setRowList(res);
    formTz.setFieldsValue(res);
  };

  // 提交修改信息
  const submitNewMsg = async () => {
    const formValue = await form.validateFields();
    setsubmitloading(true);
    const params = {
      ...formValue,
      id: mainId,
      ids: delId,
      claimProgressDOList: tzList,
    };
    const response = await tzUpdata(params);
    if (response.code === 200) {
      if (!fileNew.length && !fileDelList.length) {
        setsubmitloading(false);
        history.goBack();
        return;
      }
      if (fileDelList.length && fileNew.length) {
        deleteFile(fileDelList);
        promiseAll(mainId, 8, fileNew);
        history.goBack();
        return;
      }
      if (fileNew.length && !fileDelList.length) {
        promiseAll(mainId, 8, fileNew);
        history.goBack();
        return;
      }
      if (fileDelList.length && !fileNew.length) {
        deleteFile(fileDelList);
        history.goBack();
        return;
      }
    }
    if (response.code === 500) {
      message.error(response.msg);
    }
    setsubmitloading(false);
  };

  // 台账删除
  const deleteTz = async (res, num) => {
    const newList = tzList;
    const newcardData = newList.filter((item, index) => index !== num);
    if (res.id) {
      setDelId([...delId, res.id]);
    }
    setTzList(newcardData);
  };

  const updataList = (data) => {
    const newData = [];
    const oldData = tzList;
    const modefiyData = data;
    for (let i = 0; i < oldData.length; i += 1) {
      if (i === rowkeys) {
        oldData.splice(i, 1, modefiyData);
        newData.push(...oldData);
      }
    }
    return newData;
  };

  // 新增修改台账
  const getFormatMsg = async () => {
    const formValue = await formTz.validateFields();
    let params;
    // 修改
    if (rowList) {
      params = {
        ...rowList,
        ...formValue,
      };
      setTzList(updataList(params));
    } else {
      setTzList([...tzList, formValue]);
    }
    setTzVis(false);
    formTz.resetFields();
  };

  const getNewListDel = (id) => {
    const newArr = fileNew;
    const newFileList = newArr.filter((item) => {
      return item.uid !== id;
    });
    return newFileList;
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
    // {
    //   title: '创建人',
    //   dataIndex: 'createdBy',
    //   key: 'createdBy',
    // },
    // {
    //   title: '创建时间',
    //   valueType: 'date',
    //   dataIndex: 'createTime',
    //   key: 'createTime',
    // },
    {
      title: '操作',
      dataIndex: 'action',
      render: (_, record, index) => {
        return (
          <Fragment>
            <a onClick={() => tzModefiy(record, index)}>修改</a>
            <Divider type="vertical" />
            <Popconfirm
              title="确定删除此条数据？"
              onConfirm={() => {
                deleteTz(record, index);
              }}
              okText="确认"
            >
              <a>删除</a>
            </Popconfirm>
          </Fragment>
        );
      },
    },
  ];

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
    type: 8,
  };

  return (
    <PageContainer ghost title={false}>
      <Spin spinning={pageLoading}>
        <Card title="报损信息">
          <Form form={form} layout="vertical">
            <Row gutter={64}>
              <Col span={8}>
                <FormItem
                  label="案件编号"
                  name="caseno"
                  rules={[
                    {
                      required: false,
                      message: '请输入案件编号',
                    },
                  ]}
                >
                  <Input disabled maxLength={32} placeholder="长度小于32位" />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="客户编码"
                  name="custCode"
                  rules={[
                    {
                      required: false,
                      message: '请选择客户编码',
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
                      message: '请输入客户名称',
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
                      message: '保险公司编码不能为空',
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
                      message: '请输入汇总发票号',
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
                      message: '请输入买方代码',
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
                      message: '请选择出运日期',
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
                      message: '汇总发票金额不能为空',
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
                      message: '投保金额不能为空',
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
          <Button type="primary" loading={submitloading} onClick={() => submitNewMsg()}>
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
            formTz.resetFields();
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
      </Spin>
    </PageContainer>
  );
};

export default connect(({ lossmanagement, loading }) => ({
  lossmanagement,
  loading: loading.effects['lossmanagement/query'],
}))(SafeguardForm);
