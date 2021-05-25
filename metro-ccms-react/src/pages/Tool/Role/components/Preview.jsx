import { message, Tabs } from 'antd';
import React, { useEffect, useState } from 'react';
import { preview } from '../service';
import styles from './style.less';

const { TabPane } = Tabs;

const PreviewComp = ({ row }) => {
  const [data, setData] = useState({});

  const [loading, setLoading] = useState(false);

  // 进入页面查询信息
  const queryData = async () => {
    if (!row.tableId) {
      return;
    }

    setLoading(true);
    const { code, data: responData } = await preview(row.tableId);
    if (code === 200) {
      // const roleResourceDos = responData.roleResourceDos.map((i) => `${i}`);
      // const tempresponData = { ...responData, roleResourceDos };
      setData(responData);
    } else {
      message.error('加载失败');
    }
    setLoading(false);
  };

  useEffect(() => {
    queryData();
  }, []);

  return (
    <Tabs defaultActiveKey="1" loading={loading}>
      <TabPane tab="controller.java" key="1">
        <pre className={styles.pre}>{data['vm/java/controller.java.vm']}</pre>
      </TabPane>
      <TabPane tab="domain.java" key="2">
        <pre className={styles.pre}>{data['vm/java/domain.java.vm']}</pre>
      </TabPane>
      <TabPane tab="mapper.java" key="3">
        <pre className={styles.pre}>{data['vm/java/mapper.java.vm']}</pre>
      </TabPane>
      <TabPane tab="mapper.xml" key="9">
        <pre className={styles.pre}>{data['vm/xml/mapper.xml.vm']}</pre>
      </TabPane>
      <TabPane tab="service.java" key="4">
        <pre className={styles.pre}>{data['vm/java/service.java.vm']}</pre>
      </TabPane>
      <TabPane tab="serviceImpl.java" key="5">
        <pre className={styles.pre}>{data['vm/java/serviceImpl.java.vm']}</pre>
      </TabPane>
      <TabPane tab="service.js" key="6">
        <pre className={styles.pre}>{data['vm/js/service.js.vm']}</pre>
      </TabPane>
      <TabPane tab="sql.vm" key="7">
        <pre className={styles.pre}>{data['vm/sql/sql.vm']}</pre>
      </TabPane>
      <TabPane tab="index.vue" key="8">
        <pre className={styles.pre}>{data['vm/vue/index.vue.vm']}</pre>
      </TabPane>
    </Tabs>
  );
};

export default PreviewComp;
