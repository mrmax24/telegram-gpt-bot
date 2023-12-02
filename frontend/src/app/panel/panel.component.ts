import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { ChatService } from '../chat.service';
import { Router } from '@angular/router';
import {environment} from "../../../environment";

interface ChatLogListDto {
  chatId: number,
  tgUsername: string,
  userMessage: string,
  userMessageCount: number,
  chatResponseCount: number,
  adminResponseCount: number,
  maxUserMessageTime: Date
}

interface AdminResponseDto {
  email: string
}

@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrls: ['./panel.component.css']
})
export class PanelComponent implements OnInit {

  chatList: ChatLogListDto[] = [];
  admin: AdminResponseDto | undefined;
  chatId: number | undefined;

  constructor(
    private http: HttpClient,
    private router: Router,
    private authService: AuthService,
    private chatService: ChatService,
  ) {
  }

  ngOnInit(): void {
    this.loadChatLogs();
    this.getAdmin();
  }

  setChatId(id: number): void {
    this.chatService.setChatId(id);
  }

  loadChatLogs(): void {
    const headers = this.authService.getAuthorizationHeader();
    const serverUrl = environment.serverUrl

    this.http.get<ChatLogListDto[]>(`${serverUrl}`, { headers })
      .subscribe(
      (data: ChatLogListDto[]) => {
        this.chatList = data;
        this.setChatId(data[0]?.chatId);
      },
      error => {
        console.error('Error fetching chat list:', error);
      }
    );
  }

  getAdmin() {
    const headers = this.authService.getAuthorizationHeader()
    const serverUrl = environment.serverUrl

    this.http.get<AdminResponseDto>(`${serverUrl}/admin`, { headers })
      .subscribe(
        (data: AdminResponseDto) => {
          this.admin = data;
        },
        error => {
          console.error('Error fetching admin data:', error);
        }
      );
  }

  onChatIdClick(chatId: number): void {
    this.setChatId(chatId);
    this.router.navigate(['/logs', chatId]);
  }

  logout() {
    this.authService.logout();
  }
}
