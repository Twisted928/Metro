import request from 'umi-request';

// 客户查询
export async function query(params) {
  return request('/customer/whitelistmanagement/list', {
    params,
  });
}

// 清单查询
export async function queryList(params) {
  return request('/customer/whitelistmanagement/qdlist', {
    params,
  });
}

// 清单历史查询
export async function queryListHis(params) {
  return request('/customer/whitelistmanagement/qdlist', {
    params,
  });
}

// 客户预选查询
export async function queryCust(params) {
  return request('/customer/monitoringcustomers/kehulist', {
    params,
  });
}

// 详情查询
export async function queryDetails(params) {
  return request('/customer/whitelistmanagement/details', {
    method: 'POST',
    data: { ...params },
  });
}

// 新增
export async function addCust(params) {
  return request('/customer/whitelistmanagement/save', {
    method: 'POST',
    data: { ...params },
  });
}

// 修改
export async function updateCust(params) {
  return request('/customer/whitelistmanagement/update', {
    method: 'POST',
    data: { ...params },
  });
}

// 审批
export async function approve(params) {
  return request('/customer/whitelistmanagement/approval', {
    method: 'POST',
    data: { ...params },
  });
}
