# US 014 - As an administrator, I want to load a set of users from a CSV file.

## 1. Requirements Engineering

### 1.1. User Story Description

*As an administrator, I want to load a set of users from a CSV file.*

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

>[...]The user should introduce his/her SNS user number[...]

**From the client clarifications:**

>*Question:* "What would be the sequence of parameters to be read on the CSV? For example: "Name | User Number".
>
>*Answer:* Name, Sex, Birth Date, Address, Phone Number, E-mail, SNS User Number and Citizen Card Number.

>*Question:* "how should the admin receive the login data/passwords for all registered users?"
>
>*Answer:* During this sprint, login and password data should be presented in the console application. In US14 the application is used to register a batch of users. For each user, all the data required to register a user should be presented in the console.

>*Question:* "What should the system do if the file to be loaded has information that is repeated? For example, if there are 5 lines that have the same information or that have the same attribute, like the phone number, should the whole file be discarded?"
>
>*Answer:* If the file does not have other errors, all records should be used to register users. The business rules will be used to decide if all file records will be used to register a user. For instance, if all users in the CSV file are already registered in system, the file should be processed normally but no user will be added to the system (because the users are already in the system).

>*Question:* This question also regards the attribute sex, is the format "F"/"M"/ "N/A", or "female"/"male"/"N/A" , or a different, or can it be any?
>
>*Answer:* From a previous answer we get "Optional attributes may have a NA value".

>*Question:* "is there any specific format that should be validated for the address, or we can assume it is just of string type?"
>
>*Answer:* The address contained in the CSV file is a string and should not contain commas or semicolons.

>*Question:* "Should our application detect if the CSV file to be loaded contains the header, or should we ask the user if is submitting a file with a header or not?"
>
>*Answer:* The application should automatically detect the CSV file type.

### 1.3. Acceptance Criteria

* **AC1:** The application must support importing two types of CSV files: a) one type must have a header, column separation is done using “;” character; b) the other type does not have a header, column separation is done using “,” character.
* **AC2:** Only files with valid information are accepted by the system.
* **AC3:** Users with duplicated information are ignored by the system.
* **AC4:** All the data required to register a user should be presented in the console.

### 1.4. Found out Dependencies

US014 has no dependencies.

### 1.5 Input and Output Data

**Input Data:** 
*Typed Data
    - CSV path

* Selected data:
    - Confirm loading of a new file
    - List of SNS Users

**Output Data:**

* The number of Users that were created
* The number of Users saved
* The number of Users with duplicated information
* Warning about the file validity/existence
* A list of all the users saved in the system
* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)

*Insert here a SSD depicting the envisioned Actor-System interactions and throughout which data is inputted and outputted to fulfill the requirement. All interactions must be numbered.*

![USXXX-SSD](USXXX-SSD.svg)

### 1.7 Other Relevant Remarks

*Use this section to capture other relevant information that is related with this US such as (i) special requirements ; (ii) data and/or technology variations; (iii) how often this US is held.*


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt
*In this section, it is suggested to present an excerpt of the domain model that is seen as relevant to fulfill this requirement.*

![USXXX-MD](USXXX-MD.svg)

### 2.2. Other Remarks

*Use this section to capture some aditional notes/remarks that must be taken into consideration into the design activity. In some case, it might be usefull to add other analysis artifacts (e.g. activity or state diagrams).*



## 3. Design - User Story Realization

### 3.1. Rationale

**The rationale grounds on the SSD interactions and the identified input/output data.**

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1  		 |							 |             |                              |
| Step 2  		 |							 |             |                              |
| Step 3  		 |							 |             |                              |
| Step 4  		 |							 |             |                              |
| Step 5  		 |							 |             |                              |
| Step 6  		 |							 |             |                              |              
| Step 7  		 |							 |             |                              |
| Step 8  		 |							 |             |                              |
| Step 9  		 |							 |             |                              |
| Step 10  		 |							 |             |                              |  


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:

* Class1
* Class2
* Class3

Other software classes (i.e. Pure Fabrication) identified:
* xxxxUI
* xxxxController

## 3.2. Sequence Diagram (SD)

*In this section, it is suggested to present an UML dynamic view stating the sequence of domain related software objects' interactions that allows to fulfill the requirement.*

![USXXX-SD](USXXX-SD.svg)

## 3.3. Class Diagram (CD)

*In this section, it is suggested to present an UML static view representing the main domain related software classes that are involved in fulfilling the requirement as well as and their relations, attributes and methods.*

![USXXX-CD](USXXX-CD.svg)

# 4. Tests
*In this section, it is suggested to systematize how the tests were designed to allow a correct measurement of requirements fulfilling.*

**_DO NOT COPY ALL DEVELOPED TESTS HERE_**

**Test 1:** Check that it is not possible to create an instance of the Example class with null values.

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Exemplo instance = new Exemplo(null, null);
	}

*It is also recommended to organize this content by subsections.*

# 5. Construction (Implementation)

*In this section, it is suggested to provide, if necessary, some evidence that the construction/implementation is in accordance with the previously carried out design. Furthermore, it is recommeded to mention/describe the existence of other relevant (e.g. configuration) files and highlight relevant commits.*

*It is also recommended to organize this content by subsections.*

# 6. Integration and Demo

*In this section, it is suggested to describe the efforts made to integrate this functionality with the other features of the system.*


# 7. Observations

*In this section, it is suggested to present a critical perspective on the developed work, pointing, for example, to other alternatives and or future related work.*





