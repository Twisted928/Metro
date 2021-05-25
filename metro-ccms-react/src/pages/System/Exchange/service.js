import request from 'umi-request';

export async function query(params) {
  return request('/system/exchange/getExchangeList', {
    params,
  });
}

export async function queryById(params) {
  return request(params ? `/system/user/${params}` : '/system/user/');
}

export async function create(params) {
  return request('/system/exchange/saveExchange', {
    method: 'POST',
    data: { ...params },
  });
}
export async function update(params) {
  return request('/system/exchange/saveExchange', {
    method: 'POST',
    data: { ...params },
  });
}

export async function getDeptAll() {
  return request(`/system/dept/treeselect`);
}
