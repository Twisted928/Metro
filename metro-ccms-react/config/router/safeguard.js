export default [
  {
    name: '债权保障',
    path: '/safeguard',
    icon: 'InsuranceOutlined',
    // access: 'canAdmin',
    routes: [
      {
        name: '保险公司管理',
        path: '/safeguard/SafeguardCompnay',
        routes: [
          {
            name: '保险公司管理',
            path: '/safeguard/SafeguardCompnay',
            component: './Safeguard/SafeguardCompnay/index.jsx',
          },
          {
            name: '新增',
            path: '/safeguard/SafeguardCompnay/createForm',
            component: './Safeguard/SafeguardCompnay/components/createForm.jsx',
          },
          {
            name: '修改',
            path: '/safeguard/SafeguardCompnay/updataFrom',
            component: './Safeguard/SafeguardCompnay/components/updataFrom.jsx',
          },
          {
            name: '详情',
            path: '/safeguard/SafeguardCompnay/detailsForm',
            component: './Safeguard/SafeguardCompnay/components/detailsForm.jsx',
          },
        ],
      },
      {
        name: '保险客户清单',
        path: '/safeguard/Companies',
        routes: [
          {
            name: '保险客户清单',
            path: '/safeguard/Companies',
            component: './Safeguard/Companies/index.jsx',
          },
          {
            name: '新增',
            path: '/safeguard/Companies/createForm',
            component: './Safeguard/Companies/components/createForm.jsx',
          },
          {
            name: '补录',
            path: '/safeguard/Companies/updataForm',
            component: './Safeguard/Companies/components/updataForm.jsx',
          },
        ],
      },
      {
        name: '投保管理',
        path: '/safeguard/InsurManagement',
        routes: [
          {
            path: '/safeguard/InsurManagement',
            redirect: '/Safeguard/InsurManagement/list',
          },
          {
            name: '投保管理',
            path: '/safeguard/InsurManagement/list',
            component: './Safeguard/InsurManagement/index.jsx',
          },
        ],
      },
      {
        name: '报损管理',
        path: '/safeguard/Lossmanagement',
        routes: [
          {
            name: '报损管理',
            path: '/safeguard/Lossmanagement',
            component: './Safeguard/Lossmanagement/index.jsx',
          },
          {
            name: '新增',
            path: '/safeguard/Lossmanagement/createForm',
            component: './Safeguard/Lossmanagement/components/createForm.jsx',
          },
          {
            name: '维护',
            path: '/safeguard/Lossmanagement/updataFrom',
            component: './Safeguard/Lossmanagement/components/updataFrom.jsx',
          },
          {
            name: '详情',
            path: '/safeguard/Lossmanagement/detailForm',
            component: './Safeguard/Lossmanagement/components/detailForm.jsx',
          },
        ],
      },
      {
        name: '担保函管理',
        path: '/safeguard/GuaraManagement',
        routes: [
          {
            name: '报损管理',
            path: '/safeguard/GuaraManagement',
            component: './Safeguard/GuaraManagement/index.jsx',
          },
          {
            name: '新增',
            path: '/safeguard/GuaraManagement/createForm',
            component: './Safeguard/GuaraManagement/components/createForm.jsx',
          },
          {
            name: '维护',
            path: '/safeguard/GuaraManagement/updataFrom',
            component: './Safeguard/GuaraManagement/components/updataFrom.jsx',
          },
          {
            name: '详情',
            path: '/safeguard/GuaraManagement/detailForm',
            component: './Safeguard/GuaraManagement/components/detailForm.jsx',
          },
        ],
      },
    ],
  },
];
