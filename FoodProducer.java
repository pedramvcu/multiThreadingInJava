/*
Pedram Maleki CMSC 403
Professor: Zack Whitten

FoodProducer will add a random number of foods
to the bank
*
* */

public class FoodProducer extends Thread{
    //Intance var
    FoodBank bank;
    //constructor with a single parameter
    public FoodProducer(FoodBank newFoodBank){
        this.bank=newFoodBank;
    }
    //overriding the run method to add values to the bank
    @Override
    public void run(){
        double randNum;
        try {
            while(true){
                //producing a random number
                randNum=Math.random()*100+1;
                //calling the give method and casting to int
                bank.giveFood((int)randNum);
                Thread.sleep(100);
            }
        }catch (InterruptedException ex){}
    }
}
