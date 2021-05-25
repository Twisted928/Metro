/* eslint-disable react-hooks/exhaustive-deps */
import { message, Input, Form, Row, Col, DatePicker, Skeleton, Modal, TreeSelect } from 'antd';
import React, { useEffect, useState } from 'react';
import moment from 'moment';
import { create, update, getDeptAll } from '../service';

const FormItem = Form.Item;

const key = 'messageloading';

const BasicForm = ({ row = {}, setRow, visible, onClose }) => {
  const [deptTree, setDeptTree] = useState([]);
  const [loadding, setLoadding] = useState(false);
  const [submitting, setsubmitting] = useState(false);
  const [deptCode, setDeptCode] = useState(null);
  const [form] = Form.useForm();

  // 进入页面查询信息
  const queryData = async () => {
    if (!row || !row.id) {
      return;
    }
    form.setFieldsValue({
      ...row,
      validFrom: row.validFrom ? moment(row.validFrom) : '',
      validTo: row.validTo ? moment(row.validTo) : '',
    });
    setLoadding(false);
  };

  const queryDeptTree = async () => {
    const response = await getDeptAll();
    if (response.code === 200) {
      setDeptTree([{ value: 0, deptCode: 0, label: '主类目', children: response.data }]);
    }
  };

  useEffect(() => {
    queryData();
    queryDeptTree();
  }, []);

  const onFinish = async (values) => {
    message.loading({ content: '正在提交', key, duration: 0 });
    setsubmitting(true);
    let response;
    if (row && row.id) {
      // 更新方法
      const params = {
        ...values,
        validFrom: values.validFrom ? moment(values.validFrom).format('YYYY-MM-DD') : '',
        validTo: values.validTo ? moment(values.validTo).format('YYYY-MM-DD') : '',
        id: row.id,
        parentType: deptCode,
      };
      response = await update(params);
    } else {
      // 新增方法
      const params = {
        ...values,
        validFrom: values.validFrom ? moment(values.validFrom).format('YYYY-MM-DD') : '',
        validTo: values.validTo ? moment(values.validTo).format('YYYY-MM-DD') : '',
        parentType: deptCode,
      };
      response = await create(params);
    }

    const { code, msg } = response;
    if (code === 200) {
      message.success({ content: msg, key, duration: 3 });
      setsubmitting(false);
      onClose();
    } else {
      message.error({ content: msg, key, duration: 3 });
      setsubmitting(false);
    }

    // 返回首页
  };

  const onFinishFailed = () => {};

  const disabledDate = (date) => {
    if (!date) {
      return false;
    }
    return date < moment().subtract(1, 'days');
  };

  return (
    <Modal
      width={1000}
      title="维护参数信息"
      confirmLoading={submitting}
      destroyOnClose
      maskClosable={false}
      visible={visible}
      onOk={() => {
        form.submit();
      }}
      onCancel={() => {
        onClose();
      }}
      afterClose={() => {
        setRow();
      }}
    >
      <Skeleton loading={loadding}>
        <Form
          form={form}
          name="sysdateCreate"
          layout="vertical"
          onFinish={onFinish}
          onFinishFailed={onFinishFailed}
          initialValues={{ status: '0' }}
        >
          <Row gutter={64}>
            <Col span={8}>
              <FormItem
                label="上级参数"
                name="parentId"
                rules={[
                  {
                    required: true,
                    message: '上级参数不能为空！',
                  },
                ]}
              >
                <TreeSelect
                  style={{ width: '100%' }}
                  dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
                  treeData={deptTree}
                  placeholder=""
                  allowClear
                  onSelect={(value, node) => {
                    setDeptCode(node.deptCode);
                  }}
                />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="参数代码"
                name="ctype"
                rules={[
                  {
                    required: true,
                    message: '参数代码不能为空！',
                  },
                ]}
              >
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="参数名称"
                name="description"
                rules={[
                  {
                    required: true,
                    message: '参数名称不能为空！',
                  },
                ]}
              >
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem label="生效时间" name="validFrom">
                <DatePicker disabledDate={disabledDate} />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem label="到期时间" name="validTo">
                <DatePicker disabledDate={disabledDate} />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Skeleton>
    </Modal>
  );
};

export default BasicForm;
