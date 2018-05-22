package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import library.DataProviderBean;
import cucumber.api.testng.CucumberFeatureWrapper;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@CucumberOptions(features = "src/test/resources/features", glue = { "seleniumgluecode" }, tags = {
		"~@Ignore" }, format = { "pretty", "html:target/cucumber-reports/cucumber-pretty",
				"json:target/cucumber-reports/CucumberTestReport.json", "rerun:target/cucumber-reports/rerun.txt" })
public class TestRunner {

	private TestNGCucumberRunner testNGCucumberRunner;
	DataProviderBean data = new DataProviderBean();
	//DataProviderBean data = DataProviderInstance.getInstance();
	
	public WebDriver getDriver() {
		return data.driver;
	}
	
	@BeforeTest
	@Parameters({ "BROWSER" })
	public void Browser(String BROWSER) throws Exception {
		data.SelectBrowser(BROWSER);
	}

	@AfterTest
	public void close() throws Exception {
		data.driver.close();
	}

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		testNGCucumberRunner.finish();
	}

}
