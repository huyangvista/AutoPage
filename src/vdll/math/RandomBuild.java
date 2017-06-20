package vdll.math;

import vdll.debug.Console;

import java.util.Arrays;
import java.util.List;

/**
 * 2017-02-18 21:45:35
 * Created by Hocean on 2017/6/1.
 */
public class RandomBuild
{

	public RandomBuild()
	{
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int[] vi = randomNoRepeat(7,0,3,7);
		for (int i : vi)
		{
			Console.WriteLine(i);
		}
	}

	/**
	 * 交换 泛型列表随机
	 * @param vlist
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> randomSwap(List<T> vlist)
	{
	    for (int i = 0; i < vlist.size(); i++)
	    {
	        int vi1 = (int) (Math.random() * vlist.size());
	        int vi2 = (int) (Math.random() * vlist.size());
	        T vitem = vlist.get(vi1);
	        vlist.set(vi1, vlist.get(vi2));
	        vlist.set(vi2, vitem);
	    }
	    return vlist;
	}
	/**
	 * 交换
	 * @param count
	 * @return
	 */
	public static int[] randomSwap(int count){
		return randomSwap(count,0);
	}
	/**
	 * 交换
	 * @param count
	 * @param start
	 * @return
	 */
	public static int[] randomSwap(int count, int start)
	{
		return randomSwap(count,start,1);
	}
	/**
	 * 交换
	 * @param count
	 * @param start
	 * @param jump
	 * @return
	 */
	public static int[] randomSwap(int count, int start, int jump)
	{
		int[] arr = new int[count];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = start + jump * i;
		}
		return randomSwap(arr);
	}
	/**
	 *  交换
	 * @param count
	 * @param start
	 * @param jump
	 * @param len
     * @return
     */
	public static int[] randomSwap(int count, int start, int jump, int len)
	{
		int[] arr = randomSwap(len,start,jump);
		arr = Arrays.copyOf(arr,count);
		return randomSwap(arr);
	}
	/**
	 * 交换
	 * @param arr
	 * @return
	 */
	public static int[] randomSwap(int[] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			int i1 = (int) (Math.random() * arr.length);
			int i2 = (int) (Math.random() * arr.length);
			int item = arr[i1];
			arr[i1] = arr[i2];
			arr[i2] = item;
		}
		return arr;
	}

	/**
	 * 递归 不重复随机数组
	 * @param count
	 * @return
	 */
	public static int[] randomNoRepeat(int count){
		return randomNoRepeat(count,0,1);
	}
	/**
	 * 递归
	 * @param count
	 * @param start
	 * @return
	 */
	public static int[] randomNoRepeat(int count, int start){
		return randomNoRepeat(count,start,1);
	}
	/**
	 * 递归 不重复随机数组
	 * @param count 需要的数量
	 * @param start 开始值
	 * @param jump 步长
	 * @return
	 */
	public static int[] randomNoRepeat(int count, int start, int jump)
	{
		return randomNoRepeat(count,start,jump,count);
	}
	/**
	 * 递归 不重复随机数组
	 * @param count 需要的数量
	 * @param start 开始值
	 * @param jump 步长
	 * @param len 数据长度
     * @return
     */
	public static int[] randomNoRepeat(int count, int start, int jump, int len)
	{
		int[] arr = new int[count];  //结果数组
		int index = 0;  //完成序列
		while (true)
		{
			int ran = (int) (start + (int)(Math.random() * len) * jump);//Random.Range(0, viMax);  //随机数
			if(((ran - start) % jump) != 0) continue;
			int tag;       //检测完成序列
			for (tag = 0; tag < index; tag++) {
				if (arr[tag] == ran) break;  //存在相同检测
			}
			if (tag >= index) //是否存在相同
			{
				arr[index] = ran; //保存此数据
				if (++index >= count) break; //够了就退出了
			}
		}
		return arr;
	}

	/**
	 * 随机
	 * @param count
	 * @return
	 */
	public static int[] random(int count){
		return random(count,0,1);
	}
	/**
	 * 随机
	 * @param count
	 * @param start
	 * @return
	 */
	public static int[] random(int count, int start){
		return random(count,start,1);
	}
	/**
	 * 随机
	 * @param count
	 * @param start
	 * @param jump
     * @return
     */
	public static int[] random(int count, int start, int jump)
	{
		return random(count,start,jump,count);
	}
	/**
	 * 随机
	 * @param count
	 * @param start
	 * @param jump
	 * @param len
     * @return
     */
	public static int[] random(int count, int start, int jump, int len)
	{
		int[] arr = new int[count];  //结果数组
		int index = 0;  //完成序列
		while (true)
		{
			int ran = (int) (start + (int)(Math.random() * len) * jump);//Random.Range(0, viMax);  //随机数
			arr[index] = ran; //保存此数据
			if (++index >= count) break; //够了就退出了
		}
		return arr;
	}
}
