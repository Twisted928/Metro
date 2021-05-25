import request from 'umi-request';

export async function query(params) {
  return request('/mock/contract/query', {
    params,
  });
}

