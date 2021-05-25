export default function access(initialState: { permissions: any }) {
  const { permissions } = initialState || {};
  return {
    // 页面权限
    routeAccess: (data: { permission: any }) => {
      // admin 拥有所有权限
      if ((permissions || []).includes('*:*:*')) {
        return true;
      }
      return (permissions || []).includes(data.permission);
    },

    // 按钮权限
    compAccess: (auth: any) => {
      // admin 拥有所有权限
      if ((permissions || []).includes('*:*:*')) {
        return true;
      }
      return (permissions || []).includes(auth);
    },
  };
}
