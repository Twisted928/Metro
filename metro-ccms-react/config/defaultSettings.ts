import { Settings as LayoutSettings } from '@ant-design/pro-layout';

export default {
  navTheme: 'dark',
  primaryColor: '#333FFF',
  layout: 'top',
  contentWidth: 'Fluid',
  fixedHeader: false,
  fixSiderbar: false,
  colorWeak: true,
  menu: {
    locale: false,
  },
  title: '麦德龙风控管理系统',
  pwa: false,
  iconfontUrl: '',
} as LayoutSettings & {
  pwa: boolean;
};
