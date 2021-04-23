/*
Pedram Maleki CMSC 403
Professor: Zack Whitten

FoodBank class has a food amount and methods to give and take food
*
* */

public class FoodBank {
    //instance var of the class
    int food;
    //no param constructor will initialize food to 0
    public FoodBank(){
        this.food=0;
    }
    //This method is synchronized and will add food to the bank
    synchronized public void giveFood(int moreFood){
        this.food=this.food+moreFood;
        System.out.println("Adding "+moreFood+" items of food, the balance is now "+food+" items");
        //when food is added this will notify the other thread
        notify();
    }

    synchronized public void takeFood(int lessFood){
        //we check to see if food is 0 or we are
        //taking more food than we have
        while(food==0 || food<lessFood){
            try{
                System.out.println("waiting to get food");
                //if yes, we will wait for the other thread to add food
                wait();
            }catch (InterruptedException e){}
        }
        this.food=this.food-lessFood;
        System.out.println("Taking "+lessFood+" items of food, the balance is now "+food+" items");


    }

}
