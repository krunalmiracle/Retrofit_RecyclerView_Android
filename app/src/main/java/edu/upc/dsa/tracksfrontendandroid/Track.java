package edu.upc.dsa.tracksfrontendandroid;

public class Track {
    String id;
    String title;
    String singer;
    String speed = "1x"; //Test Parameter ALWAYS GENERATE GETTERS & SETTERS for JSON PARSING

    static int lastId;

    public Track() {

    }

    public Track(String title, String singer) {
        this();
        this.setSinger(singer);
        this.setTitle(title);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id=id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSpeed() {  return this.speed;   }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Track [id="+id+", title=" + title + ", singer=" + singer +", Test_Note="+ speed +"]";
    }
}
