package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AddcontactPage extends BasePage {
    public AddcontactPage(WebDriver navegador) {
        super(navegador);
    }
    public AddcontactPage escolhertipodecontato(String tipo){

        WebElement campoType = navegador.findElement(By.id("addmoredata")).findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

    return this;

    }
    public AddcontactPage digitarcontato(String contato){
        navegador.findElement(By.id("addmoredata")).findElement(By.name("contact")).sendKeys(contato);
        return this;
    }
    public MePage clicaremsalvar(){
        navegador.findElement(By.id("addmoredata")).findElement(By.linkText("SAVE")).click();

        return new MePage(navegador);
    }

    public MePage adicionarContato (String tipo, String contato){
    escolhertipodecontato(tipo);
    digitarcontato(contato);
    clicaremsalvar();
       return new MePage(navegador);
    }
}
