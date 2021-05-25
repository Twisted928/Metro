import React from 'react';
import Styles from './index.less';

const FooterToolbar = ({ ...restProps }) => {
  const { children } = restProps;
  return (
    <div className={Styles.toolbar}>
      <div className={Styles.right}>{children}</div>
    </div>
  );
};

export default FooterToolbar;

