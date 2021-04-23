/*
Pedram Maleki CMSC 403
Professor: Zack Whitten

FoodBankPatron has a main method and will stat the threads
*
* */
public class FoodBankPatron {
    public static void main(String []args){
            //Creating FoodBank, FoodProducer, and FoodConsumer objects
            FoodBank foodBank = new FoodBank();
            FoodProducer foodProducer = new FoodProducer(foodBank);
            FoodConsumer foodConsumer = new FoodConsumer(foodBank);
            //Starting the threads
            foodProducer.start();
            foodConsumer.start();
    }


}
