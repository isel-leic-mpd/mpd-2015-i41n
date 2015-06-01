import java.util.function.*;


class A {void a(){} } 
class B extends A {void b(){} }
class C extends B {void c(){} }


class App{
    static C Foo(B b) { return null; }
    static Object Bar(B b) { return null; }
    
    static B Dummy(Object o) { return null; }
    static B Sam(C c) { c.c(); return null; }
    
    static void main(){
    
        // Covariancia
        //
        Function<B, B> h1 = App::Foo; // aceita tipos derivados do tipo de retorno
        // Function<B, B> h2 = App::Bar; // erro de compilação: tipo de retorno incompatível
        
        h1.apply(null).a();
        h1.apply(null).b();
        
        // Contravariancia
        //
        Function<B, B> h3 = App::Dummy; // aceita tipos base do tipo do argumento
        // Function<B, B> h4 = App::Sam;  // erro de compilação: C não e tipo base
        
        // h3.apply(new A()); // Erro de compilação -- A não é B
        h3.apply(new B()); // OK
        
    }
}
