/* eslint-disable @typescript-eslint/no-unused-vars */
import { message, Input, Form, Radio, TreeSelect, InputNumber, Modal, Row, Col } from 'antd';
import React, { useEffect, useState } from 'react';
import { history } from 'umi';
import { create, update, queryById, queryMenuTree } from '../service';

const FormItem = Form.Item;

const key = 'messageloading';

const filed = {
  icon: true,
  isFrame: true,
  path: true,
  component: true,
  perms: true,
  visible: true,
  status: true,
};

const MenuForm = ({ row = {}, setRow, visible, onClose }) => {
  const [data, setData] = useState({});
  const [parentMenu, setParentMenu] = useState([]);
  const [loadding, setLoadding] = useState(false);
  const [submitting, setsubmitting] = useState(false);
  const [form] = Form.useForm();
  const [visibleF, setVisibleF] = useState(filed);

  const hideForm = (menuType = 'M') => {
    if (menuType === 'M') {
      setVisibleF({ ...filed, component: false, perms: false });
    } else if (menuType === 'F') {
      setVisibleF({
        ...filed,
        icon: false,
        isFrame: false,
        path: false,
        component: false,
        perms: true,
        visible: false,
        status: false,
      });
    } else {
      setVisibleF(filed);
    }
  };

  // 进入页面查询信息
  const queryData = async () => {
    const { menuId } = row;
    if (!menuId) {
      hideForm();
      return;
    }
    setLoadding(true);
    const { code, data: responData } = await queryById(menuId);
    if (code === 200) {
      setData(responData);
      form.setFieldsValue(responData);
      hideForm(responData.menuType);
    } else {
      message.error('加载失败');
    }

    setLoadding(false);
  };

  const queryMenu = async () => {
    const { code, data: responData } = await queryMenuTree();
    if (code === 200) {
      // const roleResourceDos = responData.roleResourceDos.map((i) => `${i}`);
      setParentMenu([{ value: 0, label: '主类目', children: responData }]);
    }
  };

  useEffect(() => {
    queryData();
    queryMenu();
  }, []);

  const onFinish = async (values) => {
    const hide = message.loading({ content: '正在提交', key, duration: 0 });
    setsubmitting(true);

    let response;
    if (data && data.menuId) {
      // 更新方法
      response = await update({ ...data, ...values });
    } else {
      // 新增方法
      response = await create({ ...values });
    }

    const { code, msg } = response;
    if (code === 200) {
      message.success({ content: msg, key });
      hide();
      setsubmitting(false);
      onClose();
    } else {
      message.error({ content: msg, key });
      hide();
      setsubmitting(false);
    }

    // 返回首页
  };

  const onFinishFailed = (errorInfo) => {};

  return (
    <Modal
      title="维护资源信息"
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
      {/* <Skeleton loading={loadding}> */}
      <Form
        form={form}
        name="sysMenuCreate"
        layout="vertical"
        onFinish={onFinish}
        onFinishFailed={onFinishFailed}
        initialValues={{ parentId: 0, isFrame: '1', menuType: 'M', visible: '0', status: '0' }}
        onValuesChange={(changedValues) => {
          if (!changedValues.menuType) {
            return;
          }
          hideForm(changedValues.menuType);
        }}
      >
        <Row gutter={64}>
          <Col span={8}>
            <FormItem label="上级菜单" name="parentId">
              <TreeSelect
                style={{ width: '100%' }}
                dropdownStyle={{ maxHeight: 400, overflow: 'auto' }}
                treeData={parentMenu}
                placeholder="上级菜单"
              />
            </FormItem>
          </Col>
          {/* {visibleF.icon && (
              <Col span={12}>
                <FormItem {...formItemLayout} label="图标" name="icon">
                  <Input placeholder="请输入" />
                </FormItem>
              </Col>
            )} */}
          <Col span={8}>
            <FormItem
              label="菜单名称"
              name="menuName"
              rules={[
                {
                  required: true,
                  message: '菜单名称不能为空！',
                },
              ]}
            >
              <Input placeholder="请输入" />
            </FormItem>
          </Col>
          <Col span={8}>
            <FormItem label="排序" name="orderNum">
              <InputNumber placeholder="请输入" style={{ width: '100%' }} />
            </FormItem>
          </Col>
          {visibleF.path && (
            <Col span={8}>
              <FormItem label="路由地址" name="path">
                <Input placeholder="请输入" />
              </FormItem>
            </Col>
          )}
          {visibleF.component && (
            <Col span={8}>
              <FormItem label="组件路径" name="pathCom">
                <Input placeholder="请输入" />
              </FormItem>
            </Col>
          )}
          {visibleF.perms && (
            <Col span={8}>
              <FormItem label="权限标识" name="perms">
                <Input placeholder="请输入" />
              </FormItem>
            </Col>
          )}
          <Col span={8}>
            <FormItem
              label="菜单类型"
              name="menuType"
              rules={[
                {
                  required: true,
                  message: '菜单类型不能为空！',
                },
              ]}
            >
              <Radio.Group>
                <Radio value="M">目录</Radio>
                <Radio value="C">菜单</Radio>
                <Radio value="F">按钮</Radio>
              </Radio.Group>
            </FormItem>
          </Col>
          {visibleF.visible && (
            <Col span={8}>
              <FormItem
                label="显示状态"
                name="visible"
                rules={[
                  {
                    required: true,
                    message: '显示状态不能为空！',
                  },
                ]}
              >
                <Radio.Group>
                  <Radio value="0">显示</Radio>
                  <Radio value="1">隐藏</Radio>
                </Radio.Group>
              </FormItem>
            </Col>
          )}
          {visibleF.status && (
            <Col span={8}>
              <FormItem
                label="菜单状态"
                name="status"
                rules={[
                  {
                    required: true,
                    message: '菜单状态不能为空！',
                  },
                ]}
              >
                <Radio.Group>
                  <Radio value="0">正常</Radio>
                  <Radio value="1">停用</Radio>
                </Radio.Group>
              </FormItem>
            </Col>
          )}
          {visibleF.isFrame && (
            <Col span={8}>
              <FormItem
                label="是否外链"
                name="isFrame"
                rules={[
                  {
                    required: true,
                    message: '访问类型不能为空！',
                  },
                ]}
              >
                <Radio.Group>
                  <Radio value="0">是</Radio>
                  <Radio value="1">否</Radio>
                </Radio.Group>
              </FormItem>
            </Col>
          )}
        </Row>
      </Form>
      {/* </Skeleton> */}
    </Modal>
  );
};

export default MenuForm;
