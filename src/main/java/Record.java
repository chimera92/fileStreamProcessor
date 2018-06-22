import java.util.Date;
import java.util.List;
import java.util.Map;

class Record
{

    private String guid;
    private boolean isActive;
    private String balance;
    private int age;
    private String eyeColor;
    private String name;
    private String gender;
    private String email;
    private String phone;
    private String address;
    private String registered;
    private List<Map<String,String>> friends;
    private String greeting;
    private String favoriteFruit;

    public String getGuid()
    {
        return guid;
    }

    public boolean isActive()
    {
        return isActive;
    }

    public String getBalance()
    {
        return balance;
    }

    public int getAge()
    {
        return age;
    }

    public String getEyeColor()
    {
        return eyeColor;
    }

    public String getName()
    {
        return name;
    }

    public String getGender()
    {
        return gender;
    }

    public List getFriends()
    {
        return friends;
    }


    public String getRegistered()
    {
        return registered;
    }
}
