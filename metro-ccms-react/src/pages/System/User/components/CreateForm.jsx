/* eslint-disable prefer-promise-reject-errors */
/* eslint-disable react-hooks/rules-of-hooks */
import { Modal, message, Input, Form, Radio, Select, TreeSelect, Row, Col, Skeleton } from 'antd';
import React, { useEffect, useState } from 'react';
import { create, update, queryById, getDeptAll } from '../service';

const FormItem = Form.Item;
const { Option } = Select;
const { SHOW_PARENT } = TreeSelect;

const { TextArea } = Input;
const key = 'messageloading';
const valueMap = {};

const BasicForm = ({ row = {}, setRow, visible, onClose }) => {
  const [data, setData] = useState({});
  const [deptList, setDeptList] = useState([]);
  const [roleList, setRoleList] = useState([]);
  const [loading, setLoading] = useState(false);
  const [submitting, setsubmitting] = useState(false);
  const [deptcode, setDeptcode] = useState([]);
  const [mainId, setMainId] = useState(null);
  const [form] = Form.useForm();

  // 进入页面查询信息
  const queryData = async () => {
    setLoading(true);
    const { userId } = row;
    setMainId(userId);
    const { code, roles, roleIds, deptIds, data: responData } = await queryById(userId);
    const rowd = { ...responData, roleIds, deptIds };
    if (code === 200) {
      setData(rowd);
      form.setFieldsValue(rowd);
      setRoleList(roles);
    } else {
      message.error('加载失败');
    }

    setLoading(false);
  };

  const queryDept = async () => {
    const response = await getDeptAll();

    if (response.code === 200) {
      setDeptList(response.data);
    } else {
      message.error('部门加载失败');
    }
  };

  useEffect(() => {
    queryData();
    queryDept();
  }, []);

  const onFinish = async (values) => {
    const hide = message.loading({ content: '正在提交', key });
    setsubmitting(true);
    let response;
    const params = {
      ...values,
      deptCodes: deptcode,
    };
    if (data && data.userId) {
      // 更新方法
      response = await update({ ...data, ...params });
    } else {
      // 新增方法
      response = await create({ ...params });
    }

    const { code, msg } = response;
    if (code === 200) {
      message.success({ content: msg, key });
      hide();
      setsubmitting(false);
      onClose();
    } else {
      message.error(msg);
      hide();
      setsubmitting(false);
    }

    // 返回首页
  };

  const onFinishFailed = (errorInfo) => {
    // eslint-disable-next-line no-console
    console.log('Failed:', errorInfo);
  };

  // 遍历数据格式
  const loopDeptItem = (depts) =>
    depts.map(({ label, children, value, deptCode }) => {
      const tmp = {
        title: label,
        value,
        deptCode,
        children: children && loopDeptItem(children),
      };
      valueMap[value] = tmp;
      return tmp;
    });

  const getDepartValue = () => {
    const ifObj = Object.keys(deptList);
    if (ifObj.length === 0) {
      return false;
    }

    return loopDeptItem(deptList);
  };

  const getCnodeId = (allCheckedNodes) => {
    let nArr = [];
    allCheckedNodes.map((item) => {
      nArr.push(item.value);
      if (item.children && item.children.length > 0) {
        const a = getCnodeId(item.children);
        nArr = [...nArr, ...a];
      }
    });
    return nArr;
  };
  const getChildren = (depts) => {
    const deptAll = [];
    depts.forEach((dept) => {
      deptAll.push(dept);
      const value = valueMap[dept];
      if (value.children) {
        deptAll.push(...getCnodeId(value.children));
      }
    });
    return deptAll;
  };

  const getDepycodeId = (allCheckedNodes) => {
    let nArr = [];
    allCheckedNodes.map((item) => {
      nArr.push(item.deptCode);
      if (item.children && item.children.length > 0) {
        const a = getDepycodeId(item.children);
        nArr = [...nArr, ...a];
      }
    });
    return nArr;
  };

  const getChildrenDeCode = (depts) => {
    const deptCodeAll = [];
    depts.forEach((dept) => {
      const value = valueMap[dept];
      deptCodeAll.push(value.deptCode);
      if (value.children) {
        deptCodeAll.push(...getDepycodeId(value.children));
      }
    });
    return deptCodeAll;
  };

  const tProps = {
    treeData: getDepartValue(),
    treeNodeFilterProp: 'title',
    // treeNodeLabelProp: 'title',
    treeCheckable: true,
    showCheckedStrategy: SHOW_PARENT,
    placeholder: '',
    style: {
      width: '100%',
    },
    onChange: (value) => {
      form.setFieldsValue({
        deptIds: getChildren(value),
      });
      setDeptcode(getChildrenDeCode(value));
    },
  };

  const minNumber = async (_, value) => {
    if (value && value.length < 6) {
      return Promise.reject(new Error('密码最少为6位！'));
    }
    return false;
  };

  const typeEmail = async (_, value) => {
    if (value && value.includes('@')) {
      const emailName = value.split('@')[1];
      if (emailName !== 'metro.com.cn') {
        return Promise.reject(new Error('邮箱格式错误！'));
      }
    } else if (value && !value.includes('@')) {
      return Promise.reject(new Error('邮箱格式错误！'));
    }
    return false;
  };

  const typePhone = async (_, value) => {
    const phoneReg = /^1(3[0-9]|4[01456879]|5[0-3,5-9]|6[2567]|7[0-8]|8[0-9]|9[0-3,5-9])\d{8}$/;
    const phoneTitleReg = /^1(3[0-9]|4[01456879]|5[0-3,5-9]|6[2567]|7[0-8]|8[0-9]|9[0-3,5-9])$/;
    if (!value) {
      return Promise.resolve();
    }
    if (value.length === 11 && value.length > 3 && !phoneReg.test(value)) {
      return Promise.reject(new Error('手机号码格式错误！'));
    }
    if (value.length < 11 && value.length > 3 && phoneTitleReg.test(value.substr(0, 3))) {
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          if (value) {
            reject('手机号码长度错误！');
          }
          resolve();
        }, 500);
      });
    }
    if (value.length < 11 && value.length >= 3 && !phoneTitleReg.test(value)) {
      return Promise.reject(new Error('手机号码前三位格式错误！'));
    }
    return false;
  };

  return (
    <Modal
      title="维护用户信息"
      width={1000}
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
      <Form
        form={form}
        name="userCreate"
        layout="vertical"
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        initialValues={{
          sex: '1',
          status: '0',
        }}
      >
        <Skeleton loading={loading}>
          <Row gutter={64}>
            <Col span={8}>
              <FormItem
                label="用户名"
                name="userName"
                rules={[
                  {
                    required: true,
                    message: '用户名不能为空！',
                  },
                ]}
              >
                <Input disabled={data.userId} style={{ minHeight: 32 }} placeholder="" />
              </FormItem>
            </Col>
            {mainId ? (
              ''
            ) : (
              <Col span={8}>
                <FormItem
                  label="用户密码"
                  name="password"
                  rules={[
                    {
                      required: true,
                      message: '用户密码不能为空！',
                    },
                    {
                      validator: minNumber,
                    },
                  ]}
                >
                  <Input maxLength={16} style={{ minHeight: 32 }} placeholder="请输入6~16位密码" />
                </FormItem>
              </Col>
            )}
            <Col span={8}>
              <FormItem
                label="用户姓名"
                name="nickName"
                rules={[
                  {
                    required: true,
                    message: '用户姓名不能为空！',
                  },
                ]}
              >
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem label="性别" name="sex">
                <Radio.Group>
                  <Radio value="1">男</Radio>
                  <Radio value="0">女</Radio>
                </Radio.Group>
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="邮箱"
                name="email"
                rules={[
                  {
                    required: true,
                  },
                  {
                    validator: typeEmail,
                  },
                ]}
              >
                <Input placeholder="格式为xxx@metro.com.cn" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem
                label="手机号"
                name="phonenumber"
                rules={[
                  {
                    required: false,
                  },
                  {
                    validator: typePhone,
                  },
                ]}
              >
                <Input maxLength={11} placeholder="" />
              </FormItem>
            </Col>
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
                <TreeSelect {...tProps} />
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
                <Select
                  mode="multiple"
                  style={{ width: '100%' }}
                  placeholder=""
                  maxTagCount={2}
                  optionFilterProp="children"
                  // filterOption={(input, option) => option.children.indexOf(input) >= 0}
                >
                  {roleList &&
                    roleList.map((item) => (
                      <Option value={item.roleId} key={item.roleId}>
                        {item.roleName}
                      </Option>
                    ))}
                </Select>
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
                <TextArea row={2} maxLength={128} placeholder="最长输入128位字符" />
              </FormItem>
            </Col>
          </Row>
        </Skeleton>
      </Form>
    </Modal>
  );
};

export default BasicForm;
