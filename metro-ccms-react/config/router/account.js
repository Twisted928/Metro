export default [
  {
    name: '个人中心',
    path: '/account',
    hideInMenu: true,
    access: 'canAdmin',
    routes: [
      {
        name: '个人中心',
        path: '/account/center',
        component: './Account/center.jsx',
      },
      {
        name: '修改密码',
        path: '/account/updataPassword',
        component: './Account/passwordModefiy.jsx',
      },
    ],
  },
];
