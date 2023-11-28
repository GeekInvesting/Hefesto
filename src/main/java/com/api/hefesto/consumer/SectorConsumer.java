package com.api.hefesto.consumer;

import com.api.hefesto.dto.SubsectorDto;
import com.api.hefesto.model.SectorModel;
import com.api.hefesto.service.SectorService;
import com.api.hefesto.service.SubsectorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class SectorConsumer {
    private static final Logger LOG = LogManager.getLogger(SectorConsumer.class);

    @Autowired
    private SectorService sectorService;

    @Autowired
    private SubsectorService subsectorService;

    private final ObjectMapper mapper = new ObjectMapper();

    @RabbitListener(queues = "${rabbitmq.queue.sector.hefesto}")
    public void SectorListen(@Payload String sectorMq) {
        LOG.info("Sector Save of Listener: " + sectorMq);
        SubsectorDto subsectorDto = new SubsectorDto();
        if((subsectorDto.getSubsectorName() != null) && (subsectorDto.getSectorName() != null)){
            try {
                subsectorDto = mapper.readValue(sectorMq, SubsectorDto.class);
            } catch (Exception e) {
                LOG.error("Error to convert json to SectorModel: " + e.getMessage());
            }
            SectorModel sectorModel = sectorService.saveSectorByName(subsectorDto.getSectorName());
            subsectorService.saveSubsectorByConsumer(subsectorDto, sectorModel);
        } else {
            LOG.error("Data Sector is null " + sectorMq);
        }
    }

}
