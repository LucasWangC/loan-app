package com.loan.app.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.loan.app.listener.FileUploadListener;
import com.loan.app.session.NoShare;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C), 2011-2015 ���ݴ�
 * FileName: com.loan.app.utils.MultipartResolver.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 16:01
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:01    1.0          Create
 */
public class MultipartResolver extends CommonsMultipartResolver {

    public static final String UPLOAD_KEY ="uploadId";
    public static final String DEFAULT_UPLOAD_ID = "DEFAULT_UPLOAD_ID";
    public static final String IS_NATIVE_SUFFIX="IS_NATIVE";
    public static final String IS_UPLOADING_SUFFIX="IS_NATIVE";
    private static final java.util.Map<String,FileUpload> FILE_UPLOAD_MAP = Maps.newConcurrentMap();

    @Override
    protected MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {
        String encoding = determineEncoding(request);
        FileUpload fileUpload = prepareFileUpload(encoding);
        try {
            beforeParse(request,fileUpload);
            java.util.List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);
            return parseFileItems(fileItems, encoding);
        } catch (FileUploadBase.SizeLimitExceededException ex) {
            throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), ex);
        } catch (FileUploadException ex) {
            throw new MultipartException("Could not parse multipart servlet request", ex);
        }finally {
            afterParse(request, fileUpload);
        }
    }

    private void beforeParse(HttpServletRequest request,FileUpload fileUpload){

        //��ȡuploadId
        String id = getUploadId(request);
        //�����ϴ���������������� map
        FILE_UPLOAD_MAP.put(id, fileUpload);
        //���������ϴ���
        request.getSession().setAttribute(id+IS_UPLOADING_SUFFIX,true);
        //���ô˷�����Ϊ�ϴ�������
        request.getSession().setAttribute(id+IS_NATIVE_SUFFIX,new NoShare(){});
        if( fileUpload.getProgressListener() instanceof FileUploadListener){
            ( (FileUploadListener)fileUpload.getProgressListener() ).reset();
        }

    }

    /**
     * �Ƿ������ϴ���
     * @param request
     * @return
     */
    public static boolean isUploading(HttpServletRequest request){
        return null != request.getSession().getAttribute( getUploadId(request) +IS_UPLOADING_SUFFIX);
    }

    /**
     * �����ļ����
     * @param request
     * @param fileUpload
     */
    private void afterParse(HttpServletRequest request,FileUpload fileUpload){

        //��ȡ�ϴ�id
        String id = getUploadId(request);
        //�Ƴ������ϴ���״��
        request.getSession().removeAttribute(id+IS_UPLOADING_SUFFIX);
        //�Ƴ����ط�����״̬
        request.getSession().removeAttribute(id+IS_NATIVE_SUFFIX);
        //�Ƴ��ϴ����
        FILE_UPLOAD_MAP.remove(id);
        ProgressListener progressListener=fileUpload.getProgressListener();
        if( progressListener instanceof FileUploadListener){
            (  (FileUploadListener) progressListener ).complete();
        }
    }

    /**
     * ��ȡ�ϴ��ļ�����id
     * @param request
     * @return
     */
    private static String getUploadId(HttpServletRequest request){
        String uploadId=request.getParameter(UPLOAD_KEY);
        return Strings.isNullOrEmpty(uploadId) ? DEFAULT_UPLOAD_ID :uploadId;
    }

    /**
     * ��ȡ�ϴ����
     * @param request
     * @return
     */
    private static FileUpload getFileUpload(HttpServletRequest request){
        return FILE_UPLOAD_MAP.get(getUploadId(request));
    }

    /**
     * ��ȡ�ϴ��ļ�������
     * @param request
     * @param defaultListener �������������,ʹ�õ�Ĭ��������
     * @return
     */
    public static FileUploadListener findFileUploadProgressListener(HttpServletRequest request,FileUploadListener defaultListener){

        FileUpload fileUpload = getFileUpload(request);

        if( null == fileUpload  )
            return null;
        ProgressListener progressListener = fileUpload.getProgressListener();
        if( null == progressListener || !(progressListener instanceof FileUploadListener)){
            fileUpload.setProgressListener(defaultListener);
            progressListener = defaultListener;
        }
        return (FileUploadListener)progressListener;
    }

    /**
     * ע��������
     * @param request
     * @param listener
     */
    public static void register(HttpServletRequest request,FileUploadListener listener){
        FileUpload fileUpload = getFileUpload(request);
        if( null != fileUpload  ){
            fileUpload.setProgressListener(listener);
        }
    }

    /**
     * �Ƿ�Ϊ�ϴ��ļ���ԭʼ������
     * @param request
     * @return
     */
    public static boolean isNative(HttpServletRequest request){
        return null != request.getSession().getAttribute( getUploadId(request)+IS_NATIVE_SUFFIX);
    }
}
