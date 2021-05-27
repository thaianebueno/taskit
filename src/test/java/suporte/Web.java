package suporte;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Web {
    public static WebDriver createChrome() {
        //Abrindo o navegador
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rafae\\driver\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();
        // WebDriver navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //Nagevando para pagina do Taskit!
        navegador.get("http://www.juliodelima.com.br/taskit");
        return navegador;
    }
}
