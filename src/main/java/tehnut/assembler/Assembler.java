package tehnut.assembler;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Assembler {

    public static String output = "Modpack";
    public static String version = "0.0.0";
    public static File packDir = getWorkingDirectory();
    public static boolean buildClient = false;
    public static boolean buildServer = false;

    static Calendar calendar = Calendar.getInstance();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("-")) {
                if (arg.startsWith("-d="))
                    packDir = getPackFolder(arg.split("=")[1]);

                if (arg.startsWith("-o="))
                    output = arg.split("=")[1];

                if (arg.startsWith("-v="))
                    version = arg.split("=")[1];

                if (arg.equalsIgnoreCase("-client"))
                    buildClient = true;

                if (arg.equalsIgnoreCase("-server"))
                    buildServer = true;
            }
        }

        zipContents();
    }

    public static void zipContents() {
        if (packDir == null) {
            log("The packDir is null", "ERROR");
            return;
        }

        if (packDir.isFile()) {
            log("The packDir cannot be a file", "ERROR");
            return;
        }

        ZipFile zipFile;

        if (buildClient) {
            info("Zipping client");
            zipFile = new ZipFile(Side.CLIENT);
            zipFile.generateFileList();
            zipFile.zipIt();
        }

        if (buildServer) {
            info("Zipping server");
            zipFile = new ZipFile(Side.SERVER);
            zipFile.generateFileList();
            zipFile.zipIt();
        }
    }

    public static String getZipName(Side type) {
        return output + "-" + version + "-" + type.toString();
    }

    public static File getPackFolder(String dir) {
        return dir.charAt(1) == ':' ? new File(dir) : new File(getWorkingDirectory() + "/" + dir);
    }

    public static File getWorkingDirectory() {
        return new File(System.getProperty("user.dir"));
    }

    public static void info(Object toLog) {
        log(toLog, "INFO");
    }

    public static void log(Object toLog, String type) {
        System.out.println("[" + dateFormat.format(calendar.getTime()) + "] [" + type + "] " + toLog);
    }
}
