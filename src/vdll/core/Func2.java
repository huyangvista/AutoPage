package vdll.core;

/**
 * Created by Hocean on 2017/3/24.
 */
public interface Func2<R, T0, T1> extends BaseFunc{
    R invoke(T0 t0, T1 t1);
}
