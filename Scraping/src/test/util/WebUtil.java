package util;

import com.sun.istack.internal.NotNull;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class WebUtil {

  public static String downloadFile(String href, String pathToSave){
    String ret = "";
    try{
      URL url = new URL(href);
      HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
      httpURLConnection.setRequestMethod("GET");

      /*Возможно, вам придется скопировать файлы cookie, которые Selenium имеет для имитации пользователя Selenium
        (например, если вы тестируете веб-сайт, для которого требуется вход).

        Set<Cookie> cookies = webDriver.manager().getCookies();
        String cookieString = "";

        for (Cookie cookie : cookies) {
            cookieString += cookie.getName() + "=" + cookie.getValue() + ";";
        }
        httpURLConnection.addRequestProperty("Cookie", cookieString);*/

      InputStream in = httpURLConnection.getInputStream();
      Files.copy(in, new File(pathToSave).toPath(),
          StandardCopyOption.REPLACE_EXISTING);
      httpURLConnection.disconnect();
    }catch (Exception e){
      System.out.println("Error occurred in downloadFile: " + e.getMessage());
    }
    return ret;
  }

  public static String getFilenameExtension(String fileName) {
    // если в имени файла есть точка и она не является первым символом в названии файла
    if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
      // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
      return fileName.substring(fileName.lastIndexOf(".") + 1);
      // в противном случае возвращаем заглушку, то есть расширение не найдено
    else return "";
  }

  public static boolean isPageReached(WebDriver driver, String url){
    try{
      driver.navigate().to(url);
    }catch (WebDriverException e){
      System.out.println("Page:" + url + " is not reached");
      return false;
    }
    return true;
  }

  public static void setProxyAtFirefoxJS(FirefoxDriver driver, String host, String port){
    driver.navigate().to("about:config");
    String script = "var prefs = Components.classes[\"@mozilla.org/preferences-service;1\"]" +
        ".getService(Components.interfaces.nsIPrefBranch);" +
        "prefs.setIntPref(\"network.proxy.type\", 1);" +
        "prefs.setCharPref(\"network.proxy.http\", \"" + host + "\");" +
        "prefs.setIntPref(\"network.proxy.http_port\", \"" + port + "\")";
    ((JavascriptExecutor) driver).executeScript(script, host, port);
    try{
      Thread.sleep(1000);
    }catch (InterruptedException e){}
  }

  public static void setNoProxyAtFirefoxJS(FirefoxDriver driver){
    driver.navigate().to("about:config");
    String script = "var prefs = Components.classes[\"@mozilla.org/preferences-service;1\"]" +
        ".getService(Components.interfaces.nsIPrefBranch);" +
        "prefs.setIntPref(\"network.proxy.type\", 0)";
    try{
      Thread.sleep(1000);
    }catch (InterruptedException e){}
  }
}
