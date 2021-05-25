import request from 'umi-request';

const AUTHTOKEN = localStorage.getItem('Authorization') || '';

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

export async function comUpload(params) {
  return request('/file/downAndUpload/upload', {
    method: 'POST',
    headers: {
      Authorization: `Bearer ${AUTHTOKEN}`,
    },
    data: params,
  });
}

export async function getFile(params) {
  return request('/insurance/checklist/getFile', {
    method: 'POST',
    data: params,
  });
}

export async function downloadFile(params) {
  return request('/file/downAndUpload/downloadFile', {
    method: 'POST',
    data: params,
    responseType: 'blob',
    headers: {
      'content-type': 'application/json',
    },
  });
}
