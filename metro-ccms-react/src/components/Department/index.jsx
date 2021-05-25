/* eslint-disable @typescript-eslint/no-shadow */
/* eslint-disable array-callback-return */
import React, { useCallback, useEffect } from 'react';
import { TreeSelect, Spin } from 'antd';
import { connect } from 'umi';
import './index.less';

const { SHOW_PARENT } = TreeSelect;
const valueMap = {};

// ifmultiple 是否多选
const DepartmentTree = ({
  dispatch,
  loading,
  placeholder,
  value,
  onChange,
  ifmultiple,
  departTree: { departData },
}) => {
  // 获取接口数据
  const getDepartment = useCallback(
    (param = {}) => {
      dispatch({
        type: 'departTree/query',
        payload: param,
      });
    },
    [dispatch],
  );

  useEffect(() => {
    getDepartment();
  }, [getDepartment]);

  // 遍历数据格式
  const loopDeptItem = (depts) =>
    depts.map(({ label, children, deptCode }) => {
      const tmp = {
        title: label,
        value: deptCode,
        children: children && loopDeptItem(children),
      };
      valueMap[deptCode] = tmp;
      return tmp;
    });

  const getDepartValue = () => {
    const ifObj = Object.keys(departData);
    if (ifObj.length === 0) {
      return false;
    }

    return loopDeptItem(departData);
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

  const changeValue = (data) => {
    const numberValue = [];
    (data || []).forEach((item) => {
      numberValue.push(item.toString());
    });
    return numberValue;
  };

  const tProps = {
    allowClear: true,
    treeData: getDepartValue(),
    value: changeValue(value) || [],
    maxTagCount: 2,
    treeCheckable: !ifmultiple,
    style: {
      width: '100%',
    },
    treeNodeFilterProp: 'title',
    showCheckedStrategy: SHOW_PARENT,
    dropdownClassName: 'dropdownStyle',
    placeholder: placeholder || '选择部门',

    onChange: (value) => {
      if (ifmultiple) {
        onChange(value);
      } else {
        onChange(getChildren(value));
      }
    },
  };

  return (
    <Spin spinning={loading}>
      <TreeSelect {...tProps} />
    </Spin>
  );
};

export default connect(({ departTree, loading }) => ({
  departTree,
  loading: loading.effects['departTree/query'],
}))(DepartmentTree);
