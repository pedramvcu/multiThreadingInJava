/*
Pedram Maleki CMSC 403
Professor: Zack Whitten

FoodConsumer will subtract a random number of foods
to the bank
*
* */
public class FoodConsumer extends Thread{
    //instance var
    FoodBank bank;
    //parameterized constructor will initialize the bank var
    public FoodConsumer(FoodBank newFoodBank){
        this.bank=newFoodBank;
    }
    //overriding the run method
    //calling the takeFood method which will subtract food from the bank
    @Override
    public void run(){
        double randNum;
        try {
            while(true){
                //subtracting a random number of food from bank and sleeping
                randNum=Math.random()*100+1;
                bank.takeFood((int)randNum);
                Thread.sleep(100);
            }
        }catch (InterruptedException ex){}
    }

}
