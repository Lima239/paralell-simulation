package sequentialSimulation;

import java.util.*;

public class Processor {
    private double timeCounter;
    private final double latency;
    private ArrayList<Double> listOfTasks;
    private ArrayList<ArrayList<Double>> listOfJobs;
    private Iterator<Double> iteratorOfTasks;
    private Iterator<ArrayList<Double>> iteratorOfJobs;

    public Processor(double latency){
        this.timeCounter = 0;
        this.latency = latency;
        this.listOfTasks = new ArrayList<>();
        this.listOfJobs = new ArrayList<>();
    }

    public void reset(){
        this.listOfTasks.clear();
        this.listOfJobs.clear();
    }

    public void read_job(ArrayList<Double> listOfTasks){
        if (listOfJobs.size() == 0) iteratorOfTasks =  listOfTasks.iterator();
        listOfJobs.add(listOfTasks);
        iteratorOfJobs = listOfJobs.iterator();
    }

    public double execute(){
        if(iteratorOfTasks.hasNext()){
            double nextTask = iteratorOfTasks.next();
            timeCounter += nextTask;
            return timeCounter;
        } else if(iteratorOfJobs.hasNext()){
            this.listOfTasks = new ArrayList<>(iteratorOfJobs.next());
            iteratorOfTasks =  listOfTasks.iterator();
            if(iteratorOfTasks.hasNext()) {
                double nextTask = iteratorOfTasks.next();
                timeCounter += latency + nextTask;
                return timeCounter;
            }
        }
        return 0;
    }

    public double next_idle(){
        double result = timeCounter;
        while(execute() != 0) {
            result += execute();
        }
        return result;
    }
}
