package com.chensq.sqli.bayes;

import com.aliyun.odps.OdpsException;
import com.aliyun.odps.data.TableInfo;
import com.aliyun.odps.mapred.JobClient;
import com.aliyun.odps.mapred.RunningJob;
import com.aliyun.odps.mapred.conf.JobConf;
import com.aliyun.odps.mapred.utils.InputUtils;
import com.aliyun.odps.mapred.utils.OutputUtils;
import com.chensq.sqli.SqlSplit.SqlSplitMapper;
import com.chensq.sqli.SqlSplit.SqlSplitReducer;

/**
 * Created by chensq on 17-2-23.
 */
public class BayesLauncher {
    public static void main(String[] args) throws OdpsException {
        JobConf job=new JobConf();
        job.setMapperClass(BayesMapper.class);
        job.setReducerClass(BayesReducer.class);

        InputUtils.addTable(TableInfo.builder().tableName("test").build(),job);
        OutputUtils.addTable(TableInfo.builder().tableName("test_result").build(),job);

        RunningJob rj= JobClient.runJob(job);
        rj.waitForCompletion();
    }
}
