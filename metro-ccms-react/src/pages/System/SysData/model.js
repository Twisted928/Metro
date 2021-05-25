import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';

export default dvaModelExtend(pageModel, {
  namespace: 'sysData',
  state: { treeList: [] },
  subscriptions: {},
  effects: {
    // *query({ payload }, { call, put }) {
    //   const response = yield call(query, payload);
    //   yield put({
    //     type: 'querySuccess',
    //     payload: { response, filter: payload },
    //   });
    // },
    // *queryTree({ payload }, { call, put }) {
    //   const response = yield call(queryTree, payload);
    //   if (response.code === 200) {
    //     yield put({
    //       type: 'updateState',
    //       payload: { treeList: [response.data] },
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
