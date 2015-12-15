package br.com.omniplusoft.gateway.domain.ctiplatform.exampleimpl;

import br.com.omniplusoft.gateway.domain.ctiplatform.CTIResponse;
import br.com.omniplusoft.gateway.domain.ctiplatform.CallbackDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by hermeswaldemarin on 11/12/15.
 */
@Service
@Profile("example")
public class OmniCTIExampleListener {

    @Autowired
    CallbackDispatcher eventDispatcher;


    //@Scheduled(fixedDelay=10000)
    public void sendEvent() {

        eventDispatcher.dispatch(new CTIResponse("ring", 0, "ringing...", null));

    }

}


