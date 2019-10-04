/**
 * Created by artsing on 2019/10/3.
 */
class  Ticket extends  Thread{
    static int ticket =100;

    public  void run(){
        while(true){
            if(ticket>0){
                System.out.println(Thread.currentThread().getName()+"售票，票号位：" + ticket--);
            }
        }
    }
}

public class TestTicket {

    public static void main(String[] args) {
        Ticket t1 = new Ticket();
        Ticket t2= new Ticket();
        Ticket t3 = new Ticket();

        t1.setName("t1窗口");
        t2.setName("t1窗口");
        t3.setName("t1窗口");

        t1.start();
        t2.start();
        t3.start();
    }
}
