package hello.dao;

import common.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by kevinmonster on 16/10/12.
 */
@Repository
@Mapper
public interface UserDao extends MyMapper{
}
