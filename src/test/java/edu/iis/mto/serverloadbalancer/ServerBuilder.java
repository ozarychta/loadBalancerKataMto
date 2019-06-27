package edu.iis.mto.serverloadbalancer;

public class ServerBuilder implements  Builder<Server>{
    private int capacity;

    public ServerBuilder withCapacity(int i) {
        this.capacity = i;
        return this;
    }

    public Server build() {
        return new Server(capacity);
    }

    public static ServerBuilder server(){
        return new ServerBuilder();
    }
}
