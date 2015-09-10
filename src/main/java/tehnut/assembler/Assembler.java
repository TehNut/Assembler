package tehnut.assembler;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A program written for easily building FTB styled modpacks from a simple file structure.
 *
 * Possible start args are:
 *
 * <ul>
 *     <li>-d=DIRECTORY : Can be absolute or relative. Defaults to the directory terminal was opened in.</li>
 *     <li>-o=PACKNAME : The output file name. Defaults to "Modpack"</li>
 *     <li>-v=VERSION : The version of the modpack. Defaults to "0.0.0"</li>
 *     <li>-client : Generates the client zip file. Defaults to false.</li>
 *     <li>-server : Generates the server zip file. Defaults to false.</li>
 * </ul>
 *
 * Mods in {@code mods/client} will only be loaded into the client zip while mods in {@code mods/server} will only be loaded into the server zip. Mods found in {@code mods/common} will be loaded into both.
 * The same is true for {@code extra/(client/server/common)}.
 * All configs found in {@code config} will be added to both zips.
 */
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

    /**
     * Once the arg information is obtained and parsed, build it!
     */
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

    /**
     *
     * @param side - The {@link Side} the pack is being built for.
     * @return     - The file name for the zip.
     */
    public static String getZipName(Side side) {
        return output + "-" + version + "-" + side.toString();
    }

    /**
     *
     * @param dir - The directory obtained from the launch args
     * @return    - The folder that the pack is to be assembled from
     */
    public static File getPackFolder(String dir) {
        return dir.charAt(1) == ':' ? new File(dir) : new File(getWorkingDirectory() + "/" + dir);
    }

    /**
     *
     * @return - The user's current working directory
     */
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
