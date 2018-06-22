import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.NaN;

class Queries
{

    private static Map<Integer,Integer> yearVS_regUserCount =new TreeMap<>();
    private static Map<Integer,Integer> allAgeVsCounts =new TreeMap<>();
    private static Map<Integer,Integer> numberOfFriendsVsCounts=new TreeMap<>();
    private static int totalPeopleCountForMedianAge=0;
    private static int totalPeopleCountForMeanBalance=0;
    private static int totalFriendsAllUsers=0;
    private static double totalBalanceAllUsers= 0.0;
    private static int totalActiveFemaleUnreadMessages=0;
    private static int totalActiveFemaleUsers=0;

    public static int getMedianAge(List<Record> ipRecordList)
    {
        for (Record r : ipRecordList)
        {
            totalPeopleCountForMedianAge++;
            allAgeVsCounts.put(r.getAge(), allAgeVsCounts.getOrDefault(r.getAge(), 0) + 1);
        }

        int countPointer=0;
        int medianPos=totalPeopleCountForMedianAge/2;
        for(int k: allAgeVsCounts.keySet())
        {
            countPointer+= allAgeVsCounts.get(k);
            if(countPointer>medianPos)
            {
                System.out.println("The median age is: "+k);
                return k;
            }
        }

        return -1;
    }




    public static int getMedianNoOfFriends(List<Record> ipRecordList)
    {
        for (Record r : ipRecordList)
        {
            totalFriendsAllUsers++;
            numberOfFriendsVsCounts.put(r.getFriends().size(), numberOfFriendsVsCounts.getOrDefault(r.getFriends().size(), 0) + 1);
        }
        int countPointer=0;
        int medianPos=totalFriendsAllUsers/2;
        for(int k: numberOfFriendsVsCounts.keySet())
        {
            countPointer+= numberOfFriendsVsCounts.get(k);
            if(countPointer>medianPos)
            {
                System.out.println("The median no of friends is: "+k);
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
            yearVS_regUserCount.put(parsedDate, yearVS_regUserCount.getOrDefault(parsedDate,0)+1);
        }
        System.out.println("Users registered in each Year: "+yearVS_regUserCount);
        return yearVS_regUserCount;
    }

    public static Double getMeanBalanceAmount(List<Record> ipRecordList)
    {
        NumberFormat format = NumberFormat.getCurrencyInstance();

        for (Record r : ipRecordList)
        {
            try
            {
                totalBalanceAllUsers+=format.parse(r.getBalance()).doubleValue();
                totalPeopleCountForMeanBalance++;
            } catch (ParseException e)
            {
                e.printStackTrace();
            }
        }

        double meanBalance=totalBalanceAllUsers/totalPeopleCountForMeanBalance;
        System.out.println("Mean Balance Amount: "+meanBalance);

        return meanBalance;
    }


    public static Double getMeanUnreadMsgsForActiveFemales(List<Record> ipRecordList)
    {

        Pattern p = Pattern.compile("have\\s*(\\d+)\\s*unread\\s*messages");
        Matcher m;
        for (Record r : ipRecordList)
        {
            if(r.getGender().toLowerCase().equals("female") && r.isActive())
            {
                m = p.matcher(r.getGreeting());
                m.find();
                totalActiveFemaleUnreadMessages+=Integer.parseInt(m.group(1));
                totalActiveFemaleUsers++;
            }
        }
        double meanUnreadMsgsForActiveFemales = NaN;
        if(totalActiveFemaleUsers>0) meanUnreadMsgsForActiveFemales=totalActiveFemaleUnreadMessages/totalActiveFemaleUsers;
        System.out.println("Mean for number of Unread messages for Active females: "+meanUnreadMsgsForActiveFemales);
        return meanUnreadMsgsForActiveFemales;
    }


}
