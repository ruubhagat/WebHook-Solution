# Bajaj Finserv Health | Java Qualifier Task

**Name:** Rutuja Bhagat  
**SRN:** PES1UG23CS808  
**Email:** pes1ug23cs808@pesu.pes.edu  

---

## Task Overview
Spring Boot application that:
- Sends a POST request to generate a webhook and JWT token on startup  
- Determines SQL problem based on RegNo (Even → Question 2)  
- Submits the final SQL query automatically using the provided webhook URL  

---

## Tech Stack
- Java 17
- Spring Boot 3.x
- WebClient (Reactive REST)
- Maven

---

## 📜 SQL Problem (Question 2)
**Problem:**  
For each employee, find how many employees in the same department are *younger* than them.

**Final SQL Query:**
```sql
SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME,
       COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT
FROM EMPLOYEE e1
JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID
LEFT JOIN EMPLOYEE e2 
       ON e1.DEPARTMENT = e2.DEPARTMENT 
      AND e2.DOB > e1.DOB
GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
ORDER BY e1.EMP_ID DESC;
