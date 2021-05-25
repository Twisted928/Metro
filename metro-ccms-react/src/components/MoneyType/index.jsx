import React, { useEffect, useCallback } from 'react';
import { connect } from 'umi';
import { Select } from 'antd';

const { Option } = Select;

const MoneyType = ({ dispatch, disabled, value, onChange, commonmodel: { basciData } }) => {
  const getMoneyType = useCallback(() => {
    dispatch({
      type: 'commonmodel/basciData',
      payload: { ctype: 'Currency' },
    });
  }, [dispatch]);

  useEffect(() => {
    getMoneyType();
  }, [getMoneyType]);

  return (
    <Select value={value} allowClear onChange={onChange} disabled={disabled} placeholder="">
      {(basciData || []).map((item) => {
        return (
          <Option key={`${item.ctype}`} value={`${item.ctype}`}>
            {item.ctype}
          </Option>
        );
      })}
    </Select>
  );
};

export default connect(({ commonmodel, loading }) => ({
  commonmodel,
  loadingBasic: loading.effects['commonmodel/basciData'],
}))(MoneyType);
