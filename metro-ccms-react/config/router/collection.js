export default [
  {
    name: '催收对账',
    path: '/collection',
    icon: 'InsuranceOutlined',
    // access: 'canAdmin',
    routes: [
      {
        name: '催收管理',
        path: '/collection/CollectionManagement',
        routes: [
          {
            name: '催收管理',
            path: '/collection/CollectionManagement',
            component: './collection/CollectionManagement/index.jsx',
          },
          {
            name: '修改',
            path: '/collection/CollectionManagement/updataFrom',
            component: './collection/CollectionManagement/components/updataFrom.jsx',
          },
          {
            name: '详情',
            path: '/collection/CollectionManagement/detailsForm',
            component: './collection/CollectionManagement/components/detailsForm.jsx',
          },
          {
            name: '预览',
            path: '/collection/CollectionManagement/wordPrint',
            component: './collection/CollectionManagement/components/wordPrint.jsx',
          },
        ],
      },
      {
        name: '对账管理',
        path: '/collection/ReconciliationManage',
        routes: [
          {
            name: '对账管理',
            path: '/collection/ReconciliationManage',
            component: './collection/ReconciliationManage/index.jsx',
          },
          {
            name: '修改',
            path: '/collection/ReconciliationManage/updataFrom',
            component: './collection/ReconciliationManage/components/updataFrom.jsx',
          },
          {
            name: '详情',
            path: '/collection/ReconciliationManage/detailsForm',
            component: './collection/ReconciliationManage/components/detailsForm.jsx',
          },
          {
            name: '预览',
            path: '/collection/ReconciliationManage/wordPrint',
            component: './collection/ReconciliationManage/components/wordPrint.jsx',
          },
        ],
      },
    ],
  },
];

