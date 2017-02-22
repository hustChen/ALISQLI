package com.chensq.sqli;

import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by chensq on 17-2-21.
 */
public class Main {
//    public static void main(String[] args) throws Exception {
//        String str="http://www.some_domain.com/some_path?keyone=a21bo.50862.201879-item-1002.4.nxuwNE&keytwo=1234.5%2522%2529%2529%2520AND%25209788%253D%2528SELECT%2520COUNT%2528%252A%2529%2520FROM%2520ALL_USERS%2520T1%252CALL_USERS%2520T2%252CALL_USERS%2520T3%252CALL_USERS%2520T4%252CALL_USERS%2520T5%2529--%2520AND%2520%2528%2528%2522QRas%2522%2520LIKE%2520%2522QRas&keythree=1477350526202_196&keyfour=7974750069";
//        //str=urlDecode(str);
//        String t=URLDecoder.decode(str,"UTF-8");
//        System.out.println(t);
//        if(t.contains("%"));
//            t= URLDecoder.decode(t,"UTF-8");
//        System.out.println(t);
//
//        if(t.contains("%"))
//            t=URLDecoder.decode(str,"gb2312");
//        if(t.contains("%"))
//            t=URLDecoder.decode(str,"gb2312");
//
//        str=t;
//        str=str.substring(str.indexOf('?')+1);
//        String[] words=str.split("\\[|!|\\$|\"|#|%|&|'|\\]|\\+|:|\\(|\\)|-|\\.|\\*|\\/| |>|<|=|\\?|@|\\||\\^|~|\\{|\\}|,");
//        for(String s:words){
//            System.out.println(s);
//        }
//    }

    private static String urlDecode(String code) throws Exception{

        String tem = URLDecoder.decode(code, "iso-8859-1");
        if (tem.matches("^(?:[\\x00-\\x7f]|[\\xe0-\\xef][\\x80-\\xbf]{2})+$"))
            return URLDecoder.decode(code, "utf-8");
        else
            return URLDecoder.decode(code,"gb2312");
    }
}
