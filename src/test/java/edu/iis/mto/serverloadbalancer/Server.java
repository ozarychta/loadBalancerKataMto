package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final double MAX_LOAD = 100.0d;
    private double currentLoadPercentage;
    private int capacity;

    private List<Vm> vms = new ArrayList<>();

    public boolean contains(Vm theVm) {
        return vms.contains(theVm);
    }

    public Server(int capacity) {
        this.setCapacity(capacity);
    }

    public void addVm(Vm vm) {
        setCurrentLoadPercentage(getCurrentLoadPercentage() + loadOfVm(vm));
        this.vms.add(vm);
    }

    private double loadOfVm(Vm vm) {
        return (double) vm.getSize() / (double) getCapacity() * MAX_LOAD;
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return getCurrentLoadPercentage() + (loadOfVm(vm)) <= MAX_LOAD;
    }

    public double getCurrentLoadPercentage() {
        return currentLoadPercentage;
    }

    public void setCurrentLoadPercentage(double currentLoadPercentage) {
        this.currentLoadPercentage = currentLoadPercentage;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
