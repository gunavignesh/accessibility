package seleniumgluecode;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import runner.TestRunner;
import library.DataProviderBean;

public class Accessibility {
	private TestRunner testRunner;
	DataProviderBean data = new DataProviderBean();

	public Accessibility(TestRunner testRunner) {
		this.testRunner = testRunner;
	}

	@Given("^Opens webpage \"([^\"]*)\"$")
	public void openpage(String arg1) throws Throwable {
		WebDriver driver = testRunner.getDriver();
		driver.get(arg1);
	}

	@When("^Runs Accessibility Report Tool$")
	public void opentool() throws Throwable {
		WebDriver driver = testRunner.getDriver();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"javascript:(function() {var _p='//squizlabs.github.io/HTML_CodeSniffer/build/';var _i=function(s,cb) {var sc=document.createElement('script');sc.onload = function() {sc.onload = null;sc.onreadystatechange = null;cb.call(this);};sc.onreadystatechange = function(){if(/^(complete|loaded)$/.test(this.readyState) === true){sc.onreadystatechange = null;sc.onload();}};sc.src=s;if (document.head) {document.head.appendChild(sc);} else {document.getElementsByTagName('head')[0].appendChild(sc);}}; var options={path:_p};_i(_p+'HTMLCS.js',function(){HTMLCSAuditor.run('WCAG2AA',null,options);});})();");
	}

	@Then("^Excel output is records the report$")
	// @Parameters({ "fileName" })
	public void excelwrite() throws Throwable {
		WebDriver driver = testRunner.getDriver();
		String workingDir = System.getProperty("user.dir");
		data.readPropertiesFile(workingDir + "\\src\\test\\Resources\\properties\\config.properties");
		Thread.sleep(3000);

		// Actual program for app:
		driver.findElement(By.xpath("//div[@id='HTMLCS-settings-issue-count']/div[2]/label/span")).click();
		driver.findElement(By.id("HTMLCS-settings-view-report")).click();
		String eCnt = driver.findElement(By.xpath("//strong")).getText();
		int eCount = Integer.parseInt(eCnt);
		// System.out.println("Error count is: " + eCount);
		driver.findElement(By.xpath("//li[@id='HTMLCS-msg-0']/span[2]")).click();

		for (int i = 0; i < eCount; i++) {
			Thread.sleep(2000);
			String url = driver.getCurrentUrl();
			String wTitle = driver.getTitle();
			String vKey = driver.findElement(By.xpath("//*[@id=\"HTMLCS-msg-detail-" + i + "\"]/div[1]/div[2]/a[2]"))
					.getText();
			int vCol = Integer.parseInt(data.getData("sColumn"));
			String[] vSeva = data.compareExcel(vKey, vCol, workingDir + data.getData("filePath"),
					data.getData("fileRName"), data.getData("vioSName"));
			String vSev = vSeva[4];
			String vComvio = vSeva[3];
			String vVio = vSeva[2];
			String vPath = driver
					.findElement(By.xpath("//*[@id=\"HTMLCS-msg-detail-" + i
							+ "\"]/div[@class='HTMLCS-issue-source']/div[@class='HTMLCS-issue-source-inner']"))
					.getText();
			String vNotes = driver.findElement(By.xpath("//*[@id=\"HTMLCS-msg-detail-" + i + "\"]/div[1]/div[1]"))
					.getText();
			String[] valueToWrite = { url, wTitle, vKey, vPath, vNotes, vSev, vVio, vComvio };
			data.writeExcel(workingDir + data.getData("filePath"), data.getData("fileWName"), data.getData("vioSName"),
					valueToWrite);

			if (i != eCount - 1) {
				System.out.println("i value now is:"+i);
				driver.findElement(By.xpath("//div[@id='HTMLCS-button-next-issue']/span")).click();
			}
		}
	}
}