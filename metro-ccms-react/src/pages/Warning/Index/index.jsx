import React, { useState, useEffect, useRef, Fragment } from 'react';
import { Button, Modal, message, Dropdown, Menu, Select } from 'antd';
import { ExclamationCircleOutlined, DownOutlined } from '@ant-design/icons';
import { connect, Access, useAccess, request } from 'umi';
import ProTable from '@ant-design/pro-table';
// import { download } from '@/services/commom';
import { PageContainer } from '@ant-design/pro-layout';
import EditForm from './components/EditForm';
import ShowDatails from './components/ShowDetails';
import { queryIndex, queryWarningIndex } from './service';

const { confirm } = Modal;
const { Option } = Select;

// 预警指标
const WarningIndex = ({ dispatch, warningindex: { list, filter, pagination }, loading }) => {
  const formRef = useRef();
  const actionRef = useRef();
  const access = useAccess();
  // 控制编辑弹窗
  const [visible, setVisible] = useState(false);
  // 控制详情弹窗
  const [showDetails, setDetails] = useState(false);
  // 详情弹窗传值
  const [details, setDetailsParams] = useState([]);
  // 修改弹窗传值
  const [thisPayloads, setPayloads] = useState([]);
  // 指标大类Enum
  const [typeEnum, setTypeEnum] = useState([]);
  // 预警指标Enum
  const [warningEnum, setWarningEnum] = useState([]);

  const query = (param = {}) => {
    dispatch({
      type: 'warningindex/query',
      payload: param,
    });
  };

  const getTypeEnum = async () => {
    const { code, data, msg } = await queryIndex();
    if (code === 200) {
      setTypeEnum(data);
    } else {
      message.error(msg);
    }
  };

  const getWarningEnum = async (value) => {
    formRef.current.setFieldsValue({
      warningindex: '',
    });
    const res = await queryWarningIndex({
      type: value,
    });
    if (res.code === 200) {
      setWarningEnum(res.rows);
    } else {
      message.error(res.msg);
    }
  };

  useEffect(() => {
    getTypeEnum();
    formRef.current.setFieldsValue(filter);
    query(filter);
  }, []);

  // 删除
  const deleteColumn = (record) => {
    confirm({
      title: '确定删除此条数据吗？',
      icon: <ExclamationCircleOutlined />,
      onOk() {
        return request('/earlywarning/warningindex/delete', {
          method: 'POST',
          data: {
            id: record.id,
          },
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
    });
  };

  // 详情
  const ShowDetails = (record) => {
    setDetailsParams(record);
    setDetails(true);
  };

  // 修改
  const editCust = (record) => {
    setPayloads(record);
    setVisible(true);
  };

  const iconChoose = (record) => {
    const showMore = document.getElementById(record.id);
    showMore.style.transform = 'rotate(180deg)';
  };

  const iconMoveout = (record) => {
    const closeAll = document.getElementById(record.id);
    closeAll.style.transform = 'rotate(0deg)';
  };

  const columns = [
    {
      title: '规则大类',
      dataIndex: 'type',
      renderFormItem: (_, { type, defaultRender, formItemProps, fieldProps, ...rest }, form) => {
        if (type === 'form') {
          return null;
        }
        const status = form.getFieldValue('state');
        if (status !== 'open') {
          return (
            <Select onSelect={getWarningEnum} {...fieldProps} {...rest}>
              {typeEnum.map((item) => {
                return (
                  <Option key={item?.id} value={item?.description}>
                    {item?.description}
                  </Option>
                );
              })}
            </Select>
          );
        }
        return defaultRender(_);
      },
    },
    {
      title: '预警规则',
      dataIndex: 'warningindex',
      renderFormItem: (_, { type, defaultRender, formItemProps, fieldProps, ...rest }, form) => {
        if (type === 'form') {
          return null;
        }
        const status = form.getFieldValue('state');
        if (status !== 'open') {
          return (
            <Select {...fieldProps} {...rest}>
              {warningEnum.map((item) => {
                return (
                  <Option key={item?.id} value={item?.warningindex}>
                    {item?.warningindex}
                  </Option>
                );
              })}
            </Select>
          );
        }
        return defaultRender(_);
      },
    },
    {
      title: '描述',
      dataIndex: 'remark',
      search: false,
    },
    {
      title: '创建人',
      dataIndex: 'createdby',
      search: false,
    },
    {
      title: '创建时间',
      dataIndex: 'createtime',
      search: false,
      valueType: 'date',
    },
    {
      title: '更新人',
      search: false,
      dataIndex: 'updatedby',
    },
    {
      title: '更新时间',
      search: false,
      valueType: 'date',
      dataIndex: 'updatetime',
    },
    {
      title: '操作',
      valueType: 'option',
      fixed: 'right',
      render: (_, record) => [
        <Fragment>
          <Access key="updata" accessible={access.compAccess('earlywarning:warningindex:update')}>
            <a ket="edit" onClick={() => editCust(record)}>
              修改
            </a>
          </Access>
          <Dropdown
            overlay={
              <Menu>
                <Menu.Item>
                  <a key="history" onClick={() => ShowDetails(record)}>
                    详情
                  </a>
                </Menu.Item>
                <Menu.Item>
                  <Access
                    key="delete"
                    accessible={access.compAccess('earlywarning:warningindex:delete')}
                  >
                    <a key="delete" onClick={() => deleteColumn(record)}>
                      删除
                    </a>
                  </Access>
                </Menu.Item>
              </Menu>
            }
          >
            <a className="ant-dropdown-link" onClick={(e) => e.preventDefault()}>
              更多 <DownOutlined />
            </a>
          </Dropdown>
        </Fragment>,
      ],
    },
  ];

  const toolBarRender = () => [
    <Access key="add" accessible={access.compAccess('earlywarning:warningindex:save')}>
      <Button
        type="primary"
        onClick={() => {
          setVisible(true);
        }}
      >
        新增
      </Button>
    </Access>,
  ];

  return (
    <PageContainer ghost title={false}>
      <ProTable
        rowKey="id"
        headerTitle="查询列表"
        options={false}
        actionRef={actionRef}
        formRef={formRef}
        columns={columns}
        pagination={pagination}
        loading={loading}
        dataSource={list}
        onSubmit={(value) => {
          query({
            ...value,
          });
        }}
        onChange={(page) => {
          query({ ...filter, ...page });
        }}
        onReset={() => {
          query({});
        }}
        toolBarRender={toolBarRender}
        search={{ span: 8, labelWidth: 'auto', collapsed: false, collapseRender: false }}
      />

      {/* 新建弹窗 */}
      <EditForm
        visible={visible}
        destroyOnClose
        payload={thisPayloads}
        onClose={() => {
          setVisible(false);
          setPayloads([]);
          query(filter);
        }}
      />

      {/* 详情弹窗 */}
      <ShowDatails
        visible={showDetails}
        destroyOnClose
        payload={details}
        onClose={() => {
          setDetails(false);
        }}
      />
    </PageContainer>
  );
};

export default connect(({ warningindex, loading }) => ({
  warningindex,
  loading: loading.effects['warningindex/query'],
}))(WarningIndex);
