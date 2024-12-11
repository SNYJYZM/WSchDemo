package Classs;


public class Stuednt {
    public int Id;   //学号
    public String Name;  //姓名
    public int Count;  //作业次数

    public Stuednt() {
    }

    public Stuednt(int id, String name, int count) {
        Id = id;
        Name = name;
        Count = count;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
