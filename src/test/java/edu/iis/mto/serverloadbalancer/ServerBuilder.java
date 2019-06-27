package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements Builder<Server>{

	private int capacity;
	private double initialLoad;

	public ServerBuilder withCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}

	public Server build() {
		Server server = new Server(capacity);

		addInitialLoad(server);

		return server;
	}

	private void addInitialLoad(Server server) {
		if(initialLoad>0.0d){
			int vmSize = (int) (initialLoad / Server.MAX_LOAD * (double) server.capacity);
			Vm vm = VmBuilder.vm().ofSize(vmSize).build();
			server.addVm(vm);
		}
	}

	public static ServerBuilder server() {
		return new ServerBuilder();
	}

	public ServerBuilder withCurrentLoad(double initialLoad) {
		this.initialLoad = initialLoad;
		return this;
	}
}
