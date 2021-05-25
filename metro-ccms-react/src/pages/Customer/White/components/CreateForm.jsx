import React, { useState, useEffect } from 'react';
import { Card, message, Form, Select, Input, Row, Col, DatePicker, Button } from 'antd';
import { connect, history } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import moment from 'moment';
import UpdataFile from '@/components/UpdataFile';
import FooterToolbar from '@/components/FooterToolbar';
import { promiseAll, deleteUploadList } from '@/services/commom';
import { addCust } from '../service';
import Styles from '../index.less';

const { Option } = Select;
const { TextArea } = Input;
const { RangePicker } = DatePicker;
const FormItem = Form.Item;

const dateFormat = 'YYYY-MM-DD';

const CreateForm = () => {
  const [form] = Form.useForm();
  const [fileList, setFile] = useState([]);
  const [fileNew, setFileList] = useState([]);
  const [Submitting, setSubmitting] = useState(false);

  useEffect(() => {
    const { custcode, custname } = history.location.query;
    form.setFieldsValue({
      custcode,
      custname,
      approvestatus: '1',
    });
  });

  const updataFileProps = {
    downloadBtu: true,
    dataSource: fileList,
    loadFun: (id) => {
      setFile(deleteUploadList(id, fileList));
    },
    changeFun: (file, fileUpList) => {
      setFileList([...fileNew, file]);
      setFile([...fileList, fileUpList]);
    },
  };

  const onFinish = async () => {
    const getFormVal = await form.validateFields();
    const { approvestatus, custcode, custname, reason, validRange } = getFormVal;
    const params = {
      approvestatus,
      custcode,
      custname,
      reason,
      validfrom: validRange && moment(validRange[0]).format('YYYY-MM-DD'),
      validto: validRange && moment(validRange[1]).format('YYYY-MM-DD'),
      formUrl: `/customer/whitelist/list/approve`,
    };

    setSubmitting(true);
    const { code, data, msg } = await addCust(params);

    if (code === 200) {
      if (fileNew.length === 0) {
        setSubmitting(false);
        message.success(msg);
        history.goBack();
      } else {
        promiseAll(data.id, 1, fileNew);
        history.goBack();
      }
    } else {
      message.error(msg);
      setSubmitting(false);
    }
  };

  return (
    <PageContainer ghost title={false}>
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
                <RangePicker format={dateFormat} />
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
                <TextArea rows={2} maxLength={255} placeholder="长度最大为255位" />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>

      <UpdataFile {...updataFileProps} />

      <FooterToolbar>
        <Button
          key="back"
          onClick={() => {
            history.goBack();
          }}
        >
          返回
        </Button>
        <Button loading={Submitting} type="primary" onClick={onFinish}>
          提交
        </Button>
      </FooterToolbar>
    </PageContainer>
  );
};

export default connect(({ whitelist, loading }) => ({
  whitelist,
  loading: loading.effects['whitelist/query'],
}))(CreateForm);
