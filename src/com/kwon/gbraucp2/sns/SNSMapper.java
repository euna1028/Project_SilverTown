package com.kwon.gbraucp2.sns;

import java.util.List;

import com.kwon.gbraucp2.member.Member;

public interface SNSMapper {
	public abstract int delete(SNSMsg sm);
	public abstract int deleteReply(SNSReply sr);
	public abstract List<SNSMsg> get(SNSSelector sSel);
	public abstract int getMsgCount(SNSSelector sSel);
	public abstract int getMsgCountByWriter(Member m);
	public abstract List<SNSReply> getReply(SNSMsg sm);
	public abstract int update(SNSMsg sm);
	public abstract int write(SNSMsg sm);
	public abstract int writeReply(SNSReply sr);
}



