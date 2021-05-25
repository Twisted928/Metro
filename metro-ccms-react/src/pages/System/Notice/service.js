import request from 'umi-request';

export async function query(params) {
  return request('/system/notice/list', {
    params,
  });
}

export async function queryById(params) {
  return request(`/system/notice/${params}`);
}

export async function roleMenuTreeselect(params) {
  return request(`/system/menu/roleMenuTreeselect/${params}`);
}

export async function queryMenuTree() {
  return request(`/system/menu/treeselect`);
}

export async function create(params) {
  return request('/system/notice', {
    method: 'POST',
    data: { ...params },
  });
}

export async function getNickName(params) {
  return request('/system/notice/getNoticeNickName', {
    method: 'POST',
    data: { ...params },
  });
}

export async function update(params) {
  return request('/system/notice', {
    method: 'PUT',
    data: { ...params },
  });
}
