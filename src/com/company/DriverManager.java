package com.company;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class DriverManager {
    private WebDriver mainDriver;
    private WebDriverWait wait;
    ExtentHtmlReporter reporter;
    ExtentReports extentReportes;
    public DriverManager() {
        System.setProperty("webdriver.gecko.driver", "..\\..\\..\\chromedriver.exe");
        mainDriver = new ChromeDriver();
        mainDriver.get("http://localhost:8300/");
        wait = new WebDriverWait(mainDriver, 30);
        reporter = new ExtentHtmlReporter("./report.html");
        extentReportes = new ExtentReports();
        extentReportes.attachReporter(reporter);
        LoginTest();
    }

    public void LoginTest(){
        ExtentTest logger = extentReportes.createTest("Login Test");
        logger.log(Status.INFO, "Logowanie");
        try {
            mainDriver.findElement(By.id("input-login")).sendKeys("organizator1");
            mainDriver.findElement(By.id("input-password")).sendKeys("organizator1");
            mainDriver.findElement(By.id("button-zaloguj")).click();
            logger.log(Status.PASS, "Udało sie zalogować");
        }
        catch (Exception e){
            logger.log(Status.FAIL, "Nie udalo sie zalogowac");
        }
        extentReportes.flush();

//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-zaloguj")));


    }
}
