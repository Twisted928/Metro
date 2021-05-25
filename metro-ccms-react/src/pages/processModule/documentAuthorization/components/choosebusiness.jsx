import React, { useState, useEffect, useCallback } from 'react';
import { Modal } from 'antd';
import moment from 'moment';
import ProTable from '@ant-design/pro-table';
import { connect } from 'umi';

const dateFormat = 'YYYY-MM-DD';

const ChooseCusModal = ({
  dispatch,
  loadingtodo,
  documentAuth: { gettodoList, modalPagination },
  roidName, // 角色姓名
  roidId, // 角色ID
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
    // chooseList.forEach((item) => {
    //   const obj = item;
    //   obj.receiveRoleName = roidName;
    //   obj.receiveRoleId = roidId;
    //   obj.receiveUser = label;
    //   obj.receiveId = value;
    //   obj.applicationNo = item.businessId;
    //   obj.beginTime = moment(time[0]).format(dateFormat);
    //   obj.endTime = moment(time[1]).format(dateFormat);
    //   newArr.push(obj);
    // });
    for (let i = 0; i < chooseList.length; i += 1) {
      for (let j = 0; j < userId.length; j += 1) {
        const obj = {
          receiveRoleName: roidName,
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

  const query = useCallback(
    (param = {}) => {
      if (!vis) {
        return;
      }
      dispatch({
        type: 'documentAuth/todoList',
        payload: param,
      });
    },
    [dispatch, vis],
  );

  useEffect(() => {
    query();
  }, [query]);

  const columns = [
    {
      title: '业务单据号',
      width: '25%',
      dataIndex: 'businessId',
    },
    {
      title: '单据类型',
      width: '25%',
      dataIndex: 'type',
      render: (val) => {
        if (val === 1) {
          return '自助平台客户引入';
        }
        if (val === 2) {
          return '大商旅客户引入';
        }
        if (val === 3) {
          return '临时额度申请';
        }
        if (val === 4) {
          return '特批客户申请';
        }
        if (val === 5) {
          return '客户进降阶';
        }
        if (val === 6) {
          return '逾期风险复核';
        }
        if (val === 7) {
          return '其他风险复核';
        }
        if (val === 8) {
          return '客户等级管理';
        }
        if (val === 9) {
          return '用户系统权限申请';
        }
        return '';
      },
    },
    {
      title: '发起人',
      width: '25%',
      dataIndex: 'createdBy',
    },
    {
      title: '发起时间',
      width: '25%',
      dataIndex: 'createTime',
      render: (val) => val && moment(val).format('YYYY-MM-DD HH:mm:ss'),
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
      title="选择单据"
      width={1000}
      visible={vis}
      onOk={handleOk}
      onCancel={handleCancel}
    >
      <ProTable
        headerTitle="查询列表"
        rowKey="businessId"
        onSubmit={(values) => {
          query({ ...values });
        }}
        onChange={(page) => {
          query({ ...page });
        }}
        dataSource={gettodoList}
        tableAlertRender={false}
        pagination={modalPagination}
        rowSelection={rowSelection}
        loading={loadingtodo}
        options={false}
        toolBarRender={false}
        columns={columns}
        search={false}
        scroll={{ y: 600 }}
      />
    </Modal>
  );
};

export default connect(({ documentAuth, loading }) => ({
  documentAuth,
  loadingtodo: loading.effects['documentAuth/todoList'],
}))(ChooseCusModal);
