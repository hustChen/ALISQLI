package com.chensq.sqli.bayes;

import com.aliyun.odps.data.Record;
import com.aliyun.odps.mapred.Mapper;
import com.aliyun.odps.mapred.MapperBase;

import java.io.IOException;

/**
 * Created by chensq on 17-2-23.
 */
public class BayesMapper extends MapperBase {

    final static double black_word=10537;
    final static double white_word=104850;



    @Override
    public void map(long key, Record record, TaskContext context) throws IOException {
        //0:word 1:id 2:appearence 3:black 4:white
        String word=record.getString(0);
        String id=record.getString(1);
        Long appear=record.getBigint(2);
        Long black=record.getBigint(3);
        if(black==null)
            black=1L;
        Long white=record.getBigint(4);
        if(white==null)
            white=1L;
        Double black_r=appear*Math.log(black/black_word);
        Double white_r=appear*Math.log(white/white_word);

        Record map_key=context.createMapOutputKeyRecord();
        Record map_val=context.createMapOutputValueRecord();

        map_key.setString(0,id);
        map_val.setDouble(0,black_r);
        map_val.setDouble(1,white_r);

        context.write(map_key,map_val);


    }
}
