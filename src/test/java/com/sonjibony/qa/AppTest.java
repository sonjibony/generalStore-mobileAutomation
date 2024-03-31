package com.sonjibony.qa;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AppTest {

	//android driver
	public AndroidDriver driver;
    @BeforeTest
    public void setDevice() throws MalformedURLException {
        File f = new File("src/test/java/resources");
        File apk = new File(f, "General-Store.apk");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:deviceName", "Redmi12C");
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("platformVersion", "14 UP1A.231005.007");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:appPackage", "com.androidsample.generalstore");
        desiredCapabilities.setCapability("appium:appActivity", "com.androidsample.generalstore.SplashActivity");
        desiredCapabilities.setCapability("appium:udid", "c6fajrnjbasozdbe");
        desiredCapabilities.setCapability("app", apk.getAbsolutePath());
        URL remoteUrl = new URL("http://192.168.227.37:4723");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
	
	
    //test
  @Test
    public void test_app() throws InterruptedException {
	  
	  System.out.println("allah hu akbar");
        driver.findElement(By.id("android:id/text1")).click();
        String name = "Bangladesh";
        WebElement countryName = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\""+name+"\"))")) ;
        countryName.click();
        WebElement nameField = driver.findElement(By.id("com.androidsample.generalstore:id/nameField"));
        nameField.sendKeys("Hello world");
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement toolbarTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.androidsample.generalstore:id/toolbar_title")));
        Assert.assertEquals(toolbarTitle.getText(), "Products");
        driver.findElement(By.xpath("//*[@text='Air Jordan 4 Retro']/following-sibling::android.widget.LinearLayout/android.widget.TextView[@text=\"ADD TO CART\"]")).click();
        WebElement count = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.androidsample.generalstore:id/counterText")));
        Assert.assertEquals(count.getText(), "1");
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        driver.findElement(By.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
    }
   
//closing 
    @AfterTest
    public void closeApp(){
        driver.removeApp("com.androidsample.generalstore");
    }

	
}
