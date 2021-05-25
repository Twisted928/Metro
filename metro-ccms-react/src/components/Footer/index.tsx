import React from 'react';
import style from './index.less';

// export default () => <DefaultFooter copyright="麦德龙商业集团有限公司" links={[]} />;
const Footer = () => {
  return (
    <div className={style.footer}>
      <div>麦德龙商业集团有限公司 2020 版权所有</div>
      <div>Metro Commercial Group Co., Ltd. 2020 All rights reserved</div>
    </div>
  );
};

export default Footer;
