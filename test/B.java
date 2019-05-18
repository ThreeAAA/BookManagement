public class B extends  A{
    public void t() {
        if (A.a == 1){
            System.out.println("YES");
        }else
        {
            System.out.println("NO!");
        }
    }

    public void e(){
        super.a = 2;
    }
}
