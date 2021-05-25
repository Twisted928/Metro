/* eslint-disable consistent-return */
import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { message } from 'antd';
import { query, insuredQuery, comUpload, getFile, downloadFile } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'lossmanagement',
  state: {
    cusData: [],
    modalPagination: {
      showSizeChanger: true,
      showQuickJumper: false,
      showTotal: (total) => `共计 ${total} 条数据`,
      current: 1,
      pageSize: 10,
      total: 0,
    },
  },
  subscriptions: {},
  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(query, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },

    *insuredQuery({ payload }, { call, put }) {
      const response = yield call(insuredQuery, payload);
      yield put({
        type: 'cusModal',
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

    *getFile({ payload }, { call, put }) {
      const response = yield call(getFile, payload);
      yield put({
        type: 'updateState',
        payload: { fileData: response },
      });
    },

    *downloadFile({ payload }, { call, put }) {
      const response = yield call(downloadFile, payload);
      console.log(response);
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
    cusModal(state, { payload }) {
      const { response } = payload;
      const { code, rows, total, pageSize, current } = response;
      if (code !== 200) {
        message.error(response.message);
        return {
          ...state,
        };
      }
      return {
        ...state,
        cusData: rows,
        modalPagination: {
          ...state.modalPagination,
          current: current || 1,
          pageSize: pageSize || 10,
          total: total || 0,
        },
      };
    },
  },
});
