package MovieProject.BE;

public class Categories {

    private int id;
    private String Name;

    public Categories(int id, String name) {
        this.id = id;
        Name = name;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

}
