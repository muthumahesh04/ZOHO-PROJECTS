import java.util.Scanner;
class modifyMonthYear
{
    static int adjustedyear(int year,String month)
    {
        if(month.equals("January")||month.equals("February"))
        {
            return year-1;
        }
        return year;
    }
    static int monthnumber(String month)
    {
        try{
            monthnumber mn=monthnumber.valueOf(month);
            return mn.getNumber();
        }
        catch(Exception e)
        {
            return 0;
        }
    }
    static int maxdaysinmonth(String Month,int year)
    {
        try{
            MaxdaysofMonth mm=MaxdaysofMonth.valueOf(Month);
            return mm.getDays(isLeapYear(year));

            }
             catch (Exception e) {
                return 0;
            }
    }
    static boolean isLeapYear(int yr)
    {
        if((yr%400==0)||(yr%4==0 && yr%100!=0))
             return true;
        return false;
    }
}
enum MaxdaysofMonth
{
    January(31),February(28),March(31),April(30),May(31),June(30),July(31),August(31),September(30),October(31),November(30),December(31);
    int Days;
    MaxdaysofMonth(int days)
    {
        Days=days;
    }
    public int getDays(boolean isleap)
    {
        if(this==February && isleap)
        {
            return 29;
        }
        return Days;
    }
}
enum monthnumber
{
     January(11),February(12),March(1),April(2),May(3),June(4),July(5),August(6),September(7),October(8),November(9),December(10);
     int number;
     monthnumber(int a)
     {
        number=a;
     }
     public int getNumber()
     {
        return number;
     }
}
public class Calendar {
    public static void main(String[] args) 
    {
        Scanner scan=new Scanner(System.in);
        System.out.print("Enter Date:");
        int date=scan.nextInt();
        scan.nextLine();
        System.out.print("Enter Month(ie:May/June):");
        String month=scan.nextLine();
        System.out.print("Enter the year");
        int year=scan.nextInt();
        scan.close();
        if(isvaliddate(date,month,year))
        {
            startingdayofmonth(date,month,year);
        }
        else
        {
               System.out.print("Invalid Date");
        }
        
    }
    static boolean isvaliddate(int date,String month,int year)
    {
        if(date<=modifyMonthYear.maxdaysinmonth(month, year))
            return true;
        else
           return false;
    }
    static void startingdayofmonth(int date,String  month,int year)
    {
        int last2digitsofyear=modifyMonthYear.adjustedyear(year,month)%100;
        int first2digitsofyear=modifyMonthYear.adjustedyear(year,month)/100;
        //Here we didnt create the object.because the method monthnumber is refered as Static in the class modifyMonthYear
        int F=1+(13*modifyMonthYear.monthnumber(month)-1)/5+(last2digitsofyear)+(last2digitsofyear/4)+(first2digitsofyear/4)-2*first2digitsofyear;
        if(F>0)
        {
            displayDay(((date%7)+(F%7))%7-1);
            displayCalendar(F%7,month,year);
        }
        else
        {
           int t=(F%7)+7;
           displayDay(((date%7)+(t%7))%7-1);
           displayCalendar(t,month,year);
        }
    }
    static void displayCalendar(int dayPos, String month, int year) 
    {
        System.out.println("\n---------------------------");
        System.out.println("  " + month + " " + year);
        System.out.println("---------------------------");
        System.out.println("  S  M  T  W  T  F  S");

        int date = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                if (i == 0 && j < dayPos) {
                    System.out.print("   ");
                } else {
                    if (date <= modifyMonthYear.maxdaysinmonth(month, year)) {
                        System.out.printf("%3d", date++);
                    }
                }
            }
            System.out.println();
        }
    }
    public static void displayDay(int d) 
    {

        String[] day = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if (d >= 0 && d < day.length) 
        {
            System.out.println("Day: " + day[d]);
        } else 
        {
            System.out.println("Invalid day number!");
        }
    }
}
