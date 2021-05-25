import request from 'umi-request';

export async function query(params) {
  return request('/contract/template/list', {
    params,
  });
}
