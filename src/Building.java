public class Building {

    private double left;
    private double right;
    private double height;

    /**
     * Represents a building object.
     * @param l Left side of the building.
     * @param r Right side of the building.
     * @param h Height of the building.
     */
    public Building(double l, double r, double h){
        this.left = l;
        this.right = r;
        this.height = h;
    }

    public double getLeft() { return left; }

    public double getRight() {
        return right;
    }

    public double getHeight() {
        return height;
    }
}
