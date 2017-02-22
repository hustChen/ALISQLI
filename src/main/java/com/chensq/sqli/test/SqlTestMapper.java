package com.chensq.sqli.test;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;
import com.aliyun.odps.mapred.MapperBase;

import java.io.IOException;
import java.net.URLDecoder;

/**
 * Created by chensq on 17-2-22.
 */
public class SqlTestMapper extends MapperBase {
    Record map_key;
    Record map_out;
    @Override
    public void map(long key, Record record, TaskContext context) throws IOException {
        //0:sql_id 1:id 2:value 3:label
        String id=record.getString(1);
        String str=record.getString(2);
        String t="";
        if(decodable(str))
            t= URLDecoder.decode(str,"UTF-8");
        //System.out.println(t);
        if(decodable(t))
            t= URLDecoder.decode(t,"UTF-8");
        //System.out.println(t);

        if(decodable(t))
            t=URLDecoder.decode(str,"gb2312");
        if(decodable(t))
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

            if(word.length()<=1)
                continue;
            if(word.charAt(0)>='0'&&word.charAt(0)<='9')
                continue;

            if(word.compareTo("")!=0){
                map_key.set(0,word);
                map_key.set(1,id);
                map_out.set(0,1);
                context.write(map_key,map_out);
            }

        }
    }

    protected boolean decodable(String str){
        char[] ch=str.toCharArray();
        for(int i=0;i<ch.length;i++){
            if(ch[i]=='%'){
                if(i+2>=ch.length)
                    return false;
                if(i+1<ch.length) {
                    char c = ch[i + 1];
                    if((c>='0'&&c<='9')||(c>='a'&&c<='f')||(c>='A'&&c<='F')){
                        c=ch[i+2];
                        if((c>='0'&&c<='9')||(c>='a'&&c<='f')||(c>='A'&&c<='F'))
                            continue;
                        else
                            return false;

                    }else
                        return false;
                }
            }
        }
        return true;
    }

}
