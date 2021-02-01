package ie.tudublin;

public class Cat extends Animal
{
    private int numLives;

    public Cat(String name)
    {
        super(name);
        this.numLives = 9;
    }

    // Need a getter method since the value is private
    public int getNumLives()
    {
        return numLives;
    }

    public void kill()
    {
        if(numLives > 0)
        {
            numLives = numLives - 1;
            System.out.println("Ouch!");
        }

        if(numLives == 0)
        {
            System.out.println("Dead");
        }
    }
    
}