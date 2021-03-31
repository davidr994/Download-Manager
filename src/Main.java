public class Main {
    public static void main(String[] args) {

        //DownloadManager downloadManager = new DownloadManager("Test");

        String downloadLink= "";

        new Thread(new DownloadManager(downloadLink)).start();
    }
}




