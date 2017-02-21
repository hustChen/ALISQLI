package com.chensq.sqli.SqlSplit;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.MapperBase;

import java.io.IOException;
import java.net.URLDecoder;

public class SqlSplitMapper extends MapperBase{
    Record map_key;
    Record map_out;

    @Override
    public void map(long key, Record record, TaskContext context) throws IOException {

        //0:sql_id 1:id 2:value 3:label

        String str=record.getString(2);

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
        //for(String s:words){
        //    System.out.println(s);
        //}

        String label=record.getString(3);
        int tag=Integer.parseInt(label);
        map_key=context.createMapOutputKeyRecord();
        map_out=context.createMapOutputValueRecord();
        for(String word:words){
            word=word.trim();
            if(word.compareTo("")!=0){
                map_key.set(0,word);
                map_out.set(0,0);
                map_out.set(1,0);
                map_out.set(label,1);
                context.write(map_key,map_out);
            }

        }
    }
}
