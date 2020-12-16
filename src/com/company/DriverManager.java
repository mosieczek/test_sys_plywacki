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
        //test logowania
        LoginTest();
        //test rejestracji
//        RegistrationTest();
        //test rejestracji zawodów
        RegistrationCompetitionTest();
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

    }
    public void RegistrationTest(){
        ExtentTest logger = extentReportes.createTest("Registration Test");
        logger.log(Status.INFO, "Rejestracja");
        try {
            mainDriver.findElement(By.id("redirect-to-registration")).click();
            mainDriver.findElement(By.id("registration-input-login")).sendKeys("organizator3");
            mainDriver.findElement(By.id("registration-input-password")).sendKeys("organizator3");
            mainDriver.findElement(By.id("registration-input-password2")).sendKeys("organizator3");
            mainDriver.findElement(By.id("button-registration")).click();
            logger.log(Status.PASS, "Udało sie zarejestrować");
        }
        catch (Exception e){
            logger.log(Status.FAIL, "Nie udalo sie zarejestrować");
        }
        extentReportes.flush();
    }

    public void RegistrationCompetitionTest(){
        ExtentTest logger = extentReportes.createTest("Registration Competition Test");
        logger.log(Status.INFO, "Rejestracja zawodów");
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-competitions-button")));
            mainDriver.findElement(By.id("add-competitions-button")).click();
            mainDriver.findElement(By.id("input-competition-date")).sendKeys("002021-03-02");
            mainDriver.findElement(By.id("input-competition-name")).sendKeys("Mistrzostwa Wojewodztwa");
            mainDriver.findElement(By.id("button-registration-competition")).click();

            logger.log(Status.PASS, "Udało sie zarejestrować zawody");
        }
        catch (Exception e){
            logger.log(Status.FAIL, "Nie udalo sie zarejestrować zawodów");
        }
        extentReportes.flush();
    }

}
