package vdll.core;

import java.io.Serializable;

/**
 * Created by Hocean on 2017/3/24.
 */

public interface Delegate extends Serializable {
    interface Actions<T> extends BaseAction {
        //void invoke(T... t);
    }

    interface Action extends BaseAction {
        void invoke();
    }

    interface Action1<T> extends BaseAction {
        void invoke(T t);
    }

    interface Action2<T0, T1> extends BaseAction {
        void invoke(T0 t0, T1 t1);
    }

    interface Action3<T0, T1, T2> extends BaseAction {
        void invoke(T0 t0, T1 t1, T2 t2);
    }

    interface Action4<T0, T1, T2, T3> extends BaseAction {
        void invoke(T0 t0, T1 t1, T2 t2, T3 t3);
    }

    interface Action5<T0, T1, T2, T3, T4> extends BaseAction {
        void invoke(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4);
    }

    interface Action6<T0, T1, T2, T3, T4, T5> extends BaseAction {
        void invoke(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
    }

    interface Action7<T0, T1, T2, T3, T4, T5, T6> extends BaseAction {
        void invoke(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6);
    }

    interface Action8<T0, T1, T2, T3, T4, T5, T6, T7> extends BaseAction {
        void invoke(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7);
    }

    interface Action9<T0, T1, T2, T3, T4, T5, T6, T7, T8> extends BaseAction {
        void invoke(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8);
    }

    interface Func<R> extends BaseFunc {
        R invoke();
    }

    interface Func1<T, R> extends BaseFunc {
        R invoke(T t);
    }

    interface Func2<T0, T1, R> extends BaseFunc {
        R invoke(T0 t0, T1 t1);
    }

    interface Func3<T0, T1, T2, R> extends BaseFunc {
        R invoke(T0 t0, T1 t1, T2 t2);
    }

    interface Func4<T0, T1, T2, T3, R> extends BaseFunc {
        R invoke(T0 t0, T1 t1, T2 t2, T3 t3);
    }

    interface Func5<T0, T1, T2, T3, T4, R> extends BaseFunc {
        R invoke(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4);
    }

    interface Func6<T0, T1, T2, T3, T4, T5, R> extends BaseFunc {
        R invoke(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
    }

    interface Func7<T0, T1, T2, T3, T4, T5, T6, R> extends BaseFunc {
        R invoke(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6);
    }

    interface Func8<T0, T1, T2, T3, T4, T5, T6, T7, R> extends BaseFunc {
        R invoke(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7);
    }

    interface Func9<T0, T1, T2, T3, T4, T5, T6, T7, T8, R> extends BaseFunc {
        R invoke(T0 t0, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8);
    }

    interface Funcs<T, R> extends BaseFunc {
        R invoke(T... t);
    }

}
