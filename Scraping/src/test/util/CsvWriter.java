package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CsvWriter extends BufferedWriter{

  private BufferedWriter writer;
  private Character separator;

  public CsvWriter(String filename)throws IOException{
    this(filename, ';');
  }

  public CsvWriter(String filename, Character separator) throws IOException{
    super(new FileWriter(filename));
    this.separator = separator;
  }

  public CsvWriter addValue(String value){
    try{
      write(value);
      write(separator);
    }catch (IOException e){
      System.out.println(e.getMessage());
    }
    return this;
  }

  public CsvWriter nextLine(){
    try{
      newLine();
      flush();
    }catch (IOException e){
      System.out.println(e.getMessage());
    }
    return this;
  }
}
