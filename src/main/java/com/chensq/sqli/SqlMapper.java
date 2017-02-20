package com.chensq.sqli;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;
import com.aliyun.odps.mapred.MapperBase;

import java.io.IOException;
import java.net.URLDecoder;

public class SqlMapper extends MapperBase{
    @Override
    public void map(long key, Record record, TaskContext context) throws IOException {
        String url=record.getString(0);
        try {
            url=urlDecode(url);
        } catch (Exception e) {
            e.printStackTrace();
        }

        url=url.substring(url.indexOf('?')+1);

    }

    //url decode
    private String urlDecode(String code) throws Exception{

        String tem = URLDecoder.decode(code, "iso-8859-1");
        if (tem.matches("^(?:[\\x00-\\x7f]|[\\xe0-\\xef][\\x80-\\xbf]{2})+$"))
            return URLDecoder.decode(code, "utf-8");
        else
            return URLDecoder.decode(code,"gb2312");
    }
}
