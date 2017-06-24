package com.wwssaadd.autopage;

import vdll.data.msql.MySql;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Hocean on 2017/5/5.
 */
public class Log {

    private JButton button1;
    public JPanel panel1;
    private JButton button2;
    MySql mySql = new MySql();

    private String sqlLog = "" +
            "delimiter $$tagEnd \n" +
            "drop procedure if exists actionUpdateJobLogvivesei;\n" +
            "create procedure actionUpdateJobLogvivesei()\n" +
            "begin\n" +
            "declare i int DEFAULT 0;\n" +
            "declare len int DEFAULT 0;\n" +
            "SELECT COUNT(*) INTO  len\n" +
            "FROM `tbi_job_log`\n" +
            "WHERE\n" +
            "tbi_job_log.user_name = '胡海洋'\n" +
            "AND  TO_SECONDS(create_date) - TO_SECONDS(DATE_FORMAT(job_date, '%Y-%m-%d  00:00:00')) >= 33 * 60 * 60\n" +
            "AND  job_date > '2017-05-01';  \n" +
            "while i < len do \n" +
            "UPDATE `tbi_job_log` SET `create_date`= DATE_FORMAT(job_date, CONCAT('%Y-%m-%d 17:',floor(rand() * 60),':',floor(rand() * 60)))\n" +
            "WHERE\n" +
            "tbi_job_log.user_name = '胡海洋'\n" +
            "AND  TO_SECONDS(create_date) - TO_SECONDS(DATE_FORMAT(job_date, '%Y-%m-%d  00:00:00')) >= 33 * 60 * 60\n" +
            "AND job_date > '2017-05-01'\n" +
            "LIMIT 1;\n" +
            "set i = i +1;\n" +
            "end while;\n" +
            "end $$tagEnd\n" +
            "delimiter ; \n" +
            "call actionUpdateJobLogvivesei();\n" +
            "drop procedure if exists actionUpdateJobLogvivesei;" +
            "";


    public Log() {
        mySql.open();
        Main.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mySql.close();
                super.windowClosing(e);
            }
        });


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}


/*

delimiter $$tagEnd              --  定义结束符为 $$
drop procedure if exists actionUpdateJobLogvivesei; --  删除 已有的 存储过程
create procedure actionUpdateJobLogvivesei()      -- 创建新的存储过程
begin

declare i int DEFAULT 0;
declare len int DEFAULT 0;

SELECT COUNT(*) INTO  len
FROM `tbi_job_log`
WHERE
tbi_job_log.user_name = '胡海洋'
AND  TO_SECONDS(create_date) - TO_SECONDS(DATE_FORMAT(job_date, '%Y-%m-%d  00:00:00')) >= 33 * 60 * 60
AND  job_date > '2017-05-01';

while i < len do  --  while
-- SELECT   CONCAT('%Y-%m-%d 17:',floor(rand() * 60),':',floor(rand() * 60));
-- 更新一个
UPDATE `tbi_job_log` SET `create_date`= DATE_FORMAT(job_date, CONCAT('%Y-%m-%d 17:',floor(rand() * 60),':',floor(rand() * 60)))
WHERE
tbi_job_log.user_name = '胡海洋'
AND  TO_SECONDS(create_date) - TO_SECONDS(DATE_FORMAT(job_date, '%Y-%m-%d  00:00:00')) >= 33 * 60 * 60
AND job_date > '2017-05-01'
LIMIT 1;

set i = i +1;
end while;      --  endwhile

end $$tagEnd
delimiter ;          --  结束定义语句
call actionUpdateJobLogvivesei();
drop procedure if exists actionUpdateJobLogvivesei; --  删除 已有的 存储过程
*/
