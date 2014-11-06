package com.stream.it.ss.hibernate.dao;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.SearchBean;
import com.stream.it.ss.utils.jdbc.*;

//import java.math.BigDecimal;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class InquiryDAOImpl extends JdbcDaoSupport implements InquiryDAO{
    private static final Log logger = LogFactory.getLog(InquiryDAOImpl.class);
    private String sql;
    private String classMapping;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
   	@Override
    public List listAll()throws Exception{
    	List resultList  = getJdbcTemplate().query(sql , new BeanPropertyRowMapper(Class.forName(classMapping)));
    	logger.info("result size:"+resultList.size());
    	
    	return resultList;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public List listAll(SearchBean searchBean) throws Exception {
		List param = searchBean.getSqlParameter();
        String sqlCondition = JDBCSQLSearchConditionHelper.getSQLSearchCondition(searchBean.getConditionValuesBean());
        String orderBy = JDBCSQLSearchConditionHelper.getSQLOrderBy(searchBean);
        String groupBy = JDBCSQLSearchConditionHelper.getSQLGroupBy(searchBean);
        
        String sqlStr = sql+sqlCondition+groupBy+orderBy;
        logger.info("sql.."+sqlStr);
        logger.info("param.."+param);
        
        /*** MYSQL EXCUTE SQL PAGGING****/
        sqlStr = "SELECT @ROW_NUMNER:=@ROW_NUMNER+1 AS NO,QUERY1.* FROM ("+sqlStr+")QUERY1, (SELECT @ROW_NUMNER:=0)QUERY2";
        
        List resultList  = getJdbcTemplate().query(sqlStr, param.toArray() , new BeanPropertyRowMapper(Class.forName(classMapping)));
        
        logger.info("result size:"+resultList.size());
        
        return resultList;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List listByPage(final SearchBean searchBean) throws Exception {
		List resultList = new ArrayList();
		List param = searchBean.getSqlParameter();
		
        String sqlCondition = JDBCSQLSearchConditionHelper.getSQLSearchCondition(searchBean.getConditionValuesBean());
        String orderBy = JDBCSQLSearchConditionHelper.getSQLOrderBy(searchBean);
        String groupBy = JDBCSQLSearchConditionHelper.getSQLGroupBy(searchBean);

        String sqlStr = sql+sqlCondition+groupBy+orderBy;
        logger.info("sqlStr.."+sqlStr);
        logger.info("param.."+param);
        
        sqlStr = sqlStr.replaceAll("\n", "").replaceAll("\t", "");
        String sqlCount = "select count(*) from("+sqlStr+")as sub_query";
        
		int pageNo = searchBean.getPaggingBean().getPageNo();
		final int rowsPerPage = searchBean.getPaggingBean().getRowsPerPage();		
		final int totalRow = getJdbcTemplate().queryForInt(sqlCount, param.toArray());
        		
        int totalPage = totalRow / rowsPerPage;
		
        if (totalRow > rowsPerPage * totalPage){
        	totalPage++;
        }
        
        if(pageNo>totalPage)pageNo = 1;
        
        final int startRow = (pageNo - 1) * rowsPerPage;


        
        /*** MYSQL EXCUTE SQL PAGGING****/
        sqlStr = "SELECT @ROW_NUMNER:=@ROW_NUMNER+1 AS NO,QUERY1.* FROM ("+sqlStr+" LIMIT "+rowsPerPage+" OFFSET "+startRow+")QUERY1, (SELECT @ROW_NUMNER:="+startRow+")QUERY2";
        resultList  = getJdbcTemplate().query(sqlStr, param.toArray() , new BeanPropertyRowMapper(Class.forName(classMapping)));
        
        /*** DB2 EXCUTE SQL SPRING PAGGING****
        getJdbcTemplate().query(
        		sqlStr,        
        		param.toArray(),        		
                new ResultSetExtractor<Object>() {
                    public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
                        int currentRow = 0;
                        
                        while (rs.next() && currentRow < startRow + rowsPerPage) {
                            if (currentRow >= startRow) {
                            	try {
                            		resultList.add(new BeanPropertyRowMapper(Class.forName(classMapping)).mapRow(rs, currentRow));
								}catch (ClassNotFoundException e) {
									e.printStackTrace();
								}
                            }
                            currentRow++;
                        }     
                        
                        return resultList;
                    }
                }
        );
        */
        
        //*** set variable to class call ****//        
        searchBean.getPaggingBean().setPageNo(pageNo);
        searchBean.getPaggingBean().setRowsPerPage(rowsPerPage);
        searchBean.getPaggingBean().setTotalPage(totalPage);
        searchBean.getPaggingBean().setTotalRow(totalRow);
		
        logger.info("result size:"+resultList.size());
        
		return resultList;
	}

    @Override
    public DataBo find(SearchBean searchBean) throws Exception {

        return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Long sum(SearchBean searchBean) throws Exception {
		List param = searchBean.getSqlParameter();
        String sqlCondition = JDBCSQLSearchConditionHelper.getSQLSearchCondition(searchBean.getConditionValuesBean());
        String orderBy = JDBCSQLSearchConditionHelper.getSQLOrderBy(searchBean);
        String groupBy = JDBCSQLSearchConditionHelper.getSQLGroupBy(searchBean);
        
        String sqlStr = sql+sqlCondition+groupBy+orderBy;
        logger.info("sql.."+sql+sqlCondition+groupBy+orderBy);
        logger.info("param.."+param);

        Long result = getJdbcTemplate().queryForLong(sqlStr, param.toArray() , new BeanPropertyRowMapper(Class.forName(classMapping)));

        return result;
    }
    
    //********** setter,getter **************//
    public void setClassMapping(String classMapping) {
        this.classMapping = classMapping;
    }
    public void setSql(String sql) {
        this.sql = sql;
    }
}

