package app.telegramgptbot.adminpanel.service.impl;

import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogByIdDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogListDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogRequestDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogResponseDto;
import app.telegramgptbot.adminpanel.mapper.ChatLogMapper;
import app.telegramgptbot.adminpanel.model.ChatLog;
import app.telegramgptbot.adminpanel.repository.ChatLogRepository;
import app.telegramgptbot.adminpanel.service.ChatLogService;
import app.telegramgptbot.adminpanel.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatLogServiceImpl implements ChatLogService {
    private final ChatLogRepository chatLogRepository;
    private final ChatLogMapper chatLogMapper;

    @Override
    public ChatLogResponseDto save(ChatLogRequestDto chatLogRequestDto) {
        ChatLog chatLog = chatLogMapper.mapToModel(chatLogRequestDto);
        return chatLogMapper.mapToDto(chatLogRepository.save(chatLog));
    }

    @Override
    public ChatLogResponseDto getChatLogById(Long id) {
        return chatLogMapper.mapToDto(chatLogRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Couldn't find chat logs by id: " + id)));
    }

    @Override
    public List<ChatLogListDto> getChatList() {
       return chatLogRepository.getChatList();
    }

    @Override
    public List<ChatLogByIdDto> getLogsByChatId(Long chatId) {
        return chatLogRepository.getLogsByChatId(chatId);
    }
}
