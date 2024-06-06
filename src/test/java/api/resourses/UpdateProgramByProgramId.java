package api.resourses;

import java.io.IOException;

import api.pojo.CreateProgramPojo;
import api.utils.ExcelReader;

public class UpdateProgramByProgramId {
	
	ExcelReader er = new ExcelReader();
		
		 public CreateProgramPojo dataBuild() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 1, 3));
			cp.setProgramName(er.getCellData("programController", 1, 4));
			cp.setProgramStatus(er.getCellData("programController", 1, 5));
			
			return cp;
		}
		 
		 public CreateProgramPojo dataBuild1() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 2, 3));
			cp.setProgramName(er.getCellData("programController", 2, 4));
			cp.setProgramStatus(er.getCellData("programController", 2, 5));
			
			return cp;
		}
		 
		 public CreateProgramPojo dataBuild2() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 3, 3));
			cp.setProgramName(er.getCellData("programController", 3, 4));
			cp.setProgramStatus(er.getCellData("programController", 3, 5));
			
			return cp;
		}
		 
		 public CreateProgramPojo dataBuild3() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 4, 3));
			cp.setProgramName(er.getCellData("programController", 4, 4));
			cp.setProgramStatus(er.getCellData("programController", 4, 5));
			
			return cp;
		}
		 
		 public CreateProgramPojo dataBuild4() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 5, 3));
			cp.setProgramName(er.getCellData("programController", 5, 4));
			cp.setProgramStatus(er.getCellData("programController", 5, 5));
			
			return cp;
		}
		 
		 public CreateProgramPojo dataBuild5() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 6, 3));
			cp.setProgramName(er.getCellData("programController", 6, 4));
			cp.setProgramStatus(er.getCellData("programController", 6, 5));
			
			return cp;
		}
		 
		 public CreateProgramPojo dataBuild6() throws IOException {
				
			 CreateProgramPojo cp = new CreateProgramPojo();
			 
			cp.setProgramDescription(er.getCellData("programController", 7, 3));
			cp.setProgramName(er.getCellData("programController", 7, 4));
			cp.setProgramStatus(er.getCellData("programController", 7, 5));
			
			return cp;
		}

}
