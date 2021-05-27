package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.nio.cs.Surrogate;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;

import java.util.concurrent.TimeUnit;
@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformaçoesUsuarioTest.csv")

public class informacoesUsuarioTest {
    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();

    @Before
    public void SetUp() {
         navegador = Web.createChrome();
        //clicar no link que possui o texto "sign in"
        navegador.findElement(By.linkText("Sign in")).click();
        //Identificar o formulario de login
        WebElement formularioSignInBox = navegador.findElement(By.id("signinbox"));
        //Digitar no campo com nome "login" que esta dentro do formulario de id "signinbox" o texto "julio0001"
        formularioSignInBox.findElement(By.name("login")).sendKeys("julio0001");
        formularioSignInBox.findElement(By.name("password")).sendKeys("123456");
        //clicar no link com texto sign in
        navegador.findElement(By.linkText("SIGN IN")).click();
        //clicar em um link que possui a class "me"
        navegador.findElement(By.className("me")).click();
        //clicar em um link que possui o texto "MORE DATA ABOUT YOU"
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

    }

    @Test
    public void testAdidicionarUmaInformaçaoAdicionalDoUsuario(@Param(name="tipo") String tipo, @Param(name="contato") String contato, @Param(name="mensagem") String mensagemEsperada) {

        //clicar no botao atraves do seu xpath
        navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
        //Identificar a popup onde esta o formulario de id "addmoredata"
        WebElement popupAddMoreData = navegador.findElement(By.id("addmoredata"));
        //na combo de name "type" escolher a opçao
        WebElement campoType = popupAddMoreData.findElement(By.name("type"));
        new Select(campoType).selectByVisibleText(tipo);

        //colocar no campo de name "contact" digitar "+551399999999"
        popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);

        //Clicar no link de text "SAVE" que esta na popup
        popupAddMoreData.findElement(By.linkText("SAVE")).click();

        //Na mensagem de id "toast-container" validar que o texto é "Your contact has been added!"
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals(mensagemEsperada, mensagem);
    }

    @Test
    public void removerDadoDeUsuario() {


        //clicar no elemento pelo seu xpath //sapn[text()="+551399999999"]/following-sibling::a
        navegador.findElement(By.xpath("//span[text()=\"+55138182838485\"]/following-sibling::a")).click();
        //confirmar a janela Javascript
        navegador.switchTo().alert().accept();

        //Validar que a mensagem apresentada foi Rest in peace, dear phone!
        WebElement mensagemPop = navegador.findElement(By.id("toast-container"));
        String mensagem = mensagemPop.getText();
        assertEquals("Rest in peace, dear phone!", mensagem);
        String screenshot = "C:\\Users\\Rafae\\OneDrive\\Documentos\\Nova pasta\\taskit" + Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.tirar(navegador, screenshot);
        //Aguardar ate 10 segundos para que a janela desapareça
        WebDriverWait aguardar = new WebDriverWait(navegador, 10);
        aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));
        //clicar no link com texto "logout"
        navegador.findElement(By.linkText("Logout")).click();
    }


}


