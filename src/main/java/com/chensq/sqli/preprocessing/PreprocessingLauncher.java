package com.chensq.sqli.preprocessing;

import com.aliyun.odps.OdpsException;
import com.aliyun.odps.data.TableInfo;
import com.aliyun.odps.mapred.JobClient;
import com.aliyun.odps.mapred.RunningJob;
import com.aliyun.odps.mapred.conf.JobConf;
import com.aliyun.odps.mapred.utils.InputUtils;
import com.aliyun.odps.mapred.utils.OutputUtils;

/**
 * Created by chensq on 17-2-21.
 */
public class PreprocessingLauncher {
    //public static void main(String[] args) throws OdpsException {
    //    JobConf job = new JobConf();
    //    job.setMapperClass(PreprocessingMapper.class);
    //    //job.setReducerClass(PreprocessingReducer.class);
    //    InputUtils.addTable(TableInfo.builder().tableName("sql_train").build(),job);
    //    OutputUtils.addTable(TableInfo.builder().tableName("sql_train_id").build(),job);
    //    RunningJob rj= JobClient.runJob(job);
    //    rj.waitForCompletion();;
    //}
}
