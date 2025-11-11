package com.bajaj.webhook_solution;

import com.bajaj.webhook_solution.model.WebhookResponse;
import com.bajaj.webhook_solution.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BajajAppApplication implements CommandLineRunner {

    @Autowired
    private WebhookService webhookService;

    public static void main(String[] args) {
        SpringApplication.run(BajajAppApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Starting Bajaj Finserv Java Qualifier Task...");

        WebhookResponse response = webhookService.generateWebhook();

        System.out.println("Webhook URL: " + response.getWebhook());
        System.out.println("Access Token: " + response.getAccessToken());

        String finalQuery = """
            SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME,
                   COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT
            FROM EMPLOYEE e1
            JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID
            LEFT JOIN EMPLOYEE e2 
                   ON e1.DEPARTMENT = e2.DEPARTMENT 
                  AND e2.DOB > e1.DOB
            GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
            ORDER BY e1.EMP_ID DESC;
        """;

        webhookService.sendSqlSolution(response.getWebhook(), response.getAccessToken(), finalQuery);
    }
}
