package com.chensq.sqli;

import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by chensq on 17-2-21.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        String str="http://www.some_domain.com/some_path?keyone=2251291083&keytwo=-76%27+union+select+1%2C2%2Cconcat_ws%280x3a%2Cuser%28%29%2Cdatabase%28%29%2Cversion%28%29%29%2C4%2C5%2C6%2C7%2C8%2C9%2C10%2C11%2C12%2C13+%2F*&keythree=7931414735";
        //str=urlDecode(str);
        String t=URLDecoder.decode(str,"UTF-8");
        System.out.println(t);
        if(t.contains("%"));
            t= URLDecoder.decode(t,"UTF-8");
        System.out.println(t);

        if(t.contains("%"))
            t=URLDecoder.decode(str,"gb2312");
        if(t.contains("%"))
            t=URLDecoder.decode(str,"gb2312");

        str=t;
        str=str.substring(str.indexOf('?')+1);
        String[] words=str.split("\\[|!|\\$|\"|#|%|&|'|\\]|\\+|:|\\(|\\)|-|\\.|\\*|\\/| |>|<|=|\\?|@|\\||\\^|~|\\{|\\}|,");
        for(String s:words){
            System.out.println(s);
        }
    }

    private static String urlDecode(String code) throws Exception{

        String tem = URLDecoder.decode(code, "iso-8859-1");
        if (tem.matches("^(?:[\\x00-\\x7f]|[\\xe0-\\xef][\\x80-\\xbf]{2})+$"))
            return URLDecoder.decode(code, "utf-8");
        else
            return URLDecoder.decode(code,"gb2312");
    }
}
