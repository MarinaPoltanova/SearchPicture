import helpers.Sleeper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Main {
    public static String word = "автокран";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();

        driver.get("https://yandex.ru/");
        Sleeper.sleep(5);

        WebElement idPictures = driver.findElement(By.xpath("//*[@data-id='images']"));
        idPictures.click();
        Sleeper.sleep(5);

        for (String childTab : driver.getWindowHandles()) {
            driver.switchTo().window(childTab);
        }

        WebElement idText = driver.findElement(By.xpath("//*[@class='input__control']"));
        idText.sendKeys(word);
        Sleeper.sleep(5);

        WebElement idSearchButton = driver.findElement(By.xpath("//*[@class='websearch-button__text']"));
        idSearchButton.click();
        Sleeper.sleep(5);

        String idAlt = driver.findElement(By.xpath("//*[@class='serp-item__thumb justifier__thumb']")).getAttribute("alt");

        if (idAlt.contains(word)) {
            System.out.println("В alt-тексте есть слово " + word);
        } else {
            System.out.println("В alt-тексте нет слова " + word);
        }

        driver.close();
        for (String childTab : driver.getWindowHandles()) {
            driver.switchTo().window(childTab);
        }
        driver.close();
        //закрыть первое окно браузера
    }
}
