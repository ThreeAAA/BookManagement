package unit;

import java.util.HashMap;
import java.util.Map;

public class JSONDate {
    String json = "{";
    Map<String, int[]> jsonMap = new HashMap<>(); //标志:key=[首位，未位]
    String[] keyArry = new String[20];  //存储key
    Map<String, String[]> jsonListMap = new HashMap<>(); //存储json里的json

    public JSONDate(){
        super();
        keyArry[0]="0";
    }

    /**
     * 添加json数据
     * @param key
     * @param value
     */
    public void put(String key, int value){
        String temp;
        if (json.length()>1){
            temp = ",\""+key+"\""+":"+""+value+"";
        }else{
            temp = "\""+key+"\""+":"+""+value+"";
        }
        this.json = json+temp;
    }
    public void put(String key,String value){
        String temp;
        if (json.length()>1){
            temp = ",\""+key+"\""+":"+"\""+value+"\"";
        }else{
            temp = "\""+key+"\""+":"+"\""+value+"\"";
        }
        this.json = json+temp;
    }

    public void put(String key, boolean value){
        String bool = value ? "true":"false";
        String temp;
        if (json.length()>1){
            temp = ",\""+key+"\""+":"+bool;
        }else{
            temp = "\""+key+"\""+":"+bool;
        }
        this.json = json+temp;
    }

    /**
     * 添加Json格式数据到[]里
     * 如{"室友":[{"张三":"男"},{"李四":"男"}]}
     * @param key
     * @param jsonDate
     */
    public void putList(String key, JSONDate jsonDate){

        if (!hasKey(key)){
            //第一次设置值
            String[] value = new String[20];
            value[0] = "1";  //有效数据长度
            value[1] = jsonDate.toString();
            jsonListMap.put(key, value);
            int index = Integer.parseInt(keyArry[0]);
            index ++;
            keyArry[index] = key;
            keyArry[0] = String.valueOf(index);
        }else {
            //第n次设置值
            String[] value = jsonListMap.get(key);
            int indexs = Integer.parseInt(value[0]);
            value[++indexs] = jsonDate.toString();
            value[0]=String.valueOf(indexs);
        }

    }
    //把jsonListMap里数据提取出来
    private String getLave(){
        String str = "";
        for (int i=1;i<Integer.parseInt(keyArry[0])+1;i++){
            String key = keyArry[i]; //key值
            String[] valueArry = jsonListMap.get(key); //存储数据的数组
            StringBuilder value = new StringBuilder("["); //
            for (int ii=1;ii<Integer.parseInt(valueArry[0])+1;ii++){ //组成一条String
                if (value.length()>1){
                    value.append(",").append(valueArry[ii]);
                }else {
                    value.append(valueArry[ii]);
                }
            }
            value.append("]");
            if (str.length()>1){  //判断空
                str +=",\""+key+"\":"+value;
            }else {
                str +="\""+key+"\":"+value;
            }
        }
//        System.out.println(keyArry[0]);
        return str;
    }

    private boolean hasKey(String key){
        return this.jsonListMap.get(key) != null;
    }

    private void setJsonMap(String key, int start, int end){
        this.jsonMap.put(key, new int[]{start, end});
    }
//    private void setJsonMap(String key, boolean value){
//        this.jsonMap.put(key, value);
//    }
//    private void setJsonMap(String key, String value){
//        this.jsonMap.put(key, value);
//    }
    @Override
    public String toString() {
        if (json.length()>1){
            //json已存入内容
            if (keyArry[0].equals("0")){
                //此json有json格式内容
                return this.json+"}";
            }else {
                return this.json+","+getLave()+"}";
            }
        }else {
            //json中没有内容
            return this.json+getLave()+"}";
        }

    }
}
