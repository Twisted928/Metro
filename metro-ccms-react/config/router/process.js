export default [
  {
    name: '流程模块',
    path: '/process',
    icon: 'InsuranceOutlined',
    routes: [
      {
        name: '流程管理',
        path: '/process/processManagement',
        routes: [
          {
            name: '流程管理',
            path: '/process/processManagement',
            component: './processModule/processManagement/index.jsx',
          },
          {
            name: '规则设置',
            path: '/process/processManagement/processConfiguration',
            component: './processModule/processManagement/components/processConfiguration.jsx',
          },
        ],
      },
      {
        name: '单据授权',
        path: '/process/documentAuthorization',
        routes: [
          {
            name: '单据授权',
            path: '/process/documentAuthorization',
            component: './processModule/documentAuthorization/index.jsx',
          },
          {
            name: '新增授权',
            path: '/process/documentAuthorization/addAuthorization',
            component: './processModule/documentAuthorization/components/addAuthorization.jsx',
          },
        ],
      },
      {
        name: '角色授权',
        path: '/process/roleAuthorization',
        routes: [
          {
            name: '角色授权',
            path: '/process/roleAuthorization',
            component: './processModule/roleAuthorization/index.jsx',
          },
          {
            name: '新增授权',
            path: '/process/roleAuthorization/addAuthorization',
            component: './processModule/roleAuthorization/components/addAuthorization.jsx',
          },
        ],
      },
    ],
  },
];
