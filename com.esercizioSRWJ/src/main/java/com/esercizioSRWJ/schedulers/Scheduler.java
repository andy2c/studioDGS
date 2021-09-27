package com.esercizioSRWJ.schedulers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
@EnableScheduling
public class Scheduler {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mmm:ss");
	
	@Async
	@Scheduled(cron = "${cron.expression}")
	public void reportCurrentTime() {
		System.out.println("Current time with cron expression = "+dateFormat.format(new Date()));
	}
	
	@Async
	@Scheduled(fixedRate=20*1000)
	public void reportCurrentTimeWithFiedRate() {
		System.out.println("Current time with fixed rate = "+dateFormat.format(new Date()));
	}

}
