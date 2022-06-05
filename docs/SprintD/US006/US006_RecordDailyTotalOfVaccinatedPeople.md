# US 006 - Record the daily total of vaccinated people in each vaccination center. 

## 1. Requirements Engineering


### 1.1. User Story Description


>* **_DGS wants to record daily the total number of people vaccinated in each vaccination center._**



### 1.2. Customer Specifications and Clarifications 


**From the specifications document:**

> **_No Information regarding this US on the specifications document._**


**From the client clarifications:**

> **Question:** Dear client, In the acceptance criteria, "the algorithm should run automatically at a time defined in a configuration file and should register a date, the name of the vaccination center and the total number of vaccinated users." How is it supposed to register this information? Should it be recorded in a file (ex: txt,...) or recorded in the system (ex: in a store) ?
 
> **Answer:** The data should be written to a CSV file (field delimiter should be a semicolon).


### 1.3. Acceptance Criteria


* **AC1:** The algorithm should run automatically at a time defined in a configuration file and should register the date, the name of the vaccination center and the total number of vaccinated users.


### 1.4. Found out Dependencies

>*There is a dependency found in US008 "As a nurse, I want to record the administration of a vaccine to a SNS user. At the end of the recovery period, the user should receive a SMS message informing the SNS user that he can leave the vaccination center.", because in order to get the daily number of vaccinated people, it's necessary that people get vaccinated.


### 1.5 Input and Output Data


**Input Data:**


**Output Data:**

>* A ".csv" file is created and the information about the daily total vaccinated people in each vaccinated center is exported to that file.

### 1.6. System Sequence Diagram (SSD)

**Alternative 1**

![US006_SSD](US006_SSD.svg)

### 1.7 Other Relevant Remarks

>*


## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US006_MD](US006_MD.svg)

### 2.2. Other Remarks

> *_There aren't any other relevant remarks._*


## 3. Design - User Story Realization 

### 3.1. Rationale

**SSD - Alternative 1 is adopted.**

| *Interaction ID* | *Question: Which class is responsible for...* | *Answer*  | *Justification (with patterns)*  |
|:-------------  |:--------------------- |:------------|:---------------------------- |
| Step 1  		 |	... interacting with the actor?	 |          |    | Step 2  		 |							 |             |                              |
| |... coordinating the US? | |  | |
### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are: 

*Company.

Other software classes (i.e. Pure Fabrication) identified: 



## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US006_SD](US006_SD.svg)

## 3.3. Class Diagram (CD)

**From alternative 1**

![US006_CD](US006_CD.svg)