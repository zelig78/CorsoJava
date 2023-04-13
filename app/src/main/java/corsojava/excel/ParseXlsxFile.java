package corsojava.excel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.format.CellDateFormatter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ParseXlsxFile {
    static Logger logger = LogManager.getLogger(ParseXlsxFile.class);

    public static LinkedHashMap<String, Record> readXlsx(String inputFile) {
        LinkedHashMap<String, Record> mapFile = new LinkedHashMap<>();

        try {
            FileInputStream file = new FileInputStream(inputFile);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            boolean skipFirstLine = true;

            for (Row row : sheet) {
                Record record;

                if (!skipFirstLine) {
                    record = readCellsRow(row);
                    mapFile.put(record.getTprNumPrev() + ":" + record.getVeiTarga(), record);
                }
                skipFirstLine = false;
            }
            file.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return mapFile;
    }

    public static void writeXlsx(LinkedHashMap<String, Record> mapFileInput, String fileName){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Data");
        CellStyle cellStyle = getCellStyle(workbook);
        CellStyle fontBoldStyle = getFontBoldStyle(workbook);

        int rownum = 0;
        Header header = new Header("tprNumPrev","veiTarga","tprTotImp","tprIDOff","offRASCL","offCATM1","staDescr","tprDtPrev","fltDescrizione","SM","newStato");
        Row headerRow = sheet.createRow(rownum++);
        createHeader(headerRow, header, fontBoldStyle);

        for (Map.Entry<String, Record> entry : mapFileInput.entrySet()) {
            Row row = sheet.createRow(rownum++);
            Record record = entry.getValue();

            createRow(row, record, workbook);
        }

        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, 10));

        for (int column = 0;column <= 10;column++) {
            sheet.autoSizeColumn(column);
        }

        try {
            FileOutputStream out = new FileOutputStream(fileName);
            workbook.write(out);
            out.close();
            logger.info(fileName);
        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    private static Record readCellsRow(Row row) {
        Record record = new Record();

        Iterator<Cell> cellIterator = row.cellIterator();

        int cellCount = 1;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();

            if (cellCount == 1) {
                record.setTprNumPrev(getCellValue(cell));
            } else if (cellCount == 2) {
                record.setVeiTarga(getCellValue(cell));
            } else if (cellCount == 3) {
                record.setTprTotImp(getCellValue(cell));
            } else if (cellCount == 4) {
                record.setTprIDOff(getCellValue(cell));
            } else if (cellCount == 5) {
                record.setOffRASCL(getCellValue(cell));
            } else if (cellCount == 6) {
                record.setOffCATM1(getCellValue(cell));
            } else if (cellCount == 7) {
                record.setStaDescr(getCellValue(cell));
            } else if (cellCount == 8) {
                record.setTprDtPrev(cell);
            } else if (cellCount == 9) {
                record.setFltDescrizione(getCellValue(cell));
            } else if (cellCount == 10) {
                record.setSM(getCellValue(cell));
            } else if (cellCount == 11) {
                record.setNewState(getCellValue(cell));
            }

            cellCount++;
        }

        return record;
    }

    private static void createRow(Row row, Record record, Workbook workbook) {
        int cellnum = 0;

        Cell cell1 = row.createCell(cellnum++);
        cell1.setCellValue(record.getTprNumPrev());

        Cell cell2 = row.createCell(cellnum++);
        cell2.setCellValue(record.getVeiTarga());

        Cell cell3 = row.createCell(cellnum++);
        cell3.setCellValue(record.getTprTotImp());

        Cell cell4 = row.createCell(cellnum++);
        cell4.setCellValue(record.getTprIDOff());

        Cell cell5 = row.createCell(cellnum++);
        cell5.setCellValue(record.getOffRASCL());

        Cell cell6 = row.createCell(cellnum++);
        cell6.setCellValue(record.getOffCATM1());

        Cell cell7 = row.createCell(cellnum++);
        cell7.setCellValue(record.getStaDescr());

        Cell cell8 = row.createCell(cellnum++);
        CreationHelper creationHelper = workbook.getCreationHelper();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        cell8.setCellStyle(cellStyle);
        cell8.setCellValue(getDateFromStringCell(record));

        Cell cell9 = row.createCell(cellnum++);
        cell9.setCellValue(record.getFltDescrizione());

        Cell cell10 = row.createCell(cellnum++);
        cell10.setCellValue(record.getSM());

        Cell cell11 = row.createCell(cellnum++);
        cell11.setCellValue(record.getNewState());
    }

    private static void createHeader(Row row, Header record, CellStyle cellStyle) {
        int cellnum = 0;

        Cell cell1 = row.createCell(cellnum++);
        cell1.setCellValue(record.getTprNumPrev());
        cell1.setCellStyle(cellStyle);

        Cell cell2 = row.createCell(cellnum++);
        cell2.setCellValue(record.getVeiTarga());
        cell2.setCellStyle(cellStyle);

        Cell cell3 = row.createCell(cellnum++);
        cell3.setCellValue(record.getTprTotImp());
        cell3.setCellStyle(cellStyle);

        Cell cell4 = row.createCell(cellnum++);
        cell4.setCellValue(record.getTprIDOff());
        cell4.setCellStyle(cellStyle);

        Cell cell5 = row.createCell(cellnum++);
        cell5.setCellValue(record.getOffRASCL());
        cell5.setCellStyle(cellStyle);

        Cell cell6 = row.createCell(cellnum++);
        cell6.setCellValue(record.getOffCATM1());
        cell6.setCellStyle(cellStyle);

        Cell cell7 = row.createCell(cellnum++);
        cell7.setCellValue(record.getStaDescr());
        cell7.setCellStyle(cellStyle);

        Cell cell8 = row.createCell(cellnum++);
        cell8.setCellValue(record.getTprDtPrev());
        cell8.setCellStyle(cellStyle);

        Cell cell9 = row.createCell(cellnum++);
        cell9.setCellValue(record.getFltDescrizione());
        cell9.setCellStyle(cellStyle);

        Cell cell10 = row.createCell(cellnum++);
        cell10.setCellValue(record.getSM());
        cell10.setCellStyle(cellStyle);

        Cell cell11 = row.createCell(cellnum++);
        cell11.setCellValue(record.getNewState());
        cell11.setCellStyle(cellStyle);
    }

    private static String getCellValue(Cell cell) {
        String cellValue="";
        //Check the cell type and format accordingly
        switch (cell.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)){
                    Date date = cell.getDateCellValue();
                    String df = "dd/MM/yyyy"; //cell.getCellStyle().getDataFormatString();
                    cellValue = new CellDateFormatter(df).format(date);
                } else {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case FORMULA:
                cellValue = cell.getStringCellValue();
                break;
        }

        return cellValue;
    }

    private static Date getDateFromStringCell(Record record){
        switch (record.getTprDtPrev().getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(record.getTprDtPrev())){
                    return record.getTprDtPrev().getDateCellValue();
                } else {
                    logger.warn("Data non disponibile - Record: " + record);
                    return new Date();
                }
            case STRING:
                String dateValue = record.getTprDtPrev().getStringCellValue();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date actualDate = null;
                try {
                    actualDate = simpleDateFormat.parse(dateValue);
                } catch (ParseException e) {
                    logger.error(e.getMessage());
                }
                return actualDate;
        }
        logger.warn("Data non disponibile - Record: " + record);
        return new Date();
    }

    private static CellStyle getCellStyle(Workbook workbook) {
        CreationHelper creationHelper = workbook.getCreationHelper();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));

        return cellStyle;
    }

    private static CellStyle getFontBoldStyle(Workbook workbook) {
        XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontHeight(12);
        font.setBold(true);
        font.setItalic(false);

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);

        return cellStyle;
    }
}
