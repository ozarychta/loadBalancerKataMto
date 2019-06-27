package edu.iis.mto.serverloadbalancer;

public class Server {

    private static final double MAX_LOAD = 100.0d;
    public double currentLoadPercentage;
    public int capacity;

    public boolean contains(Vm theVm) {

        return true;
    }

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public void addVm(Vm vm) {
        currentLoadPercentage = (double)vm.size / (double) capacity * MAX_LOAD;
    }
}
