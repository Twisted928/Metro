import React from 'react';
import LOGO from '@/assets/logo.svg';
import Password from '@/components/UpdataPassword/index';
import styles from './style.less';

const PassWordPage = () => {
  return (
    <div className={styles.wrapPass}>
      <div className={styles.topHeader}>
        <img src={LOGO} alt="" />
        <span>麦德龙风控管理系统</span>
      </div>
      <div className={styles.formMsg}>
        <Password />
      </div>
    </div>
  );
};

export default PassWordPage;
