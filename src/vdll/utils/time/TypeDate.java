package vdll.utils.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hocean on 2017/5/8.
 */
public class TypeDate {

    public static void weekday(int year, int month){

    }

    public static void holiday(int year, int month){
        DateTime dt = DateTime.Zero();
        dt.setYear(year).setMonth(month);

        System.out.println(dt.get(DateTime.DAY_OF_MONTH));
        System.out.println(dt);
    }


    //返回当前年月日
    public String getNowDate()
    {
        Date date = new Date();
        String nowDate = new SimpleDateFormat("yyyy年MM月dd日").format(date);
        return nowDate;
    }

    //返回当前年份
    public int getYear()
    {
        Date date = new Date();
        String year = new SimpleDateFormat("yyyy").format(date);
        return Integer.parseInt(year);
    }

    //返回当前月份
    public int getMonth()
    {
        Date date = new Date();
        String month = new SimpleDateFormat("MM").format(date);
        return Integer.parseInt(month);
    }

    //判断闰年
    public boolean isLeap(int year)
    {
        if (((year % 100 == 0) && year % 400 == 0) || ((year % 100 != 0) && year % 4 == 0))
            return true;
        else
            return false;
    }

    //返回当月天数
    public int getDays(int year, int month)
    {
        int days;
        int FebDay = 28;
        if (isLeap(year))
            FebDay = 29;
        switch (month)
        {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                days = FebDay;
                break;
            default:
                days = 0;
                break;
        }
        return days;
    }

    //返回当月星期天数
    public int getSundays(int year, int month)
    {
        int sundays = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Calendar setDate = Calendar.getInstance();
        //从第一天开始
        int day;
        for (day = 1; day <= getDays(year, month); day++)
        {
            setDate.set(Calendar.DATE, day);
            String str = sdf.format(setDate.getTime());
            if (str.equals("星期日"))
            {
                sundays++;
            }
        }
        return sundays;
    }

    public static void main(String[] args)
    {
        holiday(1,1);
        TypeDate du = new TypeDate();
        System.out.println("今天日期是：" + du.getNowDate());
        System.out.println("本月有" + du.getDays(du.getYear(), du.getMonth()) + "天");
        System.out.println("本月有" + du.getSundays(du.getYear(), du.getMonth()) + "个星期天");
    }
}
