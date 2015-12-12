package br.com.omniplusoft.gateway.domain.ctiplatform;

import br.com.omniplusoft.gateway.infrastructure.exception.LoginException;

import java.util.Map;

/**
 * Created by hermeswaldemarin on 10/12/15.
 */
public interface OmniPlusoftCTI {

    //public void login(String userName, String password, Map<String,String> ctiArguments) throws LoginException;

    public void login(Map<String,String> ctiArguments) throws LoginException;

    public void logout(Map<String,String> ctiArguments) throws LoginException;

    public void becomeAvailable(Map<String,String> ctiArguments) throws LoginException;

    public void becomeUnavailable(Map<String,String> ctiArguments) throws LoginException;

    public void makeCall(Map<String,String> ctiArguments) throws LoginException;

    public void answer(Map<String,String> ctiArguments) throws LoginException;

    public void dropCall(Map<String,String> ctiArguments) throws LoginException;

    public void hold(Map<String,String> ctiArguments) throws LoginException;

    public void unHold(Map<String,String> ctiArguments) throws LoginException;

    public void consult(Map<String,String> ctiArguments) throws LoginException;

    public void cancelConsult(Map<String,String> ctiArguments) throws LoginException;

    public void transfer(Map<String,String> ctiArguments) throws LoginException;

    public void conference(Map<String,String> ctiArguments) throws LoginException;

    public void genericEvent(Map<String,String> ctiArguments) throws LoginException;


}
