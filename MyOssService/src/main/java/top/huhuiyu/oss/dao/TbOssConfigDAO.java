package top.huhuiyu.oss.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import top.huhuiyu.oss.entity.TbAdmin;
import top.huhuiyu.oss.entity.TbOssConfig;

/**
 * TbOssConfig表的dao
 * 
 * @author 胡辉煜
 */
@Mapper
public interface TbOssConfigDAO {

  /**
   * 查询admin用户的TbOssConfig(不包含敏感字段)
   * 
   * @param admin
   *              用户信息
   * @return admin用户的TbOssConfig
   * @throws Exception
   *                   处理发生异常
   */
  List<TbOssConfig> queryByAdminNoInfo(TbAdmin admin) throws Exception;

  /**
   * 查询admin用户的TbOssConfig
   * 
   * @param admin
   *              用户信息
   * @return admin用户的TbOssConfig
   * @throws Exception
   *                   处理发生异常
   */
  List<TbOssConfig> queryByAdmin(TbAdmin admin) throws Exception;

  /**
   * 查询全部TbOssConfig
   *
   * @return TbOssConfig的信息
   * @throws Exception
   *                   处理发生异常
   */
  List<TbOssConfig> queryAll() throws Exception;

  /**
   * 按照主键查询TbOssConfig
   *
   * @param tbOssConfig主键信息
   * @return 主键查询TbOssConfig的结果
   * @throws Exception
   *                   处理发生异常
   */
  TbOssConfig queryByKey(TbOssConfig tbOssConfig) throws Exception;

  /**
   * 添加TbOssConfig信息
   *
   * @param tbOssConfig信息
   * @return 添加tbOssConfig信息的结果
   * @throws Exception
   *                   处理发生异常
   */
  int add(TbOssConfig tbOssConfig) throws Exception;

  /**
   * 修改TbOssConfig信息
   *
   * @param tbOssConfig信息
   * @return 修改tbOssConfig信息的结果
   * @throws Exception
   *                   处理发生异常
   */
  int update(TbOssConfig tbOssConfig) throws Exception;

  /**
   * 删除TbOssConfig信息
   *
   * @param tbOssConfig信息
   * @return 删除tbOssConfig信息的结果
   * @throws Exception
   *                   处理发生异常
   */
  int delete(TbOssConfig tbOssConfig) throws Exception;

}
