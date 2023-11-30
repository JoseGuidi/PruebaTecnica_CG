package com.PruebaTecnica.Selenium;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@SpringBootTest
class SeleniumApplicationTests {

	@Autowired
	WebDriver webDriver;

	@BeforeAll
	static void setUp(){
		ExtentReports extent = new ExtentReports();
	}
	@Test
	void sendContactForm() {
		ExtentReports extent = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/test.html");
		extent.attachReporter(sparkReporter);
		ExtentTest test =  extent.createTest("MyFirstTest");
		test.log(Status.INFO,"Inicio test");
		webDriver.navigate().to("https://www.consultoriaglobal.com.ar/");
		System.out.println("Se ingresó al sitio");
		WebElement contactButton = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/div/div[2]/a/button"));
		contactButton.click();
		System.out.println("Se ingresó a la seccion de contacto");

		Duration seconds = Duration.ofSeconds(10);
		WebDriverWait durationTime = new WebDriverWait(webDriver,seconds);

		String name = "José Guidi";
		String email = "joseguidi@gmail.com";
		String asunto = "Enviando formulario con selenium";
		String mensaje = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras in hendrerit nulla, et pharetra purus. Quisque odio nisl, cursus at dolor vel, tincidunt tempus dolor. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Mauris odio turpis, pharetra id massa eget, scelerisque blandit lorem. Sed non nisl et tortor aliquam iaculis. Phasellus blandit ultrices ultrices. Aliquam ut velit risus.";
		String captcha = "VXHC";
		WebElement inputName = durationTime.until(ExpectedConditions.visibilityOfElementLocated(By.name("your-name")));
		inputName.sendKeys(name);
		System.out.println("Se ingresó el nombre: " + name );

		WebElement inputEmail = durationTime.until(ExpectedConditions.visibilityOfElementLocated(By.name("your-email")));
		inputEmail.sendKeys(email);
		System.out.println("Se ingresó el email: " + email );

		WebElement inputSubject = durationTime.until(ExpectedConditions.visibilityOfElementLocated(By.name("your-subject")));
		inputSubject.sendKeys(asunto);
		System.out.println("Se ingresó el asunto: " + asunto );

		WebElement inputMsg = durationTime.until(ExpectedConditions.visibilityOfElementLocated(By.name("your-message")));
		inputMsg.sendKeys(mensaje);
		System.out.println("Se ingresó el mensaje: " + mensaje );

		WebElement inputCaptcha = durationTime.until(ExpectedConditions.visibilityOfElementLocated(By.name("captcha-636")));
		inputCaptcha.sendKeys(captcha);
		System.out.println("Se ingresó el captcha: " + captcha );


		WebElement sendButton = webDriver.findElement(By.xpath("//*[@id=\"wpcf7-f1297-p370-o1\"]/form/p[7]/input"));
		sendButton.click();
		System.out.println("Se envio el formulario");
		try{
			WebElement alertInvalidMail = durationTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wpcf7-f1297-p370-o1\"]/form/p[2]/span/span")));
			System.out.println("El email ingresado es invalido");
			test.log(Status.PASS,"Test pasó correctamente ya que se ingreso mail invalido");
		}catch (Exception e){
			System.out.println("El email ingresado es valido");
			test.log(Status.FAIL,"El test no pasó correctamente ya que se ingreso mail valido");

		}

		extent.flush();

	}

}
