package api.pojo;

public class CreateBatchPojo {

	// attributes
		private String batchDescription;
		private String batchName;
		private String batchStatus;
		private String batchNoOfClasses;

		public String getBatchDescription() {
			return batchDescription;
		}

		public void setBatchDescription(String batchDescription) {
			this.batchDescription = batchDescription;
		}

		public String getBatchName() {
			return batchName;
		}

		public void setBatchName(String batchName) {
			this.batchName = batchName;
		}

		public String getBatchStatus() {
			return batchStatus;
		}

		public void setBatchStatus(String batchStatus) {
			this.batchStatus = batchStatus;
		}
		
		public String getBatchNoOfClasses() {
			return batchNoOfClasses;
		}
		
		public void setBatchNoOfClasses(String batchNoOfClasses) {
			this.batchNoOfClasses = batchNoOfClasses;
		}
}
