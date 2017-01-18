package com.loan.common.utils;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * FileName: com.loan.common.utils.MyDateFormatter.java
 * Author: wangyingjie
 * Email: m13803851175@163.com
 * Date: 2016/12/29 15:06
 * Description:
 * History:
 * <Author>      <Time>    <version>    <desc>
 * wangyingjie   15:06    1.0          Create
 */
public class MyDateFormatter implements Formatter<Date> {

    @Override
    public String print(Date object, Locale locale) {
        return DateUtils.getFormattedString(object,"yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        Date date = null;
        try {
            date = DateUtils.getFormatDate14(text);
        } catch (Exception e) {
            try {
                date = DateUtils.getFormatDate8(text);
            }catch (Exception e1){
                throw new ParseException(e1.getMessage(),0);
            }
        }
        return date;
    }
}
