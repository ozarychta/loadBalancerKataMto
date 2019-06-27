package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {

        for (Vm vm : vms){
            addToLessLoadedServer(servers, vm);
        }
    }

    private void addToLessLoadedServer(Server[] servers, Vm vm) {
        Server lessLoadedServer = findLessLoadedServer(servers);
        lessLoadedServer.addVm(vm);
    }

    private Server findLessLoadedServer(Server[] servers) {
        Server lessLoadedServer = null;
        for(Server server : servers){
            if(lessLoadedServer == null || lessLoadedServer.currentLoadPercentage > server.currentLoadPercentage){
                lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }
}
