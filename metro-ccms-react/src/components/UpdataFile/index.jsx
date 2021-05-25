/* eslint-disable consistent-return */
import React, { Fragment } from 'react';
import { QuestionCircleOutlined } from '@ant-design/icons';
import { Upload, Button, Tooltip, Card, Divider, message } from 'antd';
import ProTable from '@ant-design/pro-table';
import { downloadFile } from '@/services/commom';
import Styles from './index.less';

const fileType = [
  'bmp',
  'gif',
  'jpg',
  'jpeg',
  'png',
  'doc',
  'docx',
  'xls',
  'xlsx',
  'ppt',
  'pptx',
  'html',
  'htm',
  'txt',
  'rar',
  'zip',
  'gz',
  'bz2',
  'pdf',
];

const DeleteText = ({ dataSource, loadFun, changeFun, downloadBtu, deleteBtu }) => {
  const download = async (record) => {
    if (record.ctype) {
      const { attachmentName, attachmentUrl } = record;
      const params = {
        fileName: attachmentName,
        filePath: attachmentUrl,
      };
      await downloadFile('/file/downAndUpload/downloadFile', params);
    } else {
      message.info('新上传的附件无法下载！');
    }
  };

  const deleteFilled = async (data) => {
    const { attachmentUrl, id } = data;
    const param = {
      attachmentUrl,
      id,
    };
    if (deleteBtu) {
      message.info('详情页面无法删除附件！');
      return;
    }
    loadFun(id, param);
  };

  const props = {
    name: 'file',
    multiple: false,
    showUploadList: false,
    beforeUpload: (file) => {
      const filetypeName = file.name.split('.')[1];
      const fileSize = file.size / 1024 / 1024 > 30;
      const size = (file.size / 1024 / 1024).toFixed(4);
      if (!fileType.includes(filetypeName)) {
        message.error('附件类型上传错误！');
        return;
      }
      if (fileSize) {
        message.error('附件大小不能超过30M！');
        return;
      }
      const fileUpList = {
        id: file.uid,
        attachmentName: file.name,
        fileSize: size,
      };
      changeFun(file, fileUpList);
      return false;
    },
  };

  const toolBarUploadRender = (
    <Fragment>
      <Tooltip
        title="允许上传以下格式文件:
            bmp, gif, jpg, jpeg, png,
            doc, docx, xls, xlsx, ppt, pptx, html, htm, txt,
            rar, zip, gz, bz2,
            pdf"
      >
        <QuestionCircleOutlined />
      </Tooltip>
      <Upload {...props}>
        <Button style={{ marginLeft: '8px' }} type="primary">
          附件上传
        </Button>
      </Upload>
    </Fragment>
  );

  const columns = [
    {
      title: '附件名称',
      dataIndex: 'attachmentName',
    },
    {
      title: '附件大小（MB）',
      dataIndex: 'fileSize',
    },
    {
      title: '操作',
      dataIndex: 'action',
      render: (_, record) => {
        return (
          <Fragment>
            <a
              onClick={() => {
                deleteFilled(record);
              }}
            >
              删除
            </a>
            {downloadBtu ? (
              <Fragment>
                <Divider type="vertical" />
                <a
                  onClick={() => {
                    download(record);
                  }}
                >
                  下载
                </a>
              </Fragment>
            ) : (
              ''
            )}
          </Fragment>
        );
      },
    },
  ];

  return (
    <Card
      className={Styles.extraStyle}
      title="附件信息"
      extra={toolBarUploadRender}
      style={{ marginTop: '24px' }}
    >
      <ProTable
        headerTitle=""
        rowKey="id"
        bordered
        dataSource={dataSource}
        search={false}
        options={false}
        toolBarRender={false}
        pagination={false}
        columns={columns}
      />
    </Card>
  );
};

export default DeleteText;
