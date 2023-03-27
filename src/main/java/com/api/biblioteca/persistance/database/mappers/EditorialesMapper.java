package com.api.biblioteca.persistance.database.mappers;

import com.api.biblioteca.models.Editoriales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EditorialesMapper {

    @Select("SELECT * FROM EDITORIALES WHERE ID=#{editorialesId}")
    Editoriales getEditorial(int editorialesId);
}
