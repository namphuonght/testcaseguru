
package Account;

import initEnvironement.BaseTest;
import initEnvironement.Constants;
import org.testng.annotations.*;
import screenObjects.AccountPage;
import screenObjects.HomePage;
import screenObjects.LoginPage;
import utility.LogUtils;
import utility.entity.Account;
import utility.helper.DateTime;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static Customer.TestCustomer.*;
import static SignUp.TestSignUp.guruUserName;
import static SignUp.TestSignUp.guruPassword;
import static utility.PropertiesUtils.urlWebsiteLoginPro;


public class TestAccount extends BaseTest {
    String className = this.getClass().getSimpleName();
    private LoginPage loginPage;
    private HomePage homePage;
    private AccountPage accountPage;
    public static String accId;
    private Account account;

    String testCaseName;

    @BeforeClass
    public void beforeClass() {
        super.setUp(urlWebsiteLoginPro);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        accountPage = new AccountPage(driver);
        LogUtils.info("------------------------------Test Create New Account-----------------------------");
    }

    @BeforeMethod
    public void setUp(Method method) {
        test = extent.startTest(method.getName()).assignCategory(
                getClass().getSimpleName());
    }
    @Test
    public void TC004_CreateNewAccountSuccessfully() throws InterruptedException {
        for (int i= 0 ; i < 5; i++) {
            testCaseName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            super.startRecordVideoAfterMethod(className, testCaseName);

            loginPage.Login(guruUserName, guruPassword);
            homePage.ClickLinkNewAccount();
            accountPage.VerifyAccountPageAllElements();
            accountPage.TheAccountFormTitleShouldBe("Add new account form");
            List<Account> accounts = new ArrayList<Account>();
            accounts.add(createAccountData());
            accounts.add(createAccountData1());
            account = accounts.get(i);
            accountPage.CreateNewAccount(account);
            Thread.sleep(2000);
            accountPage.TheAccountFormTitleShouldBe("Account Generated Successfully!!!");
            accountPage.TheTableContentTextShouldBe("Created Account details are as follows:");
            accId = accountPage.GetAccountId();
            account.accId = accId;
            System.out.println("DEBUG >>> ".concat(account.toString()));
            System.out.println("DEBUG >>> ".concat(account.dateOpening));
            accountPage.VerifyAccountCreatedSuccessfully(account);
        }
    }
    @Test
    public void TC006_CreateNewAccountSuccessfully() throws InterruptedException {
        for (int i= 0 ; i < 5; i++) {
            testCaseName = new Object() {
            }.getClass().getEnclosingMethod().getName();
            super.startRecordVideoAfterMethod(className, testCaseName);
            loginPage.Login(guruUserName, guruPassword);
            homePage.ClickLinkNewAccount();
            accountPage.VerifyAccountPageAllElements();
            accountPage.TheAccountFormTitleShouldBe("Add new account form");
            List<Account> accounts = new ArrayList<Account>();
           account=createAccountData2();
            accountPage.CreateNewAccount(account);
            Thread.sleep(2000);
            accountPage.TheAccountFormTitleShouldBe("Account Generated Successfully!!!");
            accountPage.TheTableContentTextShouldBe("Created Account details are as follows:");
            accId = accountPage.GetAccountId();
            account.accId = accId;
            System.out.println("DEBUG >>> ".concat(account.toString()));
            System.out.println("DEBUG >>> ".concat(account.dateOpening));
            accountPage.VerifyAccountCreatedSuccessfully(account);
        }
    }


    public Account createAccountData() {
        account = new Account();
        account.cusId = "";
        account.cusName = "";
        account.cusEmail = "";
        String[] accTypes = {"Savings", "Current"};
        account.accType = getHelper().getRandomStringArray(accTypes);
        account.dateOpening = DateTime.getCurrentTime("yyyy-MM-dd");
        account.currentAmount = getHelper().randomNumber(5);
        return account;
    }

    public Account createAccountData1() {
        account = new Account();
        account.cusId = cusId;
        account.cusName = cusName;
        account.cusEmail = cusEmail;
        String[] accTypes = {"Savings", "Current"};
        account.accType = getHelper().getRandomStringArray(accTypes);
        account.dateOpening = DateTime.getCurrentTime("yyyy-MM-dd");
        account.currentAmount = getHelper().randomNumber(5);
        return account;
    }
    public Account createAccountData2() {
        account = new Account();
        account.cusId = " ";
        account.cusName = " ";
        account.cusEmail = " ";
        String[] accTypes = {"Savings", "Current"};
        account.accType = getHelper().getRandomStringArray(accTypes);
        account.dateOpening = DateTime.getCurrentTime("yyyy-MM-dd");
        account.currentAmount = getHelper().randomNumber(5);
        return account;
    }

    @AfterMethod
    public void tearDown() {
        Constants.sClassName = className;
        super.stopRecordVideoAfterMethod();
        super.screenShotAfterMethod(className, testCaseName);
    }

    @AfterClass
    public void afterClass() {
        super.afterClass();
    }
}