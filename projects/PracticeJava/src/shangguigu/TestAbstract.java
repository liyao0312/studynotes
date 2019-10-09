/**
 * Created by artsing on 2019/10/6.
 */
public class TestAbstract {
    public static void main(String[] args) {
        Employee[] e = new Employee[10];
        e[0] =new SalariedEmployee("name11",1,new MyDate(2019,10,6),10);
        e[1] =new SalariedEmployee("name22",2,new MyDate(2019,10,6),20);
        e[2] =new HourlyEmployee("name22",2,new MyDate(2019,10,6),20,1);
        for(Object var :e){
            System.out.println(var);
        }
    }
}
abstract class  Employee{
    private String name;
    private int number;
    private MyDate birthday;

    abstract double earnings();
    public Employee(){

    }
    public Employee(String name,int number,MyDate birthday){
        this.name=name;
        this.number=number;
        this.birthday=birthday;
    }
    public  String toString(){
        return  name+" "+number+" "+birthday;
    }
}

class  MyDate{
    private int month;
    private int day;
    private int year;
    public MyDate(int year,int month,int day){
        super();
        this.year=year;
        this.day=day;
        this.month=month;
    }
    public String toDateString(){
        return this.year+"年"+this.month+"月"+this.day+"日";
    }
}

class  SalariedEmployee extends Employee{
    private double monthlySalary;
    public SalariedEmployee(){

    }
    public SalariedEmployee(String name,int number,MyDate birthday, double monthlySalary){
        super(name,number,birthday);
        this.monthlySalary=monthlySalary;

    }
    @Override
    public double earnings() {
        return monthlySalary;
    }
    public  String toString(){
        return "SalariedEmployee ["+super.toString()+"monthlySalary="+monthlySalary+"]";
    }
}

class HourlyEmployee extends  Employee{
    private double wage;
    private int hour;
    public HourlyEmployee(){}
    public HourlyEmployee(String name,int number,MyDate birthday, double wage,int hour){
        super(name,number,birthday);
        this.wage=wage;
        this.hour=hour;

    }
    @Override
    double earnings() {
        return wage*hour;
    }

    public  String toString(){
        return "HourlyEmployee ["+super.toString()+"HourlySalary="+this.earnings()+"]";
    }
}