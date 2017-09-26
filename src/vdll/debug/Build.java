package vdll.debug;//package vdll.debug;
//
//import vdll.utils.ReflectUtil;
//
////import javax.tools.*;
//import java.io.File;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
///**
// * Created by Hocean on 2017/6/6.
// */
//
//
//public class Build {
//    public static void main(String[] args) throws Exception {
//
//        Build build = new Build();
//        build.build("Hello","public class Hello{public String sayHello (String name){     return \"Hello,\" + name + \"!\";}}", "src/build" );
//        build.build("Hello","public class Hello{\n" +
//                "    public String sayHello (String name){\n" +
//                "                    com.wwssaadd.autopage.server.Command comm = new com.wwssaadd.autopage.server.Command();\n" +
//                "                    comm.setActionData(\"u\");\n" +
//                "                    comm.setDbURL(\"jdbc:mysql://vives.cc:3306/vives?useUnicode=true&characterEncoding=utf8\");\n" +
//                "                    comm.setUsername(\"root\");\n" +
//                "                    comm.setPassword(\"hoceanvista\");\n" +
//                "                    comm.setSql(\"SELECT * FROM `t_user`;\");\n" +
//                "                    comm.setSql(\"UPDATE `t_user` SET `user_phone`='888' WHERE (`id`='3');\");\n" +
//                "        return \"Hello,\" + name + \"!\" + comm.getSql();\n" +
//                "    }\n" +
//                "}", "src/build" );
//
//        Class<?> cls = build.load("src/build", "Hello");
//
//        Object hello = ReflectUtil.newInstance(cls);
//        Object invoke = ReflectUtil.invokeMethodAll(hello, "sayHello", "000" );
//        System.out.println(invoke);
//
//
//    }
//
//    public boolean build(String name, String code, String path){
//        //Java 源代码
//        String sourceStr = code ; // "public class Hello{public String sayHello (String name){     return \"Hello,\" + name + \"!\";}}";
//        // 类名及文件名
//        String clsName = name;  //"Hello";
//        // 方法名
//        //String methodName = "sayHello";
//        // 当前编译器
//        JavaCompiler cmp = ToolProvider.getSystemJavaCompiler();
//        //Java 标准文件管理器
//        StandardJavaFileManager fm = cmp.getStandardFileManager(null, null, null);
//        //Java 文件对象
//        JavaFileObject jfo = new StringJavaObject(clsName, sourceStr);
//        // 编译参数，类似于javac <options> 中的options
//        List<String> optionsList = new ArrayList<String>();
//        // 编译文件的存放地方，注意：此处是为Eclipse 工具特设的
//        /////optionsList.addAll(Arrays.asList("-d", "./bin"));
//        optionsList.addAll(Arrays.asList("-d", path));
//        // 要编译的单元
//        // List<JavaFileObject> jfos = Arrays.asList(jfo); //过时
//        List<JavaFileObject> jfos = Collections.singletonList(jfo);
//        // 设置编译环境
//        JavaCompiler.CompilationTask task = cmp.getTask(null, fm, null, optionsList, null, jfos);
//        // 编译成功
//        return task.call();
//    }
//
//    public Class<?> load(String path, String className){
//        try {
//            ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
//            // This URL for a directory will be searched *recursively*
//            //URL classes = new URL("file:///D:/MyScript/");
//            ClassLoader custom = new URLClassLoader(new URL[]{new File(path).toURL()}, systemClassLoader);
//            // this class should be loaded from your directory
//            Class<?> clazz = custom.loadClass(className);
////            Method[] methods = clazz.getDeclaredMethods();
////            for (Method method : methods) {
////                System.out.println(method.getName());
////            }
//            return clazz;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//
//}
//
//// 文本中的Java 对象
//class StringJavaObject extends SimpleJavaFileObject {
//    // 源代码
//    private String content = "";
//
//    // 遵循Java 规范的类名及文件
//    public StringJavaObject(String _javaFileName, String _content) {
//        super(_createStringJavaObjectUri(_javaFileName), Kind.SOURCE);
//        content = _content;
//    }
//
//    // 产生一个URL 资源路径
//    private static URI _createStringJavaObjectUri(String name) {
//        // 注意此处没有设置包名
//        return URI.create("String:///" + name + Kind.SOURCE.extension);
//    }
//
//    // 文本文件代码
//    @Override
//    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
//        return content;
//    }
//}
//
