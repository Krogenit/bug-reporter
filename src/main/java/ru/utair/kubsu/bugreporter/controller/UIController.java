package ru.utair.kubsu.bugreporter.controller;

import net.rcarz.jiraclient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.utair.kubsu.bugreporter.Bug;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping(path = "/rest", produces = "application/json")
public class UIController {

    @Autowired
    private RestTemplate rest;

    JiraClient jira;

    @PostMapping(path = "add_bug")
    public String createIssue(@RequestBody Bug bug) {

        if(jira == null) {
            BasicCredentials creds =
                    new BasicCredentials("Krogenit", "davikdavik123");
            jira = new JiraClient("https://jira.utair.ru", creds);
        }

        try {
            Issue newIssue = jira.createIssue(bug.getProject(), "Ошибка")
                    .field(Field.SUMMARY, bug.getSummary())
                    .field(Field.DESCRIPTION, bug.getDescription() + "\n" + bug.getMail() +
                    "\n" + bug.getName())
                    //.field(Field.REPORTER, "Krogenit")
                    .field(Field.ASSIGNEE, "Krogenit")
                    .field(Field.PRIORITY, bug.getPriority())
//                    .field(Field.DUE_DATE, bug.getDate())
                    .execute();
//            newIssue.addAttachment();
            File f = new File(".", "test.png");
            newIssue.addAttachment(f);
            //System.out.println(f.getAbsolutePath());
        } catch (JiraException e) {
            e.printStackTrace();
        }

        return "kek";
    }

    @GetMapping(path = "bug")
    public String createIssue() {

        if(jira == null) {
            BasicCredentials creds =
                    new BasicCredentials("Krogenit", "davikdavik123");
            jira = new JiraClient("https://jira.utair.ru", creds);
        }

        try {
            Issue newIssue = jira.createIssue("KUBPRCTC", "Ошибка")
                    .field(Field.SUMMARY, "короче давид тут такое дело читай внутри")
                    .field(Field.DESCRIPTION, "предлагаю бахнуть по шаве после спринта тупа атпразднывать и в манйкрафт завалица.")
                    //.field(Field.REPORTER, "Krogenit")
                    .field(Field.ASSIGNEE, "Krogenit")
                    .execute();
        } catch (JiraException e) {
            e.printStackTrace();
        }

        /*try {
            List<Project> projects = jira.getProjects();
            for(Project p : projects) {
                System.out.println(p.getName());
            }
        } catch (JiraException e) {
            e.printStackTrace();
        }*/

        return "ok";
    }}
