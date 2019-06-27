package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements  Builder<Server>{
    private int capacity;
    private double initialLoad;

    public ServerBuilder withCapacity(int i) {
        this.capacity = i;
        return this;
    }

    public Server build() {
        Server server = new Server(capacity);
        addInitialLoad(server);
        return server;
    }

    private void addInitialLoad(Server server) {
        if(initialLoad>0){
            int initialVmSize = (int)((double)initialLoad / (double)capacity * Server.MAX_LOAD);
            Vm initialVm = VmBuilder.vm().ofSize(initialVmSize).build();
            server.addVm(initialVm);
        }
    }

    public static ServerBuilder server(){
        return new ServerBuilder();
    }

    public ServerBuilder withCurrentLoadOf(double initialLoad) {
        this.initialLoad = initialLoad;
        return this;
    }
}
