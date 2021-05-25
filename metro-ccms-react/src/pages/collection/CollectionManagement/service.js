import request from 'umi-request';

export async function query(params) {
  return request('/collection/management/list', {
    params,
  });
}

export async function detailsList(params) {
  return request('/collection/management/detail', {
    method: 'POST',
    data: { ...params },
  });
}

export async function updataData(params) {
  return request('/collection/management/save', {
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
  return request('/collection/management/updateStaus', {
    method: 'POST',
    data: { ...params },
  });
}
export async function insert(params) {
  return request('/collection/management/insert', {
    method: 'POST',
    data: { ...params },
  });
}

export async function selPDFinfo(params) {
  return request('/collection/management/selPDFinfo', {
    method: 'POST',
    data: { ...params },
  });
}

export async function addModal(params) {
  return request('/collection/management/selRange', {
    params,
  });
}
