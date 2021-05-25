import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';

export default dvaModelExtend(pageModel, {
  namespace: 'blacklist',
  
  subscriptions: {},

  effects: {
    *updateFilter({ payload }, { put }) {
      yield put({
        type: 'updateState',
        payload: { filter: payload },
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
