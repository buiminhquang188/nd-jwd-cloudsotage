package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES")
    List<Note> getNotes();

    @Select("SELECT * FROM NOTES N WHERE N.noteid = #{id} AND N.userid = #{userId}")
    Note getNote(Integer id, Integer userId);

    @Insert("INSERT INTO NOTES(notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userId})")
    int insert(Note note);

    @Update("UPDATE NOTES N SET N.notetitle = #{noteTitle}, N.notedescription = #{noteDescription} WHERE N.noteid = #{id} AND N.userid = #{userId}")
    int update(Note note);

    @Delete("DELETE NOTES N WHERE N.noteid = #{id} AND N.userId = #{userId}")
    int delete(int id, int userId);
}
