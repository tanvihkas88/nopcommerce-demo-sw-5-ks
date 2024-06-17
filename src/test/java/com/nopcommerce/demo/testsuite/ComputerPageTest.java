package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.BuildYourOwnComputerPage;
import com.nopcommerce.demo.pages.ComputerPage;
import com.nopcommerce.demo.pages.DesktopsPage;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.testdata.TestData;

public class ComputerPageTest extends BaseTest {
    /*
    Inside ComputerPageTest class create following testmethods
1. verifyUserShouldNavigateToComputerPageSuccessfully()
 Click on Computer tab
 Verify "Computer" text

2. verifyUserShouldNavigateToDesktopsPageSuccessfully()
 Click on Computer tab
 Click on Desktops link
 Verify "Desktops" text
 3. verifyThatUserShouldBuildYouOwnComputerAndAddThemToCartSuccessfully(String
processor, String ram, String hdd, String os, String software)
 Click on Computer tab
 Click on Desktops link
 Click on product name "Build your own computer"
 Select processor "processor"
 Select RAM "ram"
 Select HDD "hdd"
 Select OS "os"
 Select Software "software"
 Click on "ADD TO CART" Button
 Verify message "The product has been added to your shopping cart"
 DATA SET
| processor | ram | hdd | os | software |
| 2.2 GHz Intel Pentium Dual-Core E2200 | 2 GB | 320 GB | Vista Home [+$50.00] | Microsoft Office [+$50.00] |
| 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00] | 4GB [+$20.00] | 400 GB [+$100.00]| Vista Premium [+$60.00] | Acrobat Reader [+$10.00] |
| 2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00] | 8GB [+$60.00] | 320 GB | Vista Home [+$50.00] | Total Commander [+$5.00] |

     */
    HomePage homePage;
    ComputerPage computerPage;
    DesktopsPage desktopsPage;
    BuildYourOwnComputerPage buildYourOwnComputerPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        computerPage = new ComputerPage();
        desktopsPage = new DesktopsPage();
        buildYourOwnComputerPage = new BuildYourOwnComputerPage();
    }

    @Test(groups = {"sanity","smoke"})
    public void verifyUserShouldNavigateToComputerPageSuccessfully(){
        homePage.selectMenu("Computers");           //Click on computers
        Assert.assertEquals(computerPage.getComputerHeading(),"Computers");          //Verify heading
    }

    @Test(groups = {"regression","smoke"})
    public void  verifyUserShouldNavigateToDesktopsPageSuccessfully(){
        homePage.selectMenu("Computers");           //Click on computers
        computerPage.clickOnDesktopLink();          //Click on desktops
        Assert.assertEquals(desktopsPage.getDesktopHeading(),"Desktops");        //Verify desktop heading
    }

    @Test(groups = {"regression"}, dataProvider = "buildComData", dataProviderClass = TestData.class)
    public void verifyThatUserShouldBuildYouOwnComputerAndAddThemToCartSuccessfully(String processor, String ram, String hdd, String os, String software){
        homePage.selectMenu("Computers");           //Click on computers
        computerPage.clickOnDesktopLink();          //Click on desktops
        desktopsPage.clickOnBuildYourOwnCompLink();      //Click on 'Build your own computer'

        //selecting specifications
        buildYourOwnComputerPage.selectProcessorFromDropdown(processor);
        buildYourOwnComputerPage.selectRam(ram);
        buildYourOwnComputerPage.selectHddRadio(hdd);
        buildYourOwnComputerPage.selectOs(os);
        buildYourOwnComputerPage.selectCheckBox(software);
        buildYourOwnComputerPage.clickOnAddToCart();            //click on add to cart button
    }
}
