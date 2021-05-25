/* eslint-disable consistent-return */
import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { query, comUpload, downloadFile } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'groupQuotaAllocation',
  state: { treeData: [], detailslist: []},
  subscriptions: {},
  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(query, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },

    *upload({ payload }, { call, put }) {
      const { type, file, id } = payload;
      const fromdata = new FormData();
      fromdata.append('id', id);
      fromdata.append('type', type);
      fromdata.append('file', file);
      const response = yield call(comUpload, fromdata);
      const { code } = response;
      if (code === 200) {
        yield put({
          type: 'updateState',
          payload: {},
        });
        return response;
      }
    },

    *downloadFile({ payload }, { call, put }) {
      const response = yield call(downloadFile, payload);
      yield put({
        type: 'updateState',
        payload: { fileData: response },
      });
      return response;
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
