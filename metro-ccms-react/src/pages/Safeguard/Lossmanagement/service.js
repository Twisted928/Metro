import request from 'umi-request';

const AUTHTOKEN = localStorage.getItem('Authorization') || '';

export async function query(params) {
  return request('/reportLoss/management/list', {
    params,
  });
}

export async function insuredQuery(params) {
  return request('/reportLoss/management/listInsuredPolicys', {
    params,
  });
}

export async function addList(params) {
  return request('/reportLoss/management/save', {
    method: 'POST',
    data: { ...params },
  });
}

export async function delList(params) {
  return request('/reportLoss/management/delete', {
    method: 'POST',
    data: { ...params },
  });
}

export async function delTzList(params) {
  return request('/reportLoss/management/ledgerDelete', {
    method: 'POST',
    data: { ...params },
  });
}

export async function getDetailList(params) {
  return request('/reportLoss/management/getone', {
    method: 'POST',
    data: { ...params },
  });
}

export async function tzUpdata(params) {
  return request('/reportLoss/management/update', {
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

export async function getInsure(params) {
  return request('/reportLoss/management/getInsure', {
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
