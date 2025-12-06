### Update 2
- UserType field in Registration Form
	- Registering user must select a user type **STUDENT** or **TEACHER**
	- This UserType is used later for authorization purpose
	
### Update 1
- Storage Service for file upload
	- StorageService.store() method for storing the given **MultipartFile** into *uploads* directory
	- Supports all kinds of files such as jpg, pdf, mp4, etc.
- StorageController for file download
	- To serve all types of files from *uploads* directory