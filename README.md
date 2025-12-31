### Update 6 (2025.12.31)
In an e-commerce system, a customer can place many orders but only one order at a time. Each order must be recorded in the database's table. Among other information in that record, the customer who made the order must also be recorded; usually the **id** of the customer is recorded. In this way a record of one table can be related to a record of another table. This relationship between **Order** and it's **Customer** thus recorded is known as Foreign Key(FK) relationship in the relational database system. Such a relationship is crucial for data integrity.

Foreign Key(FK) relationship of type *one-to-one*, *many-to-one* & *many-to-many* can be designed to enforce certain data integrity constraint. Hibernate ORM provides equivalent annotations for specifying such constraints. The annotations are: *@OneToOne*, *@ManyToOne* and *@ManyToMany*.

```
@Entity
public class AcademicClass() {
	...
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	...
}
```

The above entity records class or grade levels of an academy.

```
@Entity
public class Course() {
	...
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private AcademicClass academicClass;
	...
}
```

This entity records courses of a class by the use of Foreign Key relationship. Here, each course belongs a specific class. So many courses may belong to a single class. In other words, a class can have many courses or subjects.

### Update 5 (2025.12.09)
Database credentials, API tokens, etc. used in the project must be securely kept outside the source directory. Storing such data as System/environment variables is the best way to prevent such credentials from being exposed.

For example:
**ALPHA_SCHOOL_ADMIN_PASSWORD=admin@123** is an environment variable.

```
@Service
public class UserService() {
	...
	@Value("${ALPHA_SCHOOL_ADMIN_PASSWORD}")
	private String password;
	...
```

The actual value is injected into *password* during server setup.

Environment variables can be used in the similar way in *application.properties*.

```
...
spring.datasource.username=${ALPHA_SCHOOL_DB_USERNAME}
spring.datasource.password=${ALPHA_SCHOOL_DB_PASSWORD}
...
```



### Update 4 (2025.12.09)
Admin is the most privileged user in the system. Admin user is created during the deployment of the system when no other users are yet registered in the system. Registration of **ADMIN** user is automatically done when the bean for **UserService** is created. *@PostConstruct* is used to annotate this particular method so that it automatically gets executed just after the bean is initialized.

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