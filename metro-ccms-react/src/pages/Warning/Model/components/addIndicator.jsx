/* eslint-disable no-nested-ternary */
/* eslint-disable react-hooks/exhaustive-deps */
import React, { useState, useRef, useEffect } from 'react';
import {
  Button,
  Card,
  Form,
  Row,
  Col,
  Input,
  Radio,
  message,
  Popconfirm,
  Select,
  Spin,
} from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { EditableProTable } from '@ant-design/pro-table';
import { history, connect } from 'umi';
import RoleList from '@/components/RoleList';
import { deepCompare } from '@/services/commom';
import DeleteText from '@/components/DeleteText';
import { detailed, saveEarlyModelConfig } from '../service';
import FooterToolbar from '@/components/FooterToolbar';
import styles from '../index.less';

const { TextArea } = Input;
const { Option } = Select;
const FormItem = Form.Item;

const AddIndicator = ({ dispatch, userId, warningmodel: { basciData } }) => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [dataSource, setDataSource] = useState([]);
  const [loading, setLoading] = useState(false);
  const [loadingpage, setloadingpage] = useState(false);
  const [updataList, setupdataList] = useState([]);
  const [deleteList, setDeleteListId] = useState([]);
  const [modelCode, setMocdelCode] = useState('');
  const [roleList, setRoleList] = useState([]);
  const [deleteRowList, setDeleteRowList] = useState([]);
  const [warnitem, setWarnItem] = useState('');
  const [roeKey, setRowkey] = useState(null);
  const [recordCreator, setRecordCreator] = useState(true);

  const query = () => {
    dispatch({
      type: 'warningmodel/queryById',
      payload: userId,
    });
  };

  const getdetails = async () => {
    const { mocdelCode } = history.location.query;
    if (!mocdelCode) {
      return;
    }
    setMocdelCode(mocdelCode);
    setloadingpage(true);
    const res = await detailed({ mocdelCode });
    const { code, data, msg } = res;
    if (code === 200) {
      form.setFieldsValue(data.modelDO);
      setDataSource(data.configDO);
      setWarnItem(data.configDO[0]?.warnItem ?? '');
      setloadingpage(false);
    }
    if (code === 500) {
      setloadingpage(false);
      message.error(msg);
    }
  };

  useEffect(() => {
    query();
    getdetails();
  }, []);

  // 提交
  const sunmitFrom = async () => {
    const newList = [];
    const oldList = updataList;
    const deleteData = deleteRowList;
    for (let i = 0; i < oldList.length; i += 1) {
      for (let j = 0; j < deleteData.length; j += 1) {
        if (deepCompare(oldList[i], deleteData[j])) {
          oldList.splice(i, 1);
        }
      }
    }
    oldList.forEach((item) => {
      newList.push({ ...item, mocdelCode: modelCode });
    });
    const params = {
      configDO: newList,
      configIds: deleteList,
    };

    setLoading(true);
    const res = await saveEarlyModelConfig(params);
    const { code, msg } = res;
    if (code === 200) {
      history.goBack();
      setLoading(false);
    }
    if (code === 500) {
      message.error(msg);
      setLoading(false);
    }
  };

  // 删除
  const deleteDocument = (record, index) => {
    setDataSource(dataSource.filter((item, idx) => idx !== index));
    const deteArr = [];
    if (record.id) {
      deteArr.push(record.id);
    }
    setDeleteRowList([...deleteRowList, record]);
    setDeleteListId([...deleteList, ...deteArr]);
  };

  // 编辑
  const editList = (index) => {
    setRecordCreator(false);
    ref.current.startEditable(index);
    ref.current.cancelEditable(roeKey);
    setRowkey(index);
  };

  // 编辑保存
  const rowListSave = (key, row) => {
    const oldList = updataList;
    const result = [];
    const newList = [];
    if (oldList.length === 0 || roeKey === null) {
      // 数组为空直接条件
      newList.push(...oldList, row);
    } else if (row.index >= 0) {
      oldList.forEach((item, idx) => {
        if (item.index === roeKey) {
          oldList.splice(idx, 1, row);
          newList.push(...oldList);
        }
        newList.push(...oldList, row);
      });
    }
    newList.forEach((item) => {
      !result.some((v) => JSON.stringify(v) === JSON.stringify(item)) && result.push(item);
    });
    setRowkey(null);
    setRecordCreator(true);
    setupdataList(result);
  };

  const guarantList = () =>
    warnitem === '' ? (
      <Select>
        <Option key="A" value="A">
          逾期天数
        </Option>
        <Option key="B" value="B">
          合同到期前天数
        </Option>
        <Option key="C" value="C">
          授信到期前天数
        </Option>
      </Select>
    ) : (
      <Select>
        <Option key={warnitem} value={warnitem}>
          {warnitem === 'A'
            ? '逾期天数'
            : warnitem === 'B'
            ? '合同到期前天数'
            : warnitem === 'C'
            ? '授信到期前天数'
            : ''}
        </Option>
      </Select>
    );

  const setTabletext = (arr) => {
    const chooseId = arr;
    const basicList = basciData;
    const textArr = [];
    for (let i = 0; i < chooseId.length; i += 1) {
      for (let j = 0; j < basicList.length; j += 1) {
        if (basicList[j].roleId === Number(chooseId[i])) {
          textArr.push(basicList[j].roleName);
        }
      }
    }
    return textArr.toString();
  };

  const columns = [
    {
      title: '预警项',
      dataIndex: 'warnItem',
      width: '13%',
      formItemProps: {
        rules: [
          {
            required: true,
            message: '预警项为必填项!',
          },
        ],
      },
      renderFormItem: (_, data) => {
        return guarantList(data.record);
      },
      valueEnum: {
        A: {
          text: '逾期天数',
        },
        B: {
          text: '合同到期前天数',
        },
        C: {
          text: '授信到期前天数',
        },
      },
    },
    {
      title: '上限',
      key: 'upper',
      width: '10%',
      dataIndex: 'upper',
      valueType: 'digit',
      formItemProps: {
        rules: [
          {
            required: true,
            message: '上限为必填项!',
          },
        ],
      },
    },
    {
      title: '下限',
      width: '10%',
      dataIndex: 'lower',
      valueType: 'digit',
      formItemProps: {
        rules: [
          {
            required: true,
            message: '下限为必填项！',
          },
        ],
      },
    },
    {
      title: '通知角色',
      dataIndex: 'roleIds',
      width: 400,
      ellipsis: true,
      formItemProps: {
        rules: [
          {
            required: true,
            message: '通知角色为必填项！',
          },
        ],
      },
      renderFormItem: () => {
        return (
          <RoleList
            value={roleList}
            onChange={(value) => {
              setRoleList(value);
            }}
          />
        );
      },
      render: (_, record) => {
        return setTabletext(record.roleIds ?? []);
      },
    },
    {
      title: '预警频率',
      dataIndex: 'warnFre',
      valueType: 'select',
      formItemProps: {
        rules: [
          {
            required: true,
            message: '预警频率为必填项！',
          },
        ],
      },
      valueEnum: {
        A: {
          text: '一天一次',
        },
        B: {
          text: '一周一次',
        },
        C: {
          text: '半月一次',
        },
        D: {
          text: '一月一次',
        },
      },
    },
    {
      title: '方式',
      dataIndex: 'mode',
      formItemProps: {
        rules: [
          {
            required: true,
            message: '方式为必填项！',
          },
        ],
      },
      valueEnum: {
        A: {
          text: '通知',
        },
        B: {
          text: '邮件',
        },
        C: {
          text: '邮件+通知',
        },
      },
    },
    {
      title: '操作',
      valueType: 'option',
      width: 200,
      render: (text, record, index) => [
        <a
          key="editable"
          onClick={() => {
            editList(index);
          }}
        >
          编辑
        </a>,
        <DeleteText
          text="删除"
          deleteFunc={() => {
            deleteDocument(record, index);
          }}
        />,
      ],
    },
  ];

  return (
    <PageContainer ghost title={false}>
      <Spin spinning={loadingpage}>
        <Card title="预警模型信息">
          <Form form={form} layout="vertical" initialValues={{ status: '1' }}>
            <Row gutter={64}>
              <Col span={8}>
                <FormItem
                  label="模型编码"
                  name="mocdelCode"
                  rules={[
                    {
                      required: true,
                      message: '请输入担保函编码',
                    },
                  ]}
                >
                  <Input disabled placeholder="长度小于32位" maxLength={32} />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem
                  label="模型名称"
                  name="mocdelName"
                  rules={[
                    {
                      required: true,
                      message: '请输入担保函类型',
                    },
                  ]}
                >
                  <Input disabled />
                </FormItem>
              </Col>
              <Col span={8}>
                <FormItem label="状态" name="status">
                  <Radio.Group disabled>
                    <Radio value={0}>停用</Radio>
                    <Radio value={1}>启用</Radio>
                  </Radio.Group>
                </FormItem>
              </Col>
              <Col span={24}>
                <FormItem
                  label="描述"
                  name="remark"
                  rules={[
                    {
                      required: true,
                      message: '请输入客户名称',
                    },
                  ]}
                >
                  <TextArea disabled rows={2} placeholder="" />
                </FormItem>
              </Col>
            </Row>
          </Form>
        </Card>
        <Card
          className={styles.extraStyle}
          title="预警配置"
          // extra={toolBarRender}
          style={{ marginTop: '24px' }}
        >
          <EditableProTable
            headerTitle=""
            actionRef={ref}
            columns={columns}
            value={dataSource}
            recordCreatorProps={recordCreator}
            onChange={setDataSource}
            editable={{
              type: 'multiple',
              onSave: rowListSave,
              actionRender: (row, config, defaultDom) => [defaultDom.save, defaultDom.cancel],
              onCancel: () => {
                setRecordCreator(true);
                setRowkey(null);
              },
            }}
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
          <Popconfirm
            placement="top"
            title={'确认提交配置数据？'}
            onConfirm={sunmitFrom}
            okText="确认"
            cancelText="取消"
          >
            <Button loading={loading} type="primary">
              提交
            </Button>
          </Popconfirm>
        </FooterToolbar>
      </Spin>
    </PageContainer>
  );
};

export default connect(({ commonmodel, warningmodel, loading }) => ({
  commonmodel,
  warningmodel,
  loadingtodo: loading.effects['warningmodel/queryById'],
}))(AddIndicator);
