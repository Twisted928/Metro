/* eslint-disable no-nested-ternary */
/* eslint-disable react-hooks/exhaustive-deps */
import React, { useState, useRef, useEffect } from 'react';
import { Button, Card, Form, Row, Col, Input, Radio, message, Select, Spin } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import { EditableProTable } from '@ant-design/pro-table';
import { history, connect } from 'umi';
import RoleList from '@/components/RoleList';
import { detailed } from '../service';
import FooterToolbar from '@/components/FooterToolbar';
import styles from '../index.less';

const { TextArea } = Input;
const { Option } = Select;
const FormItem = Form.Item;

const AddIndicator = ({ dispatch, userId, warningmodel: { basciData } }) => {
  const ref = useRef();
  const [form] = Form.useForm();
  const [dataSource, setDataSource] = useState([]);
  const [roleList, setRoleList] = useState([]);
  const [warnitem, setWarnItem] = useState('');
  const [loadingpage, setLoadingPage] = useState(false);

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
    setLoadingPage(true);
    const res = await detailed({ mocdelCode });
    const { code, data, msg } = res;
    if (code === 200) {
      form.setFieldsValue(data.modelDO);
      setDataSource(data.configDO);
      setWarnItem(data.configDO[0].warnItem);
      setLoadingPage(false);
    }
    if (code === 500) {
      message.error(msg);
      setLoadingPage(false);
    }
  };

  useEffect(() => {
    query();
    getdetails();
  }, []);

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
        return setTabletext(record.roleIds);
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
            recordCreatorProps={false}
            onChange={setDataSource}
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

export default connect(({ commonmodel, warningmodel, loading }) => ({
  commonmodel,
  warningmodel,
  loadingtodo: loading.effects['warningmodel/queryById'],
}))(AddIndicator);
