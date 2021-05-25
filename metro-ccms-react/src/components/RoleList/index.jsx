/* eslint-disable react-hooks/exhaustive-deps */
import React, { useEffect, useState } from 'react';
import { message, Select, Spin } from 'antd';
import { request } from 'umi';

const { Option } = Select;

// 角色下拉框组件;
const RoleList = ({ userId, Single, value, onChange, onSelect, disabled }) => {
  const [submitting, setSubmitting] = useState(false);
  const [roleList, setRoleList] = useState([]);
  const getRole = async () => {
    setSubmitting(true);
    const url = userId ? `/system/user/${userId}` : '/system/user/';
    const res = await request(url);
    if (res.code === 200) {
      setRoleList(res.roles);
    } else {
      message.error(res.msg);
    }
    setSubmitting(false);
  };
  const changeValue = (data) => {
    const numberValue = [];
    (data || []).forEach((item) => {
      numberValue.push(+item);
    });
    return numberValue;
  };

  useEffect(() => {
    getRole();
  }, []);

  return (
    <Spin spinning={submitting}>
      <Select
        // allowClear
        maxTagCount={2}
        value={Single ? value : changeValue(value)}
        mode={Single ? '' : 'multiple'}
        optionFilterProp="label"
        style={{ width: '100%' }}
        onChange={onChange}
        onSelect={onSelect}
        disabled={disabled}
      >
        {roleList?.map((item) => (
          <Option value={item.roleId} key={item.roleId}>
            {item.roleName}
          </Option>
        ))}
      </Select>
    </Spin>
  );
};

export default RoleList;
