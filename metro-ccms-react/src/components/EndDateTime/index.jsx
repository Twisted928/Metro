import React from 'react';
import { DatePicker } from 'antd';
import moment from 'moment';
import './index.less';

const EndDateTime = ({ value, onChange, disabled, DateFrom }) => {
  const dateChange = (date) => {
    onChange(date);
  };

  // 失效日期只能选择今天或今天之后的时间
  const disabledDate = (date) => {
    if (!date) {
      return false;
    }
    return date < (DateFrom || moment().subtract(1, 'days'));
  };

  return (
    <DatePicker
      placeholder=""
      disabled={disabled}
      value={value}
      onChange={dateChange}
      disabledDate={disabledDate}
    />
  );
};

export default EndDateTime;
