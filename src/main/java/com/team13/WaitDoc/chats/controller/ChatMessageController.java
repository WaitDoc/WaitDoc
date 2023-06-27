package com.team13.WaitDoc.chats.controller;

import com.team13.WaitDoc.chats.dto.ChatMessageDto;
import com.team13.WaitDoc.chats.request.ChatMessageRequest;
import com.team13.WaitDoc.chats.response.SignalResponse;
import com.team13.WaitDoc.chats.service.ChatMessageService;
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

import static com.team13.WaitDoc.chats.entity.ChatMessageType.MESSAGE;
import static com.team13.WaitDoc.chats.response.SignalType.NEW_MESSAGE;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chats/{roomId}/sendMessage")
    @SendTo("/topic/chats/{roomId}")
    public SignalResponse sendChatMessage(@DestinationVariable Long roomId, ChatMessageRequest request,
                                          @AuthenticationPrincipal SecurityUser securityUser)  {

        log.info("content : {}", request.getContent());

        chatMessageService.createAndSave(request.getContent(), securityUser.getId(), roomId, MESSAGE);

        return SignalResponse.builder()
                .type(NEW_MESSAGE)
                .build();
    }

    @MessageExceptionHandler
    public void handleException(Exception ex) {
        System.out.println("예외 발생!!");
    }

    @GetMapping("/rooms/{roomId}/messages")
    @ResponseBody
    public List<ChatMessageDto> findAll(
            @PathVariable Long roomId, @AuthenticationPrincipal SecurityUser securityUser,
            @RequestParam(defaultValue = "0") Long fromId) {

        List<ChatMessageDto> chatMessageDtos =
                chatMessageService.getByChatRoomIdAndUserIdAndFromId(roomId, securityUser.getId(), fromId);

        return chatMessageDtos;
    }
}
