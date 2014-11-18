package de.lukasniemeier.gamecenterlivesender.utils;

/**
 * Created by Hugo on 14.11.2014.
 */
public class Functional {

    public interface Procedure {
        void execute();
    }

    public interface Consumer<T> {
        void accept(T t);
    }

    public interface BiConsumer<R, S> {
        void accept(R r, S s);
    }

    public interface Supplier<T> {
        T get();
    }

    public interface Function<T, R> {
        R apply(T t);
    }

    public interface Predicate<T> {
        boolean test(T t);
    }
}
