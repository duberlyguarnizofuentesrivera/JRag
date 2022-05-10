package components;

import java.util.HashSet;

public class Relation {
    private HashSet<Process> processes = new HashSet<>();
    private HashSet<Resource> resources = new HashSet<>();

    public HashSet<Process> getProcesses() {
        return processes;
    }

    public HashSet<Resource> getResources() {
        return resources;
    }

}
