import request from 'umi-request';

export async function query(params) {
  return request('/customer/sleepcustomer/list', {
    params,
  });
}

export async function queryHistory(params) {
  return request('/customer/sleepcustomer/listlishi', {
    params,
  });
}
