package vdll;

import org.xutils.DbManager;
import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.RandomAccess;

class Main
{
	public Main() throws DbException, FileNotFoundException {
		RandomAccessFile rf = new RandomAccessFile("","rw");


		//DbManager db = x.getDb(null);
		//List<VMode> all = db.selector(VMode.class).findAll();
	}
}

@Table(name = "VMode")
public class VMode
{
	@Column(name = "a")
	private String a = "";
	@Column(name = "b")
	private double b = 10;

	public static void main(String[] args){
		//new Main();
		throw new RuntimeException("服务器请求异常！");
	}

	public void setA(String a) {

	}
}
