import request from 'umi-request';

export async function query(params) {
  return request('/earlywarning/warningindex/list', {
    params,
  });
}

// 查询指标大类
export async function queryIndex() {
  return request('/common/utils/getBasciDataByCtype', {
    method: 'POST',
    data: { ctype: 'Warning_Index' },
  });
}

// 查询预警指标
export async function queryWarningIndex(params) {
  return request('/earlywarning/warningindex/list', {
    params,
  });
}

export async function create(params) {
  return request('/earlywarning/warningindex/save', {
    method: 'POST',
    data: { ...params },
  });
}

export async function update(params) {
  return request('/earlywarning/warningindex/update', {
    method: 'POST',
    data: { ...params },
  });
}
