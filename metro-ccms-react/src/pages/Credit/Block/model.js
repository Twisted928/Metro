import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query, queryById } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'creditblock',

  effects: {
    * query({ payload }, { call, put }) {
      // eslint-disable-next-line no-console
      console.log('Query In Model', payload);

      const response = yield call(query, payload);

      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },

    * queryById({ payload }, { call, put }) {
      // eslint-disable-next-line no-console
      console.log('In Model', payload);

      // const params = {
      //   CUST_CODE: payload.CUST_CODE
      // }

      const response = yield call(queryById, payload);

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
