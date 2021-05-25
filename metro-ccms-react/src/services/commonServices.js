import request from 'umi-request';

const AUTHTOKEN = localStorage.getItem('Authorization') || '';
// 获取客户主数据
export async function getCustomerlList(params) {
  return request('/common/utils/getCustPrimaryList', {
    params,
  });
}
// 获取客户卡号关系
export async function getMemberlList(params) {
  return request('/common/utils/getCustMembersList', {
    params,
  });
}

// 获取保险公司
export async function getCompanyList(params) {
  return request('/common/utils/getInsureCompanyList', {
    params,
  });
}

// 根据参数大类获取参数列表
export async function getBasciData(params) {
  return request('/common/utils/getBasciDataByCtype', {
    method: 'POST',
    data: { ...params },
  });
}

// 附件上传
export async function comUpload(params) {
  return request('/file/downAndUpload/upload', {
    method: 'POST',
    headers: {
      Authorization: `Bearer ${AUTHTOKEN}`,
    },
    data: params,
  });
}
