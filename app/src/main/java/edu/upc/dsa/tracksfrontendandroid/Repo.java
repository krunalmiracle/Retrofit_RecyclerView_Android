package edu.upc.dsa.tracksfrontendandroid;



public class Repo {
    String id;
    String name;
    String full_name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String fullname) {
        this.full_name = fullname;
    }

    @Override
    public String toString() {
        return "Id: "+this.id + " <name: "+this.name + "> full_name: " +this.full_name +"\n";
    }
}
