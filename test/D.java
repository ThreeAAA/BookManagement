import java.util.HashMap;
import java.util.Map;

public class D {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key1","value1");
        map.put("key2","value2");//插入元素
        System.out.println(map.get("key1"));
        System.out.println(map.get("key2"));
        System.out.println(map.get("key3"));
    }
    public void cc(String... values){
        System.out.println(values.length);
    }
}
