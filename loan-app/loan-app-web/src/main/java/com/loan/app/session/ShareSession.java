package com.loan.app.session;

import com.loan.app.cache.Cacheable;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2011-2015 ���ݴ�
 * FileName: com.loan.app.session.ShareSession.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 16:05
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:05    1.0          Create
 */
public class ShareSession extends HttpSessionWrapper {
    //��־
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ShareSession.class);

    //��������ӿ�
    private Cacheable cacheable;

    //�����򣬿���Ϊ����session��id
    private String domain;

    //�Ƿ��Զ�����
    private boolean cacheAutoDestroy;

    //session ����ʱ��
    private long creationTime;

    //������ʱ��
    private long lastAccessedTime;

    //session�����ʱ��
    private int maxInactiveInterval;

    //�Ƿ���session
    private boolean share = true;

    //session�洢�ڻ���������� �����ռ�
    public static final String domainKey = "entries";

    //����ʵ�ֵ�key
    public static final String cacheKey="cacheImpl";

    //����session������д����ʱ���Key
    private static final String lastWriteActionTimeKey = "lastWriteActionTime";

    //����session�Ĵ���ʱ��KEY
    private static final String creationTimeKey = "creationTime";

    //����session�������ʱ���Key
    private static final String maxInactiveIntervalKey = "maxInactiveInterval";


    @Override
    public long getCreationTime() {
        return cacheable.hasKey(this.domain) ? (Long)this.cacheable.get(domain,creationTimeKey) : creationTime;
    }

    @Override
    public long getLastAccessedTime() {
        return cacheable.hasKey(this.domain) ? (Long)this.cacheable.get(domain,lastWriteActionTimeKey) : lastAccessedTime;
    }


    @Override
    public int getMaxInactiveInterval() {
        return cacheable.hasKey(this.domain) ? (Integer)this.cacheable.get(domain,maxInactiveIntervalKey) : maxInactiveInterval;
    }

    public Cacheable getCacheImpl() {
        return cacheable;
    }

    public ShareSession(HttpSession delegate){
        this(delegate, (String) delegate.getAttribute(domainKey) );
    }

    public ShareSession(HttpSession delegate,String domain){
        this(delegate, (Cacheable) delegate.getAttribute(cacheKey), domain, true);
    }

    public ShareSession(HttpSession delegate, Cacheable cacheable, String domain,boolean cacheAutoDestroy){

        super(delegate);
        this.cacheable = cacheable;
        this.cacheAutoDestroy = cacheAutoDestroy;
        if(cacheAutoDestroy) {
            this.maxInactiveInterval = delegate.getMaxInactiveInterval();
        }else{
            this.maxInactiveInterval = -1;
        }

        if(logger.isDebugEnabled()) {
            logger.debug("//=====�����sessionId:" + domain + "==========//");
        }

        this.domain = null == domain ? this.delegate.getId() : domain;
        if(isShare() && !this.domain.equals(this.delegate.getId())){
            this.changeAttribute(domain,this.delegate.getId());
        }

        if(logger.isDebugEnabled()){
            logger.debug("//=======����ĵ�ǰ�ỰsessionIdΪ:"+this.domain+"==========//");
        }

        if(null == this.cacheable ){
            if(  logger.isWarnEnabled() ){
                logger.warn(" session ������û���ҵ������� ");
            }
        }

        if(isShare()) {
            this.delegate.setAttribute(domainKey, this.domain);
            this.delegate.setAttribute(cacheKey, this.cacheable);

            //������ʱ��
            this.updateLastWriteActionTime();
            //����sessionʧЧʱ��
            this.resetExpireOfSession();

        }

    }


    @Override
    public HttpSessionContext getSessionContext() {
        return new ShareHttpSessionContext(delegate.getSessionContext());
    }


    @Override
    public String getId() {
        if(isShare()) {
            return this.domain;
        }else{
            return this.delegate.getId();
        }
    }

    /*
       �л�session
       ���û����ʵ���Ⱥ�е���һ̨����ʱ�����ڲ������µ�session����ʱ��Ҫ����session�е�ֵת�Ƶ���session��
     */
    private void changeAttribute(String oldSessionDomain,String newSessionDomain) {

        try {
            if(cacheable.hasKey(newSessionDomain)) return;

            Map<String, Object> entries = cacheable.entries(oldSessionDomain);
            if (entries == null || entries.isEmpty()) return;

            Set<String> set = entries.keySet();
            Iterator<String> iterable = set.iterator();
            while (iterable.hasNext()) {
                String key = iterable.next();
                cacheable.put(newSessionDomain, key, entries.get(key));
            }

            this.domain = newSessionDomain;
            //��session 30��ʧЧ
            cacheable.expire(oldSessionDomain ,30, TimeUnit.SECONDS);

        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

    }

    @Override
    public void setAttribute(String key, Object value) {

        if(isShare() && !(value instanceof NoShare)){
            try{
                if(!cacheable.hasKey(domain)){
                    this.creationTime = this.delegate.getCreationTime();
                    cacheable.put(domain, creationTimeKey, this.creationTime);
                    cacheable.put(domain, maxInactiveIntervalKey, this.maxInactiveInterval);
                    this.updateLastWriteActionTime();
                    this.resetExpireOfSession();
                }

                //д���ݵ����������
                cacheable.put(domain, key, value);
            }catch (Exception e){
                if( logger.isWarnEnabled() ){
                    logger.warn(e.getMessage(),e);
                }
            }

        }else {
            delegate.setAttribute(key, value);
        }

    }

    @Override
    public Object getAttribute(String key) {

        if(isShare()){
            try{
                //�ӻ�������л�ȡ����ֵ
                Object val = cacheable.get(domain, key);
                return val;
            }catch (Exception e){

                if( logger.isWarnEnabled() ){
                    logger.warn(e.getMessage(),e);
                }
                return null;
            }
        }
        return delegate.getAttribute(key);
    }

    @Override
    public void removeAttribute(String key) {

        if(isShare()){
            try{
                //�ӻ��������ɾ������
                cacheable.delete(domain, key);
            }catch (Exception e){

                if( logger.isWarnEnabled() ){
                    logger.warn(e.getMessage(),e);
                }
            }

        }

        try {
            delegate.removeAttribute(key);
        }catch (Exception e){
            if( logger.isWarnEnabled() ){
                logger.warn(e.getMessage(),e);
            }
        }
    }

    @Override
    public Enumeration<String> getAttributeNames() {

        String[] keys = cacheable.keys(domain);
        if (keys == null || keys.length == 0) return Collections.enumeration(new ArrayList<String>());

        Enumeration<String> enumeration = Collections.enumeration(Arrays.asList(keys));
        return enumeration;
    }


    @Override
    public String[] getValueNames() {

        Enumeration<String> enumeration = this.getAttributeNames();
        if(enumeration==null || !enumeration.hasMoreElements()) return null;

        List<String> list = new ArrayList<String>();
        while (enumeration.hasMoreElements()){
            list.add(enumeration.nextElement());
        }
        String[] valueNames =  new String[list.size()];
        return list.toArray(valueNames);
    }

    @Override
    public boolean isNew() {
        return super.isNew();
    }

    @Override
    public void putValue(String s, Object o) {
        setAttribute(s, o);
    }

    @Override
    public void removeValue(String key) {
        removeAttribute(key);
    }

    @Override
    public Object getValue(String key) {
        return getAttribute(key);
    }

    /**
     * ���� SESSION ����
     * ���ٷ���ʵ�ʵ��õ� Ϊ  delegate.invalidate()
     * ֻ���ֶ����ô����ٷ��������ٲ����Ż�ͬ�������������
     *     session���ڵ����ٷ�������ͬ�������������( ��Ϊ�����������ttlʱ����session�Ĺ���ʱ����ͬ ),
     *            �Դ˽���ر���������´�����������Чsession���ڵ��µĹ���session(���������sessionʧЧ����)
     */
    @Override
    public void invalidate() {

        //����session
        if(isShare()){
            try{
                cacheable.invalidate(domain);
            }catch (Exception e){
                if( logger.isWarnEnabled() ){
                    logger.warn(e.getMessage(),e);
                }
            }
        }

        delegate.invalidate();
    }

    /**
     * ��ȡ session ���ж��������
     *       ���С�� 1 ����������
     * **/
    private int getExpire(){

        if(isShare()){
            if(cacheAutoDestroy){
                return this.getMaxInactiveInterval() -  new Long((System.currentTimeMillis() - this.getLastAccessedTime())/1000).intValue();
            }else{
                return -1;
            }
        }else{
            return delegate.getMaxInactiveInterval() - new Long((System.currentTimeMillis() - delegate.getLastAccessedTime())/1000).intValue() ;
        }

    }

    public boolean isShare(){
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }


    /*
     *���»��������������д����ʱ��
     */
    private void updateLastWriteActionTime(){

        try{
            if(this.cacheable.hasKey(this.domain)) {
                this.lastAccessedTime = System.currentTimeMillis();
                cacheable.put(this.domain, lastWriteActionTimeKey, this.lastAccessedTime);
            }
        }catch (Exception e){
            if( logger.isWarnEnabled() ){
                logger.warn(e.getMessage(),e);
            }
        }
    }

    /*
     * ����sessionʧЧʱ��
     */
    private void resetExpireOfSession(){
        if(this.cacheAutoDestroy && this.cacheable.hasKey(this.domain)) {
            //���»���ʧЧ��ʱ�� ʵ�����ڹ���
            updateCacheExpire((long) this.maxInactiveInterval, TimeUnit.SECONDS);
        }
    }

    /*
     * ���»���Ĺ���ʱ��
     */
    private void updateCacheExpire(Long interval,TimeUnit timeUnit){

        if(interval > 0){
            try{
                cacheable.expire(this.domain ,interval, timeUnit);
            }catch (Exception e){
                if( logger.isWarnEnabled() ){
                    logger.warn(e.getMessage(),e);
                }
            }
        }

    }

    /**
     * ����ָ������session
     * @param domain
     * @return
     */
    public static boolean destroyShareSessionByDomain(HttpSession session,String domain){

        if( session instanceof ShareSession && StringUtils.hasText(domain)){
            ((ShareSession)session).getCacheImpl().invalidate(domain);
            return true;
        }else if(logger.isWarnEnabled()){
            logger.warn("û����������session");
        }
        return false;
    }


    /**
     * �������ٹ���session
     * @return
     */
    public static boolean destroyShareSession(HttpSession session){

        if( session instanceof ShareSession){
            ShareSession shareSession = ((ShareSession)session);
            shareSession.invalidate();

            return true;
        }else if(logger.isWarnEnabled()){
            logger.warn("û����������session");
        }

        return false;
    }


    /**
     * ��ԭ��sessionͬ�����ٹ���session
     * @param session
     * @return
     */
    public static boolean destroyShareSessionByTimer(HttpSession session){

        if( session instanceof ShareSession){
            ShareSession shareSession = ((ShareSession)session);
            String domain = shareSession.getId();
            shareSession.getCacheImpl().expire(domain ,shareSession.getMaxInactiveInterval(), TimeUnit.SECONDS);
            return true;
        }else if(logger.isWarnEnabled()){
            logger.warn("û����������session");
        }
        return false;
    }

    /**
     * ���session��ʧЧʱ��
     * @param session
     * @return
     */
    public static boolean cleanShareSessionExpire(HttpSession session){

        if( session instanceof ShareSession){
            ShareSession shareSession = ((ShareSession)session);
            String domain = shareSession.getId();

            shareSession.getCacheImpl().expire(domain ,-1, TimeUnit.SECONDS);
            return true;

        }else if(logger.isWarnEnabled()){
            logger.warn("û����������session");
        }

        return false;
    }

    /**
     * �Ƿ��Զ�����
     * @return
     */
    public boolean isCacheAutoDestroy() {
        return cacheAutoDestroy;
    }
}
