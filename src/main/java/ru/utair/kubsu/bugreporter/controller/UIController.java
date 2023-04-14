package ru.utair.kubsu.bugreporter.controller;

import net.rcarz.jiraclient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.utair.kubsu.bugreporter.Bug;
import ru.utair.kubsu.bugreporter.Project;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/rest", produces = "application/json")
public class UIController {

    private static final String USERNAME = "Krogenit";
    private static final String PASSWORD = "davikdavik123";
    private static final String URL = "https://jira.utair.ru";

    @Autowired
    private RestTemplate rest;

    JiraClient jira;

    private void initJira() {
        if (jira == null) {
            BasicCredentials creds =
                    new BasicCredentials(USERNAME, PASSWORD);
            jira = new JiraClient(URL, creds);
        }
    }

    @PostMapping(path = "add_bug")
    public String createIssue(@RequestBody Bug bug) {
        initJira();

        try {
            Issue newIssue = jira.createIssue(bug.getProject(), "Ошибка")
                    .field(Field.SUMMARY, bug.getSummary())
                    .field(Field.DESCRIPTION, bug.getDescription() +
                            "\n E-mail: " + bug.getMail() +
                            "\n ФИО: " + bug.getName() +
                            "\n Дата ошибки: " + bug.getDate())
                    .field(Field.ASSIGNEE, "Krogenit")
                    .field(Field.PRIORITY, bug.getPriority())
                    .execute();

            File f = new File(".", bug.getFileName());
            newIssue.addAttachment(f);
        } catch (JiraException e) {
            e.printStackTrace();
        }

        return "kek";
    }

    @GetMapping(path = "projects")
    public List<Project> restGetProjects() {
        return getProjects();
    }

    public List<Project> getProjects() {
        initJira();

        List<net.rcarz.jiraclient.Project> projectsFromJira = null;
        try {
            projectsFromJira = jira.getProjects();
        } catch (JiraException e) {
            e.printStackTrace();
        }

        List<Project> projects = new ArrayList<Project>();

        for(net.rcarz.jiraclient.Project p : projectsFromJira) {
            projects.add(new Project(p.getName() + " (" + p.getKey()));
        }

        return projects;
    }

    /*@GetMapping(path = "bug")
    public String createIssue() {
        initJira();

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
        }

        return "ok";
    }}*/
}

