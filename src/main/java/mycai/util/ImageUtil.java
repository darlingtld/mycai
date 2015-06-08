package mycai.util;

/**
 * Created by tangld on 2015/6/8.
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtil {

    public static final String basedir = "D:\\Users\\tangld\\IdeaProjects\\mycai_dev\\src\\main\\webapp\\images\\";

    public static String download(String productName, String imgUrl) {
        byte[] btImg = getImageFromNetByUrl(imgUrl);
        String format = getFormatFromUrl(imgUrl);
        if (null != btImg && btImg.length > 0) {
            System.out.println("Read：" + btImg.length + " bytes");
            String fileName = basedir + productName + "." + format;
            writeImageToDisk(btImg, fileName);
        } else {
            System.out.println("Nothing");
        }
        return productName + "." + format;
    }

    private static String getFormatFromUrl(String imgUrl) {
        int dotIndex = imgUrl.lastIndexOf(".");
        String format = imgUrl.substring(dotIndex + 1);
        if (format.length() > 4) {
            format = "jpg";
        }
        return format;
    }


    private static void writeImageToDisk(byte[] img, String fileName) {
        try {
            File file = new File(fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
            System.out.println("image was written to " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static byte[] getImageFromNetByUrl(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(30 * 1000);
            InputStream inStream = conn.getInputStream();
            byte[] btImg = readInputStream(inStream);
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
