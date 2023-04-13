package corsojava.excel;

import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {
        String fileName = "C:\\Davide\\Progetti\\Java\\CorsoJava\\app\\src\\main\\resources\\test.xlsx";
        System.out.println(fileName);

        LinkedHashMap<String, Record> mapRecords = ParseXlsxFile.readXlsx(fileName);

        ParseXlsxFile.writeXlsx(mapRecords, "C:\\Davide\\Progetti\\Java\\CorsoJava\\app\\src\\main\\resources\\testOut.xlsx");
    }
}
