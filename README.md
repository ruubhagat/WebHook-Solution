# Bajaj Finserv Health | Java Qualifier Task

**Name:** Rutuja Bhagat  
**SRN:** PES1UG23CS808  
**Email:** pes1ug23cs808@pesu.pes.edu  

---

### • Task Overview
On application startup:
- Generates a webhook and JWT access token.
- Automatically sends the final SQL solution using the JWT token.
- No manual trigger or endpoint is used.

---

### • SQL Solution (Question 2 - Even RegNo)
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
