package com.example.ooadfinale;

/**
 * @author
 * stores all data for student, degree, and scholarship
 */
public class Data {

    private static Student student = new Student();

    private static Degree degree = new Degree();

    private static Scholarship scholarship = new Scholarship();

    public static String getDegreeID() {
        return Data.degree.getDegreeID();
    }

    public static void setDegreeID(String degreeID) {
        Data.degree.setDegreeID(degreeID);
    }

    public static String getStudentUsername() {
        return Data.student.getUsername();
    }

    public static void setStudentUsername(String studentID) {
        Data.student.setUsername(studentID);
    }
}