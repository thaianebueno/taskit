package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.LoginPages;
import suporte.Web;

import static org.junit.Assert.assertEquals;

public class informaçoesUsuarioPageObjetiveTest {
    private WebDriver navegador;
    @Before
    public void setUp(){

        navegador = Web.createChrome();
    }

    @Test
    public void testadicionarumainfomaçaodousuario(){
        String textotoast= new LoginPages(navegador)
                .clickSignIn()
                .fazerLogin("julio001","123456")
                .clicarme()
                .clicarnoaddmoredataaboutyou()
                .clicarnobotaoaddmoredataaboutyou()
                .adicionarContato("Phone","+5513996844414")
                .capturarTextodoToast();
        assertEquals("Your contact has been added!", textotoast);




    }
    @After
            public void tearDown () {
        navegador.quit();
    }
}
