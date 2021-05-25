export default [
  {
    name: '债权保障',
    path: '/monitor',
    icon: 'InsuranceOutlined',
    access: 'canAdmin',
    routes: [
      {
        name: '在线用户',
        path: '/monitor/online',
        component: './Monitor/online/index.jsx',
      },
    ],
  },
];
