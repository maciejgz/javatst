package pl.mg.javatst.cert.ocp;

import java.util.Map;
import java.util.concurrent.*;

public class BlockingQueueTests {

    public static void main(String[] args) {
        BlockingQueue<String> bq = new ArrayBlockingQueue<String>(20);

        bq.offer("one");
        bq.offer("two");
        bq.offer("three");
        bq.add("four");
        
        

        System.out.println(bq.poll());
        System.out.println(bq.poll());

        BlockingDeque<String> bd = new LinkedBlockingDeque<>();
        bd.offer("one");
        bd.offer("two");
        System.out.println(bd.poll());

        Map<String, String> stringStringConcurrentSkipListMap = new ConcurrentSkipListMap<>();
    }

}
