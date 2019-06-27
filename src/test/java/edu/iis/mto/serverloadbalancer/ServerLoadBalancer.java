package edu.iis.mto.serverloadbalancer;

public class ServerLoadBalancer {
    public void balance(Server[] servers, Vm[] vms) {
        
        for (Vm vm : vms){
            servers[0].addVm(vms[0]);
        }
    }
}
