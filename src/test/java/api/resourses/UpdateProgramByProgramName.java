package api.resourses;

import java.io.IOException;

import api.pojo.CreateProgramPojo;
import api.utils.ExcelReader;

public class UpdateProgramByProgramName {
	
		ExcelReader er = new ExcelReader();
		
		 public CreateProgramPojo dataBuild() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 1, 6));
			cp.setProgramName(er.getCellData("programController", 1, 7));
			cp.setProgramStatus(er.getCellData("programController", 1, 8));
			
			return cp;
		}
		 
		 public CreateProgramPojo dataBuild1() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 2, 6));
			cp.setProgramName(er.getCellData("programController", 2, 7));
			cp.setProgramStatus(er.getCellData("programController", 2, 8));
			
			return cp;
		}
		 
		 public CreateProgramPojo dataBuild2() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 3, 6));
			cp.setProgramName(er.getCellData("programController", 3, 7));
			cp.setProgramStatus(er.getCellData("programController", 3, 8));
			
			return cp;
		}
		 
		 public CreateProgramPojo dataBuild3() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 4, 6));
			cp.setProgramName(er.getCellData("programController", 4, 7));
			cp.setProgramStatus(er.getCellData("programController", 4, 8));
			
			return cp;
		}
		 
		 public CreateProgramPojo dataBuild4() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 5, 6));
			cp.setProgramName(er.getCellData("programController", 5, 7));
			cp.setProgramStatus(er.getCellData("programController", 5, 8));
			
			return cp;
		}
		 
		 public CreateProgramPojo dataBuild5() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 6, 6));
			cp.setProgramName(er.getCellData("programController", 6, 7));
			cp.setProgramStatus(er.getCellData("programController", 6, 8));
			
			return cp;
		}
		 
		 public CreateProgramPojo dataBuild6() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 7, 6));
			cp.setProgramName(er.getCellData("programController", 7, 7));
			cp.setProgramStatus(er.getCellData("programController", 7, 8));
			
			return cp;
		}

}
