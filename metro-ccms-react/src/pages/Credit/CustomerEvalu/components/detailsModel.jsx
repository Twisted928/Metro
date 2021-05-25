import React from 'react';
import { Descriptions, Modal, Card, Button } from 'antd';
import ProTable from '@ant-design/pro-table';
import Styles from '../index.less';

const DetailsModel = ({ vis, onCancel }) => {
  const cancelModal = () => {
    onCancel(false);
  };

  const columns = [
    {
      title: '净资产收益率（%）',
      dataIndex: 'modelCode',
      align: 'right',
      width: 180,
    },
    {
      title: '总资产周转率（%）',
      dataIndex: 'modelName',
      align: 'right',
      width: 180,
    },
    {
      title: '资产负债率（%）',
      dataIndex: 'grade',
      align: 'right',
      width: 150,
    },
    {
      title: '资产规模',
      dataIndex: 'rank',
      width: 150,
    },
    {
      title: '销售（营业）增长率（%）',
      dataIndex: 'adviceLimit',
      align: 'right',
      width: 230,
    },
    {
      title: '企业规模',
      dataIndex: 'adviceDays',
      width: 150,
    },
    {
      title: '资本累积率（%）',
      dataIndex: 'pgDate',
      align: 'right',
      width: 150,
    },
    {
      title: '平均提前/逾期还款天数（天）',
      dataIndex: 'validTo',
      align: 'right',
      width: 230,
    },
    {
      title: '交易金额增长率（%）',
      dataIndex: 'validTo',
      align: 'right',
      width: 180,
    },
    {
      title: '逾期金额比例变化率（%）',
      dataIndex: 'validTo',
      width: 230,
    },
    {
      title: '成立日期/时间',
      dataIndex: 'validTo',
      valueType: 'date',
      width: 150,
    },
    {
      title: '主体类型',
      dataIndex: 'validTo',
      width: 150,
    },
    {
      title: '已抵押资产占总资产的比例（%）',
      dataIndex: 'validTo',
      align: 'right',
      width: 250,
    },
    {
      title: '对外投资情况',
      dataIndex: 'validTo',
      width: 150,
    },
    {
      title: '企业负面信息',
      dataIndex: 'validTo',
      width: 150,
    },
    {
      title: '区域风险情况',
      dataIndex: 'validTo',
      width: 150,
    },
    {
      title: '注册资本',
      dataIndex: 'validTo',
      align: 'right',
      width: 150,
    },
  ];

  return (
    <Modal
      className={Styles.modalbody}
      visible={vis}
      onCancel={cancelModal}
      width={1000}
      title="详情"
      footer={null}
    >
      <span className={Styles.basicMsg}>基本信息</span>
      <div style={{ padding: '24px' }}>
        <Descriptions title="" bordered>
          <Descriptions.Item label="评估得分" labelStyle={{ width: '175px' }}>
            99
          </Descriptions.Item>
          <Descriptions.Item label="评级" labelStyle={{ width: '175px' }}>
            S
          </Descriptions.Item>
          <Descriptions.Item label="建议额度（万元）" labelStyle={{ width: '175px' }}>
            1000
          </Descriptions.Item>
          <Descriptions.Item label="建议付款天数（天）" labelStyle={{ width: '175px' }}>
            50
          </Descriptions.Item>
          <Descriptions.Item label="付款条件01" labelStyle={{ width: '175px' }}>
            1
          </Descriptions.Item>
          <Descriptions.Item label="付款条件02" labelStyle={{ width: '175px' }}>
            2
          </Descriptions.Item>
          <Descriptions.Item label="付款条件03" labelStyle={{ width: '175px' }}>
            3
          </Descriptions.Item>
          <Descriptions.Item label="付款条件04" labelStyle={{ width: '175px' }}>
            4
          </Descriptions.Item>
        </Descriptions>
      </div>

      <span className={Styles.basicMsg}>数据列表</span>
      <div style={{ padding: '24px' }}>
        <ProTable
          headerTitle=""
          rowKey="id"
          dataSource={[]}
          search={false}
          options={false}
          toolBarRender={false}
          pagination={false}
          columns={columns}
          scroll={{ x: 3000 }}
        />
      </div>
    </Modal>
  );
};

export default DetailsModel;
