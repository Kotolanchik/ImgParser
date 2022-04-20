import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws IOException {
//        Document document = Jsoup.connect("https://yandex.ru/images/search?text=%D0%BA%D0%BE%D1%82%D1%8B").get();
//
//        List<String> links = new ArrayList<>();
//
//        Elements el = document.getElementsByClass("serp-item__link");
//        for (Element element : el) {
//            links.add(element.attr("href") + "\n\n");
//        }


        System.setProperty("webdriver.chrome.driver", "selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://yandex.ru/images/search?text=коты");
        var images = webDriver.findElements(By.xpath("//div[contains(@class, 'serp-item_type_search')]//img"));

        ArrayList<String> urlImgList = new ArrayList<>();

        for (var img : images) {
            urlImgList.add(img.getAttribute("src"));
        }

        urlImgList.forEach(System.out::println);



        //Document document1 = Jsoup.connect("https://yandex.ru" + urlImgList.get(0)).userAgent("Mozilla").get();
        //System.out.println(document1.selectXpath("/html/body/div[12]/div[2]/div/div/div/div[3]/div/div[3]/div/div/div[1]/div[4]/div[1]/a"));


        //URL url = new URL(document1.selectXpath("/html/body/div[12]/div[2]/div/div/div/div[3]/div/div[3]/div/div/div[1]/div[4]/div[1]/a").html());
        for (int i = 0; i < urlImgList.size(); i++) {
            URL url = new URL(urlImgList.get(i));

            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream("images\\image" + i + ".jpg");

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        }
    }

}
