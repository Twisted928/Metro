/* eslint-disable react-hooks/exhaustive-deps */
import { Row, Col, Tabs, message, Tag, Divider, Pagination, Skeleton, Modal } from 'antd';
import React, { Fragment, useEffect, useState, useCallback } from 'react';
import { query } from './service';
import moment from 'moment';
import { connect, history } from 'umi';
import styles from './Center.less';
import userman from '../../assets/user_man.jpg';

const { TabPane } = Tabs;
const UserComponent = ({
  dispatch,
  loadingNotice,
  loadingbacklogFetch,
  loadinggetHistoryList,
  account: {
    initiateList,
    initiatePagination,
    warningVList,
    warningVPagination,
    yqWarningList,
    yqWarningPagination,
  },
}) => {
  const [deptsArr, setDepts] = useState([]);
  const [visible, setVisible] = useState(false);
  const [details, setDetails] = useState({});
  const [loading, setLoading] = useState(false);
  const [rolesArr, setRoles] = useState([]);
  const [emailcode, setEmail] = useState('');
  const [phone, setPhonenumber] = useState('');
  const [name, setNickName] = useState('');
  const [styHei, setHeight] = useState(null);
  const [style, setStyle] = useState({
    height: '685px',
    overflowY: 'hidden',
  });

  const queryList = useCallback(async () => {
    setLoading(true);
    const res = await query();
    const {
      code,
      msg,
      data: { depts, roles, email, phonenumber, nickName },
    } = res;
    if (code === 200) {
      setDepts(depts);
      setRoles(roles);
      setEmail(email);
      setPhonenumber(phonenumber);
      setNickName(nickName);
      setLoading(false);
    }
    if (code === 500) {
      message.error(msg);
      setLoading(false);
    }
  }, []);

  const noticeList = (param = {}) => {
    dispatch({
      type: 'account/list',
      payload: param,
    });
  };

  const backlogList = (param = {}) => {
    dispatch({
      type: 'account/backlogFetch',
      payload: param,
    });
  };

  const getHistory = (param = {}) => {
    dispatch({
      type: 'account/getHistoryRe',
      payload: param,
    });
  };

  const alllist = () => {
    backlogList();
    getHistory();
    noticeList();
  };

  // 判断点击哪一个Tabs
  const getList = (key = null) => {
    const newMap = new Map([
      ['1', backlogList],
      ['2', getHistory],
      ['3', noticeList],
      [null, alllist],
    ]);
    newMap.get(key)();
  };

  useEffect(() => {
    queryList();
    const styleHei = document.getElementById('rightquata').clientHeight;
    setHeight(`${styleHei}px`);
  }, [queryList]);

  useEffect(() => {
    getList();
  }, []);

  const checkMore = (data) => {
    setVisible(true);
    setDetails(data);
  };

  const colMouseOver = () => {
    const newStyle = {
      ...style,
      overflowY: 'scroll',
    };
    setStyle(newStyle);
  };

  const colMouseLeave = () => {
    const newStyle = {
      ...style,
      overflowY: 'hidden',
    };
    setStyle(newStyle);
  };

  const tabsChange = (key) => {
    getList(key);
  };

  const oncancel = () => {
    setVisible(false);
  };

  const updataPass = () => {
    history.push('/account/updataPassword');
  };

  return (
    <Fragment>
      <Row gutter={24}>
        <Col lg={6} md={24} onMouseOver={colMouseOver} onMouseLeave={colMouseLeave}>
          <div className={styles.leftQuota} style={{ height: styHei }}>
            <div className={styles.avatarImg}>
              <img src={userman} alt="" />
              <div>{name}</div>
            </div>
            <div className={styles.modefiyPass} onClick={updataPass}>
              修改密码
            </div>
            <Skeleton loading={loading}>
              <div className={styles.adminqus}>
                <p className={styles.adminMsg}>
                  <span></span>
                  <span>账号信息</span>
                </p>
                <p className={styles.otherAdminMsg}>
                  <span>电话：</span>
                  <span>{phone}</span>
                </p>
                <p className={styles.otherAdminMsg}>
                  <span>邮箱：</span>
                  <span>{emailcode}</span>
                </p>
              </div>
              <Divider />
              <div className={styles.adminqus}>
                <p className={styles.adminMsg}>
                  <span></span>
                  <span>角色信息</span>
                </p>
                <p className={styles.otherTag}>
                  {(rolesArr ?? []).map((item) => {
                    return <Tag key={item.roleId}>{item.roleName}</Tag>;
                  })}
                </p>
              </div>
              <Divider />
              <div className={styles.adminqus}>
                <p className={styles.adminMsg}>
                  <span></span>
                  <span>部门信息</span>
                </p>
                <p style={style} className={styles.otherTag}>
                  {(deptsArr ?? []).map((item) => {
                    return <Tag key={item.deptId}>{item.deptName}</Tag>;
                  })}
                </p>
              </div>
            </Skeleton>
          </div>
        </Col>
        <Col lg={18} md={24}>
          <div className={styles.rightQuota} id="rightquata">
            <Tabs defaultActiveKey="1" style={{ marginBottom: 32 }} onChange={tabsChange}>
              <TabPane tab={`待办(${initiateList.length})`} key="1">
                <Skeleton loading={loadingbacklogFetch}>
                  <div className={styles.listMsg}>
                    {(initiateList ?? []).map((item) => {
                      return (
                        <div key={item.businessId} className={styles.theowonlist}>
                          <p className={styles.listTitle}>
                            <span>{item.title}</span>
                            <span>{moment(item.createTime).format('YYYY-MM-DD hh:mm:ss')}</span>
                          </p>
                          <p className={styles.listCardMsg}>
                            <span>单据号:</span>
                            <span>{item.businessId}</span>
                            <span>门店名称:</span>
                            <span>高贻z的麦德隆门店</span>
                            <span>卡号名称:</span>
                            <span>{item.businessName}</span>
                          </p>
                          <Divider />
                        </div>
                      );
                    })}
                    {initiateList.length > 0 ? (
                      <Pagination
                        onChange={(page, pageSize) => {
                          backlogList({ current: page, pageSize });
                        }}
                        {...initiatePagination}
                      />
                    ) : (
                      '暂无数据'
                    )}
                  </div>
                </Skeleton>
              </TabPane>
              <TabPane tab={`已办(${(warningVList ?? []).length})`} key="2">
                <Skeleton loading={loadinggetHistoryList}>
                  <div className={styles.listMsg}>
                    {(warningVList ?? []).map((item) => {
                      return (
                        <div key={item.roleId} className={styles.theowonlist}>
                          <p className={styles.listTitle}>
                            <span>{item.title}</span>
                            <span>{moment(item.createTime).format('YYYY-MM-DD hh:mm:ss')}</span>
                          </p>
                          <p className={styles.listCardMsg}>
                            <span>单据号:</span>
                            <span>{item.businessId}</span>
                            <span>门店名称:</span>
                            <span>高贻z的麦德隆门店</span>
                            <span>卡号名称:</span>
                            <span>{item.businessName}</span>
                          </p>
                          <Divider />
                        </div>
                      );
                    })}
                    {(warningVList ?? []).length > 0 ? (
                      <Pagination
                        onChange={(page, pageSize) => {
                          getHistory({ current: page, pageSize });
                        }}
                        {...warningVPagination}
                      />
                    ) : (
                      '暂无数据'
                    )}
                  </div>
                </Skeleton>
              </TabPane>
              <TabPane tab={`公告(${yqWarningList.length})`} key="3">
                <Skeleton loading={loadingNotice}>
                  <div className={styles.listMsg}>
                    {(yqWarningList ?? []).map((item) => {
                      return (
                        <div key={item.noticeId} className={styles.theowonlist}>
                          <p className={styles.listTitle}>
                            <span>{item.noticeTitle}</span>
                            <span>{item.createTime}</span>
                          </p>
                          <p className={`${styles.listCardMsg} ${styles.noticeCardMsg}`}>
                            {/* <span>公告内容:</span>
                            <span className="spanConcat">{item.noticeContent}</span> */}
                            <span
                              onClick={() => {
                                checkMore(item);
                              }}
                              style={{ color: '#4c40ff', cursor: 'pointer' }}
                            >
                              更多
                            </span>
                          </p>
                          <Divider />
                        </div>
                      );
                    })}
                    {yqWarningList.length > 0 ? (
                      <Pagination
                        onChange={(page, pageSize) => {
                          noticeList({ current: page, pageSize });
                        }}
                        {...yqWarningPagination}
                      />
                    ) : (
                      '暂无数据'
                    )}
                  </div>
                </Skeleton>
              </TabPane>
            </Tabs>
          </div>
        </Col>
      </Row>
      <Modal
        className={styles.modalbody}
        width={1000}
        title="详情"
        visible={visible}
        onCancel={oncancel}
        footer={null}
      >
        <span className={styles.basicMsg}></span>
        <span className={styles.detailsTitle}>{details.noticeTitle}</span>
        <div className={styles.createAndtile}>
          <span>创建人:</span>
          <span>{details.createdBy}</span>
          <span style={{ marginLeft: '16px' }}>创建时间:</span>
          <span>{details.createTime}</span>
        </div>
        <div
          className={styles.content}
          dangerouslySetInnerHTML={{ __html: details.noticeContent }}
        ></div>
      </Modal>
    </Fragment>
  );
};

export default connect(({ account, loading }) => ({
  account,
  loadingNotice: loading.effects['account/list'],
  loadingbacklogFetch: loading.effects['account/backlogFetch'],
  loadinggetHistoryList: loading.effects['account/getHistoryRe'],
}))(UserComponent);
