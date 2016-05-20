import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * DynamicProxyHandler
 */
public class DynamicProxyHandler implements InvocationHandler
{
    private Object business; // 被代理对象

    private InterceptorClass interceptor = new InterceptorClass(); // 拦截器

    /**
     * return a proxy instance and bind class by proxy and proxy processor
     *
     * @param business
     * @return business object
     */
    public Object bind(Object business)
    {
        this.business = business;
        return Proxy.newProxyInstance(
                // class by proxy ClassLoader
                business.getClass().getClassLoader(),

                // interface by proxy
                business.getClass().getInterfaces(),

                // invocation handler
                this
        );
    }

    /**
     * Processes a method invocation on a proxy instance and returns the result
     *
     * @param proxy object
     * @param method process method by proxy
     * @param args process method arguments by proxy
     * @return return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Object result;
        interceptor.before();
        result = method.invoke(business, args);
        interceptor.after();
        return result;
    }
}