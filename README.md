# EventClip
EventClip - all event library in one place


In Application.onCreate
```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EventClip.registerProvider(new EventClipLogProvider("tests"));
        EventClip.registerProvider(new EventClipSystemProvider());
        EventClip.registerProvider(new SelectiveProvider("selective"));
        EventClip.registerProvider(new MyTestProvider());
    }
}
```

Or create own provider
```java
public class MyTestProvider extends EventClipLogProvider {

    public MyTestProvider() {
        // constructors
    }

    @Override
    public void deliver(EventParam param) {
        // do something with data
    }

    @Override
    public void userProperty(@NonNull UserParam param) {
        // update user property
    }

    @Override
    public void flush() {
        // flush data
    }

}
```

Use providers, deliver events:
```java
EventClip.deliver(new EventParam("Event 1").field("Action1", 1));
EventClip.deliver(new EventParam("Event 2").field("Action2", "Value"));
...
EventClip.userProperty(new UserParam("aaa", "123").property("MyProperty", "Mike"));
```