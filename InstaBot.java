import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class InstaBot {

    private WebDriver browser;
    private JavascriptExecutor javascript;

    public InstaBot(WebDriver browser, String username, String password){
    this.browser = browser;
    browser.get("https://www.instagram.com/");
    javascript = (JavascriptExecutor) browser;
    //System.out.println("Welcome to "+browser.getTitle());
    browser.manage().window().maximize();
    sleepfor(2);
    //New credentials
    login(browser,username,password);
}

    private static void login(WebDriver browser, String username, String password) {

        //TODO TESTING DO NOT USE REAL CREDENTIALS.
        username = "puppiesRlords@protonmail.com";
        password = "xxxxxx";
        //Must input credentials! will come when instagram accounts are up and active
        /*TODO MAJOR SECURITY RISK*/

        browser.findElement(By.name("username")).sendKeys(username);
        browser.findElement(By.name("password")).sendKeys(password);
        browser.findElement(By.xpath("//*[@id=\"loginForm\"]/div/div[3]/button")).click();
        sleepfor((float) 2.5);
        WebElement button;
        try {
            button = browser.findElement(By.xpath("/html/body/div[1]/section/main/div/div/div/section/div/button"));
            button.click();
            sleepfor(1);
        } catch (NoSuchElementException ignore){}
        sleepfor(3);
        try {
            button = browser.findElement(By.xpath("/html/body/div[4]/div/div/div/div[3]/button[1]"));
            button.click();
            sleepfor(1);
        } catch (NoSuchElementException ignore){}
    }


    public static void searchPage(WebDriver browser, Scanner input, boolean hash){
        //TODO LATER IT'S GOING TO FEED A SCANNER OBJECT TO READ FROM LIST FILE.
        System.out.print("Search for >> ");
        String search = "";
        if(hash) {
            search="#";
        }
        search += input.nextLine();
        browser.findElement(By.xpath("/html/body/div[1]/section/nav/div[2]/div/div/div[2]/input")).sendKeys(search);
        sleepfor(2);
        browser.findElement(By.xpath("/html/body/div[1]/section/nav/div[2]/div/div/div[2]/div[3]/div[2]/div/a[1]")).click();
        sleepfor(4);
    }


    public static void followMultiple(WebDriver browser, Scanner input, Boolean destination) {
        sleepfor(1);
        if (!destination) {
            searchPage(browser, input, false);
        }
        int stop = 2;
        List<WebElement> posts;
        while (!(stop <= 1)) {
            try {

                browser.findElement(By.xpath("/html/body/div[1]/section/main/div/header/section/ul/li[2]/a")).click();
                sleepfor(1);
            }catch(NoSuchElementException e){
                System.err.println("unable to process");
                break;
            }

            posts = browser.findElements(By.xpath("//button[@class=\"sqdOP  L3NKy   y3zKF     \"]"));
            // System.out.println("/html/body/div[1]/section/main/section/div/div[2]/div/article[2]/div[3]/section[1]/span[1]/button/div/span/svg");
            for (WebElement post : posts) {
                post.click();
            }
            System.out.println("this is the size of this : " + posts.size());
            stop = posts.size();
            browser.navigate().refresh();
            sleepfor(1);
        }
    }


    static List<WebElement> comments = new ArrayList<>();

    public void getCommentinputs(WebDriver browser, Scanner input,JavascriptExecutor js){
        int i=0;
        System.out.print("Please enter a comment you wish to broadcast: "); String comment = input.nextLine();
        WebElement text;
        while(i<10) {
            sleepfor(1);
            comments.addAll(browser.findElements(By.xpath("//textarea[@class=\"Ypffh\"]")));
            System.out.println("Number of comment boxes "+ comments.size());
            text = browser.findElement(By.xpath("//textarea[@class=\"Ypffh\"]"));
            text.click();
            browser.findElement(By.xpath("//textarea[@class=\"Ypffh focus-visible\"]")).sendKeys(comment,Keys.ENTER);
            js.executeScript("window.scrollBy(0,650)");
            i++;
        }
        js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
        //  massComment(comments,comment);
    }




    public void Hashtaglist(WebDriver browser,Scanner input){
        //TODO THIS ONLY LIKES FOR NOW...
        sleepfor((float)0.5);
       // System.out.println("enter comment: ");
        //String comment = input.nextLine();
        searchPage(browser,input,true);
        String comment = "Marketing Online aint as hard as it seems. DM us to grow your business";
        browser.findElement(By.xpath("/html/body/div[1]/section/main/article/div[1]/div/div/div[1]/div[1]/a")).click();
        int trigger =0;
        while(trigger != 20) {
            Like(browser).click();
            try {
                browser.findElement(By.xpath("//textarea[@class=\"Ypffh\"]")).click();
            }catch (NoSuchElementException ignore){
            }
           // sleepfor((float)0.5);
            // browser.findElement(By.xpath("//textarea[@class=\"Ypffh focus-visible\"]")).sendKeys(comment, Keys.ENTER);
            sleepfor((float)0.5);
            browser.findElement(By.xpath("/html/body/div[4]/div[1]/div/div/a")).sendKeys(Keys.ARROW_RIGHT);
            sleepfor((float)0.5);
            trigger++;
        }
        browser.findElement(By.xpath("/html/body/div[4]/div[3]/button")).click();
    }

    public void logout(WebDriver browser){
        browser.findElement(By.xpath("/html/body/div[1]/section/nav/div[2]/div/div/div[3]/div/div[4]/span")).click();
        browser.findElement(By.xpath("/html/body/div[1]/section/nav/div[2]/div/div/div[3]/div/div[4]/div[2]/div/div[2]/div[2]/div")).click();
        System.out.println("good-Bye");
    }
    public WebDriver getDriver(){
        return browser;
    }
    public JavascriptExecutor getExe(){
        return javascript;
    }


    private static void massComment(List<WebElement> comments,String comment){
        for(WebElement box: comments){
            box.click();
            box.sendKeys(comment,Keys.ENTER);
        }
    }

    private static WebElement Like(WebDriver browser){
        sleepfor(1);
        return browser.findElement(By.xpath("/html/body/div[4]/div[2]/div/article/div[3]/section[1]/span[1]/button"));
    }

    private static void sleepfor(float x){
        try {
            Thread.sleep((int)(x*1000));
        } catch (InterruptedException e) {
            System.out.println();
        }
    }


}






