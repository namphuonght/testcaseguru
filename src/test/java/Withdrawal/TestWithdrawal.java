package Withdrawal;

import initEnvironement.BaseTest;
import initEnvironement.Constants;
import org.testng.annotations.*;
import screenObjects.DepositPage;
import screenObjects.HomePage;
import screenObjects.LoginPage;
import screenObjects.Withdrawalpage;
import utility.LogUtils;
import utility.entity.Withdrawal;

import java.lang.reflect.Method;

import static Account.TestAccount.accId;
import static SignUp.TestSignUp.guruPassword;
import static SignUp.TestSignUp.guruUserName;
import static utility.PropertiesUtils.urlWebsiteLoginPro;

public class TestWithdrawal extends BaseTest {
    String className = this.getClass().getSimpleName();
    private LoginPage loginPage;
    private HomePage homePage;
    private DepositPage depositPage;
    public static String transId;
    private Withdrawal withdrawal;
    private Withdrawalpage withdrawalpage;

    String testCaseName;

    @BeforeClass
    public void beforeClass() {
        super.setUp(urlWebsiteLoginPro);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        depositPage = new DepositPage(driver);
        LogUtils.info("------------------------------Test Add New Deposit-----------------------------");
    }

    @BeforeMethod
    public void setUp(Method method) {
        test = extent.startTest(method.getName()).assignCategory(
                getClass().getSimpleName());
    }

    /*
     * SCENARIO: TC05_add_new_deposit_successfully
     * Precondition: Acc guru99 created from TestSignUp & Account ID from TestAccount suite "cusId"
     * #1: Login guru99/v4 by guru99 Account
     * #2: Goes to Deposit page
     * #3: Verify UI of Deposit page
     * #3: Input valid data & submit
     * #4: Verify Deposit is added successfully
     */
    @Test
    public void TC005_AddNewDepositSuccessfully() throws InterruptedException {
        testCaseName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        super.startRecordVideoAfterMethod(className, testCaseName);

        loginPage.Login(guruUserName, guruPassword);
        homePage.ClickLinkWithdrawal();

        withdrawalpage.VerifyWithdrawalpageAllElements();
        withdrawalpage.TheWithdrawalFormTitleShouldBe("Amount Withdrawal Form");

        withdrawal = createWithdrawalData();
        withdrawalpage.AddnewWithdrawalpage(withdrawal);
        Thread.sleep(2000);
        withdrawalpage.TheWithdrawalFormTitleShouldBe("Transaction details of Withdrawal for Account ".concat(withdrawal.accId));
        transId =withdrawalpage.GetTransactionId();
        withdrawal.transId = transId;
        withdrawalpage.VerifyDepositAddedSuccessfully(withdrawal);

    }
    public Withdrawal createWithdrawalData() {
        withdrawal = new Withdrawal();
        withdrawal.accId = "93518";
        withdrawal.amount = getHelper().randomNumber(5);
        withdrawal.typeOfTransaction = "Withdrawal";
        withdrawal.description = getHelper().randomString();
        return withdrawal;
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

