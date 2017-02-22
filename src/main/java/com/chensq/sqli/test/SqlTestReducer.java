package com.chensq.sqli.test;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Reducer;
import com.aliyun.odps.mapred.ReducerBase;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by chensq on 17-2-22.
 */
public class SqlTestReducer extends ReducerBase {
    @Override
    public void reduce(Record key, Iterator<Record> values, TaskContext context) throws IOException {
        long count=0;
        for (Iterator<Record> it = values; it.hasNext(); ) {
            Record record = it.next();
            count+=(Long) record.get(0);
        }
        Record out=context.createOutputRecord();
        out.set(0,key.get(0));
        out.set(1,key.get(1));
        out.setBigint(2,count);
        context.write(out);
    }
}
