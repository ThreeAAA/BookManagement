package dataBase.operation;

import java.sql.ResultSet;

public class Unit {
    public static boolean quchong() throws Exception{
        //去重，去掉books里的重复书名
        Query q = new Query();
        Delete d = new Delete();
        ResultSet r = q.queryAllBook();
        String[] titles = new String[50];
        int cont = 0;
        String title;
        int id;
        for (int i=0;i<titles.length;i++){
            cont = 0;
            if(r.next()){
                id = r.getInt(1);
                title = r.getString(2);
                System.out.println("--"+title);
            }else {
                break;
            }

            for (int i1 = 0;i1<i; i1++){
                if (title.equals(titles[i1])){
                    d.deleteBook(Integer.toString(id));
                    cont = 1;
                    break;
                }
            }
            if (cont != 1){
                titles[i] = title;
            }else {
                i--;
            }

        }
        return true;
    }

    //插入很多信息
    public static boolean inOO() throws Exception{
        Insert ii = new Insert();
        for (int i= 1; i<257; i++){
            if (ii.insert("insert into test values("+i+","+i+");")){
                System.out.println("OK");
            }else{
                System.out.println("NO");
            }
        }

        return true;
    }
}
