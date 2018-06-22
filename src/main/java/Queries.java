import jdk.nashorn.internal.runtime.ParserException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Queries
{


    private static Map<Integer,Integer> yearVS_regUsers=new HashMap<>();

    public static double getMeanAge(List<Record> ipRecordList)
    {
        return 0.0;
    }




    public static Map<Integer,Integer> getUsersRegisteredEachYear(List<Record> ipRecordList)
    {
//        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        parsedDate = dateFormatter.parse(r.getRegistered()).getDate();
        for (Record r : ipRecordList)
        {
            int parsedDate = Integer.parseInt(r.getRegistered().substring(0,r.getRegistered().indexOf("-")));
            yearVS_regUsers.put(parsedDate,yearVS_regUsers.getOrDefault(parsedDate,0)+1);
        }
        System.out.println(yearVS_regUsers);
        return yearVS_regUsers;
    }




}
