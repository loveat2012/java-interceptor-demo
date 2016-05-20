/**
 * Client
 */
public class Client
{
    public static void main(String args[])
    {
        DynamicProxyHandler handler = new DynamicProxyHandler();
        BusinessInterface business = new BusinessClass();
        BusinessInterface businessProxy = (BusinessInterface) handler.bind(business);
        businessProxy.doSomething();
    }
}