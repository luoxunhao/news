package org.springboot.async.handler;

import org.springboot.async.EventModel;
import org.springboot.async.EventType;

import java.util.List;

public interface EventHandler {

    void doHandle(EventModel model);

    List<EventType> getSupportEventTypes();
}
