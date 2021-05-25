import React from 'react';
import type { BasicLayoutProps, Settings as LayoutSettings } from '@ant-design/pro-layout';
import { notification } from 'antd';
import type { RequestConfig } from 'umi';
import { history } from 'umi';
import RightContent from '@/components/RightContent';
import FooterPage from '@/components/FooterPage';
import logo from '@/assets/logo.svg';
import type { ResponseError } from 'umi-request';
import { queryCurrent, queryMenu } from './services/user';
import defaultSettings from '../config/defaultSettings';
import './assets/fonts/iconfont.css';

const { NODE_ENV } = process.env;
let PATH = '/ccms/user/login';
if (NODE_ENV === 'development') {
  PATH = '/user/login';
}

export async function getInitialState(): Promise<{
  currentUser?: API.CurrentUser;
  settings?: LayoutSettings;
}> {
  // 如果是登录页面，不执行
  if (history.location.pathname !== '/user/login') {
    try {
      const { user: currentUser, permissions } = await queryCurrent();
      const { data: menu } = await queryMenu();
      const loopMenuItem = (menus) =>
        menus.map(({ hidden, children, ...item }) => ({
          ...item,
          hideChildrenInMenu: hidden,
          // icon: meta.icon && IconMap[meta.icon as string],
          children: children && loopMenuItem(children),
        }));

      return {
        menu: loopMenuItem(menu),
        currentUser,
        permissions,
        settings: defaultSettings,
      };
    } catch (error) {
      window.location.href = PATH;
    }
  }
  return {
    settings: defaultSettings,
  };
}

export const layout = ({
  initialState,
}: {
  initialState: { settings?: LayoutSettings; currentUser?: API.CurrentUser };
}): BasicLayoutProps => {
  return {
    rightContentRender: () => <RightContent />,
    disableContentMargin: false,
    footerRender: () => <FooterPage />,
    onPageChange: () => {
      if (history.location.pathname == '/user/login') {
        localStorage.clear();
      }
      // 如果没有登录，重定向到 login
      const notLogPath = ['/user/login', '/user/updataPassword'];
      if (!initialState?.currentUser?.userId && !notLogPath.includes(history.location.pathname)) {
        window.location.href = PATH;
      }
    },
    logo,
    menuDataRender: () => initialState?.menu,
    ...initialState?.settings,
  };
};

const codeMessage = {
  200: '服务器成功返回请求的数据。',
  201: '新建或修改数据成功。',
  202: '一个请求已经进入后台排队（异步任务）。',
  204: '删除数据成功。',
  400: '发出的请求有错误，服务器没有进行新建或修改数据的操作。',
  401: '用户没有权限（令牌、用户名、密码错误）。',
  403: '用户得到授权，但是访问是被禁止的。',
  404: '发出的请求针对的是不存在的记录，服务器没有进行操作。',
  405: '请求方法不被允许。',
  406: '请求的格式不可得。',
  410: '请求的资源被永久删除，且不会再得到的。',
  422: '当创建一个对象时，发生一个验证错误。',
  500: '服务器发生错误，请检查服务器。',
  502: '网关错误。',
  503: '服务不可用，服务器暂时过载或维护。',
  504: '网关超时。',
};

/**
 * 异常处理程序
 */
const errorHandler = (error: ResponseError) => {
  const { response } = error;
  if (response && response.status) {
    const errorText = codeMessage[response.status] || response.statusText;
    const { status, url } = response;

    notification.error({
      message: `请求错误 ${status}: ${url}`,
      description: errorText,
    });
  }

  if (!response) {
    notification.error({
      description: '您的网络发生异常，无法连接服务器',
      message: '网络异常',
    });
  }
  throw error;
};

/**
 * 请求拦截器
 */
const requestInterCeptor = (url: string, options: Object) => {
  const Authorization = localStorage.getItem('Authorization');
  const { headers } = options;
  if (Authorization) {
    headers.Authorization = localStorage.getItem('Authorization') || null;
  }
  let cusUrl;
  const getFourStr = url.substr(1, 4); // 判断是否是mock前端测试数据
  if (getFourStr === 'mock') {
    cusUrl = `${url}`;
  } else {
    cusUrl = `/prod-api${url}`;
  }
  if (url.startsWith('/api') || url.startsWith('http')) {
    cusUrl = url;
  }
  return {
    url: cusUrl,
    options: {
      ...options,
      headers: {
        ...headers,
      },
    },
  };
};

/**
 * 返回值拦截
 */
const responseInterceptors = async (response) => {
  const data = await response.clone().json();
  if (data && data.code === 401) {
    notification.error({
      message: `登录失效`,
      description: '登录失效，正在跳转页面',
    });
    // history.replace('/user/login');
    window.location.href = PATH;
  }
  return response;
};

export const request: RequestConfig = {
  errorHandler,
  requestInterceptors: [requestInterCeptor],
  responseInterceptors: [responseInterceptors],
};
