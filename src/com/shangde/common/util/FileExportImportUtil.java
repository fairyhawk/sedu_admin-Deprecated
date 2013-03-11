package com.shangde.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
      
    public class FileExportImportUtil {  
      
    	 InputStream  os;  
         List<List<String>> list=new ArrayList<List<String>>();
        
        //创建工作本  
        public  HSSFWorkbook demoWorkBook = new HSSFWorkbook();  
        //创建表  
        public  HSSFSheet demoSheet = demoWorkBook.createSheet("Sheet1");  
     
        /** 
         * 创建行 
         * @param cells 
         * @param rowIndex 
         */  
        public  void createTableRow(List<String> cells,short rowIndex)  
        {  
            //创建第rowIndex行  
            HSSFRow row = demoSheet.createRow((short) rowIndex);  
            for(short i = 0;i < cells.size();i++)  
            {  
                //创建第i个单元格  
                HSSFCell cell = row.createCell((short) i);  
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);  
                cell.setCellValue(cells.get(i));  
            }  
        }  
          
        /** 
         * 创建整个Excel表 
         * @throws SQLException  
         * 
         */  
        public  void createExcelSheeet() throws SQLException  
        {   
            for(int i=0;i<list.size();i++)
            {
             createTableRow((List<String>)list.get(i),(short)i);  
            }  
        }  
        /** 
         * 导出表格 
         * @param sheet 
         * @param os 
         * @throws IOException 
         */  
        public InputStream exportExcel(HSSFSheet sheet) throws IOException  
        {  
            sheet.setGridsPrinted(true);  
            HSSFFooter footer = sheet.getFooter();  
            footer.setRight("Page " + HSSFFooter.page() + " of " +  
            HSSFFooter.numPages());  	 
          	 ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			 try 
			 {              
				 demoWorkBook.write(baos);          
			 } 
			 catch (IOException e) 
			 {
				 e.printStackTrace();          
			 }          
			 byte[] ba = baos.toByteArray();          
			os= new ByteArrayInputStream(ba);
			return os;
        }  
          
        
        public  InputStream export(List<List<String>> zlist) {  
        	 InputStream myos=null;
                try {  
                	list=zlist;               
                   createExcelSheeet();  
                  myos= exportExcel(demoSheet);  
                    return myos;
                   } catch (Exception e) {  
                    JOptionPane.showMessageDialog(null, "表格导出出错，错误信息 ："+e+"\n错误原因可能是表格已经打开。");  
                    e.printStackTrace(); 
                    return null;
                } finally {  
                    try {  
                     os.close(); 
                     if(myos!=null)myos.close();
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
               
        }  
    }  

