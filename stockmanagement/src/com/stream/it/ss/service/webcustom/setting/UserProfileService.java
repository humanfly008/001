package com.stream.it.ss.service.webcustom.setting;

import java.util.List;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;

public interface UserProfileService {
	public DataBo findUserProfile(String id, String userProfileId)throws Exception;
    public List listTransaction(SearchBean searchBean)throws Exception;
    public ResultBO createUserProfile(DataBo dataBo)throws Exception;
    public ResultBO updateUserProfile(DataBo dataBo)throws Exception;
    public ResultBO deleteUserProfile(String[] userId)throws Exception;
    public ResultBO verifyUserId(String userId)throws Exception;
}
