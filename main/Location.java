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
    
    //Location constructor
    public Location(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    @Override
    public String toString() {
        return "r" + this.row + "c" + this.col;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) 
            return true;
        if (obj == null || getClass() != obj.getClass()) 
            return false;
        Location location = (Location) obj; 
        return row == location.row && col == location.col;
    }
}
