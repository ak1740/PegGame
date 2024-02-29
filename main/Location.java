package PegGame.main;

public class Location {
    //private attributes
    private int row;
    private int col;
    //getters
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    //Location method
    public Location(int row, int col){
        this.row = row;
        this.col = col;

    }
    
    @Override
    public String toString() {
    return "Row: " + this.row + "\nColumn: " + this.col;
    }

    @Override
    public boolean equals(Object obj) {
    Location location = (Location) obj;
    if(this.row == location.row && this.col == location.col){
        return true;
    }
    return false;
    }

}
