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
        this.iteratorOfTasks =  listOfTasks.iterator();
        this.iteratorOfJobs = listOfJobs.iterator();
    }

    public void reset(){
        this.listOfTasks.clear();
        this.listOfJobs.clear();
    }

    public void read_job(ArrayList<Double> listOfTasks){
        timeCounter += latency;
        listOfJobs.add(listOfTasks);
    }

    public double execute(){
        if(iteratorOfTasks.hasNext()){
            double nextTask = iteratorOfTasks.next();
            timeCounter += nextTask;
            return timeCounter;
        } else if(iteratorOfJobs.hasNext()){
            this.listOfTasks = new ArrayList<>(iteratorOfJobs.next());
            if(iteratorOfTasks.hasNext()){
                double nextTask = iteratorOfTasks.next();
                timeCounter += latency + nextTask;
                return timeCounter;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    public double next_idle(){
        double result = 0;
        try{ while(true){
                result += execute();
        }
        } catch(NoSuchElementException e){}
        return result;
    }
}
