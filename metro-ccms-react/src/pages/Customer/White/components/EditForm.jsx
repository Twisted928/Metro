import React, { useState, useEffect } from 'react';
import { Card, message, Form, Select, Input, Row, Col, DatePicker, Button, Skeleton } from 'antd';
import { connect, history } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import moment from 'moment';
import UpdataFile from '@/components/UpdataFile';
import FooterToolbar from '@/components/FooterToolbar';
import { deleteFile, promiseAll, deleteUploadList } from '@/services/commom';
import { updateCust, queryDetails } from '../service';
import Styles from '../index.less';

const { Option } = Select;
const { TextArea } = Input;
const { RangePicker } = DatePicker;
const FormItem = Form.Item;

const dateFormat = 'YYYY-MM-DD';

const BasicForm = () => {
  const [form] = Form.useForm();
  const [loading, setLoading] = useState(false);
  const [fileList, setFile] = useState([]);
  const [fileNew, setFileList] = useState([]);
  const [Submitting, setSubmitting] = useState(false);
  const [fileDelList, setFileDelList] = useState([]); // 删除的附件
  const [instance, setInstance] = useState('');
  const [mainId, setMainId] = useState(null);

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
        instanceid,
        applicationno,
      } = data;
      setMainId(id.id);
      setFile(sysBasicFile);
      setInstance(instanceid);
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

  const updataFileProps = {
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
    type: 1,
  };

  const onFinish = async () => {
    setSubmitting(true);
    const mainId = history.location.query.id;
    const getFormVal = await form.validateFields();
    const { approvestatus, custcode, custname, reason, validRange } = getFormVal;
    const params = {
      id: mainId,
      approvestatus,
      custcode,
      custname,
      reason,
      validfrom: validRange && moment(validRange[0]).format('YYYY-MM-DD'),
      validto: validRange && moment(validRange[1]).format('YYYY-MM-DD'),
      instanceid: instance,
      taskID: '',
    };

    const { code, data, msg } = await updateCust(params);

    if (code === 500) {
      message.error(msg);
    }
    if (code === 200) {
      if (!fileNew.length && !fileDelList.length) {
        history.goBack();
        return;
      }
      if (fileDelList.length && fileNew.length) {
        deleteFile(fileDelList);
        promiseAll(mainId, 1, fileNew);
        history.goBack();
        return;
      }
      if (fileNew.length && !fileDelList.length) {
        promiseAll(mainId, 1, fileNew);
        history.goBack();
        return;
      }
      if (fileDelList.length && !fileNew.length) {
        deleteFile(fileDelList);
        history.goBack();
      }
    }
    setSubmitting(false);
  };

  const disabledDate = (date) => {
    if (!date) {
      return false;
    }
    return date < moment().subtract(1, 'days');
  };

  return (
    <PageContainer ghost title={false}>
      <Skeleton active loading={loading}>
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
                  <RangePicker disabledDate={disabledDate} format={dateFormat} />
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
        <Button loading={Submitting} type="primary" onClick={() => onFinish()}>
          提交
        </Button>
      </FooterToolbar>
    </PageContainer>
  );
};

export default connect(({ whitelist, loading }) => ({
  whitelist,
  loading: loading.effects['whitelist/query'],
}))(BasicForm);
