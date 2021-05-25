import request from 'umi-request';

export async function query(params) {
  return request('/earlywarning/warningemail/list', {
    params,
  });
}
