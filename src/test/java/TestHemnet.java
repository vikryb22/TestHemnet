
import org.junit.jupiter.api.Test;
import org.junit.validator.PublicClassValidator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.microsoft.playwright.assertions.LocatorAssertions.HasTextOptions;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

import java.nio.charset.IllegalCharsetNameException;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class TestHemnet extends TestBase{
	
	  @Test
	  void LoggaInAsUser() {
		  
		  
		///Locators
		  
		  Locator loggaInButton = page.getByRole(AriaRole.BUTTON,new GetByRoleOptions().setName("Logga in med lösenord icon"));
		  Locator usernameInput = page.locator("#login-form-email");
		  Locator userPassword = page.locator("#login-form-password");
		  Locator loggaIn = page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Logga in"));
		  
		  
		/// Interactions
		  loggaInButton.click();
		  usernameInput.fill("adminTest");
		  userPassword.fill("adminTest");
		  
		  // Assertion
		 assertThat(page.getByText("Logga ut"));
		  	  
	
		  }
	  
	  
	  @Test
	        void SökBostad() {
	   
	        	String Url = "https://www.hemnet.se/";
	        	
	   	page.navigate(Url);
	        	page.getByPlaceholder("Skriv område eller adress").fill("Grimsta");
	        	
	       
	        
	  }
	        @Test
	        void SökMaklare() {
	        	
	        	String Url = "https://www.hemnet.se/";
	        	Locator sokmaklareLocator=page.getByRole(AriaRole.LINK, new GetByRoleOptions().setName("Sök mäklare"));
	        	Locator searchMaklare=page.getByRole(AriaRole.TEXTBOX, new GetByRoleOptions().setName("Skriv område eller adress"));
	        			
	        	page.navigate(Url);
	        	sokmaklareLocator.click();
	        	searchMaklare.fill("Brändström");
	        
	        	  }
	        
	        
	        
	        @Test
	        void NegativTest() {
	        	
	        	String Url = "https://www.hemnet.se/";
	        	Locator sokmaklareLocator=page.getByRole(AriaRole.LINK, new GetByRoleOptions().setName("Sök mäklare"));
	        	Locator searchMaklare=page.getByRole(AriaRole.TEXTBOX, new GetByRoleOptions().setName("Skriv område eller adress"));
	            Locator söknegativLocator=page.locator(".AutoCompleteNotice_noticeWrapper__CUyB9");

	
	        	page.navigate(Url);
	        	sokmaklareLocator.click();
	        	searchMaklare.fill("1234!!!");
	        	
	        	assertThat(söknegativLocator).isVisible();
	        }
	        
	        
	        
	        @Test
	        public void takeScreenshot() {
	            page.navigate("https://www.hemnet.se");
	            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("screenshot.png")));
	        }
	        
	        
	        
	        
	        @Test
	  	  void LoggaInWithoutPassword() {
	  		  
	  		
	  		  
	  		///Locators
	  		  
	  		  Locator loggaInButton = page.getByRole(AriaRole.BUTTON,new GetByRoleOptions().setName("Logga in med lösenord icon"));
	  		  Locator usernameInput = page.locator("#login-form-email");
	  		  Locator userPassword = page.locator("#login-form-password");
	  		  Locator loggaIn = page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Logga in"));
	  		  
	  		  
	  		/// Interactions
	  		  loggaInButton.click();
	  		  usernameInput.fill("adminTest");
	  		  userPassword.fill("");
	  		  
	  		  // Assertion
	  		 assertThat(page.getByText("Felaktigt användarnamn eller lösenord"));
	  		  	  
	  	
	  		  }
	        
	      

	         
	         @Test
	         public void testFilterByNumberOfRooms() {
	             page.navigate("https://www.hemnet.se");

	             Locator antalRumOption = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Minst antal rum"));
	             Locator visaSökFilterLocator = page.getByText("Visa sökfilter");

	             visaSökFilterLocator.click();
	             antalRumOption.click();

	             antalRumOption
	                     .selectOption(new SelectOption[]{new SelectOption().setLabel("8 rum")});
	             
	             
	             assertThat(antalRumOption).isVisible();
	         }

	         
	 
	         
	 
	         @Test
	         public void testFilterByPris() {
	        	 
	         
	             page.navigate("https://www.hemnet.se");

	             Locator filtreradeResultat = page.getByLabel("Högst prisInget100 000 kr200 000 kr300 000 kr400 000 kr500 000 kr750 000 kr1 000 000 kr1,25 milj. kr1,5 milj. kr1,75 milj. kr2 milj. kr2,25 milj. kr2,5 milj. kr3 milj. kr3,5 milj. kr4 milj. kr4,5 milj. kr5 milj. kr5,5 milj. kr6 milj. kr7 milj. kr8 milj. kr9 milj. kr10 milj. kr11 milj. kr12 milj. kr13 milj. kr15 milj. kr20 milj. kr")
;
	             Locator visaSökFilterLocator = page.getByText("Visa sökfilter");

	             visaSökFilterLocator.click();

	             
	             filtreradeResultat.click();

	            
	             filtreradeResultat
	             .selectOption("750000");
	             assertThat(filtreradeResultat).isVisible(); 
	        
}


			@Test
			  void LoggaInWithMagicLink() {
				 
				  
				///Locators
				  Locator loggaInButton = page.getByRole(AriaRole.BUTTON,new GetByRoleOptions().setName("Logga in med lösenord icon"));
				  
				  Locator loggaInUtanLösenOrdButton = page.getByRole(AriaRole.BUTTON,new GetByRoleOptions().setName("Logga in utan lösenord"));
				  
				  Locator usernameInput = page.locator("#send-magic-link-email");
				  Locator loggaIn = page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Logga in"));
				  
				  
				/// Interactions
				  loggaInButton.click();
				  loggaInUtanLösenOrdButton.click();
			
				  usernameInput.fill("adminTest");
				 
				  // Assertion
				  assertThat(page.getByText("Logga ut"));
				  
			  }
	         
}      

