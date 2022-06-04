package com.rycoding.springbatch.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("ryCoding")
public class SpringItemBatchController {
    Logger logger = LoggerFactory.getLogger(SpringItemBatchController.class);
     @Autowired
     Job job;
     @Autowired
     JobLauncher jobLauncher;

     @GetMapping("/runPersistJob")
     public BatchStatus readingPersistFile() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
         Map<String, JobParameter> param = new HashMap<>();
         param.put("time", new JobParameter(new Date().getTime()));
         param.put("fileName", new JobParameter("Testing"));
         JobParameters jobParameters =new JobParameters(param);
         JobExecution jobExecution = jobLauncher.run(job, jobParameters);
         logger.info(jobExecution.getStatus().toString());
         while (jobExecution.isRunning()){
             logger.info("running ***");
         }
         return jobExecution.getStatus();
     }
}
