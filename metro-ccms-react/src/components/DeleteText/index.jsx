import React from 'react';
import { Modal } from 'antd';
import { ExclamationCircleOutlined } from '@ant-design/icons';

const { confirm } = Modal;

const DeleteText = ({ deleteFunc, text = '删除' }) => {
  const deleteMsg = () => {
    confirm({
      title: '确定删除此条数据吗？',
      icon: <ExclamationCircleOutlined />,
      onOk() {
        deleteFunc();
      },
      onCancel() {},
    });
  };

  return <a onClick={deleteMsg}>{text}</a>;
};

export default DeleteText;
