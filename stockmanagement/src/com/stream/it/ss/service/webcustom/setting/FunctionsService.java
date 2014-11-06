package com.stream.it.ss.service.webcustom.setting;

import java.util.List;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;

public interface FunctionsService {
	public List listFunctions(SearchBean searchBean) throws Exception;
	public List findFunctionsInMapping(List funcId) throws Exception;
	public List findFunctionsNotInMapping(List funcId) throws Exception;
    public ResultBO createFunctions(DataBo dataBo)throws Exception;
    public ResultBO updateFunctions(DataBo dataBo)throws Exception;
    public ResultBO deleteFunctions(String FunctionsId)throws Exception;
    public List listAllFunctions() throws Exception;
    public DataBo findFunctions(String funcId)throws Exception;
    public String findMaxFnId() throws Exception;
    public String findFnUrlbyName(String fnName) throws Exception;
    public boolean findFnName(String functionName) throws Exception;
}
