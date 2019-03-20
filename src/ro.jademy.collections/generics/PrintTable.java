package generics;

import java.util.*;

public class PrintTable {
    //Create a data structure that maps an Excel table
    //Use different types for each column
    //Format the columns depending on their type and print them
    //Find and print a cell value by column name and row number
    //O sa avem String, Number, Boolean, Date
    public static void main(String[] args) {

        Map<String, List<?>> table = new TreeMap<> ();

        table.put ("Employee Number", Arrays.asList (1, 2, 3, 4));

        List<String> names = new ArrayList<> ();
        names.add ("Gigeleeeeeeeeeeeeeeeeeeeeeeeeee");
        names.add ("Ion");
        names.add ("Andrei");
        names.add ("Anton");

        table.put ("Names", names);

        List<Date> dates = new ArrayList<> ();
        Calendar calendar = Calendar.getInstance ();
        calendar.set (2019, 03, 10);//setam variabila calendar
        dates.add (calendar.getTime ());//din variabila de tip calendar extragem data
        calendar.set (2018, 07, 22);
        dates.add (calendar.getTime ());
        calendar.set (2018, 02, 25);
        dates.add (calendar.getTime ());
        calendar.set (2016, 9, 14);
        dates.add (calendar.getTime ());

        table.put ("Hire Date ", dates);

        List<Integer> salary = new ArrayList<> ();
        salary.add (1000);
        salary.add (2000);
        salary.add (1500);
        salary.add (3000);

        table.put ("Salary", salary);
        printTable (table);

        //getMaxWidthValues (table);

    }

    public static void printTable(Map<String, List<?>> table) {

        int maxLength = getMaxLength (table.values ());
        Map<String, Integer> paddingMap = getMaxWidthValues (table);

        System.out.print ("|");
        for (String s : table.keySet ()) {
            int padding = paddingMap.get (s);
            if (padding > 0) {
                System.out.printf ("%" + padding + "s|", s.toUpperCase ());
            } else {
                System.out.print (s.toUpperCase () + "|");
            }
        }
        System.out.println ();

        for (int i = 0; i < maxLength; i++) { // cycle through every line

            System.out.print ("|");
            for (Map.Entry<String, List<?>> entry : table.entrySet ()) {
                String key = entry.getKey ();
                List<?> val = entry.getValue ();
                if (val.size () > i) {
                    // format the content of the current cell
                    // TODO: format the content based on the content type
                    // ex: if the content is of type Date, show it like '2018-04-03'
                    Object cell = val.get (i);

                    int padding = paddingMap.get (key);

                    if (padding > 0) {
                        if (cell instanceof Integer) {
                            System.out.printf ("%" + padding + "d|", cell);
                        } else if (cell instanceof Date) {
                            System.out.printf ("%" + padding + "s|", String.format ("%tF", cell));
                        } else if (cell instanceof String) {
                            System.out.printf ("%-" + padding + "s|", cell);
                        }
                    } else {
                        if (cell instanceof Integer) {
                            System.out.printf ("%d|", cell);
                        } else if (cell instanceof Date) {
                            System.out.printf ("%s|", String.format ("%tF", cell));
                        } else if (cell instanceof String) {
                            System.out.printf ("%-s|", cell);
                        }
                    }
                }
            }
            System.out.println ("");
        }
    }


    public static int getMaxLength(Collection<List<?>> columns) {
        int maxLength = 0;
        for (List<?> val : columns) {
            if (val.size () > maxLength) {
                maxLength = val.size ();
            }
        }
        return maxLength;
    }

    public static Map<String, Integer> getMaxWidthValues(Map<String, List<?>> table) {
        Map<String, Integer> maxMap = new HashMap<> ();
        for (Map.Entry<String, List<?>> entry : table.entrySet ()) {
            int max = entry.getKey ().length ();
            for (Object o : entry.getValue ()) {
                if (o instanceof Date) {
                    if (max < String.format ("%tF", o).length ()) {
                        max = String.format ("%tF", o).length ();
                    }
                } else {
                    if (max < o.toString ().length ()) {
                        max = o.toString ().length ();
                    }
                }
            }

            maxMap.put (entry.getKey (), max);
        }

        return maxMap;
    }
}
