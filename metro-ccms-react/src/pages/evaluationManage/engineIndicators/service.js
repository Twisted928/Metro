import request from 'umi-request';


export async function query(params) {
  return request('/rulemodel/index/list', {
    params,
  });
}

export async function addAndUpdataList(params) {
  return request('/rulemodel/index/save', {
    method: 'POST',
    data: { ...params },
  });
}
export async function deleteList(params) {
  return request('/rulemodel/index/remove', {
    method: 'POST',
    data: { ...params },
  });
}
