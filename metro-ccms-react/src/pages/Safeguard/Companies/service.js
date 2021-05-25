import request from 'umi-request';

export async function query(params) {
  return request('/insurance/checklist/list', {
    params,
  });
}

export async function addList(params) {
  return request('/insurance/checklist/save', {
    method: 'POST',
    data: { ...params },
  });
}

export async function delList(params) {
  return request('/insurance/checklist/delete', {
    method: 'POST',
    data: { ...params },
  });
}

export async function updataList(params) {
  return request('/insurance/checklist/update', {
    method: 'POST',
    data: { ...params },
  });
}

export async function judgment(params) {
  return request('/insurance/checklist/judgment', {
    method: 'POST',
    data: { ...params },
  });
}

export async function listPolicy(params) {
  return request('/insurance/checklist/listPolicy', {
    method: 'POST',
    data: { ...params },
  });
}

export async function getFile(params) {
  return request('/insurance/checklist/getFile', {
    method: 'POST',
    data: params,
  });
}

