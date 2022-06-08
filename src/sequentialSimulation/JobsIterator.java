package sequentialSimulation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class JobsIterator implements Iterable<ArrayList<Double>> {
    private ArrayList<ArrayList<Double>> jobs;

    public JobsIterator(ArrayList<ArrayList<Double>> jobs){
        this.jobs = jobs;
    }

    public void addJob(ArrayList<Double> job){
        jobs.add(job);
    }

    @Override
    public Iterator iterator() {
        Iterator<ArrayList<Double>> it = new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < jobs.size();
            }

            @Override
            public ArrayList<Double> next() {
                if (this.hasNext()) {
                    int tmpIndex = index;
                    index++;
                    return jobs.get(tmpIndex);
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
        return it;
    }
}
