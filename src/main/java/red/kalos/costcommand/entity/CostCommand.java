package red.kalos.costcommand.entity;

public class CostCommand {
    private String command;
    private int coolDown;
    private int number;
    private double cost;

    public CostCommand(String command, int coolDown, int number, double cost) {
        this.command = command;
        this.coolDown = coolDown;
        this.number = number;
        this.cost = cost;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
