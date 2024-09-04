package com.framework.YourStore.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.framework.YourStore.basetest.BaseTest;
import framework.YourStore.constants.YourStoreConstants;

public class RegisterPageTest extends BaseTest{
	
	@Test(priority = 1)
	public void navigateToRegisterPageTest() {
		 registerPage = hp.navigateToRegister();
		 String pagetitle = registerPage.getTitle();
		 Assert.assertEquals(pagetitle, YourStoreConstants.RegisterPage_Title);
	}
	
	@Test(priority = 2)
	public void pageUrlTest() {
		String pageUrl = registerPage.getUrl();
		Assert.assertEquals(pageUrl, YourStoreConstants.RegisterPage_URL);
	}
	
	@Test(priority = 3)
	public void doRegisterNewUserTest() throws InterruptedException, IOException {
		String firstName = pf.readExcelData("FirstName");
		String lastName = pf.readExcelData("LastName");
		String email = pf.readExcelData("Email");
		String telephone = pf.readExcelData("Telephone");
		String password = pf.readExcelData("Password");
		boolean isRegistered = registerPage.doRegisterNewUser(firstName, lastName, email, telephone, password);
		System.out.println(isRegistered);
		Assert.assertEquals(isRegistered, true);
	}
	

}
