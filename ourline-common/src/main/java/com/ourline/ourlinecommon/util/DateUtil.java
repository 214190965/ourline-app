package com.ourline.ourlinecommon.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Description 处理日期工具类
 * @author peiliang
 * @date 20210315
 */
public class DateUtil {
    /**
     * 把日期类型转换成字符串
     */
    public static String formatDateToString(Date date) {
        SimpleDateFormat format= new SimpleDateFormat("yyyyMMdd");
        String dateStr= format.format(date);
        return dateStr;
    }
}
