### Update 3 (2025.12.08)
To determine the last login time of a user, the date & time of the login event should be stored in the database. This field can be used to let the user know their last login time. Their may be other use cases.
- **lastLoginAt** Instant field is added to **User** entity
- The field is updated at the moment of login

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