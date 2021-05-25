import request from 'umi-request';

export async function query() {
  return request('/common/utils/getBasicUserInfo', {
    method: 'POST',
  });
}
export async function backlogFetch(params) {
  return request(`/common/utils/getTodoList`, {
    params,
  });
}

export async function getHistoryList(params) {
  return request(`/common/utils/getHistoryList`, {
    params,
  });
}

export async function list(params) {
  return request(`/common/utils/getCenterNotice`, {
    params,
  });
}
