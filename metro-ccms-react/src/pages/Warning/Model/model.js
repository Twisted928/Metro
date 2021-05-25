import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { message } from 'antd';
import {
  query,
  queryDetails,
  modelInQuery,
  listkehu,
  getEwModelRecord,
  queryById,
} from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'warningmodel',

  state: {
    modelInlist: [],
    custList: [],
    detailsFilter: {},
    detailsList: [],
    basciData: [],
    getEwModelRecord: [],
    detailsPagination: {
      showSizeChanger: true,
      showQuickJumper: false,
      showTotal: (total) => `共计 ${total} 条数据`,
      current: 1,
      pageSize: 10,
      total: 0,
    },
  },

  effects: {
    *query({ payload }, { call, put }) {
      const response = yield call(query, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },

    *modelInQuery({ payload }, { call, put }) {
      const response = yield call(modelInQuery, payload);
      yield put({
        type: 'updateState',
        payload: { modelInlist: response.data },
      });
    },

    *queryById({ payload }, { call, put }) {
      const response = yield call(queryById, payload);
      yield put({
        type: 'updateState',
        payload: { basciData: response.roles },
      });
    },

    *statusList({ payload }, { call, put }) {
      const response = yield call(getEwModelRecord, payload);
      yield put({
        type: 'updateState',
        payload: { getEwModelRecord: response },
      });
    },

    *listkehu({ payload }, { call, put }) {
      const response = yield call(listkehu, payload);
      yield put({
        type: 'updateState',
        payload: { custList: response.data },
      });
    },

    *queryDetails({ payload }, { call, put }) {
      const params = { ...payload };
      const response = yield call(queryDetails, params);
      yield put({
        type: 'queryDetail',
        payload: { response, detailsFilter: payload },
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

    queryDetail(state, { payload }) {
      const { response, detailsFilter } = payload;
      const pstate = {
        ...state,
        detailsFilter,
      };

      if (response.code !== 200) {
        message.error(response.msg);
        return {
          ...pstate,
          detailsList: [],
          detailsPagination: {},
        };
      }

      const { data, current, pageSize, total } = response;

      return {
        ...pstate,
        detailsList: data || [],
        detailsPagination: {
          ...state.detailsPagination,
          current: current || 1,
          total: total || 0,
          pageSize: pageSize || 10,
        },
      };
    },
  },
});
