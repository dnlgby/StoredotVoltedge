package com.example.storedotvoltedge;

public class ApiEntry {

    private String API;
    private String Description;
    private String Auth;
    private boolean HTTPS;
    private String Cors;
    private String Link;
    private String Category;

    public ApiEntry(String s, String s1, String s2, boolean b, String s3, String s4, String s5) {
        this.API = s;
        this.Description = s1;
        this.Auth = s2;
        this.HTTPS = b;
        this.Cors = s3;
        this.Link = s4;
        this.Category = s5;
    }

    public String getAPI() {
        return API;
    }

    public void setAPI(String API) {
        this.API = API;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAuth() {
        return Auth;
    }

    public void setAuth(String auth) {
        Auth = auth;
    }

    public boolean isHTTPS() {
        return HTTPS;
    }

    public void setHTTPS(boolean HTTPS) {
        this.HTTPS = HTTPS;
    }

    public String getCors() {
        return Cors;
    }

    public void setCors(String cors) {
        Cors = cors;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
