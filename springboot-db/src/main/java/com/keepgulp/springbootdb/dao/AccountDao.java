package com.keepgulp.springbootdb.dao;

import com.keepgulp.springbootdb.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Account save(Account account);

//    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Account findDistinctById(Integer id);
}
