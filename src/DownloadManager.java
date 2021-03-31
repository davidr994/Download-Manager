import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class DownloadManager implements Runnable{

    private String link;
    private File outputFile;

    private String fileSeparator = System.getProperty("file.separator");
    private String downloadFolderPath = fileSeparator + "Users" + fileSeparator + "david" + fileSeparator+"Desktop" + "Downloads";

    private File defaultDownloadFolder = new File(downloadFolderPath);

    public DownloadManager (String link) {
        this.link = link;

        if(!defaultDownloadFolder.exists()) {
            defaultDownloadFolder.mkdirs();
        }
    }

    @Override
    public void run() {
        try {
            URL url = new URL(link);
            HttpURLConnection hConnection = (HttpURLConnection) url.openConnection();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(hConnection.getInputStream());

            //Create data
            outputFile = new File(defaultDownloadFolder, "datei");
            OutputStream outputStream = new FileOutputStream(outputFile);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024); //1024 buyte

            byte[] buffer = new byte[1024];
            int downloaded = 0;
            int readByte = 0;

            while((readByte = bufferedInputStream.read(buffer, 0, 1024)) >= 0) {
                bufferedOutputStream.write(buffer, 0, readByte);
                downloaded += readByte;

                System.out.println("Already " + downloaded + "Byte" + " downloaded");
            }

            bufferedOutputStream.close();
            bufferedInputStream.close();
            System.out.println("Download finished");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
