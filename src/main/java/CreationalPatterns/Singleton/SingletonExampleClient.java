package CreationalPatterns.Singleton;


final class GodSingleton {

    private static boolean instanceCreated = false;

    private static class LazyHolder {
        static final GodSingleton INSTANCE = new GodSingleton();
    }

    private GodSingleton() {
        if (instanceCreated) {
            throw new RuntimeException("Use getInstance() method to create.");
        }
        instanceCreated = true;
    }

    public static GodSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    // Prevent multiple instances from being created during deserialization
    private Object readResolve() {
        return getInstance();
    }

}


public class SingletonExampleClient {
    public static void main(String[] args) {
        GodSingleton godOfTheJews = GodSingleton.getInstance();
        GodSingleton godOfTheChristians = GodSingleton.getInstance();
        GodSingleton godOfTheMuslims = GodSingleton.getInstance();

        System.out.println(godOfTheJews);
        System.out.println(godOfTheChristians);
        System.out.println(godOfTheMuslims);

        if (godOfTheJews == godOfTheChristians && godOfTheJews == godOfTheMuslims && godOfTheChristians == godOfTheMuslims) {
            System.out.println("God is one!");
        } else {
            throw new RuntimeException("More than one god created!");
        }

    }
}
