/* eslint-disable @typescript-eslint/no-shadow */
import React, { useState, useEffect, useCallback } from 'react';
import { Modal } from 'antd';
import moment from 'moment';
import ProTable from '@ant-design/pro-table';
import { connect } from 'umi';

const dateFormat = 'YYYY-MM-DD';

const ChooseCusModal = ({
  dispatch,
  loadingtodo,
  userId,
  roleAuth: { queryById, modalPagination },
  roidName,
  roidId,
  basicMsg,
  vis,
  onCancel,
  setdocument,
}) => {
  const [rows, setRows] = useState([]);
  const handleCancel = () => {
    onCancel(false);
  };

  const handleOk = () => {
    const chooseList = rows;
    const newArr = [];
    const {
      // userId: { label, value },
      userId,
      time,
    } = basicMsg;
    for (let i = 0; i < chooseList.length; i += 1) {
      for (let j = 0; j < userId.length; j += 1) {
        const obj = {
          receiveRoleName: roidName,
          autherRoleNa: chooseList[i].roleName,
          autherRole: chooseList[i].roleId,
          receiveRoleId: roidId,
          receiveUser: userId[j].label,
          receiveId: userId[j].value,
          applicationNo: chooseList[i].businessId,
          beginTime: moment(time[0]).format(dateFormat),
          endTime: moment(time[1]).format(dateFormat),
        };
        newArr.push(obj);
      }
    }
    setdocument(newArr);
    handleCancel();
  };

  const query = useCallback(() => {
    if (!vis) {
      return;
    }
    dispatch({
      type: 'roleAuth/queryById',
      payload: userId,
    });
  }, [dispatch, userId, vis]);

  useEffect(() => {
    query();
  }, [query]);

  const columns = [
    {
      title: '角色ID',
      dataIndex: 'roleId',
    },
    {
      title: '角色名称',
      dataIndex: 'roleName',
    },
  ];

  const rowSelection = {
    onChange: (_, selectedRows) => {
      setRows(selectedRows);
    },
  };

  return (
    <Modal
      destroyOnClose
      title="选择角色"
      width={1000}
      visible={vis}
      onOk={handleOk}
      onCancel={handleCancel}
    >
      <ProTable
        headerTitle="查询列表"
        rowKey="roleId"
        onSubmit={(values) => {
          query({ ...values });
        }}
        onChange={(page) => {
          query({ ...page });
        }}
        dataSource={queryById}
        tableAlertRender={false}
        pagination={modalPagination}
        rowSelection={rowSelection}
        loading={loadingtodo}
        options={false}
        toolBarRender={false}
        columns={columns}
        search={false}
        scroll={{ y: 400 }}
      />
    </Modal>
  );
};

export default connect(({ roleAuth, loading }) => ({
  roleAuth,
  loadingtodo: loading.effects['roleAuth/queryById'],
}))(ChooseCusModal);
