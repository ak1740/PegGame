package PegGame.main;

public class Move{
    //attributes from Location
    private Location from;
    private Location to;
    //constructor
    public Move(Location from, Location to) {
        this.from = from;
        this.to = to;
    }
    //getters
    public Location getFrom(){
        return from;
    }
    public Location getTo(){
        return to;
    }

}
