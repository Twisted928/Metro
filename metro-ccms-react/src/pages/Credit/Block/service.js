import request from 'umi-request';

export async function query(params) {
  return request('/system/menu', {
    method: 'POST',
    data: { ...params },
  });
}

export async function queryById(params) {
  return request('/system/menu', {
    method: 'POST',
    data: { ...params },
  });
}

export async function updateRow(params) {
  return request('/system/menu', {
    method: 'POST',
    data: { ...params },
  });
}

export async function getTreeData() {
  return request('/system/dept/treeselect');
}
