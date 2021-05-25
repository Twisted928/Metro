import React from 'react';
import { Modal, Button } from 'antd';
import { ExclamationCircleOutlined } from '@ant-design/icons';

const { confirm } = Modal;

const DeleteText = ({ deleteFunc, disabled, loading, styleLess }) => {
  const deleteMsg = () => {
    confirm({
      title: '确定删除选中的数据吗？',
      icon: <ExclamationCircleOutlined />,
      onOk() {
        deleteFunc();
      },
      onCancel() {},
    });
  };

  return (
    <Button
      style={{ marginLeft: styleLess ? '-55px' : '' }}
      disabled={disabled}
      key="del"
      loading={loading}
      type="primary"
      onClick={deleteMsg}
    >
      删除
    </Button>
  );
};

export default DeleteText;
