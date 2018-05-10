import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestingOpenCSV {

    public static void main(String args[]) throws IOException {
        String path = System.getProperty("user.dir") + "\\DealPricingCSVupload-New_Seema.csv";
        Reader reader = Files.newBufferedReader(Paths.get(path));
        CSVReader csvReader = new CSVReader(reader);
        List<String[]> csvBody = csvReader.readAll();
        csvBody.get(15)[1] = "33,894.23";
        csvReader.close();
        CSVWriter writer = new CSVWriter(new FileWriter(path));
        writer.writeAll(csvBody);
        writer.close();
        String name = "€8.192,71";
        name = name.replace("€","");
        name = name.replace(".","");
        name = name.replace(",",".");
        System.out.println(name);
    }
}
