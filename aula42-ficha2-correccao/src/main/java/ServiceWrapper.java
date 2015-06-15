import java.util.function.Consumer;

/**
 * Created by mcarvalho on 15-06-2015.
 */
public class ServiceWrapper<T> {
    PureService<T> src;
    public ServiceWrapper(PureService<T> src) {
        this.src = src;
    }

    public void addSubscriber(Consumer<T> success, Consumer<Exception> excep) {
        src.addSubscriber(new Subscriber<T>() {
            @Override
            public void onComplete(T elem) {
                success.accept(elem);
            }

            @Override
            public void onException(Exception elem) {
                excep.accept(elem);
            }
        });

    }

    public void start() {
        src.start();
    }

    public SubscriberOnExceptionBuilder<T> onComplete(Consumer<T> succec) {
        this.addSubscriber(succec, e -> {
        });
        return new SubscriberOnExceptionBuilder(this);
    }

    public void onException(Consumer<Exception> excep) {
        this.addSubscriber(e -> {
        }, excep);
    }

}

class SubscriberOnExceptionBuilder<T>{

    private ServiceWrapper wrapper;

    public SubscriberOnExceptionBuilder(ServiceWrapper<T> wrapper) {
        this.wrapper = wrapper;
    }

    public void onException(Consumer<Exception> excep) {
        wrapper.addSubscriber(e -> {
        }, excep);
    }
}