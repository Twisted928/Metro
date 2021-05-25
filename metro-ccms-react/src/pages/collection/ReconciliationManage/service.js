import request from 'umi-request';

export async function query(params) {
  return request('/reconciliation/management/list', {
    params,
  });
}
export async function selRange(params) {
  return request('/reconciliation/management/selRange', {
    params,
  });
}

export async function detailsList(params) {
  return request('/reconciliation/management/ReconShow', {
    method: 'POST',
    data: { ...params },
  });
}

export async function updataData(params) {
  return request('/reconciliation/management/updateRe', {
    method: 'POST',
    data: { ...params },
  });
}

export async function deleteList(params) {
  return request('/collection/management/del', {
    method: 'POST',
    data: { ...params },
  });
}

export async function updateStaus(params) {
  return request('/reconciliation/management/updateStaus', {
    method: 'POST',
    data: { ...params },
  });
}
export async function insert(params) {
  return request('/reconciliation/management/insertRecon', {
    method: 'POST',
    data: { ...params },
  });
}
