package demo;

import vdll.data.msql.AISql;

public class t_user{

 @AISql public String id;
 @AISql public String user_name;
 @AISql public String user_phone;
 @AISql public String user_email;
 @AISql public String user_pwd;
 @AISql public String pwd_salt;
 @AISql public String create_time;
 @AISql public String modify_time;
 @AISql public String is_delete;

}
