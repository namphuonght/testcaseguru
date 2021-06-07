package Login;

import initEnvironement.BaseTest;
import initEnvironement.Constants;
import org.testng.annotations.*;
import screenObjects.HomePage;
import screenObjects.LoginPage;
import utility.LogUtils;

import java.lang.reflect.Method;

import static SignUp.TestSignUp.guruUserName;
import static SignUp.TestSignUp.guruPassword;
import static utility.PropertiesUtils.urlWebsiteLoginPro;

public class TestLogin extends BaseTest {
    String className = this.getClass().getSimpleName();
    private LoginPage loginPage;
    private HomePage homePage;
    String testCaseName;

    @BeforeClass
    public void beforeClass() {
        super.setUp(urlWebsiteLoginPro);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        LogUtils.info("------------------------------Test Login-----------------------------");
    }

    @BeforeMethod
    public void setUp(Method method) {
        test = extent.startTest(method.getName()).assignCategory(
                getClass().getSimpleName());
    }

    /*
     * SCENARIO: TC02_login_user_guru99_successfully
     * Precondition: Acc guru99 created from TestSignUp
     * #1: Goes to Login page
     * #2: Verify UI of Login page
     * #3: Input valid Account & submit
     * #4: Verify user logged successfully with Home Page display
     */
    @Test
    public void TC002_LoginUserFail() {
       testCaseName = new Object() {
       }.getClass().getEnclosingMethod().getName();
       super.startRecordVideoAfterMethod(className, testCaseName);

        loginPage.VerifyLoginPageAllElements();
        loginPage.Login(guruUserName, "1");

        homePage.VerifyHomePageAllElements();
    }
    @Test
    public void TC004_LoginUserFail() {
        testCaseName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        super.startRecordVideoAfterMethod(className, testCaseName);

        loginPage.VerifyLoginPageAllElements();
        loginPage.Login("", "");

        homePage.VerifyHomePageAllElements();
    }
    @Test
    public void TC001_LoginUserFail() {
        testCaseName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        super.startRecordVideoAfterMethod(className, testCaseName);

        loginPage.VerifyLoginPageAllElements();
        loginPage.Login("1", "");

        homePage.VerifyHomePageAllElements();
    }

    @Test
    public void TC003_LoginUserSuccessfully() {
        testCaseName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        super.startRecordVideoAfterMethod(className, testCaseName);

        loginPage.VerifyLoginPageAllElements();
        loginPage.Login(guruUserName, guruPassword);

        homePage.VerifyHomePageAllElements();
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
