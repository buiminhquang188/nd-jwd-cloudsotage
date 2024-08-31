package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES F WHERE F.userid = #{userId}")
    List<File> getFiles(int userId);

    @Select("SELECT * FROM FILES F WHERE F.fileId = #{fileId} AND F.userid = #{userId}")
    File getFile(Integer fileId, Integer userId);

    @Select("SELECT * FROM FILES F WHERE F.filename = #{fileName} AND F.userid = #{userId}")
    File getFileByFilename(String fileName, int userId);

    @Insert("INSERT INTO FILES(filename, contenttype, filesize, filedata, userid) VALUES (#{fileName}, #{contentType}, #{fileSize}, #{fileData}, #{userId})")
    int insert(File file);

    @Delete("DELETE FROM FILES F WHERE F.fileId = #{fileId}")
    int delete(Integer fileId);
}
