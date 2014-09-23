import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by Alexey on 22.09.2014.
 */
    public class FilmSearcher extends TestBase {

    final String INCORRECT_FILM_NAME = UUID.randomUUID().toString();
    @Test
    public void filmSearchTest()  {
        loginAs("admin", "admin");
        List<WebElement> films = driver.findElements(By.xpath(".//*[@class='title']"));
        if (films.isEmpty()) {
            System.out.println("No Films Found");
            return;
            }
            String firstFilm = films.get(0).getText();
            filmSearchingBy(firstFilm);
            List<WebElement> searchByFirstFilm = driver.findElements(By.xpath(".//*[@class='title']"));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.
                    xpath(".//*[@class='title']")));
            assertEquals(searchByFirstFilm.size(), 1);
            assertEquals(searchByFirstFilm.get(0).getText(), firstFilm);
            // search with incorrect data
            filmSearchingBy(INCORRECT_FILM_NAME);
            wait.until(ExpectedConditions.stalenessOf(films.get(0)));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'content') and .//text()='No movies where found.']")));
        }
}