/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable no-nested-ternary */
import React, { useState, useEffect, useRef } from 'react';
import { Card, message, Form, Select, Input, Row, Col, DatePicker, Button, Spin } from 'antd';
import { connect, history } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import moment from 'moment';
import UpdataFile from '@/components/UpdataFile';
import FooterToolbar from '@/components/FooterToolbar';
import { deleteFile, promiseAll, deleteUploadList } from '@/services/commom';
import { queryDetails, approve } from '../service';
import Styles from '../index.less';

const { Option } = Select;
const { TextArea } = Input;
const { RangePicker } = DatePicker;
const FormItem = Form.Item;

const Approve = () => {
  const mainId = history.location.query;
  const actionRef = useRef();
  const [form] = Form.useForm();
  const [fileList, setFile] = useState([]);
  const [fileNew, setFileList] = useState([]);
  const [custDetails, setCustDetails] = useState({});
  const [approvalOpinion, setApprovalOpinion] = useState('');
  const [fileDelList, setFileDelList] = useState([]); // 删除的附件
  const [remark, setReamrk] = useState('');
  const [dataSource, setDataSource] = useState([]);
  const [pageId, setID] = useState('');

  const fetchData = async (id) => {
    setID(id.id);
    const { code, data, msg } = await queryDetails(id);
    setCustDetails(data);
    if (code === 200) {
      const {
        custcode,
        approvalRecordDO,
        sysBasicFile,
        custname,
        approvestatus,
        validfrom,
        validto,
        reason,
        applicationno,
      } = data;
      setDataSource(approvalRecordDO);
      setFile(sysBasicFile);
      form.setFieldsValue({
        custcode,
        custname,
        approvestatus,
        applicationno,
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

  const approvaChange = (value) => {
    setApprovalOpinion(value);
  };
  const remarkChange = (e) => {
    setReamrk(e.target.value);
  };

  const Submit = async () => {
    const list = dataSource;
    let taskId = '';
    list.forEach((item) => {
      if (item.ifTask === 1) {
        taskId = item.taskId;
      }
    });
    const params = {
      id: custDetails?.id,
      custcode: custDetails?.custcode,
      custname: custDetails?.custname,
      validfrom: custDetails?.validRange?.[0],
      validto: custDetails?.validRange?.[1],
      applicationno: custDetails?.applicationno,
      instanceid: custDetails?.instanceid,
      approvalOpinion,
      remark,
      taskId,
    };
    const { code, msg } = await approve(params);
    if (code === 200) {
      if (!fileNew.length && !fileDelList.length) {
        history.goBack();
        return;
      }
      if (fileDelList.length && fileNew.length) {
        deleteFile(fileDelList);
        promiseAll(pageId, 1, fileNew);
        history.goBack();
        return;
      }
      if (fileNew.length && !fileDelList.length) {
        promiseAll(pageId, 1, fileNew);
        history.goBack();
        return;
      }
      if (fileDelList.length && !fileNew.length) {
        deleteFile(fileDelList);
        history.goBack();
      }
    } else {
      message.error(msg);
    }
  };

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
    pageId,
    type: 1,
  };

  const columns_approve = [
    {
      title: '审批人',
      key: 'approvalUser',
      dataIndex: 'approvalUser',
      width: '20%',
    },
    {
      title: '审批角色',
      key: 'approvalRole',
      dataIndex: 'approvalRole',
      width: '20%',
    },
    {
      title: '审批意见',
      key: 'approvalOpinion',
      width: '20%',
      dataIndex: 'approvalOpinion',
      render: (text, record) => {
        if (record.ifTask === 1) {
          return (
            <Select defaultValue={''} onChange={approvaChange} style={{ width: '100%' }}>
              <Option value="APPROVE">通过</Option>
              <Option value="REJECT">拒绝</Option>
            </Select>
          );
        }
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
      key: 'remark',
      width: '20%',
      dataIndex: 'remark',
      editable: true,
      render: (text, record) => {
        if (record.ifTask === 1) {
          return <Input onChange={remarkChange} defaultValue={''} />;
        }
        return <a style={{ color: '#595959' }}>{text}</a>;
      },
    },
    {
      title: '审批时间',
      key: 'createTime',
      width: '20%',
      dataIndex: 'createTime',
      valueType: 'date',
    },
  ];

  return (
    <PageContainer ghost title={false}>
      {/* <Spin spinning={loading}> */}
      <Card title="基本信息">
        <Form form={form} layout="vertical" preserve={false}>
          <Row gutter={64}>
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
                <TextArea rows={2} maxLength={255} placeholder="长度最大为255位" />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Card>
      <UpdataFile {...updataFile} />
      <Card title="审批信息" style={{ marginTop: '24px' }}>
        <ProTable
          search={false}
          rowKey="id"
          options={false}
          toolBarRender={false}
          actionRef={actionRef}
          columns={columns_approve}
          pagination={false}
          params={mainId}
          dataSource={dataSource}
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
        <Button key="sp" onClick={Submit} type="primary">
          审批
        </Button>
      </FooterToolbar>
      {/* </Spin> */}
    </PageContainer>
  );
};

export default connect(({ whitelist }) => ({
  whitelist,
}))(Approve);
