package com.team13.WaitDoc.chats.controller;

import com.team13.WaitDoc.chats.dto.InquiryMessageDto;
import com.team13.WaitDoc.chats.request.InquiryMessageRequest;
import com.team13.WaitDoc.chats.response.SignalResponse;
import com.team13.WaitDoc.chats.service.InquiryMessageService;
import com.team13.WaitDoc.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.team13.WaitDoc.chats.entity.InquiryMessageType.MESSAGE;
import static com.team13.WaitDoc.chats.response.SignalType.NEW_MESSAGE;


@Slf4j
@Controller
@RequiredArgsConstructor
public class InquiryMessageController {

    private final InquiryMessageService inquiryMessageService;

    @MessageMapping("/inquiry/{hospitalInquiryId}/sendMessage")
    @SendTo("/topic/inquiry/{hospitalInquiryId}")
    public SignalResponse sendChatMessage(@DestinationVariable Long hospitalInquiryId, InquiryMessageRequest request,
                                          @AuthenticationPrincipal SecurityUser securityUser)  {

        log.info("content : {}", request.getContent());

        inquiryMessageService.createAndSave(request.getContent(), securityUser.getMemberId(), hospitalInquiryId, MESSAGE);

        return SignalResponse.builder()
                .type(NEW_MESSAGE)
                .build();
    }

    @MessageExceptionHandler
    public void handleException(Exception ex) {
        System.out.println("예외 발생!!");
    }

    @GetMapping("/inquiry/{hospitalInquiryId}/messages")
    @ResponseBody
    public List<InquiryMessageDto> findAll(
            @PathVariable Long hospitalInquiryId, @AuthenticationPrincipal SecurityUser securityUser,
            @RequestParam(defaultValue = "0") Long fromId) {

        List<InquiryMessageDto> inquiryMessageDtos =
                inquiryMessageService.getByChatRoomIdAndUserIdAndFromId(hospitalInquiryId, securityUser.getMemberId(), fromId);

        return inquiryMessageDtos;
    }
}
