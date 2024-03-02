/* Location Class 
 * Aliya, Leen, Mariam, Zoya
*/
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
    //constructor
    public Location(int row, int col){
        this.row = row;
        this.col = col;

    }

    @Override
    public String toString() {
    return "Row: " + this.row + " Column: " + this.col;
    }

    //equals method, checks if 2 locations on the board are equal
    @Override
    public boolean equals(Object obj) {
    Location location = (Location) obj;
    if(this.row == location.row && this.col == location.col){
        return true;
    }
    return false;
    }


}
