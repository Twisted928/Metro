export default [
  {
    name: '预警模块',
    path: '/warning',
    routes: [
      // {
      //   name: '预警指标',
      //   path: '/warning/warningIndex',
      //   access: 'routeAccess',
      //   permission: 'earlywarning:warningindex:list',
      //   hideChildrenInMenu: true,
      //   component: './Warning/Index/index.jsx',
      // },
      {
        name: '预警模型',
        path: '/warning/warningModel',
        access: 'routeAccess',
        hideChildrenInMenu: true,
        routes: [
          {
            name: '查询列表',
            path: '/warning/warningModel',
            component: './Warning/Model/index.jsx',
            access: 'routeAccess',
            permission: 'earlywarning:warningmodel:list',
          },
          {
            name: '详情',
            path: '/warning/warningModel/details',
            component: './Warning/Model/components/ShowDetails.jsx',
            access: 'routeAccess',
          },
          {
            name: '添加指标',
            path: '/warning/warningModel/addEval',
            component: './Warning/Model/components/addIndicator.jsx',
            access: 'routeAccess',
          },
        ],
      },
      {
        name: '邮件记录',
        path: '/warning/warningMail',
        access: 'routeAccess',
        hideChildrenInMenu: true,
        routes: [
          {
            path: '/warning/warningMail',
            redirect: '/warning/warningMail/list',
            access: 'routeAccess',
          },
          {
            name: '查询列表',
            path: '/warning/warningMail/list',
            component: './Warning/Mail/index.jsx',
            access: 'routeAccess',
            permission: 'earlywarning:warningemail:list',
          },
        ],
      },
      // {
      //   name: '睡眠客户管理',
      //   path: '/customer/sleepcust',
      //   access: 'routeAccess',
      //   hideChildrenInMenu: true,
      //   routes: [
      //     {
      //       path: '/customer/sleepcust',
      //       redirect: '/customer/sleepcust/list',
      //       access: 'routeAccess',
      //     },
      //     {
      //       name: '查询列表',
      //       path: '/customer/sleepcust/list',
      //       component: './Customer/Sleep/index.jsx',
      //       access: 'routeAccess',
      //     },
      //   ],
      // },
      // {
      //   name: '监控客户清单',
      //   path: '/customer/monitor',
      //   access: 'routeAccess',
      //   hideChildrenInMenu: true,
      //   routes: [
      //     {
      //       path: '/customer/monitor',
      //       redirect: '/customer/monitort/list',
      //       access: 'routeAccess',
      //     },
      //     {
      //       name: '查询列表',
      //       path: '/customer/monitor/list',
      //       component: './Customer/Monitor/index.jsx',
      //       access: 'routeAccess',
      //     },
      //   ],
      // },
      // {
      //   name: '白名单管理',
      //   path: '/customer/whitelist',
      //   access: 'routeAccess',
      //   hideChildrenInMenu: true,
      //   routes: [
      //     {
      //       path: '/customer/whitelist',
      //       redirect: '/customer/whitelist/list',
      //       access: 'routeAccess',
      //     },
      //     {
      //       name: '白名单申请',
      //       path: '/customer/whitelist/list',
      //       component: './Customer/White/index.jsx',
      //       access: 'routeAccess',
      //     },
      //     {
      //       name: '白名单清单',
      //       path: '/customer/whitelist/checklist',
      //       component: './Customer/White/components/CheckList.jsx',
      //       access: 'routeAccess',
      //     },
      //   ],
      // },
    ],
  },
];
