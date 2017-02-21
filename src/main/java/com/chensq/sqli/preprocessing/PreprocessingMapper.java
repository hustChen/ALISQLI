package com.chensq.sqli.preprocessing;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;
import com.aliyun.odps.mapred.MapperBase;

import java.io.IOException;

/**
 * Created by chensq on 17-2-21.
 */
public class PreprocessingMapper extends MapperBase{
    @Override
    public void map(long key, Record record, TaskContext context) throws IOException {
        Record result=context.createOutputRecord();
        result.set(0,key);
        //Record value=values.next();
        result.set(1,record.get(0));
        result.set(2,record.get(1));
        result.set(3,record.get(2));
        context.write(result);

    }
}
