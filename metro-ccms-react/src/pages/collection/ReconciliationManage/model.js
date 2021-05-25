/* eslint-disable consistent-return */
import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query, detailsList, selRange } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'reconciliationManage',
  state: {
    taizhangList: [],
    cuishouList: [],
    upfileList: [],
    addModalList: [],
    modalPagination: {
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
    *query({ payload }, { call, put }) {
      const response = yield call(query, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },
    *detailsList({ payload }, { call, put }) {
      const response = yield call(detailsList, payload);
      yield put({
        type: 'querySuccess1',
        payload: { response, filter: payload },
      });
      return response;
    },

    *selRange({ payload }, { call, put }) {
      const response = yield call(selRange, payload);
      yield put({
        type: 'addModalReduce',
        payload: { response, filter: payload },
      });
    },
  },
  reducers: {
    save(state, action) {
      return {
        ...state,
        ...action.payload,
      };
    },
    addModalReduce(state, { payload }) {
      const { response } = payload;
      const { code, rows, total, pageSize, current } = response;
      if (code !== 200) {
        message.error(response.message);
        return {
          ...state,
        };
      }
      return {
        ...state,
        addModalList: rows,
        modalPagination: {
          ...state.modalPagination,
          current: current || 1,
          pageSize: pageSize || 10,
          total: total || 0,
        },
      };
    },
  },
});
