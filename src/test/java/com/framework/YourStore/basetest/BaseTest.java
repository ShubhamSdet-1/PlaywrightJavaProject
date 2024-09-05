package com.framework.YourStore.basetest;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import framework.YourStore.PageFactory.*;
import framework.YourStore.Pages.Homepage;
import framework.YourStore.Pages.Loginpage;

import framework.YourStore.Pages.*;

import com.microsoft.playwright.Page;

public class BaseTest {

	protected PlaywrightPagefactory pf;
	protected Homepage hp;
	protected Loginpage loginPage;
	protected RegisterPage registerPage;

	protected Page page;
	protected Properties property;
	protected Properties readLocator;
	
	@BeforeTest
	public void setup() throws IOException {
		this.pf = new PlaywrightPagefactory();
		this.property = pf.readPropertyFile();
		this.readLocator = pf.readLocatorPropertyFile();
		this.page = pf.launchBrowser(property);
		this.hp = new Homepage(page,readLocator);

		this.loginPage = new Loginpage(page, readLocator);

	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}

}
