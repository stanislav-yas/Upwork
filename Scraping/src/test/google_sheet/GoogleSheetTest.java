package google_sheet;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleSheetTest {

  private static final String APPLICATION_NAME = "Google Sheets API Example";
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  private static final String TOKENS_DIRECTORY_PATH = "tokens";

  /**
   * Global instance of the scopes required by this quickstart.
   * If modifying these scopes, delete your previously saved tokens/ folder.
   */
  private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
  private static final String CREDENTIALS_FILE_PATH = "results/credentials.json";

  private static Credential getCredential() throws IOException, GeneralSecurityException {
    GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH))
        .createScoped(SCOPES);
    return  credential;
/*
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
        JSON_FACTORY,
        new InputStreamReader(GoogleSheetTest.class
            .getResourceAsStream(CREDENTIALS_FILE_PATH)));
    // set up authorization code flow
    GoogleAuthorizationCodeFlow flow =
        new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(new FileDataStoreFactory(new File(TOKENS_DIRECTORY_PATH))).build();
*/

  }

  @Test
  public void testSheet(){
    Credential credential = null;
    NetHttpTransport transport = null;
    try{
      credential = getCredential();
      transport = GoogleNetHttpTransport.newTrustedTransport();
      Sheets service = new Sheets.Builder(transport, JSON_FACTORY, credential)
          .setApplicationName(APPLICATION_NAME)
          .build();
      String sheetTitle = "My_testSheet";
      Spreadsheet spreadsheet = new Spreadsheet()
          .setProperties(new SpreadsheetProperties()
              .setTitle(sheetTitle));
      spreadsheet = service.spreadsheets().create(spreadsheet)
          .setFields("spreadsheetId")
          .execute();
      System.out.println("Spreadsheet ID: " + spreadsheet.getSpreadsheetId()); //Spreadsheet ID: 1ZptU7RYb9QZY_Ym1dx2GGbsLtCFsltqyir1QCJjz3Fg
      //1M2-5JRUkuRL15r_04GPT4a4QKfEtX0SJyMkrk07RUm0
    }catch (Exception e ){
      System.out.println("Error occurred: " + e.getMessage());
      System.exit(1);
    }

/*    // connect to google service with credential
    GoogleService googleService = new SpreadsheetService(APPLICATION_NAME);
    googleService.setOAuth2Credentials(credential);
    String sheetTitle = "My_testSheet";
    Spreadsheet spreadsheet = new Spreadsheet()
        .setProperties(new SpreadsheetProperties()
            .setTitle(sheetTitle));
    spreadsheet = service.spreadsheets().create(spreadsheet)
        .setFields("spreadsheetId")
        .execute();
    System.out.println("Spreadsheet ID: " + spreadsheet.getSpreadsheetId());*/
  }
}
