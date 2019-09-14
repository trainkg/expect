/*
 * 文件名称:          CalenderUtil.java
 * 版权所有@ 2013-2014 无锡城市云计算中心有限公司，保留所有权利
 * 编译器:           JDK1.7.0_25
 * 时间:             下午8:40:07
 */
package org.barley;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO: 类注释
 * 
 * <p>
 * <p>
 * @版本:       CloudView V1.8
 * <p>
 * @作者:       zhuyy
 * <p>
 * @日期:       2015年4月4日
 * <p>
 * @负责人:     zhuyy
 * <p>
 * @负责小组:   commons
 * <p>
 * <p>
 */
public class CalendarUtil
{
    private static Calendar calendar = null;
 
    /**
     * 根据类型抓取时间段
     * @param type <dt> <dl>1:最近一个小时</dl> <dl>2:最近一天</dl> <dl>3:最近一周</dl>
     * <dl>4:最近一月</dl> <dl>5:最近一季度</dl> <dl>6:最近半年</dl> <dl>7:最近一年</dl>
     * <dl>8:昨天</dl><dl>9:上周</dl> <dl>10:上月</dl> <dl>11:上季度</dl> <dl>12:上半年</dl>
     * <dl>13:去年</dl> <dt>
     * @return
     */
    public static Date[] getTime(int type)
    {
        switch (type)
        {
            case 1:
                return calcThePastHour();
            case 2:
                return calcRecentDay();
            case 3:
                return calcTheLatestWeek();
            case 4:
                return calcRecentMonth();
            case 5:
                return calcMostRecentQuarter();
            case 6:
                return calcRecentHalfYear();
            case 7:
                return calcRecentYear();
            case 8:
                return calcYesterday();
            case 9:
                return calcLastWeek();
            case 10:
                return calcLastMonth();
            case 11:
                return calcLastSeason();
            case 12:
                return calcFirstHalfYear();
            case 13:
                return calcPastYear();
            default:
                throw new IllegalArgumentException("type 只能在[1,13]之间");
        }
    }
 
    /**
     * 昨天时间段
     */
    public static Date[] calcYesterday()
    {
        calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date from = getMinDate(calendar);
        Date to = getMaxDate(calendar);
        return new Date[]{from, to};
    }
 
    /**
     * 国内习惯 上一周时间段
     */
    public static Date[] calcLastWeek()
    {
        calendar = Calendar.getInstance();
        int minus = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -minus + 1);
        Date to = getMaxDate(calendar);
        calendar.add(Calendar.DATE, -6);
        Date from = getMinDate(calendar);
        return new Date[]{from, to};
    }
 
    /**
     * 国外习惯 上一周时间段
     */
    public static Date[] calcLastWeekF()
    {
        calendar = Calendar.getInstance();
        int minus = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -minus);
        Date to = getMaxDate(calendar);
        calendar.add(Calendar.DATE, -6);
        Date from = getMinDate(calendar);
        return new Date[]{from, to};
    }
 
    /**
     * 上个月时间段
     */
    public static Date[] calcLastMonth()
    {
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date to = getMaxDate(calendar);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date from = getMinDate(calendar);
        return new Date[]{from, to};
    }
 
    /**
     * 上半年时间段
     */
    public static Date[] calcFirstHalfYear()
    {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year, 0, 1);
        Date from = getMinDate(calendar);
        calendar.set(year, 5, 30);
        Date to = getMaxDate(calendar);
        return new Date[]{from, to};
 
    }
 
    /**
     * 下半年时间段
     */
    public static Date[] calcNextHalfYear()
    {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        calendar.set(year, 6, 1);
        Date from = getMinDate(calendar);
        calendar.set(year, 11, 31);
        Date to = getMaxDate(calendar);
        return new Date[]{from, to};
    }
 
    /**
     * 去年时间段
     */
    public static Date[] calcPastYear()
    {
        calendar = Calendar.getInstance();
        int pastYear = calendar.get(Calendar.YEAR) - 1;
        calendar.set(pastYear, 0, 1);
        Date from = getMinDate(calendar);
        calendar.set(pastYear, 11, 31);
        Date to = getMaxDate(calendar);
        return new Date[]{from, to};
    }
 
    /**
     * 上一季度时间段
     */
    public static Date[] calcLastSeason()
    {
        calendar = Calendar.getInstance();
        Date[] time = new Date[2];
        Calendar c = Calendar.getInstance();
        int lastSeason = getSeason(calendar) - 1;
        if (lastSeason == 1)
        {//第一季度  
            c.set(Calendar.MONTH, Calendar.JANUARY);
            time[0] = getMinMonthDate(c);
            c.set(Calendar.MONTH, Calendar.MARCH);
            time[1] = getMaxMonthDate(c);
        }
        else if (lastSeason == 2)
        {//第二季度  
            c.set(Calendar.MONTH, Calendar.APRIL);
            time[0] = getMinMonthDate(c);
            c.set(Calendar.MONTH, Calendar.JUNE);
            time[1] = getMaxMonthDate(c);
        }
        else if (lastSeason == 3)
        {//第三季度  
            c.set(Calendar.MONTH, Calendar.JULY);
            time[0] = getMinMonthDate(c);
            c.set(Calendar.MONTH, Calendar.SEPTEMBER);
            time[1] = getMaxMonthDate(c);
        }
        else if (lastSeason == 0)
        {//去年的第四季度  
            c.set(Calendar.MONTH, Calendar.OCTOBER);
            time[0] = getMinMonthDate(c);
            c.set(Calendar.MONTH, Calendar.DECEMBER);
            time[1] = getMaxMonthDate(c);
        }
        return time;
    }
 
    /**
     * 最近一小时时间段
     */
    public static Date[] calcThePastHour()
    {
        calendar = Calendar.getInstance();
        Date to = calendar.getTime();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        Date from = calendar.getTime();
        return new Date[]{from, to};
    }
 
    /**
     * 最近一天时间段
     */
    public static Date[] calcRecentDay()
    {
        calendar = Calendar.getInstance();
        Date to = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        Date from = calendar.getTime();
        return new Date[]{from, to};
    }
 
    /**
     * 最近一周时间段
     */
    public static Date[] calcTheLatestWeek()
    {
        calendar = Calendar.getInstance();
        Date to = calendar.getTime();
        calendar.set(Calendar.WEEK_OF_MONTH, calendar.get(Calendar.WEEK_OF_MONTH) - 1);
        Date from = calendar.getTime();
        return new Date[]{from, to};
    }
 
    /**
     * 最近一个月
     */
    public static Date[] calcRecentMonth()
    {
        calendar = Calendar.getInstance();
        Date to = calendar.getTime();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        Date from = calendar.getTime();
        return new Date[]{from, to};
    }
 
    /**
     * 最近一个季度
     */
    public static Date[] calcMostRecentQuarter()
    {
        calendar = Calendar.getInstance();
        Date to = calendar.getTime();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 3);
        Date from = calendar.getTime();
        return new Date[]{from, to};
    }
 
    /**
     * 最近半年
     */
    public static Date[] calcRecentHalfYear()
    {
        calendar = Calendar.getInstance();
        Date to = calendar.getTime();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 6);
        Date from = calendar.getTime();
        return new Date[]{from, to};
 
    }
 
    /**
     * 最近一年
     */
    public static Date[] calcRecentYear()
    {
        calendar = Calendar.getInstance();
        Date to = calendar.getTime();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
        Date from = calendar.getTime();
        return new Date[]{from, to};
    }
 
    /**
     * 抓取季度序列号
     * @param calendar
     * @return
     */
    public static int getSeason(Calendar calendar)
    {
 
        int season = 0;
        int month = calendar.get(Calendar.MONTH);
        switch (month)
        {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }
 
    /**
     * 获取当天最小时间
     */
    public static Date getMinDate(Calendar calendar)
    {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
 
    /**
     * 获取当天最大时间
     */
    public static Date getMaxDate(Calendar calendar)
    {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
 
    /**
     * 获取一个月中第一天
     */
    public static Date getMinMonthDate(Calendar c)
    {
        c.set(Calendar.DAY_OF_MONTH, 1);
        return getMinDate(c);
    }
 
    /**
     * 获取一个月的最后一天
     */
    public static Date getMaxMonthDate(Calendar c)
    {
        c.add(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 0);
        return getMaxDate(c);
    }
    
    /**
     * 计算2天之间的时间差
     * @param early 大的
     * @param late 小的
     * @return
     */
    public static final int daysBetween(Date early, Date late,Calendar c) { 
        
         c.setTime(early);   
         //设置时间为0时   
         c.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         c.set(java.util.Calendar.MINUTE, 0);   
         c.set(java.util.Calendar.SECOND, 0);
         Date ed = c.getTime();
         
         c.setTime(late);
         c.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         c.set(java.util.Calendar.MINUTE, 0);   
         c.set(java.util.Calendar.SECOND, 0);   
         Date ld = c.getTime();
         
         //得到两个日期相差的天数   
         int days = ((int) (ed.getTime() / 1000) - (int) (ld.getTime() / 1000)) / 3600 / 24;   
         
        return days;   
   }   
    
    /**
     * 查询指定时间段 [from,to]散落到指定年各个季度的天数情况
     */
     public static final Map<String, Integer> breakDownDate(Date from, Date to,String year){
         Map<String, Integer> map = new HashMap<String, Integer>();
         //
         Calendar c = Calendar.getInstance();
         c.set(Calendar.YEAR, Integer.valueOf(year));
         //
         
         //1季度开始时间
         c.set(Calendar.MONTH, Calendar.JANUARY);//1.1
         Date t1Date = CalendarUtil.getMinMonthDate(c);
         //
         //2.二季度开始时间
         c.set(Calendar.MONTH, Calendar.APRIL);//4.1
         Date t2Date = CalendarUtil.getMinMonthDate(c);
         //3.三季度开始时间
         c.set(Calendar.MONTH, Calendar.JULY);//7.1
         Date t3Date = CalendarUtil.getMinMonthDate(c);
         //4.四季度开始时间
         c.set(Calendar.MONTH, Calendar.OCTOBER);
         Date t4Date = CalendarUtil.getMinMonthDate(c);//10.1
         c.add(Calendar.YEAR, 1);//
         c.set(Calendar.MONTH, Calendar.JANUARY);//来年一月一号
         Date t5Date = CalendarUtil.getMinMonthDate(c);
         
         //Q1
          map.put("Q1", dateintersection(from, to, t1Date, t2Date, c));
         //Q2
          map.put("Q2", dateintersection(from, to, t2Date, t3Date, c));
         //Q3
          map.put("Q3", dateintersection(from, to, t3Date, t4Date, c));
         //Q4
          map.put("Q4", dateintersection(from, to, t4Date, t5Date, c));
         return map;
     }
     
   /**
    * 结合调岗时间，查询指定时间段 [from,to]散落到指定年各个季度的天数情况
	* @param adjustMap key：季度   value：调岗时间
    *//*
    public static final Map<String, Integer> breakDownDate(Date from, Date to,String year, Map<Integer, Date> adjustMap){
        
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	
    	Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, Integer.valueOf(year));
        //
        
        //1季度开始时间
        c.set(Calendar.MONTH, Calendar.JANUARY);//1.1
        Date t1Date = CalendarUtil.getMinMonthDate(c);
        //
        //2.二季度开始时间
        c.set(Calendar.MONTH, Calendar.APRIL);//4.1
        Date t2Date = CalendarUtil.getMinMonthDate(c);
        //3.三季度开始时间
        c.set(Calendar.MONTH, Calendar.JULY);//7.1
        Date t3Date = CalendarUtil.getMinMonthDate(c);
        //4.四季度开始时间
        c.set(Calendar.MONTH, Calendar.OCTOBER);
        Date t4Date = CalendarUtil.getMinMonthDate(c);//10.1
        c.add(Calendar.YEAR, 1);//
        c.set(Calendar.MONTH, Calendar.JANUARY);//来年一月一号
        Date t5Date = CalendarUtil.getMinMonthDate(c);
        boolean q2Adjust = false;
        boolean q3Adjust = false;
        boolean q4Adjust = false;
        if(adjustMap != null && adjustMap.size() > 0){
        	Date adjustDate = null;
        	Set<Integer> seasons = adjustMap.keySet();
            for(Integer season: seasons){
            	adjustDate = adjustMap.get(season);
            	if(season == 1){
            		q2Adjust = true;
            		if(t1Date.compareTo(from) < 0 && (to == null || t3Date.compareTo(to) <= 0)){
                		//比较在当前季度调岗前工作时间和调岗后工作时间哪个更长
                		if(daysBetween(adjustDate, from, c) < daysBetween(t3Date, adjustDate, c)){
                			map.put("Q1", daysBetween(t2Date, adjustDate, c));
                		}else{
                			map.put("Q1", daysBetween(adjustDate, from, c));
                		}
        			}else if(t1Date.compareTo(from) >= 0 && (to != null && t3Date.compareTo(to) > 0)){
        				if(daysBetween(adjustDate, t1Date, c) < daysBetween(to, adjustDate, c)){
        					map.put("Q1", daysBetween(to, adjustDate, c));
                		}else{
                			map.put("Q1", daysBetween(adjustDate, t1Date, c));
                		}
        			}else if(t1Date.compareTo(from) < 0 && (to != null && t2Date.compareTo(to) > 0)){
        				if(daysBetween(adjustDate, from, c) < daysBetween(to, adjustDate, c)){
        					map.put("Q1", daysBetween(to, adjustDate, c));
                		}else{
                			map.put("Q1", daysBetween(adjustDate, from, c));
                		}
        			}
            	} else 
            	if(season == 2){
            		q2Adjust = true;
            		if(t2Date.compareTo(adjustDate) > 0){
            			//第一季度转岗
            			if(t1Date.compareTo(from) < 0 && (to == null || t3Date.compareTo(to) <= 0)){
                    		//比较在当前季度调岗前工作时间和调岗后工作时间哪个更长
                			map.put("Q1", daysBetween(t2Date, adjustDate, c));
                			map.put("Q2", daysBetween(t3Date, t2Date, c));
            			}else if(t1Date.compareTo(from) >= 0 && (to != null && t3Date.compareTo(to) > 0)){
            				if(t2Date.compareTo(to) < 0){
            					if(daysBetween(adjustDate, t1Date, c) < daysBetween(to, adjustDate, c)){
                					map.put("Q1", daysBetween(t2Date, adjustDate, c));
                					map.put("Q2", daysBetween(to, adjustDate, c));
                        		}else{
                        			map.put("Q1", daysBetween(adjustDate, t1Date, c));
                        			map.put("Q2", 0);
                        		}
            				}else{
            					if(daysBetween(adjustDate, t1Date, c) < daysBetween(to, adjustDate, c)){
                					map.put("Q1", daysBetween(to, adjustDate, c));
                					map.put("Q2", 0);
                        		}else{
                        			map.put("Q1", daysBetween(adjustDate, t1Date, c));
                        			map.put("Q2", 0);
                        		}
            				}
            				
            			}else if(t1Date.compareTo(from) < 0 && (to != null && t3Date.compareTo(to) > 0)){
            				if(t2Date.compareTo(from) > 0 && t2Date.compareTo(to) > 0){
            					if(daysBetween(adjustDate, from, c) < daysBetween(to, adjustDate, c)){
                					map.put("Q1", daysBetween(to, adjustDate, c));
                					map.put("Q2", 0);
                        		}else{
                        			map.put("Q1", daysBetween(adjustDate, from, c));
                        			map.put("Q2", 0);
                        		}
            				}else if(t2Date.compareTo(from) > 0 && t2Date.compareTo(to) <= 0){
            					if(daysBetween(adjustDate, from, c) < daysBetween(to, adjustDate, c)){
                					map.put("Q1", daysBetween(t2Date, adjustDate, c));
                					map.put("Q2", daysBetween(to, t2Date, c));
                        		}else{
                        			map.put("Q1", daysBetween(adjustDate, from, c));
                        			map.put("Q2", 0);
                        		}
            				}else if(t2Date.compareTo(from) <= 0){
            					if(daysBetween(adjustDate, from, c) < daysBetween(to, adjustDate, c)){
            						map.put("Q1", 0);
            						map.put("Q2", daysBetween(to, adjustDate, c));
                        		}else{
                        			map.put("Q1", 0);
                        			map.put("Q2", daysBetween(adjustDate, from, c));
                        		}
            				}
            			}
            		}else{
            			//第一季度转岗
            			if(t1Date.compareTo(from) < 0 && (to == null || t3Date.compareTo(to) <= 0)){
                    		//比较在当前季度调岗前工作时间和调岗后工作时间哪个更长
                			map.put("Q1", daysBetween(t2Date, adjustDate, c));
                			map.put("Q2", daysBetween(t3Date, t2Date, c));
            			}else if(t1Date.compareTo(from) >= 0 && (to != null && t3Date.compareTo(to) > 0)){
            				if(t2Date.compareTo(to) < 0){
            					if(daysBetween(adjustDate, t1Date, c) < daysBetween(to, adjustDate, c)){
                					map.put("Q1", daysBetween(t2Date, adjustDate, c));
                					map.put("Q2", daysBetween(to, adjustDate, c));
                        		}else{
                        			map.put("Q1", daysBetween(adjustDate, t1Date, c));
                        			map.put("Q2", 0);
                        		}
            				}else{
            					if(daysBetween(adjustDate, t1Date, c) < daysBetween(to, adjustDate, c)){
                					map.put("Q1", daysBetween(to, adjustDate, c));
                					map.put("Q2", 0);
                        		}else{
                        			map.put("Q1", daysBetween(adjustDate, t1Date, c));
                        			map.put("Q2", 0);
                        		}
            				}
            				
            			}else if(t1Date.compareTo(from) < 0 && (to != null && t3Date.compareTo(to) > 0)){
            				if(t2Date.compareTo(from) > 0 && t2Date.compareTo(to) > 0){
            					if(daysBetween(adjustDate, from, c) < daysBetween(to, adjustDate, c)){
                					map.put("Q1", daysBetween(to, adjustDate, c));
                					map.put("Q2", 0);
                        		}else{
                        			map.put("Q1", daysBetween(adjustDate, from, c));
                        			map.put("Q2", 0);
                        		}
            				}else if(t2Date.compareTo(from) > 0 && t2Date.compareTo(to) <= 0){
            					if(daysBetween(adjustDate, from, c) < daysBetween(to, adjustDate, c)){
                					map.put("Q1", daysBetween(t2Date, adjustDate, c));
                					map.put("Q2", daysBetween(to, t2Date, c));
                        		}else{
                        			map.put("Q1", daysBetween(adjustDate, from, c));
                        			map.put("Q2", 0);
                        		}
            				}else if(t2Date.compareTo(from) <= 0){
            					if(daysBetween(adjustDate, from, c) < daysBetween(to, adjustDate, c)){
            						map.put("Q1", 0);
            						map.put("Q2", daysBetween(to, adjustDate, c));
                        		}else{
                        			map.put("Q1", 0);
                        			map.put("Q2", daysBetween(adjustDate, from, c));
                        		}
            				}
            			}
            		}
            		
            	}else if(season == 3){
            		q3Adjust = true;
            		if(t3Date.compareTo(from) < 0 && (to == null || t4Date.compareTo(to) <= 0)){
                		//比较在当前季度调岗前工作时间和调岗后工作时间哪个更长
                		if(daysBetween(adjustDate, from, c) < daysBetween(t4Date, adjustDate, c)){
                			map.put("Q3", daysBetween(t4Date, adjustDate, c));
                		}else{
                			map.put("Q3", daysBetween(adjustDate, from, c));
                		}
        			}else if(t3Date.compareTo(from) >= 0 && (to != null && t4Date.compareTo(to) > 0)){
        				if(daysBetween(adjustDate, t3Date, c) < daysBetween(to, adjustDate, c)){
        					map.put("Q3", daysBetween(to, adjustDate, c));
                		}else{
                			map.put("Q3", daysBetween(adjustDate, t3Date, c));
                		}
        			}else if(t3Date.compareTo(from) < 0 && (to != null && t4Date.compareTo(to) > 0)){
        				if(daysBetween(adjustDate, from, c) < daysBetween(to, adjustDate, c)){
        					map.put("Q3", daysBetween(to, adjustDate, c));
                		}else{
                			map.put("Q3", daysBetween(adjustDate, from, c));
                		}
        			}
            	}else if(season == 4){
            		q4Adjust = true;
            		if(t4Date.compareTo(from) < 0 && (to == null || t5Date.compareTo(to) <= 0)){
                		//比较在当前季度调岗前工作时间和调岗后工作时间哪个更长
                		if(daysBetween(adjustDate, from, c) < daysBetween(t5Date, adjustDate, c)){
                			map.put("Q4", daysBetween(t5Date, adjustDate, c));
                		}else{
                			map.put("Q4", daysBetween(adjustDate, from, c));
                		}
        			}else if(t4Date.compareTo(from) >= 0 && (to != null && t5Date.compareTo(to) > 0)){
        				if(daysBetween(adjustDate, t4Date, c) < daysBetween(to, adjustDate, c)){
        					map.put("Q4", daysBetween(to, adjustDate, c));
                		}else{
                			map.put("Q4", daysBetween(adjustDate, t4Date, c));
                		}
        			}else if(t4Date.compareTo(from) < 0 && (to != null && t5Date.compareTo(to) > 0)){
        				if(daysBetween(adjustDate, from, c) < daysBetween(to, adjustDate, c)){
        					map.put("Q4", daysBetween(to, adjustDate, c));
                		}else{
                			map.put("Q4", daysBetween(adjustDate, from, c));
                		}
        			}
            	}
            }
        }
        

        if(!q2Adjust){
        	map.put("Q1", dateintersection(from, to, t1Date, t2Date, c));
        	map.put("Q2", dateintersection(from, to, t2Date, t3Date, c));
        }
        if(!q3Adjust){
        	map.put("Q3", dateintersection(from, to, t3Date, t4Date, c));
        }
        if(!q4Adjust){
        	map.put("Q4", dateintersection(from, to, t4Date, t5Date, c));
        }

        return map;
    }*/
 
    
    /**
     * 计算时间区间的交集
     * @param a1
     * @param a2
     * @param b1
     * @param b2
     * @param c
     * @return 0 没有交集
     */
    public static final int dateintersection(Date a1,Date a2,Date b1,Date b2,Calendar c){
        
        if(a2 == null){
            a2 = b2;
        }
        Date min2,max1,max2;
        int cp = a1.compareTo(b1);
        if(cp > 0){
            //min1 = b1;
            min2 = b2;
            max1 = a1;
            max2 = a2;
        }else{
            //min1 = a1;
            min2 = a2;
            max1 = b1;
            max2 = b2;
        }
        
        if(max1.before(min2)){
            if(min2.before(max2)){
                return daysBetween(min2, max1, c);
            }else{
                return daysBetween(max2, max1, c);
            }
        }
        return 0;
    }

    /**
     * (业务局限性) 一季度和二季度被合并
     * 获取某日期所处季度的最后一天的时间, 
     * @param date
     * @return
     */
	public static Date getMaxDateBySeason(Date date) {
		Date maxDate = null;
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month)
        {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
            	c.set(Calendar.MONTH, Calendar.JULY);//7.1
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
            	 c.set(Calendar.MONTH, Calendar.OCTOBER);
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
            	c.add(Calendar.YEAR, 1);
                c.set(Calendar.MONTH, Calendar.JANUARY);//来年一月一号
                break;
            default:
                break;
        }
        maxDate = getMinMonthDate(c);
		return maxDate;
	}
	
	/**
	 * (业务局限性) 一季度和二季度被合并
	 * 获取某日期所处季度的第一天的时间
	 * @param createTime
	 * @return
	 */
	public static Date getMinDateBySeason(Date date) {
		Date minDate = null;
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month)
        {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
            	c.set(Calendar.MONTH, Calendar.JANUARY);//1.1
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
            	 c.set(Calendar.MONTH, Calendar.JULY);
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
            	c.add(Calendar.YEAR, 1);
                c.set(Calendar.MONTH, Calendar.OCTOBER);
                break;
            default:
                break;
        }
        minDate = getMinMonthDate(c);
		return minDate;
	}

	/**
	 * 获取该年度最后一天
	 * @param date
	 */
	public static Date getMaxDateByYear(Date date) {
		Date maxDate = null;
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, 1);
        c.set(Calendar.MONTH, Calendar.JANUARY);//来年一月一号
        maxDate = getMinMonthDate(c);
        return maxDate;
	}
	
	/**
	 * 获取该年度第一天
	 * @param date
	 */
	public static Date getMinDateByYear(Date date) {
		Date minDate = null;
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, Calendar.JANUARY);//一月一号
        minDate = getMinMonthDate(c);
        return minDate;
	}
	
}