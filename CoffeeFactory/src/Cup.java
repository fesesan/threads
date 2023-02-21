public class Cup extends Container {
   private static boolean getOne;
   public Cup(){
       if (!getOne){
           System.out.println("***** Pegando uma Xícara... ***** \n");
           getOne = true;
       } else {
           System.out.println("***** Pegando mais uma Xícara... ***** \n ");
       }
   }
}
