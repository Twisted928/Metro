import request from 'umi-request';

export async function query(params) {
  return request('/credit/management/list', {
    params,
  });
}

export async function queryById(params) {
  return request('/credit/management/cardlist', {
    params
  });
}

export async function queryHistory(params) {
  return request('/credit/management/listlishi', {
    params
  });
}

export async function getTreeData() {
  return request(`/system/dept/treeselect`);
}
