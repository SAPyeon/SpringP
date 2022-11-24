package org.sap.service;

import java.util.ArrayList;

import org.sap.mapper.ReplyMapper;
import org.sap.model.ReplyDto;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService{
	
	public final ReplyMapper replymapper;
	
	@Override
	public int reWrite(ReplyDto reply) {
		if(reply.getName() == null || reply.getId() == null) {
			reply.setName("익명");
			reply.setId("익명");
		}
		return replymapper.reWrite(reply);
	}

	@Override
	public ArrayList<ReplyDto> relist(String bno) {
		return replymapper.relist(bno);
	}

	@Override
	public void replDelete(String rno) {
		replymapper.replDelete(rno);
	}

	@Override
	public void replModify(ReplyDto reply) {
		replymapper.replModify(reply);
	}

}
