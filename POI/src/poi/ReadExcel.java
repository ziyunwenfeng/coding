package poi;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcel {

	public static void main(String[] args) {
		try {
			FileInputStream inputStream = new FileInputStream("C:/Users/lenovo/Desktop/poi-bin-3.17-20170915/test.xlsx");
			List<List> result = new LinkedList<List>();
			Map<String,Set<String>> map = new HashMap<>();
			
			try {
				Workbook workbook = WorkbookFactory.create(inputStream);
				Sheet sheet = workbook.getSheetAt(0);
				int rowNumber = sheet.getLastRowNum()+1;
				System.out.println(rowNumber);
				for(int i=0;i<rowNumber;i++){
					Row row = sheet.getRow(i);
					if(!map.keySet().contains(row.getCell(0).toString())){
						Set<String> set = new HashSet<>();
						set.add(row.getCell(1).toString());
						map.put(row.getCell(0).toString(),set);
					}else{
						Set<String> set = map.get(row.getCell(0).toString());
						set.add(row.getCell(1).toString());
					}
				}
				System.out.println(map);
				Set<String> keySet = map.keySet();
				Iterator<String> it = keySet.iterator();
				while(it.hasNext()){
					Set<String> valueSet  = map.get(it.next());
//					if(表存在){
//						
//					}
					
//					表不存在
					
					Iterator<String> iterator = keySet.iterator();
					
				}
				  
			} catch (EncryptedDocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
