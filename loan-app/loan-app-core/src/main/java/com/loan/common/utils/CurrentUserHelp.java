package com.loan.common.utils;

import java.io.Serializable;

/**
 * FileName: com.loan.common.utils.CurrentUserHelp.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2016/12/30 11:07
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   11:07    1.0          Create
 */
public class CurrentUserHelp {

    private static ThreadLocal<Serializable> UID = new ThreadLocal<Serializable>() {};
    private static ICurrentUser delegate;

    public static void setCurrentUserID(Serializable userID){
        UID.set(userID);
    }

    public static Serializable getCurrentUserID(){

        if( null != delegate && null != delegate.getCurrentUserID()){
            return delegate.getCurrentUserID();
        }
        return CurrentUserHelp.UID.get();
    }

    public static ICurrentUser getDelegate() {
        return delegate;
    }

    public static void setDelegate(ICurrentUser delegate) {
        CurrentUserHelp.delegate = delegate;
    }
}
