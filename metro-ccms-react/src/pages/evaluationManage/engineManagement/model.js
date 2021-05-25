/* eslint-disable consistent-return */
import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query, modelInQuery, listkehu } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'engineManagement',
  state: { treeData: [], modelInlist: [], custList: [] },
  subscriptions: {},
  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(query, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },
    *modelInQuery({ payload }, { call, put }) {
      const response = yield call(modelInQuery, payload);
      yield put({
        type: 'updateState',
        payload: { modelInlist: response.data },
      });
    },
    *listkehu({ payload }, { call, put }) {
      const response = yield call(listkehu, payload);
      yield put({
        type: 'updateState',
        payload: { custList: response.data },
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
  },
});
