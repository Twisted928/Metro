import request from 'umi-request';

export async function query(params) {
  return request('/model/info/list', {
    params,
  });
}

// 新增/修改
export async function addAndUpdata(params) {
  return request('/model/info/save', {
    method: 'POST',
    data: { ...params },
  });
}

// 作废
export async function deleteList(params) {
  return request('/model/info/remove', {
    method: 'POST',
    data: { ...params },
  });
}

// 发布
export async function pushList(params) {
  return request('/model/info/push', {
    method: 'POST',
    data: { ...params },
  });
}
// 撤销发布
export async function unpushList(params) {
  return request('/model/info/reBackPush', {
    method: 'POST',
    data: { ...params },
  });
}

// 添加指标查询
export async function catorQuery(params) {
  return request('/model/modelInfoIndex/list', {
    method: 'POST',
    data: { ...params },
  });
}

// 指标查询
export async function modelList(params) {
  return request('/model/index/list', {
    params,
  });
}

// 指标 新增查询
export async function addList(params) {
  return request('/model/modelInfoIndex/save', {
    method: 'POST',
    data: { ...params },
  });
}

// 指标 修改查询
export async function updataQuery(params) {
  return request('/model/modelInfoIndexItem/list', {
    method: 'POST',
    data: { ...params },
  });
}

// 指标 s删除
export async function deleteQuery(params) {
  return request('/model/modelInfoIndex/remove', {
    method: 'POST',
    data: { ...params },
  });
}
