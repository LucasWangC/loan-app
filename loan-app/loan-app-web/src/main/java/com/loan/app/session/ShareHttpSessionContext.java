package com.loan.app.session;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

/**
 * Copyright (C), 2011-2015 温州贷
 * FileName: com.loan.app.session.ShareHttpSessionContext.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 16:04
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:04    1.0          Create
 */
public class ShareHttpSessionContext implements HttpSessionContext {

    private HttpSessionContext delegate;

    private ShareHttpSessionContext(){

    }

    public ShareHttpSessionContext(HttpSessionContext delegate){
        this.delegate = delegate;
    }



    @Override
    public HttpSession getSession(String sessionId) {
//        HttpSession httpSession = delegate.getSession(sessionId);
//        if( httpSession == null )
//            return null;
//        return new ShareSession(delegate.getSession(sessionId));
        throw new RuntimeException("共享session暂不支持此方法");
    }

    @Override
    public Enumeration<String> getIds() {
        return delegate.getIds();
    }
}
