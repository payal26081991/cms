package com.gracenote.pages;

import com.gracenote.resources.Excel_Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class CMS {
    private static WebDriver driver;
    private static Excel_Util excelobj;
    private static By UserName = By.xpath("//input[@id='txtLoginID']");
    private static By Password = By.xpath("//input[@id='txtPWD']");
    private static By LoginButton = By.xpath("//input[@id='imgbtnlogin']");
    private static By ProgramLink = By.linkText("Program");
    private static By ProgramSearch = By.xpath("//input[@id='ctl00_ContentPlaceHolder1_txtProgramName']");
    private static By SearchButton = By.xpath("//input[@id='ctl00_ContentPlaceHolder1_imgbtnGo']");
    private static By FirstProgramLink = By.xpath("//table[@id='ctl00_ContentPlaceHolder1_grdProgramList_ctl00']/tbody/tr/td[4]/a[1]");
    private static By Desc = By.xpath("//textarea[@name='ctl00$ContentPlaceHolder1$ProgramTabContainer$ProgramDetailsTabPanel$txtLongDesc1000']");
    private static By LongDesc = By.xpath("//textarea[@name='ctl00$ContentPlaceHolder1$ProgramTabContainer$ProgramDetailsTabPanel$txtSynp']");
    private static By ShotDesc = By.xpath("//textarea[@name='ctl00$ContentPlaceHolder1$ProgramTabContainer$ProgramDetailsTabPanel$txtSmsDescription']");
    private static By ProgramSubmit = By.xpath("//input[@id='ctl00_ContentPlaceHolder1_ProgramTabContainer_ProgramDetailsTabPanel_imgBtnSubmit']");
    private static By DescValidation = By.xpath("//span[@id='ctl00_ContentPlaceHolder1_ProgramTabContainer_ProgramDetailsTabPanel_lblErrorLongDesc1000']");
    private static By LongDescValidation = By.xpath("//span[@id='ctl00_ContentPlaceHolder1_ProgramTabContainer_ProgramDetailsTabPanel_lblErrorSynp']");
    private static By ShotDescValidation = By.xpath("//span[@id='ctl00_ContentPlaceHolder1_ProgramTabContainer_ProgramDetailsTabPanel_lblErrorSmsDescription']");


    protected static void launch() {
        System.setProperty("webdriver.chrome.driver", "src//test//resources//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get("http://192.168.1.117/CMSUAT/MainPages/DasLogin.aspx");
        driver.manage().window().maximize();
    }

    protected static void after() {
        driver.quit();
    }

    protected static void login(String username, String password) {
        driver.findElement(UserName).sendKeys(username);
        driver.findElement(Password).sendKeys(password);
        driver.findElement(LoginButton).click();
    }

    protected static void navigateToProgramPage() {
        driver.findElement(ProgramLink).click();
    }

    protected static void searchProgram(String program) {
        driver.findElement(ProgramSearch).sendKeys(program);
        driver.findElement(SearchButton).click();
    }

    protected static void clickProgramLink() {
        driver.findElement(FirstProgramLink).click();
    }

    protected static void ValidateProgramDesc() {
        excelobj = new Excel_Util("src\\test\\resources\\ForbiddenWordsGlobalStandarizationProject.xlsx");
        System.out.println(excelobj.getCellData("APAC - English","Offensive Words",2));

        String ValidationMessage = "Validation Failed :\nMissing punctuation at end.\nMake the change and re-submit.";
        System.out.println(ValidationMessage);

        String SheetNames [] = {"APAC - English"};
        for(String Sheet: SheetNames){
            for (int i=2;i<=excelobj.getRowCount(Sheet);i++) {
                driver.findElement(Desc).clear();
                driver.findElement(Desc).sendKeys(excelobj.getCellData(Sheet, "Offensive Words", i));
                driver.findElement(LongDesc).clear();
                driver.findElement(LongDesc).sendKeys(excelobj.getCellData(Sheet, "Offensive Words", i));
                driver.findElement(ShotDesc).clear();
                driver.findElement(ShotDesc).sendKeys(excelobj.getCellData(Sheet, "Offensive Words", i));
                driver.findElement(ProgramSubmit).click();

//                System.out.println(driver.findElement(DescValidation).getText());
//                System.out.println(driver.findElement(LongDescValidation).getText());
//                System.out.println(driver.findElement(ShotDescValidation).getText());
                if(driver.findElement(DescValidation).getText().equals(ValidationMessage))
                {
                    excelobj.setCellData(Sheet,"Desc 1000",i,"Pass");
                }else
                {
                    excelobj.setCellData(Sheet,"Desc 1000",i,"Fail");
                }

                if(driver.findElement(LongDescValidation).getText().equals(ValidationMessage))
                {
                    excelobj.setCellData(Sheet,"Long Description",i,"Pass");
                }else
                {
                    excelobj.setCellData(Sheet,"Long Description",i,"Fail");
                }

                if(driver.findElement(ShotDescValidation).getText().equals(ValidationMessage))
                {
                    excelobj.setCellData(Sheet,"Short Description",i,"Pass");
                }else
                {
                    excelobj.setCellData(Sheet,"Short Description",i,"Fail");
                }
            }
        }
    }
}
