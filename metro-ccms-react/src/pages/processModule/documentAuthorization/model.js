import dvaModelExtend from 'dva-model-extend';
import { pageModel } from '@/pageModel';
import { message } from 'antd';
import { query, getRoleUser, todoList, getAutherDoList } from './services';

export default dvaModelExtend(pageModel, {
  namespace: 'documentAuth',
  state: {
    treeData: [],
    getRoleUserList: [],
    gettodoList: [],
    modalPagination: {
      showSizeChanger: true,
      showQuickJumper: false,
      showTotal: (total) => `共计 ${total} 条数据`,
      current: 1,
      pageSize: 10,
      total: 0,
    },
    getAutherDoList: [],
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
    *todoList({ payload }, { call, put }) {
      const response = yield call(todoList, payload);
      yield put({
        type: 'todoSuccess',
        payload: { response, filter: payload },
      });
    },
    *getAutherDoList({ payload }, { call, put }) {
      const response = yield call(getAutherDoList, payload);
      yield put({
        type: 'updateState',
        payload: { getAutherDoList: response },
      });
      return response;
    },
    *getRoleUser({ payload }, { call, put }) {
      const response = yield call(getRoleUser, payload);
      yield put({
        type: 'updateState',
        payload: { getRoleUserList: response },
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
    todoSuccess(state, { payload }) {
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
        gettodoList: rows,
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
