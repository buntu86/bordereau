package com.bordereau.utils;

public class Log {
    
    public static void msg(int i, String str)
    {
        if(i==0)
            System.out.print("[ V ] ");
        else if(i==1)
            System.out.print("[ X ] ");

        System.out.print(new Exception().getStackTrace()[1].getClassName() + " | ");

        System.out.println(str);
    }
}
