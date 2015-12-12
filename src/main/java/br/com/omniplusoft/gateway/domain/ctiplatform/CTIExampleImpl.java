package br.com.omniplusoft.gateway.domain.ctiplatform;

import br.com.omniplusoft.gateway.infrastructure.exception.LoginException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by hermeswaldemarin on 11/12/15.
 */
@Service
@Profile("example")
public class CTIExampleImpl implements OmniPlusoftCTI {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private boolean available = false;
    private boolean speaking = false;

    @Autowired
    EventDispatcher eventDispatcher;

    @Override
    public void login(Map<String, String> ctiArguments) throws LoginException {
        logger.info("login called");
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Login OK", Collections.unmodifiableMap(Stream.of(
                new AbstractMap.SimpleEntry<>("arg1", "one"),
                new AbstractMap.SimpleEntry<>("arg2", "two"))
                .collect(Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue())))));
    }

    @Override
    public void logout(Map<String, String> ctiArguments) throws LoginException {
        logger.info("logout called");
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Logout OK", null));
    }

    @Override
    public void becomeAvailable(Map<String, String> ctiArguments) throws LoginException {
        logger.info("becomeAvailable called");
        available = true;
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Now you are available.", null));
    }

    @Override
    public void becomeUnavailable(Map<String, String> ctiArguments) throws LoginException {
        logger.info("becomeUnavailable called");
        available = false;
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Now you are unavailable.", null));
    }

    @Override
    public void makeCall(Map<String, String> ctiArguments) throws LoginException {
        logger.info("makeCall called");
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Call complete.", null));
    }

    @Override
    public void answer(Map<String, String> ctiArguments) throws LoginException {
        logger.info("answer called");
        speaking = true;
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Speaking....", null));
    }

    @Override
    public void dropCall(Map<String, String> ctiArguments) throws LoginException {
        logger.info("dropCall called");
        speaking = false;
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Call dropped.", null));
    }

    @Override
    public void hold(Map<String, String> ctiArguments) throws LoginException {
        logger.info("hold called");
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Holding...", null));
    }

    @Override
    public void unHold(Map<String, String> ctiArguments) throws LoginException {
        logger.info("unHold called");
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Unhold call.", null));
    }

    @Override
    public void consult(Map<String, String> ctiArguments) throws LoginException {
        logger.info("consult called");
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Consult completed.", null));
    }

    @Override
    public void cancelConsult(Map<String, String> ctiArguments) throws LoginException {
        logger.info("cancelConsult called");
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Consult canceled.", null));
    }

    @Override
    public void transfer(Map<String, String> ctiArguments) throws LoginException {
        logger.info("transfer called");
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Transfer Completed.", null));
    }

    @Override
    public void conference(Map<String, String> ctiArguments) throws LoginException {
        logger.info("conference called");
        eventDispatcher.dispatch(new CTIEvent("status", 0, "Conference Completed.", null));
    }

    @Override
    public void genericEvent(Map<String, String> ctiArguments) throws LoginException {
        logger.info("genericEvent called");
        eventDispatcher.dispatch(new CTIEvent("status", 0, "genericEvent called.", null));
    }

    @Scheduled(fixedDelay=10000)
    public void sendEvent() {
        if(available && !speaking) {
            eventDispatcher.dispatch(new CTIEvent("ring", 0, "ringing...", null));
        }
    }

}


