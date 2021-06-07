
package utility;

import java.io.File;

public class FolderFile {

    public static boolean createFolder(String folderPath) {
        File file = new File(folderPath);
        if (!file.exists()) {
            if (file.mkdir()) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Directory was exist !");
            return false;
        }
    }


    public static boolean createMutilFolder(String folderPath) {
        File file = new File(folderPath);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("Directory was exist !");
            return false;
        }
    }
}
