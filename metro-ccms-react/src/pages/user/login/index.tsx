import { Checkbox, message, Form, Input, Button, Alert } from 'antd';
import React, { useState } from 'react';
import { useModel, history } from 'umi';
import FWKF from '@/assets/login/fwkf.svg';
import KYJR from '@/assets/login/kyjr.svg';
import SJLD from '@/assets/login/sjld.svg';
import TXH from '@/assets/login/txh.svg';
import PASS from '@/assets/login/pass.svg';
import USER from '@/assets/login/user.svg';
import LOGO from '@/assets/logo.svg';
import MetroLofin from '@/assets/login/metroLofin.svg';
import Footer from '@/components/Footer';
import { fakeAccountLogin } from '@/services/login';
import styles from './style.less';

const { NODE_ENV } = process.env;
let PATH = '/ccms';
if (NODE_ENV === 'development') {
  PATH = '/';
}

/**
 * 此方法会跳转到 redirect 参数所在的位置
 */
const replaceGoto = () => {
  // const urlParams = new URL(window.location.href);
  // const params = getPageQuery();
  // let { redirect } = params as { redirect: string };
  // if (redirect) {
  //   const redirectUrlParams = new URL(redirect);
  //   if (redirectUrlParams.origin === urlParams.origin) {
  //     redirect = redirect.substr(urlParams.origin.length);
  //     if (redirect.match(/^\/.*#/)) {
  //       redirect = redirect.substr(redirect.indexOf('#'));
  //     }
  //   } else {
  //     window.location.href = PATH;
  //     return;
  //   }
  // }
  // window.location.href = urlParams.href.split(urlParams.pathname)[0] + (redirect || '/');
  window.location.href = PATH;
};

const Login: React.FC<{}> = () => {
  const [form] = Form.useForm();
  const [userLoginState, setUserLoginState] = useState({});
  const [submitting, setSubmitting] = useState(false);
  const [errorLogin, setErrorLogin] = useState(false);

  const { refresh } = useModel('@@initialState');
  const [autoLogin, setAutoLogin] = useState(true);

  const handleSubmit = async () => {
    const values = await form.validateFields();
    setSubmitting(true);
    try {
      // 登录
      const msg = await fakeAccountLogin({ ...values });
      if (msg.code === 200) {
        message.success('登录成功！');
        setErrorLogin(false);
        msg.token && localStorage.setItem('Authorization', msg.token);
        replaceGoto();
        setTimeout(() => {
          refresh();
        }, 0);
        return;
      }
      if (msg.code === 500) {
        setErrorLogin(true);
      }
      // 如果失败去设置用户错误信息
      setUserLoginState(msg);
    } catch (error) {
      message.error('登录失败，请重试！');
    }
    setSubmitting(false);
  };

  const stopCopy = (event) => {
    if (window.event && !event) {
      event = window.event;
    }
    const ref = event.target || event.srcElement;
    if (ref.tagName === 'IMG') {
      if (event.button === 2 || event.button === 3) {
        alert('对不起,本网站不提供图片右键功能!');
        return false;
      }
    }
  };

  const forgetPassWord = () => {
    history.push('/user/updataPassword');
  };

  const userPrefix = <img src={USER} alt="" />;
  const passPrefix = <img src={PASS} alt="" />;

  return (
    <div className={styles.wrap}>
      <div className={styles.topHeader}>
        <img src={LOGO} alt="" />
        <span>麦德龙风控管理系统</span>
      </div>
      <div className={styles.container}>
        <div className={styles.leftWrap}>
          <div className={styles.descAndImg}>
            <div className={styles.svgStyle}>
              <img
                onMouseDown={(e) => {
                  stopCopy(e);
                }}
                src={KYJR}
                alt=""
              />
            </div>
            <div className={styles.desc}>
              <div>销售部门</div>
              <div>
                支持业务增长扩张及平衡风险；提供不同场景的授信组合分析；及时提示客户风险、
                价值及跟进建议；提供指引辅导客户提升其自身信用管理水平。
              </div>
            </div>
          </div>
          <div className={styles.descAndImg}>
            <div className={styles.svgStyle}>
              <img
                onMouseDown={(e) => {
                  stopCopy(e);
                }}
                src={SJLD}
                alt=""
              />
            </div>
            <div className={styles.desc}>
              <div>风控部门</div>
              <div>
                提升信用管理专业性；有效支持各产业发展，增强风控与业务、财务的协同；体现
                风控对业务的价值创造。
              </div>
            </div>
          </div>
          <div className={styles.descAndImg}>
            <div className={styles.svgStyle}>
              <img
                onMouseDown={(e) => {
                  stopCopy(e);
                }}
                src={FWKF}
                alt=""
              />
            </div>
            <div className={styles.desc}>
              <div>财务部门</div>
              <div>
                有效的信用管理政策指引支持与业务的互动；高效的管理工具支持不同场景的评估、
                分析、监控；高效、可靠、实时。
              </div>
            </div>
          </div>
          <div className={styles.descAndImg}>
            <div className={styles.svgStyle}>
              <img
                onMouseDown={(e) => {
                  stopCopy(e);
                }}
                src={TXH}
                alt=""
              />
            </div>
            <div className={styles.desc}>
              <div>公司经营管理者</div>
              <div>打造智慧风控管理平台，与集团发展战略匹配；支持绩效提升。</div>
            </div>
          </div>
        </div>
        <div className={styles.rightWrap}>
          <img src={MetroLofin} alt="" />
          <div className={styles.userpassStyle}>账号密码登录</div>

          <Alert
            style={{ display: errorLogin ? 'flex' : 'none', marginBottom: '24px' }}
            message="用户不存在/密码错误"
            type="error"
            showIcon
          />

          <Form layout="vertical" form={form} hideRequiredMark>
            <Form.Item
              label="用户名"
              name="username"
              rules={[
                {
                  required: true,
                  message: '请输入用户名!',
                },
              ]}
            >
              <Input style={{ height: '40px' }} prefix={userPrefix} />
            </Form.Item>
            <Form.Item
              label="密码"
              name="password"
              rules={[
                {
                  required: true,
                  message: '请输入密码！',
                },
              ]}
            >
              <Input.Password style={{ height: '40px' }} prefix={passPrefix} />
            </Form.Item>
          </Form>
          <div>
            <Checkbox checked={autoLogin} onChange={(e) => setAutoLogin(e.target.checked)}>
              <span className={styles.loginfree}>自动登录</span>
            </Checkbox>
            <a className={styles.forgetPass} onClick={forgetPassWord}>
              忘记密码
            </a>
          </div>
          <Button
            type="primary"
            style={{ width: '100%', height: '40px', marginTop: '32px' }}
            loading={submitting}
            onClick={() => {
              handleSubmit();
            }}
          >
            登录
          </Button>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Login;
