export default [
  {
    name: '报表',
    path: '/report',
    icon: 'TableOutlined',
    access: 'canAdmin',
    routes: [
      {
        name: '主数据维护',
        icon: 'smile',
        path: '/report/sysdate',
        component: './System/SysData/index.jsx',
      },
      {
        name: '逾期账龄分析表',
        path: '/report/overdue',
        hideChildrenInMenu: true,
        routes: [
          {
            path: '/report/overdue',
            redirect: '/report/overdue/list',
          },
          {
            name: '查询列表',
            path: '/report/overdue/list',
            component: './Report/Overdue/index.jsx',
          },
        ],
      },
    ],
  },
];
