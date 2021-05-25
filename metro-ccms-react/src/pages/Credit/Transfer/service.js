import request from 'umi-request';

export async function query(params) {
  return request('/system/menu', {
    method: 'POST',
    data: { ...params },
  });
}

export async function queryById(params) {
  return request('/system/menu',{
    method: 'POST',
    data: { ...params }
  });
}

export async function getTreeDataNew() {
  return request(`/system/dept/treeselect`);
}

export async function getTreeDataOld() {
  return request(`/system/dept/treeselect`);
}