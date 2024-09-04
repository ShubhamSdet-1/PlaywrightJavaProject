package framework.YourStore.Pages;

import java.util.Properties;

import com.microsoft.playwright.Page;

public class Homepage {

	Page page;
	Properties readLocator;

	public Homepage(Page page, Properties readLocator) {
		this.page = page;
		this.readLocator = readLocator;
	}

	public String getHomepageTitle() {
		String title = page.title();
		System.out.println("Page Title - " + title);
		return title;
	}

	public String getHomepageUrl() {
		String url = page.url();
		System.out.println("Page Url - " + url);
		return url;
	}

	public String doSearch(String searchText) {
		page.locator(readLocator.getProperty("searchField")).fill(searchText);
		page.click(readLocator.getProperty("searchIcon"));
		String searchHeader = page.textContent(readLocator.getProperty("searchResultHeader"));
		return searchHeader;

	}

	public Loginpage navigateToLogin() {
		page.click(readLocator.getProperty("myAccountDropdown"));
		page.click(readLocator.getProperty("loginLink"));
		return new Loginpage(page, readLocator);
	}


	public RegisterPage navigateToRegister() {
		page.click(readLocator.getProperty("myAccountDropdown"));
		page.click(readLocator.getProperty("registerLink"));
		return new RegisterPage(page, readLocator);
	}
	

}
