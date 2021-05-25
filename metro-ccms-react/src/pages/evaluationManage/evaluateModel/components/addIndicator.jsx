import React, { Fragment, useEffect, useState, useCallback } from 'react';
import {
  Card,
  Spin,
  Form,
  Button,
  Select,
  Divider,
  Row,
  Col,
  Radio,
  InputNumber,
  message,
  Input,
  Popconfirm,
} from 'antd';
import ProTable from '@ant-design/pro-table';
import moment from 'moment';
import { history, connect } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import DeleteText from '@/components/DeleteText/index';
import FooterToolbar from '@/components/FooterToolbar';
import { maxNumber } from '@/services/commom';
import { modelList, addList, updataQuery, deleteQuery } from '../service';
import IndicatorsModal from './indicatorsModal';
import Styles from '../index.less';

const FormItem = Form.Item;
const { Option } = Select;

const AddIndicator = ({ dispatch, loading, evaluateModel: { catorList, catorBasic } }) => {
  const [form] = Form.useForm();
  const [formMsg] = Form.useForm();
  const [editVisible, setEditVisible] = useState(false);
  const [indicaVisible, setIndiVisible] = useState(false);
  const [mainId, setMainId] = useState(null);
  const [modIndexItem, setModIndexItem] = useState(null);
  const [quotaList, setQuotaList] = useState([]);
  const [zbList, setZbList] = useState([]);
  const [method, setMethod] = useState(null);
  const [listVis, setListVis] = useState(false);
  const [submitLoading, setSubmitLoading] = useState(false);
  const [pageLoading, setPageLoading] = useState(false);
  const [ifUpdata, setIfUpdata] = useState(false);
  const [formData, setFormData] = useState(null);
  const [rowIndex, setRowIndex] = useState(null);

  const query = useCallback(() => {
    const { id } = history.location.query;
    if (!id) {
      return;
    }
    setMainId(id);
    setPageLoading(true);
    dispatch({
      type: 'evaluateModel/catorQuery',
      payload: { modId: id },
    }).then((res) => {
      formMsg.setFieldsValue(res.modelInfoDO);
      setPageLoading(false);
    });
  }, [dispatch, formMsg]);

  useEffect(() => {
    query();
  }, [query]);

  // 页面滚动
  const scrollToAnchor = (name) => {
    if (name) {
      const anchorElement = document.getElementById(name);
      if (anchorElement) {
        anchorElement.scrollIntoView({ behavior: 'smooth' });
      }
    }
  };

  const webDelete = (delidx) => {
    const list = zbList;
    const newlist = list.filter((item, index) => {
      return delidx !== index;
    });
    setZbList(newlist);
  };

  // 删除接口
  const deleteList = async (res) => {
    const response = await deleteQuery({ id: res.id });
    if (response.code === 200) {
      query();
      setEditVisible(false);
      setListVis(false);
    }
    if (response.code === 500) {
      message.error(response.msg);
    }
  };

  // 添加指标Form
  const addZbForm = () => {
    setEditVisible(true);
    setIfUpdata(false);
    form.resetFields();
    setTimeout(() => {
      scrollToAnchor('editZb');
    }, 100);
  };

  const changeZB = async (val) => {
    form.resetFields(['indexId']);
    const params = {
      type: val,
      pageSize: 100,
    };
    const response = await modelList(params);
    if (response.code === 200) {
      setQuotaList(response.rows);
    }
  };

  const selectType = (val, option) => {
    setMethod(option.method);
    setListVis(true);
    setZbList([]);
    setTimeout(() => {
      scrollToAnchor('zblist');
    }, 100);
  };

  const updataList = (arr, data, rowIdx) => {
    const oldData = arr;
    const newArr = [];
    const modefilyData = data;
    for (let i = 0; i < oldData.length; i += 1) {
      if (i === rowIdx) {
        oldData.splice(i, 1, modefilyData);
        newArr.push(...oldData);
      }
    }
    return newArr;
  };

  // 修改接口
  const updatazbMsg = async (res) => {
    const response = await updataQuery({ modIndexItemId: res.id });
    if (response.code === 200) {
      setModIndexItem(res);
      setIfUpdata(true);
      setZbList(response.data);
      form.setFieldsValue({
        ...res,
        indexId: res.indexName,
      });
      setEditVisible(true);
      setListVis(true);
      scrollToAnchor('editZb');
    }
  };

  const maxNumber100 = async (_, value) => {
    if (value && value > 100) {
      return Promise.reject(new Error('输入的数值不能大于100！'));
    }
    if (value && value < 0) {
      return Promise.reject(new Error('输入的数值不能小于0！'));
    }
    return false;
  };

  // 保存数据
  const submitMsg = async () => {
    const submitForm = await form.validateFields();
    setSubmitLoading(true);
    let params = {};
    if (!modIndexItem) {
      params = {
        ...submitForm,
        method,
        modelId: mainId,
        itemList: zbList,
      };
    } else {
      params = {
        id: modIndexItem.id,
        method: modIndexItem.method,
        indexId: modIndexItem.indexId,
        standrad: submitForm.standrad,
        weight: submitForm.weight,
        modelId: mainId,
        itemList: zbList,
      };
    }
    const response = await addList(params);
    if (response.code === 200) {
      message.success('保存成功！');
      setEditVisible(false);
      setListVis(false);
      setSubmitLoading(false);
      query();
    }
    if (response.code === 500) {
      message.error(response.msg);
      setSubmitLoading(false);
    }
  };

  const confirm = () => {
    setEditVisible(false);
    setIndiVisible(false);
    setListVis(false);
    form.resetFields();
  };

  const toolBarRender = (
    <Button
      key="add"
      type="primary"
      disabled={editVisible}
      className={Styles.closeButton}
      onClick={() => {
        addZbForm();
      }}
    >
      添加指标
    </Button>
  );

  const toolBarRenderEdit = listVis ? (
    <Popconfirm
      placement="topRight"
      title="点击确认后将隐藏添加指标和指标评分区间设置"
      onConfirm={confirm}
      okText="确认"
      cancelText="取消"
    >
      <Button key="add" type="primary" className={Styles.closeButton}>
        取消编辑
      </Button>
    </Popconfirm>
  ) : (
    <Button key="add" type="primary" onClick={confirm} className={Styles.closeButton}>
      取消编辑
    </Button>
  );

  const toolBarRenderZb = (
    <Button
      key="add"
      type="primary"
      className={Styles.closeButton}
      onClick={() => {
        setIndiVisible(true);
      }}
    >
      新增列表项
    </Button>
  );

  const columns = [
    {
      title: '指标大类',
      width: 200,
      dataIndex: 'type',
      key: 'type',
      valueEnum: {
        A: {
          text: '定性指标',
        },
        B: {
          text: '财务指标',
        },
        C: {
          text: '交易指标',
        },
      },
    },
    {
      title: '指标名称',
      width: 200,
      dataIndex: 'indexName',
      key: 'indexName',
    },
    {
      title: '打分方法',
      width: 200,
      dataIndex: 'method',
      key: 'method',
      valueEnum: {
        1: {
          text: '逻辑判断法',
        },
        2: {
          text: '区间打分法',
        },
      },
    },
    {
      title: '标准值',
      width: 200,
      dataIndex: 'standrad',
      align: 'right',
      key: 'standrad',
    },
    {
      title: '权重',
      width: 200,
      dataIndex: 'weight',
      align: 'right',
      key: 'weight',
    },
    {
      title: '操作',
      dataIndex: 'action',
      key: 'action',
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                updatazbMsg(record);
              }}
            >
              修改
            </a>
            <Divider type="vertical" />
            <DeleteText
              deleteFunc={() => {
                deleteList(record);
              }}
            />
          </Fragment>
        );
      },
    },
  ];

  const columnsZb = [
    // {
    //   title: '指标值',
    //   dataIndex: 'grade',
    //   width: 120,
    //   key: 'grade',
    // },
    // {
    //   title: '描述',
    //   dataIndex: 'description',
    //   width: 120,
    //   key: 'description',
    // },
    {
      title: '区间下限',
      dataIndex: 'lowerValue',
      width: 120,
      key: 'lowerValue',
    },
    {
      title: '区间上限',
      dataIndex: 'upperValue',
      width: 120,
      key: 'upperValue',
    },
    {
      title: '区间范围',
      dataIndex: 'includeRange',
      width: 120,
      key: 'includeRange',
      render: (val) => {
        if (val === 1) {
          return '包含上限和下限[0,1]';
        }
        if (val === 2) {
          return '不包含上限和下限(0,1)';
        }
        if (val === 3) {
          return '包含下限[0,1)';
        }
        if (val === 4) {
          return '包含上限(0,1]';
        }
        return '';
      },
    },
    {
      title: '指标评分',
      dataIndex: 'grade',
      width: 120,
      key: 'grade2',
    },
    {
      title: '区间描述',
      dataIndex: 'description',
      width: 120,
      key: 'description',
      ellipsis: true,
    },
    // {
    //   title: '加减分依据描述',
    //   dataIndex: 'remark',
    //   width: 120,
    //   key: 'remark',
    // },
    // {
    //   title: '分数',
    //   dataIndex: 'grade',
    //   width: 120,
    //   key: 'grade1',
    // },
    {
      title: '操作',
      dataIndex: 'action',
      width: 120,
      key: 'action',
      render: (_, record, index) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                setFormData(record);
                setRowIndex(index);
                setIndiVisible(true);
              }}
            >
              修改
            </a>
            <Divider type="vertical" />
            <a onClick={() => webDelete(index)}>删除</a>
          </Fragment>
        );
      },
    },
  ];

  const indicatorsModal = {
    method,
    formData,
    rowIndex,
    vis: indicaVisible,
    onCancel: (val) => {
      setIndiVisible(val);
    },
    getMsg: (data, idx) => {
      if (idx === undefined) {
        setZbList([...zbList, data]);
        return;
      }
      setZbList(updataList(zbList, data, idx));
      setRowIndex(null);
    },
  };

  return (
    <PageContainer title={false} ghost>
      <Spin spinning={pageLoading}>
        <Card id="editZb" title="模型信息">
          <Form form={formMsg} layout="vertical">
            <Row gutter={64}>
              <Col span={8}>
                <FormItem
                  label="模型编码"
                  name="id"
                  rules={[
                    {
                      required: false,
                      message: '模型编码不能为空！',
                    },
                  ]}
                >
                  <Input disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="模型名称"
                  name="name"
                  rules={[
                    {
                      required: false,
                      message: '模型名称不能为空！',
                    },
                  ]}
                >
                  <Input disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="行业类型"
                  name="indusType"
                  rules={[
                    {
                      required: false,
                      message: '行业类型不能为空！',
                    },
                  ]}
                >
                  <Select disabled>
                    <Option value={1}>企业类</Option>
                    <Option value={2}>政府背景类</Option>
                    <Option value={3}>社会组织类</Option>
                  </Select>
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="有无财报"
                  name="financial"
                  rules={[
                    {
                      required: false,
                      message: '有无财报不能为空！',
                    },
                  ]}
                >
                  <Radio.Group disabled>
                    <Radio value="1">有财报</Radio>
                    <Radio value="0">无财报</Radio>
                  </Radio.Group>
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="新/老客户"
                  name="ifOld"
                  rules={[
                    {
                      required: false,
                      message: '新/老客户不能为空！',
                    },
                  ]}
                >
                  <Radio.Group disabled>
                    <Radio value={1}>新客户</Radio>
                    <Radio value={0}>老客户</Radio>
                  </Radio.Group>
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="发布状态"
                  name="publish"
                  rules={[
                    {
                      required: false,
                      message: '发布状态不能为空！',
                    },
                  ]}
                >
                  <Radio.Group disabled>
                    <Radio value="1">已发布</Radio>
                    <Radio value="0">未发布</Radio>
                  </Radio.Group>
                </FormItem>
              </Col>
            </Row>
          </Form>
        </Card>
        <Card title="模型指标清单" extra={toolBarRender} className={Styles.extraStyle}>
          <ProTable
            rowKey="id"
            dataSource={catorList}
            options={false}
            loading={loading}
            pagination={false}
            columns={columns}
            search={false}
            scroll={{ y: 700 }}
          />
        </Card>
        {editVisible ? (
          <Card
            id="editZb"
            title="添加指标"
            extra={toolBarRenderEdit}
            className={Styles.extraStyle}
          >
            <Form form={form} layout="vertical">
              <Row gutter={64}>
                <Col span={8}>
                  <FormItem
                    label="指标大类"
                    name="type"
                    rules={[
                      {
                        required: true,
                        message: '请选择指标大类！',
                      },
                    ]}
                  >
                    <Select disabled={ifUpdata} onSelect={changeZB}>
                      <Option key="A" value="A">
                        定性指标
                      </Option>
                      <Option key="B" value="B">
                        财务指标
                      </Option>
                      <Option key="C" value="C">
                        交易指标
                      </Option>
                      {/* <Option value="D">信用状况</Option>
                    <Option value="Z">加减分项</Option> */}
                    </Select>
                  </FormItem>
                </Col>
                <Col span={8}>
                  <FormItem
                    label="指标"
                    name="indexId"
                    rules={[
                      {
                        required: true,
                        message: '请选择指标！',
                      },
                    ]}
                  >
                    <Select disabled={ifUpdata} onChange={selectType}>
                      {(quotaList || []).map((item) => {
                        return (
                          <Option method={item.method} key={item.id} value={item.id}>
                            {item.name}
                          </Option>
                        );
                      })}
                    </Select>
                  </FormItem>
                </Col>
                <Col span={8}>
                  <FormItem
                    label="权重"
                    name="weight"
                    rules={[
                      {
                        required: true,
                        message: '权重不能为空！',
                      },
                      {
                        validator: maxNumber,
                      },
                    ]}
                  >
                    <InputNumber min={0} placeholder="数值最大为1" />
                  </FormItem>
                </Col>
                <Col span={8}>
                  <FormItem
                    label="标准值"
                    name="standrad"
                    rules={[
                      {
                        required: true,
                        message: '标准值不能为空！',
                      },
                      {
                        validator: maxNumber100,
                      },
                    ]}
                  >
                    <InputNumber min={1} placeholder="数值最大为100" />
                  </FormItem>
                </Col>
              </Row>
            </Form>
          </Card>
        ) : (
          ''
        )}
        {listVis ? (
          <Card
            id="zblist"
            title="指标评分区间设置"
            className={Styles.extraStyle}
            extra={toolBarRenderZb}
          >
            <ProTable
              rowKey="id"
              dataSource={zbList}
              options={false}
              loading={false}
              pagination={false}
              columns={columnsZb}
              search={false}
              scroll={{ y: 300 }}
            />
          </Card>
        ) : (
          ''
        )}
        <IndicatorsModal {...indicatorsModal} />
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
            key="saveAndUpdata"
            loading={submitLoading}
            onClick={() => submitMsg()}
            disabled={!listVis}
          >
            保存
          </Button>
        </FooterToolbar>
      </Spin>
    </PageContainer>
  );
};

export default connect(({ evaluateModel, loading }) => ({
  evaluateModel,
  loading: loading.effects['evaluateModel/catorQuery'],
}))(AddIndicator);
