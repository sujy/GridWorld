import info.gridworld.actor.Bug;

public class DancingBug extends Bug
{
    private int steps;
    private int[] dance;
    private int count;
    private int sideLength;

    public DancingBug(int[] danceArr)
    {
        count = 0;
        steps = 0;
        sideLength = 6;
        //init dance
        int length = danceArr.length;
        dance = new int[length];
        System.arraycopy(danceArr, 0, dance, 0, danceArr.length);
    }
    
    public void act()
    {
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        } else {
            for (int i = 0; i < dance[count]; i++){
                turn();
            }
            if (count < dance.length - 1){
                count += 1;
            } else {
                count = 0;
            }
            steps = 0;
        }
    }
}
