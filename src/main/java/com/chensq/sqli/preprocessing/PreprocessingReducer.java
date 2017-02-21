package com.chensq.sqli.preprocessing;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Reducer;
import com.aliyun.odps.mapred.ReducerBase;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by chensq on 17-2-21.
 */
public class PreprocessingReducer extends ReducerBase {
    @Override
    public void reduce(Record key, Iterator<Record> values, TaskContext context) throws IOException {
        Record result=context.createOutputRecord();
        result.set(0,key.get(0));
        Record value=values.next();
        result.set(1,value.get(0));
        result.set(2,value.get(1));
        result.set(3,value.get(2));
        context.write(result);
    }
}
