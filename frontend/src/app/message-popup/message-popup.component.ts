import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-message-popup',
  templateUrl: './message-popup.component.html',
  styleUrls: ['./message-popup.component.css'],
})
export class MessagePopupComponent {
  @Output() messageSent = new EventEmitter<string>();
  message: string = '';

  @Input() isOpen: boolean = false;
  @Input() closable: boolean = true;
  @Output() closePopup: EventEmitter<void> = new EventEmitter<void>();
  @Output() sendMessage: EventEmitter<number> = new EventEmitter<number>();
  id: number | undefined;

  popupStyles: any = {};

  openPopup(id: number) {
    this.id = id;
    this.isOpen = true;
  }

  onClosePopup() {
    this.isOpen = false;
    this.closePopup.emit();
  }

  sendPostRequest() {
    if (this.id !== undefined) {
      this.sendMessage.emit(this.id);
    }
  }
}
