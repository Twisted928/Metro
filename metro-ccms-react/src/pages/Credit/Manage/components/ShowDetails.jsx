import React, { useEffect, useRef } from 'react';
import {
    Form,
    Input,
    Row,
    Col,
    // Skeleton,
    Card,
} from 'antd';
import { history, connect, useLocation } from 'umi';
import { PageContainer } from '@ant-design/pro-layout';
import Styles from "../index.less"


const FormItem = Form.Item;

const ShowDetails = ({ dispatch /* , creditmanage: { filter, list }, loading */ }) => {
    const [form] = Form.useForm();
    const formRef = useRef();
    const location = useLocation();

    const testData = location.query.record

    const query = (param = {}) => {
        dispatch({
            type: 'creditmanage/query',
            payload: param,
        });
    };

    useEffect(() => {
        query(location.query.CUST_CODE);
    }, []);

    return (

            <PageContainer
                ghost
                title={false}
                onBack={() => {
                    history.goBack();
                }}
            >
                <Card
                    className={Styles.cardWrap}
                    title="详细信息"
                    bordered
                // loading={loading}
                >
                    <Form
                        form={form}
                        formRef={formRef}
                        layout="vertical"
                        initialValues={testData}
                        style={{ background: "none" }}

                    >
                        <Row gutter={24}>
                            <Col span={8} key={1}>
                                <FormItem
                                    name='APPLICATION_NO'
                                    label='申请单号'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>

                            <Col span={8} key={2}>
                                <FormItem
                                    name='CUST_CODE'
                                    label='客户编码'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>

                            <Col span={8} key={3}>
                                <FormItem
                                    name='CUST_NAME'
                                    label='客户名称'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>
                        </Row>

                        <Row gutter={24}>
                            <Col span={8} key={4}>
                                <FormItem
                                    name='MOD_CODE'
                                    label='模型编码'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>

                            <Col span={8} key={5}>
                                <FormItem
                                    name='APPRAISAL_DATE'
                                    label='评估日期'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>
                            <Col span={8} key={6}>
                                <FormItem
                                    name='GRADE'
                                    label='评估得分'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>
                        </Row>

                        <Row gutter={24}>
                            <Col span={8} key={7}>
                                <FormItem
                                    name='RANK'
                                    label='评级'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>

                            <Col span={8} key={8}>
                                <FormItem
                                    name='ADVICE_LIMIT'
                                    label='建议额度'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>
                            <Col span={8} key={9}>
                                <FormItem
                                    name='ADVICE_DAYS'
                                    label='建议账期'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row gutter={24}>
                            <Col span={8} key={10}>
                                <FormItem
                                    name='LIMIT'
                                    label='总发布额度'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>

                            <Col span={8} key={11}>
                                <FormItem
                                    name='IFBLACK'
                                    label='是否黑名单'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>
                            <Col span={8} key={12}>
                                <FormItem
                                    name='IFWHITE'
                                    label='是否白名单'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row gutter={24}>
                            <Col span={8} key={13}>
                                <FormItem
                                    name='IAR'
                                    label='应收金额'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>

                            <Col span={8} key={14}>
                                <FormItem
                                    name='IDUE'
                                    label='逾期金额'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>
                            <Col span={8} key={15}>
                                <FormItem
                                    name='GROUP_CODE'
                                    label='信用组编号'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>
                        </Row>
                        <Row gutter={24}>
                            <Col span={8} key={16}>
                                <FormItem
                                    name='UPDATED_BY'
                                    label='更新人'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>

                            <Col span={8} key={17}>
                                <FormItem
                                    name='UPDATE_TIME'
                                    label='更新时间'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>
                            <Col span={8} key={18}>
                                <FormItem
                                    name='CURRENCY'
                                    label='币种'
                                >
                                    <Input
                                        width='130'
                                        disabled
                                    />
                                </FormItem>
                            </Col>
                        </Row>
                    </Form>
                </Card>
            </PageContainer>
    )
}

export default connect(({ creditmanage, loading }) => ({
    creditmanage,
    loading: loading.models.creditmanage,
}))(ShowDetails);