import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'creditTerms',
  
  effects: {
    * query({ payload }, { call, put }) {
      const params  = { ...payload }
      const response = yield call(query, params);
      yield put({
        type: 'querySuccess',
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
  },
});
