import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { queryList, getBusinessList, historyList } from './processService';

export default dvaModelExtend(pageModel, {
  namespace: 'actModal',
  state: { treeData: [], getBusinesslist: [], historydata:[] },
  subscriptions: {},
  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(queryList, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },
    *getBusiness({ payload }, { call, put }) {
      const response = yield call(getBusinessList, payload);
      yield put({
        type: 'updateState',
        payload: { getBusinesslist: response },
      });
    },
    *historyList({ payload }, { call, put }) {
      const response = yield call(historyList, payload);
      yield put({
        type: 'updateState',
        payload: { historydata: response },
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
