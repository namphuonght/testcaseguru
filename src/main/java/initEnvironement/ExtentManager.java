package initEnvironement;

import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);

            extent
                    .addSystemInfo("User Name", "Tran")
                    .addSystemInfo("Host Name", "Dinh Hung")
                    .addSystemInfo("Environment", "hh");

            extent.loadConfig(new File("Extent-Config.xml"));
        }

        return extent;
    }
}
