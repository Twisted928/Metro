import {
    ExclamationCircleOutlined
} from '@ant-design/icons';
import {
    Button,
    Modal,
    message,
    Divider,
    TreeSelect
} from 'antd';
import React, { useState, useEffect, useRef } from 'react';
import { connect, request, useAccess, Access, history } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import ProTable from '@ant-design/pro-table';
import { download } from '@/services/commom';
import { handleTree } from '@/utils/utils';
import { getTreeDataNew, getTreeDataOld } from './service'

const { confirm } = Modal;

// 额度转移
// -----------------------------------------Just Copy------------------------------------------------------
const CreditTransfer = ({ dispatch, creditransfer: { filter, pagination } }) => {
    const [treenew, setTreeNew] = useState();
    const [treeold, setTreeOld] = useState();
    const actionRef = useRef();
    const formRef = useRef();
    const access = useAccess();

    const query = async (param = {}) => {
        dispatch({
            type: 'creditransfer/query',
            payload: param,
        });
    };

    const queryTree = async () => {
        const responseNew = await getTreeDataNew({});
        if (responseNew.code === 200) {
            const treeDataNew = handleTree(responseNew.data, 'deptId');
            setTreeNew(treeDataNew);
        }

        const responseOld = await getTreeDataOld({});
        if (responseOld.code === 200) {
            const treeDataOld = handleTree(responseOld.data, 'deptId');
            setTreeOld(treeDataOld);
        }
    }

    useEffect(() => {
        formRef.current.setFieldsValue(filter);
        query(filter);
        queryTree(filter);
    }, []);

    const deleteColumn = (record) => {
        confirm({
            title: '确定删除此条数据吗？',
            icon: <ExclamationCircleOutlined />,
            onOk() {
                return request(`/credit/creditSearch/${record.APPLICATION_NO}`, {
                    method: 'DELETE',
                }).then((res) => {
                    if (res.code === 200) {
                        message.success(res.msg);
                    } else {
                        message.error(res.msg);
                    }
                    query(filter);
                });
            },
            onCancel() {},
        })
    };

    const checkHistory = (record) => {
        history.push({
            pathname: '/credit/manageCredit/custhistory',
            query: {
                CUST_CODE: record.CUST_CODE,
            },
        });
    }

    const columns = [
        {
            title: '新门店',
            dataIndex: 'DEPART_CODE_New',
            hideInTable: true,
            renderFormItem: (_, { type, defaultRender, ...rest }, form) => {
                if (type === 'form') {
                    return null;
                }
                const status = form.getFieldValue('state');
                if (status !== 'open') {
                    return <TreeSelect {...rest} placeholder="请选择" treeData={treenew} />;
                }
                return defaultRender(_);
            },
        },
        {
            title: '老门店',
            dataIndex: 'DEPART_CODE_Old',
            hideInTable: true,
            renderFormItem: (_, { type, defaultRender, ...rest }, form) => {
                if (type === 'form') {
                    return null;
                }
                const status = form.getFieldValue('state');
                if (status !== 'open') {
                    return <TreeSelect {...rest} placeholder="请选择" treeData={treeold} />;
                }
                return defaultRender(_);
            },
        },
        {
            title: '申请单号',
            dataIndex: 'APPLICATION_NO',
            search: false,
        },
        {
            title: '部门编码',
            dataIndex: 'DEPART_CODE',
            search: false,
        },
        {
            title: '部门名称',
            dataIndex: 'DEPART_NAME',
            search: false,
        },
        {
            title: '门店编码',
            dataIndex: 'STORE_CODE',
            search: false,
        },
        {
            title: '客户编码',
            dataIndex: 'CUST_CODE',
            // search: false,
        },
        {
            title: '客户名称',
            dataIndex: 'CUST_NAME',
            // search: false,

        },
        {
            title: '卡号编码',
            dataIndex: 'CARD_CODE',
            // search: false,

        },
        {
            title: '卡号名称',
            dataIndex: 'CARD_NAME',
            // search: false,

        },
        {
            title: '申请账期',
            dataIndex: 'APPLY_PAYTERM',
            search: false,

        },
        {
            title: '申请额度',
            dataIndex: 'APPLY_LIMIT',
            search: false,

        },
        {
            title: '额度类型',
            dataIndex: 'LIMIT_TYPE',
            search: false,

        },
        {
            title: '发布账期',
            dataIndex: 'PAYMENT_TERM',
            search: false,

        },
        {
            title: '发布额度',
            dataIndex: 'LIMIT',
            search: false,

        },
        {
            title: '币种',
            dataIndex: 'CURRENCY',
            search: false,

        },
        {
            title: '生效时间',
            dataIndex: 'VALID_FROM',
            search: false,

        },
        {
            title: '到期时间',
            dataIndex: 'VALID_TO',
            search: false,

        },
        {
            title: '申请人姓名',
            dataIndex: 'APPLICANT',
            search: false,

        },
        {
            title: '申请时间',
            dataIndex: 'APPLY_TIME',
            search: false,

        },
        {
            title: '信用组编号',
            dataIndex: 'GROUP_CODE',
            search: false,

        },
        {
            title: '信用组账期',
            dataIndex: 'GROUP_PAYTERM',
            search: false,

        },
        {
            title: '信用组额度',
            dataIndex: 'GROUP_LIMIT',
            search: false,

        },
        {
            title: '模型编码',
            dataIndex: 'MOD_CODE',
            search: false,

        },
        {
            title: '评估日期',
            dataIndex: 'APPRAISAL_DATE',
            search: false,

        },
        {
            title: '评估得分',
            dataIndex: 'GRADE',
            search: false,

        },
        {
            title: '评级',
            dataIndex: 'RANK',
            search: false,

        },
        {
            title: '建议额度/评估额度',
            dataIndex: 'ADVICE_LIMIT',
            search: false,
        },
        {
            title: '建议账期/评估账期',
            dataIndex: 'ADVICE_DAYS',
            search: false,

        },
        {
            title: '组织类型',
            dataIndex: "ORGAN_TYPE",
            hideInTable: true,
        },
        {
            title: '行业类型',
            dataIndex: 'INDUSTRY_TYPE',
            hideInTable: true,
        },
        {
            title: '客户类型',
            dataIndex: 'CUST_TYPE',
            hideInTable: true,
        },
        {
            title: '业务类型',
            dataIndex: 'BUSINESS_TYPE',
            hideInTable: true,
        },
        {
            title: '操作',
            valueType: 'option',
            fixed: 'right',
            render: (_, record) => [
                    <a onClick={() => checkHistory(record)} >
                        历史
                    </a>,
                    <Divider type="vertical" />,
                    <a onClick={() => deleteColumn(record)} >
                        删除
                    </a>
            ],
        },
    ]

    const toolBarRender = () => [
        <Access key="add" accessible={access.compAccess('message')}>
            <Button
                type="primary"
            // onClick={() => {
            //     setVisible(true);
            // }}
            >
                新增
          </Button>
        </Access>,

        <Button
            key="export"
            onClick={() => {
                download('/credit/manege/card/export', filter);
            }}
        >
            导出
       </Button>
    ]

    return (
        <PageContainer
            ghost
            title={false}
        >
            <ProTable
                headerTitle="信用卡号查询"
                actionRef={actionRef}
                formRef={formRef}
                // loading={loading}
                pagination={pagination}
                rowKey="CUST_CODE"
                onSubmit={(value) => {
                    query({ ...value });
                }}
                onReset={() => {
                    query({});
                }}
                toolBarRender={toolBarRender}
                columns={columns}
                search={{ span: 8 }}
                scroll={{ x: 3600 }}
            />
        </PageContainer>
    );
}


export default connect(({ creditransfer, loading }) => ({
    creditransfer,
    loading: loading.effects["creditransfer/quert"],
}))(CreditTransfer);
