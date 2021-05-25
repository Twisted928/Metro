import request from 'umi-request';

export async function query(params) {
  return request('/monitor/logininfor/list', {
    params,
  });
}

export async function queryById(params) {
  return request(`/monitor/logininfor/${params}`);
}

export async function create(params) {
  return request('/monitor/logininfor', {
    method: 'POST',
    data: { ...params },
  });
}
export async function update(params) {
  return request('/monitor/logininfor', {
    method: 'POST',
    data: { ...params },
  });
}
