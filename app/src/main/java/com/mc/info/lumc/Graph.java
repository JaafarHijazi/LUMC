package com.mc.info.lumc;

import android.graphics.Color;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph extends AppCompatActivity {

    //examTypesInList is a hashmap used to sort each examination result in a list of its own
    private Map < Examination.examType,List < Examination > > examTypesInList;
    //examTypeLineGraphs is the hashmap containing the lines for the graph
    private Map < Examination.examType,Map < String ,LineGraphSeries<DataPoint> > >  examTypeLineGraphs;
    //list of the Examination types for this patient
    private List< Examination.examType > listOfExamTypes;
    private GraphView graph;
    private Patient patient;

    //TODO optional delete if you want or use it to fix data
    private Map < Examination.examType,Map < String , Boolean > > mapOfChoices;

    Calendar c = new GregorianCalendar(1990,0,1);
    Date minDate = new Date(), maxDate = c.getTime();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            patient = (Patient) extras.getSerializable("take");
        }
        graph = (GraphView) findViewById(R.id.graph);
        DBHandler db = DBHandler.getInstance();
        listOfExamTypes = new ArrayList<>();
        List<Examination> examinations = db.getExaminations(patient) ;
//        List<Examination> examinations = db.getExaminations(db)) ;
        examTypesInList = new HashMap<>();
        for(Examination exam : examinations){
            //iterate over each examination done by the patient and look in the map if the type exists
            // then add the exam to list present for the key, otherwise create a new entry with key and new list
            if ( !examTypesInList.containsKey( exam.getType() ) ){
                //if it is not present create a new list of examinations
                List<Examination> L = new ArrayList<>();
                //add this exam to the list
                L.add(exam);
                //place the exam with its type in the map
                examTypesInList.put(exam.getType(),L);
                //add the exam type to the listOfExamTypes so that we know what the types are that we have
                listOfExamTypes.add(exam.getType());
            }
            else{
                //if we already have this type in the map then we only need to add it directly at the right entry
                examTypesInList.get(exam.getType()).add(exam);
            }
        }
        //this is needed to cast from the string type date to Date
        DateFormat formatter = new SimpleDateFormat("dd/MMM/yy");
        //We will make the data structure as follows: Map < examType , Map < MedicalDataName , LinGraphSeries >  >
        //so each set of lines will be in an entry of the main map, and each line from a set of lines will be
        //accessed by the name of the medical data name from the map inside
        examTypeLineGraphs = new HashMap<>();
        List<LineGraphSeries<DataPoint>> graphLines = new ArrayList<>();
        for (Map.Entry entry : examTypesInList.entrySet() ){
            Examination.examType type = (Examination.examType) entry.getKey();
            List<Examination> l= (List<Examination>)entry.getValue();
            Map<String,LineGraphSeries<DataPoint>> linesMap = new HashMap<>();
            examTypeLineGraphs.put(type,linesMap);
            //creating the map if it is not there yet
            for (Examination examination : l) {
                for ( MedicalData medicalData : examination.getMedicalData()){
                    try {
                        Date exDate = formatter.parse(examination.getDate());
                        //check minimum date and maximum date to fix the line axis for the graph
                        if(exDate.compareTo(minDate)<0){
                            minDate = (Date)exDate.clone();
                        }
                        if(exDate.compareTo(maxDate)>0){
                            maxDate = (Date) exDate.clone();
                        }
                        if(linesMap.containsKey(medicalData.getName()))
                            linesMap.get(medicalData.getName()).appendData( new DataPoint( exDate ,Double.parseDouble(medicalData.getValue()) ),true,15);
                        else{
                            DataPoint dataPoint = new DataPoint( exDate ,Double.parseDouble(medicalData.getValue()) );
                            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                                    dataPoint
                            });
                            series.setDrawDataPoints(true);
                            series.setDataPointsRadius(10);
                            linesMap.put(medicalData.getName(),series);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //this puts all lines in the graph
        for(Map.Entry entry : examTypeLineGraphs.entrySet()) {
            Map < String ,LineGraphSeries<DataPoint> > mapStringToLines = (Map) entry.getValue();
            for (Map.Entry lineEntry : mapStringToLines.entrySet())
                graph.addSeries( ((LineGraphSeries<DataPoint>)lineEntry.getValue()) );
        }
        redrawGraph();

        //setting the X-axis in the graph to fit all points
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(4);
        graph.getViewport().setMinX(minDate.getTime());

        if(maxDate.compareTo(minDate)==0) {
            Calendar c1 = Calendar.getInstance();
            c1.setTime(minDate);
            c1.add(Calendar.DAY_OF_MONTH,1);
            maxDate=c1.getTime();
        }
        graph.getViewport().setMaxX(maxDate.getTime());
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getGridLabelRenderer().setHumanRounding(false);

        //TODO make a way to add some radio buttons and posssibly some checkboxes for each exam and data

        //TODO also add on click effects for each one to add it to the graph with the function below
    }

    //this method is used and envoked when you want to redraw the graph
    private void redrawGraph(){
        graph.removeAllSeries();
        //this puts all lines in the graph
        //TODO make this function select the lines you need only and add them
        for(Map.Entry entry : examTypeLineGraphs.entrySet()) {
            Map < String ,LineGraphSeries<DataPoint> > mapStringToLines = (Map) entry.getValue();
            for (Map.Entry lineEntry : mapStringToLines.entrySet())
            //Todo if( selected in the buttons )
                graph.addSeries( ((LineGraphSeries<DataPoint>)lineEntry.getValue()) );
        }
    }
}
