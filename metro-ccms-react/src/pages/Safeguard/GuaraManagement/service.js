import request from 'umi-request';


export async function query(params) {
  return request('/guaranteeLetter/management/list', {
    params,
  });
}

export async function deleteList(params) {
  return request('/guaranteeLetter/management/delete', {
    method: 'POST',
    data: { ...params },
  });
}

export async function aaddForm(params) {
  return request('/guaranteeLetter/management/save', {
    method: 'POST',
    data: { ...params },
  });
}

// 详情
export async function detailsMsg(params) {
  return request('/guaranteeLetter/management/getOne', {
    method: 'POST',
    data: { ...params },
  });
}

// 修改
export async function updataFrom(params) {
  return request('/guaranteeLetter/management/update', {
    method: 'POST',
    data: { ...params },
  });
}
