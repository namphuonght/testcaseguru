package screenObjects;

import models.FundTransferElement;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utility.Result;
import utility.entity.Withdrawal;

public class FundTransferpage extends  CommonPage{
    FundTransferElement fundTransferElement;
    public FundTransferpage(WebDriver driver) {
        super(driver);
        fundTransferElement = new FundTransferElement();
    }

    /*
     * Input Account ID
     */
    public void InputAccountId(String accId) {
        try {
            getHelper().findElement(fundTransferElement.ele_input_acc_id).sendKeys(accId);
        } catch (Exception e) {
            Result.checkFail("Class DepositPage | Method InputAccountId | Exception desc : " + e.getMessage());
        }
    }

    /*
     * Input deposit amount
     */
    public void InputAmount(String amount) {
        try {
            getHelper().findElement(fundTransferElement.ele_input_amount).sendKeys(amount);
        } catch (Exception e) {
            Result.checkFail("Class DepositPage | Method InputAmount | Exception desc : " + e.getMessage());
        }
    }

    /*
     * Input deposit description
     */
    public void InputDescription(String description) {
        try {
            getHelper().findElement(fundTransferElement.ele_input_description).sendKeys(description);
        } catch (Exception e) {
            Result.checkFail("Class DepositPage | Method InputDescription | Exception desc : " + e.getMessage());
        }
    }

    public void ClickButtonSubmit() {
        try {
            getHelper().findElement(fundTransferElement.ele_btn_submit).click();
        } catch (Exception e) {
            Result.checkFail("Class DepositPage | Method ClickButtonSubmit | Exception desc : " + e.getMessage());
        }
    }

    /*
     * Click button Reset
     */
    public void ClickButtonReset() {
        try {
            getHelper().findElement(fundTransferElement.ele_btn_reset).click();
        } catch (Exception e) {
            Result.checkFail("Class DepositPage | Method ClickButtonReset | Exception desc : " + e.getMessage());
        }
    }

    /*
     * Input all data & Submit at Deposit Page
     */
    public void AddnewWithdrawalpage(Withdrawal withdrawal) {
        try {
            InputAccountId(withdrawal.accId);
            InputAmount(withdrawal.amount);
            InputDescription(withdrawal.description);

            ClickButtonSubmit();
        } catch (Exception e) {
            Result.checkFail("Class DepositPage | Method AddNewDeposit | Exception desc : " + e.getMessage());
        }
    }

    /*
     * Get new Transaction ID
     */
    public String GetTransactionId() {
        String cusId = "";
        try {
            cusId = getHelper().getTextElement(fundTransferElement.ele_label_table_transaction_id_value);
        } catch (Exception e) {
            Result.checkFail("Class DepositPage | Method GetTransactionId | Exception desc : " + e.getMessage());
        }
        return cusId;
    }

    /*
     * Verify All elements in Deposit Page
     */
    public void VerifyWithdrawalpageAllElements() {
        try {
            getHelper().waitForControlVisible(fundTransferElement.ele_input_acc_id);
            Assert.assertTrue(getHelper().isElementPresent(fundTransferElement.ele_label_deposit_title));
            Assert.assertTrue(getHelper().isElementPresent(fundTransferElement.ele_label_acc_id));
            Assert.assertTrue(getHelper().isElementPresent(fundTransferElement.ele_input_acc_id));
            Assert.assertTrue(getHelper().isElementPresent(fundTransferElement.ele_label_amount));
            Assert.assertTrue(getHelper().isElementPresent(fundTransferElement.ele_input_amount));
            Assert.assertTrue(getHelper().isElementPresent(fundTransferElement.ele_label_description));
            Assert.assertTrue(getHelper().isElementPresent(fundTransferElement.ele_input_description));
            Assert.assertTrue(getHelper().isElementPresent(fundTransferElement.ele_btn_submit));
            Assert.assertTrue(getHelper().isElementPresent(fundTransferElement.ele_btn_reset));
        } catch (Exception e) {
            Result.checkFail("Class DepositPage | Method VerifyDepositPageAllElements | Exception desc : " + e.getMessage());
        }
    }

    /*
     * Verify the header form add new Account
     */
    public void TheWithdrawalFormTitleShouldBe(String formTitle) {
        try {
            Assert.assertTrue(getHelper().elementTextShoudlBe( fundTransferElement.ele_label_deposit_title, formTitle));
        } catch (Exception e) {
            Result.checkFail("Class DepositPage | Method TheDepositFormTitleShouldBe | Exception desc : " + e.getMessage());
        }
    }

    /*
     * Verify new Deposit is added successfully
     */
    public void VerifyDepositAddedSuccessfully(Withdrawal deposit) {
        try {
            Assert.assertTrue(getHelper().elementTextShoudlBe(fundTransferElement.ele_label_table_transaction_id_value, deposit.transId));
            Assert.assertTrue(getHelper().elementTextShoudlBe(fundTransferElement.ele_label_table_acc_id_value, deposit.accId));
            Assert.assertTrue(getHelper().elementTextShoudlBe(fundTransferElement.ele_label_table_amount_value, deposit.amount));
            Assert.assertTrue(getHelper().elementTextShoudlBe(fundTransferElement.ele_label_table_type_transaction_value, deposit.typeOfTransaction));
            Assert.assertTrue(getHelper().elementTextShoudlBe(fundTransferElement.ele_label_table_description_value, deposit.description));
        } catch (Exception e) {
            Result.checkFail("Class DepositPage | Method VerifyCustomerCreatedSuccessfully | Exception desc : " + e.getMessage());
        }
    }
}
