package neocodesoftware.com;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.Random;

public class CHNHFAppsTest extends TestBase {

  @Test
  public void stepsTest(){
    driver.manage().window().maximize();
    //driver.get("https://test.canadahomestaynetwork.ca/chn-host-family-application/?step=1");
    driver.get("https://chn-host.neocodesoftware.com/chn-host-family-application/?step=1");
    driver.findElement(By.cssSelector("input.new_app")).click();
    completeStep1();
    completeStep2();
    completeStep3();
    completeStep4();
    completeStep5();
    completeStep6();
    completeStep7();
//    completeStep8();
    takeScreenshot();
  }

  private void completeStep1(){
    driver.findElement(By.cssSelector("input[name='primary_first_name']"))
        .sendKeys("Steve");
    driver.findElement(By.cssSelector("input[name='primary_last_name']"))
        .sendKeys("Martin");
    driver.findElement(By.cssSelector("select[name='primary_gender'] option[value='Male']")).click();
    driver.findElement(By.cssSelector("select[name='primary_age_range'] option[value='30-39']")).click();
    driver.findElement(By.cssSelector("input[name='primary_occupation']"))
        .sendKeys("bookkeeper");
    driver.findElement(By.cssSelector("input[name='primary_home_phone_areacode']"))
        .sendKeys("123");
    driver.findElement(By.cssSelector("input[name='primary_home_phone_exchange']"))
        .sendKeys("456");
    driver.findElement(By.cssSelector("input[name='primary_home_phone_line']"))
        .sendKeys("7890");
    driver.findElement(By.cssSelector("input[name='street']"))
        .sendKeys("AnyStreet");
    driver.findElement(By.cssSelector("input[name='city']"))
        .sendKeys("AnyCity");
    driver.findElement(By.cssSelector("input[name='postal_code']"))
        .sendKeys("65432");
    driver.findElement(By.cssSelector("select[name='province'] option[value='NB']")).click();
    driver.findElement(By.cssSelector("select[name='region_id'] option[value='19']")).click();
    driver.findElement(By.cssSelector("input[name='high_school']"))
        .sendKeys("Loyola High School");
    WebElement primaryEmailElement = driver.findElement(By.cssSelector("input[name='primary_email']"));
    do{
      primaryEmailElement.clear();
      String email = "steve_martin" + new Random().nextInt(999) + "@gmail.com";
      primaryEmailElement.sendKeys(email);
      driver.findElement(By.cssSelector("a.next-link")).click();
    }while(driver.findElements(By.xpath("//div[@class='err_msg'][contains(text(),'Email already in the system')]")).size() != 0);
    wait.until(ExpectedConditions.urlContains("?step=2"));
  }

  private void completeStep2(){
    //wait.until(ExpectedConditions.textToBe((By.cssSelector("div.chi_acf_field  p")),"Home stuff"));
    driver.findElement(By.cssSelector("select[name='style'] option[value='Duplex']")).click();
    driver.findElement(By.cssSelector("textarea[name='description']"))
        .sendKeys("a brief description of my home and the surrounding area");
    driver.findElement(By.cssSelector("input[name='num_bedrooms']"))
      .sendKeys("5");
    driver.findElement(By.cssSelector("input[name='num_bathrooms']"))
        .sendKeys("3");
    driver.findElement(By.cssSelector("input[name='num_student_bedrooms']"))
        .sendKeys("2");
    driver.findElement(By.cssSelector("input[name='num_student_bathrooms']"))
        .sendKeys("1");
    driver.findElement(By.cssSelector("input[name='max_students']"))
        .sendKeys("4");
    driver.findElement(By.cssSelector("select[name='student_bedroom'] option[value='private']")).click();
    driver.findElement(By.cssSelector("select[name='student_bathroom'] option[value='Shared with other student(s)']"))
        .click();
    driver.findElement(By.cssSelector("select[name='internet'] option[value='Yes']")).click();
    driver.findElement(By.cssSelector("input[name='res_first_name[]']")).sendKeys("Robin");
    driver.findElement(By.cssSelector("input[name='res_last_name[]']")).sendKeys("Bobin");
    driver.findElement(By.cssSelector("select[name='res_gender[]'] option[value='Male']")).click();
    driver.findElement(By.cssSelector("select[name='res_relationship[]'] option[value='Son']")).click();
    driver.findElement(By.cssSelector("input[name='dogs_stay[]'][value='Outside']")).click();
    driver.findElement(By.cssSelector("a.next-link")).click();
    wait.until(ExpectedConditions.urlContains("?step=3"));
  }

  private void completeStep3(){
    driver.findElement(By.cssSelector("select[name='preferred_gender'] option[value='Either']")).click();
    driver.findElement(By.cssSelector("input[name='preferred_age[]'][value='No preference']")).click();
    driver.findElement(By.cssSelector("select[name='will_take_smoker'] option[value='No']")).click();
    driver.findElement(By.cssSelector("input[name='dietary_restrictions'][value='No']")).click();
    driver.findElement(By.cssSelector("select[name='help_with_homework'] option[value='Sometimes']")).click();
    driver.findElement(By.cssSelector("input[name='availability[]'][value='Year-Round']")).click();
    driver.findElement(By.cssSelector("input[name='smoking'][value='No']")).click();
    driver.findElement(By.cssSelector("textarea[name='household_rules']")).sendKeys("No rules");
    driver.findElement(By.cssSelector("input[name='hobbies[]'][value='Computers']")).click();
    driver.findElement(By.cssSelector("input[name='hobbies[]'][value='Table Tennis']")).click();
    driver.findElement(By.cssSelector("a.next-link")).click();
    wait.until(ExpectedConditions.urlContains("?step=4"));
  }

  private void completeStep4() {
    driver.findElement(By.cssSelector("textarea[name='languages']")).sendKeys("English");
    driver.findElement(By.cssSelector("input[name='previous_host'][value='No']")).click();
    driver.findElement(By.cssSelector("div.references_table > div:nth-child(3) > div.ref_first_name > input"))
            .sendKeys("Bill");
    driver.findElement(By.cssSelector("div.references_table > div:nth-child(3) > div.ref_last_name > input"))
            .sendKeys("Clinton");
    driver.findElement(By.cssSelector("div.references_table > div:nth-child(3) > div.ref_occupation > input"))
            .sendKeys("pensioner");
    driver.findElement(By.cssSelector("div.references_table > div:nth-child(3) > div.ref_phone > input"))
            .sendKeys("1234567890");

    driver.findElement(By.cssSelector("div.references_table > div:nth-child(5) > div.ref_first_name > input"))
            .sendKeys("Tomas");
    driver.findElement(By.cssSelector("div.references_table > div:nth-child(5) > div.ref_last_name > input"))
            .sendKeys("Bach");
    driver.findElement(By.cssSelector("div.references_table > div:nth-child(5) > div.ref_occupation > input"))
            .sendKeys("pensioner");
    driver.findElement(By.cssSelector("div.references_table > div:nth-child(5) > div.ref_phone > input"))
            .sendKeys("4321987650");
    //driver.findElement(By.cssSelector("a#addRowRef")).click();
    driver.findElement(By.cssSelector("a.next-link")).click();
    wait.until(ExpectedConditions.urlContains("?step=5"));
  }

  private void completeStep5() {
    driver.findElement(By.cssSelector("input#family_photo_input"))
    .sendKeys(new File("resources\\family.jpg").getAbsolutePath());
    driver.findElement(By.cssSelector("#chifa-family-photo > div.image_wrap > button")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#chifa-family-photo > div.image_wrap > div > img")));
    driver.findElement(By.cssSelector("input#home_photo_input"))
            .sendKeys(new File("resources\\house.jpg").getAbsolutePath());
    driver.findElement(By.cssSelector("#chifa-home-photo > div.image_wrap > button")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#chifa-home-photo > div.image_wrap > div > img")));
    driver.findElement(By.cssSelector("a.next-link")).click();
    wait.until(ExpectedConditions.urlContains("?step=6"));
  }

  private void completeStep6() {
    driver.findElement(By.cssSelector("input#crimcheck1_document_input"))
            .sendKeys(new File("resources\\Criminal_Check.txt").getAbsolutePath());
    driver.findElement(By.cssSelector("#crimcheck1-document > div > div.column_2 > button")).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#preview_crimcheck1_document")));
    driver.findElement(By.cssSelector("a.next-link")).click();
    wait.until(ExpectedConditions.urlContains("?step=7"));
  }

  private void completeStep7() {
    driver.findElement(By.cssSelector("select[name='how_recruited'] option[value='Internet']")).click();
    driver.findElement(By.cssSelector("select[name='casl_consent'] option[value='No']")).click();
    driver.findElement(By.cssSelector("a.next-link")).click();
    wait.until(ExpectedConditions.urlContains("?step=8"));
  }

  private void completeStep8() {
    driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.chi_registration_iframe")));
    driver.findElement(By.cssSelector("input[name='password']")).sendKeys("somepassword");
    driver.findElement(By.cssSelector("input[name='confirmPassword']")).sendKeys("somepassword");
    driver.findElement(By.cssSelector("input[name='registration.register]'")).click();
    wait.until(ExpectedConditions.textToBe((By.cssSelector("td.header")),"Success!"));
  }
}
