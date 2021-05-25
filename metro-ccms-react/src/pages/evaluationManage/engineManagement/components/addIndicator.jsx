/* eslint-disable react-hooks/exhaustive-deps */
import React, { Fragment, useEffect, useState } from 'react';
import { Card, Form, Button, Select, Divider, Row, Col, message, Popconfirm } from 'antd';
import ProTable from '@ant-design/pro-table';
import { history, connect } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import DeleteText from '@/components/DeleteText/index';
import FooterToolbar from '@/components/FooterToolbar';
import { modelList, addList, updataQuery, deleteQuery } from '../service';
import IndicatorsModal from './indicatorsModal';
import Styles from '../index.less';

const FormItem = Form.Item;
const { Option } = Select;

const AddIndicator = ({ dispatch, loading, engineManagement: { modelInlist } }) => {
  const [form] = Form.useForm();
  const [editVisible, setEditVisible] = useState(false);
  const [indicaVisible, setIndiVisible] = useState(false);
  const [mainId, setMainId] = useState(null);
  const [modIndexItem, setModIndexItem] = useState(null);
  const [quotaList, setQuotaList] = useState([]);
  const [zbList, setZbList] = useState([]);
  const [method, setMethod] = useState(null);
  const [listVis, setListVis] = useState(false);
  const [submitLoading, setSubmitLoading] = useState(false);
  const [ifUpdata, setIfUpdata] = useState(false);
  const [formData, setFormData] = useState(null);
  const [rowIndex, setRowIndex] = useState(null);

  const query = () => {
    const { modId } = history.location.query;
    if (!modId) {
      return;
    }
    setMainId(modId);
    dispatch({
      type: 'engineManagement/modelInQuery',
      payload: { modId },
    });
  };

  useEffect(() => {
    query();
  }, []);

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
    setTimeout(() => {
      scrollToAnchor('editZb');
    }, 100);
  };

  const changeZB = async (val) => {
    form.resetFields(['indexId']);
    const params = {
      type: val,
    };
    const response = await modelList(params);
    if (response.code === 200) {
      setQuotaList(response.rows);
    }
  };

  const selectType = (val, option) => {
    setMethod(option.method);
    setListVis(true);
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
    setMethod(res.method);
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

  const confirm = () => {
    setEditVisible(false);
    setIndiVisible(false);
    setListVis(false);
    setZbList([]);
    form.resetFields();
  };

  const toolBarRenderEdit = listVis ? (
    <Popconfirm
      placement="topRight"
      title="点击确认后将隐藏添加指标和指标评分区间设置"
      onConfirm={confirm}
      okText="确认"
      cancelText="取消"
    >
      <Button key="add" type="primary" className={Styles.closeButton} onClick={() => {}}>
        取消编辑
      </Button>
    </Popconfirm>
  ) : (
    <Button key="add1" type="primary" onClick={confirm} className={Styles.closeButton}>
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
      dataIndex: 'type',
      key: 'type',
      valueEnum: {
        A: {
          text: '信用准入',
        },
        B: {
          text: '保险准入',
        },
      },
    },
    {
      title: '指标名称',
      dataIndex: 'indexName',
      ellipsis: true,
      key: 'indexName',
    },
    {
      title: '打分方法',
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
    {
      title: '目标值',
      dataIndex: 'grade',
      width: 120,
      key: 'grade',
      hideInTable: method === '2',
    },
    {
      title: '区间下限',
      dataIndex: 'lowerValue',
      width: 120,
      key: 'lowerValue',
      hideInTable: method === '1',
    },
    {
      title: '区间上限',
      dataIndex: 'upperValue',
      width: 120,
      key: 'upperValue',
      hideInTable: method === '1',
    },
    {
      title: '区间范围',
      dataIndex: 'includeRange',
      width: 120,
      key: 'includeRange',
      hideInTable: method === '1',
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
      title: '描述',
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
      <Card title="指标列表" extra={toolBarRender} className={Styles.extraStyle}>
        <ProTable
          rowKey="id"
          dataSource={modelInlist}
          options={false}
          loading={loading}
          pagination={false}
          columns={columns}
          search={false}
          scroll={{ y: 700 }}
        />
      </Card>
      {editVisible ? (
        <Card id="editZb" title="编辑指标" extra={toolBarRenderEdit} className={Styles.extraStyle}>
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
                      信用准入
                    </Option>
                    <Option key="B" value="B">
                      保险准入
                    </Option>
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
            </Row>
          </Form>
        </Card>
      ) : (
        ''
      )}
      {listVis ? (
        <Card
          id="zblist"
          title="编辑指标列表"
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
    </PageContainer>
  );
};

export default connect(({ engineManagement, loading }) => ({
  engineManagement,
  loading: loading.effects['engineManagement/modelInQuery'],
}))(AddIndicator);
