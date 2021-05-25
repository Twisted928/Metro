/* eslint-disable no-undef */
import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { backlogFetch, getHistoryList, list } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'account',
  state: {
    // 待办
    initiateList: [],
    initiatePagination: {
      showSizeChanger: true,
      showQuickJumper: false,
      showTotal: (total) => `共计 ${total} 条数据`,
      current: 1,
      pageSize: 10,
      total: 0,
    },
    // 已办
    warningVList: [],
    warningVPagination: {
      showSizeChanger: true,
      showQuickJumper: false,
      showTotal: (total) => `共计 ${total} 条数据`,
      current: 1,
      pageSize: 10,
      total: 0,
    },
    // 公告
    yqWarningList: [],
    yqWarningPagination: {
      showSizeChanger: true,
      showQuickJumper: false,
      showTotal: (total) => `共计 ${total} 条数据`,
      current: 1,
      pageSize: 10,
      total: 0,
    },
  },
  subscriptions: {},
  effects: {
    *backlogFetch({ payload }, { call, put }) {
      const response = yield call(backlogFetch, payload);
      if (response) {
        yield put({
          type: 'queryBacklog',
          payload: {
            ...response,
          },
        });
      }
    },
    *getHistoryRe({ payload }, { call, put }) {
      const response = yield call(getHistoryList, payload);
      if (response) {
        yield put({
          type: 'getHistoryListText',
          payload: {
            ...response,
          },
        });
      }
    },
    *list({ payload }, { call, put }) {
      const response = yield call(list, payload);
      if (response) {
        yield put({
          type: 'noticeList',
          payload: {
            ...response,
          },
        });
      }
    },
  },
  reducers: {
    // 公告
    noticeList(state, { payload }) {
      const { rows, current, pageSize, total } = payload;
      return {
        ...state,
        yqWarningList: rows,
        yqWarningPagination: {
          ...state.yqWarningPagination,
          current,
          total,
          pageSize,
        },
      };
    },
    // 待办
    queryBacklog(state, { payload }) {
      const { rows, current, pageSize, total } = payload;
      return {
        ...state,
        initiateList: rows,
        initiatePagination: {
          ...state.initiatePagination,
          current,
          total,
          pageSize,
        },
      };
    },
    // 已办
    getHistoryListText(state, { payload }) {
      const { rows, current, pageSize, total } = payload;
      return {
        ...state,
        warningVList: rows,
        warningVPagination: {
          ...state.warningVPagination,
          current,
          total,
          pageSize,
        },
      };
    },
  },
});
