import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query, details, boundary } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'safeguardCompnay',
  state: { treeData: [], detailslist: [], boundaryData: [] },
  subscriptions: {},
  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(query, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },

    *details({ payload }, { call, put }) {
      const response = yield call(details, payload);
      yield put({
        type: 'updateState',
        payload: { detailslist: response.rows },
      });
    },

    *boundary({ payload }, { call, put }) {
      const response = yield call(boundary, payload);
      yield put({
        type: 'updateState',
        payload: { boundaryData: response.data },
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
