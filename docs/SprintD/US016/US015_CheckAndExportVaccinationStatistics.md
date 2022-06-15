# US 016 - Analyze the performance of a center

## 1. Requirements Engineering

### 1.1. User Story Description

As a **Center Coordinator**, I intend to **analyze** the performance of a center.


### 1.2. Customer Specifications and Clarifications

### **From the specifications document:**

> Each vaccination center has a Center Coordinator that has the responsibility to manage the Covid19 vaccination process. The Center Coordinator wants to monitor the vaccination process, to see
statistics and charts, to evaluate the performance of the vaccination process, generate reports

### **From the client clarifications:**

**-From the requirements document:**
>The Center Coordinator wants to monitor the vaccination process, to see
statistics and charts, to evaluate the performance of the vaccination process, generate reports and
analyze data from other centers, including data from legacy systems. The goal of the performance
analysis is to decrease the number of clients in the center, from the moment they register at the
arrival, until the moment they receive the SMS informing they can leave the vaccination center. To
evaluate this, it proceeds as follows: for any time interval on one day, the difference between the
number of new clients arrival and the number of clients leaving the center every five-minute period
is computed.

>Now, the problem consists in determining what the contiguous subsequence of the initial sequence
is, whose sum of their entries is maximum. This will show the time interval, in such a day, when the
vaccination center was less effective in responding. So, the application should implement a brute-
force algorithm (an algorithm which examines all the contiguous subsequences) to determine the
contiguous subsequence with maximum sum. The implemented algorithm should be analyzed in
terms of its worst-case time complexity, and it should be compared to a benchmark algorithm
provided. The computational complexity analysis (of the brute-force algorithm and any sorting
algorithms implemented within this application), must be accompanied by the observation of the
execution time of the algorithms for inputs of variable size, in order to observe the asymptotic
behavior. The worst-case time complexity analysis of the algorithms should be properly
documented in the user manual of the application (in the annexes). The user manual must be
delivered with the application
 

**-From the client forum:**


>**Question:**
>The file loaded in US17 have only one day to analyse, or it could have more than one day(?) and in US16 we need to select the day to analyse from 8:00 to 20:00
>
>**Answer:**
>The file can have data from more than one day. In US16 the center coordinator should select the day for which he wants to analyse the performance of the vaccination center.


>**Question:**
>Is the time of departure of an SNS user the time he got vaccinated plus the recovery time or do we have another way of knowing it?
>
**Answer:**
>The time of departure of an SNS user is the time he got vaccinated plus the recovery time



>**Questions:**
>In US 16, should the coordinator have the option to choose which algorithm to run (e.g. via a configuration file or while running the application) in order to determine the goal sublist, or is the Benchmark Algorithm strictly for drawing comparisons with the Bruteforce one?
>
**Answer:**
>The algorithm to run should be defined in a configuration file.
 

>**Question:**
>I would like to ask that if to analyse the performance of a center, we can assume (as a pre requirement) that the center coordinator was already attributed to a specific vaccination center and proceed with the US as so (like the center coordinator does not have to choose at a certain point where he is working. This is already treated before this US happens). Could you clarify this?
>
>**Answer:**
>A center coordinator can only coordinate one vaccination center. The center coordinator can only analyze the performance of the center that he coordinates.


>**Question**
>I would like to know if we could be strict the user to pick only those intervals (m) (i.e. 1, 5, 10, 20, 30) as options for analyzing the performance of a center, since picking intervals is dependent on the list which is 720/m (which the length is an integer result). If we let the user pick an interval that results in a non-integer result, this will result in an invalid list since some data for the performance analysis will be lost. Can you provide a clarification on this situation?
> 
> **Answer**
>  The user can introduce any interval value. The system should validate the interval value introduced by the user.


>**Question**
> From the Sprint D requirements it is possible to understand that we ought to implement a procedure that creates a list with the differences between the number of new clients arriving and the number of leaving clients for each time interval. My question then is, should this list strictly data from the legacy system (csv file from moodle which is loaded in US17), or should it also include data from our system?
> 
> **Answer**
>US 16 is for all the data that exists in the system.



### 1.3. Acceptance Criteria


* **AC1:** A file with the Vaccination Statistics is created.


### 1.4. Found out Dependencies

There is a dependency with US008, as it is required to have the necessary information that that US provides in order to create a file with the vaccination statistics.

### 1.5 Input and Output Data

**Input Data:**

* Typed data:
    - The name of the file intended to export the vaccination statistics.

* Selected data:
    - The time interval.
    - Between the options of checking or exporting the vaccination statistics.

**Output Data:**

The Vaccination Statistics;
 A file with the Vaccination Statistics.


### 1.6. System Sequence Diagram (SSD)

![US015_SSD](US015_SSD.svg)

### 1.7 Other Relevant Remarks

>No other relevant remarks.

## 2. OO Analysis

### 2.1. Relevant Domain Model Excerpt 

![US015_MD](US015_MD.svg)

### 2.2. Other Remarks

> VaccinationStatistics represents the statistics of the vaccination process.
> By the VaccinationStatistics we get the total number of fully vaccinated users per day, in a center.
> The same center can have several statistics, as the Center Coordinator can check and export statistics for different time intervals.
> Per example: the Center Coordinator can check the statistics from day 1 to 10 of a month, and then export the statistics from day 10 to 20 of the next month. Therefore, those are different statistics.


## 3. Design - User Story Realization 

### 3.1. Rationale



### Systematization ##

According to the taken rationale, the conceptual classes promoted to software classes are:


* ExportListToFile
* VaccinationCenter
* Company

Other software classes (i.e. Pure Fabrication) identified:
* CheckAndExportVacStatsGUI
* CheckAndExportVaccinationStatsController
* CheckListVacStatsGUI

## 3.2. Sequence Diagram (SD)

**Alternative 1**

![US015_SD](US015_SD.svg)

## 3.3. Class Diagram (CD)

**From alternative 1**

![US008_CD](US008_CD.svg)