package api.resourses;
import java.io.IOException;


import api.pojo.CreateProgramPojo;
import api.utils.ExcelReader;

public class CreateProgramData {
	
	ExcelReader er = new ExcelReader();


    public CreateProgramPojo dataBuild() throws IOException {
           
            CreateProgramPojo cp = new CreateProgramPojo();
            
           cp.setProgramDescription(er.getCellData("programController", 1, 0));
           cp.setProgramName(er.getCellData("programController", 1, 1));
           cp.setProgramStatus(er.getCellData("programController", 1, 2));
           
           return cp;
   }

    public CreateProgramPojo dataBuild1() throws IOException {
		
		 CreateProgramPojo cp = new CreateProgramPojo();
		 
		cp.setProgramDescription(er.getCellData("programController", 2, 0));
		cp.setProgramName(er.getCellData("programController", 2, 1));
		cp.setProgramStatus(er.getCellData("programController", 2, 2));
		
		return cp;
	}
	 
	 public CreateProgramPojo dataBuild2() throws IOException {
			
		 CreateProgramPojo cp = new CreateProgramPojo();
		 
		cp.setProgramDescription(er.getCellData("programController", 3, 0));
		cp.setProgramName(er.getCellData("programController", 3, 1));
		cp.setProgramStatus(er.getCellData("programController", 3, 2));
		
		return cp;
	}
	 
	 public CreateProgramPojo dataBuild3() throws IOException {
			
		 CreateProgramPojo cp = new CreateProgramPojo();
		 
		cp.setProgramDescription(er.getCellData("programController", 4, 0));
		cp.setProgramName(er.getCellData("programController", 4, 1));
		cp.setProgramStatus(er.getCellData("programController", 4, 2));
		
		return cp;
	}
	 
	 public CreateProgramPojo dataBuild4() throws IOException {
			
		 CreateProgramPojo cp = new CreateProgramPojo();
		 
		cp.setProgramDescription(er.getCellData("programController", 5, 0));
		cp.setProgramName(er.getCellData("programController", 5, 1));
		cp.setProgramStatus(er.getCellData("programController", 5, 2));
		
		return cp;
	}
	 
	 public CreateProgramPojo dataBuild5() throws IOException {
			
		 CreateProgramPojo cp = new CreateProgramPojo();
		 
		cp.setProgramDescription(er.getCellData("programController", 6, 0));
		cp.setProgramName(er.getCellData("programController", 6, 1));
		cp.setProgramStatus(er.getCellData("programController", 6, 2));
		
		return cp;
	}
	 
	 public CreateProgramPojo dataBuild6() throws IOException {
			
		 CreateProgramPojo cp = new CreateProgramPojo();
		 
		cp.setProgramDescription(er.getCellData("programController", 7, 0));
		cp.setProgramName(er.getCellData("programController", 7, 1));
		cp.setProgramStatus(er.getCellData("programController", 7, 2));
		
		return cp;
	}
	 
	 public CreateProgramPojo dataBuild7() throws IOException {
			
		 CreateProgramPojo cp = new CreateProgramPojo();
		 
		cp.setProgramDescription(er.getCellData("programController", 8, 0));
		cp.setProgramName(er.getCellData("programController", 8, 1));
		cp.setProgramStatus(er.getCellData("programController", 8, 2));
		
		return cp;
	}
	 public CreateProgramPojo dataBuild8_active() throws IOException {
			
		 CreateProgramPojo cp = new CreateProgramPojo();
		 
		cp.setProgramDescription(er.getCellData("programController", 9, 0));
		cp.setProgramName(er.getCellData("programController", 9, 1));
		cp.setProgramStatus(er.getCellData("programController", 9, 2));
		
		return cp;
	}
	 
	 public CreateProgramPojo dataBuild9_inactive() throws IOException {
			
		 CreateProgramPojo cp = new CreateProgramPojo();
		 
		cp.setProgramDescription(er.getCellData("programController", 10, 0));
		cp.setProgramName(er.getCellData("programController", 10, 1));
		cp.setProgramStatus(er.getCellData("programController", 10, 2));
		
		return cp;
	}
	 
}
