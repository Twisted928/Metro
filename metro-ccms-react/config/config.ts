import { defineConfig } from 'umi';
import theme from './theme';
import proxy from './proxy';
import systemRouter from './router/system';
import monitorRouter from './router/monitor';
import customerRouter from './router/customer';
import creditRouter from './router/credit';
import contractRouter from './router/contract';
import safeguardRouter from './router/safeguard';
import collectionRouter from './router/collection';
import EvaluationRouter from './router/evaluation';
import ProcessRouter from './router/process';
import WarningRouter from './router/warning';
import AccountRouter from './router/account';
// import reportRouter from './router/report';
// import accountRouter from './router/account';

const { REACT_APP_ENV } = process.env;
export default defineConfig({
  hash: false,
  antd: {},
  dva: {
    hmr: true,
  },
  layout: {
    name: 'Metro CCMS',
    locale: 'zh-CN',
    siderWidth: 208,
  },
  locale: {
    // default zh-CN
    default: 'zh-CN',
    antd: true,
    baseNavigator: false,
  },
  nodeModulesTransform: {
    type: 'none',
    exclude: [],
  },
  dynamicImport: {
    loading: '@/components/PageLoading/index',
  },
  targets: {
    ie: 11,
  },
  devtool: 'eval',
  // umi routes: https://umijs.org/docs/routing
  routes: [
    {
      path: '/user',
      layout: false,
      routes: [
        {
          name: '登录',
          path: '/user/login',
          component: './user/login',
        },
        {
          name: '修改密码',
          path: '/user/updataPassword',
          component: './user/login/passwordModefiy.jsx',
        },
      ],
    },
    {
      path: '/',
      name: '首页',
      component: './Welcome',
    },
    ...systemRouter,
    ...monitorRouter,
    ...customerRouter,
    ...creditRouter,
    ...contractRouter,
    ...safeguardRouter,
    ...collectionRouter,
    ...EvaluationRouter,
    ...WarningRouter,
    ...ProcessRouter,
    ...AccountRouter,
    // ...activitiRouter,
    // ...reportRouter,
    // ...accountRouter,
    {
      path: '/tool',

      routes: [
        {
          name: '代码',
          path: '/tool/gen',
          component: './Tool/Role/index',
        },
        {
          name: '代码',
          path: '/tool/gen/edit',
          component: './Tool/Role/components/UpdateForm.jsx',
        },
      ],
    },
    {
      component: './404',
    },
  ],
  theme,
  title: false,
  ignoreMomentLocale: true,
  proxy: proxy[REACT_APP_ENV || 'dev'],
  manifest: {
    basePath: '/',
  },
});
