import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { message } from 'antd';
import { query, queryDetails, queryCust } from './service';

export default dvaModelExtend(pageModel, {
  namespace: 'monitorcust',

  state:{ 
    filterCust: {},
    custlist: [],
    paginationCust: {
      showSizeChanger: true,
      showQuickJumper: false,
      showTotal: (total) => `共计 ${total} 条数据`,
      current: 1,
      pageSize: 10,
      total: 0,
    },
  },

  effects: {
    * query({ payload }, { call, put }) {
      const response = yield call(query, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },
    
    * queryDetails({ payload }, { call, put }) {
      const response = yield call(queryDetails, payload);
      yield put({
        type: 'querySuccess',
        payload: { response, filter: payload },
      });
    },
    
    * queryCust({ payload }, { call, put }) {
      const response = yield call(queryCust, payload);
      yield put({
        type: 'queryCustSuccess',
        payload: { response, filterCust: payload },
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

    queryCustSuccess(state, { payload }) {
      const { response, filterCust } = payload;
      const pstate = {
        ...state,
        filterCust,
      };

      if (response.code !== 200) {
        message.error(response.msg);
        return {
          ...pstate,
          custlist: [],
          paginationCust: {},
        };
      }

      const { rows, current, pageSize, total } = response;

      return {
        ...pstate,
        custlist: rows || [],
        paginationCust: {
          ...state.paginationCust,
          current: current || 1,
          total: total || 0,
          pageSize: pageSize || 10,
        },
      };
    },
  },
});
