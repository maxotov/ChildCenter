package kz.enu.fit.entities;

public class Commentary extends Entity {
    
    private String name;
    private String email;
    private String text;
    private int checking;
    private int data_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getChecking() {
        return checking;
    }

    public void setChecking(int checking) {
        this.checking = checking;
    }

    public int getData_id() {
        return data_id;
    }

    public void setData_id(int data_id) {
        this.data_id = data_id;
    }
}
