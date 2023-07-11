package com.team13.WaitDoc.notification.event;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.member.entity.Member;
import com.team13.WaitDoc.waiting.entity.Waiting;
import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {
	private Member member;
	private Hospital hospital;
	private Waiting waiting;

	public NotificationEvent(Object source, Member member, Hospital hospital, Waiting waiting) {
		super(source);
		this.member = member;
		this.hospital = hospital;
		this.waiting = waiting;
	}

	public Member getMember() {
		return member;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public Waiting getWaiting() {
		return waiting;
	}
}