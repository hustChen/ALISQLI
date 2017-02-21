package com.chensq.sqli.SqlSplit;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Reducer;
import com.aliyun.odps.mapred.ReducerBase;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by chensq on 17-2-21.
 */
public class SqlSplitReducer extends ReducerBase {
    @Override
    public void reduce(Record key, Iterator<Record> values, TaskContext context) throws IOException {
        int black=0;
        int white=0;
        for (Iterator<Record> it = values; it.hasNext(); ) {
            Record record = it.next();
            white+=(Integer) record.get(0);
            black+=(Integer) record.get(1);
        }

        Record output=context.createOutputRecord();
        output.set(0,key.get(0));
        output.set(1,white);
        output.set(2,black);
        output.set(3,black+white);
        context.write(output);
    }
}
