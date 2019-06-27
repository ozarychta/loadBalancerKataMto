package edu.iis.mto.serverloadbalancer;

public class Vm {
    private int size;

    public Vm(int size) {
        this.setSize(size);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
