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

* **AC01:** The application must support importing two types of CSV files: a) one type must have a header, column separation is done using “;” character; b) the other type does not have a header, column separation is done using “,” character.
* **AC02:** Only files with valid information are accepted by the system.
* **AC03:** Users with duplicated information are ignored by the system.
* **AC04:** All the data required to register a user should be presented in the console.
* **AC05:** The SNS User must become a system user. The "auth" component available on the repository must be reused (without modifications).
* **AC06:** Citizen Card number must follow the Portuguese format.
* **AC07:** Name can't be empty.
* **AC08:** SNS user number must have 9 characters.
* **AC09:** Birth day must have the format: DD/MM/YYYY. 
* **AC10:** Address has to be valid, zip-code must follow the Portuguese format.
* **AC11:** Sex options: Male/Female/NA or empty.
* **AC12:** Phone number must follow the Portuguese format.
* **AC13:** E-mail must have a valid suffix.
* **AC14:** All input fields are required except sex.
* **AC15:** The password should be randomly generated.
* **AC16:** The following fields must be unique for each SNS user: citizen card number, SNS number, phone number, and e-mail address.

### 1.4. Found out Dependencies

US014 has no dependencies.

### 1.5 Input and Output Data

**Input Data:** 
* Typed Data
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

![US014-SSD](C:\Users\Castro\Desktop\CODE\lei-22-s2-1da-g02\docs\SprintC\US014\US014_SSD.svg)

### 1.7 Other Relevant Remarks

* There are some similarities to US 11 regarding the need (i) to generate a password.

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt

![US014-MD](C:\Users\Castro\Desktop\CODE\lei-22-s2-1da-g02\docs\SprintC\US014\US014_DM.svg)

### 2.2. Other Remarks

No remarks.

## 3. Design - User Story Realization

### 3.1. Rationale

| Interaction ID | Question: Which class is responsible for... | Answer  | Justification (with patterns)  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1  		 |	Asking to load a CSV file	 |   LoadCSVUI          |   Pure Fabrication: |
| Step 2  		 |	Requesting the path of the CSV File						 |   LoadCSVUI          |  Pure Fabrication:                            |
| Step 3  		 |	Typing the path of the CSV File						 |   LoadCSVUI          |  Pure Fabrication:                            |
| 		 |		... validating the path |  LoadCSVUI       |      |
| 		 |		... validating the CSV that has SNS User data |  Utils     |        |
| 		 |		... generating a password for the SNS User	 |  Utils    |        |
| 		 |		... transferring the SNS User data from the UI to the controller?	 |  SNSUserDTO    | **DTO:** When there is so much data to transfer, it is better to opt by using a DTO in order to reduce coupling between UI and domain       |
| 		 |		... transfer the SNS User data from the controller to the company? |  LoadCSVController    |        |
| 		 |		... instantiating a new SNS User?	 |  Company    |        |
| 		 |		... validating the SNS User	 |  SNSUser    |        |
| 		 |		... validating SNS User duplication 	 |  Company    |        |
| 		 |		... saving the SNS User?	 |  Company    |        |
| 		 |	... registering the SNS User as a system user? | AuthFacade | **IE:** cf. A&A component documentation |
| Step 4  		 |	Informing about the (in)success of the operation						 |      LoadCSVUI      |                              |
| Step 5  		 |		Asking for a list with all saved SNS Users					 |    LoadCSVUI         |                              |
| 		 |    ... disponibilize the previous list to the GetListOfUsersUI  | LoadCSVController| **Controller:** act as a mediator between the UI and the Model. Has the responsibility of controlling the data transmission between both. It maps the user action into model updates.  |
| 			  		 |    ... disponibilize the previous list to the ScheduleVaccineController  | Company | **IE:** The Company knows all SNS Users  |
| Step 6  		 |	Showing the list						 |     LoadCSVUI        |      **IE:** is responsible for user interactions                         |              



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