package com.loan.app.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.query.SortQuery;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2011-2015 ���ݴ�
 * FileName: com.loan.app.redis.RedisUtils.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2017/1/3 16:10
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   16:10    1.0          Create
 */
public class RedisUtils {

    private static RedisTemplate redisTemplate;

    /**
     *���� redisTemplate
     * @param redisTemplate
     */
    public static void setRedisTemplate(RedisTemplate redisTemplate){
        RedisUtils.redisTemplate = redisTemplate;
    }

    /**
     * ��ȡ redisTemplate
     * @return
     */
    public static RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    /**
     * ������ִ��redis����( �����ӳػ�����ʹ��ͬһconnect )
     * @param sessionCallback
     * @param <T>
     * @return
     */
    public static <T> T execute ( SessionCallback<T> sessionCallback ){

        return (T) getRedisTemplate().execute(sessionCallback);
    }

    /**
     * ������ִ��redis����
     * @param sessionCallback
     * @param <T>
     * @return
     */
    public static <T> T executeInTransactional (final SessionCallback<T> sessionCallback){

        SessionCallback transactionalSessionCallback= new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {

                operations.multi();
                Object result = sessionCallback.execute(operations);
                operations.exec();
                return result;
            }
        };
        return (T) execute(transactionalSessionCallback);
    }

    /**
     * ��ȡָ�� key ��ʣ�ൽ��ʱ��
     * @param key
     * @return
     */
    public static Long getExpire(Object key ){
        return getRedisTemplate().getExpire(key);
    }

    /**
     * ��ȡָ�� key ��ʣ�ൽ��ʱ��
     * @param key
     * @param timeUnit  ʱ��ĵ�λ
     * @return
     */
    public static Long getExpire(Object key,TimeUnit timeUnit){
        return getRedisTemplate().getExpire(key,timeUnit);
    }

    /**
     * ����ָ��key ָ��ʱ����ڵ���
     * @param key
     * @param timeout   ���ʱ�����
     * @param unit      ʱ�䵥λ
     * @return
     */
    public static boolean expire(Object key,long timeout, final TimeUnit unit){
        return getRedisTemplate().expire(key, timeout, unit);
    }

    /**
     * ����ָ�� key ��ָ��ʱ�䵽��
     * @param key
     * @param date  ָ����ʱ��
     * @return
     */
    public static boolean expireAt(Object key,java.util.Date date){
        return getRedisTemplate().expireAt(key,date);
    }

    /**
     * �Ƿ����ĳ�� key
     * @param key
     * @return
     */
    public static boolean hasKey(Object key){

        return getRedisTemplate().hasKey(key);
    }

    public static boolean persist(Object key){

        return getRedisTemplate().persist(key);
    }


    /**
     * ������ָ���� key
     * @param oldKey ԭʼkey
     * @param newKey ��key
     * @return
     */
    public static boolean renameIfAbsent(Object oldKey,Object newKey){
        if( oldKey == null || newKey == null )
            return false;
        if( !oldKey.getClass().equals( newKey.getClass() ) )
            return false;

        return getRedisTemplate().renameIfAbsent(oldKey, newKey);
    }

    /**
     * ������ָ���� key
     * @param oldKey ԭʼkey
     * @param newKey ��key
     * @return
     */
    public static void rename(Object oldKey,Object newKey){
        if( oldKey == null || newKey == null )
            return ;
        if( !oldKey.getClass().equals( newKey.getClass() ) )
            return ;

        getRedisTemplate().rename(oldKey, newKey);
    }

    /**
     * pubsub functionality on the template
     * @param channel
     * @param message
     */
    public static void convertAndSend(String channel,Object message){

        getRedisTemplate().convertAndSend(channel,message);
    }


    /**
     * ��ȡ ָ�����ʽ�� keys
     * @param pattern ���ʽ
     * @return
     */
    public static Set keys(Object pattern){

        return getRedisTemplate().keys(pattern);
    }

    /**
     * ɾ��ָ�� key
     * @param key
     */
    public static void delete(Object key){

        getRedisTemplate().delete(key);
    }

    /**
     * ɾ��ָ�����ϵ� key
     * @param key
     */
    public static void delete(Collection key){

        getRedisTemplate().delete(key);
    }

    /**
     * ������� key
     * @return
     */
    public static Object randomKey(){

        return getRedisTemplate().randomKey();
    }

    /**
     * ��ָ������ �ƶ���ָ���� db
     * @param key       ָ����key
     * @param dbIndex   db������
     * @return
     */
    public static Boolean move(Object key, int dbIndex) {
        return getRedisTemplate().move(key, dbIndex);
    }


    /**
     * ��һ�� key ����������hash��Ĳ���(  ���� map )
     * @param key
     * @param <HK>
     * @param <HV>
     * @return
     */
    public static <H,HK, HV> BoundHashOperations<H, HK, HV> boundHashOps(Object key) {
        return getRedisTemplate().boundHashOps(key);
    }

    /**
     * ��һ�� key ����������set��Ĳ���(  ���� set )
     * @param key
     * @return
     */
    public static BoundZSetOperations boundZSetOps(Object key) {
        return getRedisTemplate().boundZSetOps(key);
    }

    /**
     * �����ļ���
     * @param query
     * @param bulkMapper
     * @param <T>
     * @return
     */
    public static <T> List<T> sort(SortQuery query, BulkMapper bulkMapper) {
        return getRedisTemplate().sort(query, bulkMapper);
    }

    /**
     *  ִ��redis ����
     * @param action ����
     * @param exposeConnection �Ƿ�ʹ��ԭ����redis����
     * @return
     */
    public static Object execute(RedisCallback action, boolean exposeConnection) {
        return getRedisTemplate().execute(action, exposeConnection);
    }


    /**
     * ��ȡ�������
     * @return
     */
    public static ValueOperations opsForValue() {
        return getRedisTemplate().opsForValue();
    }

    /**
     * ��ָ�� key ����ȡ��set�����ľ��
     * @param key
     * @return
     */
    public static BoundSetOperations boundSetOps(Object key) {
        return getRedisTemplate().boundSetOps(key);
    }

    /**
     * ��ȡָ�� key �����ݴ洢����
     * @param key
     * @return
     */
    public static DataType type(Object key) {
        return getRedisTemplate().type(key);
    }

    /**
     * ����
     * @param query
     * @param storeKey
     * @return
     */
    public static Long sort(SortQuery query, Object storeKey) {
        return getRedisTemplate().sort(query, storeKey);
    }

    /**
     * ִ�� redis ����
     * @param action
     * @return
     */
    public static List<Object> executePipelined(RedisCallback action) {
        return getRedisTemplate().executePipelined(action);
    }

    /**
     * ��ȡ hash �����ľ��
     * @param <HK>
     * @param <HV>
     * @return
     */
    public static <HK, HV> HashOperations opsForHash() {
        return getRedisTemplate().opsForHash();
    }

    /**
     * �����ļ���
     * @param query
     * @return
     */
    public static List sort(SortQuery query) {
        return getRedisTemplate().sort(query);
    }

    /**
     * zset �����ľ��
     * @return
     */
    public static ZSetOperations opsForZSet() {
        return getRedisTemplate().opsForZSet();
    }

    /**
     * ��ȡ set �����ľ��
     * @return
     */
    public static SetOperations opsForSet() {
        return getRedisTemplate().opsForSet();
    }

    /**
     * ��ȡ list ����
     * @return
     */
    public static ListOperations opsForList() {
        return getRedisTemplate().opsForList();
    }

    /**
     * ��ȡָ�� key �Ĳ������
     * @param key
     * @return
     */
    public static BoundValueOperations boundValueOps(Object key) {
        return getRedisTemplate().boundValueOps(key);
    }

    /**
     * ��ȡָ�� key ��list�������
     * @param key
     * @return
     */
    public static BoundListOperations boundListOps(Object key) {
        return getRedisTemplate().boundListOps(key);
    }

    /**
     * ִ�� redis ����
     * @param session
     * @return
     */
    public static List<Object> executePipelined(SessionCallback session) {
        return getRedisTemplate().executePipelined(session);
    }


/*###########################################################  ������ʹ�ù����ݲ�����  ########################################################################*/

//    /**
//     * ����
//     * @param query
//     * @param resultSerializer
//     * @param <T>
//     * @return
//     */
//    public static <T> List<T> sort(SortQuery query, RedisSerializer resultSerializer) {
//        return getRedisTemplate().sort(query, resultSerializer);
//    }
//    public static Object execute(RedisCallback action, boolean exposeConnection, boolean pipeline) {
//        return getRedisTemplate().execute(action, exposeConnection, pipeline);
//    }
//
//    public static Object execute(RedisScript script, List keys, Object... args) {
//        return getRedisTemplate().execute(script, keys, args);
//    }
//
//    public static List<Object> executePipelined(RedisCallback action, RedisSerializer resultSerializer) {
//        return getRedisTemplate().executePipelined(action, resultSerializer);
//    }
//    public static <T, S> List<T> sort(SortQuery query, BulkMapper bulkMapper, RedisSerializer resultSerializer) {
//        return getRedisTemplate().sort(query, bulkMapper, resultSerializer);
//    }
//
//    public static Object execute(RedisScript script, RedisSerializer argsSerializer, RedisSerializer resultSerializer, List keys, Object... args) {
//        return getRedisTemplate().execute(script, argsSerializer, resultSerializer, keys, args);
//    }
//
//    public static List<Object> executePipelined(SessionCallback session, RedisSerializer resultSerializer) {
//        return getRedisTemplate().executePipelined(session, resultSerializer);
//    }
//
//    public static byte[] dump(Object key){
//
//        return getRedisTemplate().dump(key);
//    }
//
//    public static void restore(Object key,  byte[] value, long timeToLive, TimeUnit unit){
//
//        getRedisTemplate().restore(key, value, timeToLive, unit);
//    }
/*###########################################################  ������ʹ�ù����ݲ�����  ########################################################################*/





/*###########################################################  �����๦���ݲ�����  ########################################################################*/

//
//    public static void killClient( String host,  int port){
//        getRedisTemplate().killClient(host,port);
//    }
//
//    public static void afterPropertiesSet() {
//        getRedisTemplate().afterPropertiesSet();
//    }
//
//    public static void setEnableTransactionSupport(boolean enableTransactionSupport) {
//        getRedisTemplate().setEnableTransactionSupport(enableTransactionSupport);
//    }
//
//    public static void unwatch() {
//        getRedisTemplate().unwatch();
//    }
//
//    public static void discard() {
//        getRedisTemplate().discard();
//    }
//
//    public static RedisSerializer<?> getHashKeySerializer() {
//        return getRedisTemplate().getHashKeySerializer();
//    }
//
//    public static void setExposeConnection(boolean exposeConnection) {
//        getRedisTemplate().setExposeConnection(exposeConnection);
//    }
//
//    public static void setHashValueSerializer(RedisSerializer hashValueSerializer) {
//        getRedisTemplate().setHashValueSerializer(hashValueSerializer);
//    }
//
//    public static void setDefaultSerializer(RedisSerializer serializer) {
//        getRedisTemplate().setDefaultSerializer(serializer);
//    }
//
//    public static void watch(Object key) {
//        getRedisTemplate().watch(key);
//    }
//
//    public static RedisSerializer<?> getHashValueSerializer() {
//        return getRedisTemplate().getHashValueSerializer();
//    }
//
//    public static List<RedisClientInfo> getClientList() {
//        return getRedisTemplate().getClientList();
//    }
//
//    public static List<Object> exec() {
//        return getRedisTemplate().exec();
//    }
//
//    public static void setScriptExecutor(ScriptExecutor scriptExecutor) {
//        getRedisTemplate().setScriptExecutor(scriptExecutor);
//    }
//
//    public static void setValueSerializer(RedisSerializer serializer) {
//        getRedisTemplate().setValueSerializer(serializer);
//    }
//
//    public static RedisSerializer<?> getValueSerializer() {
//        return getRedisTemplate().getValueSerializer();
//    }
//
//    public static void setConnectionFactory(RedisConnectionFactory connectionFactory) {
//        getRedisTemplate().setConnectionFactory(connectionFactory);
//    }
//
//    public static void setKeySerializer(RedisSerializer serializer) {
//        getRedisTemplate().setKeySerializer(serializer);
//    }
//
//    public static RedisConnectionFactory getConnectionFactory() {
//        return getRedisTemplate().getConnectionFactory();
//    }
//
//    public static void setEnableDefaultSerializer(boolean enableDefaultSerializer) {
//        getRedisTemplate().setEnableDefaultSerializer(enableDefaultSerializer);
//    }
//
//    public static void setStringSerializer(RedisSerializer stringSerializer) {
//        getRedisTemplate().setStringSerializer(stringSerializer);
//    }
//
//    public static void slaveOfNoOne(){
//        getRedisTemplate().slaveOfNoOne();
//    }
//
//    public static void multi() {
//        getRedisTemplate().multi();
//    }
//
//    public static void slaveOf(String host, int port) {
//        getRedisTemplate().slaveOf(host, port);
//    }
//
//    public static void watch(Collection keys) {
//        getRedisTemplate().watch(keys);
//    }
//
//    public static RedisSerializer<?> getKeySerializer() {
//        return getRedisTemplate().getKeySerializer();
//    }
//
//    public static boolean isExposeConnection() {
//        return getRedisTemplate().isExposeConnection();
//    }
//
//    public static List<Object> exec(RedisSerializer valueSerializer) {
//        return getRedisTemplate().exec(valueSerializer);
//    }
//
//    public static RedisSerializer<?> getDefaultSerializer() {
//        return getRedisTemplate().getDefaultSerializer();
//    }
//
//    public static void setHashKeySerializer(RedisSerializer hashKeySerializer) {
//        getRedisTemplate().setHashKeySerializer(hashKeySerializer);
//    }
//
//    public static boolean isEnableDefaultSerializer() {
//        return getRedisTemplate().isEnableDefaultSerializer();
//    }
//
//    public static RedisSerializer<String> getStringSerializer() {
//        return getRedisTemplate().getStringSerializer();
//    }

/*###########################################################  �����๦���ݲ�����  ########################################################################*/

}
