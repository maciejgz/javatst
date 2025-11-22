package pl.mg.javatst.clone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Vap implements Cloneable {

    private String name;
    private int id;
    private List<VapSource> vapSources;

    public Vap(String name, int id, List<VapSource> vapSources) {
        this.name = name;
        this.id = id;
        this.vapSources = vapSources;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<VapSource> vapSourceCloned = new ArrayList<>();
        if (vapSources != null) {
            for (VapSource vapSource : vapSources) {
                vapSourceCloned.add((VapSource) vapSource.clone());
            }
        }
        Vap cloned = new Vap(name, id, vapSourceCloned);
        return cloned;
    }

    @Override
    public String toString() {
        return "Vap{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", vapSources=" + vapSources +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<VapSource> getVapSources() {
        return vapSources;
    }

    public void setVapSources(List<VapSource> vapSources) {
        this.vapSources = vapSources;
    }
}
