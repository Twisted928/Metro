import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query, queryHistory } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'sleepcust',

  effects: {
    * query({ payload }, { call, put }) {
      const response = yield call(query, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },
    
    * queryHistory({ payload }, { call }) {
      const response = yield call(queryHistory, payload);
      return response
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
