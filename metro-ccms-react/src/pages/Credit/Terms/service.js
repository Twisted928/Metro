import request from 'umi-request';

export async function query(params) {
  return request('/payTerm/list', {
    params,
  });
}

export async function create(params) {
  return request('/payTerm/save', {
    method: 'POST',
    data: { ...params },
  });
}
export async function update(params) {
  return request('/payTerm/update', {
    method: 'POST',
    data: { ...params },
  });
}

export async function queryById(params) {
  return request(`/credit/termsterms/edit/${params}`);
}

export async function queryTerm() {
  return request('/common/utils/getBasciDataByCtype', {
    method: 'POST',
    data: {
      ctype: 'PayTerm',
    },
  });
}

export async function queryDesc(params) {
  return request('/common/utils/getPayTermBySettleType', {
    method: 'POST',
    data: { ...params },
  });
}
