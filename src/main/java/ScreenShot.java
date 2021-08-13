import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {

    private WebDriver driver;
    private String screenURL = "https://www.onliner.by/";
    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    String pathToFile = "src/main/resources/";

    public ScreenShot(WebDriver driver) {
        this.driver = driver;
    }

    public void getScreen () throws IOException {
        driver.get(screenURL);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(pathToFile+ timeStamp + "_" + driver.getTitle() + ".jpg"));
    }
}
