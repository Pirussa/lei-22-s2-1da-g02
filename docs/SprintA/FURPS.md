# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._




| Function                             | Description                                                                                                                |
|:-------------------------------------|:---------------------------------------------------------------------------------------------------------------------------|
| Nationality                          | [Allows the user to change the main language in the aplication](Nationality.md)                                            |
| FAQ                                  | [Permits the placement and clarification of the most diverse doubts](Qs&As.md)                                             |
| Email                                | [Adding services related to sending/receiving email](Email.md)                                                             |
| Printing                             | [Graphic presentation coming from the information treatment](Printing.md)                                                  |
| Layered Data Access                  | [Hierarchy of permissions to certain functions or data](Layered Data Access.md)                                            |
| System Management                    | [Control systems for process and manufacturing facilities in a complex multidistributed environment](System Management.md) |
| Phone Number                         | [Send a message to confirm the scheduling of a vaccine](Phone Number.md)                                                   |
| User Counter                         | [Add or subtract the number of users present at a given time](User Counter.md)                                             |
| Digital Vaccination Certificate      | [Request the digital vaccination certificate at any given time](Digital Vaccination Certificate.md)                        |
| Schedule the Vaccine                 | [Schedule the vaccine in any available data](Schedule the Vaccine.md)                                                      |
|                                      |
|                                      |                                                                                                                            |



## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._


(fill in here)

## Reliability

What is your expectation in terms of system up-time? Between 0-4 seconds. 
Frequency and severity of failure? Minimal frequency and light severity, the only possible unconfortable failure would be if the app went down while you schedule the vaccine.
Possibility of recovery? The app has an expected recovery time of about 10 to 15 seconds. 
Accuracy? Every callculation that the app performs is expected to have optimal precision.
Average time between failures? As expected in the frequency of failure, a fair time would be weekly.
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._


(fill in here)

## Performance

Response time - Every functionality should repsond in a maximum of 2 to 3 seconds.
Start-up time - The app is predicted to start in about 3 seconds.
Recovery Time - The app has an expected recovery time of about 10 to 15 seconds.
Memory Consumption - As the functions are not expected to consume that much memory, it´s expected to consume less than a Gb.
Load Capacity - The application will be really light and will consume much load capacity.
CPU Usage - Just like load capacity and memory consumption, the CPU usage will be minimal and it will not affect the performace.
Application Availability - _**Confirmar com a Professora**_.
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._


(fill in here)

## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._ 



(fill in here)


## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._


(fill in here)


### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._


(fill in here)

### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._

(fill in here)