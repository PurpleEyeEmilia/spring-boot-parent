package com.github.support.snowflake;

/**
 * Copyright (c) 2017-2018 github Company LTD.
 * All rights reserved.
 *
 * @Description: SnowflakeID 0 00000000000000000000000000000000000000000 00000 00000 000000000000
 * 第1位：符号位固定为0，代表为正数
 * 后41位：时间戳
 * 后10位：高5位为数据中心标识位，低5位为：机器标识位
 * 最后12位为：自增序列位
 *
 * @Date: Created in 2018 2018/1/20 17:55
 * @Author: pengnian
 */
public class SnowflakeGenerator {

    /**
     * 起始的时间戳 2018-05-09
     */
//    private final static long START_STMP = 1480166465631L;
    private final static long START_TIMESTAMP = 1525795200000L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;  //机器标识占用的位数
    private final static long DATA_CENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATA_CENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    /**
     * 数据中心标识(0 ~ 31)
     */
    private long dataCenterId;

    /**
     * 机器标识(0 ~ 31)
     */
    private long machineId;

    /**
     * 序列号
     */
    private long sequence = 0L;

    /**
     * 上一次时间戳
     */
    private long lastStmp = -1L;

    public SnowflakeGenerator(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATACENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("dataCenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        // 丨运算符，如果相对应位都是0，则结果为0，否则为1
        return (currStmp - START_TIMESTAMP) << TIMESTAMP_LEFT //时间戳部分
                | dataCenterId << DATA_CENTER_LEFT      //数据中心部分
                | machineId << MACHINE_LEFT            //机器标识部分
                | sequence;                            //序列号部分
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }


    public static void main(String[] args) {

        Long i = 1L;

        Long y = 2L;

        System.out.println(Long.toBinaryString(i));

        System.out.println(Long.toBinaryString(y));

        Long x = i & y;
        System.out.println(x + ":" + Long.toBinaryString(x));

        /*int i = 1 << -1;

        int x = Integer.MAX_VALUE;

        int y = Integer.MIN_VALUE;

        int z = 2 << 2;

        long a = 2L << 2;

        long b = -1L << 5;

        long c = -1L ^ (-1L << 5);

        System.out.println(i);

        System.out.println(x);

        System.out.println(y);

        System.out.println(z);

        System.out.println(a);

        System.out.println(b);

        System.out.println(c);*/

        long currentTimeMillis = System.currentTimeMillis();

        long startMillis = currentTimeMillis - 1525795200000L;

        String binaryString = Long.toBinaryString(startMillis);

        System.out.println("currentTimeMillis的binaryString：" + binaryString);

        System.out.println("id:" + ((startMillis << 22) | (3L << 17) | (3L << 12) | 1L));

        /*SnowflakeGenerator snowflakeGenerator = new SnowflakeGenerator(1, 1);

        long id = snowflakeGenerator.nextId();*/

        System.out.println((-1 ^ (-1L << 10)));

        System.out.println(1 % 1024);
        System.out.println(2 % 1024);
        System.out.println(1023 % 1024);
        System.out.println(1024 % 1024);
        System.out.println(1025 % 1024);

//        System.out.println(id);
    }

}
