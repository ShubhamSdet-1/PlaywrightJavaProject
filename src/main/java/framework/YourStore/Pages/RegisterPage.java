package framework.YourStore.Pages;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.microsoft.playwright.Page;

public class RegisterPage {

	Page page;
	Properties readLocator;

	public RegisterPage(Page page, Properties readLocator) {
		this.page = page;
		this.readLocator = readLocator;
	}

	public String getUrl() {
		String registerPageUrl = page.url();
		System.out.println("Register Page Url - " + registerPageUrl);
		return registerPageUrl;
	}

	public String getTitle() {
		String registerPageTitle = page.title();
		System.out.println("Register Page Title - " + registerPageTitle);
		return registerPageTitle;
	}

	public boolean doRegisterNewUser(String firstName, String lastName, String email, String telephone, String password)
			throws InterruptedException {

		page.fill(readLocator.getProperty("firstName"), firstName);
		page.fill(readLocator.getProperty("lastName"), lastName);
		page.fill(readLocator.getProperty("email"), email);
		page.fill(readLocator.getProperty("telephone"), telephone);
		page.fill(readLocator.getProperty("password"), password);
		page.fill(readLocator.getProperty("confirmPassword"), password);
		page.click(readLocator.getProperty("checkboxPrivacyPolicy"));
		page.click(readLocator.getProperty("continue"));
		TimeUnit.SECONDS.sleep(4);
		if (page.isVisible(readLocator.getProperty("accountcreationheader"))) {
			System.out.println("User registration successful.");
			return true;
		} else {
			return false;
		}
	}

}
