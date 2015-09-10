package tehnut.assembler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFile {

    String outputZip;
    Side side;
    List<File> files = new ArrayList<>();

    /**
     *
     * @param side - The {@link Side} to build the zip for.
     */
    public ZipFile(Side side) {
        this.side = side;
        this.outputZip = Assembler.getZipName(side) + ".zip";
    }

    /**
     * Generates a list of all files to be added to the zip folder.
     *
     * @return - Returns itself for chaining if needed.
     */
    public ZipFile generateFileList() {
        generateFiles(new File(Assembler.getWorkingDirectory() + "/config"), "config");
        generateFiles(new File(Assembler.getWorkingDirectory() + "/mods/" + side.toString()), "mods");
        generateFiles(new File(Assembler.getWorkingDirectory() + "/mods/" + Side.COMMON.toString()), "mods");
        generateFiles(new File(Assembler.getWorkingDirectory() + "/extra/" + side.toString()), "extra");
        generateFiles(new File(Assembler.getWorkingDirectory() + "/extra/" + Side.COMMON.toString()), "extra");

        return this;
    }

    /**
     * Zips up the files generated from {@code generateFileList()}.
     *
     * @return - Returns itself for chaining if needed.
     */
    public ZipFile zipIt() {

        try {
            byte[] buffer = new byte[1024];

            ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(outputZip));
            for (File file : files) {

                FileInputStream inputStream = new FileInputStream(file);
                outputStream.putNextEntry(new ZipEntry(file.getPath()));

                int len;
                while ((len = inputStream.read(buffer)) > 0)
                    outputStream.write(buffer, 0, len);

                outputStream.closeEntry();
                inputStream.close();
            }

            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    /**
     * @param folder   - The folder to scan for files.
     * @param fileType - The type of folder. Generally {@code config}, {@code mods}, or {@code extra}
     */
    @SuppressWarnings("ConstantConditions")
    private void generateFiles(File folder, String fileType) {
        if (folder != null && folder.isDirectory()) {
            for (File file : folder.listFiles()) {
                File add = new File(fileType + File.separator + file.getName());
                files.add(file);
                Assembler.info("Added file: " + add);
            }
        }
    }
}
