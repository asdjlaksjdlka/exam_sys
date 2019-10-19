package com.qf.examsys.utils;

import com.qf.examsys.entity.Brief;
import com.qf.examsys.entity.Choose;
import com.qf.examsys.entity.Judge;
import com.qf.examsys.entity.Subject;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by sang on 2018/1/16.
 */
public class PoiUtils {

    public static ResponseEntity<byte[]> exportEmp2Excel(List<Choose> chooses) {
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("试题信息");
            //3.2设置文档管理员
            dsi.setManager("yqz");
            //3.3设置组织机构
            dsi.setCompany("XXX集团");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("试题信息表");
            //4.2.设置文档标题
            si.setTitle("试题信息");
            //4.3 设置文档作者
            si.setAuthor("XXX集团");
            //4.4设置文档备注
            si.setComments("备注信息暂无");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("XXX集团试题信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 5 * 256);
            sheet.setColumnWidth(1, 12 * 256);
            sheet.setColumnWidth(2, 10 * 256);
            sheet.setColumnWidth(3, 5 * 256);
            sheet.setColumnWidth(4, 12 * 256);
            sheet.setColumnWidth(5, 20 * 256);
            sheet.setColumnWidth(6, 10 * 256);
            sheet.setColumnWidth(7, 10 * 256);
            sheet.setColumnWidth(8, 16 * 256);
            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("编号");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("题目");
            cell1.setCellStyle(headerStyle);
            HSSFCell cell2 = headerRow.createCell(2);
            cell2.setCellValue("选项A");
            cell2.setCellStyle(headerStyle);
            HSSFCell cell3 = headerRow.createCell(3);
            cell3.setCellValue("选项B");
            cell3.setCellStyle(headerStyle);
            HSSFCell cell4 = headerRow.createCell(4);
            cell4.setCellValue("选项C");
            cell4.setCellStyle(headerStyle);
            HSSFCell cell5 = headerRow.createCell(5);
            cell5.setCellValue("选项D");
            cell5.setCellStyle(headerStyle);
            HSSFCell cell6 = headerRow.createCell(6);
            cell6.setCellValue("答案");
            cell6.setCellStyle(headerStyle);
            HSSFCell cell7 = headerRow.createCell(7);
            cell7.setCellValue("科目");
            cell7.setCellStyle(headerStyle);
            HSSFCell cell8 = headerRow.createCell(8);
            cell8.setCellValue("分值");
            cell8.setCellStyle(headerStyle);

            //6.装数据
            for (int i = 0; i < chooses.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                Choose choose = chooses.get(i);
                row.createCell(0).setCellValue(choose.getCid());
                row.createCell(1).setCellValue(choose.getcTitle());
                row.createCell(2).setCellValue(choose.getcOptionA());
                row.createCell(3).setCellValue(choose.getcOptionB());
                row.createCell(4).setCellValue(choose.getcOptionC());
                row.createCell(5).setCellValue(choose.getcOptionD());
                row.createCell(6).setCellValue(choose.getcAnswer());
                row.createCell(7).setCellValue(choose.getSubject().getsName());
                row.createCell(8).setCellValue(choose.getcScore());

            }
            headers = new HttpHeaders();

            /**
             * 获取系统时间随机生成文件名
             */
            String format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
            String filename = new String("选择题试题表");
            String xls = new String(".xls");
            filename = filename + format + xls;


            headers.setContentDispositionFormData("attachment",
                    new String(filename.getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }


    /**
     * 上传已经成功
     * @param file
     * @param subjects
     * @return
     */
    public static List<Choose> importEmp2List(MultipartFile file,List<Subject> subjects) {
//        System.out.println("subjects"+subjects);
        List<Choose> chooses = new ArrayList<>();
        try {
            HSSFWorkbook workbook =
                    new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                Choose choose;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//没数据
                    }
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    choose = new Choose();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellTypeEnum()) {
                            case STRING: {
                                String cellValue = cell.getStringCellValue();
                                if (cellValue == null) {
                                    cellValue = "";
                                }
                                switch (k) {
                                    case 0:
                                        break;
                                    case 1:
                                        choose.setcTitle(cellValue);
                                        break;
                                    case 2:
                                        choose.setcOptionA(cellValue);
                                        break;
                                    case 3:
                                        choose.setcOptionB(cellValue);
                                        break;
                                    case 4:
                                        choose.setcOptionC(cellValue);
                                        break;
                                    case 5:
                                        choose.setcOptionD(cellValue);
                                        break;
                                    case 6:
                                        choose.setcAnswer(cellValue);
                                        break;
                                    case 7:
//                                        System.out.println(cellValue+"===++");

                                        int subjectIndex = subjects.indexOf(new Subject(cellValue));
                                       /* System.out.println(subjectIndex);
                                        System.out.println(new Subject(cellValue));*/
                                        /*indexOf是返回列表中首次出现指定元素的索引*/
                                        /*System.out.println("哈哈"+subjects.get(subjectIndex).getSid());
                                        System.out.println("呵呵"+subjects.get(subjectIndex));*/
                                        choose.setSid(subjects.get(subjectIndex).getSid());
                                        break;
                                }
                            }
                            break;
                            default: {
                                switch (k) {

                                    case 8:
                                        choose.setcScore((int) cell.getNumericCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    chooses.add(choose);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return chooses;
    }

    /**
     * 简答题导出和导入
     */

    public static ResponseEntity<byte[]> exportBriefExcel(List<Brief> briefs) {
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("试题信息");
            //3.2设置文档管理员
            dsi.setManager("yqz");
            //3.3设置组织机构
            dsi.setCompany("XXX集团");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("试题信息表");
            //4.2.设置文档标题
            si.setTitle("试题信息");
            //4.3 设置文档作者
            si.setAuthor("XXX集团");
            //4.4设置文档备注
            si.setComments("备注信息暂无");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("XXX集团试题信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 5 * 256);
            sheet.setColumnWidth(1, 12 * 256);
            sheet.setColumnWidth(2, 10 * 256);
            sheet.setColumnWidth(3, 5 * 256);
            sheet.setColumnWidth(4, 12 * 256);

            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("编号");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("题目");
            cell1.setCellStyle(headerStyle);
            HSSFCell cell6 = headerRow.createCell(2);
            cell6.setCellValue("答案");
            cell6.setCellStyle(headerStyle);
            HSSFCell cell7 = headerRow.createCell(3);
            cell7.setCellValue("科目");
            cell7.setCellStyle(headerStyle);
            HSSFCell cell8 = headerRow.createCell(4);
            cell8.setCellValue("分值");
            cell8.setCellStyle(headerStyle);

            //6.装数据
            for (int i = 0; i < briefs.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                Brief brief = briefs.get(i);
                row.createCell(0).setCellValue(brief.getBid());
                row.createCell(1).setCellValue(brief.getbTitle());
                row.createCell(2).setCellValue(brief.getbAnswer());
                row.createCell(3).setCellValue(brief.getSubject().getsName());
                row.createCell(4).setCellValue(brief.getbScore());

            }
            headers = new HttpHeaders();

            /**
             * 获取系统时间随机生成文件名
             */
            String format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
            String filename = new String("简答题试题表");
            String xls = new String(".xls");
            filename = filename + format + xls;


            headers.setContentDispositionFormData("attachment",
                    new String(filename.getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }


    public static List<Brief> importBriefList(MultipartFile file,List<Subject> subjects) {
//        System.out.println("subjects"+subjects);
        List<Brief> briefs = new ArrayList<>();
        try {
            HSSFWorkbook workbook =
                    new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                Brief brief;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//没数据
                    }
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    brief = new Brief();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellTypeEnum()) {
                            case STRING: {
                                String cellValue = cell.getStringCellValue();
                                if (cellValue == null) {
                                    cellValue = "";
                                }
                                switch (k) {
                                    case 0:
                                        break;
                                    case 1:
                                        brief.setbTitle(cellValue);
                                        break;
                                    case 2:
                                        brief.setbAnswer(cellValue);
                                        break;
                                    case 3:
//                                        System.out.println(cellValue+"===++");

                                        int subjectIndex = subjects.indexOf(new Subject(cellValue));
                                       /* System.out.println(subjectIndex);
                                        System.out.println(new Subject(cellValue));*/
                                        /*indexOf是返回列表中首次出现指定元素的索引*/
                                 /*       System.out.println("哈哈"+subjects.get(subjectIndex).getSid());
                                        System.out.println("呵呵"+subjects.get(subjectIndex));*/
                                        brief.setSid(subjects.get(subjectIndex).getSid());
                                        break;
                                }
                            }
                            break;
                            default: {
                                switch (k) {

                                    case 4:
                                        brief.setbScore((int) cell.getNumericCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    briefs.add(brief);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return briefs;
    }


    /**
     * 判断题导出和导入
     */

    public static ResponseEntity<byte[]> exportJudgeExcel(List<Judge> judges) {
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook = new HSSFWorkbook();
            //2.创建文档摘要
            workbook.createInformationProperties();
            //3.获取文档信息，并配置
            DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
            //3.1文档类别
            dsi.setCategory("试题信息");
            //3.2设置文档管理员
            dsi.setManager("yqz");
            //3.3设置组织机构
            dsi.setCompany("XXX集团");
            //4.获取摘要信息并配置
            SummaryInformation si = workbook.getSummaryInformation();
            //4.1设置文档主题
            si.setSubject("试题信息表");
            //4.2.设置文档标题
            si.setTitle("试题信息");
            //4.3 设置文档作者
            si.setAuthor("XXX集团");
            //4.4设置文档备注
            si.setComments("备注信息暂无");
            //创建Excel表单
            HSSFSheet sheet = workbook.createSheet("XXX集团试题信息表");
            //创建日期显示格式
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0, 5 * 256);
            sheet.setColumnWidth(1, 12 * 256);
            sheet.setColumnWidth(2, 10 * 256);
            sheet.setColumnWidth(3, 5 * 256);
            sheet.setColumnWidth(4, 12 * 256);

            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("编号");
            cell0.setCellStyle(headerStyle);
            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("题目");
            cell1.setCellStyle(headerStyle);
            HSSFCell cell6 = headerRow.createCell(2);
            cell6.setCellValue("答案");
            cell6.setCellStyle(headerStyle);
            HSSFCell cell7 = headerRow.createCell(3);
            cell7.setCellValue("科目");
            cell7.setCellStyle(headerStyle);
            HSSFCell cell8 = headerRow.createCell(4);
            cell8.setCellValue("分值");
            cell8.setCellStyle(headerStyle);

            //6.装数据
            for (int i = 0; i < judges.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                Judge judge = judges.get(i);
                row.createCell(0).setCellValue(judge.getJid());
                row.createCell(1).setCellValue(judge.getjTitle());
                row.createCell(2).setCellValue(judge.getjAnswer());
                row.createCell(3).setCellValue(judge.getSubject().getsName());
                row.createCell(4).setCellValue(judge.getjScore());

            }
            headers = new HttpHeaders();

            /**
             * 获取系统时间随机生成文件名
             */
            String format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
            String filename = new String("判断试题表");
            String xls = new String(".xls");
            filename = filename + format + xls;


            headers.setContentDispositionFormData("attachment",
                    new String(filename.getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }


    public static List<Judge> importJudgeList(MultipartFile file,List<Subject> subjects) {
//        System.out.println("subjects"+subjects);
        List<Judge> judges = new ArrayList<>();
        try {
            HSSFWorkbook workbook =
                    new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                HSSFSheet sheet = workbook.getSheetAt(i);
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                Judge judge;
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    if (j == 0) {
                        continue;//标题行
                    }
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//没数据
                    }
                    int physicalNumberOfCells = row.getPhysicalNumberOfCells();
                    judge = new Judge();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k);
                        switch (cell.getCellTypeEnum()) {
                            case STRING: {
                                String cellValue = cell.getStringCellValue();
                                if (cellValue == null) {
                                    cellValue = "";
                                }
                                switch (k) {
                                    case 0:
                                        break;
                                    case 1:
                                        judge.setjTitle(cellValue);
                                        break;
                                    case 2:
                                        judge.setjAnswer(cellValue);
                                        break;
                                    case 3:
//                                        System.out.println(cellValue+"===++");

                                        int subjectIndex = subjects.indexOf(new Subject(cellValue));
                                       /* System.out.println(subjectIndex);
                                        System.out.println(new Subject(cellValue));*/
                                        /*indexOf是返回列表中首次出现指定元素的索引*/
                                     /*   System.out.println("哈哈"+subjects.get(subjectIndex).getSid());
                                        System.out.println("呵呵"+subjects.get(subjectIndex));*/
                                        judge.setSid(subjects.get(subjectIndex).getSid());
                                        break;
                                }
                            }
                            break;
                            default: {
                                switch (k) {

                                    case 4:
                                        judge.setjScore((int) cell.getNumericCellValue());
                                        break;
                                }
                            }
                            break;
                        }
                    }
                    judges.add(judge);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return judges;
    }
}
