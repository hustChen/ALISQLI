package com.chensq.sqli.SqlSplit;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.ReducerBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by chensq on 17-2-21.
 */
public class SqlSplitReducer extends ReducerBase {
    public static final Logger LOGGER= LoggerFactory.getLogger(SqlSplitReducer.class);
    @Override
    public void reduce(Record key, Iterator<Record> values, TaskContext context) throws IOException {
        long black=0;
        long white=0;
        HashSet<String> set=new HashSet<>();
        for (Iterator<Record> it = values; it.hasNext(); ) {
            Record record = it.next();
            white+=(Long) record.get(0);
            black+=(Long) record.get(1);
            set.add(record.getString(2));
        }

        //Record output=context.createOutputRecord();
        //Record out_key=context.createOutputKeyRecord();
        Record out_val=context.createOutputRecord();
        String str=key.getString(0);
        LOGGER.info("key",str);
        //out_key.set(0,str);
        out_val.set(0,str);
        out_val.setBigint(1,white);
        out_val.setBigint(2,black);
        out_val.setBigint(3,black+white);
        //out_val.setBigint(4,new Long(set.size()));
        context.write(out_val);
    }
}
