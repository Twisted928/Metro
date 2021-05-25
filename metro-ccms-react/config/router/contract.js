export default [
  {
    name: '合同',
    path: '/contract',
    icon: 'SolutionOutlined',
    access: 'canAdmin',
    routes: [
      {
        name: '合同管理',
        path: '/contract/contractManage',
        routes: [
          {
            path: '/contract/clause',
            redirect: '/contract/contractManage/list',
          },
          {
            name: '合同管理',
            path: '/contract/contractManage/list',
            component: './contract/contractManage/index.jsx',
          },
          {
            name: '合同维护',
            path: '/contract/contractManage/updataFrom',
            component: './contract/contractManage/components/updataFrom.jsx',
          },
          {
            name: '合同复核',
            path: '/contract/contractManage/review',
            component: './contract/contractManage/components/review.jsx',
          },
        ],
      },
      {
        name: '合同模板管理',
        path: '/contract/templateManage',
        routes: [
          {
            path: '/contract/templateManage',
            redirect: '/contract/templateManage/list',
          },
          {
            name: '合同管理',
            path: '/contract/templateManage/list',
            component: './contract/templatePreview/index.jsx',
          },
          {
            name: '模板预览',
            path: '/contract/templateManage/preview',
            component: './contract/templatePreview/components/preview.jsx',
          },
        ],
      },
    ],
  },
];
