package com.rycoding.springbatch.batch;

import com.rycoding.springbatch.model.ShareTransaction;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ShareTransReader implements ItemReader<ShareTransaction> {

@Override
public ShareTransaction read()  throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
        }
}