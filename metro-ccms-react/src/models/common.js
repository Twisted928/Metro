/* eslint-disable consistent-return */
import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import {
  getCustomerlList,
  getMemberlList,
  getCompanyList,
  getBasciData,
  comUpload,
} from '../services/commonServices';

export default dvaModelExtend(pageModel, {
  namespace: 'commonmodel',
  state: {
    customerlList: [],
    cusPagination: {
      showSizeChanger: true,
      showQuickJumper: false,
      showTotal: (total) => `共计 ${total} 条数据`,
      current: 1,
      pageSize: 10,
      total: 0,
    },
    memberlList: [],
    memPagination: {
      showSizeChanger: true,
      showQuickJumper: false,
      showTotal: (total) => `共计 ${total} 条数据`,
      current: 1,
      pageSize: 10,
      total: 0,
    },
    companyList: [],
    companyPagination: {
      showSizeChanger: true,
      showQuickJumper: false,
      showTotal: (total) => `共计 ${total} 条数据`,
      current: 1,
      pageSize: 10,
      total: 0,
    },
    basciData: [],
  },
  subscriptions: {},
  effects: {
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
    *customerlList({ payload }, { call, put }) {
      const response = yield call(getCustomerlList, payload);
      yield put({
        type: 'customerState',
        payload: { response, filter: payload },
      });
    },
    *memberlList({ payload }, { call, put }) {
      const response = yield call(getMemberlList, payload);
      yield put({
        type: 'memState',
        payload: { response, filter: payload },
      });
    },
    *companyList({ payload }, { call, put }) {
      const response = yield call(getCompanyList, payload);
      yield put({
        type: 'companyState',
        payload: { response, filter: payload },
      });
    },
    *basciData({ payload }, { call, put }) {
      const response = yield call(getBasciData, payload);
      yield put({
        type: 'updateState',
        payload: { basciData: response.data },
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
    customerState(state, { payload }) {
      const { response } = payload;
      const { rows, total, pageSize, current } = response;
      return {
        ...state,
        customerlList: rows,
        cusPagination: {
          ...state.cusPagination,
          current: current || 1,
          pageSize: pageSize || 10,
          total: total || 0,
        },
      };
    },
    memState(state, { payload }) {
      const { response } = payload;
      const { rows, total, pageSize, current } = response;
      return {
        ...state,
        memberlList: rows,
        memPagination: {
          ...state.memPagination,
          current: current || 1,
          pageSize: pageSize || 10,
          total: total || 0,
        },
      };
    },
    companyState(state, { payload }) {
      const { response } = payload;
      const { rows, total, pageSize, current } = response;
      return {
        ...state,
        companyList: rows,
        companyPagination: {
          ...state.companyPagination,
          current: current || 1,
          pageSize: pageSize || 10,
          total: total || 0,
        },
      };
    },
  },
});
