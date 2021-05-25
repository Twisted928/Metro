/* eslint-disable no-nested-ternary */
import React, { useState, useEffect, useRef } from 'react';
import {
  Card,
  message,
  Form,
  Select,
  Input,
  Row,
  Col,
  DatePicker,
  Button,
  Skeleton,
  Space,
} from 'antd';
import { connect, history } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import moment from 'moment';
import FooterToolbar from '@/components/FooterToolbar';
import { queryDetails } from '../service';
import { downloadFile } from '@/services/commom';
import Styles from '../index.less';

const { Option } = Select;
const { TextArea } = Input;
const { RangePicker } = DatePicker;
const FormItem = Form.Item;

const ShowDetails = () => {
  const mainId = history.location.query;
  const formRef = useRef();
  const actionRef = useRef();
  const [form] = Form.useForm();
  const [fileList, setFile] = useState([]);
  const [dataSource, setDataSource] = useState([]);
  const [loading, setLoading] = useState(false);

  const fetchData = async (id) => {
    setLoading(true);
    const { code, data, msg } = await queryDetails(id);
    setLoading(false);
    if (code === 200) {
      const {
        custcode,
        custname,
        approvestatus,
        validfrom,
        validto,
        reason,
        sysBasicFile,
        applicationno,
        approvalRecordDO,
      } = data;
      setFile(sysBasicFile);
      setDataSource(approvalRecordDO);
      form.setFieldsValue({
        custcode,
        custname,
        applicationno,
        approvestatus,
        validRange: [moment(validfrom), moment(validto)],
        reason,
      });
    } else {
      message.error(msg);
    }
  };

  useEffect(() => {
    const { query } = history.location;
    fetchData({ ...query });
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

  const columns_approve = [
    {
      title: '审批人',
      dataIndex: 'approvalUser',
    },
    {
      title: '审批角色',
      dataIndex: 'approvalRole',
    },
    {
      title: '审批意见',
      dataIndex: 'approvalOpinion',
      render: (text) => {
        return (
          <a style={{ color: '#595959' }}>
            {text === 'APPROVE'
              ? '通过'
              : text === 'REJECT'
              ? '拒绝'
              : text === 'SUBMIT'
              ? '提交'
              : text === 'ROLLBACK'
              ? '驳回'
              : '-'}
          </a>
        );
      },
    },
    {
      title: '审批备注',
      dataIndex: 'remark',
    },
    {
      title: '审批时间',
      dataIndex: 'createTime',
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
          <a
            onClick={() => {
              download(record);
            }}
          >
            下载
          </a>
        );
      },
    },
  ];

  return (
    <PageContainer ghost title={false}>
      <Skeleton active loading={loading}>
        <Space direction="vertical" style={{ width: '100%' }} size="large">
          <Card title="基本信息">
            <Form form={form} layout="vertical" preserve={false}>
              <Row gutter={24}>
                <Col span={8} key="applicationno">
                  <FormItem name="applicationno" label="申请单号">
                    <Input disabled />
                  </FormItem>
                </Col>
                <Col span={8} key="custcode">
                  <FormItem name="custcode" label="客户编码">
                    <Input disabled />
                  </FormItem>
                </Col>

                <Col span={8} key="custname">
                  <FormItem name="custname" label="客户名称">
                    <Input disabled />
                  </FormItem>
                </Col>
                <Col span={8} key="approvestatus">
                  <FormItem name="approvestatus" label="申请单状态">
                    <Select disabled>
                      <Option value="1">审批中</Option>
                      <Option value="2">生效</Option>
                      <Option value="3">退回</Option>
                      <Option value="4">已撤销</Option>
                    </Select>
                  </FormItem>
                </Col>
                <Col span={8} key="validRange">
                  <FormItem
                    name="validRange"
                    label="生效区间"
                    rules={[
                      {
                        required: true,
                      },
                    ]}
                  >
                    <RangePicker disabled />
                  </FormItem>
                </Col>
                <Col span={24} key="reason">
                  <FormItem
                    name="reason"
                    label="原因"
                    rules={[
                      {
                        required: true,
                      },
                    ]}
                  >
                    <TextArea disabled rows={2} maxLength={255} placeholder="长度最大为255位" />
                  </FormItem>
                </Col>
              </Row>
            </Form>
          </Card>

          <Card className={Styles.extraStyle} title="附件信息" style={{ marginTop: '24px' }}>
            <ProTable
              headerTitle=""
              rowKey="id"
              bordered
              dataSource={fileList}
              search={false}
              options={false}
              toolBarRender={false}
              pagination={false}
              columns={columnsUpload}
            />
          </Card>

          <Card title="审批信息">
            <ProTable
              rowKeys="id"
              actionRef={actionRef}
              formRef={formRef}
              search={false}
              options={false}
              toolBarRender={false}
              columns={columns_approve}
              pagination={false}
              params={mainId}
              dataSource={dataSource}
            />
          </Card>
        </Space>
      </Skeleton>

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
    </PageContainer>
  );
};

export default connect(({ whitelist, loading }) => ({
  whitelist,
  loading: loading.effects['whitelist/query'],
}))(ShowDetails);
