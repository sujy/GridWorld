import info.gridworld.actor.Bug;

public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int isfinsh;
    private int turns;

    public ZBug(int length)
    {
        steps = 0;
        isfinsh = 0;
        turns = 0;
        sideLength = length;
        turn();
        turn();
    }
    
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
            if(isfinsh > 1 || steps < sideLength){
                return;         
            } else if(isfinsh == 0){
                turns = 3;
                for (int i = 0; i < turns; i++){
                    turn();
                }
                isfinsh += 1;
                steps = 0;
            } else if(isfinsh == 1){
                turns = 5;
                for (int i = 0; i < turns; i++){
                    turn();
                }
                steps = 0;
                isfinsh += 1;
                }
         }
    }
}
