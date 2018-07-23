package ru.utair.kubsu.bugreporter;

public class Bug {

    private String firstName;
    private String secondName;
    private String summary;
    private String description;

    public Bug(String firstName, String secondName, String summary, String description) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.summary = summary;
        this.description = description;
    }



    @Override
    public String toString() {
        return "Bug{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
