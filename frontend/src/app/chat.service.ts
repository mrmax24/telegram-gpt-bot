import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  private chatId: number = 0;

  getChatId(): number {
    return this.chatId;
  }

  setChatId(id: number): void {
    this.chatId = id;
  }
}
