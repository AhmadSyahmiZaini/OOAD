package com.example.ooadfinale;

/**
 * Holds string to display into table for qualification and grade in profileView.fxml
 */
public class StringTableDataProfile {

    private String qualification;
    private String grade;

    public StringTableDataProfile(String qualification, String grade) {
        this.qualification = qualification;
        this.grade = grade;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
