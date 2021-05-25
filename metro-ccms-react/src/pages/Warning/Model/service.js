import request from 'umi-request';

export async function query(params) {
  return request('/earlywarning/warningmodel/list', {
    params,
  });
}

export async function queryDetails(params) {
  return request('/earlywarning/warningmodel/detailed', {
    method: 'POST',
    data: { ...params },
  });
}

export async function create(params) {
  return request('/earlywarning/warningmodel/save', {
    method: 'POST',
    data: { ...params },
  });
}

export async function update(params) {
  return request('/earlywarning/warningmodel/update', {
    method: 'POST',
    data: { ...params },
  });
}

export async function getTreeData() {
  return request(`/system/dept/treeselect`);
}

export async function getRoleData() {
  return request(`/system/user/`);
}

export async function modelList(params) {
  return request('/earlywarning/warningindex/list', {
    params,
  });
}

export async function addList(params) {
  return request('/earlywaring/modelInfoIndex/save', {
    method: 'POST',
    data: { ...params },
  });
}

export async function updataQuery(params) {
  return request('/earlywaring/modelInfoIndex/listit', {
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

export async function modelInQuery(params) {
  return request('/earlywaring/modelInfoIndex/list', {
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

export async function enableOrDisable(params) {
  return request('/earlywarning/warningmodel/enableOrDisable', {
    method: 'POST',
    data: { ...params },
  });
}

export async function getEwModelRecord(params) {
  return request('/earlywarning/warningmodel/getEwModelRecord', {
    method: 'POST',
    data: { ...params },
  });
}

export async function detailed(params) {
  return request('/earlywarning/warningmodel/detailed', {
    method: 'POST',
    data: { ...params },
  });
}

export async function saveEarlyModelConfig(params) {
  return request('/earlywarning/warningmodel/saveEarlyModelConfig', {
    method: 'POST',
    data: { ...params },
  });
}

export async function queryById(params) {
  return request(params ? `/system/user/${params}` : '/system/user/');
}

