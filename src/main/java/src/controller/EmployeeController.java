
package src.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

	@RequestMapping(value = "/regestrationSuccess", method = RequestMethod.POST)
	public ModelAndView saveEmployeeDetails(@RequestParam("name") String name, 
											@RequestParam("defectid") String defectid,
											@RequestParam("train") String train,
											@RequestParam("squad") String squad, 
											@RequestParam("comments") String comments,
											@RequestParam("supportedby") String supportedby) throws IOException {

		ModelAndView modelandview = new ModelAndView("regestrationSuccess");

		File file = new File("C:\\DEVDUTT JADEJA\\Projects\\data\\EmployeeNew.xls");
		
		if(!file.exists()) {
			// what if file does not exist at given path
		}
		
		FileInputStream inputStream = new FileInputStream(file); // Note : it wont create new file, this will give FileNotFoundException if file is not present at given path
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
		
		
		String todaydate = LocalDate.now().toString();
		String sheetname = "Status "+ todaydate;
		HSSFSheet sheet = null;
		
		if( hssfWorkbook.getSheet(sheetname) == null ) {
			sheet = hssfWorkbook.createSheet(sheetname);
			
			HSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue(name);
			row.createCell(1).setCellValue(defectid);
			row.createCell(2).setCellValue(train);
			row.createCell(3).setCellValue(squad);
			row.createCell(4).setCellValue(comments);
			row.createCell(5).setCellValue(supportedby);

			// formating excel imporve the format of data
		    
			
		}else {
			
			sheet = hssfWorkbook.getSheet(sheetname);
			
			int lastrow = sheet.getLastRowNum();
			lastrow++;
			HSSFRow newrow = sheet.createRow(lastrow);
			
			newrow.createCell(0).setCellValue(name);
			newrow.createCell(1).setCellValue(defectid);
			newrow.createCell(2).setCellValue(train);
			newrow.createCell(3).setCellValue(squad);
			newrow.createCell(4).setCellValue(comments);
			newrow.createCell(5).setCellValue(supportedby);
			
		}
		

		// close input stream before opening output stream
		inputStream.close();

		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream(new File("C:\\DEVDUTT JADEJA\\Projects\\data\\EmployeeNew.xls"));
		hssfWorkbook.write(out);
		hssfWorkbook.close();
		out.close();
		System.out.println("Writesheet.xlsx written successfully");

		return modelandview;
	}
	
	
	@ExceptionHandler(FileNotFoundException.class)
	public ModelAndView handleFileNotFoundException(HttpServletRequest request, Exception ex){
		
		
		ModelAndView modelAndView = new ModelAndView("error");
	    modelAndView.addObject("exception", ex);
	    modelAndView.addObject("url", request.getRequestURL());
	    
	    return modelAndView;
	}
	
	
	@RequestMapping(value = "/download")
    public void downloadResource( HttpServletRequest request,HttpServletResponse response){
    	
        //If user is not authorized - he should be thrown out from here itself
         
        //Authorized user will download the file
    	
        String path = "C:\\DEVDUTT JADEJA\\Projects\\data"; 
        String fileName = "EmployeeNew.xls";
        
        Path file = Paths.get(path, fileName);
        if (Files.exists(file))
        {
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
	

}
