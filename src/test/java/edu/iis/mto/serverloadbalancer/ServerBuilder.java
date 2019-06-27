package edu.iis.mto.serverloadbalancer;

public class ServerBuilder {
    private int capacity;

    public ServerBuilder withCapacity(int i) {
        this.capacity = i;
        return this;
    }

    public Server build() {
        return new Server();
    }
}
