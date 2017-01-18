package com.loan.app.listener;

import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.ProgressListener;

/**
 * Copyright (C), 2011-2015 ÎÂÖÝ´û
 * FileName: listener.FileUploadListener.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 16:27
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:27    1.0          Create
 */
public class FileUploadListener implements ProgressListener {

    private boolean complete=false;
    private long bytesRead;
    private long contentLength;
    private int items;

    public void reset(){
        complete=false;
        this.bytesRead = 0;
        this.contentLength = 0;
        this.items = 0;
    }

    public void complete(){
        complete=true;
    }

    public boolean isComplete(){
        return complete;
    }

    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        this.bytesRead = pBytesRead;
        this.contentLength = pContentLength;
        this.items = pItems;
    }

    public long getBytesRead() {
        return bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public int getItems() {
        return items;
    }
}
