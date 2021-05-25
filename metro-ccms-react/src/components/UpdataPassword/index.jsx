import React from 'react';
import ProForm, { ProFormText, ProFormCaptcha } from '@ant-design/pro-form';
import { message, Button } from 'antd';
import { history } from 'umi';
import styles from './index.less';

const waitTime = (time = 100) => {
  return new Promise((resolve) => {
    setTimeout(() => {
      resolve(true);
    }, time);
  });
};

const UpdataPassWord = () => {
  // // 60s倒计时
  // const settime = () => {
  //   if (countdown === 0) {
  //     setBtuText('重新发送');
  //     setDisabled(false);
  //     countdown = 60;
  //   } else {
  //     setBtuText(`重新发送(${countdown})`);
  //     countdown -= 1;
  //     setDisabled(true);
  //     setTimeout(() => {
  //       settime();
  //     }, 1000);
  //   }
  // };

  const layout = {
    labelCol: { span: 5 },
    wrapperCol: { span: 19 },
  };

  // const onFinish = () => {};
  return (
    <div>
      <div className={styles.updataTitle}>修改密码</div>
      <div className={styles.updataForm}>
        <div className={styles.wrap}>
          <ProForm
            layout="inline"
            {...layout}
            onFinish={async () => {
              await waitTime(2000);
              message.success('提交成功');
            }}
            submitter={{
              searchConfig: {
                resetText: '重置',
                submitText: '提交',
              },
              render: () => {
                return [
                  <Button
                    style={{ marginLeft: '100px' }}
                    type="button"
                    type="primary"
                    key="submit"
                    onClick={() => {}}
                  >
                    提交
                  </Button>,
                  <Button type="button" key="rest" onClick={() => history.goBack()}>
                    返回
                  </Button>,
                ];
              },
            }}
          >
            <ProFormText
              label="输入新密码"
              name="aaa"
              placeholder=""
              rules={[
                {
                  required: true,
                  message: '请输入新密码!',
                },
              ]}
            />
            <ProFormText
              label="确认新密码"
              name="aaa"
              placeholder=""
              rules={[
                {
                  required: true,
                  message: '请确认新密码!',
                },
              ]}
            />
            <ProFormCaptcha
              captchaProps={{
                size: 'default',
              }}
              label="登录邮箱"
              phoneName="email"
              name="email"
              rules={[
                {
                  required: true,
                  message: '请输入登录邮箱',
                },
              ]}
              placeholder=""
              onGetCaptcha={async (email) => {
                await waitTime(1000);
                message.success(`邮箱 ${email} 验证码发送成功!`);
              }}
            />
            <ProFormText
              label="输入验证码"
              name="aaa"
              placeholder=""
              rules={[
                {
                  required: true,
                  message: '请输入验证码!',
                },
              ]}
            />
          </ProForm>
        </div>
      </div>
    </div>
  );
};

export default UpdataPassWord;
