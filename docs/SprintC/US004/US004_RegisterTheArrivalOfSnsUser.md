# US 006 - To register the arrival of a SNS user

## 1. Requirements Engineering


### 1.1. User Story Description

As a receptionist, I want to register the arrival of an SNS user so that the SNS user can take the vaccine.



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	When an SNS user arrives at the vaccination center, the repecionist should check the appointment and, if correct, the receptionist
should register the arrival.




**From the client clarifications:**

> **Question:** 
>  
> **Answer:** 

-



### 1.3. Acceptance Criteria


* **AC1:** No duplicate entriens should be possible for the same SNS user on the same day or vaccine period.


### 1.4. Found out Dependencies


* There is a dependency to "US001 and US002 schedule a vaccination" since in order to an SNS user go to a vaccination center
he/she needs to have a vaccination appointment.


### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* a reference, 
	* a designation, 
	* an informal description
	* a technical description
	* an estimated duration
	* an estimated cost
	
* Selected data:
	* Classifying task category 


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)


![US006_SSD](US006_SSD.svg)



### 1.7 Other Relevant Remarks

* 


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US006_MD](US006_MD.svg)

### 2.2. Other Remarks

n/a


## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| Interaction ID | Which class responsible for...             | Answer                              | Justification                                                                                                 |
|:---------------|:-------------------------------------------|:------------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1         | ...interacting with the actor?             | RegisterArrivalOfSNSUserUI          | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                | ...coordinating the US                     | RegisterArrivalOfSNSUserController  | **Controller**                                                                                                |
| Step 2         | ...register the arrival of an SNS user     | RegisterArrivalOfSNSUserController  | **Creator**                                                                                                   |
| Step 3         | ...saving the inputted data of the arrival | Company                             | IE: An Administrator configures and manages the data                                                          |
| Step 4         | ...informing operation success             | RegisterArrivalOfSNSUserUI          | IE: is responsible for user interactions                                                                      |


### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 



Other software classes (i.e. Pure Fabrication) identified: 

 * CreateTaskUI  
 * CreateTaskController
 * Company


## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US006_SD](US006_SD.svg)

**Alternative 2**

![US006_SD](US006_SD_v2.svg)

## 3.3. Class Diagram (CD)

**From alternative 1**

![US006_CD](US006_CD.svg)

