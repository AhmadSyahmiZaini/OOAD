package com.example.ooadfinale;

/**
 * @author
 * holds Strings to display into table for course information and scholarships in facultyView.fxml,
 * facultyMasterView.fxml, and profileView.fxml
 */
public class StringTableData {
    private String eee;
    private String cse;
    private String ece;
    private String ce;
    private String me;

    private String scholarship;


    public StringTableData(String eee, String cse, String ece, String ce, String me) {
        this.eee = eee;
        this.cse = cse;
        this.ece = ece;
        this.ce = ce;
        this.me = me;
    }

    public StringTableData(String eee, String ece, String ce, String me) {
        this.eee = eee;
        this.ece = ece;
        this.ce = ce;
        this.me = me;
    }

    public StringTableData(String scholarship) {
        this.scholarship = scholarship;
    }

    public String getScholarship() {
        return scholarship;
    }

    public void setScholarship(String scholarship) {
        this.scholarship = scholarship;
    }

    public String getEee() {
        return eee;
    }

    public void setEee(String eee) {
        this.eee = eee;
    }

    public String getCse() {
        return cse;
    }

    public void setCse(String cse) {
        this.cse = cse;
    }

    public String getEce() {
        return ece;
    }

    public void setEce(String ece) {
        this.ece = ece;
    }

    public String getCe() {
        return ce;
    }

    public void setCe(String ce) {
        this.ce = ce;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }
}
