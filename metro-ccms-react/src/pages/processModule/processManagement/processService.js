import request from 'umi-request';

export async function queryList(params) {
  return request('/activiti/model/list', {
    params,
  });
}
export async function getBusinessList(params) {
  return request('/activiti/model/getBusiness', {
    params,
  });
}
export async function saveActBusinessList(params) {
  return request('/activiti/model/saveActBusiness', {
    method: 'POST',
    data: { ...params },
  });
}
export async function chnagePublish(params) {
  return request('/activiti/model/publish', {
    method: 'POST',
    data: { ...params },
  });
}
export async function modelCreate(params) {
  return request('/activiti/model/create', {
    method: 'POST',
    data: { ...params },
  });
}
export async function historyList(params) {
  return request('/activiti/deploy/list', {
    method: 'POST',
    data: { ...params },
  });
}
