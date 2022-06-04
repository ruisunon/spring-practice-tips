package com.rycoding.springbatch.repository;

import com.rycoding.springbatch.model.ShareTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ShareTransactionRepository extends CrudRepository<ShareTransaction, Integer> {
}
