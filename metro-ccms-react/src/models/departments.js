import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { getTree } from '../services/departments';

export default dvaModelExtend(pageModel, {
  namespace: 'departTree',
  state: { departData: []},
  subscriptions: {},
  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(getTree, payload);
      yield put({
        type: 'updateState',
        payload: { departData: response.data },
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
