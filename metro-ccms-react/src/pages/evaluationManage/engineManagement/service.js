import request from 'umi-request';

export async function query(params) {
  return request('/rulemodel/info/list', {
    params,
  });
}

export async function addAndUpdataList(params) {
  return request('/rulemodel/info/save', {
    method: 'POST',
    data: { ...params },
  });
}

export async function deleteList(params) {
  return request('/rulemodel/info/remove', {
    method: 'POST',
    data: { ...params },
  });
}

export async function modelInQuery(params) {
  return request('/rulemodel/modelInfoIndex/list', {
    method: 'POST',
    data: { ...params },
  });
}

export async function listkehu(params) {
  return request('/rulemodel/modelInfoIndex/listkehu', {
    method: 'POST',
    data: { ...params },
  });
}

export async function addList(params) {
  return request('/rulemodel/modelInfoIndex/save', {
    method: 'POST',
    data: { ...params },
  });
}

export async function updataQuery(params) {
  return request('/rulemodel/modelInfoIndexItem/list', {
    method: 'POST',
    data: { ...params },
  });
}

export async function deleteQuery(params) {
  return request('/rulemodel/modelInfoIndex/remove', {
    method: 'POST',
    data: { ...params },
  });
}

export async function modelList(params) {
  return request('/rulemodel/index/list', {
    params,
  });
}
