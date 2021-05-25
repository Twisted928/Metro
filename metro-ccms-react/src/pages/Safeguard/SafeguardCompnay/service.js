import request from 'umi-request';

export async function query(params) {
  return request('/insurance/management/list', {
    params,
  });
}

export async function deleteList(params) {
  return request('/insurance/management/delete', {
    method: 'POST',
    data: { ...params },
  });
}

// 新增
export async function add(params) {
  return request('/insurance/management/save', {
    method: 'POST',
    data: { ...params },
  });
}
// 修改
export async function update(params) {
  return request('/insurance/management/update', {
    method: 'POST',
    data: { ...params },
  });
}
// 详情
export async function details(params) {
  return request('/insurance/management/getone', {
    params,
  });
}

// 范围
export async function boundary(params) {
  return request('/insurance/management/insureScopeList', {
    params,
  });
}

// 保单新增
export async function policyAdd(params) {
  return request('/insurance/management/policySave', {
    method: 'POST',
    data: { ...params },
  });
}
// 保单修改
export async function policyUpdata(params) {
  return request('/insurance/management/policyUpdate', {
    method: 'POST',
    data: { ...params },
  });
}
// 保单删除
export async function policyDelete(params) {
  return request('/insurance/management/policyDelete', {
    method: 'POST',
    data: { ...params },
  });
}

// 校验时间
export async function timeJudgment(params) {
  return request('/insurance/management/timeJudgment', {
    method: 'POST',
    data: { ...params },
  });
}
export async function policyJudgment(params) {
  return request('/insurance/management/policyJudgment', {
    method: 'POST',
    data: { ...params },
  });
}
