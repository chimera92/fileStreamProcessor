import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Queries
{


    private static Map<Integer,Integer> yearVS_regUsers=new HashMap<>();
    private static Map<Integer,Integer> allAgeCounts=new TreeMap<>();
    private static int totalPeopleCount=0;

    //Still to be tested
    public static double getMeanAge(List<Record> ipRecordList)
    {
        for (Record r : ipRecordList)
        {
            totalPeopleCount++;
            allAgeCounts.put(r.getAge(), allAgeCounts.getOrDefault(r.getAge(), 0) + 1);
        }

        int countPointer=0;
        int medianPos=totalPeopleCount/2;
        for(int k:allAgeCounts.keySet())
        {
            countPointer+=allAgeCounts.get(k);
            if(countPointer>medianPos)
            {
                System.out.println("The median age is: "+k);
                return k;
            }
        }

        return -1;
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
//        System.out.println(yearVS_regUsers);
        return yearVS_regUsers;
    }




}
