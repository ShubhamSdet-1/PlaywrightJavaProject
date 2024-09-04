package com.framework.YourStore.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.framework.YourStore.basetest.BaseTest;

import framework.YourStore.constants.YourStoreConstants;

public class HomepageTest extends BaseTest {

	@Test(priority = 1)
	public void urlTest() {
		String actualUrl = hp.getHomepageUrl();
		Assert.assertEquals(actualUrl, property.getProperty("url"));
	}

	@Test(priority = 2)
	public void titleTest() {
		String actualTitle = hp.getHomepageTitle();

		Assert.assertEquals(actualTitle, YourStoreConstants.HomePage_Title);

		Assert.assertEquals(actualTitle, property.getProperty("pageTitle"));

	}
	
	@DataProvider
	public Object[][] getProduct(){
		return new Object[][] {
			{"Macbook"},
			{"iPad"},
			{"samsung"}
		};
	}

	@Test(priority = 3,dataProvider = "getProduct")
	public void searchTest(String productName) {
		String actualSearchHeader = hp.doSearch(productName);
		Assert.assertEquals(actualSearchHeader, "Search - "+productName);
	}

}
