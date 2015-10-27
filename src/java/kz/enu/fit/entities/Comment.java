package kz.enu.fit.entities;

public class Comment extends Entity{
    private String name;
    private String text;
    private int id_center;
    private int check;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId_center() {
        return id_center;
    }

    public void setId_center(int id_center) {
        this.id_center = id_center;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
