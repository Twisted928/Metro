import request from 'umi-request';

export async function query(params) {
  return request('/customer/monitoringcustomers/list', {
    params,
  });
}

export async function queryCust(params) {
  return request('/customer/monitoringcustomers/kehulist', {
    params,
  });
}

export async function queryDetails(params) {
  return request('/customer/monitoringcustomers/listdetails', {
    method: 'POST',
    data: { ...params },
  });
}

export async function update(params) {
  return request('/customer/monitoringcustomers/uodqd', {
    method: 'POST',
    data: { ...params },
  });
}

export async function create(params) {
  return request('/customer/monitoringcustomers/save', {
    method: 'POST',
    data: { ...params },
  });
}
