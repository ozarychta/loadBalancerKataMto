package edu.iis.mto.serverloadbalancer;

public class Server {

    public double currentLoadPercentage;
    public int capacity;

    public boolean contains(Vm theVm) {

        return true;
    }

    public Server(int capacity) {
        this.capacity = capacity;
    }
}
