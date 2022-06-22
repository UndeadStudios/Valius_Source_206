package com.mayhem.rs2.content.event;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.mayhem.rs2.content.event.impl.BossEvent;
import com.mayhem.rs2.entity.World;
import com.mayhem.rs2.entity.player.Player;

/**
 * @author Andy || ReverendDread Mar 29, 2017
 */
public class EventManager {

	//Fields
	private Event event;
	private int delay;
	private int totalBossEvents = 0;
	
	/**
	 * Constructs a new EventManager object.
	 * @param player 
	 */
	public EventManager() {
		
	}
	
	//Methods	
	
	/**
	 * Processes task on {@link World} thread every game tick.
	 */
	public void process() {
		if (event != null) {
			if (!event.preStartupCheck()) {
				forceStop();
			}
		}
		if (delay > 0) {
			delay--;
			return;
		}
		if (event == null)
			return;
		int delay = event.process();
		if (delay == -1) {
			forceStop();
			return;
		}
		this.delay += delay;
	}
	
	/**
	 * Force stops an event safely.
	 */
	public void forceStop() {
		if (event == null)
			return;
		event.stop();
		event = null;
	}
	
	//Getters and setters
	
	/**
	 * Gets the delay.
	 * @return The delay.
	 */
	public int getEventDelay() {
		return delay;
	}
	 
	/**
	 * Adds delay the the event processing. 
	 * @param delay The event.
	 */
	public void addEventDelay(int delay) {
		this.delay += delay;
	}

	/**
	 * Sets the delay.
	 * @param delay The delay.
	 */
	public void setEventDelay(int delay) {
		this.delay = delay;
	}
	
	/**
	 * Sets the current event and stops the previous one.
	 * @param event {@link Event} The event.
	 * @return
	 */
	public boolean setEvent(Event event) {
		forceStop();
		if (!event.start())
			return false;
		this.event = event;
		return true;
	}
	
	/**
	 * Gets the {@link Event} event.
	 * @return the event.
	 */
	public Event getEvent() {
		return event;
	}
	
	/**
	 * Starts event boss timer.
	 */
	public void appendTimer() {
		Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
			
		    @Override
		    public void run() {
		    	setEvent(new BossEvent());
		    	totalBossEvents++;
		    }
		    
		}, 0, 2, TimeUnit.HOURS);
	}
	
}
