import java.io.IOException;

class StreamProcessorMain
{

    public static void main(String[] args) throws IOException
    {
        int batchSize=1000;
        String ipURI = "file:///home/chimera/Desktop/aisera/ip.json";
        StreamJsonParser streamProcessorInstance = new StreamJsonParser(batchSize,ipURI);
        streamProcessorInstance.addQuery(Queries::getUsersRegisteredEachYear);
        streamProcessorInstance.addQuery(Queries::getMeanAge);
        streamProcessorInstance.start();
    }

}
