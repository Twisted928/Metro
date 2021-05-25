/* eslint-disable consistent-return */
import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query, listPolicy } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'companies',
  state: { treeData: [], listPolicy: [] },
  subscriptions: {},
  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(query, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },
    *listPolicy({ payload }, { call, put }) {
      const response = yield call(listPolicy, payload);
      yield put({
        type: 'updateState',
        payload: { listPolicy: response.data },
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
