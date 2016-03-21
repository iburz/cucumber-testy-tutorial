package org.fasttrackit.exemple;


import com.sdl.selenium.web.utils.Utils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NavigationPage {

    @FindBy(how = How.XPATH, using = "//nav//button")
    private WebElement preference;

    public void navigationPage() {
        preference.click();
   //1000 milliseconds is one second.
        Utils.sleep(1000);

    }

}
