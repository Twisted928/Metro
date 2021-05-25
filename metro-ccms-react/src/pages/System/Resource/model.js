import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';

export default dvaModelExtend(pageModel, {
  namespace: 'resource',
  state: { treeList: [] },
  subscriptions: {},
  effects: {},
  reducers: {
    save(state, action) {
      return {
        ...state,
        ...action.payload,
      };
    },
  },
});
