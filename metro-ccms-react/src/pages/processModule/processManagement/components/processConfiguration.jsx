/* eslint-disable no-multi-assign */
import React, { useState, useEffect, useRef } from 'react';
import { Card, Input, Form } from 'antd';
import { PageContainer } from '@ant-design/pro-layout';
import styles from '../index.less';

const FormItem = Form.Item;
const { TextArea } = Input;

const operator = [
  { value: '>', key: '>', name: '大于' },
  { value: '<', key: '<', name: '小于' },
  { value: '>=', key: '>=', name: '大于等于' },
  { value: '<=', key: '<=', name: '小于等于' },
  { value: '==', key: '==', name: '等于' },
  { value: '!=', key: '!=', name: '不等于' },
  { value: '()', key: '（）', name: '括号' },
  { value: '&&', key: '&&', name: '并且' },
  { value: '||', key: '||', name: '或者' },
];

const ProcessConfiguration = () => {
  const [form] = Form.useForm();
  const inputEl = useRef();
  const inputEl1 = useRef();
  const [jscode, setJscode] = useState('');
  // const [chcode, setChcode] = useState('');

  useEffect(() => {
    inputEl.current.focus();
    form.setFieldsValue({
      text: jscode,
      // text1: chcode,
    });
  }, [form, jscode]);

  // 光标向前进位
  const setCaretPosition = (ctrl, value) => {
    ctrl.focus();
    const len = ctrl.value.length;
    if (document.selection) {
      const sel = obj.createTextRange();
      sel.moveStart('character', len - 1);
      sel.collapse();
      sel.select();
    } else if (
      typeof ctrl.selectionStart === 'number' &&
      typeof ctrl.selectionEnd === 'number' &&
      value === '()'
    ) {
      // eslint-disable-next-line no-param-reassign
      ctrl.selectionStart = ctrl.selectionEnd = len - 1;
      // insertText(msg);
    }
  };

  // 光标处插入
  const insertText = (val) => {
    const obj = document.getElementById('firstText');
    const str = val;
    if (document.selection) {
      obj.focus();
      const sel = document.selection.createRange();
      sel.text = str;
    } else if (typeof obj.selectionStart === 'number' && typeof obj.selectionEnd === 'number') {
      const startPos = obj.selectionStart;
      const endPos = obj.selectionEnd;
      const tmpStr = obj.value;
      obj.value = `${tmpStr.substring(0, startPos)}${str}${tmpStr.substring(
        endPos,
        tmpStr.length,
      )}`;
      setCaretPosition(obj);
    } else {
      obj.value += str;
    }
  };

  const operclick = (data, num) => {
    const getClass = document.getElementsByClassName('aperMount');
    const getfh = document.getElementsByClassName('fh');
    const getfhName = document.getElementsByClassName('fhname');
    const ctrl = document.getElementById('firstText');
    ctrl.focus();
    for (let i = 0; i < getClass.length; i += 1) {
      if (num === i) {
        getClass[i].style.background = '#333fff';
        getfh[i].style.color = '#fff';
        getfhName[i].style.color = '#fff';
      } else {
        getClass[i].style.background = '#fff';
        getfh[i].style.color = '#727272';
        getfhName[i].style.color = 'rgba(0, 0, 0, 0.65)';
      }
    }
    insertText(data.value);
    // const newCode = jscode + data.value;
    // setJscode(newCode);

    // setTimeout(() => {
    //   setCaretPosition(ctrl, data.value);
    // }, 100);
  };

  const attrClick = (data, num) => {
    const getClass = document.getElementsByClassName('attrMount');
    const getfh = document.getElementsByClassName('iconfont');
    const getfhName = document.getElementsByClassName('attrName');
    const ctrl = document.getElementById('firstText');
    ctrl.focus();
    for (let i = 0; i < getClass.length; i += 1) {
      if (num === i) {
        getClass[i].style.background = '#333fff';
        getfh[i].style.color = '#fff';
        getfhName[i].style.color = '#fff';
      } else {
        getClass[i].style.background = '#fff';
        getfh[i].style.color = '#727272';
        getfhName[i].style.color = 'rgba(0, 0, 0, 0.65)';
      }
    }
    insertText(data);
  };

  return (
    <PageContainer ghost title={false}>
      <Card title="规则编辑">
        <div className={styles.wrap}>
          <div className={styles.calculation}>
            <div className={styles.operating}>
              <div className={styles.operTitle}>操作符</div>
              {operator.map((item, idx) => {
                return (
                  <div
                    key={item.key}
                    onClick={() => {
                      operclick(item, idx);
                    }}
                    className={`${styles.operCon} aperMount`}
                  >
                    <span className="fh">{item.key}</span>
                    <span className="fhname">{item.name}</span>
                  </div>
                );
              })}
            </div>
            <div className={styles.attributes}>
              <div className={styles.attrTitle}>属性</div>
              <div className={`${styles.attrCon} attrMount`} onClick={() => attrClick('额度', 0)}>
                <i className="iconfont">&#xe635;</i>
                <span className="attrName">额度</span>
              </div>
              <div className={`${styles.attrCon} attrMount`} onClick={() => attrClick('账期', 1)}>
                <i className="iconfont">&#xe634;</i>
                <span className="attrName">账期</span>
              </div>
            </div>
          </div>
          <Form style={{ marginTop: '24px' }} form={form} layout="vertical">
            <FormItem
              label=""
              name="text"
              rules={[
                {
                  required: false,
                  message: '请输入公式！',
                },
              ]}
            >
              <TextArea
                id="firstText"
                style={{ fontSize: '20px' }}
                placeholder="请选择上方工具栏属性进行规则配置"
                ref={inputEl}
                rows={4}
              />
            </FormItem>
            <FormItem
              label=""
              name="text1"
              rules={[
                {
                  required: false,
                  message: '请输入公式！',
                },
              ]}
            >
              <TextArea
                style={{ fontSize: '20px' }}
                placeholder="请选择上方工具栏属性进行规则配置"
                ref={inputEl1}
                rows={4}
              />
            </FormItem>
          </Form>
        </div>
      </Card>
    </PageContainer>
  );
};

export default ProcessConfiguration;
