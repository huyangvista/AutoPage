package vdll.core;

/**
 * Created by Hocean on 2017/3/24.
 */
public interface Funcs<R, T> extends BaseFunc{
    R invoke(T... t);
}
