import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query, getNickName } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'notice',
  state: { treeData: [], nickList: [] },
  subscriptions: {},
  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(query, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },
    *getNickName({ payload }, { call, put }) {
      const response = yield call(getNickName, payload);
      yield put({
        type: 'updateState',
        payload: { nickList: response },
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
