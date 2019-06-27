package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

public class ServerLoadBalancer {

    public void balance(Server[] servers, Vm[] vms) {

        for (Vm vm : vms) {
            addToLessLoadedServer(servers, vm);
        }

    }

    private void addToLessLoadedServer(Server[] servers, Vm vm) {
        List<Server> capableServers = findCapableServers(servers, vm);

        Server lessLoadedServer = extractLessLoadedServer(capableServers);
        if(lessLoadedServer != null){
            lessLoadedServer.addVm(vm);
        }
    }

    private List<Server> findCapableServers(Server[] servers, Vm vm) {
        List<Server> capableServers = new ArrayList<>();
        for(Server server: servers){
            if(server.canFit(vm)){
                capableServers.add(server);
            }
        }
        return capableServers;
    }

    private Server extractLessLoadedServer(List<Server> servers) {
        Server lessLoadedServer = null;

        for (Server server : servers) {
            if (lessLoadedServer == null || lessLoadedServer.currentLoadPecentage > server.currentLoadPecentage) {
                    lessLoadedServer = server;
            }
        }
        return lessLoadedServer;
    }

}
