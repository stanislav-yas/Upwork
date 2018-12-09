package google_sheet;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GoogleSheetTest {

  private static final String APPLICATION_NAME = "Google Sheets API Example";
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  private static final String TOKENS_DIRECTORY_PATH = "tokens";
  private static  Credential credential;
  private static NetHttpTransport transport ;
  private static final String SPREADSHEET_ID = "1KYwqJrJev8hvxNrpWYCd9hcYz_rB7WAmIIZsJVttZgE";

  /**
   * Global instance of the scopes required by this quickstart.
   * If modifying these scopes, delete your previously saved tokens/ folder.
   */
  private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
  private static final String CREDENTIALS_FILE_PATH = "resources/credentials.json";

  private static Credential getServiceCredential() throws IOException, GeneralSecurityException {
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

  private static NetHttpTransport getTransport() throws IOException, GeneralSecurityException{
    if(transport == null){
      transport = GoogleNetHttpTransport.newTrustedTransport();
    }
    return transport;
  }

  @Test
  public void readingSheetTest(){
    try{
      if(credential == null){
        credential = getServiceCredential();
      }
      Sheets sheetService = new Sheets.Builder(getTransport(), JSON_FACTORY, credential)
          .setApplicationName(APPLICATION_NAME)
          .build();
      String range = "Sheet1!A1:D4";
      ValueRange result = sheetService.spreadsheets().values().get(SPREADSHEET_ID, range).execute();
      int numRows = result.getValues() != null ? result.getValues().size() : 0;
      System.out.printf("%d rows retrieved.", numRows);
    }catch (Exception e ) {
      System.out.println("Reading Sheet error: " + e.getMessage());
      System.exit(1);
    }
  }

  @Test
  public void updatingSheetSingleRangeTest(){
    List<List<Object>> values = Arrays.asList(
        Arrays.asList("Ячейка A10","Ячейка B10","Ячейка C10","Ячейка D10"
            // Cell values ...
        ),
        Arrays.asList("Ячейка A11","Ячейка B11","Ячейка C11","Ячейка D11")
        // Additional rows ...
    );
    try{
      if(credential == null){
        credential = getServiceCredential();
      }
      Sheets sheetService = new Sheets.Builder(getTransport(), JSON_FACTORY, credential)
          .setApplicationName(APPLICATION_NAME)
          .build();
      ValueRange body = new ValueRange()
          .setValues(values);
      String range = "Sheet1!A10:D11";
      UpdateValuesResponse result =
          sheetService.spreadsheets().values().update(SPREADSHEET_ID, range, body)
              .setValueInputOption("RAW")  // RAW | USER_ENTERED
              .execute();
      System.out.printf("%d cells updated.", result.getUpdatedCells());
      // appending values
      range = "Sheet1!A1:D1";
      values = Arrays.asList(
          Arrays.asList("Append A1","Append B1","Append C1","Append D1"
              // Cell values ...
          ));
      body = new ValueRange()
          .setValues(values);
      AppendValuesResponse response =
          sheetService.spreadsheets().values().append(SPREADSHEET_ID, range, body)
              .setValueInputOption("RAW") // RAW | USER_ENTERED
              .setInsertDataOption("INSERT_ROWS") // INSERT_ROWS | OVERWRITE
              .execute();
      System.out.printf("%d cells updated.", response.getUpdates().getUpdatedCells());
    }catch (Exception e ) {
      System.out.println("Writing SheetSingleRange error: " + e.getMessage());
      System.exit(1);
    }
  }



  @Test
  public void createSpreadsheetTest(){
    try{
      if(credential == null){
        credential = getServiceCredential();
      }
      Sheets sheetService = new Sheets.Builder(getTransport(), JSON_FACTORY, credential)
          .setApplicationName(APPLICATION_NAME)
          .build();
      String sheetTitle = "My_testSheet";
      Spreadsheet spreadsheet = new Spreadsheet()
          .setProperties(new SpreadsheetProperties()
              .setTitle(sheetTitle));
      spreadsheet = sheetService.spreadsheets().create(spreadsheet)
          .setFields("spreadsheetId")
          .execute();
      System.out.println("Spreadsheet ID: " + spreadsheet.getSpreadsheetId());
    }catch (Exception e ){
      System.out.println("Creating Sheet error: " + e.getMessage());
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
