package edu.upc.dsa.tracksfrontendandroid;

public class Repo {
    String id;
    String name;
    String full_name;

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getfull_name() {
        return full_name;
    }

    public void setfullname(String fullname) {
        this.full_name = fullname;
    }

    @Override
    public String toString() {
        return "Id: "+this.id + " <name: "+this.name + "> full_name: " +this.full_name +"\n";
    }
}
