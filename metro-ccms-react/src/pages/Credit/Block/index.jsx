import React, { useState, useEffect, useRef } from 'react';
import { Button, TreeSelect, message, Spin } from 'antd';
import { connect } from 'umi';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';
import { PageContainer } from '@ant-design/pro-layout';
import { handleTree } from '@/utils/utils';
import { LoadingOutlined } from '@ant-design/icons';
import ShowHistory from './components/ShowHistory';
import { getTreeData, updateRow } from './service';

const testData = [
  {
    id: 325132032,
    DEPART_CODE: '549433213',
    DEPART_NAME: '此处名称限制在十个字',
    STORE_CODE: '549433213',
    CUST_CODE: '549433213',
    CUST_NAME: '此处名称限制在十个字',
    CARD_CODE: '549433213',
    CARD_NAME: '此处名称限制在十个字',
    CREDIT_BLOCK: '1',
    CREATED_BY: '此处名称限制在十个字',
    CREATE_TIME: '2020-11-25',
    UPDATED_BY: '此处名称限制在十个字',
    UPDATE_TIME: '2020-11-25',
    VALID_FROM: '2020-11-25',
    VALID_TO: '2020-11-25',
    GROUP_CODE: '549433213',
    GROUP_PAYTERM: '2020.11.25 - 2020.12.25',
    GROUP_LIMIT: '此处限制在五个字',
  },
  {
    id: 624691229,
    DEPART_CODE: '549433213',
    DEPART_NAME: '此处名称限制在十个字',
    STORE_CODE: '549433213',
    CUST_CODE: '549433213',
    CUST_NAME: '此处名称限制在十个字',
    CARD_CODE: '549433213',
    CARD_NAME: '此处名称限制在十个字',
    CREDIT_BLOCK: '1',
    CREATED_BY: '此处名称限制在十个字',
    CREATE_TIME: '2020-11-25',
    UPDATED_BY: '此处名称限制在十个字',
    UPDATE_TIME: '2020-11-25',
    VALID_FROM: '2020-11-25',
    VALID_TO: '2020-11-25',
    GROUP_CODE: '549433213',
    GROUP_PAYTERM: '2020.11.25 - 2020.12.25',
    GROUP_LIMIT: '此处限制在五个字',
  },
  {
    id: 624748504,
    DEPART_CODE: '549433213',
    DEPART_NAME: '此处名称限制在十个字',
    STORE_CODE: '549433213',
    CUST_CODE: '549433213',
    CUST_NAME: '此处名称限制在十个字',
    CARD_CODE: '549433213',
    CARD_NAME: '此处名称限制在十个字',
    CREDIT_BLOCK: '1',
    CREATED_BY: '此处名称限制在十个字',
    CREATE_TIME: '2020-11-25',
    UPDATED_BY: '此处名称限制在十个字',
    UPDATE_TIME: '2020-11-25',
    VALID_FROM: '2020-11-25',
    VALID_TO: '2020-11-25',
    GROUP_CODE: '549433213',
    GROUP_PAYTERM: '2020.11.25 - 2020.12.25',
    GROUP_LIMIT: '此处限制在五个字',
  },
];

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;

// 信用冻结解冻
const CreditBlock = ({ dispatch, creditblock: { filter, pagination }, loading }) => {
  const formRef = useRef();
  const actionRef = useRef();
  const [submitting, setSubmitting] = useState(false);
  const [tree, setTree] = useState();
  const [history, setHistory] = useState();
  const [editableKeys, setEditableRowKeys] = useState([]);

  // const query = (param = {}) => {
  //   dispatch({
  //     type: 'creditblock/query',
  //     payload: param,
  //   });
  // };

  const queryTree = async () => {
    const response = await getTreeData({});
    if (response.code === 200) {
      const treeData = handleTree(response.data, 'deptId');
      setTree(treeData);
    }
  };

  useEffect(() => {
    formRef.current.setFieldsValue(filter);
    // query(filter);
    queryTree(filter);
  }, []);

  const checkHistory = (record) => {
    // eslint-disable-next-line no-console
    console.log(record);
    setHistory(true);
  };

  const columns = [
    {
      title: '部门',
      dataIndex: 'DEPART_CODE',
      hideInTable: true,
      editable: false,
      // search: false,
      renderFormItem: (_, { type, defaultRender, ...rest }, form1) => {
        if (type === 'form') {
          return null;
        }
        const status = form1.getFieldValue('state');
        if (status !== 'open') {
          return <TreeSelect {...rest} placeholder="请选择" treeData={tree} />;
        }
        return defaultRender(_);
      },
    },
    {
      title: '卡号名称',
      dataIndex: 'CARD_NAME',
      // search: false,
      editable: false,
    },
    {
      title: '卡号编码',
      dataIndex: 'CARD_CODE',
      hideInTable: true,
      // search: false,
      editable: false,
    },
    {
      title: '部门名称',
      dataIndex: 'DEPART_NAME',
      search: false,
      editable: false,
    },
    {
      title: '门店编码',
      dataIndex: 'STORE_CODE',
      search: false,
      editable: false,
    },
    {
      title: '客户编码',
      dataIndex: 'CUST_CODE',
      // search:false,
      editable: false,
    },
    {
      title: '客户名称',
      dataIndex: 'CUST_NAME',
      // search:false,
      editable: false,
    },
    {
      title: '客户组名称',
      dataIndex: 'CUSTGR _NAME',
      search: false,
      editable: false,
    },
    {
      title: '信用组名称',
      dataIndex: 'GROUP_NAME',
      search: false,
      editable: false,
    },
    {
      title: '冻结原因',
      dataIndex: 'FREEZE_REASON',
      search: false,
      formItemProps: {
        rules: [
          {
            required: true,
            message: '此项为必填项',
          },
        ],
      },
    },
    {
      title: '信用冻结标记',
      dataIndex: 'CREDIT_BLOCK',
      valueType: 'select',
      // search:false,
      valueEnum: {
        0: {
          text: '解冻',
          status: '0',
        },
        1: {
          text: '冻结',
          status: '1',
        },
        2: {
          text: '全部',
          status: '1',
        },
      },
    },
    {
      title: '创建人',
      dataIndex: 'CREATED_BY',
      search: false,
      editable: false,
    },
    {
      title: '创建时间',
      dataIndex: 'CREATE_TIME',
      search: false,
      editable: false,
    },
    {
      title: '操作',
      valueType: 'option',
      width: '120',
      fixed: 'right',
      render: (text, record, _, action) => [
        <a
          key="editable"
          onClick={() => {
            // !!Error: Expected an assignment or function call and instead saw an expression.
            action.startEditable(record.id);
          }}
        >
          编辑
        </a>,
        <a key="history" onClick={() => checkHistory(record)}>
          历史
        </a>,
      ],
    },
  ];

  const toolBarRender = () => [
    <Button
      key="export"
      onClick={() => {
        download();
      }}
    >
      导出
    </Button>,
  ];

  const editable = {
    editableKeys,
    onSave: async (key, row) => {
      const { code, msg } = await updateRow({ key, ...row });
      if (code === 200) {
        message.success(msg);
        actionRef.current.reload();
      } else {
        message.error({ content: msg, duration: 3 });
      }
    },
    onChange: setEditableRowKeys,
    actionRender: (row, config) => [
      <a
        key="save"
        onClick={async () => {
          setSubmitting(true);
          const values = await config?.form?.validateFields();
          await config?.onSave?.(config.recordKey, { ...row, ...values });
          setSubmitting(false);
        }}
      >
        <Spin size="small" spinning={submitting}>
          保存
        </Spin>
      </a>,
      <a
        key="cancel"
        onClick={async () => {
          const values = await config?.form?.validateFields();
          config?.cancelEditable(config.recordKey);
          await config?.onCancel?.(config.recordKey, { ...row, ...values });
        }}
      >
        取消
      </a>,
    ],
  };

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="id"
        headerTitle="查询列表"
        dateFormatter="string"
        options={false}
        actionRef={actionRef}
        formRef={formRef}
        columns={columns}
        pagination={pagination}
        loading={loading}
        recordCreatorProps={false}
        params={testData}
        request={async () => {
          return {
            data: testData,
            total: 3,
            success: true,
          };
        }}
        editable={editable}
        // onSubmit={(value) => {
        //   query({
        //     ...value,
        //     validfrom: value?.validRange?.[0],
        //     validto: value?.validRange?.[1],
        //   });
        // }}
        // onChange={(page) => {
        //   query({...filter, ...page})
        // }}
        // onReset={() => {
        //   query({});
        // }}
        toolBarRender={toolBarRender}
        scroll={{ x: 2000 }}
        search={{ span: 8, labelWidth: 100 }}
      />

      <ShowHistory
        destroyOnClose
        visible={history}
        // payload={payload}
        onClose={() => {
          setHistory(false);
          //   setPayload([]);
        }}
      />
    </PageContainer>
  );
};

export default connect(({ creditblock, loading }) => ({
  creditblock,
  loading: loading.models.creditblock,
}))(CreditBlock);
