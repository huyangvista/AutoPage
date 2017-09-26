//package vdll.data.dbc;
//
//import com.mongodb.MongoClient;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.gridfs.GridFS;
//import com.mongodb.gridfs.GridFSDBFile;
//import com.mongodb.gridfs.GridFSFile;
//import com.mongodb.gridfs.GridFSInputFile;
//import org.bson.Document;
//import org.bson.types.ObjectId;
//
//import java.io.File;
//import java.io.FileInputStream;
//
///**
// * Created by Hocean on 2017/6/26.
// */
//public class MongoDB implements IDB {
//    public static void main(String args[]) {
//
//        // 连接到 mongodb 服务
//        MongoClient mongoClient = new MongoClient("localhost", 27017);
//        try {
//
//            // 连接到数据库
//            MongoDatabase mongoDatabase = mongoClient.getDatabase("td");
//            System.out.println("Connect to database successfully");
//
//            MongoCollection<Document> collection = mongoDatabase.getCollection("test");
//            System.out.println("集合 test 选择成功" + collection.count());
//            FindIterable<Document> documents = collection.find();
//            for (Document document : documents) {
//                System.out.println(document);
//
//            }
//
//            GridFS gridFS = new GridFS(mongoClient.getDB("td"), "fs");
//            GridFSInputFile gridFSInputFile = gridFS.createFile(new File("src/sql.txt"));
//            //gridFSInputFile.setId("57172b0f657f8bbb34d70113");
//            gridFSInputFile.setFilename("sql.txt");
//            //gridFSInputFile.setChunkSize();
//            //gridFSInputFile.setContentType();
//            //gridFSInputFile.setMetaData();
//            gridFSInputFile.save();
//
//            //输出文件
//           // GridFSDBFile file = gridFS.findOne("sql.txt");
//            //file.writeTo(new File("src/sql101010.txt"));
//
//            //删除文件
//            GridFSDBFile del = gridFS.findOne("sql.txt");
//           // gridFS.remove(new ObjectId( del.getId().toString()));
//            //gridFS.remove("sql.txt");
//
//
//
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//        } finally {
//            mongoClient.close();
//        }
//    }
//}