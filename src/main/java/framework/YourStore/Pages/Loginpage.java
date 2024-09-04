package framework.YourStore.Pages;

import java.util.Properties;

import java.util.concurrent.TimeUnit;

import com.microsoft.playwright.Page;

public class Loginpage {

	Page page;
	Properties readLocator;
	public Loginpage(Page page,Properties readLocator) {
		this.page = page;
		this.readLocator = readLocator;
	}

	public String getUrl() {
		String loginPageUrl =page.url();
		System.out.println("Login Page Url - "+loginPageUrl);
		return loginPageUrl;
	}
	
	public String getTitle() {
		String loginPageTitle =page.title();
		System.out.println("Login Page Title - "+loginPageTitle);
		return loginPageTitle;
	}
	
	public boolean isForgetPassLinkVisible() {
		boolean passLink = page.isVisible(readLocator.getProperty("forgottenpasswordlink"));
		return passLink;
	}


	public boolean login(String username,String password) throws InterruptedException {
		page.fill(readLocator.getProperty("usernamefield"), username);
		page.fill(readLocator.getProperty("passwordfield"), password);
		page.click(readLocator.getProperty("loginbutton"));
		TimeUnit.SECONDS.sleep(4);
		if (page.isVisible(readLocator.getProperty("logoutLink"))) {

			System.out.println("Login Successful.");
			return true;
		} else {
			return false;
		}
	}
	
	
}
