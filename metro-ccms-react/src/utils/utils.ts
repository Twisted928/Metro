import { parse } from 'querystring';

const locationHost = `http://${window.location.host}`;
const textHost = `https://${window.location.host}`;

/* eslint no-useless-escape:0 import/prefer-default-export:0 */
const reg = /(((^https?:(?:\/\/)?)(?:[-;:&=\+\$,\w]+@)?[A-Za-z0-9.-]+(?::\d+)?|(?:www.|[-;:&=\+\$,\w]+@)[A-Za-z0-9.-]+)((?:\/[\+~%\/.\w-_]*)?\??(?:[-\+=&;%@.\w_]*)#?(?:[\w]*))?)$/;
export const baseUrl = (url: string) =>
  (process.env.NODE_ENV === 'development' ? '/prod-api' : '/prod-api') + url;

export const getHearUrl = () => (process.env.NODE_ENV == 'development' ? '/prod-api' : '');

// 跳转流程图页面
export const goToActivitPage = () =>
  process.env.NODE_ENV == 'development' ? `${locationHost}` : `${textHost}/ccms`;

export const isUrl = (path: string): boolean => reg.test(path);

export const getPageQuery = () => {
  const { href } = window.location;
  const qsIndex = href.indexOf('?');
  const sharpIndex = href.indexOf('#');

  if (qsIndex !== -1) {
    if (qsIndex > sharpIndex) {
      return parse(href.split('?')[1]);
    }

    return parse(href.slice(qsIndex + 1, sharpIndex));
  }

  return {};
};

/**
 * 构造树型结构数据
 * @param {*} data 数据源
 * @param {*} id id字段 默认 'id'
 * @param {*} parentId 父节点字段 默认 'parentId'
 * @param {*} children 孩子节点字段 默认 'children'
 * @param {*} rootId 根Id 默认 0
 */
export const handleTree = (
  data: Object,
  id: string,
  parentId: string,
  children: Object,
  rootId: string,
) => {
  id = id || 'id';
  parentId = parentId || 'parentId';
  children = children || 'children';
  rootId =
    rootId ||
    Math.min.apply(
      Math,
      data.map((item) => {
        return item[parentId];
      }),
    ) ||
    0;
  // 对源数据深度克隆
  const cloneData = JSON.parse(JSON.stringify(data));
  // 循环所有项
  const treeData = cloneData.filter((father) => {
    const branchArr = cloneData.filter((child) => {
      // 返回每一项的子级数组
      return father[id] === child[parentId];
    });
    branchArr.length > 0 ? (father.children = branchArr) : '';
    if (branchArr.length === 0) {
      delete father.children;
    }
    // 返回第一层
    return father[parentId] === rootId;
  });
  return treeData != '' ? treeData : data;
};

// 回显数据字典
export const selectDictLabel = (datas, value) => {
  const actions = [];
  Object.keys(datas).some((key) => {
    if (datas[key].dictValue == '' + value) {
      actions.push(datas[key].dictLabel);
      return true;
    }
  });
  return actions.join('');
};
