package com.keepgulp.springbootdb.rest;

import com.keepgulp.springbootdb.optlocking.RetryOnOptimisticLockingFailure;
import com.keepgulp.springbootdb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/account")
public class AccountResource {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "/{id}/{name}", method = RequestMethod.PUT)
	@ResponseBody
	public void updateName(@PathVariable Integer id, @PathVariable String name) {
		System.out.println("id = [" + id + "], name = [" + name + "]");
		accountService.updateName(id, name);
	}
}
