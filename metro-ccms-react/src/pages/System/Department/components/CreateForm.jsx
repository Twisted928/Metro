import { message, Input, Form, Row, Col, Radio, Skeleton, Modal, TreeSelect } from 'antd';
import React, { useEffect, useState } from 'react';
// import Department from '@/components/Department/index';
import { abcAndNumber } from '@/services/commom';
import { create, update, queryById, getDeptAll } from '../service';

const { TextArea } = Input;
const FormItem = Form.Item;

const key = 'messageloading';

const BasicForm = ({ row = {}, setRow, visible, onClose }) => {
  const [data, setData] = useState({});
  const [deptTree, setDeptTree] = useState([]);
  const [loadding, setLoadding] = useState(false);
  const [submitting, setsubmitting] = useState(false);
  const [form] = Form.useForm();

  // 进入页面查询信息
  const queryData = async () => {
    if (!row || !row.deptId) {
      return;
    }

    setLoadding(true);
    const { code, data: responData } = await queryById(row.deptId);
    if (code === 200) {
      setData(responData);
      form.setFieldsValue(responData);
    } else {
      message.error('加载失败');
    }

    setLoadding(false);
  };

  const queryDeptTree = async () => {
    const response = await getDeptAll();
    if (response.code === 200) {
      setDeptTree(response.data);
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
    if (data && data.deptId) {
      // 更新方法
      const params = {
        ...values,
        deptId: row.deptId,
      };
      response = await update({ ...params });
    } else {
      // 新增方法
      response = await create({ ...values });
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

  return (
    <Modal
      width={1000}
      title="维护部门信息"
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
                label="上级部门"
                name="parentId"
                rules={[
                  {
                    required: true,
                    message: '上级部门不能为空！',
                  },
                ]}
              >
                <TreeSelect
                  style={{ width: '100%' }}
                  dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
                  treeData={deptTree}
                  placeholder=""
                  allowClear
                />
                {/* // <Department ifmultiple /> */}
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="部门编码"
                name="deptCode"
                rules={[
                  {
                    required: true,
                    message: '部门编码不能为空！',
                  },
                ]}
              >
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="部门名称"
                name="deptName"
                rules={[
                  {
                    required: true,
                    message: '部门名称不能为空！',
                  },
                ]}
              >
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="户名"
                name="accountName"
                rules={[
                  {
                    required: false,
                    message: '部门名称不能为空！',
                  },
                ]}
              >
                <Input maxLength={32} placeholder="最大长度为32位" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="开户行"
                name="accountBank"
                rules={[
                  {
                    required: false,
                    message: '部门名称不能为空！',
                  },
                ]}
              >
                <Input maxLength={32} placeholder="最大长度为32位" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="账户号"
                name="accountNo"
                rules={[
                  {
                    required: false,
                    message: '部门名称不能为空！',
                  },
                  {
                    validator: abcAndNumber,
                  },
                ]}
              >
                <Input maxLength={32} placeholder="最大长度为32位" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="门店地址"
                name="address"
                rules={[
                  {
                    required: false,
                    message: '部门名称不能为空！',
                  },
                ]}
              >
                <Input maxLength={32} placeholder="最大长度为32位" />
              </FormItem>
            </Col>
            {/* <Col span={8}>
              <FormItem label="状态" name="status">
                <Radio.Group>
                  <Radio value="0">正常</Radio>
                  <Radio value="1">停用</Radio>
                </Radio.Group>
              </FormItem>
            </Col> */}
          </Row>
          <Row>
            <Col span={24}>
              <FormItem label="备注" name="remark">
                <TextArea
                  rows={2}
                  maxLength={128}
                  placeholder="最大长度为128位"
                />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Skeleton>
    </Modal>
  );
};

export default BasicForm;
