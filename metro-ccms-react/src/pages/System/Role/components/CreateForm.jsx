/* eslint-disable no-multi-assign */
/* eslint-disable @typescript-eslint/no-unused-vars */
import { ConsoleSqlOutlined } from '@ant-design/icons';
import {
  Skeleton,
  TreeSelect,
  message,
  Input,
  Form,
  Row,
  Col,
  Radio,
  Modal,
  InputNumber,
} from 'antd';
import TextArea from 'antd/lib/input/TextArea';
import React, { useEffect, useState } from 'react';
import { create, update, queryById, queryMenuTree, roleMenuTreeselect } from '../service';

const FormItem = Form.Item;
const valueMap = {};
const key = 'messageloading';

const BasicForm = ({ row = {}, setRow, visible, onClose }) => {
  const [data, setData] = useState({});
  const [menuTree, setMenuTree] = useState([]);
  const [resourceId, setResourceID] = useState([]);
  const [submitting, setsubmitting] = useState(false);
  const [loading, setLoading] = useState(false);
  const [form] = Form.useForm();

  // 进入页面查询信息
  const queryData = async () => {
    if (!row.roleId) {
      return;
    }

    setLoading(true);
    const { code, data: responData } = await queryById(row.roleId);
    if (code === 200) {
      // const roleResourceDos = responData.roleResourceDos.map((i) => `${i}`);
      // const tempresponData = { ...responData, roleResourceDos };
      setData(responData);
      form.setFieldsValue(responData);
    } else {
      message.error('加载失败');
    }
    setLoading(false);
  };

  const queryMenu = async () => {
    if (!row.roleId) {
      const { code, data: responData } = await queryMenuTree();
      if (code === 200) {
        setMenuTree(responData);
      }
      return;
    }

    const { code, menus, checkedKeys } = await roleMenuTreeselect(row.roleId);
    if (code === 200) {
      setMenuTree(menus);
      form.setFieldsValue({ menuIds: checkedKeys });
    }
  };

  useEffect(() => {
    queryData();
    queryMenu();
  }, []);

  // 遍历数据格式
  const loopDeptItem = (depts) =>
    depts.map(({ label, children, value }) => {
      const tmp = {
        title: label,
        value,
        children: children && loopDeptItem(children),
      };
      return tmp;
    });

  const getDepartValue = () => {
    const ifObj = Object.keys(menuTree);
    if (ifObj.length === 0) {
      return false;
    }
    return loopDeptItem(menuTree);
  };

  const loops = (list, parent) => {
    return (list || []).map(({ children, value }) => {
      const node = (valueMap[value] = {
        parent,
        value,
      });
      node.children = loops(children, node);
      return node;
    });
  };

  // 获取父节点id
  const getPath = async (value) => {
    await loops(getDepartValue());
    const path = [];
    value.map((item) => {
      let current = valueMap[item];
      while (current) {
        path.unshift(current.value);
        current = current.parent;
      }
    });
    setResourceID(path);
  };

  const getChildren = (depts) => {
    getPath(depts);
  };

  const onFinish = async (values) => {
    const resourceArr = Array.from(new Set(resourceId));
    message.loading({ content: '正在提交', key, duration: 0 });
    setsubmitting(true);
    const params = {
      ...values,
      menuIds: resourceArr,
    };
    console.log(params);
    let response;
    if (data && data.roleId) {
      // 更新方法
      response = await update({ ...data, ...params });
    } else {
      // 新增方法
      response = await create({ ...params });
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

  const tProps = {
    treeData: getDepartValue(),
    placeholder: '',
    showCheckedStrategy: TreeSelect.SHOW_ALL,
    maxTagCount: 3,
    treeCheckable: true,
    allowClear: true,
    onChange: (value) => {
      getChildren(value);
    },
  };

  return (
    <Modal
      width={1000}
      title="维护角色信息"
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
      <Skeleton loading={loading}>
        <Form
          form={form}
          name="sysdateCreate"
          initialValues={{
            status: '0',
          }}
          layout="vertical"
          onFinish={onFinish}
        >
          <Row gutter={64}>
            <Col span={8}>
              <FormItem
                label="角色名称"
                name="roleName"
                rules={[
                  {
                    required: true,
                    message: '角色名称不能为空！',
                  },
                ]}
              >
                <Input placeholder="" />
              </FormItem>
            </Col>
            <Col span={8}>
              <FormItem label="菜单权限" name="menuIds">
                <TreeSelect {...tProps} />
              </FormItem>
            </Col>
            {/* <Col span={8}>
              <FormItem
                label="状态"
                name="status"
                rules={[
                  {
                    required: true,
                    message: '状态不能为空！',
                  },
                ]}
              >
                <Radio.Group>
                  <Radio value="0">正常</Radio>
                  <Radio value="1">停用</Radio>
                </Radio.Group>
              </FormItem>
            </Col> */}
          </Row>
          <Row>
            <Col span={24}>
              <FormItem label="角色描述" name="remark">
                <TextArea rows={2} placeholder="" />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Skeleton>
    </Modal>
  );
};

export default BasicForm;
