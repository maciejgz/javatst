package pl.mg.javatst.certificate.oca;

public class StringConcat {

  public static void main(String[] args) {
    /*   StringConcat ot = new StringConcat();
    String[] arr = new String[2];
    ot.initData(arr);
    ot.printData(arr);*/

    String str1 = "one";
    String str2 = "two";
    System.out.println("result" + str1.equals(str1 = str2));


      switch(getSwitch("--0.50")){
          case 0 : System.out.print("Hello ");
          case 1 : System.out.print("World"); break;
          default : System.out.print("Good Bye");
      }
  }

    public static int getSwitch(String str){
        return (int) Math.round( Double.parseDouble(str.substring(1, str.length()-1)) );
    }

  public void initData(String[] arr) {
    int ind = 0;
    for (String str : arr) {
      str.concat(str + " " + ind);
      ind++;
    }
  }

  public void printData(String[] arr) {
    for (String str : arr) {
      System.out.println(str);
    }
  }
}
