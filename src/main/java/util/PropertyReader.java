package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertyReader {

    static Properties PropDetails = new Properties();
    InputStream input = null;
    /*OutputStream output = null;
    private static String popupButton;
    private static String loginButton;
    private Object pageLoadTimeOut;
     */

    public static String fileRead(String FileName, String ObName) {
        try {

            InputStream FilePath =  PropertyReader.class.getClassLoader().getResourceAsStream(FileName);
            PropDetails.load(FilePath);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String ObValue = PropDetails.getProperty(ObName);
        return ObValue;
    }

    /*
    public int getPageLoadTimeOut() {
        return Integer.parseInt(PropDetails.getProperty("PageLoadTimeOut"));
    }

    public int getImplicitTimeOut() {
        return Integer.parseInt(PropDetails.getProperty("ImplicitWait"));
    }
*/
    public String getWebsite() {
        return fileRead("ProjectData.properties", "URL");
    }

    public String getChromeDriverPath() {
        return fileRead("ProjectData.properties", "ChromePath");
    }

    public long getPageLoadTimeOut() {
        return Long.parseLong(fileRead("ProjectData.properties","PageLoadTimeOut"));
    }

    public long getImplicitTimeOut() {
        return Long.parseLong(fileRead("ProjectData.properties","ImplicitTimeOut"));
    }
}
