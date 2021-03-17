package com.ourline.ourlinecommon.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName IDGenerator
 * @Description ID生成器，利用原子操作，保证同一批次不重复，添加日期字符串，保证每天不重复
 * @date 20210315
 */
public class IDGenerator {

    private static AtomicLong atomic = new AtomicLong();

    /**
     * 生成指定长度位数的ID, 位数越长，支持的并发数越多
     *
     * @param len 范围值为18-36
     * @return
     * @Title generator
     * @Description TODO
     */
    public static String generator(int len) {

        if (len <= 17) {

            return null;
        }

        StringBuffer sb = new StringBuffer();

        int formatLen = len - 17;

        // 最大循序号
        long maxInc = getMaxInc(formatLen);

        // 17位日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

        String preStr = sdf.format(new Date());

        sb.append(preStr);

        String fixStr = "";

        long next = atomic.incrementAndGet();

        if (next > maxInc) {

            // 超过长度，循环
            atomic.getAndSet(0L);
            next = atomic.incrementAndGet();
        }

        // 不足长度补0 %01d  %d十进制，前面补0，长度为formatLen
        String formatStr = "%0" + formatLen + "d";
        fixStr = String.format(formatStr, Long.valueOf(next));

        sb.append(fixStr);

        return sb.toString();
    }

    /**
     * @param len
     * @return
     * @Title getMaxInc
     * @Description 获取长度为len的最大数值
     */
    private static long getMaxInc(int len) {

        StringBuffer sb = new StringBuffer();

        long max = 0;

        for (int i = 0; i < len; i++) {
            //设置超出位数的最大个数
            sb.append(9);
        }

        try {

            max = Long.valueOf(sb.toString());

        } catch (Exception e) {

            return Long.MAX_VALUE;

        }
        return max;
    }
}
