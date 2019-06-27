package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {

	public static final double MAX_LOAD = 100.0d;
	public double currentLoadPecentage;
	public int capacity;

	List<Vm> vms = new ArrayList<>();

	public Server(int capacity) {
		this.capacity = capacity;
	}

	public void addVm(Vm vm) {
		currentLoadPecentage += countVmLoad(vm);
		vms.add(vm);
	}

	private double countVmLoad(Vm vm) {
		return (double) vm.size / (double) capacity * MAX_LOAD;
	}

	public boolean contains(Vm vm) {
		return vms.contains(vm);
	}

	public int countVms() {
		return vms.size();
	}

	public boolean canFit(Vm vm) {

		return currentLoadPecentage + (countVmLoad(vm)) <= MAX_LOAD;
	}
}
