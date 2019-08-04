public class Test{
    String loc;
    static boolean rainy;

    public static boolean getWeather(){
        rainy =true;
        return rainy;
    }
    
    public static void main(String ar[]){
        if(getWeather()==true){
            System.out.print("warning make sure to bring the umbrella");
        }
    }
}