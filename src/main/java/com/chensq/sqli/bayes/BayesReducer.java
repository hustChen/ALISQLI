package com.chensq.sqli.bayes;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Reducer;
import com.aliyun.odps.mapred.ReducerBase;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by chensq on 17-2-23.
 */
public class BayesReducer extends ReducerBase {

    final static double black_clz=700;
    final static double white_clz=20000;
    final static double total_clz=20700;

    final static double black_rate=black_clz/total_clz;
    final static double white_rate=white_clz/total_clz;

    @Override
    public void reduce(Record key, Iterator<Record> values, TaskContext context) throws IOException {
        double black_sum=0;
        double white_sum=0;

        for (Iterator<Record> it = values; it.hasNext(); ) {
            Record record = it.next();
            black_sum+=record.getDouble(0);
            white_sum+=record.getDouble(1);
        }

        black_sum+=Math.log(black_rate);
        white_sum+=Math.log(white_rate);

        Record reduce_out=context.createOutputRecord();
        reduce_out.setString(0,key.getString(0));
        reduce_out.setDouble(1,black_sum);
        reduce_out.setDouble(2,white_sum);

        int result=black_sum>white_sum?1:0;

        reduce_out.setString(3,String.valueOf(result));
        context.write(reduce_out);

    }
}
