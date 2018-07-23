package ru.utair.kubsu.bugreporter.controller;

import net.rcarz.jiraclient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.utair.kubsu.bugreporter.Bug;

import java.util.List;

@RestController
@RequestMapping(path = "/rest", produces = "application/json")
public class UIController {

    @Autowired
    private RestTemplate rest;

    @PostMapping(path = "add_bug")
    public String createIssue(@RequestBody Bug bug) {
        HttpEntity<Bug> request = new HttpEntity<>(bug);
        String url = "https://jira.utair.ru/rest/api/2/issue";
        ResponseEntity<String> response = rest.exchange(url, HttpMethod.POST, request, String.class);
        //com.atlassian.

        BasicCredentials creds =
                new BasicCredentials("Krogenit", "davikdavik123");
        JiraClient jira = new JiraClient("jira.utair.ru", creds);

        try {
            Issue newIssue = jira.createIssue("TEST", "Bug")
                    .field(Field.SUMMARY, "Bat signal is broken")
                    .field(Field.DESCRIPTION, "Commissioner Gordon reports the Bat signal is broken.")
                    .field(Field.REPORTER, "batman")
                    .field(Field.ASSIGNEE, "robin")
                    .execute();
        } catch (JiraException e) {
            System.err.println(e.getMessage());
            if (e.getCause() != null)
                System.err.println(e.getCause().getMessage());
           // e.printStackTrace();
        }

        return response.getStatusCode().toString();
    }

    @GetMapping(path = "bug")
    public String createIssue() {
        BasicCredentials creds =
                new BasicCredentials("Krogenit", "davikdavik123");
        JiraClient jira = new JiraClient("jira.utair.ru", creds);

        /*try {
            Issue newIssue = jira.createIssue("TEST", "Bug")
                    .field(Field.SUMMARY, "Bat signal is broken")
                    .field(Field.DESCRIPTION, "Commissioner Gordon reports the Bat signal is broken.")
                    .field(Field.REPORTER, "batman")
                    .field(Field.ASSIGNEE, "robin")
                    .execute();
        } catch (JiraException e) {
            e.printStackTrace();
        }*/

        try {
            List<Project> projects = jira.getProjects();
            for(Project p : projects) {
                System.out.println(p.getName());
            }
        } catch (JiraException e) {
            e.printStackTrace();
        }

        return "ok";
    }}
