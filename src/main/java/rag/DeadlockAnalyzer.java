package rag;

import java.util.Arrays;

public class DeadlockAnalyzer {
    private String relationsData;
    private int numberOfResources;
    private int numberOfProcesses;
    private int[] instancesPerResource;
    private int[] availableVector;
    private int[][] allocationMatrix;
    private int[][] requestMatrix;
    private int[] workVector;
    private int[] finishVector;

    public DeadlockAnalyzer(String relationsData, String numberOfResources, String numberOfProcesses, String[] instancesPerResource) {
        this.relationsData = relationsData.toUpperCase();
        this.numberOfProcesses = Integer.parseInt(numberOfProcesses);
        this.numberOfResources = Integer.parseInt(numberOfResources);
        this.instancesPerResource = new int[instancesPerResource.length];
        for (int i = 0; i < instancesPerResource.length; i++) {
            this.instancesPerResource[i] = Integer.parseInt(instancesPerResource[i]);
        }
        availableVector = new int[this.numberOfResources];
        allocationMatrix = new int[this.numberOfProcesses][this.numberOfResources];
        requestMatrix = new int[this.numberOfProcesses][this.numberOfResources];
        workVector = new int[this.numberOfResources];
        finishVector = new int[this.numberOfProcesses];
    }

    /**
     * initialize the 3 matrices required for the algorithm
     *
     * @return true if the initialization was successful, false otherwise
     */
    public void initializeMatrices() {
        //available vector
        for (int i = 0; i < this.numberOfResources; i++) {
            availableVector[i] = this.instancesPerResource[i];
        }
        //allocation matrix
        String[] relations = relationsData.split("\n");
        for (String relation : relations) {
            String[] relationData = relation.split(" ");
            if (relationData[0].contains("P")) {
                //fill allocation matrix
                int resourceId;
                int instanceNumber;
                int processId = Integer.parseInt(relationData[0].substring(1)); //we get only the number
                String resourceString = relationData[1].substring(1);
                if (resourceString.contains(".")) {
                    //add to allocation matrix
                    resourceId = Integer.parseInt(resourceString.substring(0, resourceString.indexOf(".")));
                    instanceNumber = Integer.parseInt(resourceString.substring(resourceString.indexOf(".") + 1));
                    requestMatrix[processId-1][resourceId-1]++;

                } else {
                    resourceId = Integer.parseInt(resourceString);
                }
                allocationMatrix[processId-1][resourceId-1]++;

            } else if (relationData[0].contains("R")) {
                //fill request matrix
                String resourceString = relationData[0].substring(1);
                int processId = Integer.parseInt(relationData[1].substring(1));
                int resourceId;
                int instanceNumber;
                if (resourceString.contains(".")) {
                    //add to allocation matrix
                    resourceId = Integer.parseInt(resourceString.substring(0, resourceString.indexOf(".")));
                    instanceNumber = Integer.parseInt(resourceString.substring(resourceString.indexOf(".") + 1));
                } else {
                    resourceId = Integer.parseInt(resourceString);
                }
                allocationMatrix[processId-1][resourceId-1]++;
            } else {
                System.out.println("Error in relation data... different element than P or R: " + relationData[0] + " - " + relationData[1]);
            }
        }
        System.out.println("Allocation matrix: " + Arrays.deepToString(allocationMatrix));
        System.out.println("Request matrix: " + Arrays.deepToString(requestMatrix));
    }

    /**
     * check if the system is in deadlock
     *
     * @return true if the system is in deadlock, false otherwise
     */
    public boolean isDeadlock() {
        return false;
    }

    public int[] getAvailableVector() {
        return availableVector;
    }

    public int[][] getAllocationMatrix() {
        return allocationMatrix;
    }

    public int[][] getRequestMatrix() {
        return requestMatrix;
    }
}