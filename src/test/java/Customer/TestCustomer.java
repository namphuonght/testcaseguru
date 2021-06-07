package Customer;

import initEnvironement.BaseTest;
import initEnvironement.Constants;
import org.testng.annotations.*;
import screenObjects.CustomerPage;
import screenObjects.HomePage;
import screenObjects.LoginPage;
import utility.LogUtils;
import utility.entity.Customer;
import utility.helper.DateTime;

import java.lang.reflect.Method;
import java.util.Random;

import static SignUp.TestSignUp.guruUserName;
import static SignUp.TestSignUp.guruPassword;
import static utility.PropertiesUtils.urlWebsiteLoginPro;


public class TestCustomer extends BaseTest {
    String className = this.getClass().getSimpleName();
    private LoginPage loginPage;
    private HomePage homePage;
    private CustomerPage customerPage;
    private Customer customer;
    public static String cusId="86140";
    public static String cusName="TranDinhHung";
    public static String cusEmail="Tdhung.18it4@vku.udn.vn";
    String testCaseName;

    @BeforeClass
    public void beforeClass() {
        super.setUp(urlWebsiteLoginPro);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        customerPage = new CustomerPage(driver);
        LogUtils.info("------------------------------Test Create New Customer-----------------------------");
    }

    @BeforeMethod
    public void setUp(Method method) {
        test = extent.startTest(method.getName()).assignCategory(
                getClass().getSimpleName());
    }


    @Test
    public void TC003_CreateNewCustomerSuccessfully() throws InterruptedException {
        testCaseName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        super.startRecordVideoAfterMethod(className, testCaseName);

        loginPage.Login(guruUserName, guruPassword);
        homePage.ClickLinkNewCustomer();
        customerPage.VerifyCustomerPageAllElements();
        customer = createCustomerData();
        customerPage.CreateNewCustomer(customer);
        Thread.sleep(2000);
        customerPage.TheTableHeaderTextShouldBe("Customer Registered Successfully!!!");
        customerPage.TheTableContentTextShouldBe("Registered Customer details are as follows:");
        customerPage.VerifyCustomerCreatedSuccessfully(customer);
        cusId = customerPage.GetCustomerId();
        cusName = customer.name;
        cusEmail = customer.email;

    }
    @Test
    public void TC003_CreateNewCustomerFail() throws InterruptedException {
        testCaseName = new Object() {
        }.getClass().getEnclosingMethod().getName();
        super.startRecordVideoAfterMethod(className, testCaseName);

        loginPage.Login(guruUserName, guruPassword);
        homePage.ClickLinkNewCustomer();
        customerPage.VerifyCustomerPageAllElements();
        customer = createCustomerData1();
        customerPage.CreateNewCustomer(customer);
        Thread.sleep(2000);
        customerPage.TheTableHeaderTextShouldBe("Fail!!!");
        customerPage.TheTableContentTextShouldBe("Registered Customer details are as follows:");
        customerPage.VerifyCustomerCreatedSuccessfully(customer);
        cusId = customerPage.GetCustomerId();
        cusName = customer.name;
        cusEmail = customer.email;

    }
    public Customer createCustomerData1() {
        customer = new Customer();
        customer.name = (" ");
        String[] genders = {"m", "f"};
        customer.gender = getRandomStringArray(genders);
        customer.dateOfBirth = DateTime.getCurrentTime("MM/dd/yyyy");
        customer.address = getHelper().randomChars(10);
        String[] cities = {""};
        customer.city = getRandomStringArray(cities);
        String[] states = {""};
        customer.state ="";
        customer.pin = "";
        customer.telephone = "";
        customer.email =  "";
        customer.passWord = "";
        return customer;
    }


    public Customer createCustomerData() {
        customer = new Customer();
        customer.name = ("Customer ".concat(getHelper().randomString()));
        String[] genders = {"m", "f"};
        customer.gender = getRandomStringArray(genders);
        customer.dateOfBirth = DateTime.getCurrentTime("MM/dd/yyyy");
        customer.address = getHelper().randomChars(10);
        String[] cities = {"Ho Chi Minh", "Ha Noi", "Da Nang"};
        customer.city = getRandomStringArray(cities);
        String[] states = {"Hoa Xuan", "Cam Le", "Hai Chau"};
        customer.state = getRandomStringArray(states);
        customer.pin = getHelper().randomNumber(6);
        customer.telephone = "09".concat(getHelper().randomNumber(8));
        customer.email =  System.currentTimeMillis() + "@vku.udn.vn";
        customer.passWord = "123456";
        return customer;
    }

    public String getRandomStringArray(String[] strings) {
        Random ran = new Random();
        return strings[ran.nextInt(strings.length)];
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