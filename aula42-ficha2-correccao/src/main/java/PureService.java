/**
 * Created by mcarvalho on 15-06-2015.
 */


interface Subscriber<T> {
    void onComplete(T elem);
    void onException(Exception elem);
}

class PureService<T>{
    public void addSubscriber(Subscriber<T> s) { }

    public void start(){ }
}

class Program<T> {

    void main(){

        PureService<T> serv1 = new PureService<T>();
        serv1.addSubscriber(new Subscriber<T>() {
            public void onComplete(T elem) { System.out.println("Succeeded with " + elem); }
            public void onException(Exception elem) { System.out.println("bhaaaa"); }
        });
        serv1.start();

        ServiceWrapper<T> serv = new ServiceWrapper<>(new PureService<>());
        serv.addSubscriber(
                elem -> System.out.println("Succeeded with " + elem),
                excep -> System.out.println("bhaaaa")
        );

        serv
                .onComplete(elem -> System.out.println("Succeeded with " + elem))
                .onException(excep -> System.out.println("bhaaaa"));
        serv.start();

    }
}