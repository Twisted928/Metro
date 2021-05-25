/* eslint-disable react-hooks/exhaustive-deps */
/* eslint-disable @typescript-eslint/no-unused-vars */
import { Modal, Descriptions } from 'antd';
import React, { useEffect, useState } from 'react';
import { connect } from 'umi';

const PeopleMsg = ({ dispatch, onCancel, vis, depAndRole, notice: { nickList } }) => {
  const [submitting, setsubmitting] = useState(false);
  const [content, setContent] = useState('');

  const query = () => {
    if (!depAndRole) {
      return;
    }
    const params = {
      depts: depAndRole.deptIds,
      roles: depAndRole.roleIds,
    };
    dispatch({
      type: 'notice/getNickName',
      payload: params,
    });
  };

  useEffect(() => {
    query();
  }, [depAndRole]);

  return (
    <Modal
      visible={vis}
      width={1200}
      title="公告人员信息"
      onCancel={() => {
        onCancel(false);
      }}
    >
      <div style={{ height: '500px', overflowY: 'scroll' }}>
        {nickList.length === 0 ? (
          '暂无人员信息'
        ) : (
          <Descriptions title="" bordered>
            {(nickList || []).map((item) => {
              return <Descriptions.Item label={item}></Descriptions.Item>;
            })}
          </Descriptions>
        )}
      </div>
    </Modal>
  );
};

export default connect(({ notice, loading }) => ({
  notice,
  loadingNotice: loading.effects['notice/getNickName'],
}))(PeopleMsg);
