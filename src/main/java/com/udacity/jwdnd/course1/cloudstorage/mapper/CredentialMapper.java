package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("SELECT * FROM CREDENTIALS C")
    List<Credential> getCredentials();

    @Select("SELECT * FROM CREDENTIALS C WHERE C.credentialid = #{id} AND C.userid = #{userId}")
    Credential getCredential(Integer id, Integer userId);

    @Insert("INSERT INTO CREDENTIALS(url, username, key, password, userId) VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
    int insert(Credential credential);

    @Update("UPDATE CREDENTIALS C SET N.url = #{url}, N.username = #{username}, N.key = #{key}, N.password = #{password}, N.userid = #{userId}")
    int update(Credential credential);

    @Delete("DELETE CREDENTIALS C WHERE C.id = #{id} AND C.userid = #{userId}")
    int delete(int id, int userId);
}
