package com.loan.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class GenericsUtils {

    /**
     *
     * @param objClass
     * @param interfaceClass
     * @return
     */
    public static Class getInterfaceActualClass(Class objClass , Class interfaceClass){

        return (Class)getInterfaceActualType(objClass,interfaceClass);
    }

    public static Type getInterfaceActualType(Class objClass , Class interfaceClass){

        Type[] types = getInterfaceActualTypeArguments(objClass, interfaceClass);
        if( null == types )
            return Object.class;
        return types[0];
    }

    public static Type[] getInterfaceActualTypeArguments(Class objClass , Class interfaceClass){

        try{
            if( null == objClass  ||  null == interfaceClass || objClass.getGenericInterfaces().length < 1)
                return null;

            Type[] types = objClass.getGenericInterfaces();
            for(Type type:types){
                if(type instanceof ParameterizedType) {
                    ParameterizedType parameterizedType = ( ParameterizedType)type;
                    if(parameterizedType.getRawType()== interfaceClass ){
                        return parameterizedType.getActualTypeArguments();
                    }
                }
            }
        }finally {

        }
        return null;
    }


    public static Type[] getSuperclassTypeArguments(Class objClass){
        try{
            if( null == objClass )
                return null;

            Type type = objClass.getGenericSuperclass();
            if(type instanceof ParameterizedType) {
                Type[] types = ((ParameterizedType) type).getActualTypeArguments();
                return types;
            }
        }finally {

        }
        return null;
    }
} 

