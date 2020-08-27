import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class classMain {
    /*
     * TODO this is a instabot.
     *  about 20 users
     *  According to Instagram, to help reduce spam, Instagram does not allow anyone to follow more than 7,500 people.
     *  Regarding hourly limits, we find that with new accounts that only been active for a few months,
     *  you can only follow / unfollow about 20 users an hour, and a maximum of 100-200 users in a day.
     *
     * TODO
     *  ENCAPSULATE THE BOT INTO IT'S OWN CLASS
     * */
    public static void main(String[] args) {

        System.out.println("hello");
        Scanner input = new Scanner(System.in);
        //creating a browser
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\novac\\SeleniumJAR\\chromedriver.exe");


        InstaBot bot = new InstaBot(new ChromeDriver(),"","");


        int choice = 0;
        while (choice != 6) {
            System.out.println(menu());
            try {
                choice = Integer.parseInt(input.nextLine());
            } catch (InputMismatchException | NumberFormatException ignore) {
            }
            switch (choice) {
                case 1:
                    bot.followMultiple(bot.getDriver(), input, true);
                    break;
                case 2:
                    bot.searchPage(bot.getDriver(), input, false);
                    break;
                case 3:
                    bot.getCommentinputs(bot.getDriver(), input, bot.getExe());
                    break;
                case 4:
                    bot.Hashtaglist(bot.getDriver(), input);
                    break;
                case 5:
                    bot.logout(bot.getDriver());
                    bot.getDriver().close();
                    bot = new InstaBot(new ChromeDriver(),"","");
                    break;
                case 6:
                   System.out.println("Goodbye");
                    break;
                default:
                    System.err.println("invalid input");
                    break;
            }
        }

       // Actions actions = new Actions(browser);


    }//END OF METHOD MAIN.

    private static String menu(){
        return "Bot Menu\n"+
                "1.follow 30\n"+
                "2.search for web page\n"+
                "3.Comment\n"+
                "4.hashtaglink\n"+
                "5.logout\n"+
                "6. exit";
    }

}


/**TODO
 *  i need to create the file write to and write from to get a list of words and hashtage the bot can scroll through and do activity
 *  create a nother account for email and instagram to log on and create activities for testing purposes.
 *
 *
 */
















//  List<WebElement> list =browser.findElements(By.className("_8-yf5 "));
//    System.out.println("size of array: "+list.size());
       /* browser.findElement(By.xpath("/html/body/div[1]/section/main/div/header/section/ul/li[2]/a")).click();
        sleepfor(1);*/
// js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
// browser.findElement(By.xpath("//div[@class=\"isgrP\"]"));
// WebElement list =  browser.findElement(By.xpath("//div[@class=\"isgrP\"]"));
//           js.executeScript("window.scrollBy(0,document.body.scrollHeight);",list);
//        int i=0;
//        while(i<7) {
//            js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
//            sleepfor(1);
//            i++;
//        }
//  browser.findElement(By.xpath("/html/body/div[1]/section/main/section/div/div[2]/div/article[1]/div[1]/button")).click();
//  browser.findElement(By.xpath("/html/body/div[4]/div/div/div/div/button[3]")).click();
// browser.findElement(By.xpath("/html/body/div[4]/div/div/div/div/button[3]")).click();
    /*
       List posts = browser.findElements(By.className("u7YqG"));
        System.out.println("size of list: "+posts.size());
      //  browser.findElement(By.xpath("/html/body/div[1]/section/main/div/div[3]/article/div[1]/div/div[1]/div[1]/a")).click();
        browser.navigate().back();
        button = browser.findElement(By.xpath("/html/body/div[4]/div/div/div/div[3]/button[1]"));
        if(button!=null){
            button.click();
        }
    */