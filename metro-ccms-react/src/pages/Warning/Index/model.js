import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query, queryIndex } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'warningindex',

  state:{ custlist: [] },

  effects: {
    * query({ payload }, { call, put }) {
      const response = yield call(query, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },
    
    * queryIndex({ payload }, { call, put }) {
      const response = yield call(queryIndex, payload);
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
