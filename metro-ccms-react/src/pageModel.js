import modelExtend from 'dva-model-extend';
import { message } from 'antd';

const model = {
  reducers: {
    updateState(state, { payload }) {
      return {
        ...state,
        ...payload,
      };
    },
  },
};

const pageModel = modelExtend(model, {
  state: {
    filter: {},
    list: [],
    pagination: {
      showSizeChanger: true,
      showQuickJumper: false,
      showTotal: (total) => `共计 ${total} 条数据`,
      current: 1,
      pageSize: 10,
      total: 0,
    },
  },

  reducers: {
    querySuccess(state, { payload }) {
      const { response, filter } = payload;
      const pstate = {
        ...state,
        filter,
      };

      if (response.code !== 200) {
        message.error(response.msg);
        return {
          ...pstate,
          list: [],
          pagination: {},
        };
      }

      const { rows, current, pageSize, total } = response;

      return {
        ...pstate,
        list: rows || [],
        pagination: {
          ...state.pagination,
          current: current || 1,
          total: total || 0,
          pageSize: pageSize || 10,
        },
      };
    },
  },
});

export { model, pageModel };
