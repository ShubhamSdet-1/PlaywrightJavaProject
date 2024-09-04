package com.framework.YourStore.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.framework.YourStore.basetest.BaseTest;

import framework.YourStore.constants.YourStoreConstants;

public class LoginPageTest extends BaseTest {
	@Test(priority = 1)
	public void navigateToLoginPageTest() {
		loginPage = hp.navigateToLogin();
		String actualTitle = loginPage.getTitle();
		Assert.assertEquals(actualTitle, YourStoreConstants.LoginPage_Title);
	}

	@Test (priority = 2)
	public void urlTest() {
		String actualUrl = loginPage.getUrl();
		Assert.assertEquals(actualUrl, "https://naveenautomationlabs.com/opencart/index.php?route=account/login");
	}


	@Test (priority = 3)
	public void loginTest() throws InterruptedException {
		boolean loginTest = loginPage.login(property.getProperty("username"), property.getProperty("password"));
		Assert.assertEquals(loginTest, true);
	}

}
