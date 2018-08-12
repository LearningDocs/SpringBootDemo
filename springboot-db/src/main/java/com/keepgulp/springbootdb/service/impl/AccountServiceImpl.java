package com.keepgulp.springbootdb.service.impl;

import com.keepgulp.springbootdb.dao.AccountDao;
import com.keepgulp.springbootdb.model.Account;
import com.keepgulp.springbootdb.optlocking.RetryOnOptimisticLockingFailure;
import com.keepgulp.springbootdb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@RetryOnOptimisticLockingFailure
	@Transactional
	public void updateName(Integer id, String name) {
		System.out.println("调用更新接口：id = [" + id + "], name = [" + name + "]");

		Account account = accountDao.findDistinctById(id);
		String oriName = account.getName();
		Integer oriVersion = account.getVersion();
		//pausing a moment, chance for hit optimistic locking 
		try{
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}
		account.setName(name);
		accountDao.save(account);
		System.out.println("Original:name=["+oriName+"],version=["+oriVersion+"],New:name=["+name+"],version=["+account.getVersion()+"]");
	}

}
