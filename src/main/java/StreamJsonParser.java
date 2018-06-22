import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


class StreamJsonParser
{
        private JsonReader reader;
        private Gson gson;
        private int batchSize;
        List<Record> records;
        List<Function<List<Record>,?>> queryList;
        private int processedCount=0;


    public StreamJsonParser(int batchSize,String inputURI) throws IOException
        {
            this.batchSize = batchSize;
            reader = new JsonReader(new InputStreamReader(new URL(inputURI).openConnection().getInputStream(), "UTF-8"));
            gson = new GsonBuilder().create();
            queryList = new ArrayList<>();
        }

        public void start() throws IOException
        {
            reader.beginArray();
            records = new ArrayList<>(batchSize);

            while (reader.hasNext())
            {
                records.add(gson.fromJson(reader, Record.class));
                processedCount++;

                if(processedCount==batchSize)
                {
                    executeQueriesN_clearBuffer();
                }
            }
            if(processedCount>0)
                executeQueriesN_clearBuffer();
            reader.close();
        }

    private void executeQueriesN_clearBuffer()
    {
        System.out.println("\nSummary: ");
        for(Function<List<Record>,?> lambaFunc:queryList)
        {
            lambaFunc.apply(records);
        }
        records.clear();
        processedCount=0;
    }

    public void addQuery(Function<List<Record>,?> lambaFunc)
    {
        queryList.add(lambaFunc);
    }

}





