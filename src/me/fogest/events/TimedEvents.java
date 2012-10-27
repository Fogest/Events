package me.fogest.events;

public class TimedEvents {
	private Events plugin;
	private MessageHandler m;

	public TimedEvents(Events plugin, MessageHandler m) {
		this.plugin = plugin;
		this.m = m;
	}

	public void scheduleKoTH(int time) {
		Scheduler s = new Scheduler(plugin);
		s.scheduleRepeatAtTime(new Runnable() {
			public void run() {
				m.serverBroadCast("It is time for KoTH"); // This is what happens when it hits the time.
			}
		}, time);

	}
	
	/**
	 * @param time The time in minutes to countdown from.
	 */
	public void countdown(int time) {
		Scheduler s = new Scheduler(plugin);
		s.scheduleCountdown(new Runnable() {
			public void run() {
				m.serverBroadCast( "1 minutes has passed");
			}
		}, time);
	}
}
