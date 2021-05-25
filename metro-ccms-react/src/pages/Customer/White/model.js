import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query, queryList, queryCust } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'whitelist',

  state:{ custlist: [] },

  effects: {
    * query({ payload }, { call, put }) {
      const params = { ...payload }
      const response = yield call(query, params);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },
    
    * queryList({ payload }, { call, put }) {
      const response = yield call(queryList, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },

    * queryCust({ payload }, { call, put }) {
      const response = yield call(queryCust, payload);
      yield put({
        type: 'updateState',
        payload: { custlist: response.rows },
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
