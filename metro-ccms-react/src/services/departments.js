import request from 'umi-request';

export async function getTree() {
  return request('/system/dept/treeselect');
}
