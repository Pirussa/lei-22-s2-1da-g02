# US 006 - To register a SNS user

## 1. Requirements Engineering


### 1.1. User Story Description


As a receptionist, I want to register a SNS user.


### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

>	Each task is characterized by having a unique reference per organization, a designation, an informal and a technical description, an estimated duration and cost as well as the its classifying task category. 


>	As long as it is not published, access to the task is exclusive to the employees of the respective organization. 



**From the client clarifications:**

> **Question:** Which is the unit of measurement used to estimate duration?
>  
> **Answer:** Duration is estimated in days.

-

> **Question:** Monetary data is expressed in any particular currency?
>  
> **Answer:** Monetary data (e.g. estimated cost of a task) is indicated in POTs (virtual currency internal to the platform).


### 1.3. Acceptance Criteria

The SNS user must become a system user.


### 1.4. Found out Dependencies


No dependencies were found.

### 1.5 Input and Output Data


**Input Data:**

* Typed data:
	* a name, 
	* a email, 
	* a password
	


**Output Data:**

* (In)Success of the operation

### 1.6. System Sequence Diagram (SSD)


![US003_SSD](US003_SSD.svg)



### 1.7 Other Relevant Remarks

No other relevant remarks.


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US006_MD](US003_MD.svg)

### 2.2. Other Remarks

No other relevant remarks.


## 3. Design - User Story Realization 

### 3.1. Rationale


| Interaction ID | Which class responsible for...           | Answer               | Justification                                                                                                 |
|:---------------|:-----------------------------------------|:---------------------|:--------------------------------------------------------------------------------------------------------------|
| Step 1         | ...interatctin with the actor?           | CreateUserUI         | Pure Fabrication: there is no reason to assign this responsibility to any existing class in the Domain Model. |
|                | ...coordinating the US                   | CreateUserController | **Controller**                                                                                                |
| Step 2         | ...instantiating a new User              | Company              | **Creator**                                                                                                   |
| Step 3         | ...saving the inputted data for the User | Administrator        | IE: An Administrator configures and manages the data                                                          |
| Step 4         | ...informing operation success           | CreateUserUI         | IE: is responsible for user interactions                                                                      |

 
### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

 * Administrator
 * Platform
 * Task

Other software classes (i.e. Pure Fabrication) identified: 

 * CreateTaskUI  
 * CreateTaskController


## 3.2. Sequence Diagram (SD)

![US006_SD](US006_SD.svg)


## 3.3. Class Diagram (CD)

**From alternative 1**

![US006_CD](US006_CD.svg)




