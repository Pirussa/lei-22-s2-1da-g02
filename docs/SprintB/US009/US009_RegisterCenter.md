# US 009 - Register a Vaccination Center

## 1. Requirements Engineering


### 1.1. User Story Description


As an administrator, I want to register a vaccination center to respond to a certain pandemic.


### 1.2. Customer Specifications and Clarifications


**From the specifications document:**

> Different from the health care centers, which provide a wide range of healthcare services to citizens in  a certain area, the mass vaccination centers are facilities specifically created to aminister vaccines  of a single type as response to an ongoing disease outbreak (e.g.: Covid-19).

> The vaccination process flow and employees enrolled in the vaccination process are almost the same in each kind of vaccination center. The main difference between the two kinds of centers is that a healthcare center is associated with a given ars ( adminis tracao regional de saude ) and ages ( agrupamentos de centros de saude ), and it can administer any type of vaccines (e. G. : covid - 19, dengue, tetanus , smallpox ) . Moreover, nurses working in the healthcare centers can issue and deliver on site a vaccination certificate whenever a sns user asks for it . Both kinds of vaccination centers are characterized by a name, an address, a phone number, an e-mail address, a fax number, a website address, opening and closing hours, slot duration ( e. G. : 5 minutes) and the maximum number of vaccines that can be given per slot (e. G. : 10 vaccines per slot) . In addition, each vaccination center has one coordinator. Furthermore, receptionists and nurses registered in the application will work in the vaccination process. As the allocation of receptionists and nurses to vaccination centers might be complex, by now, the system might assume that receptionists and nurses can work on any vaccination center.


**From the client clarifications:**

> **Question:** How will be a center registered in the system (what data must be asked)?
>
> **Answer:** This information is available in the project description. When asking a question the student should show to be aware of all the information that the client has provided.

> **Answer:** Community mass vaccination centers are facilities created specifically to administer a single type of vaccine as a response to an ongoing disease outbreak. These centers are closed when there is no outbreak.

### 1.3. Acceptance Criteria

* **AC1:** All required fields must be filled in.

### 1.4. Found out Dependencies


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
    * name
    * address
    * phoneNumber
    * emailAddress
    * faxNumber
    * websiteAddress

* Selected data:
    * N/A


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

![US009_SSD](C:\Users\Castro\Desktop\CODE\lei-22-s2-1da-g02\docs\SprintB\US009\US009_SSD.svg)

### 1.7 Other Relevant Remarks

* No information in this field.


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US009_MD](C:\Users\Castro\Desktop\CODE\lei-22-s2-1da-g02\docs\SprintB\US009\US009_MD.svg)

### 2.2. Other Remarks

n/a


## 3. Design - User Story Realization

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1  		 |	... interacting with the actor? | CreateVaccinationCenterUI   |  Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model.           |
| 			  		 |	... coordinating the US? | CreateVaccinationCenterController | Controller                             |
| 			  		 |	... instantiating a new Task? | Organization   | Creator (Rule 1): in the DM Organization has a Task.   |
| 			  		 | ... knowing the user using the system?  | UserSession  | IE: cf. A&A component documentation.  |
| 			  		 |	... knowing to which organization the user belongs to? | Platform  | IE: has registed all Organizations |
| 			  		 |							 | Organization   | IE: knows/has its own Employees|
| 			  		 |							 | Employee  | IE: knows its own data (e.g. email) |
| Step 2  		 |							 |             |                              |
| Step 3  		 |	...saving the inputted data? | Task  | IE: object created in step 1 has its own data.  |
| Step 4  		 |	...knowing the task categories to show? | Platform  | IE: Task Categories are defined by the Platform. |
| Step 5  		 |	... saving the selected category? | Task  | IE: object created in step 1 is classified in one Category.  |
| Step 6  		 |							 |             |                              |              
| Step 7  		 |	... validating all data (local validation)? | Task | IE: owns its data.| 
| 			  		 |	... validating all data (global validation)? | Organization | IE: knows all its tasks.| 
| 			  		 |	... saving the created task? | Organization | IE: owns all its tasks.| 
| Step 8  		 |	... informing operation success?| CreateTaskUI  | IE: is responsible for user interactions.  | 

### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Organization
* Platform
* Task

Other software classes (i.e. Pure Fabrication) identified:

* CreateTaskUI
* CreateTaskController


## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US006_SD](US006_SD.svg)

**Alternative 2**

![US006_SD](US006_SD_v2.svg)

## 3.3. Class Diagram (CD)

**From alternative 1**

![US006_CD](US006_CD.svg)

# 4. Tests

**Test 1:** Check that it is not possible to create an instance of the Task class with null values.

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Task instance = new Task(null, null, null, null, null, null, null);
	}


**Test 2:** Check that it is not possible to create an instance of the Task class with a reference containing less than five chars - AC2.

	@Test(expected = IllegalArgumentException.class)
		public void ensureReferenceMeetsAC2() {
		Category cat = new Category(10, "Category 10");
		
		Task instance = new Task("Ab1", "Task Description", "Informal Data", "Technical Data", 3, 3780, cat);
	}


*It is also recommended to organize this content by subsections.*

# 5. Construction (Implementation)


## Class CreateTaskController

		public boolean createTask(String ref, String designation, String informalDesc, 
			String technicalDesc, Integer duration, Double cost, Integer catId)() {
		
			Category cat = this.platform.getCategoryById(catId);
			
			Organization org;
			// ... (omitted)
			
			this.task = org.createTask(ref, designation, informalDesc, technicalDesc, duration, cost, cat);
			
			return (this.task != null);
		}


## Class Organization


		public Task createTask(String ref, String designation, String informalDesc, 
			String technicalDesc, Integer duration, Double cost, Category cat)() {
		
	
			Task task = new Task(ref, designation, informalDesc, technicalDesc, duration, cost, cat);
			if (this.validateTask(task))
				return task;
			return null;
		}



# 6. Integration and Demo

* A new option on the Employee menu options was added.

* Some demo purposes some tasks are bootstrapped while system starts.


# 7. Observations

Platform and Organization classes are getting too many responsibilities due to IE pattern and, therefore, they are becoming huge and harder to maintain.

Is there any way to avoid this to happen?





