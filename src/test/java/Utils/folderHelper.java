package Utils;

import Utilities.pathHelpers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class folderHelper {
    private static final Logger log = LogManager.getLogger(folderHelper.class.getName());

    public static void deleteFolder(String folderPath) {
        File dir = new File(folderPath);

        if (!dir.isDirectory()) {
            //System.out.println("Not a directory. Do nothing");
            log.info("Not a directory. Do nothing");
            return;
        }
        File[] listFiles = dir.listFiles();
        assert listFiles != null;
        for (File file : listFiles) {
            //System.out.println("Deleting " + file.getName());
            log.info("Deleting " + file.getName());
            file.delete();
        }
    }

    public static void CreateDirectory(String folderPath) {
        File file = new File(folderPath);
        if (!file.exists()) {
            if (file.mkdir()) {
                //System.out.println("Directory is created!");
                log.info(folderPath + " is created.");
            } else {
                //System.out.println("Failed to create directory!");
                log.error(folderPath + " creation failed.");
            }
        }

        // Method to create sub directories
            /*File files = new File("C:\\Directory2\\Sub2\\Sub-Sub2");
            if (!files.exists()) {
                if (files.mkdirs()) {
                    System.out.println("Multiple directories are created!");
                } else {
                    System.out.println("Failed to create multiple directories!");
                }
            }*/
    }
}
