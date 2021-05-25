/**
 * 在生产环境 代理是无法生效的，所以这里没有生产环境的配置
 * The agent cannot take effect in the production environment
 * so there is no configuration of the production environment
 * For details, please see
 * https://pro.ant.design/docs/deploy
 */
export default {
  dev: {
    '/api/': {
      target: 'https://preview.pro.ant.design',
      changeOrigin: true,
      pathRewrite: { '^': '' },
    },
    '/prod-api/': {
      // target: 'https://juweiinfo.com/prod-api/',
      target: 'http://localhost:8080',
      changeOrigin: true,
      pathRewrite: { '^/prod-api': '' },
    },
    '/mock/': {
      target: 'http://rap2api.taobao.org/app/mock/272658/',
      changeOrigin: true,
      pathRewrite: { '^/mock': '' },
    },
  },
  test: {
    '/api/': {
      target: 'http://localhost:8086',
      changeOrigin: true,
      pathRewrite: { '^': '' },
    },
  },
  pre: {
    '/api/': {
      target: 'your pre url',
      changeOrigin: true,
      pathRewrite: { '^': '' },
    },
  },
};
