import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'user',
  state: {},
  subscriptions: {},
  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(query, payload);
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
