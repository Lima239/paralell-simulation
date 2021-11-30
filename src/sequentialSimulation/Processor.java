package sequentialSimulation;

import java.util.*;

public class Processor {
    private boolean idle;
    private double timeCounter;
    private final int latency;
    private ArrayList<Double> listOfTasks;
    private ArrayList<ArrayList<Double>> listOfJobs;
    private ArrayList<Double> historyOfTasks;
    private ArrayList<ArrayList<Double>> historyOfJobs;
    private Iterator<Double> iteratorOfTasks;
    private Iterator<ArrayList<Double>> iteratorOfJobs;

    public Processor(int latency){
        this.idle = false;
        this.timeCounter = 0;
        this.latency = latency;
        this.listOfTasks = new ArrayList<>();
        this.listOfJobs = new ArrayList<>();
        this.historyOfTasks = new ArrayList<>();
        this.historyOfJobs = new ArrayList<>();
        this.iteratorOfTasks =  listOfTasks.iterator();
        this.iteratorOfJobs = listOfJobs.iterator();
    }

    public void reset(){
        this.listOfTasks.clear();
        this.listOfJobs.clear();
        this.historyOfTasks.clear();
        this.historyOfJobs.clear();
    }

    public void read_job(ArrayList<Double> listOfTasks){
        listOfJobs.add(listOfTasks);
    }

    public double execute(){
        if(iteratorOfTasks.hasNext()){
            double nextTask = iteratorOfTasks.next();
            historyOfTasks.add(nextTask);
            timeCounter += latency + nextTask;
            return timeCounter;
        } else if(iteratorOfJobs.hasNext()){
            this.historyOfJobs.add(this.listOfTasks);
            this.historyOfTasks = new ArrayList<>();
            this.listOfTasks = new ArrayList<>(iteratorOfJobs.next());
            if(iteratorOfTasks.hasNext()){
                double nextTask = iteratorOfTasks.next();
                historyOfTasks.add(nextTask);
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

    public ArrayList HistoryOfTasks(){
        return this.historyOfTasks;
    }

}
