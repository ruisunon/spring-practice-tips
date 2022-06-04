package com.rycoding.springbatch.config;

import com.rycoding.springbatch.batch.ShareTransWriter;
import com.rycoding.springbatch.batch.ShareTransProcessor;
import com.rycoding.springbatch.model.ShareTransaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@EnableBatchProcessing
@Configuration
public class SpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Value("classPath")

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, ItemReader itemReader, ShareTransProcessor itemsProcessor,
                   ShareTransWriter itemsWriter, StepBuilderFactory stepBuilderFactory){
     Step step=stepBuilderFactory.get("Share.trans.step").<ShareTransaction, ShareTransaction>chunk(100).reader(itemReader)
             .writer(itemsWriter).processor(itemsProcessor).build();
     return jobBuilderFactory.get("share-tran").incrementer(new RunIdIncrementer()).start(step).build();
    }
    @Bean
    @StepScope
    public FlatFileItemReader<ShareTransaction> itemReader(){
        FlatFileItemReader<ShareTransaction> flatFileItemReader =new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileSystemResource("src/main/resources/test.txt"));
        flatFileItemReader.setName("Flat file reader");
        flatFileItemReader.setLineMapper(lineMapper());
        flatFileItemReader.setLinesToSkip(1);
        return flatFileItemReader;
    }
    @Bean
    public LineMapper<ShareTransaction> lineMapper(){
        DefaultLineMapper<ShareTransaction> defaultLineMapper =new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("shareCode","companyName","qty","amount","type");
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper<ShareTransaction> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(ShareTransaction.class);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
        return defaultLineMapper;
    }
}
