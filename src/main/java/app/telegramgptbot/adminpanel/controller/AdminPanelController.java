package app.telegramgptbot.adminpanel.controller;

import app.telegramgptbot.adminpanel.dto.admin.AdminMessageResponseDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogByIdDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogListDto;
import app.telegramgptbot.adminpanel.dto.admin.AdminMessageRequestDto;
import app.telegramgptbot.adminpanel.dto.chatlog.ChatLogResponseDto;
import app.telegramgptbot.adminpanel.mapper.AdminMessageMapper;
import app.telegramgptbot.adminpanel.service.AdminResponseHandler;
import app.telegramgptbot.adminpanel.service.ChatLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class AdminPanelController {
    private final ChatLogService chatLogService;
    private final AdminMessageMapper adminMessageMapper;
    private final AdminResponseHandler adminResponseHandler;

    @GetMapping
    public List<ChatLogListDto> getChatList() {
        return chatLogService.getChatList();
    }

    @GetMapping("/{id}")
    public List<ChatLogByIdDto> getLogsByChatId(@PathVariable Long id) {
        return chatLogService.getLogsByChatId(id);
    }

    @PostMapping("/reply/{id}")
    public AdminMessageResponseDto respondToUser(@PathVariable Long id,
                                                 @Valid @RequestBody AdminMessageRequestDto requestDto) {
        ChatLogResponseDto chatLogResponseDto = chatLogService.getChatLogById(id);
        String adminResponse = adminMessageMapper.mapToModel(requestDto).getAdminResponse();
        return adminResponseHandler.handleAdminResponse(chatLogResponseDto, adminResponse);
    }
}
