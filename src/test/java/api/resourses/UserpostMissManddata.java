package api.resourses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.pojo.UserMPostLoginPojo;
import api.pojo.UserMPostMainPojo;

import api.pojo.UserMPostRoleMapPojo;
import api.utils.ExcelReader;

public class UserpostMissManddata {
	
	ExcelReader er = new ExcelReader();

	 public UserMPostMainPojo dataBuild() throws IOException {
		
		// UserMPostPojo U1 = new UserMPostPojo();
		 UserMPostMainPojo Ul = new UserMPostMainPojo();
		
		 Ul.setUserComments(er.getCellData("UserPostData", 3, 0));
		 Ul.setUserEduPg(er.getCellData("UserPostData", 3, 1));
		 Ul.setUserEduUg(er.getCellData("UserPostData", 3, 2));
		 Ul.setUserFirstName(er.getCellData("UserPostData", 3, 3));
		 Ul.setUserLastName(er.getCellData("UserPostData", 3, 4));
		 Ul.setUserLinkedinUrl(er.getCellData("UserPostData", 3, 5));
		 Ul.setUserLocation(er.getCellData("UserPostData", 3, 6));
		 
		 UserMPostLoginPojo L1 = new UserMPostLoginPojo();
		 L1.setLoginStatus(er.getCellData("UserPostData", 3, 7));
		 L1.setPassword(er.getCellData("UserPostData", 3, 8));
		 L1.setUserLoginEmail(er.getCellData("UserPostData", 3, 9));
		 Ul.setUserLogin(L1);
		 
		 Ul.setUserMiddleName(er.getCellData("UserPostData", 3, 10));
		 Ul.setUserPhoneNumber(er.getCellData("UserPostData", 3, 11));
		
		UserMPostRoleMapPojo urm =new UserMPostRoleMapPojo();
		urm.setRoleId(er.getCellData("UserPostData", 3, 12));
		urm.setUserRoleStatus(er.getCellData("UserPostData", 3, 13));
		List<UserMPostRoleMapPojo> userMpostRoleMapList = new ArrayList<UserMPostRoleMapPojo>();
		userMpostRoleMapList.add(urm);
		Ul.setUserRoleMaps(userMpostRoleMapList);
		
		Ul.setUserTimeZone(er.getCellData("UserPostData", 3, 14));
		Ul.setUserVisaStatus(er.getCellData("UserPostData", 3, 15));
		return Ul;
   }

}
