import React, { useState, useEffect } from 'react';
import { Modal, Form, Select, Row, Col, Space } from 'antd';
import ProTable from '@ant-design/pro-table';
import ProCard from '@ant-design/pro-card';
import { history, connect } from 'umi';
import { querySelect, queryCredit } from '../service';

const { Option } = Select;
const FormItem = Form.Item;

const CreditEdit = ({ visible, onClose }) => {
  const [form] = Form.useForm();
  const [rowKeys, setRowKeys] = useState([]);
  const [custEnum, setCust] = useState([]);
  const [limitEnum, setLimit] = useState([]);
  const [busiEnum, setBusi] = useState([]);

  const queryEnum = async () => {
    const cust = await querySelect({ ctype: 'CustType' });
    const limit = await querySelect({ ctype: 'LimitType' });
    const busi = await querySelect({ ctype: 'BusinessType' });
    setBusi(busi.data);
    setCust(cust.data);
    setLimit(limit.data);
  };

  useEffect(() => {
    queryEnum();
  }, []);

  const onFinish = async (values) => {
    await history.push({
      pathname: '/credit/creditApply/update',
      query: {
        values,
        rowKeys,
      },
    });
  };

  const onFinishFailed = (errorInfo) => {
    // eslint-disable-next-line no-console
    console.log('Failed:', errorInfo);
  };

  const columns = [
    {
      title: '门店名称',
      dataIndex: 'STORE_NAME',
    },
    {
      title: '卡号编码',
      dataIndex: 'CARD_CODE',
    },
    {
      title: '卡号名称',
      dataIndex: 'CARD_NAME',
    },
    {
      title: '信用锁定状态',
      dataIndex: 'CREDIT_BLOCK',
      search: false,
    },
    {
      title: '客户锁定状态',
      dataIndex: 'CUST_BLOCK',
      search: false,
    },
  ];

  const rowSelection = {
    onChange: (selectedRowKeys, selectedRows) => {
      // eslint-disable-next-line no-console
      console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
      setRowKeys(selectedRowKeys);
    },
  };

  return (
    <Modal
      width={1200}
      destroyOnClose
      title="类型选择"
      visible={visible}
      onOk={() => {
        form.submit();
      }}
      onCancel={() => {
        onClose();
      }}
    >
      <Space size="large" direction="vertical" style={{ width: '100%' }}>
        <ProCard title="类型选择" type="inner" bordered headerBordered>
          <Form form={form} onFinish={onFinish} onFinishFailed={onFinishFailed}>
            <Row gutter={64}>
              <Col span={8} key="custtype">
                <FormItem name="custtype" label="客户类型">
                  <Select>
                    {custEnum.map((item) => {
                      return (
                        <Option key={item?.id} value={item?.description}>
                          {item?.description}
                        </Option>
                      );
                    })}
                  </Select>
                </FormItem>
              </Col>
              <Col span={8} key="limittype">
                <FormItem name="limittype" label="额度类型">
                  <Select>
                    {limitEnum.map((item) => {
                      return (
                        <Option key={item?.id} value={item?.description}>
                          {item?.description}
                        </Option>
                      );
                    })}
                  </Select>
                </FormItem>
              </Col>
              <Col span={8} key="businesstype">
                <FormItem name="businesstype" label="业务类型">
                  <Select>
                    {busiEnum.map((item) => {
                      return (
                        <Option key={item?.id} value={item?.description}>
                          {item?.description}
                        </Option>
                      );
                    })}
                  </Select>
                </FormItem>
              </Col>
            </Row>
          </Form>
        </ProCard>

        <ProCard title="卡号查询" type="inner" bordered headerBordered>
          <ProTable
            options={false}
            columns={columns}
            request={async (params) => {
              const msg = await queryCredit({
                ...params,
                page: params.current,
                pageSize: params.pageSize,
              });
              return {
                data: msg.rows,
                success: true,
                total: msg.total,
              };
            }}
            pagination={{
              pageSize: 5,
            }}
            rowSelection={{
              type: 'radio',
              ...rowSelection,
            }}
            search={{ span: 6 }}
          />
        </ProCard>
      </Space>
    </Modal>
  );
};

export default connect(({ creditTerms, loading }) => ({
  creditTerms,
  loading: loading.effects['creditTerms/query'],
}))(CreditEdit);
