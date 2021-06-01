import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HeapTest {

//    private byte[] arr = new byte[100 * 1024];

    public static void main(String[] args) throws InterruptedException {
//        ArrayList<HeapTest> heapTests = new ArrayList<>();
//        while (true){
//            heapTests.add(new HeapTest());
//            Thread.sleep(10);
//        }

        MyHashMap<String, String> stringStringHashMap = new MyHashMap<>();
        stringStringHashMap.put("一", "一个人");
        stringStringHashMap.put("二", "二个人");
        stringStringHashMap.put("三", "三个人");
        stringStringHashMap.put("四", "四个人");

        System.out.println(stringStringHashMap.get("四"));
    }
}
