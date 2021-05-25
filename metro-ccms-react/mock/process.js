const getProcess = () => {
  return {
    data: [
      {
        id: '000000001',
        autherUser: 'ADMIN',
        autherRoleName: 'admin',
        documentNo: 'cc12345678',
        autherTime: '2021-1-11',
      },
      {
        id: '000000002',
        autherUser: 'ADMIN',
        autherRoleName: 'admin',
        documentNo: 'cc12345679',
        autherTime: '2021-1-11',
      },
      {
        id: '000000003',
        autherUser: 'ADMIN',
        autherRoleName: 'admin',
        documentNo: 'cc12345680',
        autherTime: '2021-1-11',
      },
    ],
  };
};

export default {
  'GET /api/process': getProcess,
};
