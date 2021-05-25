import qs from 'qs';
import { message } from 'antd';
import { request, history } from 'umi';
import { baseUrl } from '@/utils/utils';

const AUTHTOKEN = localStorage.getItem('Authorization') || '';

export async function doExport1(reqUrl, param) {
  const AUTHTOKEN = localStorage.getItem('Authorization') || '';
  const cusUrl = baseUrl(reqUrl);
  fetch(cusUrl, {
    method: 'POST',
    body: JSON.stringify(param),
    responseType: 'blob',
    headers: {
      Authorization: `Bearer ${AUTHTOKEN}`,
      'content-type': 'application/json',
    },
    parseResponse: false, // 添加参数
  })
    .then((res) => res.blob())
    .then((res) => {
      const content = res;
      const blob = new Blob([content]);
      if ('download' in document.createElement('a')) {
        // 非IE下载
        const elink = document.createElement('a');
        elink.download = 'test';
        elink.style.display = 'none';
        elink.href = URL.createObjectURL(blob);
        document.body.appendChild(elink);
        elink.click();
        URL.revokeObjectURL(elink.href); // 释放URL 对象
        document.body.removeChild(elink);
      } else {
        // IE10+下载
        navigator.msSaveBlob(blob, 'test');
      }
    });
}

export async function doExport(reqUrl, param) {
  const cusUrl = baseUrl(reqUrl);
  fetch(cusUrl, {
    method: 'POST',
    body: JSON.stringify(param),
    responseType: 'blob',
    headers: {
      Authorization: `Bearer ${AUTHTOKEN}`,
      'content-type': 'application/json',
    },
  }).then((res) =>
    res.blob().then((blob) => {
      const a = document.createElement('a');
      const url = window.URL.createObjectURL(blob);
      const filename = res.headers.get('Content-Disposition');
      const fo = qs.parse(filename);
      a.href = url;
      a.download = fo.filename;
      a.click();
      window.URL.revokeObjectURL(url);
    }),
  );
}

export async function download(url, params) {
  const hide = message.loading('正在导出', 0);
  request(url, { params }).then((res) => {
    hide();
    if (res.code === 200) {
      window.location.href = baseUrl(`/common/download?fileName=${encodeURI(res.msg)}&delete=true`);
    } else {
      message.error(res.msg);
    }
  });
}

// 根据字典类型查询字典数据信息
export function getDicts(dictType) {
  return request(`/system/dict/data/type/${dictType}`);
}

const mimeMap = {
  xlsx: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet',
  zip: 'application/zip',
};

export const downLoadZip = (str, filename) => {
  const url = baseUrl(str);
  const AUTHTOKEN = localStorage.getItem('Authorization') || '';
  fetch(url, {
    method: 'get',
    responseType: 'blob',
    headers: { Authorization: `Bearer ${AUTHTOKEN}` },
  }).then((res) => {
    resolveBlob(res, mimeMap.zip);
  });
};
/**
 * 解析blob响应内容并下载
 * @param {*} res blob响应内容
 * @param {String} mimeType MIME类型
 */

export function resolveBlob(res, mimeType) {
  res.blob().then((blob) => {
    // const aLink = document.createElement('a');
    // // const blob = new Blob([res.data], { type: mimeType });
    // // //从response的headers中获取filename, 后端response.setHeader("Content-disposition", "attachment; filename=xxxx.docx") 设置的文件名;
    // const patt = new RegExp('filename=([^;]+\\.[^\\.;]+);*');
    // const contentDisposition = res.headers.get('Content-Disposition');
    // const result = patt.exec(contentDisposition);
    // let fileName = result[1];
    // fileName = fileName.replace(/\"/g, '');
    // aLink.href = URL.createObjectURL(blob);
    // aLink.setAttribute('download', fileName); // 设置下载文件名称
    // document.body.appendChild(aLink);
    // aLink.click();
    // document.body.appendChild(aLink);

    const a = document.createElement('a');
    const url = window.URL.createObjectURL(blob);

    // const patt = new RegExp('filename=([^;]+\\.[^\\.;]+);*');
    // const contentDisposition = res.headers.get('Content-Disposition');
    // const result = patt.exec(contentDisposition);
    // let fileName = result[1];
    // fileName = fileName.replace(/\"/g, '');
    a.href = url;
    a.download = 'code.zip';
    a.click();
    window.URL.revokeObjectURL(url);
  });
}

// 附件下载
export async function downloadFile(reqUrl, param) {
  const cusUrl = baseUrl(reqUrl);
  fetch(cusUrl, {
    method: 'POST',
    body: JSON.stringify(param),
    responseType: 'blob',
    headers: {
      Authorization: `Bearer ${AUTHTOKEN}`,
      'content-type': 'application/json',
    },
  }).then((res) =>
    res.blob().then((blob) => {
      const blobUrl = window.URL.createObjectURL(blob);
      // const filename = res.headers.get('Content-Disposition');
      const filename = param.fileName;
      const aElement = document.createElement('a');
      document.body.appendChild(aElement);
      aElement.style.display = 'none';
      aElement.href = blobUrl;
      aElement.download = decodeURI(filename);
      aElement.click();
      document.body.removeChild(aElement);
    }),
  );
}

// 附件删除
export const deleteFile = (param) => {
  const cusUrl = baseUrl('/file/downAndUpload/deleteFileList');
  return fetch(cusUrl, {
    method: 'POST',
    body: JSON.stringify(param),
    headers: {
      Authorization: `Bearer ${AUTHTOKEN}`,
      'content-type': 'application/json',
    },
  }).then((res) => {
    if (res.status !== 200) {
      message.error('附件删除失败！');
    }
  });
};

// 使用Promise解决多个并列方法重复执行某个条件
const getUpload = (id, type, fileNew) => {
  const url = baseUrl('/file/downAndUpload/upload');
  return new Promise((resolve) => {
    fileNew.forEach((item) => {
      const fromdata = new FormData();
      fromdata.append('id', id);
      fromdata.append('type', type);
      fromdata.append('file', item);
      fetch(url, {
        method: 'POST',
        body: fromdata,
        headers: {
          Authorization: `Bearer ${AUTHTOKEN}`,
        },
      }).then((response) => {
        resolve(response.status);
      });
    });
  });
};

export const promiseAll = (id, type, fileNew) => {
  Promise.all([getUpload(id, type, fileNew)]).catch(() => {
    message.error('附件上传失败！');
  });
};

// 前端删除附件
export const deleteUploadList = (id, fileList) => {
  const newList = fileList;
  const newUpList = newList.filter((item) => item.id !== id);
  return newUpList;
};

// 检验只能输入字母和数字
export const abcAndNumber = async (_, value) => {
  const reg = /[^\w]/;
  if (value && reg.test(value)) {
    return Promise.reject(new Error('不能带有中文字符！'));
  }
  return false;
};

// 检验数字框最大值1
export const maxNumber = async (_, value) => {
  if (value && value > 1) {
    return Promise.reject(new Error('输入的数值不能大于1！'));
  }
  if (value && value < 0) {
    return Promise.reject(new Error('输入的数值不能小于0！'));
  }
  return false;
};

// 判断对象是否相等

export function deepCompare(x, y) {
  var i, l, leftChain, rightChain;
  function compare2Objects(x, y) {
    var p;
    if (isNaN(x) && isNaN(y) && typeof x === 'number' && typeof y === 'number') {
      return true;
    }
    if (x === y) {
      return true;
    }
    if (
      (typeof x === 'function' && typeof y === 'function') ||
      (x instanceof Date && y instanceof Date) ||
      (x instanceof RegExp && y instanceof RegExp) ||
      (x instanceof String && y instanceof String) ||
      (x instanceof Number && y instanceof Number)
    ) {
      return x.toString() === y.toString();
    }
    if (!(x instanceof Object && y instanceof Object)) {
      return false;
    }
    if (x.isPrototypeOf(y) || y.isPrototypeOf(x)) {
      return false;
    }
    if (x.constructor !== y.constructor) {
      return false;
    }
    if (x.prototype !== y.prototype) {
      return false;
    }
    if (leftChain.indexOf(x) > -1 || rightChain.indexOf(y) > -1) {
      return false;
    }
    for (p in y) {
      if (y.hasOwnProperty(p) !== x.hasOwnProperty(p)) {
        return false;
      } else if (typeof y[p] !== typeof x[p]) {
        return false;
      }
    }
    for (p in x) {
      if (y.hasOwnProperty(p) !== x.hasOwnProperty(p)) {
        return false;
      } else if (typeof y[p] !== typeof x[p]) {
        return false;
      }
      switch (typeof x[p]) {
        case 'object':
        case 'function':
          leftChain.push(x);
          rightChain.push(y);
          if (!compare2Objects(x[p], y[p])) {
            return false;
          }
          leftChain.pop();
          rightChain.pop();
          break;
        default:
          if (x[p] !== y[p]) {
            return false;
          }
          break;
      }
    }
    return true;
  }
  if (arguments.length < 1) {
    return true;
  }
  for (i = 1, l = arguments.length; i < l; i++) {
    leftChain = []; //Todo: this can be cached
    rightChain = [];

    if (!compare2Objects(arguments[0], arguments[i])) {
      return false;
    }
  }
  return true;
}
