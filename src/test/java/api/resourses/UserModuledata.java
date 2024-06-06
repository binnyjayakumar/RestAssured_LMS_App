package api.resourses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.pojo.UserMPostLoginPojo;
import api.pojo.UserMPostMainPojo;
import api.pojo.UserMPostRoleMapPojo;
import api.pojo.UserMPutPojo;
import api.utils.ExcelReader;
import api.utils.IdHolder;

public class UserModuledata {
	
	
	ExcelReader er = new ExcelReader();

	 public UserMPostMainPojo usermodulePostdata() throws IOException {
		
		 UserMPostMainPojo Ul = new UserMPostMainPojo();
		 
		 Ul.setUserComments(er.getCellData("UserModule", 1, 0));
		 Ul.setUserEduUg(er.getCellData("UserModule", 1, 1));
		 Ul.setUserEduPg(er.getCellData("UserModule", 1, 2));
		 Ul.setUserFirstName(er.getCellData("UserModule", 1, 3));
		 Ul.setUserLastName(er.getCellData("UserModule", 1, 4));
		 Ul.setUserLinkedinUrl(er.getCellData("UserModule", 1, 5));
		 Ul.setUserLocation(er.getCellData("UserModule", 1, 6));
		
		 UserMPostLoginPojo L=new UserMPostLoginPojo();
		 
		 L.setLoginStatus(er.getCellData("UserModule", 1, 7));
		 L.setPassword(er.getCellData("UserModule", 1, 8));
		 L.setUserLoginEmail(er.getCellData("UserModule", 1, 9));
		 Ul.setUserLogin(L);
		 
		 Ul.setUserMiddleName(er.getCellData("UserModule", 1, 10));
		 Ul.setUserPhoneNumber(er.getCellData("UserModule", 1, 11));
		 
		
		 UserMPostRoleMapPojo R=new UserMPostRoleMapPojo();
		 R.setRoleId(er.getCellData("UserModule", 1, 12));
		 R.setUserRoleStatus(er.getCellData("UserModule", 1, 13));
		 
		 List<UserMPostRoleMapPojo> MyList=new ArrayList<>();
			MyList.add(R);
		Ul.setUserRoleMaps(MyList);
		 
		 Ul.setUserTimeZone(er.getCellData("UserModule", 1, 14));
		 Ul.setUserVisaStatus(er.getCellData("UserModule", 1, 15));
		
		
		return Ul;
	}
	 
	 public UserMPostMainPojo usermodulePostMandata() throws IOException {
		 
		 UserMPostMainPojo userData = new UserMPostMainPojo();	
		// UserMPostPojo userData = new UserMPostPojo();
		
		 userData.setUserComments(er.getCellData("UserModule", 2, 0));
		 userData.setUserEduPg(er.getCellData("UserModule", 2, 1));
		 userData.setUserEduUg(er.getCellData("UserModule", 2, 2));
		 userData.setUserFirstName(er.getCellData("UserModule", 2, 3));
		 userData.setUserLastName(er.getCellData("UserModule", 2, 4));
		 userData.setUserLinkedinUrl(er.getCellData("UserModule", 2, 5));
		 userData.setUserLocation(er.getCellData("UserModule", 2, 6));
		 
		 UserMPostLoginPojo L1 = new UserMPostLoginPojo();
		 L1.setLoginStatus(er.getCellData("UserModule", 2, 7));
		 L1.setPassword(er.getCellData("UserModule", 2, 8));
		 L1.setUserLoginEmail(er.getCellData("UserModule", 2, 9));
		 userData.setUserLogin(L1);
		 
		userData.setUserMiddleName(er.getCellData("UserModule", 2, 10));
		userData.setUserPhoneNumber(er.getCellData("UserModule", 2, 11));
		
		UserMPostRoleMapPojo urm =new UserMPostRoleMapPojo();
		urm.setRoleId(er.getCellData("UserModule", 2, 12));
		urm.setUserRoleStatus(er.getCellData("UserModule", 2, 13));
		List<UserMPostRoleMapPojo> userMpostRoleMapList = new ArrayList<UserMPostRoleMapPojo>();
		userMpostRoleMapList.add(urm);
		userData.setUserRoleMaps(userMpostRoleMapList);
		
		userData.setUserTimeZone(er.getCellData("UserModule", 2, 14));
		userData.setUserVisaStatus(er.getCellData("UserModule", 2, 15));
		return userData;
	}
	 
	 public String  UpdateUserRoleProgramBatchStatus(int PID,int BID ) {
		 
		 return"{\r\n  \"programId\":"+ PID+ ",\r\n  \"roleId\": \"R03\",\r\n  \"userRoleProgramBatches\": "
		 		+ "[\r\n    {\r\n      \"batchId\": "+BID+",\r\n      \"userRoleProgramBatchStatus\": \"Active\"\r\n    }\r\n  ]\r\n}\r\n";
		 
		}
	 
	// ----update user by userID----
		public UserMPutPojo userPUTdata() throws IOException {

			UserMPutPojo Uput = new UserMPutPojo();

			Uput.setUserFirstName(er.getCellData("UserPUT", 1, 0));
			Uput.setUserLastName(er.getCellData("UserPUT", 1, 1));
			Uput.setUserPhoneNumber(er.getCellData("UserPUT", 1, 2));
			Uput.setUserTimeZone(er.getCellData("UserPUT", 1, 3));
			Uput.setUserVisaStatus(er.getCellData("UserPUT", 1, 4));

			return Uput;
		}

		// ----update user by rolestatus-----
		public UserMPostRoleMapPojo userPUTRoleStatusdata() throws IOException {

			UserMPostRoleMapPojo role = new UserMPostRoleMapPojo();
			role.setRoleId(er.getCellData("UserPUT", 1, 5));
			role.setUserRoleStatus(er.getCellData("UserPUT", 1, 6));

			return role;
		}
		
		// ---update user by roleID---->
				public String updateUserbyRoleID() {
					return "{\r\n  \"userRoleList\": [\r\n    \"R03\"\r\n ]\r\n}";
				}
				
				//---update user by LoginEmail/Status----
				public String updateUserByLoginStatus() {
					return "{\r\n  \"loginStatus\": \"string\",\r\n  \"password\": \"string\",\r\n "
							+ " \"roleIds\": [\r\n    \"string\"\r\n  ],\r\n  \"status\": \"Active\",\r\n "
							+ " \"userLoginEmail\": \"userEmailID@gmail.com\"\r\n}";
					
				}
				public String updateUserByLoginStatus_invalidBody() {
					return "{\\r\\n  \\\"loginStatus\\\": \\\"string\\\",\\r\\n "
							+ " \\\"password\\\": \\\"string\\\",\\r\\n  \\\"roleIds\\\": [\\r\\n   "
							+ " \\\"string\\\"\\r\\n  ],\\r\\n  \\\"status\\\": \\\"\\\",\\r\\n  \\\"userLoginEmail\\\": \\\"\\\"\\r\\n}";
					
				}

}
