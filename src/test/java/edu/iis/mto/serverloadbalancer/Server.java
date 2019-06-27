package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final double MAX_LOAD = 100.0d;
    public double currentLoadPercentage;
    public int capacity;

    private List<Vm> vms = new ArrayList<>();

    public boolean contains(Vm theVm) {
        return vms.contains(theVm);
    }

    public Server(int capacity) {
        this.capacity = capacity;
    }

    public void addVm(Vm vm) {
        currentLoadPercentage += loadOfVm(vm);
        this.vms.add(vm);
    }

    private double loadOfVm(Vm vm) {
        return (double) vm.size / (double) capacity * MAX_LOAD;
    }

    public int countVms() {
        return vms.size();
    }

    public boolean canFit(Vm vm) {
        return currentLoadPercentage + (loadOfVm(vm)) <= MAX_LOAD;
    }
}
