export default [
  {
    name: '客户模块',
    path: '/customer',
    icon: 'TeamOutlined',
    // access: 'canAdmin',
    routes: [
      {
        name: '信用客户管理',
        path: '/customer/creditGroup',
        // access: 'routeAccess',
        hideChildrenInMenu: true,
        routes: [
          {
            path: '/customer/creditGroup',
            redirect: '/customer/creditGroup/list',
            // access: 'routeAccess',
          },
          {
            name: '查询列表',
            path: '/customer/creditGroup/list',
            component: './Customer/Group/index.jsx',
            // access: 'routeAccess',
          },
          {
            name: '新建列表',
            path: '/customer/creditGroup/edit',
            component: './Customer/Group/components/CreditEdit.jsx',
            // access: 'routeAccess',
          },
          {
            name: '详情列表',
            path: '/customer/creditGroup/details',
            component: './Customer/Group/components/GroupsDetails.jsx',
            // access: 'routeAccess',
          },
        ],
      },
      {
        name: '黑名单管理',
        path: '/customer/blacklist',
        // access: 'routeAccess',
        hideChildrenInMenu: true,
        routes: [
          {
            path: '/customer/blacklist',
            redirect: '/customer/creditGroup/list',
            // access: 'routeAccess',
          },
          {
            name: '查询列表',
            path: '/customer/blacklist/list',
            component: './Customer/Black/index.jsx',
            // access: 'routeAccess',
          },
        ],
      },
      {
        name: '睡眠客户管理',
        path: '/customer/sleepcust',
        // access: 'routeAccess',
        hideChildrenInMenu: true,
        routes: [
          {
            path: '/customer/sleepcust',
            redirect: '/customer/sleepcust/list',
            // access: 'routeAccess',
          },
          {
            name: '查询列表',
            path: '/customer/sleepcust/list',
            component: './Customer/Sleep/index.jsx',
            // access: 'routeAccess',
          },
        ],
      },
      {
        name: '监控客户清单',
        path: '/customer/monitor',
        // access: 'routeAccess',
        hideChildrenInMenu: true,
        routes: [
          {
            path: '/customer/monitor',
            redirect: '/customer/monitort/list',
            // access: 'routeAccess',
          },
          {
            name: '查询列表',
            path: '/customer/monitor/list',
            component: './Customer/Monitor/index.jsx',
            // access: 'routeAccess',
          },
        ],
      },
      {
        name: '白名单管理',
        path: '/customer/whitelist',
        // access: 'routeAccess',
        // hideChildrenInMenu: true,
        routes: [
          {
            name: '白名单申请',
            path: '/customer/whitelist/list',
            // access: 'routeAccess',
            routes: [
              {
                name: '白名单申请',
                path: '/customer/whitelist/list',
                component: './Customer/White/index.jsx',
              },
              {
                name: '白名单申请-审批',
                path: '/customer/whitelist/list/approve',
                component: './Customer/White/components/Approve.jsx',
                // access: 'routeAccess',
              },
              {
                name: '白名单申请-新增',
                path: '/customer/whitelist/list/create',
                component: './Customer/White/components/CreateForm.jsx',
                // access: 'routeAccess',
              },
              {
                name: '白名单申请-修改',
                path: '/customer/whitelist/list/edit',
                component: './Customer/White/components/EditForm.jsx',
                // access: 'routeAccess',
              },
              {
                name: '白名单申请-详情',
                path: '/customer/whitelist/list/details',
                component: './Customer/White/components/ShowDetails.jsx',
                // access: 'routeAccess',
              },
            ],
          },
          {
            name: '白名单清单',
            path: '/customer/whitelist/checklist',
            component: './Customer/White/CheckList.jsx',
            // access: 'routeAccess',
          },
        ],
      },
    ],
  },
];
