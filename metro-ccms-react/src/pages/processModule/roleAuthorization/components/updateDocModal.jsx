import React, { useState, useEffect, useCallback } from 'react';
import { Modal, Button, message } from 'antd';
import ProTable from '@ant-design/pro-table';
import { connect } from 'umi';
import DeleteBtu from '@/components/DeleteBtu';
import { updateAutherList } from '../services';

const UpdateDocModal = ({
  dispatch,
  loadingList,
  roleAuth: { getAutherDoList },
  updateVis,
  mainId,
  onCancel,
  queryLoad,
  details,
}) => {
  const [rows, setRows] = useState([]);
  const [rowsKeys, setRowsKeys] = useState([]);
  const [doList, setDoList] = useState([]);
  const [submitLoaing, setSubmitLoaing] = useState(false);
  const [ifDelete, setIfDelete] = useState(true);

  const handleCancel = () => {
    onCancel(false);
    setRows([]);
    setRowsKeys([]);
    setIfDelete(true);
  };

  const handleOk = async () => {
    const response = await updateAutherList({ list: rows });
    setSubmitLoaing(true);
    const { code, msg } = response;
    if (code === 200) {
      handleCancel();
      queryLoad();
      setSubmitLoaing(false);
    }
    if (code === 500) {
      message.error(msg);
      setSubmitLoaing(false);
    }
  };

  const query = useCallback(() => {
    if (!mainId) {
      return;
    }
    dispatch({
      type: 'roleAuth/getAutherDoList',
      payload: { mainId },
    }).then((res) => {
      setDoList(res);
    });
  }, [dispatch, mainId]);

  useEffect(() => {
    query();
  }, [query]);

  const deleteList = () => {
    const list = getAutherDoList;
    const checkeys = rowsKeys;
    const newArr = [];
    for (let i = 0; i < checkeys.length; i += 1) {
      for (let j = 0; j < list.length; j += 1) {
        if (list[j].id === checkeys[i]) {
          list.splice(j, 1);
          j -= 1;
        }
      }
    }
    newArr.push(...list);
    setDoList(newArr);
    setIfDelete(false);
  };

  const columns = [
    {
      title: '授权人',
      dataIndex: 'autherUser',
      width: 130,
      ellipsis: true,
    },
    {
      title: '授权角色',
      dataIndex: 'autherRoleName',
      width: 200,
      ellipsis: true,
    },
    {
      title: '生效时间',
      width: 120,
      dataIndex: 'beginTime',
      valueType: 'date',
    },
    {
      title: '失效时间',
      width: 120,
      dataIndex: 'endTime',
      valueType: 'date',
    },
  ];

  const rowSelection = {
    onChange: (selectedRowsKeys, selectedRows) => {
      setRows(selectedRows);
      setRowsKeys(selectedRowsKeys);
    },
    getCheckboxProps: () => {
      if (details) {
        return { disabled: true };
      }
      return null;
    },
  };

  const closeIconBtu = details ? (
    ''
  ) : (
    <DeleteBtu
      styleLess
      disabled={rows.length === 0}
      deleteFunc={() => {
        deleteList();
      }}
    />
  );

  return (
    <Modal
      destroyOnClose
      title="单据信息"
      closeIcon={details ? true : closeIconBtu}
      width={1000}
      visible={updateVis}
      footer={
        details
          ? [
              <Button
                key="back"
                onClick={() => {
                  handleCancel();
                }}
              >
                取消
              </Button>,
            ]
          : [
              <Button
                key="back"
                onClick={() => {
                  handleCancel();
                }}
              >
                取消
              </Button>,
              <Button
                type="primary"
                key="submit"
                loading={submitLoaing}
                disabled={ifDelete}
                onClick={() => {
                  handleOk();
                }}
              >
                提交
              </Button>,
            ]
      }
    >
      <ProTable
        headerTitle="查询列表"
        rowKey="id"
        onSubmit={(values) => {
          query({ ...values });
        }}
        onChange={(page) => {
          query({ ...page });
        }}
        dataSource={doList}
        tableAlertRender={false}
        pagination={false}
        rowSelection={rowSelection}
        loading={loadingList}
        options={false}
        toolBarRender={false}
        columns={columns}
        search={false}
        scroll={{ y: 600 }}
      />
    </Modal>
  );
};

export default connect(({ roleAuth, loading }) => ({
  roleAuth,
  loadingList: loading.effects['roleAuth/getAutherDoList'],
}))(UpdateDocModal);
