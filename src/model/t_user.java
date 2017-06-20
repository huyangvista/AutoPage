package model;

import vdll.data.msql.AISql;

public class t_user {

 @AISql public String id;
 @AISql public String user_name;
 @AISql public String user_phone;
 @AISql public String user_email;
 @AISql public String user_pwd;
 @AISql public String pwd_salt;
 @AISql public String create_time;

 public String getId() {
  return id;
 }

 public void setId(String id) {
  this.id = id;
 }

 public String getUser_name() {
  return user_name;
 }

 public void setUser_name(String user_name) {
  this.user_name = user_name;
 }

 public String getUser_phone() {
  return user_phone;
 }

 public void setUser_phone(String user_phone) {
  this.user_phone = user_phone;
 }

 public String getUser_email() {
  return user_email;
 }

 public void setUser_email(String user_email) {
  this.user_email = user_email;
 }

 public String getUser_pwd() {
  return user_pwd;
 }

 public void setUser_pwd(String user_pwd) {
  this.user_pwd = user_pwd;
 }

 public String getPwd_salt() {
  return pwd_salt;
 }

 public void setPwd_salt(String pwd_salt) {
  this.pwd_salt = pwd_salt;
 }

 public String getCreate_time() {
  return create_time;
 }

 public void setCreate_time(String create_time) {
  this.create_time = create_time;
 }

 public String getModify_time() {
  return modify_time;
 }

 public void setModify_time(String modify_time) {
  this.modify_time = modify_time;
 }

 public String getIs_delete() {
  return is_delete;
 }

 public void setIs_delete(String is_delete) {
  this.is_delete = is_delete;
 }

 @AISql public String modify_time;
 @AISql public String is_delete;

}
