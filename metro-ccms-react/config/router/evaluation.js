export default [
  {
    name: '评估管理',
    path: '/evaluation',
    icon: 'InsuranceOutlined',
    access: 'canAdmin',
    routes: [
      {
        name: '评估模型',
        path: '/evaluation/evaluationManage',
        routes: [

          {
            name: '评估模型',
            path: '/evaluation/evaluationManage',
            component: './evaluationManage/evaluateModel/index.jsx',
          },
          {
            name: '添加指标',
            path: '/evaluation/evaluationManage/addIndicator',
            component: './evaluationManage/evaluateModel/components/addIndicator.jsx',
          },
        ],
      },
      {
        name: '评估指标',
        path: '/evaluation/evaluateIndicator',
        routes: [
          {
            path: '/evaluation/evaluateIndicator',
            redirect: '/evaluation/evaluateIndicator/list',
          },
          {
            name: '评估指标',
            path: '/evaluation/evaluateIndicator/list',
            component: './evaluationManage/evaluateIndicator/index.jsx',
          },
        ],
      },
      {
        name: '规则引擎指标',
        path: '/evaluation/engineIndicators',
        routes: [
          {
            path: '/evaluation/engineIndicators',
            redirect: '/evaluation/engineIndicators/list',
          },
          {
            name: '规则引擎指标',
            path: '/evaluation/engineIndicators/list',
            component: './evaluationManage/engineIndicators/index.jsx',
          },
        ],
      },
      {
        name: '规则引擎管理',
        path: '/evaluation/engineManagement',
        routes: [
          {
            name: '规则引擎指标',
            path: '/evaluation/engineManagement',
            component: './evaluationManage/engineManagement/index.jsx',
          },
          {
            name: '规则引擎添加指标',
            path: '/evaluation/engineManagement/addIndicator',
            component: './evaluationManage/engineManagement/components/addIndicator.jsx',
          },
        ],
      },
    ],
  },
];
