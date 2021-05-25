/* eslint-disable consistent-return */
import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query, catorQuery } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'evaluateModel',
  state: {
    catorList: [],
    catorBasic: {},
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
    *catorQuery({ payload }, { call, put }) {
      const response = yield call(catorQuery, payload);
      yield put({
        type: 'updateState',
        payload: { catorList: response.data.list, catorBasic: response.data.modelInfoDO },
      });
      return response.data;
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
