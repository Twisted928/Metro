/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable @typescript-eslint/no-unused-vars */
import { message, Modal, Input, Row, Col, Form, Radio, Upload, DatePicker, Button } from 'antd';
import React, { useEffect, useState } from 'react';
import { PictureOutlined } from '@ant-design/icons';
import Department from '@/components/Department';
import Roles from '@/components/RoleList';
import EndDateTime from '@/components/EndDateTime';
import moment from 'moment';
import E from 'wangeditor';
import { create, update } from '../service';
import PeopleModal from './peopleMsg';
import styles from '../index.less';

const { RangePicker } = DatePicker;
const { TextArea } = Input;
const FormItem = Form.Item;

const key = 'messageloading';
let editor = null;
const BasicForm = ({ row, onClose, visible }) => {
  const [form] = Form.useForm();
  const [submitting, setsubmitting] = useState(false);
  const [vis, setVis] = useState(false);
  const [content, setContent] = useState('');
  const [depAndRole, setDepAndRole] = useState(null);

  const formItemLayout = {
    labelCol: {
      span: 4,
    },
  };

  useEffect(() => {
    if (row.noticeId) {
      form.setFieldsValue({
        ...row,
        validFrom: moment(row.validFrom),
        validTo: moment(row.validTo),
      });
    }
  }, []);

  useEffect(() => {
    editor = new E('#edior');
    editor.config.onchange = (newHtml) => {
      setContent(newHtml);
      form.setFieldsValue({
        noticeContent: newHtml,
      });
    };
    editor.config.uploadImgShowBase64 = true;
    editor.create();
    if (row.noticeId) {
      editor.txt.html(row.noticeContent);
    }
    return () => {
      editor.destroy();
    };
  }, []);

  const onFinish = async () => {
    const values = await form.validateFields();
    message.loading({ content: '正在提交', key, duration: 0 });
    setsubmitting(true);
    let response;
    let param;
    if (row && row.noticeId) {
      // 更新方法
      param = {
        ...values,
        noticeId: row.noticeId,
        noticeContent: content,
      };
      response = await update({ ...param });
    } else {
      // 新增方法
      param = {
        ...values,
        noticeContent: content,
      };
      response = await create({ ...param });
    }
    const { code, msg } = response;
    if (code === 200) {
      message.success({ content: msg, key });
      setsubmitting(false);
      onClose();
    } else {
      message.error({ content: msg, key });
      setsubmitting(false);
    }

    // 返回首页
  };

  const handleEditorChange = (value) => {};

  const disabledDate = (date) => {
    if (!date) {
      return false;
    }
    return date < moment().subtract(1, 'days');
  };

  const checkPeople = async () => {
    const deptIdsAndRoles = await form.validateFields(['deptIds', 'roleIds']);
    setVis(true);
    setDepAndRole(deptIdsAndRoles);
  };

  const newFooter = (
    <div style={{ display: 'flex', justifyContent: 'space-between' }}>
      <div>
        <Button onClick={checkPeople}>公告人员信息</Button>
      </div>
      <div>
        <Button onClick={onClose}>取消</Button>
        <Button type="primary" onClick={onFinish}>
          确认
        </Button>
      </div>
    </div>
  );

  const peopleModal = {
    vis,
    depAndRole,
    onCancel: (data) => {
      setVis(data);
    },
  };

  return (
    <Modal
      visible={visible}
      width={1200}
      title="公告信息维护"
      onOk={() => {
        form.submit();
      }}
      onCancel={() => {
        onClose();
      }}
      footer={newFooter}
    >
      <div className={styles.formStyle}>
        <Form
          form={form}
          layout="vertical"
          initialValues={{
            status: '1',
          }}
          onFinish={onFinish}
        >
          <Row gutter={64}>
            <Col span={8}>
              <FormItem
                label="部门"
                name="deptIds"
                rules={[
                  {
                    required: true,
                    message: '部门不能为空！',
                  },
                ]}
              >
                <Department />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="角色"
                name="roleIds"
                rules={[
                  {
                    required: true,
                    message: '角色不能为空！',
                  },
                ]}
              >
                <Roles />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="生效时间"
                name="validFrom"
                rules={[
                  {
                    required: false,
                    message: '生效区间不能为空！',
                  },
                ]}
              >
                <DatePicker />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="失效时间"
                name="validTo"
                rules={[
                  {
                    required: false,
                    message: '失效区间不能为空！',
                  },
                ]}
              >
                <EndDateTime />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span={24}>
              <FormItem
                label="标题"
                name="noticeTitle"
                rules={[
                  {
                    required: true,
                    message: '标题不能为空！',
                  },
                ]}
              >
                <Input placeholder="" maxLength={50} />
              </FormItem>
            </Col>
          </Row>
          <FormItem
            label="公告内容"
            name="noticeContent"
            rules={[
              {
                required: true,
                message: '通知内容不能为空！',
              },
            ]}
          >
            <div id="edior"></div>
          </FormItem>
        </Form>
        <PeopleModal {...peopleModal} />
      </div>
    </Modal>
  );
};

export default BasicForm;
