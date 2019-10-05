//子类对象实例化的全过程
public class TestDog {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.setAge(10);
        d.setName("小明");
        d.setHostName("花花");

        System.out.println("name:" + d.getName() + " age:" + d.getAge()
                + "hostName:" + d.getHostName());

        System.out.println(d.toString());
    }
}

// 生物
class Creator {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Creator() {
        super();
        System.out.println("this is Creator's constructor");
    }

}

// 动物类
class Animal extends Creator {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal() {
        super();
        System.out.println("this is Animal's constructor");
    }

}

// 狗
class Dog extends Animal {
    private String hostName;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Dog() {
        super();
        System.out.println("this is Dog's constructor");
    }

}

