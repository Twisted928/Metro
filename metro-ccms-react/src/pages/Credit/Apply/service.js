import request from 'umi-request';

export async function query(params) {
  return request('/system/menu', {
    method: 'POST',
    data: { ...params },
  });
}

export async function create(params) {
  return request('/credit/apply/edit', {
    method: 'POST',
    data: { ...params },
  });
}

export async function update(params) {
  return request('/credit/apply/edit', {
    method: 'POST',
    data: { ...params },
  });
}

export async function querySelect(params) {
  return request('/common/utils/getBasciDataByCtype', {
    method: 'POST',
    data: { ...params },
  });
}

export async function queryCredit(params) {
  return request('/common/utils/getBasciDataByCtype', {
    method: 'POST',
    data: { ...params },
  });
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
