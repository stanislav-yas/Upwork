package google_sheet;

import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import org.junit.Test;

public class GoogleSheetTest {

  @Test
  public void testSheet(){
    String sheetTitle = "My_testSheet";
    Spreadsheet spreadsheet = new Spreadsheet()
        .setProperties(new SpreadsheetProperties()
            .setTitle(sheetTitle));
    spreadsheet = service.spreadsheets().create(spreadsheet)
        .setFields("spreadsheetId")
        .execute();
    System.out.println("Spreadsheet ID: " + spreadsheet.getSpreadsheetId());
  }
}
