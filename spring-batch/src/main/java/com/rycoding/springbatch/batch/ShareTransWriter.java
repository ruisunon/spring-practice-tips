package com.rycoding.springbatch.batch;


import com.rycoding.springbatch.model.ShareTransaction;
import com.rycoding.springbatch.repository.ShareTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ShareTransWriter implements ItemWriter<ShareTransaction> {
    Logger logger = LoggerFactory.getLogger(ShareTransWriter.class);

    @Autowired
    ShareTransactionRepository shareTransactionRepository;
    @Override
    public void write(List<? extends ShareTransaction> list) throws Exception {
      shareTransactionRepository.saveAll(list);
      logger.info(String.valueOf("list of size " + list!=null ? list.size() : 0));
    }
}
