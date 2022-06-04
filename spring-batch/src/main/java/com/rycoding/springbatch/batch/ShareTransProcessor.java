package com.rycoding.springbatch.batch;

import com.rycoding.springbatch.model.ShareTransaction;
import com.rycoding.springbatch.repository.ShareTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ShareTransProcessor implements ItemProcessor<ShareTransaction, ShareTransaction> {

    Logger logger = LoggerFactory.getLogger(ShareTransProcessor.class);
    @Autowired
    ShareTransactionRepository shareTransactionRepository;

    @Override
    public ShareTransaction process(ShareTransaction shareTransaction) throws Exception {
        Optional<ShareTransaction> opt=shareTransactionRepository.findById(shareTransaction.getShareCode());
        if(opt.isPresent()){
            Double amount=Double.valueOf(shareTransaction.getAmount());
            ShareTransaction shareTransaction1=opt.get();
            Double amount1= Double.valueOf(shareTransaction1.getAmount());
            Double total=amount + amount1;
            shareTransaction.setAmount(""+ total);

        }
        return shareTransaction;
    }
}
