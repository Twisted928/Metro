export default [
  {
    name: '信用',
    path: '/credit',
    // access: 'routeAccess',
    routes: [
      {
        name: '限额申请',
        path: '/credit/creditApply',
        hideChildrenInMenu: true,
        //  access: 'routeAccess',
        routes: [
          {
            path: '/credit/creditApply',
            redirect: '/credit/creditApply/list',
            //  access: 'routeAccess',
          },
          {
            name: '限额申请',
            path: '/credit/creditApply/list',
            component: './Credit/Apply/index.jsx',
            //  access: 'routeAccess',
          },
          {
            name: '限额申请-新增',
            path: '/credit/creditApply/update',
            component: './Credit/Apply/update.jsx',
            //  access: 'routeAccess',
          },
        ],
      },
      {
        name: '付款条件管理',
        path: '/credit/termsCredit',
        // access: 'routeAccess',
        hideChildrenInMenu: true,
        routes: [
          {
            path: '/credit/termsCredit',
            redirect: '/credit/termsCredit/list',
            // access: 'routeAccess',
          },
          {
            name: '查询列表',
            path: '/credit/termsCredit/list',
            component: './Credit/Terms/index.jsx',
            // access: 'routeAccess',
          },
        ],
      },
      {
        name: '授信管理',
        path: '/credit/manageCredit',
        // access: 'routeAccess',
        hideChildrenInMenu: true,
        routes: [
          {
            path: '/credit/manageCredit',
            redirect: '/credit/manageCredit/list',
            // access: 'routeAccess',
          },
          {
            name: '查询列表',
            path: '/credit/manageCredit/list',
            component: './Credit/Manage/index.jsx',
            // access: 'routeAccess',
          },
          {
            name: '评估详情',
            path: '/credit/manageCredit/evaldetail',
            component: './Credit/Manage/components/ShowDetails.jsx',
            // access: 'routeAccess',
          },
          {
            name: '客户历史记录',
            path: '/credit/manageCredit/custhistory',
            component: './Credit/Manage/components/ShowHistory.jsx',
            // access: 'routeAccess',
          },
          {
            name: '信用卡号查询',
            path: '/credit/manageCredit/cardsearch',
            component: './Credit/Manage/CardSearch.jsx',
            // access: 'routeAccess',
          },
        ],
      },
      {
        name: '客户组管理',
        path: '/credit/custGroup',
        // access: 'routeAccess',
        hideChildrenInMenu: true,
        routes: [
          {
            path: '/credit/custGroup',
            redirect: '/credit/custGroup/list',
            // access: 'routeAccess',
          },
          {
            name: '查询列表',
            path: '/credit/custGroup/list',
            component: './Credit/Custgroup/index.jsx',
            // access: 'routeAccess',
          },
          {
            name: '详情页',
            path: '/credit/custGroup/showdetails',
            component: './Credit/Custgroup/components/ShowDetails.jsx',
            // access: 'routeAccess',
          },
        ],
      },
      {
        name: '客户评估',
        path: '/credit/CustomerEvalu',
        // access: 'routeAccess',
        hideChildrenInMenu: true,
        routes: [
          {
            name: '客户评估',
            path: '/credit/CustomerEvalu',
            component: './Credit/CustomerEvalu/index.jsx',
            // access: 'routeAccess',
          },
          {
            name: '发起评估',
            path: '/credit/CustomerEvalu/initiateEvalu',
            component: './Credit/CustomerEvalu/components/initiateEvalu.jsx',
            // access: 'routeAccess',
          },
        ],
      },
      {
        name: '信用冻结解冻',
        path: '/credit/creditBlock',
        // access: 'routeAccess',
        hideChildrenInMenu: true,
        routes: [
          {
            path: '/credit/creditBlock',
            redirect: '/credit/creditBlock/list',
            // access: 'routeAccess',
          },
          {
            name: '查询列表',
            path: '/credit/creditBlock/list',
            component: './Credit/Block/index.jsx',
            // access: 'routeAccess',
          },
        ],
      },
      {
        name: '信用复审',
        path: '/credit/creditReview',
        // access: 'routeAccess',
        hideChildrenInMenu: true,
        routes: [
          {
            path: '/credit/creditReview',
            redirect: '/credit/creditReview/list',
            // access: 'routeAccess',
          },
          {
            name: '信用复审',
            path: '/credit/creditReview/list',
            component: './Credit/creditReview/index.jsx',
            // access: 'routeAccess',
          },
          {
            name: '新增',
            path: '/credit/creditReview/createForm',
            component: './Credit/creditReview/components/createForm.jsx',
            // access: 'routeAccess',
          },
          {
            name: '新增',
            path: '/credit/creditReview/updataFrom',
            component: './Credit/creditReview/components/updataFrom.jsx',
            // access: 'routeAccess',
          },
        ],
      },
      {
        name: '额度转移',
        path: '/credit/QuotaTransfer',
        // access: 'routeAccess',
        routes: [
          {
            path: '/credit/QuotaTransfer',
            redirect: '/credit/QuotaTransfer/list',
            // access: 'routeAccess',
          },
          {
            name: '信用复审',
            path: '/credit/QuotaTransfer/list',
            component: './Credit/QuotaTransfer/index.jsx',
            // access: 'routeAccess',
          },
          {
            name: '新增',
            path: '/credit/QuotaTransfer/createForm',
            component: './Credit/QuotaTransfer/components/createForm.jsx',
            // access: 'routeAccess',
          },
          {
            name: '新增',
            path: '/credit/QuotaTransfer/updataFrom',
            component: './Credit/QuotaTransfer/components/updataFrom.jsx',
            // access: 'routeAccess',
          },
        ],
      },
      {
        name: '信用组管理',
        path: '/credit/Creditgroup',
        routes: [
          {
            name: '信用组总览',
            path: '/credit/Creditgroup/groupOverview',
            routes: [
              {
                name: '信用组总览',
                path: '/credit/Creditgroup/groupOverview',
                component: './Credit/CreditGroup/GroupOverview/index',
              },
              {
                name: '新增',
                path: '/credit/Creditgroup/groupOverview/createForm',
                component: './Credit/CreditGroup/GroupOverview/components/createForm',
              },
              {
                name: '修改',
                path: '/credit/Creditgroup/groupOverview/updateForm',
                component: './Credit/CreditGroup/GroupOverview/components/createForm',
              },
            ],
          },
          {
            name: '信用组额度申请',
            path: '/credit/Creditgroup/GroupQuotaApplication',
            routes: [
              {
                name: '信用组额度申请',
                path: '/credit/Creditgroup/GroupQuotaApplication',
                component: './Credit/CreditGroup/GroupQuotaApplication/index',
              },
              {
                name: '新增',
                path: '/credit/Creditgroup/GroupQuotaApplication/createForm',
                component: './Credit/CreditGroup/GroupQuotaApplication/components/createForm',
              },
              {
                name: '修改',
                path: '/credit/Creditgroup/GroupQuotaApplication/updataForm',
                component: './Credit/CreditGroup/GroupQuotaApplication/components/updataForm',
              },
            ],
          },
          {
            name: '信用组额度分配',
            path: '/credit/Creditgroup/GroupQuotaAllocation',
            routes: [
              {
                name: '信用组额度分配',
                path: '/credit/Creditgroup/GroupQuotaAllocation',
                component: './Credit/CreditGroup/GroupQuotaAllocation/index',
              },
              {
                name: '新增',
                path: '/credit/Creditgroup/GroupQuotaAllocation/createForm',
                component: './Credit/CreditGroup/GroupQuotaAllocation/components/createForm',
              },
              {
                name: '修改',
                path: '/credit/Creditgroup/GroupQuotaAllocation/updataForm',
                component: './Credit/CreditGroup/GroupQuotaAllocation/components/updataForm',
              },
            ],
          },
        ],
      },
      //   {
      //      name: '信贷限额管控',
      //      path: '/credit/limitCredit',
      //      access: 'routeAccess',
      //      hideChildrenInMenu: true,
      //      routes: [
      //        {
      //          path: '/credit/limitCredit',
      //          redirect: '/credit/limitCredit/list',
      //          access: 'routeAccess',
      //        },
      //        {
      //          name: '查询列表',
      //          path: '/credit/limitCredit/list',
      //          component: './Credit/LimitCredit/index.jsx',
      //          access: 'routeAccess',
      //        },
      //      ],
      //    },
      //   {
      //      name: '信贷账期管控',
      //      path: '/credit/accountCredit',
      //      access: 'routeAccess',
      //      hideChildrenInMenu: true,
      //      routes: [
      //        {
      //          path: '/credit/accountCredit',
      //          redirect: '/credit/accountCredit/list',
      //          access: 'routeAccess',
      //        },
      //        {
      //          name: '查询列表',
      //          path: '/credit/accountCredit/list',
      //          component: './Credit/AccountCredit/index.jsx',
      //          access: 'routeAccess',
      //        },
      //      ],
      //    },
      //   {
      //      name: '风险类别管理',
      //      path: '/credit/riskCredit',
      //      access: 'routeAccess',
      //      hideChildrenInMenu: true,
      //      routes: [
      //        {
      //          path: '/credit/riskCredit',
      //          redirect: '/credit/riskCredit/list',
      //          access: 'routeAccess',
      //        },
      //        {
      //          name: '查询列表',
      //          path: '/credit/riskCredit/list',
      //          component: './Credit/RiskCredit/index.jsx',
      //          access: 'routeAccess',
      //        },
      //      ],
      //    },
    ],
  },
];
