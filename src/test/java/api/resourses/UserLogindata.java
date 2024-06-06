package api.resourses;

import java.io.IOException;
import api.pojo.UserLoginPojo;
import api.utils.ExcelReader;

public class UserLogindata {
	
ExcelReader er = new ExcelReader();

 public UserLoginPojo dataBuild() throws IOException {
	
	UserLoginPojo dl = new UserLoginPojo();
	
	dl.setPassword(er.getCellData("UserLogin", 1, 0));
	dl.setUserLoginEmailId(er.getCellData("UserLogin", 1, 1));
	
	return dl;
}
 
 public UserLoginPojo invaliddataBuild() throws IOException {
		
		UserLoginPojo dl = new UserLoginPojo();
		
		dl.setPassword(er.getCellData("UserLogin", 1, 0));
		dl.setUserLoginEmailId(er.getCellData("UserLogin", 1, 3));
		
		return dl;
	}
	
	

}
