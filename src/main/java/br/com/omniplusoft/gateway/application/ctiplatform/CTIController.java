package br.com.omniplusoft.gateway.application.ctiplatform;

import br.com.omniplusoft.gateway.domain.ctiplatform.CTIEvent;
import br.com.omniplusoft.gateway.domain.ctiplatform.MainActionExecution;
import br.com.omniplusoft.gateway.domain.ctiplatform.OmniPlusoftCTI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by hermeswaldemarin on 10/12/15.
 */
@RestController
public class CTIController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageSendingOperations<String> messagingTemplate;

    @Autowired(required = false)
    private OmniPlusoftCTI omniPlusoftCTI;


    @MessageMapping("/cti/command")
    public void executeCommand(CTIEvent event) {

        try {
            new MainActionExecution(OmniPlusoftCTI.class.getDeclaredMethod(event.getEventName(), Map.class), Map.class).execute(event.getArguments(), omniPlusoftCTI);
        } catch (NoSuchMethodException e) {
            try {
                new MainActionExecution(OmniPlusoftCTI.class.getDeclaredMethod("genericEvent", Map.class), Map.class).execute(event.getArguments(), omniPlusoftCTI);
            } catch (NoSuchMethodException e1) {
                logger.error("Generic Method not exists.", e1);
            }
        }
    }

    @RequestMapping("/sendEvent")
    @ResponseBody
    public void sendEvent (@RequestBody CTIEvent event ) {

        this.messagingTemplate.convertAndSend("/message/cti/eventcallback", event);

    }

}
