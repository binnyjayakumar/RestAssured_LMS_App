package api.resourses;

import java.io.IOException;

import api.pojo.CreateBatchPojo;
import api.utils.ExcelReader;

public class CreateBatchData {
	
		ExcelReader er = new ExcelReader();

		// Valid data for Post request
		public CreateBatchPojo dataBuild() throws IOException {

			CreateBatchPojo createBatchPojo = new CreateBatchPojo();

			createBatchPojo.setBatchDescription(er.getCellData("programBatchController", 1, 0));
			createBatchPojo.setBatchName(er.getCellData("programBatchController", 1, 1));
			createBatchPojo.setBatchStatus(er.getCellData("programBatchController", 1, 2));
			createBatchPojo.setBatchNoOfClasses(er.getCellData("programBatchController", 1, 3));

			return createBatchPojo;
		}
		
		// Valid data for Put request
		public CreateBatchPojo put_dataBuild() throws IOException {

			CreateBatchPojo createBatchPojo = new CreateBatchPojo();

			createBatchPojo.setBatchDescription(er.getCellData("programBatchController", 1, 4));
			createBatchPojo.setBatchName(er.getCellData("programBatchController", 1, 5));
			createBatchPojo.setBatchStatus(er.getCellData("programBatchController", 1, 6));
			createBatchPojo.setBatchNoOfClasses(er.getCellData("programBatchController", 1, 7));

			return createBatchPojo;
		}
		

		// no auth valid batch data for creation and updation
		public CreateBatchPojo noauth_dataBuild() throws IOException {
			CreateBatchPojo createBatchPojo = new CreateBatchPojo();

			createBatchPojo.setBatchDescription(er.getCellData("programBatchController", 2, 0));
			createBatchPojo.setBatchName(er.getCellData("programBatchController", 2, 1));
			createBatchPojo.setBatchStatus(er.getCellData("programBatchController", 2, 2));
			createBatchPojo.setBatchNoOfClasses(er.getCellData("programBatchController", 2, 3));

			return createBatchPojo;
		}
		// Valid data for Put request
			public CreateBatchPojo noauth_updatedataBuild() throws IOException {

				CreateBatchPojo createBatchPojo = new CreateBatchPojo();

				createBatchPojo.setBatchDescription(er.getCellData("programBatchController", 1, 4));
				createBatchPojo.setBatchName(er.getCellData("programBatchController", 1, 5));
				createBatchPojo.setBatchStatus(er.getCellData("programBatchController", 1, 6));
				createBatchPojo.setBatchNoOfClasses(er.getCellData("programBatchController", 1, 7));

				return createBatchPojo;
			}

			public CreateBatchPojo valid_endpoint_existingBatch_dataBuild() throws IOException {
				CreateBatchPojo createBatchPojo = new CreateBatchPojo();

				createBatchPojo.setBatchDescription(er.getCellData("programBatchController", 3, 0));
				createBatchPojo.setBatchName(er.getCellData("programBatchController", 1, 1));
				createBatchPojo.setBatchStatus(er.getCellData("programBatchController", 3, 2));
				createBatchPojo.setBatchNoOfClasses(er.getCellData("programBatchController", 3, 3));

				return createBatchPojo;
			}
			
			public CreateBatchPojo valid_endpoint_missing_mandatory_dataBuild() throws IOException {
				CreateBatchPojo createBatchPojo = new CreateBatchPojo();

				createBatchPojo.setBatchDescription(er.getCellData("programBatchController", 4, 0));
				createBatchPojo.setBatchName(er.getCellData("programBatchController", 4, 1));
				createBatchPojo.setBatchStatus(er.getCellData("programBatchController", 4, 2));
				createBatchPojo.setBatchNoOfClasses(er.getCellData("programBatchController", 4, 3));

				return createBatchPojo;
			}
			public CreateBatchPojo valid_endpoint_invalid_data_dataBuild() throws IOException {
				CreateBatchPojo createBatchPojo = new CreateBatchPojo();

				createBatchPojo.setBatchDescription(er.getCellData("programBatchController", 4, 0));
				createBatchPojo.setBatchName(er.getCellData("programBatchController", 4, 1));
				createBatchPojo.setBatchStatus(er.getCellData("programBatchController", 4, 2));
				createBatchPojo.setBatchNoOfClasses(er.getCellData("programBatchController", 4, 3));

				return createBatchPojo;
			}
			public CreateBatchPojo valid_endpoint_invalid_data_put_dataBuild() throws IOException {
				CreateBatchPojo createBatchPojo = new CreateBatchPojo();

				createBatchPojo.setBatchDescription(er.getCellData("programBatchController", 6, 4));
				createBatchPojo.setBatchName(er.getCellData("programBatchController", 6, 5));
				createBatchPojo.setBatchStatus(er.getCellData("programBatchController", 6, 6));
				createBatchPojo.setBatchNoOfClasses(er.getCellData("programBatchController", 6, 7));

				return createBatchPojo;
			}
}
