import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'role',
  state: { treeData: [] },
  subscriptions: {},
  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(query, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },
    // *queryTree({ payload }, { call, put }) {
    //   const response = yield call(tree, payload);
    //   if (response.code === 200) {
    //     yield put({
    //       type: 'updateState',
    //       payload: { treeData: [response.data] },
    //     });
    //   }
    // },
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
