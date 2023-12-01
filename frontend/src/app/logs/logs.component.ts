import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { ChatService } from '../chat.service';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import {environment} from "../../../environment";

export interface ChatLogByIdDto {
  id: number,
  fullUsername: string,
  userMessageTime: Date,
  userMessage: string,
  chatResponseTime: Date,
  chatResponse: string,
  adminResponseTime: Date,
  adminResponse: string
}

interface AdminMessageRequestDto {
  adminResponse: string
}

interface AdminMessageResponseDto {
  message: string
}

interface AdminResponseDto {
  email: string
}

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css']
})

export class LogsComponent implements OnInit {
  id: number = 0
  chatId: number = 0
  message: string = ''
  chatLogs: ChatLogByIdDto[] = [];
  admin: AdminResponseDto | undefined;
  isPopupOpen: boolean = false;
  openLogOutPopup: boolean = false;

  constructor(
    private http: HttpClient,
    private authService: AuthService,
    private chatService: ChatService,
    private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    const serverUrl = environment.serverUrl
    this.getAdmin();
    this.route.params.pipe(
      switchMap(params => {
        this.chatId = +params['chatId'];
        return this.http.get<ChatLogByIdDto[]>
        (`${serverUrl}/${this.chatId}`,
          { headers: this.authService.getAuthorizationHeader() });
      })
    ).subscribe(
      (data: ChatLogByIdDto[]) => {
        console.log('Chat logs received:', data);
        this.chatLogs = data;
        this.isPopupOpen = false;
      },
      error => {
        console.error('Error fetching chat list:', error);
      }
    );
  }

  getChatId(): number {
    return this.chatService.getChatId();
  }

  getLogsByChatId() {
    console.log('getLogsByChatId called');
    const chatId = this.getChatId();
    const serverUrl = environment.serverUrl
    const headers = this.authService.getAuthorizationHeader();

    this.http.get<ChatLogByIdDto[]>(`${serverUrl}/${chatId}`, { headers })
      .subscribe(
        (data: ChatLogByIdDto[]) => {
          this.chatLogs = data;
          this.isPopupOpen = false;
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

  sendMessage(message: string) {
    const headers = this.authService.getAuthorizationHeader();
    const serverUrl = environment.serverUrl
    const body: AdminMessageRequestDto = {
      adminResponse: message
    };
    this.http.post<AdminMessageResponseDto>(`${serverUrl}/reply/${this.id}`, body, { headers })
      .subscribe(
        (response: AdminMessageResponseDto) => {
          console.log('Server response:', response.message);
        },
        error => {
          console.error('Error sending message:', error);
        }
      );
    this.closePopup();
  }


  logout() {
    console.log('Logout called');
    this.authService.logout();
  }

  openPopup(id: number) {
    this.isPopupOpen = true;
    this.id = id;
    console.log('Popup should open for log with ID:', id);
  }

  closePopup() {
    this.isPopupOpen = false;
  }
}
